package com.rx.back.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rx.bean.AccountStatusType;
import com.rx.bean.DeletedType;
import com.rx.bean.UserType;
import com.rx.common.util.FileUploadUtil;
import com.rx.common.util.RequestResultUtil;
import com.rx.entity.User;
import com.rx.entity.UserExtends;
import com.rx.service.back.IUserService;
import com.rx.service.front.IAgentService;
import com.rx.service.front.IUserAgentService;

/**
 * @ClassName UserAgentController
 * @Description 签约客户维护控制器
 * @author Administrator
 * @Date 2017年6月17日 上午11:37:13
 * @version 1.0.0
 */
@Controller
@RequestMapping("/back/agent")
public class UserAgentController {
	final String RESPONSE_THYMELEAF_BACK = "back/thymeleaf/user_agent/";
	final String RESPONSE_JSP = "jsps/front/";
	
	final String DEFAULT_PASSWORD="123456";

	private final int PAGE_SIZE = 8;

	private final Logger log = Logger.getLogger(getClass());

	@Autowired
	IUserAgentService userAgentService;
	@Autowired
	IUserService userService;
	@Autowired
	IAgentService agentService; 

	/**
	 * @Description 显示-签约客户列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/show")
	public String user_agent_show(Model model) {
		return RESPONSE_THYMELEAF_BACK + "user_agent_show";
	}
	
	/**
	 * @Description 
	 * @param pageNum 查询页号
	 * @param pageSize 页大小
	 * @param searchTypeValue 查询字段（以整形表示：0-选择查询条件；1：企业名称；2：负责人姓名；3：电话号码）
	 * @param condValue 查询条件值
	 * @return 
	 */
	@RequestMapping(value = "/agenttable")
	public String user_agent_agenttable(Integer pageNum, Integer pageSize,Integer searchTypeValue,String condValue,Model model) {
		if(pageNum==null || pageNum==0)
		{
			pageNum=1;
			pageSize=PAGE_SIZE;
		}
		//置默认值
		if(searchTypeValue==null){
			searchTypeValue=0;
			condValue="";
		}
		
		// 查询 并分页		
		PageHelper.startPage(pageNum, pageSize); // PageHelper
			
		//TODO 根据查询类型、条件值进行查询
		//List<UserExtends> userAgents = userAgentService.getAllUserAgent();
		List<UserExtends> userAgents = userAgentService.searchUserAgent(searchTypeValue, "%"+condValue+"%");
		PageInfo<UserExtends> pageInfo = new PageInfo<>(userAgents);// (使用了拦截器或是AOP进行查询的再次处理)
		
		model.addAttribute("pageInfo", pageInfo);  					//分页信息
		model.addAttribute("searchTypeValue", searchTypeValue);  	//查询字段值
		model.addAttribute("condValue", condValue);  				//查询条件值
		
		//setPageInfo(model, pageInfo); // 向前台传递分页信息

		model.addAttribute("userAgents", userAgents);

		return RESPONSE_THYMELEAF_BACK + "user_agent_table";
	}
	

	/*private void setPageInfo(Model model, PageInfo pageInfo) {
		// 获得当前页
		model.addAttribute("pageNum", pageInfo.getPageNum());
		// 获得一页显示的条数
		model.addAttribute("pageSize", pageInfo.getPageSize());
		// 是否是第一页
		model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
		// 获得总页数
		model.addAttribute("totalPages", pageInfo.getPages());
		// 是否是最后一页
		model.addAttribute("isLastPage", pageInfo.isIsLastPage());
	}*/

	/**
	 * @Description 增加签约客户 导航至增加签约客户界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String user_agent_add(Model model) {
		return RESPONSE_THYMELEAF_BACK + "user_agent_add";
	}
	
	
	/**
	 * @Description 分配帐号（帐号默认为有效状态）
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/dispatch" ,method=RequestMethod.POST)
	@ResponseBody
	public Object user_agent_dispatch(HttpServletRequest request){
		
		String loginName=request.getParameter("loginName");
		String nickName=request.getParameter("nickName");
		String password=request.getParameter("password");
		long agentId=Long.parseLong(request.getParameter("agentId"));
		
		
		User user=new User();
		user.setCreatedTime(new Date());
		user.setUsername(loginName);
		user.setNickname(nickName);
		String md5Pass=genMD5Password(loginName,password);
		user.setPassword(md5Pass);
		user.setStatus(AccountStatusType.VALID);
		user.setType(UserType.AGENT);  //帐号类型
		user.setDeleted(DeletedType.NO);//删除类型：1=未删除，2=已删除
		
		int row =userService.insertSelective(user);
		if(row>0){
			UserExtends agent=new UserExtends();
			agent.setExtendId(agentId);
			agent.setUserId(user.getId());
			agent.setAccountState(AccountStatusType.VALID);
			userAgentService.updateByPrimaryKeySelective(agent);
		}
		
		return RequestResultUtil.getResultUpdateSuccess();
		
	}
	
	
	/**
	 * @Description 重置口令 (ajax请求)	 
	 * @param userId 用户ID 代理商所分配的用户ID
	 * @return 返回相应的MAP.(成功/警告)
	 */
	@RequestMapping(value="/reset_password")
	@ResponseBody
	public Object user_agent_reset_password(long userId){
		
		//(1)读取原来的登录用户名称
		User user=userService.selectByPrimaryKey(userId);
		String loginName=user.getUsername();
		//(2)计算默认口令的加密结果
		String password=genMD5Password(loginName,DEFAULT_PASSWORD);
		user.setPassword(password);
		//(3)采用默认口令进行更新
		int row=userService.updateByPrimaryKeySelective(user);
		if (row==1){			
			return RequestResultUtil.getResultUpdateWarn("此用户口令已经重置.用户登录名称:"+loginName+";口令:123456");
		}
		else
			return RequestResultUtil.getResultUpdateWarn();
		
	}
	
	
	/**
	 * @Description 查询是否有相同的登录帐号
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/sameloginname" ,method=RequestMethod.POST)
	@ResponseBody
	public Object user_agent_same_loginname(String loginName,HttpServletRequest request){
		User user=new User();
		user.setUsername(loginName);
		
		boolean has=agentService.hasSameLoginName(loginName);
		if(has)
			return RequestResultUtil.getResultSelectSuccess();
		
		
		return RequestResultUtil.getResultSelectWarn();
	}
	
	
	
	
	
	/**
	 * @Description 签约客户-详情（修改）
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/detail/{id}")
	public String user_agent_detail(@PathVariable("id") long extendId, Model model) {
	
		UserExtends agent = userAgentService.selectByPrimaryKey(extendId);
		model.addAttribute("agent", agent);
	
		return RESPONSE_THYMELEAF_BACK + "user_agent_detail";
	}

	/**
	 * @Description 分配帐号（帐号默认为有效状态）
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/setstate" ,method=RequestMethod.POST)
	@ResponseBody
	public Object user_agent_set_state(long agentId,long userId,int accountState, HttpServletRequest request){
		UserExtends agent=new UserExtends();
		agent.setExtendId(agentId);
		agent.setAccountState(accountState);
		userAgentService.updateByPrimaryKeySelective(agent);
		
		User user=new User();
		user.setId(userId);
		user.setStatus(accountState);
		userService.updateByPrimaryKeySelective(user);
		
		return RequestResultUtil.getResultUpdateSuccess(); 
		
		
		
	}
	
	/**
	 * @Description 插入签约客户
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public Object user_agent_insert(HttpServletRequest request, UserExtends agent) {
		System.out.println(agent.getBusinessLicencePicSrc());
		//处理上传文件
		if (!this.processUploadFile(request, agent)) {
			return RequestResultUtil.getResultUploadWarn();
		}

		agent.setCreateDt(new Date());

		int row = userAgentService.addUserAgent(agent);
		if (row > 0) {
			return RequestResultUtil.getResultAddSuccess();
		}

		return RequestResultUtil.getResultAddWarn();
	}

	/**
	 * @Description 更新签约客户
	 * @param agent 签约客户对象
	 * @param request
	 * @return 
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Object user_agent_update(UserExtends agent,HttpServletRequest request) {
		System.out.println(agent.getBusinessLicencePicSrc());
		//处理上传文件
		if (!this.processUploadFile(request, agent)) {
			return RequestResultUtil.getResultUploadWarn();
		}

		int row=userAgentService.updateByPrimaryKeySelective(agent);
		if (row > 0) {
			return RequestResultUtil.getResultUpdateSuccess();
		}

		return RequestResultUtil.getResultUpdateWarn();
	}
	
	/**
	 * @Description 文件上传
	 * @param request
	 * @param brand
	 * @return
	 */
	private boolean processUploadFile(HttpServletRequest request, UserExtends agent) {
		boolean flag = false;
		try {
			//上传营业执照
			String backImgPath = FileUploadUtil.getFile2Upload(request, "back logo", "businessLicencePic_Src");
			if (StringUtils.isNotBlank(backImgPath)) {
				if (!FileUploadUtil.deleteFile(request, agent.getBusinessLicencePicSrc())) {
					log.error("文件不存在或已删除 logo图路径：" + agent.getBusinessLicencePicSrc());
				}
				agent.setBusinessLicencePicSrc(backImgPath);
			}

			//上传税务登录证
			backImgPath = FileUploadUtil.getFile2Upload(request, "back logo", "taxRegistrationCertificatePic_Src");
			if (StringUtils.isNotBlank(backImgPath)) {
				if (!FileUploadUtil.deleteFile(request, agent.getTaxRegistrationCertificatePicSrc())) {
					log.error("文件不存在或已删除 logo图路径：" + agent.getTaxRegistrationCertificatePicSrc());
				}
				agent.setTaxRegistrationCertificatePicSrc(backImgPath);
			}

			//上传组织机构代码证
			backImgPath = FileUploadUtil.getFile2Upload(request, "back logo", "organizationPic_Src");
			if (StringUtils.isNotBlank(backImgPath)) {
				if (!FileUploadUtil.deleteFile(request, agent.getOrganizationPicSrc())) {
					log.error("文件不存在或已删除 logo图路径：" + agent.getOrganizationPicSrc());
				}
				agent.setOrganizationPicSrc(backImgPath);
			}

			flag = true;
		} catch (IOException e) {
			log.error("上传文件异常", e);
		} catch (Exception e) {
			log.error("删除上传文件异常", e);
		}
		return flag;
	}

	/**
	 * @Description 根据用户名及口令生成MD5加密口令
	 * @param loginName
	 * @param password
	 * @return 生成MD5的加密口令
	 */
	private String genMD5Password(String loginName, String password) {
		// user_pass加密规则：UPPER(MD5(CONCAT(user_name,":CNWELL:",user_pass)))
		String pass = loginName + ":CNWELL:" + password;
		// log.debug("md5 password upper : " +
		// DigestUtils.md5Hex(pass.getBytes()).toUpperCase());
		String md5Password = DigestUtils.md5Hex(pass.getBytes()).toUpperCase();
		return md5Password;
	}

}

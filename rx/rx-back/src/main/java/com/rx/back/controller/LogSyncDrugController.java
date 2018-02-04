package com.rx.back.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rx.back.commons.StaticConstants;
import com.rx.bean.PageBean;
import com.rx.bean.UserBean;
import com.rx.common.util.RequestResultUtil;
import com.rx.entity.LogSyncDrug;
import com.rx.service.back.ILogSyncDrugService;

/**
 * Class: LogSyncDrugController
 * 		药品信息同步日志Controller类
 * @author srd 
 * @version 1.0 $Date: 2018年1月22日 下午2:16:02
 */
@Controller
@RequestMapping("/back/log-sync-drug")
public class LogSyncDrugController {

	private final Logger log = Logger.getLogger(getClass());
	
	@Resource(name="logSyncDrugServiceBean")
	private ILogSyncDrugService logSyncDrugService;
	
	/**
	 * 方法功能：查询列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/select-items")
	public ModelAndView selectItems(HttpServletRequest request, HttpServletResponse response, Boolean clickPageBtn, PageBean pageBean, String pagehelperFun) {
		ModelAndView mav = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		UserBean user = (UserBean)subject.getPrincipal();
		
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderBy", "created_date desc");//orderBy:排序 = id desc 或 created_date asc 等
		List<LogSyncDrug> logList = logSyncDrugService.getList(map);
		PageInfo<LogSyncDrug> pagehelper = new PageInfo<LogSyncDrug>(logList);
		
		mav.addObject("pagehelper", pagehelper);
		
		if(clickPageBtn!=null && clickPageBtn){
			mav.setViewName(StaticConstants.LOG_SYNC_DRUG_MANAGE_TABLE_PAGE);
		}else{
			mav.setViewName(StaticConstants.LOG_SYNC_DRUG_MANAGE_PAGE);
		}
		
		mav.addObject("pagehelperFun", pagehelperFun);
		return mav;
	}
	
	/**
	 * 方法功能：查询要修改的信息
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/select-update-by-id")
	@ResponseBody
	public Map<String, Object> selectUpdateById(HttpServletRequest request, HttpServletResponse response, Long id) {
		try {
			LogSyncDrug logSyncDrug = logSyncDrugService.selectByPrimaryKey(id);
			Map<String, Object> respM = RequestResultUtil.getResultSelectSuccess();
			respM.put("logSyncDrug", logSyncDrug);
			return respM;
		} catch (Exception e) {
			log.error("查询异常", e);
			return RequestResultUtil.getResultSelectWarn();
		}
	}
	
}

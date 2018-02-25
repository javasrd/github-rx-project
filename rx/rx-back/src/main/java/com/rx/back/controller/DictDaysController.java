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
import com.rx.entity.DictDays;
import com.rx.service.back.IDictDaysService;

/**
 * Class: DictDaysController
 * 		用药疗程信息维护Controller类
 * @author srd 
 * @version 1.0 $Date: 2018年1月20日 下午2:16:02
 */
@Controller
@RequestMapping("/back/dict-days")
public class DictDaysController {

	private final Logger log = Logger.getLogger(getClass());
	
	@Resource(name="dictDaysServiceBean")
	private IDictDaysService dictDaysService;
	
	/**
	 * 方法功能：查询列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/selectItems")
	public ModelAndView selectLinkItem(HttpServletRequest request, HttpServletResponse response, Boolean clickPageBtn, PageBean pageBean, String pagehelperFun) {
		ModelAndView mav = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		UserBean user = (UserBean)subject.getPrincipal();
		
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put("type", 1);//type=1:默认（后台管理用户）type=2:前端访问用户
		List<DictDays> dictDaysList = dictDaysService.selectAll();//.getList(map);
		PageInfo<DictDays> pagehelper = new PageInfo<DictDays>(dictDaysList);
		
		mav.addObject("pagehelper", pagehelper);
		
		if(clickPageBtn!=null && clickPageBtn){
			mav.setViewName(StaticConstants.DICT_DAYS_MANAGE_TABLE_PAGE);
		}else{
			mav.setViewName(StaticConstants.DICT_DAYS_MANAGE_PAGE);
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
	@RequestMapping("/selectUpdateById")
	@ResponseBody
	public Map<String, Object> selectUpdateById(HttpServletRequest request, HttpServletResponse response, Long id) {
		try {
			DictDays dictDays = dictDaysService.selectByPrimaryKey(id);
			Map<String, Object> respM = RequestResultUtil.getResultSelectSuccess();
			respM.put("dictDays", dictDays);
			return respM;
		} catch (Exception e) {
			log.error("查询异常", e);
			return RequestResultUtil.getResultSelectWarn();
		}
	}
	
	/**
	 * 方法功能：添加
	 * @param request
	 * @param response
	 * @param days
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String, Object> insertContent(HttpServletRequest request, HttpServletResponse response, DictDays days) {
		
		Subject subject = SecurityUtils.getSubject();
		UserBean userBean = (UserBean)subject.getPrincipal();
		if(userBean!=null){
			int rows = dictDaysService.insertSelective(days);
			if(rows>0){
				return RequestResultUtil.getResultAddSuccess();
			}
		}
		return RequestResultUtil.getResultAddWarn();
	}
	
	/**
	 * 方法功能：修改
	 * @param request
	 * @param response
	 * @param days
	 * @return
	 */
	@RequestMapping("/updateById")
	@ResponseBody
	public Map<String, Object> updateById(HttpServletRequest request, HttpServletResponse response, DictDays days) {
		
		try {
			int rows = dictDaysService.updateByPrimaryKeySelective(days);
			if(rows>0){
				return RequestResultUtil.getResultUpdateSuccess();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return RequestResultUtil.getResultUpdateWarn();
	}
	
	/**
	 * 方法功能：物理删除
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteById")
	@ResponseBody
	public Map<String, Object> deleteById(HttpServletRequest request, HttpServletResponse response, Long id) {
		int rows = dictDaysService.deleteByPrimaryKey(id);
		if(rows>0){
			return RequestResultUtil.getResultDeleteSuccess();
		}
		return RequestResultUtil.getResultDeleteWarn();
	}
	
	/**
	 * 方法功能：逻辑删除
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	/*@RequestMapping("/logicDelById")
	@ResponseBody
	public Map<String, Object> logicDelById(HttpServletRequest request, HttpServletResponse response, Long id) {
		int rows = dictTimesService.logicDelById(id);
		if(rows>0){
			return RequestResultUtil.getResultDeleteSuccess();
		}
		return RequestResultUtil.getResultDeleteWarn();
	}*/
	
}
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
import com.rx.entity.LogReceivePatient;
import com.rx.service.back.ILogReceivePatientService;

/**
 * Class: LogReceivePatientController
 * 		接收患者信息日志Controller类
 * @author srd 
 * @version 1.0 $Date: 2018年1月22日 下午2:16:02
 */
@Controller
@RequestMapping("/back/log-receive-patient")
public class LogReceivePatientController {

	private final Logger log = Logger.getLogger(getClass());
	
	@Resource(name="logReceivePatientServiceBean")
	private ILogReceivePatientService logReceivePatientService;
	
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
		map.put("orderBy", "created_time desc");//orderBy:排序 = id desc 或 created_date asc 等
		List<LogReceivePatient> logList = logReceivePatientService.getList(map);
		PageInfo<LogReceivePatient> pagehelper = new PageInfo<LogReceivePatient>(logList);
		
		mav.addObject("pagehelper", pagehelper);
		
		if(clickPageBtn!=null && clickPageBtn){
			mav.setViewName(StaticConstants.LOG_RECEIVE_PATIENT_MANAGE_TABLE_PAGE);
		}else{
			mav.setViewName(StaticConstants.LOG_RECEIVE_PATIENT_MANAGE_PAGE);
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
			LogReceivePatient logReceivePatient = logReceivePatientService.selectByPrimaryKey(id);
			Map<String, Object> respM = RequestResultUtil.getResultSelectSuccess();
			respM.put("logReceivePatient", logReceivePatient);
			return respM;
		} catch (Exception e) {
			log.error("查询异常", e);
			return RequestResultUtil.getResultSelectWarn();
		}
	}
	
}

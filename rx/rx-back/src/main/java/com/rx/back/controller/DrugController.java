package com.rx.back.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rx.back.commons.StaticConstants;
import com.rx.bean.PageBean;
import com.rx.bean.UserBean;
import com.rx.common.util.FileUploadUtil;
import com.rx.common.util.RequestResultUtil;
import com.rx.entity.Drug;
import com.rx.service.back.IDrugService;
import com.rx.service.excelutil.Common;
import com.rx.service.excelutil.ReadExcel;

/**
 * Class: DrugController
 * 		药品信息维护Controller类
 * @author srd 
 * @version 1.0 $Date: 2018年1月22日 下午2:16:02
 */
@Controller
@RequestMapping("/back/drug")
public class DrugController {

	private final Logger log = Logger.getLogger(getClass());
	
	@Resource(name="drugServiceBean")
	private IDrugService drugService;
	
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
		map.put("status", 1);//在售/停售状态  1：在售； 2：停售
		List<Drug> drugList = drugService.getList(map);
		PageInfo<Drug> pagehelper = new PageInfo<Drug>(drugList);
		
		mav.addObject("pagehelper", pagehelper);
		
		if(clickPageBtn!=null && clickPageBtn){
			mav.setViewName(StaticConstants.DRUG_MANAGE_TABLE_PAGE);
		}else{
			mav.setViewName(StaticConstants.DRUG_MANAGE_PAGE);
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
			Drug drug = drugService.selectByPrimaryKey(id);
			Map<String, Object> respM = RequestResultUtil.getResultSelectSuccess();
			respM.put("drug", drug);
			return respM;
		} catch (Exception e) {
			log.error("查询异常", e);
			return RequestResultUtil.getResultSelectWarn();
		}
	}
	
	/**
	 * 上传药品信息EXCEL文件
	 * @param request
	 * @param response
	 */
	@RequestMapping("/upload-drug-file")
	@ResponseBody
	public Map<String, Object> uploadDrugFile(HttpServletRequest request, HttpServletResponse response){
		
		try {
			//获取上传药品信息EXCEL文件
			String drugFilePath = FileUploadUtil.getFile2Upload(request, "drug file", "drugFile");
			if(StringUtils.isNotBlank(drugFilePath)){
				System.out.println("上传成功，药品文件保存目录："+drugFilePath);
				drugFilePath = FileUploadUtil.getRealPath(request)+drugFilePath;
				System.out.println("药品文件在磁盘的真实目录："+drugFilePath);
				Map<String, Object> reqMap = new HashMap<String, Object>();
				reqMap.put(Common.EXCEL_PATH, drugFilePath);
				Map<String, Object> respMap = ReadExcel.readExcel(reqMap);
				String result_code = respMap.get("result_code").toString();
				if(StringUtils.isNotBlank(result_code) && result_code.equalsIgnoreCase("success")){
					String drugListJSON = respMap.get("drugList").toString();
					List<Drug> drugList = JSONArray.parseArray(drugListJSON, Drug.class);
					int rows = drugService.insertListSelective(drugList);
					if(rows>0){
						return RequestResultUtil.getResultSaveSuccess();
					}else{
						return RequestResultUtil.getResultSaveWarn();
					}
				}else{
					return respMap;
				}
				/*if(!FileUploadUtil.deleteFile(request, brand.getBrandLogoUrl())){
					log.error("文件不存在或已删除 logo图路径："+brand.getBrandLogoUrl());
				}*/
				//return RequestResultUtil.getResultUploadSuccess();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return RequestResultUtil.getResultUploadWarn();
	}
	
	/**
	 * 方法功能：添加
	 * @param request
	 * @param response
	 * @param drug
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String, Object> insertContent(HttpServletRequest request, HttpServletResponse response, Drug drug) {
		
		Subject subject = SecurityUtils.getSubject();
		UserBean userBean = (UserBean)subject.getPrincipal();
		if(userBean!=null){
			int rows = drugService.insertSelective(drug);
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
	 * @param drug
	 * @return
	 */
	@RequestMapping("/updateById")
	@ResponseBody
	public Map<String, Object> updateById(HttpServletRequest request, HttpServletResponse response, Drug drug) {
		
		try {
			int rows = drugService.updateByPrimaryKeySelective(drug);
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
		int rows = drugService.deleteByPrimaryKey(id);
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

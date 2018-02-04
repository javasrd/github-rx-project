package com.rx.back.task;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.rx.back.commons.SyncDrugInfoUtil;
import com.rx.entity.Drug;
import com.rx.service.back.IDrugService;
import com.rx.service.impl.back.DrugServiceImpl;

/** 
 * 定时任务
 * 		同步药品信息 
 */
public class SyncDrugInfoTask {
	
	private final static Logger log = Logger.getLogger(SyncDrugInfoUtil.class);
	
	@Resource(name = "drugServiceBean")
	private IDrugService drugService;
	
	public void sync(){
		System.out.println("================================ 定时任务 同步药品信息 开始 ================================");
		List<Drug> drugList = SyncDrugInfoUtil.processSyncDrug();
		this.saveToDB(drugList);
		System.out.println("================================ 定时任务 同步药品信息 结束 ================================");
	}
	
	/**
	 * 药品信息保存到数据库
	 * @param drugList
	 */
	private void saveToDB(List<Drug> drugList){
		if(drugList!=null && drugList.size()>0){
			int rows = drugService.insertListSelective(drugList);
			if (rows > 0) {
				System.out.println("保存数据库成功");
			} else {
				System.out.println("保存数据库异常");
			}
		}else{
			//TODO 解析JSON为空
			log.error("解析JSON为空");
		}
	}
	
}

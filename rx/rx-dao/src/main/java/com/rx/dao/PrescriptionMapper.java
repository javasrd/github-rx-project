package com.rx.dao;

import com.rx.entity.Prescription;
import tk.mybatis.mapper.common.Mapper;

public interface PrescriptionMapper extends Mapper<Prescription> {
	
	/**
	 * @Description: 获取最大的序号
	 * @param
	 *     @param dateCond OYYYYMMDD格式的条件.
	 *     @return   OYYYYMMDDXXXXX 最大的序号
	 * @return 
	 *     String  OYYYYMMDDXXXXX 最大的序号
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月29日-下午6:32:58
	 */
	public String getMaxPrescriptionNo(String dateCond);
}
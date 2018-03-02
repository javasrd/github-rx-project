package com.rx.dao;

import java.util.List;
import java.util.Map;

import com.rx.entity.PrescDrug;

import tk.mybatis.mapper.common.Mapper;

public interface PrescDrugMapper extends Mapper<PrescDrug> {
	
	/**
	 * 根据处方ID查询处方药品信息和处方相关信息
	 * @param prescId
	 * @return
	 */
	public List<Map<String, Object>> getByPrescId(Long prescId);
	
}
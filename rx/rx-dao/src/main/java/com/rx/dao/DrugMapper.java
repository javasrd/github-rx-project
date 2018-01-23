package com.rx.dao;

import java.util.List;
import java.util.Map;

import com.rx.entity.Drug;

import tk.mybatis.mapper.common.Mapper;

public interface DrugMapper extends Mapper<Drug> {
	public List<Map<String,Object>> getDrugByAbc(String abc);
}
package com.rx.dao;

import java.util.List;

import com.rx.entity.Drug;

import tk.mybatis.mapper.common.Mapper;

public interface DrugMapper extends Mapper<Drug> {
	public List<Drug> getDrugByAbc(String abc);
}
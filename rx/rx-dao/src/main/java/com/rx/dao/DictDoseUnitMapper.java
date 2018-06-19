package com.rx.dao;

import java.util.List;
import java.util.Map;

import com.rx.entity.DictDoseUnit;

import tk.mybatis.mapper.common.Mapper;

public interface DictDoseUnitMapper extends Mapper<DictDoseUnit> {
	List<DictDoseUnit> getDoseUnitByAbc(String abc);
	public List<Map<String, Object>> getDoseUnitByDaysName(String name);
}
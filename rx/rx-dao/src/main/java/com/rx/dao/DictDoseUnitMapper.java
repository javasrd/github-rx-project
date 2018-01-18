package com.rx.dao;

import java.util.List;

import com.rx.entity.DictDoseUnit;

import tk.mybatis.mapper.common.Mapper;

public interface DictDoseUnitMapper extends Mapper<DictDoseUnit> {
	List<DictDoseUnit> getDoseUnitByAbc(String abc);
}
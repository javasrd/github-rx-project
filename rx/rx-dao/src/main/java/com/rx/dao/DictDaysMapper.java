package com.rx.dao;

import java.util.List;
import java.util.Map;

import com.rx.entity.DictDays;
import tk.mybatis.mapper.common.Mapper;

public interface DictDaysMapper extends Mapper<DictDays> {
	public List<DictDays> getDaysByAbc(String abc);
	public List<Map<String, Object>> getDaysByDaysName(String name);
}
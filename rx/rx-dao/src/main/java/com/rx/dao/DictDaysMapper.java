package com.rx.dao;

import java.util.List;

import com.rx.entity.DictDays;
import tk.mybatis.mapper.common.Mapper;

public interface DictDaysMapper extends Mapper<DictDays> {
	public List<DictDays> getDaysByAbc(String abc);
}
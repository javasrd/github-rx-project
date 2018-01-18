package com.rx.dao;

import java.util.List;

import com.rx.entity.DictTimes;
import tk.mybatis.mapper.common.Mapper;

public interface DictTimesMapper extends Mapper<DictTimes> {
	public List<DictTimes> getTimesByAbc(String abc);
}
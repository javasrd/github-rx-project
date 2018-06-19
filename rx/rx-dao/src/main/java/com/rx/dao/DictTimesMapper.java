package com.rx.dao;

import java.util.List;
import java.util.Map;

import com.rx.entity.DictTimes;
import tk.mybatis.mapper.common.Mapper;

public interface DictTimesMapper extends Mapper<DictTimes> {
	public List<DictTimes> getTimesByAbc(String abc);
	public List<Map<String, Object>> getTimesByTimesName(String name);
}
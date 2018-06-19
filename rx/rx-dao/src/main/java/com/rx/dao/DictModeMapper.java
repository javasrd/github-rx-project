package com.rx.dao;

import java.util.List;
import java.util.Map;

import com.rx.entity.DictMode;

import tk.mybatis.mapper.common.Mapper;

public interface DictModeMapper extends Mapper<DictMode> {
	public List<DictMode> getModeByAbc(String abc);
	public List<Map<String, Object>> getModeByModeName(String name);
}
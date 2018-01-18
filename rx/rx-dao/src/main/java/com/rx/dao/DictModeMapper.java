package com.rx.dao;

import java.util.List;

import com.rx.entity.DictMode;

import tk.mybatis.mapper.common.Mapper;

public interface DictModeMapper extends Mapper<DictMode> {
	public List<DictMode> getModeByAbc(String abc);
}
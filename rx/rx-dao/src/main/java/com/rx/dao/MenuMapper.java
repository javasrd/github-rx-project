package com.rx.dao;

import java.util.List;

import com.rx.entity.Menu;

import tk.mybatis.mapper.common.Mapper;

public interface MenuMapper extends Mapper<Menu> {
	
	/**
	 * 根据菜单ID集合菜单查询
	 * @param menuIds
	 * @return
	 */
	public List<Menu> getByMenuIds(List<Long> menuIds);
	
}
package com.rx.dao;

import java.util.List;

import com.rx.entity.Drug;

import tk.mybatis.mapper.common.Mapper;

public interface DrugMapper extends Mapper<Drug> {
	/**
	 * 根据助记码查询药品信息
	 * 
	 * @param abc
	 * @return
	 */
	public List<Drug> getDrugByAbc(String abc);
	
	/**
	 * 删除表
	 * truncate是整体删除（速度较快）， delete是逐条删除（速度较慢）。
	 * truncate不写服务器log，delete写服务器log，也就是truncate效率比delete高的原因。
	 * truncate不激活trigger(触发器)，但是会重置Identity（标识列、自增字段），相当于自增列会被置为初始值，又重新从1开始记录，而不是接着原来的ID数。而delete删除以后，Identity依旧是接着被删除的最近的那一条记录ID加1后进行记录。
	 * @return
	 */
	public int deleteAll();
	
	/**
	 * 删除表
	 * truncate是整体删除（速度较快）， delete是逐条删除（速度较慢）。
	 * truncate不写服务器log，delete写服务器log，也就是truncate效率比delete高的原因。
	 * truncate不激活trigger(触发器)，但是会重置Identity（标识列、自增字段），相当于自增列会被置为初始值，又重新从1开始记录，而不是接着原来的ID数。而delete删除以后，Identity依旧是接着被删除的最近的那一条记录ID加1后进行记录。
	 * @return
	 */
	public int truncateAll();
	
}
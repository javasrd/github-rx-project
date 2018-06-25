package com.rx.service.back;

import java.util.List;
import java.util.Map;

import com.rx.entity.Drug;
import com.rx.service.IBaseService;

public interface IDrugService extends IBaseService<Drug, Long> {
	
	/**
	 * 根据map中的条件查询
	 * @param map
	 * 		status:在售/停售状态  1：在售； 2：停售
	 * @return
	 */
	public List<Drug> getList(Map<String, Object> map);
	
	/**
	 * @param drugList
	 * 		批量添加
	 * @return
	 */
	public int insertListSelective(List<Drug> drugList);
	
}

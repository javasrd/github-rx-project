package com.rx.service.inputrx;

import java.util.List;

import com.rx.entity.DictDoseUnit;
import com.rx.service.IBaseService;

/**
 * @ClassName: IDictDoseUnitService 
 * @Description: 字典-剂量单位
 * @author Administrator
 * @date 2018年1月16日-下午4:57:04
 * @version 1.0.0
 */
public interface IDictDoseUnitService extends IBaseService<DictDoseUnit, Long> {
	public List<DictDoseUnit> getDoseUnitByAbc(String abc);
}

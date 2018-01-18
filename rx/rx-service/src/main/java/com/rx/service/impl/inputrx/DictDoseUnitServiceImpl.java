package com.rx.service.impl.inputrx;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rx.dao.DictDoseUnitMapper;
import com.rx.dao.DictModeMapper;
import com.rx.entity.DictDoseUnit;
import com.rx.service.impl.AbstractBaseService;
import com.rx.service.inputrx.IDictDoseUnitService;

@Service
public class DictDoseUnitServiceImpl extends AbstractBaseService<DictDoseUnit, Long> implements IDictDoseUnitService {

	DictDoseUnitMapper dictDoseUnitMapper;
	
	/**
	 * @Description: set方式注入
	 * @param
	 *     @param mapper   
	 * @return 
	 *     void  
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月16日-上午10:05:15
	 */
	public void setDictDoseUnitMapper(DictDoseUnitMapper dictDoseUnitMapper) {
		this.dictDoseUnitMapper=dictDoseUnitMapper;
		this.setMapper(dictDoseUnitMapper);
	}

	@Override
	public List<DictDoseUnit> getDoseUnitByAbc(String abc) {
		return dictDoseUnitMapper.getDoseUnitByAbc(abc);
	}

}

package com.rx.service.impl.back;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rx.dao.DictDoseUnitMapper;
import com.rx.entity.DictDoseUnit;
import com.rx.service.back.IDictDoseUnitService;
import com.rx.service.impl.AbstractBaseService;

import tk.mybatis.mapper.entity.Example;

@Service("dictDoseUnitServiceBean")
public class DictDoseUnitServiceImpl extends AbstractBaseService<DictDoseUnit, Long> implements IDictDoseUnitService {

	private DictDoseUnitMapper dictDoseUnitMapper;

	/**
	 * @param dictDoseUnitMapper the dictDoseUnitMapper to set
	 * set方式注入
	 */
	public void setDictDoseUnitMapper(DictDoseUnitMapper dictDoseUnitMapper) {
		this.dictDoseUnitMapper = dictDoseUnitMapper;
		this.setMapper(dictDoseUnitMapper);
	}

	@Override
	public List<DictDoseUnit> getList(Map<String, Object> map) {
		Example example=new Example(DictDoseUnit.class);
		//example.createCriteria().andEqualTo("type", map.get("type").toString());
		List<DictDoseUnit> list=dictDoseUnitMapper.selectByExample(example);
		return list;
	}

}

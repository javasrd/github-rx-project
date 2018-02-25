package com.rx.service.impl.back;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rx.dao.DictDaysMapper;
import com.rx.entity.DictDays;
import com.rx.service.back.IDictDaysService;
import com.rx.service.impl.AbstractBaseService;

import tk.mybatis.mapper.entity.Example;

@Service("dictDaysServiceBean")
public class DictDaysServiceImpl extends AbstractBaseService<DictDays, Long> implements IDictDaysService {

	private DictDaysMapper dictDaysMapper;

	/**
	 * @param dictDaysMapper the dictDaysMapper to set
	 * set方式注入
	 */
	public void setDictDaysMapper(DictDaysMapper dictDaysMapper) {
		this.dictDaysMapper = dictDaysMapper;
		this.setMapper(dictDaysMapper);
	}

	@Override
	public List<DictDays> getList(Map<String, Object> map) {
		Example example=new Example(DictDays.class);
		//example.createCriteria().andEqualTo("type", map.get("type").toString());
		List<DictDays> list=dictDaysMapper.selectByExample(example);
		return list;
	}
	
}

package com.rx.service.impl.back;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rx.dao.DictTimesMapper;
import com.rx.entity.DictTimes;
import com.rx.entity.User;
import com.rx.service.back.IDictTimesService;
import com.rx.service.impl.AbstractBaseService;

import tk.mybatis.mapper.entity.Example;

@Service("dictTimesServiceBean")
public class DictTimesServiceImpl extends AbstractBaseService<DictTimes, Long> implements IDictTimesService {

	private DictTimesMapper dictTimesMapper;

	/**
	 * @param dictTimesMapper the dictTimesMapper to set
	 * set方式注入
	 */
	public void setDictTimesMapper(DictTimesMapper dictTimesMapper) {
		this.dictTimesMapper = dictTimesMapper;
		this.setMapper(dictTimesMapper);
	}

	@Override
	public List<DictTimes> getList(Map<String, Object> map) {
		Example example=new Example(User.class);
		example.createCriteria().andEqualTo("type", map.get("type").toString());
		List<DictTimes> list=dictTimesMapper.selectByExample(example);
		return list;
	}
	
}

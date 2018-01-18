package com.rx.service.impl.inputrx;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rx.dao.DictModeMapper;
import com.rx.dao.DictTimesMapper;
import com.rx.entity.DictTimes;
import com.rx.service.impl.AbstractBaseService;
import com.rx.service.inputrx.IDictTimesService;

@Service
public class DictTimesServiceImpl extends AbstractBaseService<DictTimes, Long> implements IDictTimesService {

	DictTimesMapper dictTimesMapper;
	
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
	public void setDictTimesMapper(DictTimesMapper dictTimesMapper) {
		this.dictTimesMapper=dictTimesMapper;
		this.setMapper(dictTimesMapper);
	}

	@Override
	public List<DictTimes> getTimesByAbc(String abc) {
		return dictTimesMapper.getTimesByAbc(abc);
	}

}

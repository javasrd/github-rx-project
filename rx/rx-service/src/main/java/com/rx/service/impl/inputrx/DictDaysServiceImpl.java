package com.rx.service.impl.inputrx;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rx.dao.DictDaysMapper;
import com.rx.entity.DictDays;
import com.rx.service.impl.AbstractBaseService;
import com.rx.service.inputrx.IDictDaysService;

@Service
public class DictDaysServiceImpl extends AbstractBaseService<DictDays, Long> implements IDictDaysService {

	DictDaysMapper dictDaysMapper;
	
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
	public void setDictDaysMapper(DictDaysMapper dictDaysMapper) {
		this.dictDaysMapper=dictDaysMapper;
		this.setMapper(dictDaysMapper);
	}

	@Override
	public List<DictDays> getDaysByAbc(String abc) {		
		return dictDaysMapper.getDaysByAbc(abc);
	}

	@Override
	public List<Map<String, Object>> getDaysByDaysName(String name) {
		return dictDaysMapper.getDaysByDaysName(name);		
	}
	
	
	

}

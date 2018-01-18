package com.rx.service.impl.inputrx;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rx.dao.DictModeMapper;
import com.rx.entity.DictMode;
import com.rx.service.impl.AbstractBaseService;
import com.rx.service.inputrx.IDictModeService;

@Service
public class DictModeServiceImpl extends AbstractBaseService<DictMode, Long> implements IDictModeService {

	DictModeMapper dictModeMapper;
	
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
	public void setDictModeMapper(DictModeMapper dictModeMapper) {
		this.dictModeMapper=dictModeMapper;
		this.setMapper(dictModeMapper);
	}

	@Override
	public List<DictMode> getModeByAbc(String abc) {
		return dictModeMapper.getModeByAbc(abc);
	}

}

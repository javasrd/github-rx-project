package com.rx.service.impl.inputrx;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rx.dao.DictCommonMapper;
import com.rx.entity.DictCommon;
import com.rx.service.impl.AbstractBaseService;
import com.rx.service.inputrx.IDictCommonService;

@Service
public class DictCommonServiceImpl extends AbstractBaseService<DictCommon, Long> implements IDictCommonService {

	DictCommonMapper dictCommonMapper;
	
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
	public void setDictCommonMapper(DictCommonMapper dictCommonMapper) {
		this.dictCommonMapper=dictCommonMapper;
		this.setMapper(dictCommonMapper);
	}

	@Override
	public List<DictCommon> getDictCommonByCode(String code) {
		DictCommon rec=new DictCommon();
		rec.setCode(code);
		rec.setDeleted(1);
		return dictCommonMapper.select(rec);
	}

}

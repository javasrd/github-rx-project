package com.rx.service.impl.inputrx;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rx.dao.DispensaryMapper;
import com.rx.dao.DrugMapper;
import com.rx.entity.Dispensary;
import com.rx.entity.Drug;
import com.rx.service.impl.AbstractBaseService;
import com.rx.service.inputrx.IDispensaryService;

@Service
public class DispensaryServiceImpl extends AbstractBaseService<Dispensary, Long> implements IDispensaryService {

	DispensaryMapper dispensaryMapper;
	
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
	public void setDispensaryMapper(DispensaryMapper dispensaryMapper) {
		this.dispensaryMapper=dispensaryMapper;
		this.setMapper(dispensaryMapper);
	}

}

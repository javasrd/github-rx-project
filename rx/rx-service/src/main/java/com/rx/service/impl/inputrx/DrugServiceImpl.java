package com.rx.service.impl.inputrx;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rx.dao.DrugMapper;
import com.rx.entity.Drug;
import com.rx.service.impl.AbstractBaseService;
import com.rx.service.inputrx.IDrugService;

@Service
public class DrugServiceImpl extends AbstractBaseService<Drug, Long> implements IDrugService {

	DrugMapper drugMapper;
	
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
	public void setDrugMapper(DrugMapper drugMapper) {
		this.drugMapper=drugMapper;
		this.setMapper(drugMapper);
	}

	@Override
	public long addDrug(Drug drug) {
		Drug rec=drug;
		//保存
		int row=drugMapper.insertSelective(rec);		
		if(row>0)
			return rec.getId();
		else
			return 0;	
	}

	@Override
	public List<Map<String,Object>> getDrugByAbc(String abc) {
		return drugMapper.getDrugByAbc(abc);		
	}

}

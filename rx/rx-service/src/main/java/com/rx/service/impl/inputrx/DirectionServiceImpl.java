package com.rx.service.impl.inputrx;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.rx.dao.DirectionMapper;
import com.rx.entity.Direction;
import com.rx.service.impl.AbstractBaseService;
import com.rx.service.inputrx.IDirectionService;

@Service
public class DirectionServiceImpl extends AbstractBaseService<Direction, Long> implements IDirectionService {

	DirectionMapper directionMapper;
	
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
	public void setDirectionMapper(DirectionMapper directionMapper) {
		this.directionMapper=directionMapper;
		this.setMapper(directionMapper);
	}

	@Override
	public long addDirection(long presc_drug_id, String mode, String times, BigDecimal dosage, String doseunit,int days) {
		Direction rec=new Direction();
		
		//设定对象属性
		rec.setPrescDrugId(presc_drug_id);
		rec.setMode(mode);
		rec.setTimes(times);
		rec.setDosage(dosage);
		rec.setDoseunit(doseunit);
		rec.setDays(days);
		
		
		//保存
		int row=directionMapper.insertSelective(rec);
		
		if(row>0)
			return rec.getId();
		else
			return 0;
	}

}

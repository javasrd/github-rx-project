package com.rx.service.inputrx;

import java.math.BigDecimal;

import com.rx.entity.Direction;
import com.rx.service.IBaseService;

/**
 * @ClassName: IDirectionService
 * @Description: 用药指导 
 * @author Administrator
 * @date 2018年1月16日-下午3:40:07
 * @version 1.0.0
 */
public interface IDirectionService extends IBaseService<Direction, Long> {
	/**
	 * @Description: 增加用药指导
	 * @param
	 *     @param presc_drug_id 处方药id
	 *     @param mode 给药方式
	 *     @param times 给药次数
	 *     @param dosage 剂量
	 *     @param doseunit 剂量单位
	 *     @return   
	 * @return 
	 *     long  用药指导id
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月16日-下午3:44:10
	 */
	public long addDirection(long presc_drug_id,String mode,String times,BigDecimal dosage,String doseunit,int days);
	
	
}

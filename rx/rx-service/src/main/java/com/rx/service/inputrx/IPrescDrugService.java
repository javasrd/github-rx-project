package com.rx.service.inputrx;

import com.rx.entity.Drug;
import com.rx.entity.PrescDrug;
import com.rx.service.IBaseService;


/**
 * @ClassName: IPrescDrugService
 * @Description: 处方中药品关系
 * @author Administrator
 * @date 2018年1月16日-下午3:56:43
 * @version 1.0.0
 */
public interface IPrescDrugService extends IBaseService<PrescDrug, Long> {
	
	/**
	 * @Description: 增加处方-药品关系
	 * @param
	 *     @param presc_id 处方id
	 *     @param drug_id  药品id
	 *     @return   
	 * @return 
	 *     long  插入记录id
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月16日-下午3:58:52
	 */
	public long addPrescDrug(long presc_id,long drug_id);
	
	/**
	 * @Description: 增加处方-药品关系
	 * @param
	 *     @param presc_id 处方id
	 *     @param drug 药品
	 *     @return   
	 * @return 
	 *     long  关系id
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月23日-上午5:45:25
	 */
	public long addPrescDrug(long presc_id,Drug drug,int quantity);
		
}

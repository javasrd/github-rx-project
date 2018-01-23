package com.rx.service.inputrx;

import java.util.List;
import java.util.Map;

import com.rx.entity.Drug;
import com.rx.service.IBaseService;


/**
 * @ClassName: IDrugService
 * @Description: 药品-服务接口
 * @author Administrator
 * @date 2018年1月16日-下午4:04:31
 * @version 1.0.0
 */
public interface IDrugService extends IBaseService<Drug, Long> {
	
	/**
	 * @Description: 增加药品
	 * @param
	 *     @param drug 
	 *     @return   
	 * @return 
	 *     long  插入记录id
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月16日-下午4:15:33
	 */
	public long addDrug(Drug drug);
	
	/**
	 * @Description: 根据助记码模糊查询药品
	 * @param
	 *     @param abc 助词码
	 *     @return    药品目录列表
	 * @return 
	 *     List<Drug>  药品目录列表
	 * @throws 
	 * @author Administrator
	 * @date 2018年1月18日-上午8:31:46
	 */
	public List<Map<String,Object>> getDrugByAbc(String abc);
}

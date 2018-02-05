package com.rx.service.inputrx;

import java.util.List;

import com.rx.entity.DictDays;
import com.rx.service.IBaseService;

/**
 * @ClassName: IDictDaysService
 * @Description: 疗程数据字典
 * @author Administrator
 * @date 2018年2月5日-下午11:48:58
 * @version 1.0.0
 */
public interface IDictDaysService extends IBaseService<DictDays, Long> {
	
	/**
	 * @Description: 根据查询码查询
	 * @param
	 *     @param abc
	 *     @return   
	 * @return 
	 *     List<DictDays>  
	 * @throws 
	 * @author Administrator
	 * @date 2018年2月5日-下午11:53:14
	 */
	public List<DictDays> getDaysByAbc(String abc);
}

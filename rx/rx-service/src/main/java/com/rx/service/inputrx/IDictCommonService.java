package com.rx.service.inputrx;

import java.util.List;

import com.rx.entity.DictCommon;
import com.rx.service.IBaseService;

/**
 * @ClassName: IDictCommonService
 * @Description: 通用数据字典
 * @author Administrator
 * @date 2018年2月1日-下午3:26:28
 * @version 1.0.0
 */
public interface IDictCommonService extends IBaseService<DictCommon, Long> {
	/**
	 * @Description: 通过code读取通用数据字典中的数据
	 * @param
	 *     @param code  编码
	 *     @return      对应的数据字典列表
	 * @return 
	 *     DictCommon  如果查询到则返回DictCommon列表对象
	 * @throws 
	 * @author Administrator
	 * @date 2018年2月2日-下午4:10:43
	 */
	public List<DictCommon> getDictCommonByCode(String code);
}

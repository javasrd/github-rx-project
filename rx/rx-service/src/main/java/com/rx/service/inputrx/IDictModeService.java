package com.rx.service.inputrx;

import java.util.List;

import com.rx.entity.DictMode;
import com.rx.service.IBaseService;


/**
 * @ClassName: IDictModeService
 * @Description: 字典-给药方式
 * @author Administrator
 * @date 2018年1月16日-下午4:45:19
 * @version 1.0.0
 */
public interface IDictModeService extends IBaseService<DictMode, Long> {
	
	public List<DictMode> getModeByAbc(String abc);
}

package com.rx.service.impl.inputrx;

import org.springframework.stereotype.Service;

import com.rx.dao.TemplateDetailMapper;
import com.rx.entity.TemplateDetail;
import com.rx.service.impl.AbstractBaseService;
import com.rx.service.inputrx.ITemplateDetailService;

@Service
public class TemplateDetailServiceImpl extends AbstractBaseService<TemplateDetail, Long> implements ITemplateDetailService {

	TemplateDetailMapper templateDetailMapper;
	
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
	public void setTemplateDetailMapper(TemplateDetailMapper templateDetailMapper) {
		this.templateDetailMapper=templateDetailMapper;
		this.setMapper(templateDetailMapper);
	}


}

package com.rx.service.impl.inputrx;

import org.springframework.stereotype.Service;

import com.rx.dao.TemplateMapper;
import com.rx.entity.Template;
import com.rx.service.impl.AbstractBaseService;
import com.rx.service.inputrx.ITemplateService;

@Service
public class TemplateServiceImpl extends AbstractBaseService<Template, Long> implements ITemplateService {

	TemplateMapper templateMapper;
	
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
	public void setTemplateMapper(TemplateMapper templateMapper) {
		this.templateMapper=templateMapper;
		this.setMapper(templateMapper);		
	}
	
	


}

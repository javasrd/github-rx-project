package com.rx.service.impl.front;

import org.springframework.stereotype.Service;

import com.rx.dao.ContractTemplateMapper;
import com.rx.entity.ContractTemplate;
import com.rx.service.front.IContractTemplateService;
import com.rx.service.impl.AbstractBaseService;

@Service
public class ContractTemplateServiceImpl extends AbstractBaseService<ContractTemplate, Long> implements IContractTemplateService {

	private ContractTemplateMapper contractTemplateMapper;

	/**
	 * @param the mapper to set
	 * set方式注入
	 */	
	
	public void setContractTemplateMapper(ContractTemplateMapper contractTemplateMapper) {
		this.contractTemplateMapper=contractTemplateMapper;
		this.setMapper(contractTemplateMapper);
	}
	

}

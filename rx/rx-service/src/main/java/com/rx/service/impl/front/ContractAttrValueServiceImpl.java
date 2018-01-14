package com.rx.service.impl.front;

import org.springframework.stereotype.Service;

import com.rx.dao.ContractAttrValueMapper;
import com.rx.entity.ContractAttrValue;
import com.rx.service.front.IContractAttrValueService;
import com.rx.service.impl.AbstractBaseService;

@Service
public class ContractAttrValueServiceImpl extends AbstractBaseService<ContractAttrValue, Long> implements IContractAttrValueService {

	private ContractAttrValueMapper contractAttrValueMapper;

	/**
	 * @param the mapper to set
	 * set方式注入
	 */	
	
	public void setContractAttrValueMapper(ContractAttrValueMapper contractAttrValueMapper) {
		this.contractAttrValueMapper=contractAttrValueMapper;
		this.setMapper(contractAttrValueMapper);
	}
	

}

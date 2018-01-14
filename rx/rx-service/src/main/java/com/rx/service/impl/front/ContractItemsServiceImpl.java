package com.rx.service.impl.front;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rx.dao.ContractItemsMapper;
import com.rx.entity.ContractItems;
import com.rx.service.front.IContractItemsService;
import com.rx.service.impl.AbstractBaseService;

@Service
public class ContractItemsServiceImpl extends AbstractBaseService<ContractItems, Long> implements IContractItemsService {

	private ContractItemsMapper contractItemsMapper;

	/**
	 * @param  the mapper to set 
	 * set方式注入
	 */	
	
	public void setContractItemsMapper(ContractItemsMapper contractItemsMapper) {
		this.contractItemsMapper=contractItemsMapper;
		this.setMapper(contractItemsMapper);
	}
	
	public List<Map<String,Object>> selectItemsByContractNo(String contractNo) {
		return contractItemsMapper.selectItemsByContractNo(contractNo);
	}
	

}

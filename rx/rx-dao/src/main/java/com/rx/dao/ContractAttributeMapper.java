package com.rx.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rx.entity.ContractAttribute;

import tk.mybatis.mapper.common.Mapper;

public interface ContractAttributeMapper extends Mapper<ContractAttribute> {
	
	public List<Map<String, String>> selectAttrValByContractId(@Param("contractId") long contractId);
}
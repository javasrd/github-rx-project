package com.rx.service.impl.back;

import org.springframework.stereotype.Service;

import com.rx.dao.CompanyInfoMapper;
import com.rx.entity.CompanyInfo;
import com.rx.service.back.ICompanyInfoService;
import com.rx.service.impl.AbstractBaseService;

@Service
public class CompanyInfoServiceImpl extends AbstractBaseService<CompanyInfo, Long> implements ICompanyInfoService {

	private CompanyInfoMapper companyInfoMapper;

	/**
	 * @param brandMapper the brandMapper to set
	 * set方式注入
	 */
	public void setCompanyInfoMapper(CompanyInfoMapper companyInfoMapper) {
		this.companyInfoMapper = companyInfoMapper;
		this.setMapper(companyInfoMapper);
	}

}

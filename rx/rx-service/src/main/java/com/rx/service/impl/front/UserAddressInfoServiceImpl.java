package com.rx.service.impl.front;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rx.bean.UserAddressType;
import com.rx.dao.UserAddressInfoMapper;
import com.rx.entity.UserAddressInfo;
import com.rx.service.front.IUserAddressInfoService;
import com.rx.service.impl.AbstractBaseService;

@Service
public class UserAddressInfoServiceImpl extends AbstractBaseService<UserAddressInfo, Long> implements IUserAddressInfoService {
	
	UserAddressInfoMapper userAddressInfoMapper;
	
	/**
	 * @param mapper
	 * the mapper to set set方式注入
	 */	
	public void setUserAddressInfoMapper(UserAddressInfoMapper userAddressInfoMapper) { 
		this.userAddressInfoMapper=userAddressInfoMapper;
		this.setMapper(userAddressInfoMapper);
	}

	@Override
	public List<UserAddressInfo> selectByBuyerId(long buyerId) {
		UserAddressInfo record=new UserAddressInfo();
		record.setBuyerId(buyerId);
		return userAddressInfoMapper.select(record);
		
	}

	@Override
	public UserAddressInfo selectBuyerDefaultAddress(long buyerId) {
		UserAddressInfo record=new UserAddressInfo();
		record.setBuyerId(buyerId);
		record.setIsDefault(UserAddressType.DEFAULT);
		return userAddressInfoMapper.selectOne(record);
	}	

}

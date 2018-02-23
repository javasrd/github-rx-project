package com.rx.service.impl.back;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rx.bean.DeletedType;
import com.rx.dao.HospitalMapper;
import com.rx.entity.Hospital;
import com.rx.entity.User;
import com.rx.service.back.IHospitalService;
import com.rx.service.impl.AbstractBaseService;

import tk.mybatis.mapper.entity.Example;

@Service("hospitalServiceBean")
public class HospitalServiceImpl extends AbstractBaseService<Hospital, Long> implements IHospitalService {

	private HospitalMapper hospitalMapper;

	/**
	 * @param hospitalMapper the hospitalMapper to set
	 * set方式注入
	 */
	public void setHospitalMapper(HospitalMapper hospitalMapper) {
		this.hospitalMapper = hospitalMapper;
		this.setMapper(hospitalMapper);
	}

	@Override
	public List<Hospital> getList(Map<String, Object> map) {
		Example example=new Example(User.class);
		example.createCriteria().andEqualTo("deleted", DeletedType.NO);
		List<Hospital> list=hospitalMapper.selectByExample(example);
		return list;
	}
	
	@Override
	public int logicDelById(Long id) {
		Hospital hospital = new Hospital();
		hospital.setId(id);
		hospital.setDeleted(DeletedType.YES);
		return hospitalMapper.updateByPrimaryKeySelective(hospital);
	}
	
}

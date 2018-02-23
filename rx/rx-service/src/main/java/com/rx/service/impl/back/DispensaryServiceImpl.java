package com.rx.service.impl.back;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rx.bean.DeletedType;
import com.rx.dao.DispensaryMapper;
import com.rx.entity.Dispensary;
import com.rx.entity.Hospital;
import com.rx.service.back.IDispensaryService;
import com.rx.service.impl.AbstractBaseService;

import tk.mybatis.mapper.entity.Example;

@Service("dispensaryServiceBean")
public class DispensaryServiceImpl extends AbstractBaseService<Dispensary, Long> implements IDispensaryService {

	private DispensaryMapper dispensaryMapper;

	/**
	 * @param dispensaryMapper the dispensaryMapper to set
	 * set方式注入
	 */
	public void setDispensaryMapper(DispensaryMapper dispensaryMapper) {
		this.dispensaryMapper = dispensaryMapper;
		this.setMapper(dispensaryMapper);
	}

	@Override
	public List<Dispensary> getList(Map<String, Object> map) {
		Example example=new Example(Dispensary.class);
		example.createCriteria().andEqualTo("deleted", DeletedType.NO);
		List<Dispensary> list=dispensaryMapper.selectByExample(example);
		return list;
	}
	
	@Override
	public int logicDelById(Long id) {
		Dispensary dispensary = new Dispensary();
		dispensary.setId(id);
		dispensary.setDeleted(DeletedType.YES);
		return dispensaryMapper.updateByPrimaryKeySelective(dispensary);
	}
	
}

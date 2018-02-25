package com.rx.service.impl.back;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rx.bean.DeletedType;
import com.rx.dao.DictCommonMapper;
import com.rx.entity.DictCommon;
import com.rx.service.back.IDictCommonService;
import com.rx.service.impl.AbstractBaseService;

import tk.mybatis.mapper.entity.Example;

@Service("dictCommonServiceBean")
public class DictCommonServiceImpl extends AbstractBaseService<DictCommon, Long> implements IDictCommonService {

	private DictCommonMapper dictCommonMapper;

	/**
	 * @param dictCommonMapper the dictCommonMapper to set
	 * set方式注入
	 */
	public void setDictCommonMapper(DictCommonMapper dictCommonMapper) {
		this.dictCommonMapper = dictCommonMapper;
		this.setMapper(dictCommonMapper);
	}

	@Override
	public List<DictCommon> getList(Map<String, Object> map) {
		Example example=new Example(DictCommon.class);
		example.createCriteria().andEqualTo("deleted", DeletedType.NO);
		List<DictCommon> list=dictCommonMapper.selectByExample(example);
		return list;
	}

	@Override
	public String getUrl(String code) {
		Example example=new Example(DictCommon.class);
		example.createCriteria().andEqualTo("deleted", DeletedType.NO).andEqualTo("code", code);
		List<DictCommon> commonList=dictCommonMapper.selectByExample(example);
		if(commonList!=null && commonList.size()>0 ){
			return commonList.get(0).getName();
		}
		return null;
	}
	
	@Override
	public int logicDelById(Long id) {
		DictCommon common = new DictCommon();
		common.setId(id);
		common.setDeleted(DeletedType.YES);
		return dictCommonMapper.updateByPrimaryKeySelective(common);
	}

}

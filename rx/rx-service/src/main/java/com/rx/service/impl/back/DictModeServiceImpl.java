package com.rx.service.impl.back;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rx.dao.DictModeMapper;
import com.rx.entity.DictMode;
import com.rx.entity.User;
import com.rx.service.back.IDictModeService;
import com.rx.service.impl.AbstractBaseService;

import tk.mybatis.mapper.entity.Example;

@Service("dictModeServiceBean")
public class DictModeServiceImpl extends AbstractBaseService<DictMode, Long> implements IDictModeService {

	private DictModeMapper dictModeMapper;

	/**
	 * @param dictModeMapper the dictModeMapper to set
	 * set方式注入
	 */
	public void setDictModeMapper(DictModeMapper dictModeMapper) {
		this.dictModeMapper = dictModeMapper;
		this.setMapper(dictModeMapper);
	}

	@Override
	public List<DictMode> getList(Map<String, Object> map) {
		Example example=new Example(User.class);
		example.createCriteria().andEqualTo("type", map.get("type").toString());
		List<DictMode> list=dictModeMapper.selectByExample(example);
		return list;
	}

}

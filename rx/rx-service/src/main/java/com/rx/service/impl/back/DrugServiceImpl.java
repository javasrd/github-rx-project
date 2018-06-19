package com.rx.service.impl.back;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.rx.dao.DrugMapper;
import com.rx.entity.Drug;
import com.rx.entity.User;
import com.rx.service.back.IDrugService;
import com.rx.service.impl.AbstractBaseService;

import tk.mybatis.mapper.entity.Example;

@Service("drugServiceBean")
public class DrugServiceImpl extends AbstractBaseService<Drug, Long> implements IDrugService {

	private final Logger log = Logger.getLogger(getClass());
	
	private DrugMapper drugMapper;

	/**
	 * @param drugMapper the drugMapper to set
	 * set方式注入
	 */
	public void setDrugMapper(DrugMapper drugMapper) {
		this.drugMapper = drugMapper;
		this.setMapper(drugMapper);
	}

	@Override
	public List<Drug> getList(Map<String, Object> map) {
		Example example=new Example(User.class);
		/*在售/停售状态  1：在售； 2：停售*/
		example.createCriteria().andEqualTo("status", map.get("status").toString());
		List<Drug> list=drugMapper.selectByExample(example);
		return list;
	}

	@Override
	@Transactional
	public int insertListSelective(List<Drug> drugList) {
		int rows = 0;
		try {
			rows = drugMapper.truncateAll();
			log.info("清空药品信息表");
			for(int i=0; i<drugList.size(); i++){
				Drug temp = drugList.get(i);
				rows = drugMapper.insertSelective(temp);
				if(rows<=0){
					log.error("保存内容到数据库异常，异常数据："+temp);
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				}
			}
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return 0;
	}
	
}

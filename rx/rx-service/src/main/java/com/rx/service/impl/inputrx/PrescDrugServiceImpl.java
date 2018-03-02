package com.rx.service.impl.inputrx;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rx.dao.PrescDrugMapper;
import com.rx.entity.Drug;
import com.rx.entity.PrescDrug;
import com.rx.service.impl.AbstractBaseService;
import com.rx.service.inputrx.IPrescDrugService;

@Service
public class PrescDrugServiceImpl extends AbstractBaseService<PrescDrug, Long> implements IPrescDrugService {

	PrescDrugMapper prescDrugMapper;

	/**
	 * @Description: set方式注入
	 * @param
	 * @param mapper
	 * @return void
	 * @throws @author
	 *             Administrator
	 * @date 2018年1月16日-上午10:05:15
	 */
	public void setPrescDrugMapper(PrescDrugMapper prescDrugMapper) {
		this.prescDrugMapper = prescDrugMapper;
		this.setMapper(prescDrugMapper);
	}

	@Override
	public long addPrescDrug(long presc_id, long drug_id) {
		PrescDrug rec = new PrescDrug();

		// 设定对象属性
		rec.setCreatedTime(new Date());
		rec.setPrescId(presc_id);
		rec.setDrugId(drug_id);

		// 保存
		int row = prescDrugMapper.insertSelective(rec);

		if (row > 0)
			return rec.getId();
		else
			return 0;
	}

	@Override
	public long addPrescDrug(long presc_id, Drug drug,int quantity) {
		PrescDrug rec = new PrescDrug();
		// 设定对象属性
		rec.setCreatedTime(new Date());
		rec.setPrescId(presc_id);
		rec.setDrugId(drug.getId());
		
		rec.setWareid(drug.getWareid());
		rec.setBarcode(drug.getBarcode());
		rec.setWarename(drug.getWarename());
		rec.setWaresimname(drug.getWaresimname());
		rec.setWarespec(drug.getWarespec());
		rec.setProdAddr(drug.getProdAddr());
		rec.setProducer(drug.getProducer());
		rec.setWareunit(drug.getWareunit());
		rec.setSaleprice(drug.getSaleprice());
		rec.setQuantity(quantity);
		
		

		//保存
		int row = prescDrugMapper.insertSelective(rec);

		if (row > 0)
			return rec.getId();
		else
			return 0;
	}

	@Override
	public List<Map<String, Object>> getByPrescId(Long presc_id) {
		return prescDrugMapper.getByPrescId(presc_id);
	}

}

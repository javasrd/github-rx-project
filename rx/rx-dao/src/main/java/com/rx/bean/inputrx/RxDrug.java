package com.rx.bean.inputrx;

/**
 * 用于向海典ERP发送处方药品数据
 * @ClassName: RxDrug
 * @Description: TODO
 * @author Administrator
 * @date 2018年1月24日-下午6:42:05
 * @version 1.0.0
 */
public class RxDrug {
	String id;	
	String drug_name;	
	String drug_code;
	String quantity;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDrug_name() {
		return drug_name;
	}
	public void setDrug_name(String drug_name) {
		this.drug_name = drug_name;
	}
	public String getDrug_code() {
		return drug_code;
	}
	public void setDrug_code(String drug_code) {
		this.drug_code = drug_code;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	

}

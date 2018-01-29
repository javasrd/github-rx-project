package com.rx.bean.inputrx;

public class RxPatient {
	private String id;  		//就诊号
	private String name;
	private String gender;
	private String old;
	private String cr_no;		//病历号
	private String rn;  		//登记号
	private String presc_no; 	//处方号
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getOld() {
		return old;
	}
	public void setOld(String old) {
		this.old = old;
	}
	public String getCr_no() {
		return cr_no;
	}
	public void setCr_no(String cr_no) {
		this.cr_no = cr_no;
	}
	public String getRn() {
		return rn;
	}
	public void setRn(String rn) {
		this.rn = rn;
	}
	public String getPresc_no() {
		return presc_no;
	}
	public void setPresc_no(String presc_no) {
		this.presc_no = presc_no;
	}
	
}

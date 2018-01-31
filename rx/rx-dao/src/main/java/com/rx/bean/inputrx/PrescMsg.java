package com.rx.bean.inputrx;

public class PrescMsg {
	private long prescId;  //保存的处方ID
	private String prescNo;//处方号码
	private String presc;	//处方内容 (JSON)格式
	
	public long getPrescId() {
		return prescId;
	}
	public void setPrescId(long prescId) {
		this.prescId = prescId;
	}
	public String getPrescNo() {
		return prescNo;
	}
	public void setPrescNo(String prescNo) {
		this.prescNo = prescNo;
	}
	public String getPresc() {
		return presc;
	}
	public void setPresc(String presc) {
		this.presc = presc;
	}
}

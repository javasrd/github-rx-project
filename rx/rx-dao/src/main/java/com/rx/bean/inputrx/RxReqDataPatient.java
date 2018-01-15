package com.rx.bean.inputrx;

import java.util.List;

/**
 * 医,患,诊断信息 Data段
 * @author Administrator
 *
 */
public class RxReqDataPatient {
	
	private RxPatient patient;  //患者对象
	private RxDoctor  doctor;    //医生对象
	private RxDepartment department;	//科室对象
	private List<RxDisease> diagnosis;  //疾病对象.
	
	public void setDiagnosis(List<RxDisease> diagnosis) {
		this.diagnosis = diagnosis;
	}
	public RxPatient getPatient() {
		return patient;
	}
	public void setPatient(RxPatient patient) {
		this.patient = patient;
	}
	public RxDoctor getDoctor() {
		return doctor;
	}
	public void setDoctor(RxDoctor doctor) {
		this.doctor = doctor;
	}
	public RxDepartment getDepartment() {
		return department;
	}
	public void setDepartment(RxDepartment department) {
		this.department = department;
	}
	public List<RxDisease> getDiagnosis() {
		return diagnosis;
	}
		
}

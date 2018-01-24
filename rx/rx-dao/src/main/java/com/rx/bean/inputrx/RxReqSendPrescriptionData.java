package com.rx.bean.inputrx;

import java.util.ArrayList;
import java.util.List;

/**
 * 处方发送 Data 段定义
 * @author Administrator
 *
 */
public class RxReqSendPrescriptionData {
	
	private RxPatient patient;  //患者对象
	private RxDoctor  doctor;    //医生对象
	private RxDepartment department;	//科室对象
	private List<RxDisease> diagnosis;  //疾病对象.
	private List<RxDrug> prescription;  //处方药品
	
	
	public RxReqSendPrescriptionData() {
		super();
		patient=new RxPatient();
		doctor=new RxDoctor();
		department=new RxDepartment();
		diagnosis=new ArrayList<RxDisease>();
		prescription=new ArrayList<RxDrug>();
	}
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
	public List<RxDrug> getPrescription() {
		return prescription;
	}
	public void setPrescription(List<RxDrug> prescription) {
		this.prescription = prescription;
	}
		
}

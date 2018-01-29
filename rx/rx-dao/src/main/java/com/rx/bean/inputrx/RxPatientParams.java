package com.rx.bean.inputrx;

/**
 * @ClassName: RxPatientParams
 * @Description: 用于接收东华传送过来的参数
 * @author Administrator
 * @date 2018年1月29日-上午10:33:02
 * @version 1.0.0
 */
public class RxPatientParams {
	/**
	 patient_id=123&
							patient_rn=123456789&
							patient_name=张三&
							patient_gender=男&
							patient_old=50&
							patient_cr_no=123456&
							doctor_id=12&
							doctor_name="李四"&
							department_id=15&
							department_name=内科
							diagnosis_result=消化不良,重感冒
	 */
	
	private String patient_id;  //就诊号
	private String patient_rn;	//登记号
	private String patient_name;	
	private String patient_gender; 
	private String patient_old;
	private String patient_cr_no; //病历号
	private String doctor_id;
	private String doctor_name;
	private String depatment_id;
	private String department_name;
	private String diagnosis_result;  //诊断结果,如果有多个疾病,采用逗号分隔.
	
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public String getPatient_rn() {
		return patient_rn;
	}
	public void setPatient_rn(String patient_rn) {
		this.patient_rn = patient_rn;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	public String getPatient_gender() {
		return patient_gender;
	}
	public void setPatient_gender(String patient_gender) {
		this.patient_gender = patient_gender;
	}
	public String getPatient_old() {
		return patient_old;
	}
	public void setPatient_old(String patient_old) {
		this.patient_old = patient_old;
	}
	public String getPatient_cr_no() {
		return patient_cr_no;
	}
	public void setPatient_cr_no(String patient_cr_no) {
		this.patient_cr_no = patient_cr_no;
	}
	public String getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}
	public String getDoctor_name() {
		return doctor_name;
	}
	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}
	public String getDepatment_id() {
		return depatment_id;
	}
	public void setDepatment_id(String depatment_id) {
		this.depatment_id = depatment_id;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getDiagnosis_result() {
		return diagnosis_result;
	}
	public void setDiagnosis_result(String diagnosis_result) {
		this.diagnosis_result = diagnosis_result;
	}
	
	
}

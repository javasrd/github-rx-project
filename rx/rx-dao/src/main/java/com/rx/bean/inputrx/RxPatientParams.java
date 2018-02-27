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
	
	private String patientid;  //就诊号
	private String patientrn;	//登记号
	private String patientname;	
	private String patientgender; 
	private String patientold;
	private String patientcrno; //病历号
	private String doctorid;
	private String doctorname;
	private String depatmentid;
	private String departmentname;
	private String diagnosisresult;  //诊断结果,如果有多个疾病,采用逗号分隔.
	
	public String getPatientid() {
		return patientid;
	}
	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}
	public String getPatientrn() {
		return patientrn;
	}
	public void setPatientrn(String patientrn) {
		this.patientrn = patientrn;
	}
	public String getPatientname() {
		return patientname;
	}
	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}
	public String getPatientgender() {
		return patientgender;
	}
	public void setPatientgender(String patientgender) {
		this.patientgender = patientgender;
	}
	public String getPatientold() {
		return patientold;
	}
	public void setPatientold(String patientold) {
		this.patientold = patientold;
	}
	public String getPatientcrno() {
		return patientcrno;
	}
	public void setPatientcrno(String patientcrno) {
		this.patientcrno = patientcrno;
	}
	public String getDoctorid() {
		return doctorid;
	}
	public void setDoctorid(String doctorid) {
		this.doctorid = doctorid;
	}
	public String getDoctorname() {
		return doctorname;
	}
	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}
	public String getDepatmentid() {
		return depatmentid;
	}
	public void setDepatmentid(String depatmentid) {
		this.depatmentid = depatmentid;
	}
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	public String getDiagnosisresult() {
		return diagnosisresult;
	}
	public void setDiagnosisresult(String diagnosisresult) {
		this.diagnosisresult = diagnosisresult;
	}
	
	
	
}

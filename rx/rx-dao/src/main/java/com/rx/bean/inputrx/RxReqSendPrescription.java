package com.rx.bean.inputrx;

/**
 * 用于接收医,患,诊断等信息的数据对象
 * @author Administrator
 *
 */
public class RxReqSendPrescription {
	private String version;  //版本号
	private String token;	//身份令牌
	private String func;	//action name
	
	private RxReqSendPrescriptionData data;
	

	public RxReqSendPrescription() {
		super();
		data=new RxReqSendPrescriptionData();
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getFunc() {
		return func;
	}
	public void setFunc(String func) {
		this.func = func;
	}
	public RxReqSendPrescriptionData getData() {
		return data;
	}
	public void setData(RxReqSendPrescriptionData data) {
		this.data = data;
	}
			
}

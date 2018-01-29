package com.rx.bean.inputrx;

/**
 * 处方通信协议.东华响应数据包
 * 协议具体描述见需求分析
 * @author Administrator
 */
public class RxRespProtocolDH {
	private String isSuccess;  //如果成功返回 "true" 否则返回 "false"
	private String code;       //如果成功返回 "1",否则返回"0",其它编码待定
	private String message;    //附加消息.
	private String patientId;  //患者ID.
	
	public String getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	
	
	
}

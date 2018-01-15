package com.rx.bean.inputrx;

/**
 * 处方通信协议.响应数据包
 * 协议具体描述见需求分析
 * @author Administrator
 */
public class RxRespProtocol {
	private String version="";  
	private String result="";  //结果编码，0-失败；1-成功
	private String msg="";  //附加信息，一般为异常描述
	private String data=""; //API 中另行规定
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
}

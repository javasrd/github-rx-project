package com.rx.bean.inputrx;

/**
 * 此对象暂时未用.
 * 请求协议头
 * @author Administrator
 *
 */
public class RxReqProtocolHead {
	private String version;  //版本号
	private String token;	//身份令牌
	private String func;	//action name
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
	
}

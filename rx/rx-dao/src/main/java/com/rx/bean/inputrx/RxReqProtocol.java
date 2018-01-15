package com.rx.bean.inputrx;

/**
 * 
 * 此对象暂时未用.
 * ---------------------
 * 处方请求协议数据包对象.
 * 此对象暂时没有使用.
 * @author Administrator
 *
 */
public class RxReqProtocol {
	private String version;  //版本号
	private String token;	//身份令牌
	private String func;	//action name	
	private String data;	//数据段
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
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
	
}

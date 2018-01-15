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
	private RxReqProtocolHead head;  //请求协议头
	private String data;	//数据段
	
	public RxReqProtocolHead getHead() {
		return head;
	}
	public void setHead(RxReqProtocolHead head) {
		this.head = head;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}

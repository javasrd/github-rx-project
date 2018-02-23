package com.rx.bean;

public enum SyncDrugLogStatus {

	/*1:成功；
	2：连接失败；
	3：请求异常；
	4：获取保存文件目录错误；
	5：写入文本文件异常；
	6：HTTP请求返回为空；
	7：解压TXT文本文件异常；
	8：读取TXT文本文件异常；
	9：文件格式错误；*/
	
	/**
	 * 1:成功；
	 */
	SUCCESS(1, "成功"),
	/**
	 * 2：连接失败；
	 */
	FAIL_HTTP_CONNECTOIN(2, "连接失败"),
	/**
	 * 3：请求异常；
	 */
	FAIL_HTTP_REQUEST(3, "请求异常"),
	/**
	 * 4：获取保存文件目录错误；
	 */
	FAIL_GET_SAVE_PATH(4, "获取保存文件目录错误"),
	/**
	 * 5：写入文本文件异常；
	 */
	FAIL_WRITE_FILE(5, "写入文本文件异常"),
	/**
	 * 6：HTTP请求返回为空；
	 */
	FAIL_HTTP_RESULT_NULL(6, "HTTP请求返回为空"),
	/**
	 * 7：解压TXT文本文件异常；
	 */
	FAIL_ZIP(7, "解压TXT文本文件异常"),
	/**
	 * 8：读取TXT文本文件异常；
	 */
	FAIL_READ_FILE(8, "读取TXT文本文件异常"),
	/**
	 * 9：文件格式错误；
	 */
	FAIL_FILE_FORMAT(9, "文件格式错误");
	
	private Integer index;
	private String value;
	
	private SyncDrugLogStatus(Integer index, String value) {
		this.index = index;
		this.value = value;
	}

	/**
	 * 
	 	1:成功；
		2：连接失败；
		3：请求异常；
		4：获取保存文件目录错误；
		5：写入文本文件异常；
		6：HTTP请求返回为空；
		7：解压TXT文本文件异常；
		8：读取TXT文本文件异常；
		9：文件格式错误；
	 * @param index
	 * @return
	 */
	public static String getValue(int index){
        for (SyncDrugLogStatus log : SyncDrugLogStatus.values()){
            if (log.getIndex()==index){
                return log.value;
            }
        }
        return null;
    }

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}

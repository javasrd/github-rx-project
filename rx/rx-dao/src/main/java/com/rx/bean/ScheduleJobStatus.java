package com.rx.bean;

public enum ScheduleJobStatus {

	/**
		1：任务中；
		2：已暂停；
		3：已停止；
	*/
	
	/**
	 * 1:成功；
	 */
	JOB_ING(1, "任务中"),
	/**
	 * 2：连接失败；
	 */
	JOB_PAUSE(2, "已暂停"),
	/**
	 * 3：请求异常；
	 */
	JOB_DELETE(3, "已停止");
	
	private Integer index;
	private String value;
	
	private ScheduleJobStatus(Integer index, String value) {
		this.index = index;
		this.value = value;
	}

	/**
	 * 
	 	1：任务中；
		2：已暂停；
		3：已停止；
	 * @param index
	 * @return
	 */
	public static String getValue(int index){
        for (ScheduleJobStatus log : ScheduleJobStatus.values()){
            if (log.getIndex()==index){
                return log.value;
            }
        }
        return "未知";
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

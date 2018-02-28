package com.rx.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rx.bean.ScheduleJobStatus;

/**
 * 计划任务信息
 * @author srd
 *
 */
@Table(name = "schedule_job")
public class ScheduleJob {
    /**
     * 任务自增ID
     */
    @Id
    @Column(name = "job_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    /**
     * 任务名称
     */
    @Column(name = "job_name")
    private String jobName;

    /**
     * 任务分组
     */
    @Column(name = "job_group")
    private String jobGroup;

    /**
     * 任务状态 1：任务中；2：暂停；3：停止；
     */
    @Column(name = "job_status")
    private Byte jobStatus;

    
    /**
     * 任务运行时间表达式名称
     */
    @Column(name = "cron_expression_name")
    private String cronExpressionName;
    
    /**
     * 任务运行时间表达式
     */
    @Column(name = "cron_expression")
    private String cronExpression;
    
	/**
     * 任务描述
     */
    private String jobDesc;

    /**
     * 默认构造函数
     */
    public ScheduleJob() {
		super();
	}

	/**
	 * 构造函数
	 * 
	 * @param jobId
	 * @param jobName
	 * @param jobGroup
	 * @param jobStatus
	 * @param cronExpression
	 * @param jobDesc
	 */
	public ScheduleJob(Long jobId, String jobName, String jobGroup, Byte jobStatus, String cronExpression,
			String jobDesc) {
		super();
		this.jobId = jobId;
		this.jobName = jobName;
		this.jobGroup = jobGroup;
		this.jobStatus = jobStatus;
		this.cronExpression = cronExpression;
		this.jobDesc = jobDesc;
	}
    
    /**
     * 获取任务自增ID
     *
     * @return job_id - 任务自增ID
     */
    public Long getJobId() {
        return jobId;
    }

    /**
     * 设置任务自增ID
     *
     * @param jobId 任务自增ID
     */
    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    /**
     * 获取任务名称
     *
     * @return job_name - 任务名称
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * 设置任务名称
     *
     * @param jobName 任务名称
     */
    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    /**
     * 获取任务分组
     *
     * @return job_group - 任务分组
     */
    public String getJobGroup() {
        return jobGroup;
    }

    /**
     * 设置任务分组
     *
     * @param jobGroup 任务分组
     */
    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup == null ? null : jobGroup.trim();
    }

    /**
     * 获取任务状态 1：任务中；2：暂停；3：停止；
     *
     * @return job_status - 任务状态 1：任务中；2：暂停；3：停止；
     */
    public Byte getJobStatus() {
        return jobStatus;
    }

    /**
     * 设置任务状态 1：任务中；2：暂停；3：停止；
     *
     * @param jobStatus 任务状态 1：任务中；2：暂停；3：停止；
     */
    public void setJobStatus(Byte jobStatus) {
        this.jobStatus = jobStatus;
    }
    
    /**
     * 获取任务状态 1：任务中；2：暂停；3：停止；
     *
     * @return job_status - 任务状态 1：任务中；2：暂停；3：停止；
     */
    public String getJobStatusStr() {
    	String statusStr = "";
    	try {
    		statusStr = ScheduleJobStatus.getValue(jobStatus);
		} catch (Exception e) {
			// TODO: handle exception
		}
        return statusStr;
    }

    /**
     * 获取任务运行时间表达式名称
     *
     * @return cron_expression_name - 任务运行时间表达式名称
     */
    public String getCronExpressionName() {
        return cronExpressionName;
    }

    /**
     * 设置任务运行时间表达式名称
     *
     * @param cronExpressionName 任务运行时间表达式名称
     */
    public void setCronExpressionName(String cronExpressionName) {
        this.cronExpressionName = cronExpressionName == null ? null : cronExpressionName.trim();
    }
    
    /**
     * 获取任务运行时间表达式
     *
     * @return cron_expression - 任务运行时间表达式
     */
    public String getCronExpression() {
        return cronExpression;
    }

    /**
     * 设置任务运行时间表达式
     *
     * @param cronExpression 任务运行时间表达式
     */
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression == null ? null : cronExpression.trim();
    }

    /**
     * 获取任务描述
     *
     * @return jobDesc - 任务描述
     */
    public String getJobDesc() {
        return jobDesc;
    }

    /**
     * 设置任务描述
     *
     * @param desc 任务描述
     */
    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc == null ? null : jobDesc.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", jobId=").append(jobId);
        sb.append(", jobName=").append(jobName);
        sb.append(", jobGroup=").append(jobGroup);
        sb.append(", jobStatus=").append(jobStatus);
        sb.append(", cronExpressionName=").append(cronExpressionName);
        sb.append(", cronExpression=").append(cronExpression);
        sb.append(", jobDesc=").append(jobDesc);
        sb.append("]");
        return sb.toString();
    }
}
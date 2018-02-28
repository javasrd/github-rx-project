package com.rx.entity;

import javax.persistence.*;

@Table(name = "job_cron_expression")
public class JobCronExpression {
    /**
     * 任务运行时间表达式表自增ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 任务运行时间表达式名称
     */
    private String name;

    /**
     * 任务运行时间表达式
     */
    @Column(name = "cron_expression")
    private String cronExpression;

    /**
     * 获取任务运行时间表达式表自增ID
     *
     * @return id - 任务运行时间表达式表自增ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置任务运行时间表达式表自增ID
     *
     * @param id 任务运行时间表达式表自增ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取任务运行时间表达式名称
     *
     * @return name - 任务运行时间表达式名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置任务运行时间表达式名称
     *
     * @param name 任务运行时间表达式名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", cronExpression=").append(cronExpression);
        sb.append("]");
        return sb.toString();
    }
}
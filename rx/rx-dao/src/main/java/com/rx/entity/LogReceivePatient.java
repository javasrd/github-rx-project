package com.rx.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "log_receive_patient")
public class LogReceivePatient {
    /**
     * 自增ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 记录创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 数据发送方IP地址
     */
    private String url;

    /**
     * 患者ID. FK( ref patient:id)
     */
    @Column(name = "patient_id")
    private Long patientId;

    /**
     * 接收到的数据（JSON格式）
     */
    private String data;

    /**
     * 获取自增ID
     *
     * @return id - 自增ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置自增ID
     *
     * @param id 自增ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取记录创建时间
     *
     * @return created_time - 记录创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置记录创建时间
     *
     * @param createdTime 记录创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取数据发送方IP地址
     *
     * @return url - 数据发送方IP地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置数据发送方IP地址
     *
     * @param url 数据发送方IP地址
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 获取患者ID. FK( ref patient:id)
     *
     * @return patient_id - 患者ID. FK( ref patient:id)
     */
    public Long getPatientId() {
        return patientId;
    }

    /**
     * 设置患者ID. FK( ref patient:id)
     *
     * @param patientId 患者ID. FK( ref patient:id)
     */
    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    /**
     * 获取接收到的数据（JSON格式）
     *
     * @return data - 接收到的数据（JSON格式）
     */
    public String getData() {
        return data;
    }

    /**
     * 设置接收到的数据（JSON格式）
     *
     * @param data 接收到的数据（JSON格式）
     */
    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", url=").append(url);
        sb.append(", patientId=").append(patientId);
        sb.append(", data=").append(data);
        sb.append("]");
        return sb.toString();
    }
}
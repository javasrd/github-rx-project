package com.rx.entity;

import java.util.Date;
import javax.persistence.*;

public class Prescription {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 医生ID(FK ref doctor:id)
     */
    @Column(name = "doctor_id")
    private Long doctorId;

    /**
     * 患者ID(FK ref patient:id)
     */
    @Column(name = "patient_id")
    private Long patientId;

    /**
     * 处方创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 处方编号（按相应的业务规则生成）
     */
    @Column(name = "rx_no")
    private String rxNo;

    /**
     * 获取自增主键
     *
     * @return id - 自增主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置自增主键
     *
     * @param id 自增主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取医生ID(FK ref doctor:id)
     *
     * @return doctor_id - 医生ID(FK ref doctor:id)
     */
    public Long getDoctorId() {
        return doctorId;
    }

    /**
     * 设置医生ID(FK ref doctor:id)
     *
     * @param doctorId 医生ID(FK ref doctor:id)
     */
    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * 获取患者ID(FK ref patient:id)
     *
     * @return patient_id - 患者ID(FK ref patient:id)
     */
    public Long getPatientId() {
        return patientId;
    }

    /**
     * 设置患者ID(FK ref patient:id)
     *
     * @param patientId 患者ID(FK ref patient:id)
     */
    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    /**
     * 获取处方创建时间
     *
     * @return created_time - 处方创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置处方创建时间
     *
     * @param createdTime 处方创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取处方编号（按相应的业务规则生成）
     *
     * @return rx_no - 处方编号（按相应的业务规则生成）
     */
    public String getRxNo() {
        return rxNo;
    }

    /**
     * 设置处方编号（按相应的业务规则生成）
     *
     * @param rxNo 处方编号（按相应的业务规则生成）
     */
    public void setRxNo(String rxNo) {
        this.rxNo = rxNo == null ? null : rxNo.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", doctorId=").append(doctorId);
        sb.append(", patientId=").append(patientId);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", rxNo=").append(rxNo);
        sb.append("]");
        return sb.toString();
    }
}
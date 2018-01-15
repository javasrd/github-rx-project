package com.rx.entity;

import javax.persistence.*;

@Table(name = "doctor_patient")
public class DoctorPatient {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 医生ID（FK ref doctor:id）
     */
    @Column(name = "doctor_id")
    private Long doctorId;

    /**
     * 患者ID（FK ref patient:id）
     */
    @Column(name = "patient_id")
    private Long patientId;

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
     * 获取医生ID（FK ref doctor:id）
     *
     * @return doctor_id - 医生ID（FK ref doctor:id）
     */
    public Long getDoctorId() {
        return doctorId;
    }

    /**
     * 设置医生ID（FK ref doctor:id）
     *
     * @param doctorId 医生ID（FK ref doctor:id）
     */
    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * 获取患者ID（FK ref patient:id）
     *
     * @return patient_id - 患者ID（FK ref patient:id）
     */
    public Long getPatientId() {
        return patientId;
    }

    /**
     * 设置患者ID（FK ref patient:id）
     *
     * @param patientId 患者ID（FK ref patient:id）
     */
    public void setPatientId(Long patientId) {
        this.patientId = patientId;
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
        sb.append("]");
        return sb.toString();
    }
}
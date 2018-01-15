package com.rx.entity;

import javax.persistence.*;

public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 东华HIS诊断结果ID
     */
    @Column(name = "old_id")
    private String oldId;

    /**
     * 疾病名称
     */
    private String disease;

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
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取东华HIS诊断结果ID
     *
     * @return old_id - 东华HIS诊断结果ID
     */
    public String getOldId() {
        return oldId;
    }

    /**
     * 设置东华HIS诊断结果ID
     *
     * @param oldId 东华HIS诊断结果ID
     */
    public void setOldId(String oldId) {
        this.oldId = oldId == null ? null : oldId.trim();
    }

    /**
     * 获取疾病名称
     *
     * @return disease - 疾病名称
     */
    public String getDisease() {
        return disease;
    }

    /**
     * 设置疾病名称
     *
     * @param disease 疾病名称
     */
    public void setDisease(String disease) {
        this.disease = disease == null ? null : disease.trim();
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
        sb.append(", oldId=").append(oldId);
        sb.append(", disease=").append(disease);
        sb.append(", doctorId=").append(doctorId);
        sb.append(", patientId=").append(patientId);
        sb.append("]");
        return sb.toString();
    }
}
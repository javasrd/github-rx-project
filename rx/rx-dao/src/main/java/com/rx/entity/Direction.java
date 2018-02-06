package com.rx.entity;

import java.math.BigDecimal;
import javax.persistence.*;

public class Direction {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 处方药品ID(FK ref presc_drug:id)
     */
    @Column(name = "presc_drug_id")
    private Long prescDrugId;

    /**
     * 给药方式（口服，外敷等）
     */
    private String mode;

    /**
     * 给药次数
     */
    private String times;

    /**
     * 剂量
     */
    private BigDecimal dosage;

    /**
     * 剂量单位
     */
    private String doseunit;

    private String days;

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
     * 获取处方药品ID(FK ref presc_drug:id)
     *
     * @return presc_drug_id - 处方药品ID(FK ref presc_drug:id)
     */
    public Long getPrescDrugId() {
        return prescDrugId;
    }

    /**
     * 设置处方药品ID(FK ref presc_drug:id)
     *
     * @param prescDrugId 处方药品ID(FK ref presc_drug:id)
     */
    public void setPrescDrugId(Long prescDrugId) {
        this.prescDrugId = prescDrugId;
    }

    /**
     * 获取给药方式（口服，外敷等）
     *
     * @return mode - 给药方式（口服，外敷等）
     */
    public String getMode() {
        return mode;
    }

    /**
     * 设置给药方式（口服，外敷等）
     *
     * @param mode 给药方式（口服，外敷等）
     */
    public void setMode(String mode) {
        this.mode = mode == null ? null : mode.trim();
    }

    /**
     * 获取给药次数
     *
     * @return times - 给药次数
     */
    public String getTimes() {
        return times;
    }

    /**
     * 设置给药次数
     *
     * @param times 给药次数
     */
    public void setTimes(String times) {
        this.times = times == null ? null : times.trim();
    }

    /**
     * 获取剂量
     *
     * @return dosage - 剂量
     */
    public BigDecimal getDosage() {
        return dosage;
    }

    /**
     * 设置剂量
     *
     * @param dosage 剂量
     */
    public void setDosage(BigDecimal dosage) {
        this.dosage = dosage;
    }

    /**
     * 获取剂量单位
     *
     * @return doseunit - 剂量单位
     */
    public String getDoseunit() {
        return doseunit;
    }

    /**
     * 设置剂量单位
     *
     * @param doseunit 剂量单位
     */
    public void setDoseunit(String doseunit) {
        this.doseunit = doseunit == null ? null : doseunit.trim();
    }

    /**
     * @return days
     */
    public String getDays() {
        return days;
    }

    /**
     * @param days
     */
    public void setDays(String days) {
        this.days = days == null ? null : days.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", prescDrugId=").append(prescDrugId);
        sb.append(", mode=").append(mode);
        sb.append(", times=").append(times);
        sb.append(", dosage=").append(dosage);
        sb.append(", doseunit=").append(doseunit);
        sb.append(", days=").append(days);
        sb.append("]");
        return sb.toString();
    }
}
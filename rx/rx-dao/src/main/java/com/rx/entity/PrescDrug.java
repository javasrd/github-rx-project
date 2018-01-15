package com.rx.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "presc_drug")
public class PrescDrug {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 处方ID(FK ref prescription:id)
     */
    @Column(name = "presc_id")
    private Long prescId;

    /**
     * 药品ID( FK ref drug:id)
     */
    @Column(name = "drug_id")
    private Long drugId;

    /**
     * 加入日期
     */
    @Column(name = "created_time")
    private Date createdTime;

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
     * 获取处方ID(FK ref prescription:id)
     *
     * @return presc_id - 处方ID(FK ref prescription:id)
     */
    public Long getPrescId() {
        return prescId;
    }

    /**
     * 设置处方ID(FK ref prescription:id)
     *
     * @param prescId 处方ID(FK ref prescription:id)
     */
    public void setPrescId(Long prescId) {
        this.prescId = prescId;
    }

    /**
     * 获取药品ID( FK ref drug:id)
     *
     * @return drug_id - 药品ID( FK ref drug:id)
     */
    public Long getDrugId() {
        return drugId;
    }

    /**
     * 设置药品ID( FK ref drug:id)
     *
     * @param drugId 药品ID( FK ref drug:id)
     */
    public void setDrugId(Long drugId) {
        this.drugId = drugId;
    }

    /**
     * 获取加入日期
     *
     * @return created_time - 加入日期
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置加入日期
     *
     * @param createdTime 加入日期
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", prescId=").append(prescId);
        sb.append(", drugId=").append(drugId);
        sb.append(", createdTime=").append(createdTime);
        sb.append("]");
        return sb.toString();
    }
}
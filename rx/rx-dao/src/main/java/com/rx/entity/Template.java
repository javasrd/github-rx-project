package com.rx.entity;

import java.util.Date;
import javax.persistence.*;

public class Template {
    /**
     * 自增ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 医生ID(在本地数据库中)
     */
    @Column(name = "doctor_id")
    private Long doctorId;

    /**
     * Doctor ID(在东华系统中)
     */
    @Column(name = "doctor_old_id")
    private String doctorOldId;

    /**
     * 模板备注
     */
    private String comment;

    /**
     * 是否删除（1-未删除，2-删除，默认1）
     */
    private Integer deleted;

    /**
     * 记录创建日期
     */
    @Column(name = "created_time")
    private Date createdTime;

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
     * 获取模板名称
     *
     * @return name - 模板名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置模板名称
     *
     * @param name 模板名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取医生ID(在本地数据库中)
     *
     * @return doctor_id - 医生ID(在本地数据库中)
     */
    public Long getDoctorId() {
        return doctorId;
    }

    /**
     * 设置医生ID(在本地数据库中)
     *
     * @param doctorId 医生ID(在本地数据库中)
     */
    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * 获取Doctor ID(在东华系统中)
     *
     * @return doctor_old_id - Doctor ID(在东华系统中)
     */
    public String getDoctorOldId() {
        return doctorOldId;
    }

    /**
     * 设置Doctor ID(在东华系统中)
     *
     * @param doctorOldId Doctor ID(在东华系统中)
     */
    public void setDoctorOldId(String doctorOldId) {
        this.doctorOldId = doctorOldId == null ? null : doctorOldId.trim();
    }

    /**
     * 获取模板备注
     *
     * @return comment - 模板备注
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置模板备注
     *
     * @param comment 模板备注
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     * 获取是否删除（1-未删除，2-删除，默认1）
     *
     * @return deleted - 是否删除（1-未删除，2-删除，默认1）
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除（1-未删除，2-删除，默认1）
     *
     * @param deleted 是否删除（1-未删除，2-删除，默认1）
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取记录创建日期
     *
     * @return created_time - 记录创建日期
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置记录创建日期
     *
     * @param createdTime 记录创建日期
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
        sb.append(", name=").append(name);
        sb.append(", doctorId=").append(doctorId);
        sb.append(", doctorOldId=").append(doctorOldId);
        sb.append(", comment=").append(comment);
        sb.append(", deleted=").append(deleted);
        sb.append(", createdTime=").append(createdTime);
        sb.append("]");
        return sb.toString();
    }
}
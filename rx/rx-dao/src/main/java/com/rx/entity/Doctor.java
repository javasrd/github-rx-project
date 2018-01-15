package com.rx.entity;

import javax.persistence.*;

public class Doctor {
    /**
     * 自增ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 自东华传送过来的医生ID
     */
    @Column(name = "old_id")
    private String oldId;

    /**
     * 医生姓名
     */
    private String name;

    /**
     * 医生所在科室ID（FK ref depatment:id）
     */
    @Column(name = "department_id")
    private Long departmentId;

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
     * 获取自东华传送过来的医生ID
     *
     * @return old_id - 自东华传送过来的医生ID
     */
    public String getOldId() {
        return oldId;
    }

    /**
     * 设置自东华传送过来的医生ID
     *
     * @param oldId 自东华传送过来的医生ID
     */
    public void setOldId(String oldId) {
        this.oldId = oldId == null ? null : oldId.trim();
    }

    /**
     * 获取医生姓名
     *
     * @return name - 医生姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置医生姓名
     *
     * @param name 医生姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取医生所在科室ID（FK ref depatment:id）
     *
     * @return department_id - 医生所在科室ID（FK ref depatment:id）
     */
    public Long getDepartmentId() {
        return departmentId;
    }

    /**
     * 设置医生所在科室ID（FK ref depatment:id）
     *
     * @param departmentId 医生所在科室ID（FK ref depatment:id）
     */
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", oldId=").append(oldId);
        sb.append(", name=").append(name);
        sb.append(", departmentId=").append(departmentId);
        sb.append("]");
        return sb.toString();
    }
}
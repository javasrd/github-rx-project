package com.rx.entity;

import javax.persistence.*;

public class Department {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 东华HIS科室ID
     */
    @Column(name = "old_id")
    private String oldId;

    /**
     * 东华HIS中科室名称
     */
    private String name;

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
     * 获取东华HIS科室ID
     *
     * @return old_id - 东华HIS科室ID
     */
    public String getOldId() {
        return oldId;
    }

    /**
     * 设置东华HIS科室ID
     *
     * @param oldId 东华HIS科室ID
     */
    public void setOldId(String oldId) {
        this.oldId = oldId == null ? null : oldId.trim();
    }

    /**
     * 获取东华HIS中科室名称
     *
     * @return name - 东华HIS中科室名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置东华HIS中科室名称
     *
     * @param name 东华HIS中科室名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
        sb.append("]");
        return sb.toString();
    }
}
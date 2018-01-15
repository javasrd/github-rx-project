package com.rx.entity;

import javax.persistence.*;

@Table(name = "dict_dose_unit")
public class DictDoseUnit {
    /**
     * 自增ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 剂量单位编码
     */
    private String code;

    /**
     * 剂量单位名称
     */
    private String name;

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
     * 获取剂量单位编码
     *
     * @return code - 剂量单位编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置剂量单位编码
     *
     * @param code 剂量单位编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取剂量单位名称
     *
     * @return name - 剂量单位名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置剂量单位名称
     *
     * @param name 剂量单位名称
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
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append("]");
        return sb.toString();
    }
}
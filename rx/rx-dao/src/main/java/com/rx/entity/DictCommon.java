package com.rx.entity;

import javax.persistence.*;

@Table(name = "dict_common")
public class DictCommon {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 编码
     */
    private String code;

    /**
     * 医院名称
     */
    private String name;

    /**
     * 数据类型(根据业务进行定义)
     */
    private Integer type;

    /**
     * 备注
     */
    private String comment;

    /**
     * 是否删除（1-未删除，2-删除，默认1）
     */
    private Integer deleted;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取编码
     *
     * @return code - 编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置编码
     *
     * @param code 编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取医院名称
     *
     * @return name - 医院名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置医院名称
     *
     * @param name 医院名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取数据类型(根据业务进行定义)
     *
     * @return type - 数据类型(根据业务进行定义)
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置数据类型(根据业务进行定义)
     *
     * @param type 数据类型(根据业务进行定义)
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取备注
     *
     * @return comment - 备注
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置备注
     *
     * @param comment 备注
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", comment=").append(comment);
        sb.append(", deleted=").append(deleted);
        sb.append("]");
        return sb.toString();
    }
}
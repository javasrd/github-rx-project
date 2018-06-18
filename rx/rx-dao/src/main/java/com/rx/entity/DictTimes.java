package com.rx.entity;

import javax.persistence.*;

@Table(name = "dict_times")
public class DictTimes {
    /**
     * 自增ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 给药次数编码
     */
    private String code;

    /**
     * 给药次数名称:1次/日，2次/日或qd: 1次/日，bid: 2次/日等
     */
    private String name;

    /**
     * 数值:2/1（2次/天），用药次数/天数
     */
    private String value;

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
     * 获取给药次数编码
     *
     * @return code - 给药次数编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置给药次数编码
     *
     * @param code 给药次数编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取给药次数名称:1次/日，2次/日或qd: 1次/日，bid: 2次/日等
     *
     * @return name - 给药次数名称:1次/日，2次/日或qd: 1次/日，bid: 2次/日等
     */
    public String getName() {
        return name;
    }

    /**
     * 设置给药次数名称:1次/日，2次/日或qd: 1次/日，bid: 2次/日等
     *
     * @param name 给药次数名称:1次/日，2次/日或qd: 1次/日，bid: 2次/日等
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取数值:2/1（2次/天），用药次数/天数
     *
     * @return value - 数值:2/1（2次/天），用药次数/天数
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置数值:2/1（2次/天），用药次数/天数
     *
     * @param value 数值:2/1（2次/天），用药次数/天数
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
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
        sb.append(", value=").append(value);
        sb.append("]");
        return sb.toString();
    }
}
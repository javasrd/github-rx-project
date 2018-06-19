package com.rx.entity;

import javax.persistence.*;

@Table(name = "dict_days")
public class DictDays {
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
     * 给药次数名称
     */
    private String name;
    
    /**
     * 给药次数值
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
     * 获取给药次数名称
     *
     * @return name - 给药次数名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置给药次数名称
     *
     * @param name 给药次数名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取给药疗程值
     * 
     * @return
     */
    public String getValue() {
		return value;
	}

	/**
	 * 设置给药疗程值
	 * 
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "DictDays [id=" + id + ", code=" + code + ", name=" + name + ", value=" + value + "]";
	}

}
package com.rx.entity;

import javax.persistence.*;

public class Patient {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 自东华传送过来的原患者ID
     */
    @Column(name = "old_id")
    private String oldId;

    /**
     * 患者姓名
     */
    private String name;

    /**
     * 患者性别。1:男；2：女
     */
    private Byte sex;

    /**
     * 患者年龄
     */
    private Integer old;

    /**
     * 病历号
     */
    @Column(name = "cr_no")
    private String crNo;

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
     * 获取自东华传送过来的原患者ID
     *
     * @return old_id - 自东华传送过来的原患者ID
     */
    public String getOldId() {
        return oldId;
    }

    /**
     * 设置自东华传送过来的原患者ID
     *
     * @param oldId 自东华传送过来的原患者ID
     */
    public void setOldId(String oldId) {
        this.oldId = oldId == null ? null : oldId.trim();
    }

    /**
     * 获取患者姓名
     *
     * @return name - 患者姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置患者姓名
     *
     * @param name 患者姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取患者性别。1:男；2：女
     *
     * @return sex - 患者性别。1:男；2：女
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * 设置患者性别。1:男；2：女
     *
     * @param sex 患者性别。1:男；2：女
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * 获取患者年龄
     *
     * @return old - 患者年龄
     */
    public Integer getOld() {
        return old;
    }

    /**
     * 设置患者年龄
     *
     * @param old 患者年龄
     */
    public void setOld(Integer old) {
        this.old = old;
    }

    /**
     * 获取病历号
     *
     * @return cr_no - 病历号
     */
    public String getCrNo() {
        return crNo;
    }

    /**
     * 设置病历号
     *
     * @param crNo 病历号
     */
    public void setCrNo(String crNo) {
        this.crNo = crNo == null ? null : crNo.trim();
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
        sb.append(", sex=").append(sex);
        sb.append(", old=").append(old);
        sb.append(", crNo=").append(crNo);
        sb.append("]");
        return sb.toString();
    }
}
package com.rx.entity;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "rx_knowledge_base_raw")
public class rxKnowledgeBaseRaw {
    /**
     * 处方知识库自增ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 助记码
     */
    private String acb;

    /**
     * 商品名
     */
    private String warename;

    /**
     * 通用名
     */
    private String waresimname;

    /**
     * 规格
     */
    private String warespec;

    /**
     * 产地
     */
    @Column(name = "prod_addr")
    private String prodAddr;

    /**
     * 生产厂家
     */
    private String producer;

    /**
     * 单位
     */
    private String wareuint;

    /**
     * 单次剂量
     */
    private BigDecimal dosage;

    /**
     * 单次剂量单位
     */
    private String doseunit;

    /**
     * 频次（给药次数）:1次/天
     */
    private String times;

    /**
     * 用法（给药方式）:口服，外用
     */
    private String mode;

    /**
     * 疗程（天数）
     */
    private Integer days;

    /**
     * 最小可出售包装规格
     */
    private String saleminspec;

    /**
     * 最小可出售包装剂量单位
     */
    private String saleminunit;

    /**
     * 最小可出售包装剂量单位单价
     */
    private BigDecimal saleminprice;

    /**
     * 药品数量
     */
    private Integer count;

    /**
     * 患者性别 1:男；2：女
     */
    private Byte sex;

    /**
     * 患者年龄
     */
    private Integer old;

    /**
     * 疾病名称（诊断结果） 多个结果用英文“,”分隔
     */
    private String diseae;

    /**
     * 获取处方知识库自增ID
     *
     * @return id - 处方知识库自增ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置处方知识库自增ID
     *
     * @param id 处方知识库自增ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取助记码
     *
     * @return acb - 助记码
     */
    public String getAcb() {
        return acb;
    }

    /**
     * 设置助记码
     *
     * @param acb 助记码
     */
    public void setAcb(String acb) {
        this.acb = acb == null ? null : acb.trim();
    }

    /**
     * 获取商品名
     *
     * @return warename - 商品名
     */
    public String getWarename() {
        return warename;
    }

    /**
     * 设置商品名
     *
     * @param warename 商品名
     */
    public void setWarename(String warename) {
        this.warename = warename == null ? null : warename.trim();
    }

    /**
     * 获取通用名
     *
     * @return waresimname - 通用名
     */
    public String getWaresimname() {
        return waresimname;
    }

    /**
     * 设置通用名
     *
     * @param waresimname 通用名
     */
    public void setWaresimname(String waresimname) {
        this.waresimname = waresimname == null ? null : waresimname.trim();
    }

    /**
     * 获取规格
     *
     * @return warespec - 规格
     */
    public String getWarespec() {
        return warespec;
    }

    /**
     * 设置规格
     *
     * @param warespec 规格
     */
    public void setWarespec(String warespec) {
        this.warespec = warespec == null ? null : warespec.trim();
    }

    /**
     * 获取产地
     *
     * @return prod_addr - 产地
     */
    public String getProdAddr() {
        return prodAddr;
    }

    /**
     * 设置产地
     *
     * @param prodAddr 产地
     */
    public void setProdAddr(String prodAddr) {
        this.prodAddr = prodAddr == null ? null : prodAddr.trim();
    }

    /**
     * 获取生产厂家
     *
     * @return producer - 生产厂家
     */
    public String getProducer() {
        return producer;
    }

    /**
     * 设置生产厂家
     *
     * @param producer 生产厂家
     */
    public void setProducer(String producer) {
        this.producer = producer == null ? null : producer.trim();
    }

    /**
     * 获取单位
     *
     * @return wareuint - 单位
     */
    public String getWareuint() {
        return wareuint;
    }

    /**
     * 设置单位
     *
     * @param wareuint 单位
     */
    public void setWareuint(String wareuint) {
        this.wareuint = wareuint == null ? null : wareuint.trim();
    }

    /**
     * 获取单次剂量
     *
     * @return dosage - 单次剂量
     */
    public BigDecimal getDosage() {
        return dosage;
    }

    /**
     * 设置单次剂量
     *
     * @param dosage 单次剂量
     */
    public void setDosage(BigDecimal dosage) {
        this.dosage = dosage;
    }

    /**
     * 获取单次剂量单位
     *
     * @return doseunit - 单次剂量单位
     */
    public String getDoseunit() {
        return doseunit;
    }

    /**
     * 设置单次剂量单位
     *
     * @param doseunit 单次剂量单位
     */
    public void setDoseunit(String doseunit) {
        this.doseunit = doseunit == null ? null : doseunit.trim();
    }

    /**
     * 获取频次（给药次数）:1次/天
     *
     * @return times - 频次（给药次数）:1次/天
     */
    public String getTimes() {
        return times;
    }

    /**
     * 设置频次（给药次数）:1次/天
     *
     * @param times 频次（给药次数）:1次/天
     */
    public void setTimes(String times) {
        this.times = times == null ? null : times.trim();
    }

    /**
     * 获取用法（给药方式）:口服，外用
     *
     * @return mode - 用法（给药方式）:口服，外用
     */
    public String getMode() {
        return mode;
    }

    /**
     * 设置用法（给药方式）:口服，外用
     *
     * @param mode 用法（给药方式）:口服，外用
     */
    public void setMode(String mode) {
        this.mode = mode == null ? null : mode.trim();
    }

    /**
     * 获取疗程（天数）
     *
     * @return days - 疗程（天数）
     */
    public Integer getDays() {
        return days;
    }

    /**
     * 设置疗程（天数）
     *
     * @param days 疗程（天数）
     */
    public void setDays(Integer days) {
        this.days = days;
    }

    /**
     * 获取最小可出售包装规格
     *
     * @return saleminspec - 最小可出售包装规格
     */
    public String getSaleminspec() {
        return saleminspec;
    }

    /**
     * 设置最小可出售包装规格
     *
     * @param saleminspec 最小可出售包装规格
     */
    public void setSaleminspec(String saleminspec) {
        this.saleminspec = saleminspec == null ? null : saleminspec.trim();
    }

    /**
     * 获取最小可出售包装剂量单位
     *
     * @return saleminunit - 最小可出售包装剂量单位
     */
    public String getSaleminunit() {
        return saleminunit;
    }

    /**
     * 设置最小可出售包装剂量单位
     *
     * @param saleminunit 最小可出售包装剂量单位
     */
    public void setSaleminunit(String saleminunit) {
        this.saleminunit = saleminunit == null ? null : saleminunit.trim();
    }

    /**
     * 获取最小可出售包装剂量单位单价
     *
     * @return saleminprice - 最小可出售包装剂量单位单价
     */
    public BigDecimal getSaleminprice() {
        return saleminprice;
    }

    /**
     * 设置最小可出售包装剂量单位单价
     *
     * @param saleminprice 最小可出售包装剂量单位单价
     */
    public void setSaleminprice(BigDecimal saleminprice) {
        this.saleminprice = saleminprice;
    }

    /**
     * 获取药品数量
     *
     * @return count - 药品数量
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置药品数量
     *
     * @param count 药品数量
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 获取患者性别 1:男；2：女
     *
     * @return sex - 患者性别 1:男；2：女
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * 设置患者性别 1:男；2：女
     *
     * @param sex 患者性别 1:男；2：女
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
     * 获取疾病名称（诊断结果） 多个结果用英文“,”分隔
     *
     * @return diseae - 疾病名称（诊断结果） 多个结果用英文“,”分隔
     */
    public String getDiseae() {
        return diseae;
    }

    /**
     * 设置疾病名称（诊断结果） 多个结果用英文“,”分隔
     *
     * @param diseae 疾病名称（诊断结果） 多个结果用英文“,”分隔
     */
    public void setDiseae(String diseae) {
        this.diseae = diseae == null ? null : diseae.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", acb=").append(acb);
        sb.append(", warename=").append(warename);
        sb.append(", waresimname=").append(waresimname);
        sb.append(", warespec=").append(warespec);
        sb.append(", prodAddr=").append(prodAddr);
        sb.append(", producer=").append(producer);
        sb.append(", wareuint=").append(wareuint);
        sb.append(", dosage=").append(dosage);
        sb.append(", doseunit=").append(doseunit);
        sb.append(", times=").append(times);
        sb.append(", mode=").append(mode);
        sb.append(", days=").append(days);
        sb.append(", saleminspec=").append(saleminspec);
        sb.append(", saleminunit=").append(saleminunit);
        sb.append(", saleminprice=").append(saleminprice);
        sb.append(", count=").append(count);
        sb.append(", sex=").append(sex);
        sb.append(", old=").append(old);
        sb.append(", diseae=").append(diseae);
        sb.append("]");
        return sb.toString();
    }
}
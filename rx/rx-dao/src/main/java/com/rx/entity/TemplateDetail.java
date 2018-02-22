package com.rx.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "template_detail")
public class TemplateDetail {
    /**
     * 自增ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 模板id(FK ref template:id)
     */
    @Column(name = "template_id")
    private Long templateId;

    private String wareid;

    private String barcode;

    private String abc;

    private String warename;

    private String waresimname;

    private String warespec;

    @Column(name = "prod_addr")
    private String prodAddr;

    private String producer;

    private String wareunit;

    private Byte status;

    private BigDecimal saleprice;

    private Integer inventory;

    private String mode;

    private String times;

    private BigDecimal dosage;

    private String doseunit;

    private String days;

    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 是否删除（1-未删除，2-删除，默认1）
     */
    private Integer deleted;

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
     * 获取模板id(FK ref template:id)
     *
     * @return template_id - 模板id(FK ref template:id)
     */
    public Long getTemplateId() {
        return templateId;
    }

    /**
     * 设置模板id(FK ref template:id)
     *
     * @param templateId 模板id(FK ref template:id)
     */
    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    /**
     * @return wareid
     */
    public String getWareid() {
        return wareid;
    }

    /**
     * @param wareid
     */
    public void setWareid(String wareid) {
        this.wareid = wareid == null ? null : wareid.trim();
    }

    /**
     * @return barcode
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * @param barcode
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    /**
     * @return abc
     */
    public String getAbc() {
        return abc;
    }

    /**
     * @param abc
     */
    public void setAbc(String abc) {
        this.abc = abc == null ? null : abc.trim();
    }

    /**
     * @return warename
     */
    public String getWarename() {
        return warename;
    }

    /**
     * @param warename
     */
    public void setWarename(String warename) {
        this.warename = warename == null ? null : warename.trim();
    }

    /**
     * @return waresimname
     */
    public String getWaresimname() {
        return waresimname;
    }

    /**
     * @param waresimname
     */
    public void setWaresimname(String waresimname) {
        this.waresimname = waresimname == null ? null : waresimname.trim();
    }

    /**
     * @return warespec
     */
    public String getWarespec() {
        return warespec;
    }

    /**
     * @param warespec
     */
    public void setWarespec(String warespec) {
        this.warespec = warespec == null ? null : warespec.trim();
    }

    /**
     * @return prod_addr
     */
    public String getProdAddr() {
        return prodAddr;
    }

    /**
     * @param prodAddr
     */
    public void setProdAddr(String prodAddr) {
        this.prodAddr = prodAddr == null ? null : prodAddr.trim();
    }

    /**
     * @return producer
     */
    public String getProducer() {
        return producer;
    }

    /**
     * @param producer
     */
    public void setProducer(String producer) {
        this.producer = producer == null ? null : producer.trim();
    }

    /**
     * @return wareunit
     */
    public String getWareunit() {
        return wareunit;
    }

    /**
     * @param wareunit
     */
    public void setWareunit(String wareunit) {
        this.wareunit = wareunit == null ? null : wareunit.trim();
    }

    /**
     * @return status
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * @return saleprice
     */
    public BigDecimal getSaleprice() {
        return saleprice;
    }

    /**
     * @param saleprice
     */
    public void setSaleprice(BigDecimal saleprice) {
        this.saleprice = saleprice;
    }

    /**
     * @return inventory
     */
    public Integer getInventory() {
        return inventory;
    }

    /**
     * @param inventory
     */
    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    /**
     * @return mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * @param mode
     */
    public void setMode(String mode) {
        this.mode = mode == null ? null : mode.trim();
    }

    /**
     * @return times
     */
    public String getTimes() {
        return times;
    }

    /**
     * @param times
     */
    public void setTimes(String times) {
        this.times = times == null ? null : times.trim();
    }

    /**
     * @return dosage
     */
    public BigDecimal getDosage() {
        return dosage;
    }

    /**
     * @param dosage
     */
    public void setDosage(BigDecimal dosage) {
        this.dosage = dosage;
    }

    /**
     * @return doseunit
     */
    public String getDoseunit() {
        return doseunit;
    }

    /**
     * @param doseunit
     */
    public void setDoseunit(String doseunit) {
        this.doseunit = doseunit == null ? null : doseunit.trim();
    }

    /**
     * @return days
     */
    public String getDays() {
        return days;
    }

    /**
     * @param days
     */
    public void setDays(String days) {
        this.days = days == null ? null : days.trim();
    }

    /**
     * @return created_time
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * @param createdTime
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
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
        sb.append(", templateId=").append(templateId);
        sb.append(", wareid=").append(wareid);
        sb.append(", barcode=").append(barcode);
        sb.append(", abc=").append(abc);
        sb.append(", warename=").append(warename);
        sb.append(", waresimname=").append(waresimname);
        sb.append(", warespec=").append(warespec);
        sb.append(", prodAddr=").append(prodAddr);
        sb.append(", producer=").append(producer);
        sb.append(", wareunit=").append(wareunit);
        sb.append(", status=").append(status);
        sb.append(", saleprice=").append(saleprice);
        sb.append(", inventory=").append(inventory);
        sb.append(", mode=").append(mode);
        sb.append(", times=").append(times);
        sb.append(", dosage=").append(dosage);
        sb.append(", doseunit=").append(doseunit);
        sb.append(", days=").append(days);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", deleted=").append(deleted);
        sb.append("]");
        return sb.toString();
    }
}
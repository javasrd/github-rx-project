package com.rx.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "presc_drug")
public class PrescDrug {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 处方ID(FK ref prescription:id)
     */
    @Column(name = "presc_id")
    private Long prescId;

    /**
     * 药品ID( FK ref drug:id)
     */
    @Column(name = "drug_id")
    private Long drugId;

    private Integer quantity;

    private String wareid;

    private String barcode;

    private String warename;

    private String waresimname;

    private String warespec;

    @Column(name = "prod_addr")
    private String prodAddr;

    private String producer;

    private String wareunit;

    private BigDecimal saleprice;

    /**
     * 加入日期
     */
    @Column(name = "created_time")
    private Date createdTime;

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
     * 获取处方ID(FK ref prescription:id)
     *
     * @return presc_id - 处方ID(FK ref prescription:id)
     */
    public Long getPrescId() {
        return prescId;
    }

    /**
     * 设置处方ID(FK ref prescription:id)
     *
     * @param prescId 处方ID(FK ref prescription:id)
     */
    public void setPrescId(Long prescId) {
        this.prescId = prescId;
    }

    /**
     * 获取药品ID( FK ref drug:id)
     *
     * @return drug_id - 药品ID( FK ref drug:id)
     */
    public Long getDrugId() {
        return drugId;
    }

    /**
     * 设置药品ID( FK ref drug:id)
     *
     * @param drugId 药品ID( FK ref drug:id)
     */
    public void setDrugId(Long drugId) {
        this.drugId = drugId;
    }

    /**
     * @return quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
     * 获取加入日期
     *
     * @return created_time - 加入日期
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置加入日期
     *
     * @param createdTime 加入日期
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
        sb.append(", prescId=").append(prescId);
        sb.append(", drugId=").append(drugId);
        sb.append(", quantity=").append(quantity);
        sb.append(", wareid=").append(wareid);
        sb.append(", barcode=").append(barcode);
        sb.append(", warename=").append(warename);
        sb.append(", waresimname=").append(waresimname);
        sb.append(", warespec=").append(warespec);
        sb.append(", prodAddr=").append(prodAddr);
        sb.append(", producer=").append(producer);
        sb.append(", wareunit=").append(wareunit);
        sb.append(", saleprice=").append(saleprice);
        sb.append(", createdTime=").append(createdTime);
        sb.append("]");
        return sb.toString();
    }
}
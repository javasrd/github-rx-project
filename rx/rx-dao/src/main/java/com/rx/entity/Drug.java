package com.rx.entity;

import java.math.BigDecimal;
import javax.persistence.*;

public class Drug {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 商品ID(来自海典ERP)
     */
    private String wareid;

    /**
     * 条形码
     */
    private String barcode;

    /**
     * 助记码
     */
    private String abc;

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
    private String wareunit;

    /**
     * 在售/停售状态  1：在售； 2：停售
     */
    private Byte status;

    /**
     * 售价
     */
    private BigDecimal saleprice;

    /**
     * 当天药品库存
     */
    private Integer inventory;

    /**
     * 剂量
     */
    private String jl;

    /**
     * 疗程
     */
    private String lc;

    /**
     * 频次
     */
    private String pc;

    /**
     * 用法用量
     */
    private String yfyl;

    /**
     * 选项(最小剂量单位药品的单价)
     */
    private BigDecimal xuanx;

    /**
     * 最小可出售包装规格
     */
    private String saleminspec;

    /**
     * 最小可出售包装剂量单位
     */
    private String saleminunit;

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
     * 获取商品ID(来自海典ERP)
     *
     * @return wareid - 商品ID(来自海典ERP)
     */
    public String getWareid() {
        return wareid;
    }

    /**
     * 设置商品ID(来自海典ERP)
     *
     * @param wareid 商品ID(来自海典ERP)
     */
    public void setWareid(String wareid) {
        this.wareid = wareid == null ? null : wareid.trim();
    }

    /**
     * 获取条形码
     *
     * @return barcode - 条形码
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * 设置条形码
     *
     * @param barcode 条形码
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    /**
     * 获取助记码
     *
     * @return abc - 助记码
     */
    public String getAbc() {
        return abc;
    }

    /**
     * 设置助记码
     *
     * @param abc 助记码
     */
    public void setAbc(String abc) {
        this.abc = abc == null ? null : abc.trim();
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
     * @return wareunit - 单位
     */
    public String getWareunit() {
        return wareunit;
    }

    /**
     * 设置单位
     *
     * @param wareunit 单位
     */
    public void setWareunit(String wareunit) {
        this.wareunit = wareunit == null ? null : wareunit.trim();
    }

    /**
     * 获取在售/停售状态  1：在售； 2：停售
     *
     * @return status - 在售/停售状态  1：在售； 2：停售
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置在售/停售状态  1：在售； 2：停售
     *
     * @param status 在售/停售状态  1：在售； 2：停售
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取售价
     *
     * @return saleprice - 售价
     */
    public BigDecimal getSaleprice() {
        return saleprice;
    }

    /**
     * 设置售价
     *
     * @param saleprice 售价
     */
    public void setSaleprice(BigDecimal saleprice) {
        this.saleprice = saleprice;
    }

    /**
     * 获取当天药品库存
     *
     * @return inventory - 当天药品库存
     */
    public Integer getInventory() {
        return inventory;
    }

    /**
     * 设置当天药品库存
     *
     * @param inventory 当天药品库存
     */
    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    /**
     * 获取剂量
     *
     * @return jl - 剂量
     */
    public String getJl() {
        return jl;
    }

    /**
     * 设置剂量
     *
     * @param jl 剂量
     */
    public void setJl(String jl) {
        this.jl = jl == null ? null : jl.trim();
    }

    /**
     * 获取疗程
     *
     * @return lc - 疗程
     */
    public String getLc() {
        return lc;
    }

    /**
     * 设置疗程
     *
     * @param lc 疗程
     */
    public void setLc(String lc) {
        this.lc = lc == null ? null : lc.trim();
    }

    /**
     * 获取频次
     *
     * @return pc - 频次
     */
    public String getPc() {
        return pc;
    }

    /**
     * 设置频次
     *
     * @param pc 频次
     */
    public void setPc(String pc) {
        this.pc = pc == null ? null : pc.trim();
    }

    /**
     * 获取用法用量
     *
     * @return yfyl - 用法用量
     */
    public String getYfyl() {
        return yfyl;
    }

    /**
     * 设置用法用量
     *
     * @param yfyl 用法用量
     */
    public void setYfyl(String yfyl) {
        this.yfyl = yfyl == null ? null : yfyl.trim();
    }

    /**
     * 获取选项(最小剂量单位药品的单价)
     *
     * @return xuanx - 选项(最小剂量单位药品的单价)
     */
    public BigDecimal getXuanx() {
        return xuanx;
    }

    /**
     * 设置选项(最小剂量单位药品的单价)
     *
     * @param xuanx 选项(最小剂量单位药品的单价)
     */
    public void setXuanx(BigDecimal xuanx) {
        this.xuanx = xuanx;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
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
        sb.append(", jl=").append(jl);
        sb.append(", lc=").append(lc);
        sb.append(", pc=").append(pc);
        sb.append(", yfyl=").append(yfyl);
        sb.append(", xuanx=").append(xuanx);
        sb.append(", saleminspec=").append(saleminspec);
        sb.append(", saleminunit=").append(saleminunit);
        sb.append("]");
        return sb.toString();
    }
}
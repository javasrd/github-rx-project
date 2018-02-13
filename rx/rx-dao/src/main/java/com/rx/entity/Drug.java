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

    public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	@Override
	public String toString() {
		return "Drug [id=" + id + ", wareid=" + wareid + ", barcode=" + barcode + ", abc=" + abc + ", warename="
				+ warename + ", waresimname=" + waresimname + ", warespec=" + warespec + ", prodAddr=" + prodAddr
				+ ", producer=" + producer + ", wareunit=" + wareunit + ", status=" + status + ", saleprice="
				+ saleprice + ", inventory=" + inventory + "]";
	}

}
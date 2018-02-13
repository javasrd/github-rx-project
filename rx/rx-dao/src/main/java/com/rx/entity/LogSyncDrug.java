package com.rx.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rx.bean.SyncDrugLogStatus;

@Table(name = "log_sync_drug")
public class LogSyncDrug {
	
    public LogSyncDrug() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LogSyncDrug(Long id, Date createdDate, Integer status, String errormsg, String url, String filePath) {
		super();
		this.id = id;
		this.createdDate = createdDate;
		this.status = status;
		this.errormsg = errormsg;
		this.url = url;
		this.filePath = filePath;
	}

	/**
     * 自增ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建日期
     */
    @Column(name = "created_date")
    private Date createdDate;

    /**
     * 同步状态。1:成功；2：文件格式错误；3：连接失败
     */
    private Integer status;

    /**
     * 错误信息
     */
    private String errormsg;

    /**
     * 数据源地址
     */
    private String url;
    
    private String filePath;

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
     * 获取创建日期
     *
     * @return created_date - 创建日期
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * 设置创建日期
     *
     * @param createdDate 创建日期
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    /**
     * 获取创建日期
     *
     * @return created_date - 创建日期
     */
    public String getCreatedDateStr() {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(createdDate);
    }

    /**
     * 获取同步状态。1:成功；2：文件格式错误；3：连接失败
     *
     * @return status - 同步状态。1:成功；2：文件格式错误；3：连接失败
     */
    public Integer getStatus() {
        return status;
    }
    
    /**
     * 设置同步状态。1:成功；2：文件格式错误；3：连接失败
     *
     * @param status 同步状态。1:成功；2：文件格式错误；3：连接失败
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    /**
     * 获取同步状态。1:成功；2：文件格式错误；3：连接失败
     *
     * @return status - 同步状态。1:成功；2：文件格式错误；3：连接失败
     */
    public String getStatusStr() {
    	return SyncDrugLogStatus.getValue(status);
    }

    /**
     * 获取错误信息
     *
     * @return errormsg - 错误信息
     */
    public String getErrormsg() {
        return errormsg;
    }

    /**
     * 设置错误信息
     *
     * @param errormsg 错误信息
     */
    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg == null ? null : errormsg.trim();
    }

    /**
     * 获取数据源地址
     *
     * @return url - 数据源地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置数据源地址
     *
     * @param url 数据源地址
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "LogSyncDrug [id=" + id + ", createdDate=" + createdDate + ", status=" + status + ", errormsg="
				+ errormsg + ", url=" + url + ", filePath=" + filePath + "]";
	}

}
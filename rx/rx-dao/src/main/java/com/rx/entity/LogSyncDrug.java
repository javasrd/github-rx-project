package com.rx.entity;

import java.util.Date;
import javax.persistence.*;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.rx.bean.SyncDrugLogStatus;

@Table(name = "log_sync_drug")
public class LogSyncDrug {
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

    /**
     * 同步文件保存目录
     */
    @Column(name = "file_path")
    private String filePath;

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
     * 获取记录创建时间字符串
     *
     * @return createdTimeStr - 记录创建时间
     */
    public String getCreatedDateStr() {
        return DateFormatUtils.format(createdDate, "yyyy-MM-dd HH:mm:ss");
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
     * 获取同步状态。
     * 
     	1:成功；
		2：连接失败；
		3：请求异常；
		4：获取保存文件目录错误；
		5：写入文本文件异常；
		6：HTTP请求返回为空；
		7：解压TXT文本文件异常；
		8：读取TXT文本文件异常；
		9：文件格式错误；
     *
     * @return status
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

    /**
     * 获取同步文件保存目录
     *
     * @return file_path - 同步文件保存目录
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 设置同步文件保存目录
     *
     * @param filePath 同步文件保存目录
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", status=").append(status);
        sb.append(", errormsg=").append(errormsg);
        sb.append(", url=").append(url);
        sb.append(", filePath=").append(filePath);
        sb.append("]");
        return sb.toString();
    }
}
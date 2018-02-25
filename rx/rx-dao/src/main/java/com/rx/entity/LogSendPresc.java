package com.rx.entity;

import java.util.Date;
import javax.persistence.*;

import org.apache.commons.lang3.time.DateFormatUtils;

@Table(name = "log_send_presc")
public class LogSendPresc {
    /**
     * 自增ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 发送状态。1:成功；2：失败；3：连接失败
     */
    private Integer status;

    /**
     * 数据接收方
     */
    private String url;

    /**
     * 处方ID(FK ref  prescription:id)
     */
    @Column(name = "presc_id")
    private Long prescId;

    /**
     * 所发送的数据
     */
    private String data;

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
     * 获取创建时间
     *
     * @return created_time - 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
    
    /**
     * 获取记录创建时间字符串
     *
     * @return createdTimeStr - 记录创建时间
     */
    public String getCreatedTimeStr() {
        return DateFormatUtils.format(createdTime, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取发送状态。1:成功；2：失败；3：连接失败
     *
     * @return status - 发送状态。1:成功；2：失败；3：连接失败
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置发送状态。1:成功；2：失败；3：连接失败
     *
     * @param status 发送状态。1:成功；2：失败；3：连接失败
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    /**
     * 获取发送状态。1:成功；2：失败；3：连接失败
     *
     * @return status - 发送状态。1:成功；2：失败；3：连接失败
     */
    public String getStatusStr() {
    	String statusStr = "";
    	switch (status) {
		case 1:
			statusStr = "成功";
			break;
		case 2:
			statusStr = "失败";
			break;
		case 3:
			statusStr = "连接失败";
			break;

		default:
			break;
		}
        return statusStr;
    }

    /**
     * 获取数据接收方
     *
     * @return url - 数据接收方
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置数据接收方
     *
     * @param url 数据接收方
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 获取处方ID(FK ref  prescription:id)
     *
     * @return presc_id - 处方ID(FK ref  prescription:id)
     */
    public Long getPrescId() {
        return prescId;
    }

    /**
     * 设置处方ID(FK ref  prescription:id)
     *
     * @param prescId 处方ID(FK ref  prescription:id)
     */
    public void setPrescId(Long prescId) {
        this.prescId = prescId;
    }

    /**
     * 获取所发送的数据
     *
     * @return data - 所发送的数据
     */
    public String getData() {
        return data;
    }

    /**
     * 设置所发送的数据
     *
     * @param data 所发送的数据
     */
    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", status=").append(status);
        sb.append(", url=").append(url);
        sb.append(", prescId=").append(prescId);
        sb.append(", data=").append(data);
        sb.append("]");
        return sb.toString();
    }
}
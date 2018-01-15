package com.rx.entity;

import java.util.Date;
import javax.persistence.*;

public class User {
    /**
     * 自增ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 父帐户ID（某帐户下可以有子帐户）
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 用户登录名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户登录口令
     */
    private String password;

    /**
     * 帐户创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 所在部门
     */
    private String department;

    /**
     * 用户EMAIL地址
     */
    private String email;

    /**
     * 帐户类型。1：前台帐户；2：后台帐户
     */
    private Integer type;

    /**
     * 联系人姓名
     */
    @Column(name = "link_man")
    private String linkMan;

    /**
     * 联系人电话号码
     */
    @Column(name = "link_phone_num")
    private String linkPhoneNum;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 帐户状态。1：有效；2：无效
     */
    private Integer status;

    /**
     * 是否删除（1-未删除，2-删除，默认1）
     */
    private Integer deleted;

    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取父帐户ID（某帐户下可以有子帐户）
     *
     * @return parent_id - 父帐户ID（某帐户下可以有子帐户）
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父帐户ID（某帐户下可以有子帐户）
     *
     * @param parentId 父帐户ID（某帐户下可以有子帐户）
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取用户登录名
     *
     * @return username - 用户登录名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户登录名
     *
     * @param username 用户登录名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 获取用户登录口令
     *
     * @return password - 用户登录口令
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置用户登录口令
     *
     * @param password 用户登录口令
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取帐户创建时间
     *
     * @return created_time - 帐户创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置帐户创建时间
     *
     * @param createdTime 帐户创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取所在部门
     *
     * @return department - 所在部门
     */
    public String getDepartment() {
        return department;
    }

    /**
     * 设置所在部门
     *
     * @param department 所在部门
     */
    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    /**
     * 获取用户EMAIL地址
     *
     * @return email - 用户EMAIL地址
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置用户EMAIL地址
     *
     * @param email 用户EMAIL地址
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取帐户类型。1：前台帐户；2：后台帐户
     *
     * @return type - 帐户类型。1：前台帐户；2：后台帐户
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置帐户类型。1：前台帐户；2：后台帐户
     *
     * @param type 帐户类型。1：前台帐户；2：后台帐户
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取联系人姓名
     *
     * @return link_man - 联系人姓名
     */
    public String getLinkMan() {
        return linkMan;
    }

    /**
     * 设置联系人姓名
     *
     * @param linkMan 联系人姓名
     */
    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan == null ? null : linkMan.trim();
    }

    /**
     * 获取联系人电话号码
     *
     * @return link_phone_num - 联系人电话号码
     */
    public String getLinkPhoneNum() {
        return linkPhoneNum;
    }

    /**
     * 设置联系人电话号码
     *
     * @param linkPhoneNum 联系人电话号码
     */
    public void setLinkPhoneNum(String linkPhoneNum) {
        this.linkPhoneNum = linkPhoneNum == null ? null : linkPhoneNum.trim();
    }

    /**
     * 获取手机号码
     *
     * @return mobile - 手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号码
     *
     * @param mobile 手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 获取帐户状态。1：有效；2：无效
     *
     * @return status - 帐户状态。1：有效；2：无效
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置帐户状态。1：有效；2：无效
     *
     * @param status 帐户状态。1：有效；2：无效
     */
    public void setStatus(Integer status) {
        this.status = status;
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

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", username=").append(username);
        sb.append(", nickname=").append(nickname);
        sb.append(", password=").append(password);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", department=").append(department);
        sb.append(", email=").append(email);
        sb.append(", type=").append(type);
        sb.append(", linkMan=").append(linkMan);
        sb.append(", linkPhoneNum=").append(linkPhoneNum);
        sb.append(", mobile=").append(mobile);
        sb.append(", status=").append(status);
        sb.append(", deleted=").append(deleted);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}
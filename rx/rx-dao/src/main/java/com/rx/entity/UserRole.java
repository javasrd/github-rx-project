package com.rx.entity;

import javax.persistence.*;

@Table(name = "user_role")
public class UserRole {
    /**
     * 用户角色关系表自增ID
     */
    @Id
    @Column(name = "user_role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRoleId;

    /**
     * FK,引用用户ID( ref user:id )
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * FK,引用角色ID ( ref role:role_id )
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 是否删除（1-未删除，2-删除，默认1）
     */
    private Integer deleted;

    /**
     * 获取用户角色关系表自增ID
     *
     * @return user_role_id - 用户角色关系表自增ID
     */
    public Long getUserRoleId() {
        return userRoleId;
    }

    /**
     * 设置用户角色关系表自增ID
     *
     * @param userRoleId 用户角色关系表自增ID
     */
    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    /**
     * 获取FK,引用用户ID( ref user:id )
     *
     * @return user_id - FK,引用用户ID( ref user:id )
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置FK,引用用户ID( ref user:id )
     *
     * @param userId FK,引用用户ID( ref user:id )
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取FK,引用角色ID ( ref role:role_id )
     *
     * @return role_id - FK,引用角色ID ( ref role:role_id )
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置FK,引用角色ID ( ref role:role_id )
     *
     * @param roleId FK,引用角色ID ( ref role:role_id )
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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
        sb.append(", userRoleId=").append(userRoleId);
        sb.append(", userId=").append(userId);
        sb.append(", roleId=").append(roleId);
        sb.append(", deleted=").append(deleted);
        sb.append("]");
        return sb.toString();
    }
}
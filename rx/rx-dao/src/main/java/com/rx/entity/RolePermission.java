package com.rx.entity;

import javax.persistence.*;

@Table(name = "role_permission")
public class RolePermission {
    /**
     * 角色权限关系表自增ID
     */
    @Id
    @Column(name = "role_permission_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rolePermissionId;

    /**
     * FK. 引用角色ID( ref role:role_id)
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * FK,引用权限ID( ref menu:menu_id)
     */
    @Column(name = "permission_id")
    private Long permissionId;

    /**
     * 是否删除（1-未删除，2-删除，默认1）
     */
    private Integer deleted;

    /**
     * 获取角色权限关系表自增ID
     *
     * @return role_permission_id - 角色权限关系表自增ID
     */
    public Long getRolePermissionId() {
        return rolePermissionId;
    }

    /**
     * 设置角色权限关系表自增ID
     *
     * @param rolePermissionId 角色权限关系表自增ID
     */
    public void setRolePermissionId(Long rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    /**
     * 获取FK. 引用角色ID( ref role:role_id)
     *
     * @return role_id - FK. 引用角色ID( ref role:role_id)
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置FK. 引用角色ID( ref role:role_id)
     *
     * @param roleId FK. 引用角色ID( ref role:role_id)
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取FK,引用权限ID( ref menu:menu_id)
     *
     * @return permission_id - FK,引用权限ID( ref menu:menu_id)
     */
    public Long getPermissionId() {
        return permissionId;
    }

    /**
     * 设置FK,引用权限ID( ref menu:menu_id)
     *
     * @param permissionId FK,引用权限ID( ref menu:menu_id)
     */
    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
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
        sb.append(", rolePermissionId=").append(rolePermissionId);
        sb.append(", roleId=").append(roleId);
        sb.append(", permissionId=").append(permissionId);
        sb.append(", deleted=").append(deleted);
        sb.append("]");
        return sb.toString();
    }
}
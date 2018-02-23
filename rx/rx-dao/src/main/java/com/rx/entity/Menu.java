package com.rx.entity;

import javax.persistence.*;

public class Menu {
    /**
     * 菜单自增ID
     */
    @Id
    @Column(name = "menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    /**
     * 菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 菜单图标样式
     */
    @Column(name = "menu_icon")
    private String menuIcon;

    /**
     * 菜单描述
     */
    @Column(name = "menu_description")
    private String menuDescription;

    /**
     * 菜单URL
     */
    @Column(name = "menu_url")
    private String menuUrl;

    /**
     * 父id(菜单为树结构)
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 排序
     */
    @Column(name = "sort_num")
    private Integer sortNum;

    /**
     * 是否删除（1-未删除，2-删除，默认1）
     */
    private Integer deleted;

    /**
     * 获取菜单自增ID
     *
     * @return menu_id - 菜单自增ID
     */
    public Long getMenuId() {
        return menuId;
    }

    /**
     * 设置菜单自增ID
     *
     * @param menuId 菜单自增ID
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    /**
     * 获取菜单名称
     *
     * @return menu_name - 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单名称
     *
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * 获取菜单图标样式
     *
     * @return menu_icon - 菜单图标样式
     */
    public String getMenuIcon() {
        return menuIcon;
    }

    /**
     * 设置菜单图标样式
     *
     * @param menuIcon 菜单图标样式
     */
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon == null ? null : menuIcon.trim();
    }

    /**
     * 获取菜单描述
     *
     * @return menu_description - 菜单描述
     */
    public String getMenuDescription() {
        return menuDescription;
    }

    /**
     * 设置菜单描述
     *
     * @param menuDescription 菜单描述
     */
    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription == null ? null : menuDescription.trim();
    }

    /**
     * 获取菜单URL
     *
     * @return menu_url - 菜单URL
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * 设置菜单URL
     *
     * @param menuUrl 菜单URL
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    /**
     * 获取父id(菜单为树结构)
     *
     * @return parent_id - 父id(菜单为树结构)
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父id(菜单为树结构)
     *
     * @param parentId 父id(菜单为树结构)
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取排序
     *
     * @return sort_num - 排序
     */
    public Integer getSortNum() {
        return sortNum;
    }

    /**
     * 设置排序
     *
     * @param sortNum 排序
     */
    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
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
        sb.append(", menuId=").append(menuId);
        sb.append(", menuName=").append(menuName);
        sb.append(", menuIcon=").append(menuIcon);
        sb.append(", menuDescription=").append(menuDescription);
        sb.append(", menuUrl=").append(menuUrl);
        sb.append(", parentId=").append(parentId);
        sb.append(", sortNum=").append(sortNum);
        sb.append(", deleted=").append(deleted);
        sb.append("]");
        return sb.toString();
    }
}
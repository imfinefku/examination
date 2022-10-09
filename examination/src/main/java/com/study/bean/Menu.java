package com.study.bean;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * 菜单表
 */
public class Menu {

    private String id;

    @NotBlank(message = "菜单名称不能为空")
    @Length(max = 20, message = "菜单名称长度不能超过20个字符")
    private String name;

    private String icon;

    private int hidden;

    private int sort;

    private String masterid;

    private String mastername;

    @Length(max = 50, message = "菜单路由长度不能超过50个字符")
    private String route;

    private Date createtime;

    private List<Menu> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getHidden() {
        return hidden;
    }

    public void setHidden(int hidden) {
        this.hidden = hidden;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getMasterid() {
        return masterid;
    }

    public void setMasterid(String masterid) {
        this.masterid = masterid;
    }

    public String getMastername() {
        return mastername;
    }

    public void setMastername(String mastername) {
        this.mastername = mastername;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", hidden=" + hidden +
                ", sort=" + sort +
                ", masterid='" + masterid + '\'' +
                ", mastername='" + mastername + '\'' +
                ", route='" + route + '\'' +
                ", createtime=" + createtime +
                ", children=" + children +
                '}';
    }
}

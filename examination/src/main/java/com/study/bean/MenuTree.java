package com.study.bean;

import java.util.List;

/**
 * 菜单树
 */
public class MenuTree {

    private String id;

    private String name;

    private String route;

    private String icon;

    private int sort;

    private List<MenuTree> children;

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

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public List<MenuTree> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTree> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "MenuTree{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", route='" + route + '\'' +
                ", icon='" + icon + '\'' +
                ", sort=" + sort +
                ", children=" + children +
                '}';
    }
}

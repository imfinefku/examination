package com.study.bean;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * 用户表
 */
public class User {

    private String id;

    @NotBlank(message = "用户名称不能为空")
    @Length(max = 10, message = "用户名称长度不能超过10个字符")
    private String name;

    @NotBlank(message = "账号不能为空")
    @Length(max = 20, message = "账号长度不能超过20个字符")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(max = 20, message = "密码长度不能超过20个字符")
    private String password;

    private Date createtime;

    private String departmentid;

    private String departmentname;

    private Date lastlogintime;

    private List<UserRole> userRoleList;

    private List<String> userRoleLists;

    private List<MenuTree> menuList;

    private String token;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public Date getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public List<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRole> userRoleList) {
        this.userRoleList = userRoleList;
    }

    public List<String> getUserRoleLists() {
        return userRoleLists;
    }

    public void setUserRoleLists(List<String> userRoleLists) {
        this.userRoleLists = userRoleLists;
    }

    public List<MenuTree> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuTree> menuList) {
        this.menuList = menuList;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createtime=" + createtime +
                ", departmentid='" + departmentid + '\'' +
                ", departmentname='" + departmentname + '\'' +
                ", lastlogintime=" + lastlogintime +
                ", userRoleList=" + userRoleList +
                ", userRoleLists=" + userRoleLists +
                ", menuList=" + menuList +
                ", token='" + token + '\'' +
                '}';
    }
}
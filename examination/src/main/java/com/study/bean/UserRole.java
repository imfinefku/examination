package com.study.bean;

import java.util.Date;

/**
 * 用户角色表
 */
public class UserRole {

    private String id;

    private String userid;

    private String roleid;

    private Date createtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id='" + id + '\'' +
                ", userid='" + userid + '\'' +
                ", roleid='" + roleid + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}

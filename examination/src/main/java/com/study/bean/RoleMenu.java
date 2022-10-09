package com.study.bean;

import java.util.Date;

/**
 * 菜单权限实体
 */
public class RoleMenu {

    public static int ALL_SELECT = 0;

    public static int HALF_SELECT = 1;

    private String id;

    private String roleid;

    private String menuid;

    private int status;

    private Date createtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "MenuAuthority{" +
                "id='" + id + '\'' +
                ", roleid='" + roleid + '\'' +
                ", menuid='" + menuid + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}

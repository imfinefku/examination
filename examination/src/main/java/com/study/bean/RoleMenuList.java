package com.study.bean;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class RoleMenuList {

    @NotBlank(message="请选择一个角色")
    private String roleid;

    private List<String> allSelectMenu;

    private List<String> halfSelectMenu;

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public List<String> getAllSelectMenu() {
        return allSelectMenu;
    }

    public void setAllSelectMenu(List<String> allSelectMenu) {
        this.allSelectMenu = allSelectMenu;
    }

    public List<String> getHalfSelectMenu() {
        return halfSelectMenu;
    }

    public void setHalfSelectMenu(List<String> halfSelectMenu) {
        this.halfSelectMenu = halfSelectMenu;
    }
}

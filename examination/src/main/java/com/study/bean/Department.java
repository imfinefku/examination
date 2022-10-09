package com.study.bean;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * 部门实体
 */
public class Department {

    private String id;

    @NotBlank(message = "部门名称不能为空")
    @Length(max = 20, message = "部门名称长度不能超过20个字符")
    private String name;

    private int sort;

    private String masterid;

    private Date createtime;

    private List<Department> children;

    private String mastername;

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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public List<Department> getChildren() {
        return children;
    }

    public void setChildren(List<Department> children) {
        this.children = children;
    }

    public String getMastername() {
        return mastername;
    }

    public void setMastername(String mastername) {
        this.mastername = mastername;
    }
}

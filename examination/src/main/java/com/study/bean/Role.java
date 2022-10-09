package com.study.bean;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 角色实体
 */
public class Role {

    private String id;

    @NotBlank(message = "角色名称不能为空")
    @Length(max = 20, message = "角色名称长度不能超过20个字符")
    private String name;

    @Length(max=100,message="描述长度不能超过100个字符")
    private String description;

    private Date createtime;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}

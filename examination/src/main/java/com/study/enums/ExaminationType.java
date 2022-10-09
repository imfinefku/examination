package com.study.enums;

/**
 * 考试权限
 */
public enum ExaminationType {

    GONGKAI("完全公开",  0),
    PASSWORD("口令考试",  1);

    private String name;

    private int type;

    ExaminationType(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

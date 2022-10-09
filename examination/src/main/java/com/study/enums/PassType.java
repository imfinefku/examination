package com.study.enums;

/**
 * 考试权限
 */
public enum PassType {

    PASS("合格", "green"),
    NOPASS("不合格", "red"),
    DEFAULT("其他", "blue");

    private String name;

    private String color;

    PassType(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

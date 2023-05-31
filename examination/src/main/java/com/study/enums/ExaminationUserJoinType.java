package com.study.enums;

/**
 * 题型
 */
public enum ExaminationUserJoinType {

    WEIJIAOJUAN("未交卷",  0),
    YIJIAOJUAN("已交卷",  1),
    YIPIGAI("已批改",  2);

    private String name;

    private int type;

    ExaminationUserJoinType(String name, int type) {
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

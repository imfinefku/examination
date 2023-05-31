package com.study.enums;

/**
 * 题型
 */
public enum QuestionType {

    DANXUAN("单选题",  3),
    PANDUAN("判断题",  3),
    JIANDA("简答题",  10);

    private String name;

    private int score;

    QuestionType(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

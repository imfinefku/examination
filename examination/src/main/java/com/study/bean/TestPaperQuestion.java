package com.study.bean;

import java.util.Date;

/**
 * 试卷试题关联实体
 */
public class TestPaperQuestion {

    private String id;

    private String testpaper;

    private String question;

    private String content;

    private String type;

    private String subjectName;

    private int sort;

    private Date createtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestpaper() {
        return testpaper;
    }

    public void setTestpaper(String testpaper) {
        this.testpaper = testpaper;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "TestPaperQuestion{" +
                "id='" + id + '\'' +
                ", testpaper='" + testpaper + '\'' +
                ", question='" + question + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", sort=" + sort +
                ", createtime=" + createtime +
                '}';
    }
}

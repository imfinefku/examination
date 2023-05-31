package com.study.bean;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * 试卷实体
 */
public class TestPaper {

    private String id;

    @NotBlank(message = "试卷名称不能为空")
    @Length(max = 20, message = "试卷名称长度不能超过20个字符")
    private String name;

    private String subject;

    private String subjectName;

    private int xznum;

    private int pdnum;

    private int jdnum;

    private int score;

    @Min(value=1,message = "考试时间不能低于1分钟")
    private int time;

    private Date createtime;

    private List<TestPaperQuestion> testPaperQuestionList;

    private List<Question> questionList;

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getXznum() {
        return xznum;
    }

    public void setXznum(int xznum) {
        this.xznum = xznum;
    }

    public int getPdnum() {
        return pdnum;
    }

    public void setPdnum(int pdnum) {
        this.pdnum = pdnum;
    }

    public int getJdnum() {
        return jdnum;
    }

    public void setJdnum(int jdnum) {
        this.jdnum = jdnum;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public List<TestPaperQuestion> getTestPaperQuestionList() {
        return testPaperQuestionList;
    }

    public void setTestPaperQuestionList(List<TestPaperQuestion> testPaperQuestionList) {
        this.testPaperQuestionList = testPaperQuestionList;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    @Override
    public String toString() {
        return "TestPaper{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", xznum=" + xznum +
                ", pdnum=" + pdnum +
                ", jdnum=" + jdnum +
                ", score=" + score +
                ", time=" + time +
                ", createtime=" + createtime +
                ", testPaperQuestionList=" + testPaperQuestionList +
                ", questionList=" + questionList +
                '}';
    }
}

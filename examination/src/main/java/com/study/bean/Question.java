package com.study.bean;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 题目实体
 */
public class Question {

    private String id;

    @NotBlank(message = "题干不能为空")
    @Length(max = 1000, message = "题干长度不能超过1000个字符")
    private String content;

    @NotBlank(message = "题型不能为空")
    @Length(max = 20, message = "题型长度不能超过20个字符")
    private String type;

    private String subject;

    private String subjectName;

    private String a;

    private String b;

    private String c;

    private String d;

    private String imgurl;

    @Length(max = 1000, message = "解析长度不能超过1000个字符")
    private String analysis;

    @NotBlank(message = "答案不能为空")
    @Length(max = 1000, message = "答案长度不能超过1000个字符")
    private String answer;

    private Date createtime;

    private String reply;

    private int score;

    private int correctScore;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCorrectScore() {
        return correctScore;
    }

    public void setCorrectScore(int correctScore) {
        this.correctScore = correctScore;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", subject='" + subject + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", analysis='" + analysis + '\'' +
                ", answer='" + answer + '\'' +
                ", createtime=" + createtime +
                ", reply='" + reply + '\'' +
                ", score=" + score +
                ", correctScore=" + correctScore +
                '}';
    }
}

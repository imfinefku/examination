package com.study.bean;

import java.util.List;

/**
 * 提交试卷实体
 */
public class SubmitTestPaper {

    private String joinid;

    private List<Question> questionList;

    public String getJoinid() {
        return joinid;
    }

    public void setJoinid(String joinid) {
        this.joinid = joinid;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    @Override
    public String toString() {
        return "SubmitTestPaper{" +
                "joinid='" + joinid + '\'' +
                ", questionList=" + questionList +
                '}';
    }
}

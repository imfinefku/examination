package com.study.bean;

/**
 * 用户考试信息详情
 */
public class ExaminationUserJoinInfo {

    private String id;

    private String masterid;

    private String question;

    private String reply;

    private int correctScore;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMasterid() {
        return masterid;
    }

    public void setMasterid(String masterid) {
        this.masterid = masterid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public int getCorrectScore() {
        return correctScore;
    }

    public void setCorrectScore(int correctScore) {
        this.correctScore = correctScore;
    }

    @Override
    public String toString() {
        return "ExaminationUserJoinInfo{" +
                "id='" + id + '\'' +
                ", masterid='" + masterid + '\'' +
                ", question='" + question + '\'' +
                ", reply='" + reply + '\'' +
                ", correctScore=" + correctScore +
                '}';
    }
}

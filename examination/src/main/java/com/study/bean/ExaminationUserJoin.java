package com.study.bean;

import java.util.Date;

/**
 * 用户考试信息
 */
public class ExaminationUserJoin {

    private String id;

    private String userid;

    private String examination;

    private String testpaper;

    private Date starttime;

    private Date endtime;

    private int type;

    private int score;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getExamination() {
        return examination;
    }

    public void setExamination(String examination) {
        this.examination = examination;
    }

    public String getTestpaper() {
        return testpaper;
    }

    public void setTestpaper(String testpaper) {
        this.testpaper = testpaper;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

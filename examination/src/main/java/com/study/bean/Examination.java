package com.study.bean;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 考试实体
 */
public class Examination {

    private String id;

    @NotBlank(message = "考试名称不能为空")
    @Length(max = 20, message = "考试名称长度不能超过20个字符")
    private String name;

    @NotBlank(message = "考试试卷不能为空")
    private String testpaper;

    private String testPapername;

    private int qualifiedscore;

    private int type;

    private String password;

    private Date createtime;

    private TestPaper testPaperData;

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

    public String getTestpaper() {
        return testpaper;
    }

    public void setTestpaper(String testpaper) {
        this.testpaper = testpaper;
    }

    public String getTestPapername() {
        return testPapername;
    }

    public void setTestPapername(String testPapername) {
        this.testPapername = testPapername;
    }

    public int getQualifiedscore() {
        return qualifiedscore;
    }

    public void setQualifiedscore(int qualifiedscore) {
        this.qualifiedscore = qualifiedscore;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public TestPaper getTestPaperData() {
        return testPaperData;
    }

    public void setTestPaperData(TestPaper testPaperData) {
        this.testPaperData = testPaperData;
    }

    @Override
    public String toString() {
        return "Examination{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", testpaper='" + testpaper + '\'' +
                ", testPapername='" + testPapername + '\'' +
                ", qualifiedscore=" + qualifiedscore +
                ", type=" + type +
                ", password='" + password + '\'' +
                ", createtime=" + createtime +
                ", testPaperData=" + testPaperData +
                '}';
    }
}

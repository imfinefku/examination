package com.study.service;

import com.alibaba.fastjson.JSONObject;
import com.study.bean.*;
import com.study.dao.ExaminationManageMapper;
import com.study.enums.ExaminationUserJoinType;
import com.study.enums.PassType;
import com.study.enums.QuestionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminationManageService {

    @Autowired
    private ExaminationManageMapper examinationManageMapper;

    public List<Subject> getSubjectPage(Map dataMap) {
        return examinationManageMapper.getSubjectPage(dataMap);
    }

    public int getSubjectCount() {
        return examinationManageMapper.getSubjectCount();
    }

    public List<Subject> getSubjectAll() {
        return examinationManageMapper.getSubjectAll();
    }


    public List<Question> getQuestionPage(Map dataMap) {
        return examinationManageMapper.getQuestionPage(dataMap);
    }

    public int getQuestionCount() {
        return examinationManageMapper.getQuestionCount();
    }


    public List<TestPaper> getTestPaperPage(Map dataMap) {
        return examinationManageMapper.getTestPaperPage(dataMap);
    }

    public int getTestPaperCount() {
        return examinationManageMapper.getTestPaperCount();
    }

    public TestPaper getTestPaperById(String id) {
        TestPaper testPaper = examinationManageMapper.getTestPaperById(id);
        return testPaper;
    }

    public List<TestPaper> getTestPagerAll() {
        return examinationManageMapper.getTestPagerAll();
    }


    public List<Examination> getExaminationPage(Map dataMap) {
        return examinationManageMapper.getExaminationPage(dataMap);
    }

    public int getExaminationCount() {
        return examinationManageMapper.getExaminationCount();
    }

    public List<JSONObject> getOnlineExaminationPage(Map dataMap) {
        return examinationManageMapper.getOnlineExaminationPage(dataMap);
    }

    public int getOnlineExaminationCount() {
        return examinationManageMapper.getOnlineExaminationCount();
    }

    public Examination getExaminationById(String id) {
        return examinationManageMapper.getExaminationById(id);
    }

    public int addExaminationUserJoin(ExaminationUserJoin examinationUserJoin) {
        return examinationManageMapper.addExaminationUserJoin(examinationUserJoin);
    }

    public ExaminationUserJoin getExaminationUserJoinById(String id) {
        return examinationManageMapper.getExaminationUserJoinById(id);
    }

    public List<Question> getQuestionListByTestPaperId(String testpaper) {
        List<Question> rtnList = examinationManageMapper.getQuestionListByTestPaperId(testpaper);
        setQuestionScore(rtnList);
        return rtnList;
    }

    private void setQuestionScore(List<Question> questionList) {
        for (Question question : questionList) {
            if (QuestionType.DANXUAN.getName().equals(question.getType())) {
                question.setScore(QuestionType.DANXUAN.getScore());
            }
            if (QuestionType.PANDUAN.getName().equals(question.getType())) {
                question.setScore(QuestionType.PANDUAN.getScore());
            }
            if (QuestionType.JIANDA.getName().equals(question.getType())) {
                question.setScore(QuestionType.JIANDA.getScore());
            }
        }
    }

    public List<JSONObject> getMyScorePage(Map dataMap) {
        List<JSONObject> rtnList = examinationManageMapper.getMyScorePage(dataMap);
        setPassType(rtnList);
        return rtnList;
    }

    private void setPassType(List<JSONObject> rtnList){
        for (JSONObject json : rtnList) {
            int type = Integer.parseInt(json.getString("type"));
            if (ExaminationUserJoinType.WEIJIAOJUAN.getType() == type) {
                json.put("pass", ExaminationUserJoinType.WEIJIAOJUAN.getName());
                json.put("color",PassType.DEFAULT.getColor());
            } else if (ExaminationUserJoinType.YIJIAOJUAN.getType() == type) {
                json.put("pass", ExaminationUserJoinType.YIJIAOJUAN.getName());
                json.put("color",PassType.DEFAULT.getColor());
            } else if (ExaminationUserJoinType.YIPIGAI.getType() == type) {
                json.put("pass", PassType.PASS.getName());
                json.put("color",PassType.PASS.getColor());
            }
        }
    }

    public int getMyScoreCount(Map dataMap) {
        return examinationManageMapper.getMyScoreCount(dataMap);
    }

    public List<JSONObject> getTestPaperCorrectionPage(Map dataMap) {
        return examinationManageMapper.getTestPaperCorrectionPage(dataMap);
    }

    public int getTestPaperCorrectionCount(Map dataMap) {
        return examinationManageMapper.getTestPaperCorrectionCount(dataMap);
    }

    public List<Question> getTestPaperCorrectionQuestion(String joinid) {
        List<Question> rtnList = examinationManageMapper.getTestPaperCorrectionQuestion(joinid);
        setQuestionScore(rtnList);
        return rtnList;
    }

    public List<Question> getQuestionByTestPaper(String id){
        List<Question> rtnList = examinationManageMapper.getQuestionByTestPaper(id);
        setQuestionScore(rtnList);
        return rtnList;
    }

    public int getQuestionCountBySubject(String subject){
        return examinationManageMapper.getQuestionCountBySubject(subject);
    }

    public int getTestPaperCountByQuestion(String question){
        return examinationManageMapper.getTestPaperCountByQuestion(question);
    }

    public int getExaminationCountByTestpaper(String testpaper){
        return examinationManageMapper.getExaminationCountByTestpaper(testpaper);
    }

    public int getUserjoinCountByExamination(String examination){
        return examinationManageMapper.getUserjoinCountByExamination(examination);
    }


    public List<JSONObject> getReportByExaminationIdPage(Map dataMap){
        List<JSONObject> rtnList = examinationManageMapper.getReportByExaminationIdPage(dataMap);
        setPassType(rtnList);
        return rtnList;
    }

    public int getReportByExaminationIdCount(Map dataMap){
        return examinationManageMapper.getReportByExaminationIdCount(dataMap);
    }
}

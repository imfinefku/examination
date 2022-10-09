package com.study.service;

import com.alibaba.fastjson.JSONObject;
import com.study.bean.*;
import com.study.dao.ExaminationManageMapper;
import com.study.enums.ExaminationUserJoinType;
import com.study.enums.PassType;
import com.study.enums.QuestionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ExaminationManageService {

    @Autowired
    private ExaminationManageMapper examinationManageMapper;

    public int addSubject(Subject subject) {
        return examinationManageMapper.addSubject(subject);
    }

    public int updateSubject(Subject subject) {
        return examinationManageMapper.updateSubject(subject);
    }

    public int deleteSubject(String id) {
        return examinationManageMapper.deleteSubject(id);
    }

    public List<Subject> getSubjectPage(Map dataMap) {
        return examinationManageMapper.getSubjectPage(dataMap);
    }

    public int getSubjectCount() {
        return examinationManageMapper.getSubjectCount();
    }

    public List<Subject> getSubjectAll() {
        return examinationManageMapper.getSubjectAll();
    }

    public int addQuestion(Question question) {
        return examinationManageMapper.addQuestion(question);
    }

    public int updateQuestion(Question question) {
        return examinationManageMapper.updateQuestion(question);
    }

    public int deleteQuestion(String id) {
        return examinationManageMapper.deleteQuestion(id);
    }

    public List<Question> getQuestionPage(Map dataMap) {
        return examinationManageMapper.getQuestionPage(dataMap);
    }

    public int getQuestionCount() {
        return examinationManageMapper.getQuestionCount();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addTestPaper(TestPaper testPaper) {
        Date date = new Date();
        String id = UUID.randomUUID().toString();
        testPaper.setId(id);
        testPaper.setCreatetime(date);
        addTestPaperQuestionData(testPaper);
        examinationManageMapper.addTestPaper(testPaper);
        if (testPaper.getTestPaperQuestionList() != null && testPaper.getTestPaperQuestionList().size() > 0) {
            for (int i=0;i<testPaper.getTestPaperQuestionList().size();i++){
                //设置题目顺序
                TestPaperQuestion testPaperQuestion = testPaper.getTestPaperQuestionList().get(i);
                testPaperQuestion.setSort(i);
            }
            examinationManageMapper.addTestPaperQuestionList(testPaper.getTestPaperQuestionList());
        }
    }

    private void addTestPaperQuestionData(TestPaper testPaper) {
        int xznum = 0;
        int pdnum = 0;
        int jdnum = 0;
        int score = 0;
        Date date = new Date();
        for (TestPaperQuestion testPaperQuestion : testPaper.getTestPaperQuestionList()) {
            testPaperQuestion.setId(UUID.randomUUID().toString());
            testPaperQuestion.setTestpaper(testPaper.getId());
            testPaperQuestion.setCreatetime(date);
            if (QuestionType.DANXUAN.getName().equals(testPaperQuestion.getType())) {
                xznum++;
                score = score + QuestionType.DANXUAN.getScore();
            } else if (QuestionType.PANDUAN.getName().equals(testPaperQuestion.getType())) {
                pdnum++;
                score = score + QuestionType.PANDUAN.getScore();
            } else if (QuestionType.JIANDA.getName().equals(testPaperQuestion.getType())) {
                jdnum++;
                score = score + QuestionType.JIANDA.getScore();
            }
        }
        testPaper.setXznum(xznum);
        testPaper.setPdnum(pdnum);
        testPaper.setJdnum(jdnum);
        testPaper.setScore(score);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteTestPaper(String id) {
        examinationManageMapper.deleteTestPaper(id);
        examinationManageMapper.deleteTestPaperQuestion(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateTestPaper(TestPaper testPaper) {
        addTestPaperQuestionData(testPaper);
        examinationManageMapper.updateTestPaper(testPaper);
        examinationManageMapper.deleteTestPaperQuestion(testPaper.getId());
        if (testPaper.getTestPaperQuestionList() != null && testPaper.getTestPaperQuestionList().size() > 0) {
            examinationManageMapper.addTestPaperQuestionList(testPaper.getTestPaperQuestionList());
        }
    }

    public List<TestPaper> getTestPaperPage(Map dataMap) {
        return examinationManageMapper.getTestPaperPage(dataMap);
    }

    public int getTestPaperCount() {
        return examinationManageMapper.getTestPaperCount();
    }

    public TestPaper getTestPaperById(String id) {
        TestPaper testPaper = examinationManageMapper.getTestPaperById(id);
        if (testPaper != null) {
            testPaper.setTestPaperQuestionList(examinationManageMapper.getQuestionByTestPaperId(id));
        }
        return testPaper;
    }

    public List<TestPaper> getTestPagerAll() {
        return examinationManageMapper.getTestPagerAll();
    }

    public int addExamination(Examination examination) {
        return examinationManageMapper.addExamination(examination);
    }

    public int deleteExamination(Examination examination) {
        return examinationManageMapper.deleteExamination(examination);
    }

    public int updateExamination(Examination examination) {
        return examinationManageMapper.updateExamination(examination);
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

    public int addExaminationUserJoinInfoList(List<ExaminationUserJoinInfo> examinationUserJoinInfoList) {
        return examinationManageMapper.addExaminationUserJoinInfoList(examinationUserJoinInfoList);
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

    public int updateExaminationUserJoinType(String joinid) {
        Map dataMap = new HashMap();
        dataMap.put("joinid", joinid);
        dataMap.put("otype", ExaminationUserJoinType.WEIJIAOJUAN.getType());
        dataMap.put("dtype", ExaminationUserJoinType.YIJIAOJUAN.getType());
        return examinationManageMapper.updateExaminationUserJoinType(dataMap);
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
                int qualifiedscore = Integer.parseInt(json.getString("qualifiedscore"));
                int myScore = Integer.parseInt(json.getString("myScore"));
                if (myScore>=qualifiedscore){
                    json.put("pass", PassType.PASS.getName());
                    json.put("color",PassType.PASS.getColor());
                }else{
                    json.put("pass", PassType.NOPASS.getName());
                    json.put("color",PassType.NOPASS.getColor());
                }
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

    public int updateExaminationUserJoinTypeCorrection(String joinid) {
        Map dataMap = new HashMap();
        dataMap.put("joinid", joinid);
        dataMap.put("otype", ExaminationUserJoinType.YIJIAOJUAN.getType());
        dataMap.put("dtype", ExaminationUserJoinType.YIPIGAI.getType());
        return examinationManageMapper.updateExaminationUserJoinType(dataMap);
    }

    public int updateExaminationUserJoinInfo(ExaminationUserJoinInfo examinationUserJoinInfo) {
        return examinationManageMapper.updateExaminationUserJoinInfo(examinationUserJoinInfo);
    }

    public int updateExaminationUserJoinScore(String joinid, int score) {
        return examinationManageMapper.updateExaminationUserJoinScore(joinid, score);
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

    public void autoCorrection(List<ExaminationUserJoinInfo> examinationUserJoinInfoList){
        if (examinationUserJoinInfoList.size()>0){
            int autoCorrectionCount=0;
            int totalScore=0;
            for (ExaminationUserJoinInfo examinationUserJoinInfo : examinationUserJoinInfoList){
                Question question =examinationManageMapper.getQuestionById(examinationUserJoinInfo.getQuestion());
                if (QuestionType.DANXUAN.getName().equals(question.getType())){//单选题自动批改
                    if (question.getAnswer().equals(examinationUserJoinInfo.getReply())){
                        examinationUserJoinInfo.setCorrectScore(QuestionType.DANXUAN.getScore());
                        totalScore+=QuestionType.DANXUAN.getScore();
                    }
                    autoCorrectionCount++;
                } else if (QuestionType.PANDUAN.getName().equals(question.getType())){//判断题自动批改
                    if (question.getAnswer().equals(examinationUserJoinInfo.getReply())){
                        examinationUserJoinInfo.setCorrectScore(QuestionType.PANDUAN.getScore());
                        totalScore+=QuestionType.PANDUAN.getScore();
                    }
                    autoCorrectionCount++;
                }
            }
            //如果试卷全是选择题或判断题，将试卷置为批改完成
            if (examinationUserJoinInfoList.size()==autoCorrectionCount && examinationUserJoinInfoList.size()!=0){
                //修改总分
                updateExaminationUserJoinScore(examinationUserJoinInfoList.get(0).getMasterid(), totalScore);
                //将试卷置为已批改
                updateExaminationUserJoinTypeCorrection(examinationUserJoinInfoList.get(0).getMasterid());
            }
        }
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

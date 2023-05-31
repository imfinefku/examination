package com.study.dao;

import com.alibaba.fastjson.JSONObject;
import com.study.bean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface ExaminationManageMapper {

    public int addSubject(Subject subject);

    public int updateSubject(Subject subject);

    public int deleteSubject(@Param("id") String id);

    public List<Subject> getSubjectPage(Map dataMap);

    public int getSubjectCount();

    public List<Subject> getSubjectAll();

    public int addQuestion(Question question);

    public int updateQuestion(Question question);

    public int deleteQuestion(@Param("id") String id);

    public List<Question> getQuestionPage(Map dataMap);

    public List<Question> getQuestionByTestPaper(@Param("id") String id);

    public int getQuestionCount();

    public int addTestPaper(TestPaper testPaper);

    public int addTestPaperQuestionList(List<TestPaperQuestion> testPaperQuestionList);

    public int deleteTestPaper(@Param("id") String id);

    public int deleteTestPaperQuestion(@Param("testpaper") String testpaper);

    public int updateTestPaper(TestPaper testPaper);

    public List<TestPaper> getTestPaperPage(Map dataMap);

    public int getTestPaperCount();

    public TestPaper getTestPaperById(@Param("id") String id);

    public List<TestPaperQuestion> getQuestionByTestPaperId(@Param("id") String id);

    public List<TestPaper> getTestPagerAll();

    public int addExamination(Examination examination);

    public int deleteExamination(Examination examination);

    public int updateExamination(Examination examination);

    public List<Examination> getExaminationPage(Map dataMap);

    public int getExaminationCount();

    public List<JSONObject> getOnlineExaminationPage(Map dataMap);

    public int getOnlineExaminationCount();

    public Examination getExaminationById(@Param("id") String id);

    public int addExaminationUserJoin(ExaminationUserJoin examinationUserJoin);

    public ExaminationUserJoin getExaminationUserJoinById(@Param("id") String id);

    public List<Question> getQuestionListByTestPaperId(@Param("testpaper") String testpaper);

    public int addExaminationUserJoinInfoList(List<ExaminationUserJoinInfo> examinationUserJoinInfoList);

    public int updateExaminationUserJoinType(Map dataMap);

    public List<JSONObject> getMyScorePage(Map dataMap);

    public int getMyScoreCount(Map dataMap);

    public List<JSONObject> getTestPaperCorrectionPage(Map dataMap);

    public int getTestPaperCorrectionCount(Map dataMap);

    public List<Question> getTestPaperCorrectionQuestion(@Param("joinid") String joinid);

    public int updateExaminationUserJoinInfo(ExaminationUserJoinInfo examinationUserJoinInfo);

    public int updateExaminationUserJoinScore(@Param("joinid") String joinid,@Param("score") int score);

    public int getQuestionCountBySubject(@Param("subject") String subject);

    public int getTestPaperCountByQuestion(@Param("question") String question);

    public int getExaminationCountByTestpaper(@Param("testpaper") String testpaper);

    public int getUserjoinCountByExamination(@Param("examination") String examination);

    public Question getQuestionById(@Param("id") String id);

    public List<JSONObject> getReportByExaminationIdPage(Map dataMap);

    public int getReportByExaminationIdCount(Map dataMap);
}
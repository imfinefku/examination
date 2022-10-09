package com.study.controller;

import com.alibaba.fastjson.JSONObject;
import com.study.bean.*;
import com.study.common.bean.CommonResult;
import com.study.common.util.UserContextHolder;
import com.study.enums.ExaminationType;
import com.study.enums.ExaminationUserJoinType;
import com.study.service.ExaminationManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * 考试管理controller
 */
@RestController
@RequestMapping("examinationManage")
public class ExaminationManageController {

    @Autowired
    private ExaminationManageService examinationManageService;

    /**
     * 添加科目
     *
     * @param subject
     * @param validResult
     * @return
     */
    @PostMapping("/addSubject")
    public CommonResult addSubject(@RequestBody @Valid Subject subject, BindingResult validResult) {
        Date date = new Date();
        subject.setId(UUID.randomUUID().toString());
        subject.setCreatetime(date);
        int result = examinationManageService.addSubject(subject);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 修改科目
     *
     * @param subject
     * @param validResult
     * @return
     */
    @PostMapping("/updateSubject")
    public CommonResult updateSubject(@RequestBody @Valid Subject subject, BindingResult validResult) {
        int result = examinationManageService.updateSubject(subject);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 删除科目
     *
     * @param subject
     * @return
     */
    @PostMapping("/deleteSubject")
    public CommonResult deleteSubject(@RequestBody Subject subject) {
        int questionCount = examinationManageService.getQuestionCountBySubject(subject.getId());
        if (questionCount > 0) {
            return CommonResult.failed("该科目下已存在试题");
        }
        int result = examinationManageService.deleteSubject(subject.getId());
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 分页获取科目
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getSubjectPage")
    public CommonResult getSubjectPage(@RequestParam int page, @RequestParam int limit) {
        int start = limit * page - limit;
        Map dataMap = new HashMap();
        dataMap.put("start", start);
        dataMap.put("limit", limit);
        List<Subject> dataList = examinationManageService.getSubjectPage(dataMap);
        int count = examinationManageService.getSubjectCount();
        return CommonResult.success(dataList, count);
    }

    /**
     * 获取所有科目
     *
     * @return
     */
    @GetMapping("/getSubjectAll")
    public CommonResult getSubjectAll() {
        List<Subject> dataList = examinationManageService.getSubjectAll();
        return CommonResult.success(dataList);
    }

    /**
     * 添加题目
     *
     * @param question
     * @param validResult
     * @return
     */
    @PostMapping("/addQuestion")
    public CommonResult addQuestion(@RequestBody @Valid Question question, BindingResult validResult) {
        Date date = new Date();
        question.setId(UUID.randomUUID().toString());
        question.setCreatetime(date);
        int result = examinationManageService.addQuestion(question);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 修改题目
     *
     * @param question
     * @param validResult
     * @return
     */
    @PostMapping("/updateQuestion")
    public CommonResult updateQuestion(@RequestBody @Valid Question question, BindingResult validResult) {
        int result = examinationManageService.updateQuestion(question);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 删除题目
     *
     * @param question
     * @return
     */
    @PostMapping("/deleteQuestion")
    public CommonResult deleteQuestion(@RequestBody Question question) {
        int testPaperCount = examinationManageService.getTestPaperCountByQuestion(question.getId());
        if (testPaperCount > 0) {
            return CommonResult.failed("该题目已被试卷引用");
        }
        int result = examinationManageService.deleteQuestion(question.getId());
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 分页获取题目
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getQuestionPage")
    public CommonResult getQuestionPage(@RequestParam int page, @RequestParam int limit) {
        int start = limit * page - limit;
        Map dataMap = new HashMap();
        dataMap.put("start", start);
        dataMap.put("limit", limit);
        List<Question> dataList = examinationManageService.getQuestionPage(dataMap);
        int count = examinationManageService.getQuestionCount();
        return CommonResult.success(dataList, count);
    }

    /**
     * 添加试卷
     *
     * @param testPaper
     * @param validResult
     * @return
     */
    @PostMapping("/addTestPaper")
    public CommonResult addTestPaper(@RequestBody @Valid TestPaper testPaper, BindingResult validResult) {
        if (testPaper.getTestPaperQuestionList() == null || testPaper.getTestPaperQuestionList().size() == 0) {
            return CommonResult.failed("试题不能为空");
        }
        for (TestPaperQuestion t : testPaper.getTestPaperQuestionList()) {
            int flag = 0;
            for (TestPaperQuestion temp : testPaper.getTestPaperQuestionList()) {
                if (t.getQuestion().equals(temp.getQuestion())) {
                    flag++;
                }
            }
            if (flag >= 2) {
                return CommonResult.failed("试卷中存在重复试题");
            }
        }
        examinationManageService.addTestPaper(testPaper);
        return CommonResult.success();
    }

    /**
     * 删除试卷
     *
     * @param testPaper
     * @return
     */
    @PostMapping("/deleteTestPaper")
    public CommonResult deleteTestPaper(@RequestBody TestPaper testPaper) {
        int examinationCount = examinationManageService.getExaminationCountByTestpaper(testPaper.getId());
        if (examinationCount > 0) {
            return CommonResult.failed("该试卷已被考试使用");
        }
        examinationManageService.deleteTestPaper(testPaper.getId());
        return CommonResult.success();
    }

    /**
     * 修改试卷
     *
     * @param testPaper
     * @param validResult
     * @return
     */
    @PostMapping("/updateTestPaper")
    public CommonResult updateTestPaper(@RequestBody @Valid TestPaper testPaper, BindingResult validResult) {
        examinationManageService.updateTestPaper(testPaper);
        return CommonResult.success();
    }

    /**
     * 分页获取试卷
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getTestPaperPage")
    public CommonResult getTestPaperPage(@RequestParam int page, @RequestParam int limit) {
        int start = limit * page - limit;
        Map dataMap = new HashMap();
        dataMap.put("start", start);
        dataMap.put("limit", limit);
        List<TestPaper> dataList = examinationManageService.getTestPaperPage(dataMap);
        int count = examinationManageService.getTestPaperCount();
        return CommonResult.success(dataList, count);
    }

    /**
     * 根据id查询试卷详细信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getTestPagerById")
    public CommonResult getTestPagerById(@RequestParam String id) {
        return CommonResult.success(examinationManageService.getTestPaperById(id));
    }

    /**
     * 获取所有的试卷信息
     *
     * @return
     */
    @GetMapping("/getTestPagerAll")
    public CommonResult getTestPagerAll() {
        return CommonResult.success(examinationManageService.getTestPagerAll());
    }

    /**
     * 添加考试信息
     *
     * @param examination
     * @param validResult
     * @return
     */
    @PostMapping("/addExamination")
    public CommonResult addExamination(@RequestBody @Valid Examination examination, BindingResult validResult) {
        if (examination.getQualifiedscore() > examination.getTestPaperData().getScore()) {
            return CommonResult.failed("合格分数不能高于试卷总分");
        }
        Date date = new Date();
        examination.setId(UUID.randomUUID().toString());
        examination.setCreatetime(date);
        int result = examinationManageService.addExamination(examination);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 删除考试
     *
     * @param examination
     * @return
     */
    @PostMapping("/deleteExamination")
    public CommonResult deleteExamination(@RequestBody Examination examination) {
        int userjoinCount = examinationManageService.getUserjoinCountByExamination(examination.getId());
        if (userjoinCount > 0) {
            return CommonResult.failed("考试已在进行中，无法删除");
        }
        int result = examinationManageService.deleteExamination(examination);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 修改考试信息
     *
     * @param examination
     * @param validResult
     * @return
     */
    @PostMapping("/updateExamination")
    public CommonResult updateExamination(@RequestBody @Valid Examination examination, BindingResult validResult) {
        if (examination.getQualifiedscore() > examination.getTestPaperData().getScore()) {
            return CommonResult.failed("合格分数不能高于试卷总分");
        }
        int result = examinationManageService.updateExamination(examination);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 分页获取考试信息
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getExaminationPage")
    public CommonResult getExaminationPage(@RequestParam int page, @RequestParam int limit) {
        int start = limit * page - limit;
        Map dataMap = new HashMap();
        dataMap.put("start", start);
        dataMap.put("limit", limit);
        List<Examination> dataList = examinationManageService.getExaminationPage(dataMap);
        int count = examinationManageService.getExaminationCount();
        return CommonResult.success(dataList, count);
    }

    /**
     * 分页获取在线考试数据
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getOnlineExaminationPage")
    public CommonResult getOnlineExaminationPage(@RequestParam int page, @RequestParam int limit) {
        int start = limit * page - limit;
        Map dataMap = new HashMap();
        dataMap.put("start", start);
        dataMap.put("limit", limit);
        List<JSONObject> dataList = examinationManageService.getOnlineExaminationPage(dataMap);
        int count = examinationManageService.getOnlineExaminationCount();
        return CommonResult.success(dataList, count);
    }

    /**
     * 参加考试
     *
     * @param examMessage
     * @return
     */
    @PostMapping("/joinExamination")
    public CommonResult joinExamination(@RequestBody Examination examMessage) {
        Examination examination = examinationManageService.getExaminationById(examMessage.getId());
        if (ExaminationType.PASSWORD.getType() == examination.getType()) {//口令考试
            if (!examination.getPassword().equals(examMessage.getPassword())) {
                return CommonResult.failed("口令错误");
            }
        }
        TestPaper testPaper = examinationManageService.getTestPaperById(examination.getTestpaper());
        ExaminationUserJoin examinationUserJoin = new ExaminationUserJoin();
        Date starttime = new Date();
        Date endtime = new Date(starttime.getTime() + (testPaper.getTime() + 10) * 60 * 1000);
        examinationUserJoin.setId(UUID.randomUUID().toString());
        examinationUserJoin.setUserid(UserContextHolder.getInstance().getId());
        examinationUserJoin.setStarttime(starttime);
        examinationUserJoin.setExamination(examination.getId());
        examinationUserJoin.setTestpaper(testPaper.getId());
        examinationUserJoin.setEndtime(endtime);
        examinationUserJoin.setType(ExaminationUserJoinType.WEIJIAOJUAN.getType());
        examinationManageService.addExaminationUserJoin(examinationUserJoin);
        return CommonResult.success(examinationUserJoin.getId());
    }

    /**
     * 根据参加考试信息获取试卷
     *
     * @param joinMessage
     * @return
     */
    @GetMapping("/getTestPaperByJoinId")
    public CommonResult getTestPaperByJoinId(ExaminationUserJoin joinMessage) {
        ExaminationUserJoin examinationUserJoin = examinationManageService.getExaminationUserJoinById(joinMessage.getId());
        if (examinationUserJoin == null) {
            return CommonResult.failed("没有参加考试信息");
        }
        if (examinationUserJoin.getType() != ExaminationUserJoinType.WEIJIAOJUAN.getType()) {
            return CommonResult.failed("本次考试已交卷");
        }
        TestPaper testPaper = examinationManageService.getTestPaperById(examinationUserJoin.getTestpaper());
        List<Question> questionList = examinationManageService.getQuestionListByTestPaperId(testPaper.getId());
        testPaper.setQuestionList(questionList);
        return CommonResult.success(testPaper);
    }

    /**
     * 交卷
     *
     * @param submitTestPaper
     * @return
     */
    @PostMapping("/submitTestPaper")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public CommonResult submitTestPaper(@RequestBody SubmitTestPaper submitTestPaper) {
        ExaminationUserJoin examinationUserJoin = examinationManageService.getExaminationUserJoinById(submitTestPaper.getJoinid());
        Date date = new Date();
        if (date.compareTo(examinationUserJoin.getEndtime()) > 0) {
            return CommonResult.failed("考试已超时，交卷失败");
        }
        //根据joinid修改参加考试信息状态，将未交卷改为已交卷，防止多次提交
        int submit = examinationManageService.updateExaminationUserJoinType(submitTestPaper.getJoinid());
        if (submit <= 0) {
            return CommonResult.failed("本次考试已交卷");
        }
        List<ExaminationUserJoinInfo> examinationUserJoinInfoList = new ArrayList<ExaminationUserJoinInfo>();
        for (Question question : submitTestPaper.getQuestionList()) {
            ExaminationUserJoinInfo examinationUserJoinInfo = new ExaminationUserJoinInfo();
            examinationUserJoinInfo.setId(UUID.randomUUID().toString());
            examinationUserJoinInfo.setMasterid(submitTestPaper.getJoinid());
            examinationUserJoinInfo.setQuestion(question.getId());
            examinationUserJoinInfo.setReply(question.getReply());
            examinationUserJoinInfoList.add(examinationUserJoinInfo);
        }
        //自动批改选择题和判断题
        examinationManageService.autoCorrection(examinationUserJoinInfoList);
        if (examinationUserJoinInfoList.size() > 0) {
            int result = examinationManageService.addExaminationUserJoinInfoList(examinationUserJoinInfoList);
            if (result <= 0) {
                return CommonResult.failed();
            }
        }
        return CommonResult.success();
    }

    /**
     * 分页获取我的成绩数据
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getMyScorePage")
    public CommonResult getMyScorePage(@RequestParam int page, @RequestParam int limit) {
        int start = limit * page - limit;
        Map dataMap = new HashMap();
        dataMap.put("start", start);
        dataMap.put("limit", limit);
        dataMap.put("userid", UserContextHolder.getInstance().getId());
        List<JSONObject> dataList = examinationManageService.getMyScorePage(dataMap);
        int count = examinationManageService.getMyScoreCount(dataMap);
        return CommonResult.success(dataList, count);
    }

    /**
     * 分页获取需要批改的试卷
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getTestPaperCorrectionPage")
    public CommonResult getTestPaperCorrectionPage(@RequestParam int page, @RequestParam int limit) {
        int start = limit * page - limit;
        Map dataMap = new HashMap();
        dataMap.put("start", start);
        dataMap.put("limit", limit);
        dataMap.put("userid", UserContextHolder.getInstance().getId());
        List<JSONObject> dataList = examinationManageService.getTestPaperCorrectionPage(dataMap);
        int count = examinationManageService.getTestPaperCorrectionCount(dataMap);
        return CommonResult.success(dataList, count);
    }

    /**
     * 获取需要批改的试卷信息
     *
     * @param joinid
     * @return
     */
    @GetMapping("/getTestPaperCorrectionByJoinId")
    public CommonResult getTestPaperCorrectionByJoinId(@RequestParam String joinid) {
        ExaminationUserJoin examinationUserJoin = examinationManageService.getExaminationUserJoinById(joinid);
        TestPaper testPaper = examinationManageService.getTestPaperById(examinationUserJoin.getTestpaper());
        List<Question> questionList = examinationManageService.getTestPaperCorrectionQuestion(joinid);
        testPaper.setQuestionList(questionList);
        return CommonResult.success(testPaper);
    }

    /**
     * 提交批改完成试卷
     *
     * @param submitTestPaper
     * @return
     */
    @PostMapping("/submitCorrect")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public CommonResult submitCorrect(@RequestBody SubmitTestPaper submitTestPaper) {
        //根据joinid修改参加考试信息状态，将已交卷改为已批改，防止多次批改
        int submit = examinationManageService.updateExaminationUserJoinTypeCorrection(submitTestPaper.getJoinid());
        if (submit <= 0) {
            return CommonResult.failed("该试卷已批改");
        }
        List<Question> questionList = submitTestPaper.getQuestionList();
        int totalNum = 0;
        for (Question question : questionList) {
            ExaminationUserJoinInfo examinationUserJoinInfo = new ExaminationUserJoinInfo();
            examinationUserJoinInfo.setMasterid(submitTestPaper.getJoinid());
            examinationUserJoinInfo.setQuestion(question.getId());
            examinationUserJoinInfo.setReply(question.getReply());
            examinationUserJoinInfo.setCorrectScore(question.getCorrectScore());
            totalNum += question.getCorrectScore();
            int result = examinationManageService.updateExaminationUserJoinInfo(examinationUserJoinInfo);
            if (result <= 0) {
                //手动回滚事务
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return CommonResult.failed("提交批改试卷失败");
            }
        }
        //更新批改总分
        examinationManageService.updateExaminationUserJoinScore(submitTestPaper.getJoinid(), totalNum);
        return CommonResult.success();
    }

    /**
     * 获取试卷预览数据
     *
     * @param id
     * @returnw
     */
    @GetMapping("/getTestPaperById")
    public CommonResult getTestPaperById(@RequestParam String id) {
        TestPaper testPaper = examinationManageService.getTestPaperById(id);
        List<Question> questionList = examinationManageService.getQuestionByTestPaper(id);
        testPaper.setQuestionList(questionList);
        return CommonResult.success(testPaper);
    }

    /**
     * 根据考试id获取成绩单
     *
     * @param id
     * @return
     */
    @GetMapping("/getReportByExaminationIdPage")
    public CommonResult getReportByExaminationIdPage(@RequestParam String id, @RequestParam int page, @RequestParam int limit) {
        int start = limit * page - limit;
        Map dataMap = new HashMap();
        dataMap.put("start", start);
        dataMap.put("limit", limit);
        dataMap.put("id", id);
        List<JSONObject> dataList = examinationManageService.getReportByExaminationIdPage(dataMap);
        int count = examinationManageService.getReportByExaminationIdCount(dataMap);
        return CommonResult.success(dataList, count);
    }

}
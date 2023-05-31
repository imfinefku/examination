package com.study.controller;

import com.alibaba.fastjson.JSONObject;
import com.study.bean.*;
import com.common.bean.CommonResult;
import com.common.util.UserContextHolder;
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
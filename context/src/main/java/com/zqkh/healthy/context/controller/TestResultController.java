package com.zqkh.healthy.context.controller;

import com.zqkh.healthy.context.appservice.inter.TestResultService;
import com.zqkh.healthy.feign.TestResultClient;
import com.zqkh.healthy.feign.dto.paper.app.TestResultToAppDto;
import com.zqkh.healthy.feign.vo.paper.SubmitAnAnswerVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 测题结果控制层
 *
 * @author 东来
 * @create 2018/5/11 0011
 */
@RestController
public class TestResultController  implements TestResultClient {

    @Resource
    private TestResultService testResultService;



    @Override
    public TestResultToAppDto getTestResultToApp(@PathVariable("id") String id) {
        return testResultService.getTestResultToApp(id);
    }

    @Override
    public TestResultToAppDto submitAnAnswer(@RequestBody SubmitAnAnswerVo submitAnAnswerVo) {
       return testResultService.submitAnAnswer(submitAnAnswerVo);

    }
}

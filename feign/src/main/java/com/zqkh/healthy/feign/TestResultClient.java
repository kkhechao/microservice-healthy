package com.zqkh.healthy.feign;

import com.zqkh.common.configuration.feign.BaseFeignConfiguration;
import com.zqkh.healthy.feign.dto.paper.app.TestResultToAppDto;
import com.zqkh.healthy.feign.vo.paper.SubmitAnAnswerVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 测试结果单Feign Client
 *
 * @author 东来
 * @create 2018/5/11 0011
 */
@FeignClient(name = "microservice-healthy-context",configuration = BaseFeignConfiguration.class)
public interface TestResultClient {


    /**
     * 获取测提结果
     * @param id
     * @return
     */
    @GetMapping("/testResult/{id}")
    TestResultToAppDto getTestResultToApp(@PathVariable("id") String id);

    /**
     * 提交试卷答案
     * @param submitAnAnswerVo
     * @return TestResultToAppDto
     */
    @PostMapping("/testResult/submit")
    TestResultToAppDto submitAnAnswer(@RequestBody SubmitAnAnswerVo submitAnAnswerVo);

}

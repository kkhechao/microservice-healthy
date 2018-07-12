package com.zqkh.healthy.context.appservice.inter;

import com.zqkh.healthy.feign.dto.paper.app.TestResultToAppDto;
import com.zqkh.healthy.feign.vo.paper.SubmitAnAnswerVo;

/**
 * 测题结果业务接口
 *
 * @author 东来
 * @create 2018/5/11 0011
 */
public interface TestResultService  {
    /**
     * 获取测提结果
     * @param id
     * @return
     */
    TestResultToAppDto getTestResultToApp(String id);

    /**
     * 提交试卷答案
     * @param submitAnAnswerVo
     */
    TestResultToAppDto submitAnAnswer(SubmitAnAnswerVo submitAnAnswerVo);
}

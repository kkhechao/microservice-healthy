package com.zqkh.healthy.context.controller;

import com.jovezhao.nest.log.Log;
import com.jovezhao.nest.utils.JsonUtils;
import com.zqkh.healthy.context.appservice.inter.TestResultService;
import com.zqkh.healthy.context.appservice.inter.TestUserInfoService;
import com.zqkh.healthy.feign.TestUserInfoClient;
import com.zqkh.healthy.feign.dto.paper.app.TestUserInfoToAppDto;
import com.zqkh.healthy.feign.vo.program.template.ProgramUserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Slf4j
public class TestUserInfoController implements TestUserInfoClient {
    /**
     * 日志记录
     */
    private Logger logger = LoggerFactory.getLogger(TestUserInfoController.class);

    @Resource
    private TestUserInfoService testUserInfoService;

    @Override
    public TestUserInfoToAppDto getTestUserToApp(@PathVariable("id") String id) {
        log.info("ttttttt");
        log.info("id===="+id);
        return testUserInfoService.UserInfo(id);
    }

    @Override
    public String saveUserInfo(ProgramUserInfoVo programUserInfoVo) {
        logger.info(JsonUtils.toJsonString(programUserInfoVo));
        return testUserInfoService.saveUserInfo(programUserInfoVo);
    }


}

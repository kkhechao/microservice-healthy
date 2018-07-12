package com.zqkh.healthy.feign;

import com.zqkh.common.configuration.feign.BaseFeignConfiguration;
import com.zqkh.healthy.feign.dto.paper.app.TestResultToAppDto;
import com.zqkh.healthy.feign.dto.paper.app.TestUserInfoToAppDto;
import com.zqkh.healthy.feign.vo.program.template.ProgramUserInfoVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @create 2018/5/11 0011
 */
@FeignClient(name = "microservice-healthy-context",configuration = BaseFeignConfiguration.class)
public interface TestUserInfoClient {
    /**
     * 获取测提结果
     * @param id
     * @return
     */
    @GetMapping("/testUserInfo/{id}")
    TestUserInfoToAppDto getTestUserToApp(@PathVariable("id")String id);
    @PostMapping("/testUser/add")
    public String saveUserInfo(@RequestBody ProgramUserInfoVo ProgramUserInfoVo);

}

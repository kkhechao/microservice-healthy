package com.zqkh.healthy.feign;

import com.zqkh.common.configuration.feign.BaseFeignConfiguration;
import com.zqkh.healthy.feign.dto.program.app.ProgramActionDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 解决方案Feign
 *
 * @author 东来
 * @create 2018/5/15 0015
 */
@FeignClient(name = "microservice-healthy-context",configuration = BaseFeignConfiguration.class)
public interface ProgramActionClient {

    /**
     * 查看解决方案
     * @param familyMemberId
     * @return
     */
    @GetMapping("/program/action")
    List<ProgramActionDto> selectProgramAction(@RequestParam(value = "familyMemberId",required = false) String familyMemberId);

}

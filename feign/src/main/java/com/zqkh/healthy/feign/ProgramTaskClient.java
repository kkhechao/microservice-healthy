package com.zqkh.healthy.feign;

import com.zqkh.common.configuration.feign.BaseFeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 方案任务Client
 *
 * @author 东来
 * @create 2018/6/8 0008
 */
@FeignClient(name = "microservice-healthy-context",configuration = BaseFeignConfiguration.class)
public interface ProgramTaskClient {

    /**
     * 修改方案任务完成状态
     * @param id:方案编号
     * @param familyMemberId:家庭成员编号
     * @param done:是否完成
     */
    @PostMapping("/program/task/edit/done")
    void editDone(@RequestParam(value = "id",required = false)String id,
                  @RequestParam(value = "familyMemberId",required = false)String familyMemberId,
                  @RequestParam(value = "done",required = false)boolean done);
}

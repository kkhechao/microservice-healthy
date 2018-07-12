package com.zqkh.healthy.context.controller;

import com.zqkh.healthy.context.appservice.inter.ProgramTaskService;
import com.zqkh.healthy.feign.ProgramTaskClient;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 方案任务控制层
 *
 * @author 东来
 * @create 2018/6/8 0008
 */
@RestController
public class ProgramTaskController  implements ProgramTaskClient {

    @Resource
    private ProgramTaskService programTaskService;

    @Override
    public void editDone(@RequestParam(value = "id",required = false)String id,
                         @RequestParam(value = "familyMemberId",required = false)String familyMemberId,
                         @RequestParam(value = "done",required = false)boolean done) {
        programTaskService.editDone(id, familyMemberId, done);
    }
}

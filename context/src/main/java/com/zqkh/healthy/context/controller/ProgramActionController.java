package com.zqkh.healthy.context.controller;

import com.zqkh.healthy.context.appservice.inter.ProgramActionService;
import com.zqkh.healthy.feign.ProgramActionClient;
import com.zqkh.healthy.feign.dto.program.app.ProgramActionDto;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 解决方案控制层
 *
 * @author 东来
 * @create 2018/5/15 0015
 */
@RestController
public class ProgramActionController implements ProgramActionClient {

    @Resource
    private ProgramActionService programActionService;

    @Override
    public List<ProgramActionDto> selectProgramAction(@RequestParam(value = "familyMemberId",required = false) String familyMemberId) {
        return programActionService.selectProgramAction(familyMemberId);
    }
}

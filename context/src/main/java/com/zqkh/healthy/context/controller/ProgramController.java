package com.zqkh.healthy.context.controller;

import com.zqkh.common.result.PageResult;
import com.zqkh.healthy.context.appservice.inter.ProgramResultService;
import com.zqkh.healthy.context.appservice.inter.ProgramService;
import com.zqkh.healthy.feign.ProgramClient;
import com.zqkh.healthy.feign.dto.program.app.ProgramInfoDto;
import com.zqkh.healthy.feign.dto.program.app.ProgramListDto;
import com.zqkh.healthy.feign.dto.program.app.ProgramResultInfoDto;
import com.zqkh.healthy.feign.vo.program.ProgramFeedbackVo;
import com.zqkh.healthy.feign.vo.program.ProgramSrcTypeEnum;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 解决方案控制层
 *
 * @author 东来
 * @create 2018/6/6 0006
 */
@RestController
public class ProgramController implements ProgramClient {

    @Resource
    private ProgramService programService;

    @Resource
    private ProgramResultService programResultService;

    @Override
    public PageResult<ProgramListDto> search(@RequestParam(value = "familyMemberId", required = false) String familyMemberId,
                                             @RequestParam(value = "key", required = false) String key,
                                             @RequestParam(value = "done", required = false) Boolean done,
                                             @RequestParam(value = "dayDone", required = false) Boolean dayDone,
                                             @RequestParam(value = "programSrcType",required = false)ProgramSrcTypeEnum programSrcType,
                                             @RequestParam(value = "src",required = false)String src,
                                             @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
                                             @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        return programService.search(familyMemberId, key, done, dayDone,programSrcType,src, pageIndex, pageSize);
    }

    @Override
    public ProgramInfoDto info(@PathVariable("id") String id) {
        return programService.info(id);
    }

    @Override
    public String feedback(@RequestBody ProgramFeedbackVo feedback) {
        return programService.feedback(feedback);
    }

    @Override
    public ProgramResultInfoDto endProgram(@RequestParam(value = "id", required = false) String id,
                                           @RequestParam(value = "familyMemberId", required = false) String familyMemberId) {
        return programService.endProgram(id, familyMemberId);
    }

    @Override
    public ProgramResultInfoDto getProgramResultInfo(@PathVariable("id") String id) {
        return programResultService.getProgramInfo(id);
    }

    @Override
    public void open(@RequestParam(value = "id",required = false) String id, @RequestParam(value = "familyMemberId",required = false) String familyMemberId) {
        programService.open(id, familyMemberId);
    }

    @Override
    public String copy(@RequestParam(value = "id",required = false) String id, @RequestParam(value = "familyMemberId",required = false) String familyMemberId) {
        return programService.copy(id, familyMemberId);
    }
}

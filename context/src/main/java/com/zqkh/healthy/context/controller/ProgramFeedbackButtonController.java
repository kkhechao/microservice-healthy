package com.zqkh.healthy.context.controller;

import com.zqkh.common.result.PageResult;
import com.zqkh.healthy.context.appservice.inter.ProgramFeedbackButtonService;
import com.zqkh.healthy.feign.ProgramFeedbackButtonClient;
import com.zqkh.healthy.feign.dto.program.app.ProgramFeedbackButtonListToAppDto;
import com.zqkh.healthy.feign.dto.program.feedback.ProgramFeedbackButtonInfoDto;
import com.zqkh.healthy.feign.dto.program.feedback.ProgramFeedbackButtonListDto;
import com.zqkh.healthy.feign.vo.program.ProgramFeedbackTypeEnum;
import com.zqkh.healthy.feign.vo.program.feedback.SaveProgramFeedbackVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 方案反馈按钮控制层
 *
 * @author 东来
 * @create 2018/7/4 0004
 */
@RestController
public class ProgramFeedbackButtonController implements ProgramFeedbackButtonClient {

    @Resource
    private ProgramFeedbackButtonService programFeedbackButtonService;

    @Override
    public PageResult<ProgramFeedbackButtonListDto> search(@RequestParam(value = "type", required = false) ProgramFeedbackTypeEnum type,
                                                           @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
                                                           @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        return programFeedbackButtonService.search(type, pageIndex, pageSize);
    }

    @Override
    public void save(@RequestBody SaveProgramFeedbackVo saveProgramFeedbackVo) {
        programFeedbackButtonService.save(saveProgramFeedbackVo);
    }

    @Override
    public ProgramFeedbackButtonInfoDto info(@PathVariable("id") String id) {
        return programFeedbackButtonService.info(id);
    }

    @Override
    public void del(@PathVariable("id") String id) {
        programFeedbackButtonService.del(id);
    }

    @Override
    public List<ProgramFeedbackButtonListToAppDto> appButtonList() {
        return programFeedbackButtonService.appButtonList();
    }
}

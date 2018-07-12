package com.zqkh.healthy.feign;

import com.zqkh.common.configuration.feign.BaseFeignConfiguration;
import com.zqkh.common.result.PageResult;
import com.zqkh.healthy.feign.dto.program.app.ProgramFeedbackButtonListToAppDto;
import com.zqkh.healthy.feign.dto.program.feedback.ProgramFeedbackButtonInfoDto;
import com.zqkh.healthy.feign.dto.program.feedback.ProgramFeedbackButtonListDto;
import com.zqkh.healthy.feign.vo.program.ProgramFeedbackTypeEnum;
import com.zqkh.healthy.feign.vo.program.feedback.SaveProgramFeedbackVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 方案反馈按钮Feign
 *
 * @author 东来
 * @create 2018/7/4 0004
 */
@FeignClient(name = "microservice-healthy-context",configuration = BaseFeignConfiguration.class)
public interface ProgramFeedbackButtonClient {


    /**
     * 方案反馈按钮列表
     * @param type :
     * @param pageIndex:第几页
     * @param pageSize:每页显示多少条
     * @return
     */
    @GetMapping("/program/feedback/button")
    PageResult<ProgramFeedbackButtonListDto> search(
                                                  @RequestParam(value = "type",required = false)ProgramFeedbackTypeEnum type,
                                                  @RequestParam(value = "pageIndex",required = false,defaultValue = "1") Integer pageIndex,
                                                  @RequestParam(value = "pageSize",required = false,defaultValue = "20") Integer pageSize);


    /**
     * 保存方案反馈按钮
     * @param saveProgramFeedbackVo
     */
    @PostMapping("/program/feedback/button")
    void save(SaveProgramFeedbackVo saveProgramFeedbackVo);


    /**
     * 获取反馈按钮详情
     * @param id:按钮编号
     * @return
     */
    @GetMapping("/program/feedback/button/{id}")
    ProgramFeedbackButtonInfoDto info(@PathVariable("id")String id);

    /**
     * 删除反馈按钮
     * @param id
     */
    @DeleteMapping("/program/feedback/button/{id}")
    void del(@PathVariable("id")String id);


    /**
     * APP方案按钮列表
     * @return
     */
    @GetMapping("/program/feedback/button/app")
    List<ProgramFeedbackButtonListToAppDto> appButtonList();

}

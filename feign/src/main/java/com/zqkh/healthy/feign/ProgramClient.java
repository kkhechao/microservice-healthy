package com.zqkh.healthy.feign;

import com.zqkh.common.configuration.feign.BaseFeignConfiguration;
import com.zqkh.common.result.PageResult;
import com.zqkh.healthy.feign.dto.program.app.ProgramInfoDto;
import com.zqkh.healthy.feign.dto.program.app.ProgramListDto;
import com.zqkh.healthy.feign.dto.program.app.ProgramResultInfoDto;
import com.zqkh.healthy.feign.vo.program.ProgramFeedbackVo;
import com.zqkh.healthy.feign.vo.program.ProgramSrcTypeEnum;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 解决方案Feign
 *
 * @author 东来
 * @create 2018/5/15 0015
 */
@FeignClient(name = "microservice-healthy-context",configuration = BaseFeignConfiguration.class)
public interface ProgramClient {

    /**
     * 搜索方案
     * @param familyMemberId:家庭成员编号
     * @param key:搜索KEY
     * @param done:是否完成
     * @param dayDone:当日是否完成
     * @param programSrcType :方案来源类型
     * @param src :方案来源
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/program")
    PageResult<ProgramListDto> search(@RequestParam(value = "familyMemberId",required = false) String familyMemberId,
                                    @RequestParam(value = "key",required = false)String key,
                                    @RequestParam(value = "done",required = false)Boolean done,
                                    @RequestParam(value = "dayDone",required = false)Boolean dayDone,
                                    @RequestParam(value = "programSrcType",required = false)ProgramSrcTypeEnum programSrcType,
                                    @RequestParam(value = "src",required = false)String src,
                                    @RequestParam(value = "pageIndex",required = false,defaultValue = "1")Integer pageIndex,
                                    @RequestParam(value = "pageSize",required = false,defaultValue = "20")Integer pageSize);


    /**
     * 获取方案详情
     * @param id
     * @return
     */
    @GetMapping("/program/{id}")
    ProgramInfoDto info(@PathVariable("id")String id);


    /**
     * 反馈
     * @param feedback
     */
    @PostMapping("/program/feedback")
    String feedback(@RequestBody ProgramFeedbackVo feedback);


    /**
     * 结束方案
     * @param id:方案编号
     * @param familyMemberId:家庭成员
     * @return
     */
    @PostMapping("/program/end")
    ProgramResultInfoDto endProgram(@RequestParam(value = "id",required = false) String id,
                                    @RequestParam(value = "familyMemberId",required = false) String familyMemberId);

    /**
     * 获取方案结果详情
     * @param id:方案结果编号
     * @return
     */
    @GetMapping("/program/result/{id}")
    ProgramResultInfoDto getProgramResultInfo(@PathVariable("id") String id);

    /**
     * 开启方案
     * @param id:方案编号
     * @param familyMemberId:家庭成员编号
     */
    @PostMapping("/program/open")
    void open(@RequestParam(value = "id",required = false) String id,
              @RequestParam(value = "familyMemberId",required = false) String familyMemberId);


    /**
     * 拷贝方案
     * @param id:方案编号
     * @param familyMemberId :家庭成员编号
     * @return :新方案编号
     */
    @PostMapping("/program/copy")
    String copy(@RequestParam(value = "id",required = false) String id, @RequestParam(value = "familyMemberId",required = false) String familyMemberId);

}

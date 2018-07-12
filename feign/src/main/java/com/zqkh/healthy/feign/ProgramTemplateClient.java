package com.zqkh.healthy.feign;

import com.zqkh.common.configuration.feign.BaseFeignConfiguration;
import com.zqkh.common.result.PageResult;
import com.zqkh.healthy.feign.dto.program.template.ProgramTemplateInfoDto;
import com.zqkh.healthy.feign.dto.program.template.ProgramTemplateListDto;
import com.zqkh.healthy.feign.vo.program.template.SaveProgramTemplateVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 方案模板
 *
 * @author 东来
 * @create 2018/6/4 0004
 */
@FeignClient(name = "microservice-healthy-context",configuration = BaseFeignConfiguration.class)
public interface ProgramTemplateClient {

    /**
     * 搜索
     * @param key
     * @param enable :开启状态
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/program/template")
    PageResult<ProgramTemplateListDto> search(@RequestParam(value = "key",required = false) String key,
                                              @RequestParam(value = "enable",required = false)Boolean enable,
                                              @RequestParam(value = "pageIndex",required = false,defaultValue = "1") Integer pageIndex,
                                              @RequestParam(value = "pageSize",required = false,defaultValue = "20") Integer pageSize);


    /**
     * 保存
     * @param saveProgramTemplateVo
     */
    @PostMapping("/program/template")
    void save(@RequestBody SaveProgramTemplateVo saveProgramTemplateVo);


    /**
     * 修改方案开启状态
     * @param id:方案编号
     * @param enable:是否开启
     */
    @PostMapping("/program/template/edit/enable")
    void editEnable(@RequestParam(value = "id",required = false) String id,@RequestParam(value = "enable",required = false) boolean enable);


    /**
     * 查看方案模板详情
     * @param id
     * @return
     */
    @GetMapping("/program/template/{id}")
    ProgramTemplateInfoDto info(@PathVariable("id")String id);
}

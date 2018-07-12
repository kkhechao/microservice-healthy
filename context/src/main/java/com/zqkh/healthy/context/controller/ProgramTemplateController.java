package com.zqkh.healthy.context.controller;

import com.zqkh.common.result.PageResult;
import com.zqkh.healthy.context.appservice.inter.ProgramTemplateService;
import com.zqkh.healthy.feign.ProgramTemplateClient;
import com.zqkh.healthy.feign.dto.program.template.ProgramTemplateInfoDto;
import com.zqkh.healthy.feign.dto.program.template.ProgramTemplateListDto;
import com.zqkh.healthy.feign.vo.program.template.SaveProgramTemplateVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 方案模板控制层
 *
 * @author 东来
 * @create 2018/6/4 0004
 */
@RestController
public class ProgramTemplateController implements ProgramTemplateClient {

    @Resource
    private ProgramTemplateService programTemplateService;

    @Override
    public PageResult<ProgramTemplateListDto> search(@RequestParam(value = "key",required = false) String key,
                                                     @RequestParam(value = "enable",required = false)Boolean enable,
                                                     @RequestParam(value = "pageIndex",required = false,defaultValue = "1") Integer pageIndex,
                                                     @RequestParam(value = "pageSize",required = false,defaultValue = "20") Integer pageSize) {
        return programTemplateService.search(key, enable,pageIndex, pageSize);
    }

    @Override
    public void save(@RequestBody SaveProgramTemplateVo saveProgramTemplateVo) {
        programTemplateService.save(saveProgramTemplateVo);
    }

    @Override
    public void editEnable(@RequestParam(value = "id",required = false) String id,@RequestParam(value = "enable",required = false) boolean enable) {
        programTemplateService.editEnable(id, enable);
    }

    @Override
    public ProgramTemplateInfoDto info(@PathVariable("id") String id) {
        return programTemplateService.info(id);
    }
}

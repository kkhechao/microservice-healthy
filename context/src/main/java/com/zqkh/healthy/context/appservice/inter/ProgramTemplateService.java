package com.zqkh.healthy.context.appservice.inter;

import com.zqkh.common.result.PageResult;
import com.zqkh.healthy.feign.dto.program.template.ProgramTemplateInfoDto;
import com.zqkh.healthy.feign.dto.program.template.ProgramTemplateListDto;
import com.zqkh.healthy.feign.vo.program.template.SaveProgramTemplateVo;

/**
 * 方案模板接口
 *
 * @author 东来
 * @create 2018/6/4 0004
 */
public interface ProgramTemplateService {

    /**
     * 搜索方案
     * @param key
     * @param enable
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageResult<ProgramTemplateListDto> search(String key,Boolean enable, Integer pageIndex, Integer pageSize);

    /**
     * 保存
     * @param saveProgramTemplateVo
     */
    void save(SaveProgramTemplateVo saveProgramTemplateVo);

    /**
     * 修改开启状态
     * @param id
     * @param enable
     */
    void editEnable(String id,boolean enable);

    /**
     * 查看方案详情
     * @param id
     * @return
     */
    ProgramTemplateInfoDto info(String id);
}

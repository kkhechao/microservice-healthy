package com.zqkh.healthy.context.appservice.inter;

import com.zqkh.common.result.PageResult;
import com.zqkh.healthy.feign.dto.paper.type.template.SearchTestPaperTypeTemplateListDto;
import com.zqkh.healthy.feign.dto.paper.type.template.TestPaperTypeTemplateInfoDto;
import com.zqkh.healthy.feign.vo.paper.type.template.SaveTestPaperTypeTemplateVo;

import java.util.List;

/**
 * 测题类型前端显示模板业务接口
 *
 * @author 东来
 * @create 2018/6/29 0029
 */
public interface TestPaperTypeTemplateService {

    /**
     * 搜索
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageResult<SearchTestPaperTypeTemplateListDto> search(Integer pageIndex, Integer pageSize);


    /**
     * 保存
     * @param saveTestPaperTypeTemplateVo
     */
    void save(SaveTestPaperTypeTemplateVo saveTestPaperTypeTemplateVo);


    /**
     * 删除
     * @param id
     */
    void del(String id);


    /**
     * 获取详情
     * @param id
     * @return
     */
    TestPaperTypeTemplateInfoDto info(String id);


    /**
     * 获取所有
     * @return
     */
    List<TestPaperTypeTemplateInfoDto> allList();
}

package com.zqkh.healthy.context.controller;

import com.zqkh.common.result.PageResult;
import com.zqkh.healthy.context.appservice.inter.TestPaperTypeTemplateService;
import com.zqkh.healthy.feign.TestPaperTypeTemplateClient;
import com.zqkh.healthy.feign.dto.paper.type.template.SearchTestPaperTypeTemplateListDto;
import com.zqkh.healthy.feign.dto.paper.type.template.TestPaperTypeTemplateInfoDto;
import com.zqkh.healthy.feign.vo.paper.type.template.SaveTestPaperTypeTemplateVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 测题类型前端显示模板控制层
 *
 * @author 东来
 * @create 2018/6/29 0029
 */
@RestController
public class TestPaperTypeTemplateController  implements TestPaperTypeTemplateClient {

    @Resource
    private TestPaperTypeTemplateService testPaperTypeTemplateService;


    @Override
    public PageResult<SearchTestPaperTypeTemplateListDto> search(@RequestParam(value = "pageIndex",required = false,defaultValue = "1") Integer pageIndex,
                                                                 @RequestParam(value = "pageSize",required = false,defaultValue = "20") Integer pageSize) {
        return testPaperTypeTemplateService.search(pageIndex, pageSize);
    }

    @Override
    public void save(@RequestBody SaveTestPaperTypeTemplateVo saveTestPaperTypeTemplateVo) {
        testPaperTypeTemplateService.save(saveTestPaperTypeTemplateVo);
    }

    @Override
    public void del(@PathVariable("id") String id) {
        testPaperTypeTemplateService.del(id);
    }

    @Override
    public TestPaperTypeTemplateInfoDto info(@PathVariable("id")String id) {
        return testPaperTypeTemplateService.info(id);
    }

    @Override
    public List<TestPaperTypeTemplateInfoDto> allList() {
        return testPaperTypeTemplateService.allList();
    }
}

package com.zqkh.healthy.feign;

import com.zqkh.common.configuration.feign.BaseFeignConfiguration;
import com.zqkh.common.result.PageResult;
import com.zqkh.healthy.feign.dto.paper.type.template.SearchTestPaperTypeTemplateListDto;
import com.zqkh.healthy.feign.dto.paper.type.template.TestPaperTypeTemplateInfoDto;
import com.zqkh.healthy.feign.vo.paper.type.template.SaveTestPaperTypeTemplateVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 测题类型前端显示模板
 *
 * @author 东来
 * @create 2018/6/29 0029
 */
@FeignClient(name = "microservice-healthy-context",configuration = BaseFeignConfiguration.class)
public interface TestPaperTypeTemplateClient {


    /**
     * 搜索模板
     * @param pageSize
     * @param pageIndex
     * @return
     */
    @GetMapping("/test/paper/type/template")
    PageResult<SearchTestPaperTypeTemplateListDto> search(@RequestParam(value = "pageIndex",required = false,defaultValue = "1") Integer pageIndex,
                                                          @RequestParam(value = "pageSize",required = false,defaultValue = "20") Integer pageSize);


    /**
     * 保存模板
     * @param saveTestPaperTypeTemplateVo
     */
    @PostMapping("/test/paper/type/template")
    void save(@RequestBody SaveTestPaperTypeTemplateVo saveTestPaperTypeTemplateVo);


    /**
     * 删除模板
     * @param id
     */
    @DeleteMapping("/test/paper/type/template/{id}")
    void del(@PathVariable("id")String id);


    /**
     * 查看详情
     * @param id
     * @return
     */
    @GetMapping("/test/paper/type/template/{id}")
    TestPaperTypeTemplateInfoDto info(@PathVariable("id")String id);

    /**
     * 查看所有模板
     * @return
     */
    @GetMapping("/test/paper/type/template/all")
    List<TestPaperTypeTemplateInfoDto> allList();

}

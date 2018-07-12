package com.zqkh.healthy.context.controller;

import com.zqkh.common.result.PageResult;
import com.zqkh.healthy.context.appservice.impl.domain.repository.TestPaperType;
import com.zqkh.healthy.context.appservice.inter.TestPaperTypeService;
import com.zqkh.healthy.feign.TestPaperTypeClient;
import com.zqkh.healthy.feign.dto.paper.app.type.TestPaperTypeListToAppDto;
import com.zqkh.healthy.feign.dto.paper.type.TestPaperTypeInfoDto;
import com.zqkh.healthy.feign.dto.paper.type.TestPaperTypeListDto;
import com.zqkh.healthy.feign.vo.paper.TestPaperTypeVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 试卷类型控制层
 *
 * @author 东来
 * @create 2018/5/28 0028
 */
@RestController
public class TestPaperTypeController implements TestPaperTypeClient {

    @Resource
    private TestPaperTypeService testPaperTypeService;

    @Override
    public void save(@RequestBody TestPaperTypeVo testPaperTypeVo) {
        testPaperTypeService.save(testPaperTypeVo);
    }

    @Override
    public TestPaperTypeInfoDto getInfo(@PathVariable("id") String id) {
        return testPaperTypeService.getInfo(id);
    }

    @Override
    public PageResult<TestPaperTypeListDto> getTestPaperTypeListDto(@RequestParam(value = "pageIndex",required = false,defaultValue = "1") Integer pageIndex,
                                                                    @RequestParam(value = "pageSize",required = false,defaultValue = "20") Integer pageSize) {
        return testPaperTypeService.getTestPaperTypeListDto(pageIndex, pageSize);
    }

    @Override
    public void del(@RequestParam("id") String id) {
        testPaperTypeService.del(id);
    }

    @Override
    public List<TestPaperTypeListDto> all() {
        return testPaperTypeService.all();
    }

    @Override
    public List<TestPaperTypeListToAppDto> getTaperTypeListToApp(@RequestParam(value = "familyMemberId",required = false) String familyMemberId) {
        return testPaperTypeService.getTaperTypeListToApp(familyMemberId);
    }

    @Override
    public void asc(String id, String userId) {
            testPaperTypeService.asc(id, userId);
    }

    @Override
    public void descend(String id, String userId) {
            testPaperTypeService.descend(id, userId);
    }
}

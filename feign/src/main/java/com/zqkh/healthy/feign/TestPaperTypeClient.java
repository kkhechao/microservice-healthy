package com.zqkh.healthy.feign;

import com.zqkh.common.configuration.feign.BaseFeignConfiguration;
import com.zqkh.common.result.PageResult;
import com.zqkh.healthy.feign.dto.paper.app.type.TestPaperTypeListToAppDto;
import com.zqkh.healthy.feign.dto.paper.type.TestPaperTypeInfoDto;
import com.zqkh.healthy.feign.dto.paper.type.TestPaperTypeListDto;
import com.zqkh.healthy.feign.vo.paper.TestPaperTypeVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 试题类型
 *
 * @author 东来
 * @create 2018/5/28 0028
 */
@FeignClient(name = "microservice-healthy-context",configuration = BaseFeignConfiguration.class)
public interface TestPaperTypeClient {

    /**
     * 保存试卷类型
     * @param testPaperTypeVo
     */
    @PostMapping("/testPaperType")
    void save(@RequestBody TestPaperTypeVo testPaperTypeVo);


    /**
     * 查看试卷详情
     * @param id
     * @return
     */
    @GetMapping("/testPaperType/{id}")
    TestPaperTypeInfoDto getInfo(@PathVariable("id") String id);


    /**
     * 查看试卷类型列表
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/testPaperType")
    PageResult<TestPaperTypeListDto> getTestPaperTypeListDto(@RequestParam(value = "pageIndex",required = false,defaultValue = "1") Integer pageIndex,
                                                             @RequestParam(value = "pageSize",required = false,defaultValue = "20") Integer pageSize);


    /**
     * 删除试卷类型
     * @param id
     */
    @PostMapping("/testPaperType/del")
    void del(@RequestParam(value = "id",required = false) String id);

    /**
     * 查询试卷类型下拉列表
     * @return
     */
    @GetMapping("/testPaperType/all")
    List<TestPaperTypeListDto> all();


    /**
     * 获取试题类型列表
     * @param familyMemberId
     * @return
     */
    @GetMapping("/testPaperType/app/list")
    List<TestPaperTypeListToAppDto> getTaperTypeListToApp(@RequestParam(value = "familyMemberId",required = false)String familyMemberId);

    /**
     * 升序
     * @param id:类型编号
     * @param userId:操作者
     */
    @PostMapping("/testPaperType/asc")
    void asc(@RequestParam(value = "id",required = false) String id,
             @RequestParam(value = "userId",required = false)String userId);


    /**
     * 降序
     * @param id:类型编号
     * @param userId:操作者
     */
    @PostMapping("/testPaperType/descend")
    void descend(@RequestParam(value = "id",required = false) String id,
                 @RequestParam(value = "userId",required = false)String userId);
}

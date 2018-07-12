package com.zqkh.healthy.feign;

import com.zqkh.common.configuration.feign.BaseFeignConfiguration;
import com.zqkh.common.result.PageResult;
import com.zqkh.healthy.feign.dto.paper.TestPaperCompletionDto;
import com.zqkh.healthy.feign.dto.paper.TestPaperInfoDto;
import com.zqkh.healthy.feign.dto.paper.TestPaperListDto;
import com.zqkh.healthy.feign.dto.paper.app.TestPaperInfoToAppDto;
import com.zqkh.healthy.feign.dto.paper.app.TestPaperListToAppDto;
import com.zqkh.healthy.feign.vo.paper.SaveTestPaperVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 试卷FeignClient
 *
 * @author 东来
 * @create 2018/5/7 0007
 */
@FeignClient(name = "microservice-healthy-context",configuration = BaseFeignConfiguration.class)
public interface TestPaperClient {

    /**
     * 保存试卷
     * @param saveTestPaperVo
     */
    @PostMapping("/testPaper")
    void saveTestPaper(@RequestBody SaveTestPaperVo saveTestPaperVo);


    /**
     * 搜索试卷
     * @param title:试卷标题
     * @param typeId:试卷类型
     * @param pageIndex:第几页
     * @param pageSize:每页显示多少条
     * @return
     */
    @GetMapping("/testPaper/search")
    PageResult<TestPaperListDto> search(@RequestParam(value = "title",required = false) String title,
                                               @RequestParam(value = "release",required = false) Boolean release,
                                               @RequestParam(value = "typeId",required = false) String typeId,
                                               @RequestParam(value = "pageIndex",required = false,defaultValue = "1") int pageIndex,
                                               @RequestParam(value = "pageSize",required = false,defaultValue = "20") int pageSize);


    /**
     * 获取试卷详情
     * @param id
     * @return
     */
    @GetMapping("/testPaper/{id}")
    TestPaperInfoDto info(@PathVariable("id")String id);

    /**
     * 根据试卷标题查询试题完成情况
     * @param title
     * @return
     */
    @GetMapping("/testPaper/completion/byTitle")
    List<TestPaperCompletionDto> getTestPaperCompletionByTile(@RequestParam("title") List<String> title,@RequestParam("type") String type,@RequestParam("familyMemberId") String familyMemberId);


    /**
     * 获取所有试卷
     * @param type
     * @return
     */
    @GetMapping("/testPaper")
    List<TestPaperListToAppDto> allTestPaperList(@RequestParam(name = "familyMemberId",required = false) String familyMemberId, @RequestParam(value = "type",required = false) String type);


    /**
     * 获取试题详情
     * @param id
     * @param familyMemberId
     * @return
     */
    @GetMapping("/testPaper/info/app")
    TestPaperInfoToAppDto getTestPaperInfoToAppById(@RequestParam(name = "id",required = false)String id, @RequestParam(name = "familyMemberId",required = false)String familyMemberId);


    /**
     * 删除试题
     * @param id
     */
    @PostMapping("/testPaper/del")
    void delTestPaper(@RequestParam(name = "id",required = false) String id);


    /**
     * 发布试卷
     * @param id
     * @param userId
     */
    @PostMapping("/testPaper/release")
    void release(@RequestParam(name = "id",required = false)String id,@RequestParam(name = "userId",required = false)String userId);

    /**
     * 取消发布试卷
     * @param id
     * @param userId
     */
    @PostMapping("/testPaper/unRelease")
    void unRelease(@RequestParam(name = "id",required = false)String id,@RequestParam(name = "userId",required = false)String userId);


    /**
     * 试题列表
     * @param familyMemberId
     * @return
     */
    @GetMapping("/testPaper/app/list")
    List<TestPaperListToAppDto> listToApp(@RequestParam(value = "familyMemberId",required = false)String familyMemberId,
                                          @RequestParam(value = "typeId",required = false)String typeId);

}

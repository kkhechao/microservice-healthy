package com.zqkh.healthy.context.controller;

import com.zqkh.common.result.PageResult;
import com.zqkh.healthy.context.appservice.inter.TestPaperService;
import com.zqkh.healthy.feign.TestPaperClient;
import com.zqkh.healthy.feign.dto.paper.*;
import com.zqkh.healthy.feign.dto.paper.app.TestPaperInfoToAppDto;
import com.zqkh.healthy.feign.dto.paper.app.TestPaperListToAppDto;
import com.zqkh.healthy.feign.vo.paper.SaveTestPaperVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 试卷控制层
 *
 * @author 东来
 * @create 2018/5/7 0007
 */
@RestController
public class TestPaperController implements TestPaperClient {

    @Resource
    private TestPaperService testPaperService;

    @Override
    public void saveTestPaper(@RequestBody  SaveTestPaperVo saveTestPaperVo) {
        testPaperService.saveTestPaper(saveTestPaperVo);
    }

    @Override
    public PageResult<TestPaperListDto> search(@RequestParam(value = "title",required = false) String title,@RequestParam(value = "release",required = false) Boolean release,
                                        @RequestParam(value = "typeId",required = false) String typeId,
                                        @RequestParam(value = "pageIndex",required = false,defaultValue = "1") int pageIndex,
                                        @RequestParam(value = "pageSize",required = false,defaultValue = "20") int pageSize){
        return testPaperService.search(title, release,typeId, pageIndex, pageSize);
    }

    @Override
    public TestPaperInfoDto info(@PathVariable("id") String id) {
        return testPaperService.info(id);
    }

    @Override
    public List<TestPaperCompletionDto> getTestPaperCompletionByTile(@RequestParam("title") List<String> title ,@RequestParam("type") String type,@RequestParam("familyMemberId") String familyMemberId) {
        return testPaperService.getTestPaperCompletionByTile(title,type,familyMemberId);
    }

    @Override
    public List<TestPaperListToAppDto> allTestPaperList(@RequestParam(name = "familyMemberId",required = false) String familyMemberId, @RequestParam(value = "type",required = false) String type) {
        return testPaperService.allTestPaperList(familyMemberId, type);
    }

    @Override
    public TestPaperInfoToAppDto getTestPaperInfoToAppById(@RequestParam(name = "id",required = false)String id, @RequestParam(name = "familyMemberId",required = false)String familyMemberId) {
        return testPaperService.getTestPaperInfoToAppById(id, familyMemberId);
    }

    @Override
    public void delTestPaper(@RequestParam(name = "id",required = false)String id) {
        testPaperService.delTestPaper(id);
    }

    /**
     * 发布试卷
     * @param id
     * @param userId
     */
    @Override
    public void release(@RequestParam(name = "id",required = false)String id,@RequestParam(name = "userId",required = false)String userId){
        testPaperService.release(id, userId);
    };

    /**
     * 取消发布试卷
     * @param id
     * @param userId
     */
    @Override
    public void unRelease(@RequestParam(name = "id",required = false)String id,@RequestParam(name = "userId",required = false)String userId){
        testPaperService.unReLease(id, userId);
    }


    @Override
    public List<TestPaperListToAppDto> listToApp(@RequestParam(value = "familyMemberId",required = false)String familyMemberId,
                                                 @RequestParam(value = "typeId",required = false)String typeId) {
        return testPaperService.listToApp(familyMemberId, typeId);
    }
}

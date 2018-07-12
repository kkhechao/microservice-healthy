package com.zqkh.healthy.context.appservice.inter;

import com.zqkh.common.result.PageResult;
import com.zqkh.healthy.feign.dto.paper.*;
import com.zqkh.healthy.feign.dto.paper.app.TestPaperInfoToAppDto;
import com.zqkh.healthy.feign.dto.paper.app.TestPaperListToAppDto;
import com.zqkh.healthy.feign.vo.paper.SaveTestPaperVo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 试卷业务层
 *
 * @author 东来
 * @create 2018/5/7 0007
 */
public interface TestPaperService  {

    /**
     * 保存试卷
     * @param saveTestPaperVo
     */
    void saveTestPaper(SaveTestPaperVo saveTestPaperVo);

    /**
     * 搜索试卷
     * @param title:试卷标题
     * @param release :是否发布
     * @param typeId:试卷类型
     * @param pageIndex:第几页
     * @param pageSize:每页显示多少条
     * @return
     */
    PageResult<TestPaperListDto> search(String title, Boolean release,String typeId, int pageIndex, int pageSize);

    /**
     * 获取试卷详情
     * @param id
     * @return
     */
    TestPaperInfoDto info(String id);

    /**
     * 根据试卷标题获取试卷完成情况
     * @param title:试卷标题
     * @param familyMemberId:家庭成员编号
     * @return
     */
    List<TestPaperCompletionDto> getTestPaperCompletionByTile(List<String> title,String type, String familyMemberId);


    /**
     * 获取所有试卷
     * @param familyMemberId
     * @param type
     * @return
     */
    List<TestPaperListToAppDto> allTestPaperList(String familyMemberId, String type);


    /**
     * 获取试题详情
     * @param id
     * @param familyMemberId
     * @return
     */
    TestPaperInfoToAppDto getTestPaperInfoToAppById(String id, String familyMemberId);

    /**
     * 删除试题
     * @param id
     */
    void delTestPaper(String id);

    /**
     * 发布
     * @param id
     * @param userId
     */
    void release(String id,String userId);

    /**
     * 取消发布
     * @param id
     * @param userId
     */
    void unReLease(String id,String userId);

    /**
     * 获取APP试题列表
     * @param familyMemberId:家庭成员编号
     * @param typeId:试题类型编号
     * @return
     */
    List<TestPaperListToAppDto> listToApp(String familyMemberId, String typeId);


    /**
     * 获取TestPaperListToAppDto
     * @param testPaperId
     * @param familyMemberId
     * @return
     */
    List<TestPaperListToAppDto> getTestPaperListToAppDto(List<String> testPaperId,String familyMemberId);

}

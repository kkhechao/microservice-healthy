package com.zqkh.healthy.context.appservice.inter;

import com.zqkh.common.result.PageResult;
import com.zqkh.healthy.context.appservice.impl.domain.repository.Program;
import com.zqkh.healthy.context.appservice.impl.domain.repository.ProgramTemplate;
import com.zqkh.healthy.feign.dto.program.app.ProgramInfoDto;
import com.zqkh.healthy.feign.dto.program.app.ProgramListDto;
import com.zqkh.healthy.feign.dto.program.app.ProgramResultInfoDto;
import com.zqkh.healthy.feign.vo.program.ProgramFeedbackVo;
import com.zqkh.healthy.feign.vo.program.ProgramSrcTypeEnum;

import java.util.List;

/**
 * 解决方案业务接口
 *
 * @author 东来
 * @create 2018/6/6 0006
 */
public interface ProgramService {

    /**
     * 搜索方案列表
     * @param familyMemberId: 家庭成员
     * @param key:搜索关键字
     * @param done:方案是否完成
     * @param dayDone:今日完成
     * @param programSrcType :方案来源类型
     * @param src :方案来源
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageResult<ProgramListDto> search(String familyMemberId, String key, Boolean done, Boolean dayDone, ProgramSrcTypeEnum programSrcType,String src, Integer pageIndex, Integer pageSize);


    /**
     * 获取方案详情
     * @param id
     * @return
     */
    ProgramInfoDto info(String id);


    /**
     * 反馈
     * @param feedback
     */
    String feedback(ProgramFeedbackVo feedback);


    /**
     * 结束方案
     * @param id:方案编号
     * @param familyMemberId:家庭成员编号
     * @return
     */
    ProgramResultInfoDto endProgram(String id, String familyMemberId);


    /**
     * 开启方案
     * @param id:方案编号
     * @param familyMemberId:家庭成员编号
     */
    void open(String id, String familyMemberId);


    /**
     * 拷贝方案
     * @param id:方案编号
     * @param familyMemberId :家庭成员编号
     * @return :返回方案编号
     */
    String copy(String id,String familyMemberId);






}

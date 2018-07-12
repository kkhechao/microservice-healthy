package com.zqkh.healthy.context.appservice.inter;

import com.zqkh.common.result.PageResult;
import com.zqkh.healthy.feign.dto.program.app.ProgramFeedbackButtonListToAppDto;
import com.zqkh.healthy.feign.dto.program.feedback.ProgramFeedbackButtonInfoDto;
import com.zqkh.healthy.feign.dto.program.feedback.ProgramFeedbackButtonListDto;
import com.zqkh.healthy.feign.vo.program.ProgramFeedbackTypeEnum;
import com.zqkh.healthy.feign.vo.program.feedback.SaveProgramFeedbackVo;

import java.util.List;

/**
 * 方案反馈按钮业务接口
 *
 * @author 东来
 * @create 2018/7/4 0004
 */
public interface ProgramFeedbackButtonService {


    /**
     * 搜索
     * @param type
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageResult<ProgramFeedbackButtonListDto> search(ProgramFeedbackTypeEnum type,Integer pageIndex, Integer pageSize);


    /**
     * 保存
     * @param saveProgramFeedbackVo
     */
    void save(SaveProgramFeedbackVo saveProgramFeedbackVo);


    /**
     * 获取方案反馈按钮详情
     * @param id:方案反馈按钮编号
     * @return
     */
    ProgramFeedbackButtonInfoDto info(String id);

    /**
     * 删除方案反馈按钮
     * @param id
     */
    void del(String id);

    /**
     * APP方案按钮列表
     * @return
     */
    List<ProgramFeedbackButtonListToAppDto> appButtonList();
}

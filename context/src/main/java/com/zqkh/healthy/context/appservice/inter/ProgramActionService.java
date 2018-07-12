package com.zqkh.healthy.context.appservice.inter;

import com.zqkh.healthy.feign.dto.program.app.ProgramActionDto;

import java.util.List;

/**
 * 解决方案业务接口
 *
 * @author 东来
 * @create 2018/5/15 0015
 */
public interface ProgramActionService {
    /**
     * 查看解决方案
     * @param familyMemberId
     * @return
     */
    List<ProgramActionDto> selectProgramAction(String familyMemberId);
}

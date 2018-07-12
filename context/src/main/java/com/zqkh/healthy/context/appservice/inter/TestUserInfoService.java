package com.zqkh.healthy.context.appservice.inter;

import com.zqkh.healthy.feign.dto.paper.app.TestUserInfoToAppDto;
import com.zqkh.healthy.feign.vo.program.template.ProgramUserInfoVo;

/**
 * 用户服务层
 */
public interface TestUserInfoService {
    /**
     *
     * @param id
     * @return
     */
    public TestUserInfoToAppDto UserInfo(String id);

    /**
     *
     * @param ProgramUserInfoVo
     * @return
     */
    public String saveUserInfo(ProgramUserInfoVo ProgramUserInfoVo);
}

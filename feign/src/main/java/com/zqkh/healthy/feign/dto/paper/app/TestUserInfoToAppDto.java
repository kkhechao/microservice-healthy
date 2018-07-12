package com.zqkh.healthy.feign.dto.paper.app;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 用户信息
 */
@NoArgsConstructor
@Setter
@Getter
public class TestUserInfoToAppDto {
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 用户id
     */
    private String id;
    /**
     * json
     */
    private String json;
}

package com.zqkh.healthy.feign.vo.paper;

import org.springframework.util.ObjectUtils;

/**
 * 试题类型枚举
 */
public enum TestQuestionsTypeEnum {

    /**
     * 单选
     */
    SINGLE_SELECTION,

    /**
     * 多选
     */
    MULTI_SELECTION,

    ;

    public static TestQuestionsTypeEnum getTestQuestionsTypeEnum(String name){
        if(ObjectUtils.isEmpty(name)){
            return null;
        }

        for (TestQuestionsTypeEnum testQuestionsTypeEnum:TestQuestionsTypeEnum.values()) {
            if(testQuestionsTypeEnum.name().equals(name)){
                return testQuestionsTypeEnum;
            }
        }
        return null;
    }


}

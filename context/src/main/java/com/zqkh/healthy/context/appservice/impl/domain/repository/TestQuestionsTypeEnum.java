package com.zqkh.healthy.context.appservice.impl.domain.repository;

import org.springframework.util.ObjectUtils;

/**
 * @author 东来
 * @create 2018/5/7 0007
 */
public enum  TestQuestionsTypeEnum {

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

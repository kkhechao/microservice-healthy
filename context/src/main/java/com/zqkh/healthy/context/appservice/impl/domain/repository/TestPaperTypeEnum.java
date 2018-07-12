package com.zqkh.healthy.context.appservice.impl.domain.repository;

import org.springframework.util.ObjectUtils;

/**
 * @author
 * 试卷类型
 */
public enum TestPaperTypeEnum {

    /**
     * 疾病自测题
     */
    DISEASE,
    /**
     * 健康自测题
     */
    HEALTHY,
    ;

    public static TestPaperTypeEnum getTestPaperTypeEnum(String name){
        if(ObjectUtils.isEmpty(name)){
            return null;
        }
        for (TestPaperTypeEnum testPaperTypeEnum: TestPaperTypeEnum.values()) {
            if(testPaperTypeEnum.name().equals(name)){
                return testPaperTypeEnum;
            }
        }
        return null;
    }
}

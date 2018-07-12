package com.zqkh.healthy.feign.vo.program;

import org.springframework.util.ObjectUtils;

/**
 * 方案反馈类型
 */
public enum ProgramFeedbackTypeEnum {

    /**
     * 难以坚持,放弃
     */
    GIVE_UP,

    /**
     * 变化不大,徘徊
     */
    WANDER,
    /**
     * 状态更好
     */
    BETTER_STATE
    ;


    public static final ProgramFeedbackTypeEnum getType(String type){
        if(ObjectUtils.isEmpty(type)){
            return null;
        }
        for (ProgramFeedbackTypeEnum t: ProgramFeedbackTypeEnum.values()) {
            if(type.equals(t.name())){
                return t;
            }

        }
        return null;
    }
}

package com.zqkh.healthy.context.appservice.impl.domain.repository;

import org.springframework.util.ObjectUtils;

/**
 * 方案反馈类型
 *
 * @author 东来
 * @create 2018/7/4 0004
 */
public enum  ProgramFeedbackType {

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

    public static final ProgramFeedbackType getType(String type){
        if(ObjectUtils.isEmpty(type)){
            return null;
        }

        for (ProgramFeedbackType t: ProgramFeedbackType.values()) {
            if(type.equals(t.name())){
                return t;
            }
        }
        return null;
    }
}

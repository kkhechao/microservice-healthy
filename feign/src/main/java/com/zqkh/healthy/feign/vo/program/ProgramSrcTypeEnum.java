package com.zqkh.healthy.feign.vo.program;

import org.springframework.util.ObjectUtils;

/**
 * 方案来源类型
 * @author 东来
 * @create 2018/6/15 0015
 */
public enum ProgramSrcTypeEnum {

    /**
     * 测试结果
     */
    TEST_PAPER_RESULT
    ;

    public static final ProgramSrcTypeEnum getSrcType(String srcType){
        if(ObjectUtils.isEmpty(srcType)){
            return null;
        }

        for (ProgramSrcTypeEnum src:ProgramSrcTypeEnum.values()) {
            if(srcType.equals(src.name())){
                return src;
            }
        }

        return null;
    }

}

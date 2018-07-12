package com.zqkh.healthy.feign.dto.paper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 试题选项
 *
 * @author 东来
 * @create 2018/5/7 0007
 */
@NoArgsConstructor
@Getter
@Setter
public class TestQuestionOptionDto implements Serializable {

    /**
     * 选项名
     */
    private String name;

    /**
     * 选项值
     */
    private String value;

    /**
     * 跳转起始值
     */
    private Integer startIndex;

    /**
     * 跳转结束值
     */
    private Integer endIndex;

    /**
     * 选项分数
     */
    private List<TestQuestionOptionFractionDto> fraction;




}

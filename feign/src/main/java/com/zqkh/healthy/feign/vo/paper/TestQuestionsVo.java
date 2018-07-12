package com.zqkh.healthy.feign.vo.paper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 试题VO
 *
 * @author 东来
 * @create 2018/5/7 0007
 */
@NoArgsConstructor
@Getter
@Setter
public class TestQuestionsVo implements Serializable {

    /**
     * 第几题
     */
    private Integer index;

    /**
     * 标题
     */
    private String title;

    /**
     * 是否必填
     */
    private Boolean required;

    /**
     * 试题类型
     */
    private TestQuestionsTypeEnum type;

    /**
     * 指标名
     */
    private List<String> point;

    /**
     * 备注
     */
    private String remark;

    /**
     * 试题选项
     */
    private List<TestQuestionOptionVo> option;

}

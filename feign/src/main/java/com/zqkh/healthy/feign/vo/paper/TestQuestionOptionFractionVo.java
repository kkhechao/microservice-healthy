package com.zqkh.healthy.feign.vo.paper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 选项分数VO
 *
 * @author 东来
 * @create 2018/5/7 0007
 */
@NoArgsConstructor
@Getter
@Setter
public class TestQuestionOptionFractionVo implements Serializable {

    /**
     * 分数
     */
    private Integer fraction;

    /**
     * 指标名称
     */
    private String point;

}

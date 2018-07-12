package com.zqkh.healthy.context.appservice.impl.domain.repository;

import com.jovezhao.nest.ddd.ValueObject;
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
public class TestQuestionOptionFraction extends ValueObject {

    /**
     * 分数
     */
    private Integer fraction;

    /**
     * 指标名称
     */
    private String point;

}

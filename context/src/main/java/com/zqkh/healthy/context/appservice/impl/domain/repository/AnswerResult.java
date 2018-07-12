package com.zqkh.healthy.context.appservice.impl.domain.repository;

import com.jovezhao.nest.ddd.ValueObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 答题结果
 *
 * @author 东来
 * @create 2018/5/10 0010
 */
@NoArgsConstructor
@Getter
@Setter
public class AnswerResult extends ValueObject {

    /**
     * 试题编号
     */
    private int questionId;

    /**
     * 答案
     */
    private String answer;

    /**
     * 上一题试题编号
     */
    private Integer previousIndex;

}

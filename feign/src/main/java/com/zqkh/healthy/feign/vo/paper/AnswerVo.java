package com.zqkh.healthy.feign.vo.paper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 试卷答案
 *
 * @author 东来
 * @create 2018/5/14 0014
 */
@Getter
@Setter
@NoArgsConstructor
public class AnswerVo  implements Serializable{

    /**
     * 试题编号
     */
    private Integer index;

    /**
     * 答案
     */
    private String answer;

    /**
     * 上一题
     */
    private Integer previousIndex;

}

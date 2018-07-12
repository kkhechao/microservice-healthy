package com.zqkh.healthy.feign.vo.paper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 评分标准
 *
 * @author 东来
 * @create 2018/5/7 0007
 */
@NoArgsConstructor
@Getter
@Setter
public class GradingStandardVo  implements Serializable {

    /**
     * 标题
     */
    private String title;

    /**
     * 封面图编号
     */
    private List<String> coverId;

    /**
     * 内容
     */
    private String content;

    /**
     * 分数范围
     */
    private String fractionalRange;

    /**
     * 方案模板
     */
    private String programTemplate;


}

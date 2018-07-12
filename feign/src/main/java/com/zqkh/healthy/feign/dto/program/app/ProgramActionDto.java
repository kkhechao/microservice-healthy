package com.zqkh.healthy.feign.dto.program.app;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 东来
 * @create 2018/5/15 0015
 */
@NoArgsConstructor
@Getter
@Setter
public class ProgramActionDto implements Serializable {

    /**
     * 答题指标编号
     */
    private String id;

    /**
     * 指标名称
     */
    private String pointName;

    /**
     * 试卷标题
     */
    private String title;

    /**
     * 图片编号
     */
    private String coverId;

    /**
     * 方案内容
     */
    private String actionContent;

    /**
     * 结果
     */
    private String result;


}

package com.zqkh.healthy.context.appservice.impl.domain.repository;

import com.jovezhao.nest.ddd.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 答题指标
 *
 * @author 东来
 * @create 2018/5/10 0010
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestResultPoint extends ValueObject {

    /**
     * 指标名称
     */
    private String name;

    /**
     * 背景图编号
     */
    private String coverId;

    /**
     * 结果内容
     */
    private String content;

    /**
     * 方案内容
     */
    private String actionContent;




}

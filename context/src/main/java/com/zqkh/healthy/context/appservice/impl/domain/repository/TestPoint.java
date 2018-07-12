package com.zqkh.healthy.context.appservice.impl.domain.repository;

import com.jovezhao.nest.ddd.ValueObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 试题/题集 指标
 *
 * @author 东来
 * @create 2018/5/7 0007
 */
@NoArgsConstructor
@Getter
@Setter
public class TestPoint extends ValueObject {

    /**
     * 指标名称
     */
    private String name;

    /**
     * 评分标准
     */
    private List<GradingStandard> standard;





}

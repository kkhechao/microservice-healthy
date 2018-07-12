package com.zqkh.healthy.feign.dto.paper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
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
public class TestPointDto implements Serializable {

    /**
     * 指标名称
     */
    private String name;

    /**
     * 评分标准
     */
    private List<GradingStandardDto> standard;





}

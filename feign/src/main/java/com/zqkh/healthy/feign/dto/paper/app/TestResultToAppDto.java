package com.zqkh.healthy.feign.dto.paper.app;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 测试结果单
 *
 * @author 东来
 * @create 2018/5/11 0011
 */
@NoArgsConstructor
@Getter
@Setter
public class TestResultToAppDto {

    /**
     * 试卷编号
     */
    private String id;

    /**
     * 试卷标题
     */
    private String title;

    /**
     * 答题结果编号
     */
    private String testResultId;

    /**
     * 指标测试结果
     */
    private List<TestResultPointToAppDto> point;

}

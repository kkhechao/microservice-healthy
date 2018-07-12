package com.zqkh.healthy.feign.dto.paper.app;

import com.zqkh.healthy.feign.dto.paper.TestQuestionOptionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 试题详情DTO
 *
 * @author 东来
 * @create 2018/5/10 0010
 */
@NoArgsConstructor
@Getter
@Setter
public class TestQuestionsToAppDto implements Serializable {

    /**
     * 第几题
     */
    private Integer index;

    /**
     * 标题
     */
    private String title;

    /**
     * 是否必填
     */
    private Boolean required=false;

    /**
     * 选项
     */
    private List<TestQuestionOptionDto> option;


}

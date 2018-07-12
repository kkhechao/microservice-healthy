package com.zqkh.healthy.feign.dto.paper;

import com.zqkh.healthy.feign.dto.paper.type.TestPaperTypeInfoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 试卷详情DTO
 *
 * @author 东来
 * @create 2018/5/8 0008
 */
@NoArgsConstructor
@Getter
@Setter
public class TestPaperInfoDto implements Serializable {


    private String id;

    private String title;

    private TestPaperTypeInfoDto type;

    private Boolean release=false;

    /**
     * 封面图编号
     */
    private String coverId;

    /**
     * 备注
     */
    private String remark;


    /**
     * 试题
     */
    private List<TestQuestionsDto> questions;

    /**
     * 指标
     */
    private List<TestPointDto> point;


}

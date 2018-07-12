package com.zqkh.healthy.feign.dto.paper.app;

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
@Getter
@Setter
@NoArgsConstructor
public class TestPaperInfoToAppDto implements Serializable {

    /**
     * 试题编号
     */
    private String id;

    /**
     * 试题标题
     */
    private String title;

    /**
     * 备注
     */
    private String remark;


    /**
     * 试题
     */
    private List<TestQuestionsToAppDto> question;

    /**
     * 用户答题结果
     */
    private List<FamilyMemberTestResultDto> testResult;

    /**
     * 答题结果单编号
     */
    private String testResultId;

   

}

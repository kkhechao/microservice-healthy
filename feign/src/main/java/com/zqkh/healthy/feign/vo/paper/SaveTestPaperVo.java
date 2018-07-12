package com.zqkh.healthy.feign.vo.paper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 保存试卷/题集 VO
 *
 * @author 东来
 * @create 2018/5/7 0007
 */
@NoArgsConstructor
@Getter
@Setter
public class SaveTestPaperVo implements Serializable {


    /**
     * 试卷编号
     */
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 试卷类型编号
     */
    private String testPaperTypeId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 试卷封面图编号
     */
    private String coverId;

    /**
     * 指标
     */
    private List<TestPointVo> point;

    /**
     * 试卷
     */
    private List<TestQuestionsVo> questions;

    /**
     * 操作者
     */
    private String userId;



}

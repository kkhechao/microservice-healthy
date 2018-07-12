package com.zqkh.healthy.feign.vo.paper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 提交试卷答案
 *
 * @author 东来
 * @create 2018/5/14 0014
 */
@NoArgsConstructor
@Getter
@Setter
public class SubmitAnAnswerVo implements Serializable {

    /**
     * 试卷编号
     */
    private String id;


    /**
     * 家庭成员编号
     */
    private String familyMemberId;


    /**
     * 用户编号
     */
    private String userId;


    /**
     * 提交的答案
     */
    private List<AnswerVo> answer;



}

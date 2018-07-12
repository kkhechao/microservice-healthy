package com.zqkh.healthy.context.appservice.impl.domain.repository;

import com.jovezhao.nest.ddd.EntityObject;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 测试结果
 *
 * @author 东来
 * @create 2018/5/10 0010
 */
@NoArgsConstructor
@Getter
public class TestResult extends EntityObject {

    /**
     * 家庭成员编号
     */
    private String familyMemberId;

    /**
     * 题集编号
     */
    private String testPaperId;

    /**
     * 用户反馈
     */
    private String  feedback;

    /**
     * 答题结果
     */
    private List<AnswerResult> answer;

    /**
     * 答题指标
     */
    private List<TestResultPoint> point;


    private Date createTime;

    private Date updateTime;

    private String userId;


    /**
     * 测题冗余
     */
    private TestPaper testPaper;


    /**
     * 初始化
     * @param familyMemberId
     * @param testPaperId
     * @param feedback
     * @param answer
     * @param point
     */
    public void init(String userId,String familyMemberId,String testPaperId,String feedback,List<AnswerResult> answer,List<TestResultPoint> point,TestPaper testPaper){
        this.userId=userId;
        this.familyMemberId=familyMemberId;
        this.testPaperId=testPaperId;
        this.feedback=feedback;
        this.answer=answer;
        this.point=point;
        this.testPaper=testPaper;
        this.createTime=new Date();
        this.updateTime=new Date();
    }

    /**
     * 修改
     * @param feedback
     * @param answer
     * @param point
     * @param testPaper
     */
    public void edit(String feedback,List<AnswerResult> answer,List<TestResultPoint> point,TestPaper testPaper){
        this.feedback=feedback;
        this.answer=answer;
        this.point=point;
        this.testPaper=testPaper;
        this.updateTime=new Date();
    }


}

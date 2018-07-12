package com.zqkh.healthy.context.appservice.impl.domain.repository;

import com.jovezhao.nest.ddd.EntityObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 试卷实体
 * @author 东来
 * @create 2018/5/7 0007
 */
@NoArgsConstructor
@Getter
public class TestPaper extends EntityObject {


    /**
     * 标题
     */
    private String title;


    /**
     * 备注
     */
    private String remark;

    /**
     * 试卷类型编号
     */
    private String testPaperType;

    /**
     * 试卷封面图编号
     */
    private String coverId;

    /**
     * 试卷指标
     */
    private List<TestPoint>  point;

    /**
     * 试卷
     */
    private List<TestQuestions> question;

    private String createId;

    private String updateId;

    private Date createTime;

    private Date updateTime;

    /**
     * 是否发布
     */
    private Boolean release=false;


    /**
     * 初始化
     * @param title
     * @param testPaperType
     * @param remark
     * @param coverId
     * @param point
     * @param question
     * @param userId
     */
    public void init(String title,String testPaperType,String remark,String coverId, List<TestPoint>  point,List<TestQuestions> question,String userId){
        this.title=title;
        this.testPaperType=testPaperType;
        this.remark=remark;
        this.coverId=coverId;
        this.point=point;
        this.question=question;
        this.createId=userId;
        this.updateId=userId;
        this.createTime=new Date();
        this.updateTime=new Date();
    }

    /**
     * 修改
     * @param title
     * @param testPaperType
     * @param remark
     * @param coverId
     * @param point
     * @param question
     * @param userId
     */
    public void edit(String title,String testPaperType,String remark,String coverId, List<TestPoint>  point,List<TestQuestions> question,String userId){
        this.title=title;
        this.testPaperType=testPaperType;
        this.remark=remark;
        this.coverId=coverId;
        this.point=point;
        this.question=question;
        this.updateId=userId;
        this.updateTime=new Date();
    }

    /**
     * 添加试题
     * @param testQuestions
     */
    public void addQuestion(TestQuestions testQuestions){
        if(ObjectUtils.isEmpty(testQuestions)){
            return;
        }
        if(ObjectUtils.isEmpty( this.question)){
           this.question=new ArrayList<>();
        }
        Iterator<TestQuestions> iterator = this.question.iterator();
        while (iterator.hasNext()){
            TestQuestions testQuestions1=iterator.next();
            if(testQuestions1.getIndex().equals(testQuestions.getIndex())){
                iterator.remove();
            }
        }
        this.question.add(testQuestions);
    }

    /**
     * 添加指标
     * @param point
     */
    public void addPoint(TestPoint point){
        if(ObjectUtils.isEmpty(point)){
            return;
        }
        if(ObjectUtils.isEmpty(this.point)){
            this.point=new ArrayList<>();
        }

        Iterator<TestPoint> iterator = this.point.iterator();
        while (iterator.hasNext()){
            TestPoint testPoint=iterator.next();
            if(testPoint.getName().equals(point.getName())){
                iterator.remove();
            }
        }
        this.point.add(point);

    }

    /**
     * 发布
     */
    public void release(String userId){
        this.release=true;
        this.updateId=userId;
        this.updateTime=new Date();
    }

    /**
     * 取消发布
     */
    public void unRelease(String userId){
        this.release=false;
        this.updateId=userId;
        this.updateTime=new Date();
    }





}

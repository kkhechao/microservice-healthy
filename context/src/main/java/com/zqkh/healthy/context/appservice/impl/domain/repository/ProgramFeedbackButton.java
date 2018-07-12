package com.zqkh.healthy.context.appservice.impl.domain.repository;

import com.jovezhao.nest.ddd.EntityObject;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 方案反馈按钮
 *
 * @author 东来
 * @create 2018/7/4 0004
 */
@NoArgsConstructor
@Getter
public class ProgramFeedbackButton extends EntityObject {


    /**
     * 反馈按钮名称
     */
    private String name;

    /**
     * 反馈信息
     */
    private List<String> feedback;


    private Date createTime;

    private Date updateTime;

    /**
     * 创建用户
     */
    private String createId;

    /**
     * 修改者
     */
    private String updateId;

    /**
     * 按钮类型
     */
    private ProgramFeedbackType type;

    /**
     * 副标题
     */
    private String title;


    /**
     * 初始化
     * @param name
     * @param feedback
     * @param type
     * @param userId
     */
    public void init(String name,List<String> feedback,ProgramFeedbackType type,String title,String userId){
        this.name=name;
        this.feedback=feedback;
        this.type=type;
        this.title=title;
        this.createTime=new Date();
        this.updateTime=new Date();
        this.createId=userId;
        this.updateId=userId;
    }

    /**
     * 修改
     * @param name
     * @param feedback
     * @param type
     * @param userId
     */
    public void edit(String name,List<String> feedback,ProgramFeedbackType type,String title,String userId){
        this.name=name;
        this.feedback=feedback;
        this.type=type;
        this.title=title;
        this.updateTime=new Date();
        this.updateId=userId;
    }
}

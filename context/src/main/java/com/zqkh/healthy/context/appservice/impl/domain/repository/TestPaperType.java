package com.zqkh.healthy.context.appservice.impl.domain.repository;

import com.jovezhao.nest.ddd.EntityObject;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 试题类型
 *
 * @author 东来
 * @create 2018/5/28 0028
 */
@NoArgsConstructor
@Getter
public class TestPaperType extends EntityObject {

    /**
     * 类型名称
     */
    private String name;

    /**
     * 排序
     */
    private int seq=0;

    /**
     * 类型描述
     */
    private String desc;

    /**
     * 测题前端显示模板
     */
    private String templateId;

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
     * 初始化
     * @param name
     * @param userId
     */
    public void init(String name,String desc,String userId,String templateId){
        this.name=name;
        this.desc=desc;
        this.templateId=templateId;
        this.createTime=new Date();
        this.updateTime=new Date();
        this.createId=userId;
        this.updateId=userId;
    }

    /**
     * 编辑
     * @param name
     * @param userId
     */
    public void edit(String name,String desc,String userId,String templateId){
        this.name=name;
        this.desc=desc;
        this.templateId=templateId;
        this.updateTime=new Date();
        this.updateId=userId;
    }


    /**
     * 升序
     */
    public void asc(String userId){
        this.seq=seq-1;
        this.updateTime=new Date();
        this.updateId=userId;
    }

    /**
     * 降序
     */
    public void descend(String userId){
        this.seq=seq+1;
        this.updateTime=new Date();
        this.updateId=userId;
    }



}

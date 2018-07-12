package com.zqkh.healthy.context.appservice.impl.domain.repository;

import com.jovezhao.nest.ddd.EntityObject;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 方案任务
 *
 * @author 东来
 * @create 2018/6/5 0005
 */
@NoArgsConstructor
@Getter
public class ProgramTask  extends EntityObject {

    /**
     * 任务投放日期
     */
    private Date time;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务描述
     */
    private String desc;

    /**
     * 所属成员
     */
    private String familyMember;

    /**
     * 是否完成
     */
    private Boolean done=false;

    /**
     * 所属方案
     */
    private String program;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 初始化
     * @param time
     * @param name
     * @param desc
     * @param familyMember
     * @param program
     */
    public void init(Date time,String name,String desc,String familyMember,String program){
        this.time=time;
        this.name=name;
        this.desc=desc;
        this.familyMember=familyMember;
        this.program=program;
        this.createTime=new Date();
    }

    /**
     * 完成任务
     */
    public void doneTask(boolean done){
        this.done=done;
    }


}

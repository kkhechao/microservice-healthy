package com.zqkh.healthy.context.appservice.impl.domain.repository;

import com.jovezhao.nest.ddd.EntityObject;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 方案模板
 *
 * @author 东来
 * @create 2018/6/4 0004
 */
@NoArgsConstructor
@Getter
public class ProgramTemplate extends EntityObject {

    /**
     * 方案名称
     */
    private String name;

    /**
     * 方案提醒
     */
    private String remind;

    /**
     * 分享图
     */
    private List<String> sharingImg;

    /**
     * 任务
     */
    private List<ProgramTaskTemplate> task;

    /**
     *是否开启
     */
    private Boolean enable=false;

    /**
     * 创建者
     */
    private String createUser;

    /**
     * 修改者
     */
    private String editUser;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    /**
     * 初始化
     * @param name
     * @param remind
     * @param sharingImg
     * @param task
     * @param user
     */
    public void init(String name,String remind,List<String> sharingImg,List<ProgramTaskTemplate> task,String user){
        this.name=name;
        this.remind=remind;
        this.sharingImg=sharingImg;
        this.task=task;
        this.createUser=user;
        this.editUser=user;
        this.createTime=new Date();
        this.updateTime=new Date();
    }

    /**
     * 编辑
     * @param name
     * @param remind
     * @param sharingImg
     * @param task
     * @param user
     */
    public void edit(String name,String remind,List<String> sharingImg,List<ProgramTaskTemplate> task,String user){
        this.name=name;
        this.remind=remind;
        this.sharingImg=sharingImg;
        this.task=task;
        this.editUser=user;
        this.updateTime=new Date();
    }


    /**
     * 修改开启状态
     * @param enable
     */
    public void editEnable(boolean enable){
        this.enable=enable;
    }


}

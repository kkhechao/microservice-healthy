package com.zqkh.healthy.context.appservice.impl.domain.repository;

import com.jovezhao.nest.ddd.EntityObject;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 测题类型前端显示模板
 *
 * @author 东来
 * @create 2018/6/29 0029
 */
@NoArgsConstructor
@Getter
public class TestPaperTypeTemplate extends EntityObject {

    private String name;

    private Date createTime;

    private Date updateTime;

    private String createId;

    private String updateId;

    public void init(String name,String userId){
        this.name=name;
        this.createTime=new Date();
        this.updateTime=new Date();
        this.createId=userId;
        this.updateId=userId;
    }

    public void edit(String name,String userId){
        this.name=name;
        this.updateTime=new Date();
        this.updateId=userId;
    }


}

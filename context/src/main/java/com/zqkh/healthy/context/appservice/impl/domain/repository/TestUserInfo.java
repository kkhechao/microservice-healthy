package com.zqkh.healthy.context.appservice.impl.domain.repository;

import com.jovezhao.nest.ddd.EntityObject;
import lombok.Getter;
import lombok.NoArgsConstructor;

/***
 * @Author chao
 * @Description
 * @Date 2018/7/11 0011 下午 17:37
 * @Param
 **/
@NoArgsConstructor
@Getter
public class TestUserInfo extends EntityObject {

    private String name;

    private String json;

    private Integer age;

    /**
     * 初始化
     * @param name
     * @param json
     * @param age
     */
    public void init(String name,String json,Integer age){
        this.name=name;
        this.age=age;
        this.json=json;
    }

    /**
     * 修改
     * @param name
     * @param json
     * @param age
     */
    public void edit(String name,String json,Integer age){
        this.name=name;
        this.age=age;
        this.json=json;
    }
}

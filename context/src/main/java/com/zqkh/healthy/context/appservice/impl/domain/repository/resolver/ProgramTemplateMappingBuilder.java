package com.zqkh.healthy.context.appservice.impl.domain.repository.resolver;

import com.zqkh.healthy.context.appservice.impl.domain.repository.ProgramTemplate;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.ProgramTemplateDmoWithBLOBs;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.stereotype.Component;

/**
 * 方案模板映射
 *
 * @author 东来
 * @create 2018/6/4 0004
 */
@Component
public class ProgramTemplateMappingBuilder extends BeanMappingBuilder {


    @Override
    protected void configure() {
        mapping(type(ProgramTemplate.class).accessible(true), ProgramTemplateDmoWithBLOBs.class)
                .fields("name","name")
                .fields("remind","remind")
                .fields("enable","enable")
                .fields("enable","enable")
                .fields("createUser","createUser")
                .fields("editUser","editUser")
                .fields("createTime","createTime")
                .fields("updateTime","updateTime");
    }
}

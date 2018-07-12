package com.zqkh.healthy.context.appservice.impl.domain.repository.resolver;

import com.zqkh.healthy.context.appservice.impl.domain.repository.TestPaperTypeTemplate;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.TestPaperTypeTemplateDmo;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.stereotype.Component;

/**
 * 测题类型前端显示模板映射
 *
 * @author 东来
 * @create 2018/6/29 0029
 */
@Component
public class TestPaperTypeTemplateBeanMappingBuilder extends BeanMappingBuilder {
    @Override
    protected void configure() {
       mapping(type(TestPaperTypeTemplate.class).accessible(true), TestPaperTypeTemplateDmo.class)
               .fields("name","name")
               .fields("createId","createId")
               .fields("updateId","updateId")
               .fields("createTime","createTime")
               .fields("updateTime","updateTime")
               ;
    }
}

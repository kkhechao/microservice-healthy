package com.zqkh.healthy.context.appservice.impl.domain.repository.resolver;

import com.zqkh.healthy.context.appservice.impl.domain.repository.TestPaperType;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.TestPaperTypeDmo;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.stereotype.Component;

/**
 * @author 东来
 * @create 2018/5/28 0028
 */
@Component
public class TestPaperTypeBeanMappingBuilder  extends BeanMappingBuilder {
    @Override
    protected void configure() {
        mapping(type(TestPaperType.class).accessible(true), TestPaperTypeDmo.class)
                .fields("name","name")
                .fields("seq","seq")
                .fields("desc","desc")
                .fields("createTime","createTime")
                .fields("updateTime","updateTime")
                .fields("updateId","updateId")
                .fields("createId","createId")
                .fields("templateId","templateId")
                ;
    }
}

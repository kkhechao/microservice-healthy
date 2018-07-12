package com.zqkh.healthy.context.appservice.impl.domain.repository.resolver;

import com.zqkh.healthy.context.appservice.impl.domain.repository.TestPaper;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.TestPaperDmoWithBLOBs;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.stereotype.Component;

/**
 * @author 东来
 * @create 2018/5/7 0007
 */
@Component
public class TestPaperBeanMappingBuilder extends BeanMappingBuilder {
    @Override
    protected void configure() {
        mapping(type(TestPaper.class).accessible(true), TestPaperDmoWithBLOBs.class)
                .fields("title","title")
                .fields("remark","remark")
                .fields("testPaperType","type")
                .fields("coverId","coverId")
                .fields("createId","createId")
                .fields("updateId","updateId")
                .fields("createTime","createTime")
                .fields("updateTime","updateTime")
                .fields("release","release");
    }
}

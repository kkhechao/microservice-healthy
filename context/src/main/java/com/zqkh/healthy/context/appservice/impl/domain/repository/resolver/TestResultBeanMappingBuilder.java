package com.zqkh.healthy.context.appservice.impl.domain.repository.resolver;

import com.zqkh.healthy.context.appservice.impl.domain.repository.TestResult;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.TestResultDmoWithBLOBs;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.stereotype.Component;

/**
 * @author 东来
 * @create 2018/5/10 0010
 */
@Component
public class TestResultBeanMappingBuilder extends BeanMappingBuilder {
    @Override
    protected void configure() {
        mapping(type(TestResult.class).accessible(true), TestResultDmoWithBLOBs.class)
                .fields("familyMemberId","familyMemberId")
                .fields("testPaperId","fkTestPaperId")
                .fields("feedback","feedback")
                .fields("createTime","createTime")
                .fields("updateTime","updateTime")
                .fields("userId","userId");
    }
}

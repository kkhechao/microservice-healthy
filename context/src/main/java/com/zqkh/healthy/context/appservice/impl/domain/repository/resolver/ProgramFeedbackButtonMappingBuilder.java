package com.zqkh.healthy.context.appservice.impl.domain.repository.resolver;

import com.zqkh.healthy.context.appservice.impl.domain.repository.ProgramFeedbackButton;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.ProgramFeedbackButtonDmo;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.stereotype.Component;

/**
 * 方案映射
 *
 * @author 东来
 * @create 2018/6/4 0004
 */
@Component
public class ProgramFeedbackButtonMappingBuilder extends BeanMappingBuilder {


    @Override
    protected void configure() {
        mapping(type(ProgramFeedbackButton.class).accessible(true), ProgramFeedbackButtonDmo.class)
                .fields("name", "name")
                .fields("createTime", "createTime")
                .fields("updateTime", "updateTime")
                .fields("createId", "createId")
                .fields("updateId", "updateId")
                .fields("type","type")
                .fields("title","title")
                ;
    }
}

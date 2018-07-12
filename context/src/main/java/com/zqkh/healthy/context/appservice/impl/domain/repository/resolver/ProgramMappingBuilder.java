package com.zqkh.healthy.context.appservice.impl.domain.repository.resolver;

import com.zqkh.healthy.context.appservice.impl.domain.repository.Program;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.ProgramDmoWithBLOBs;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.stereotype.Component;

/**
 * 方案映射
 *
 * @author 东来
 * @create 2018/6/4 0004
 */
@Component
public class ProgramMappingBuilder extends BeanMappingBuilder {


    @Override
    protected void configure() {
        mapping(type(Program.class).accessible(true), ProgramDmoWithBLOBs.class)
                .fields("status","status")
                .fields("familyMember","familyMemberId")
                .fields("srcType","sourceType")
                .fields("receiveTime","receiveTime")
                .fields("openTime","openTime")
                .fields("endTime","endTime")
                .fields("rejectTime","rejectTime")
                .fields("src","src")
                .fields("srcDesc","srcDesc")
                .fields("sharingImg","sharingImg")
                .fields("programTemplateId","programTemplateId");
    }
}

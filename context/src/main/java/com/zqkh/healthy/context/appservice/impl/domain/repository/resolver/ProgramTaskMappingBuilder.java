package com.zqkh.healthy.context.appservice.impl.domain.repository.resolver;

import com.zqkh.healthy.context.appservice.impl.domain.repository.Program;
import com.zqkh.healthy.context.appservice.impl.domain.repository.ProgramTask;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.ProgramDmoWithBLOBs;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.ProgramTaskDmo;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.stereotype.Component;

/**
 * 方案映射
 *
 * @author 东来
 * @create 2018/6/4 0004
 */
@Component
public class ProgramTaskMappingBuilder extends BeanMappingBuilder {


    @Override
    protected void configure() {
        mapping(type(ProgramTask.class).accessible(true), ProgramTaskDmo.class)
                .fields("time","launchTime")
                .fields("name","explain")
                .fields("desc","desc")
                .fields("familyMember","familyMemberId")
                .fields("done","done")
                .fields("program","programId")
                .fields("createTime","createTime");
    }
}

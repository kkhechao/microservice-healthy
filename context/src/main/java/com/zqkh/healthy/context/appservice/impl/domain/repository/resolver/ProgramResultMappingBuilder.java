package com.zqkh.healthy.context.appservice.impl.domain.repository.resolver;

import com.zqkh.healthy.context.appservice.impl.domain.repository.ProgramResult;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.ProgramResultDmo;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.stereotype.Component;

/**
 * 方案结果映射
 *
 * @author 东来
 * @create 2018/6/8 0008
 */
@Component
public class ProgramResultMappingBuilder extends BeanMappingBuilder {
    @Override
    protected void configure() {
        mapping(type(ProgramResult.class).accessible(true),ProgramResultDmo.class)
                .fields("familyMember","familyMemberId")
                .fields("programId","programId")
                .fields("startTime","startTime")
                .fields("endTime","endTime")
                .fields("insistDay","insistDay")
                .fields("wanderNum","wanderNum")
                .fields("giveUpNum","giveUpNum")
                .fields("finishNum","finishNum")
                .fields("createTime","createTime")
                .fields("total","total")
                .fields("transcend","transcend");

    }
}

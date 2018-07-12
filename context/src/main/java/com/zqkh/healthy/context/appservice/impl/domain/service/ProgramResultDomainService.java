package com.zqkh.healthy.context.appservice.impl.domain.service;

import com.jovezhao.nest.ddd.builder.ConstructLoader;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.builder.RepositoryLoader;
import com.jovezhao.nest.ddd.identifier.IdGenerator;
import com.zqkh.common.exception.BusinessException;
import com.zqkh.healthy.context.appservice.impl.domain.repository.ProgramResult;
import com.zqkh.healthy.context.domain.program.InitProgramResultVo;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * 方案结果领域服务
 *
 * @author 东来
 * @create 2018/6/8 0008
 */
@Service
public class ProgramResultDomainService {


    private ConstructLoader<ProgramResult> programResultConstructLoader = new ConstructLoader<>(ProgramResult.class);


    public ProgramResult init(InitProgramResultVo initProgramResultVo) {
        if (ObjectUtils.isEmpty(initProgramResultVo)) {
            throw new BusinessException("初始化方案结果参数为空");
        }
        if (ObjectUtils.isEmpty(initProgramResultVo.getFamilyMember())) {
            throw new BusinessException("初始化方案结果家庭成员为空");
        }
        if (ObjectUtils.isEmpty(initProgramResultVo.getStartTime())) {
            throw new BusinessException("初始化方案结果起始时间为空");
        }
        if (ObjectUtils.isEmpty(initProgramResultVo.getEndTime())) {
            throw new BusinessException("初始化方案结果结束时间为空");
        }
        if (ObjectUtils.isEmpty(initProgramResultVo.getProgram())) {
            throw new BusinessException("初始化方案结果方案为空");
        }
        if (ObjectUtils.isEmpty(initProgramResultVo.getFinishNum())) {
            throw new BusinessException("初始化方案结果完成任务次数为空");
        }

        ProgramResult programResult = programResultConstructLoader.create(IdGenerator.getInstance().generate(ProgramResult.class));
        programResult.init(initProgramResultVo.getFamilyMember(),
                initProgramResultVo.getProgram().getId().toValue(),initProgramResultVo.getProgram(),initProgramResultVo.getStartTime(),
                initProgramResultVo.getEndTime(), initProgramResultVo.getInsistDay(),
                initProgramResultVo.getWanderNum(), initProgramResultVo.getGiveUpNum(),
                initProgramResultVo.getFinishNum(),initProgramResultVo.getTotal(),initProgramResultVo.getTranscend());
        return programResult;
    }

}

package com.zqkh.healthy.context.appservice.impl;

import com.jovezhao.nest.ddd.StringIdentifier;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.builder.RepositoryLoader;
import com.jovezhao.nest.starter.AppService;
import com.zqkh.common.exception.BusinessException;
import com.zqkh.healthy.context.appservice.impl.domain.repository.Program;
import com.zqkh.healthy.context.appservice.impl.domain.repository.ProgramResult;
import com.zqkh.healthy.context.appservice.impl.domain.repository.TestResult;
import com.zqkh.healthy.context.appservice.impl.domain.repository.query.ProgramQueryMapper;
import com.zqkh.healthy.context.appservice.inter.ProgramResultService;
import com.zqkh.healthy.feign.dto.program.app.ProgramResultInfoDto;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * 方案结果业务实现
 *
 * @author 东来
 * @create 2018/6/8 0008
 */
@AppService
public class ProgramResultServiceImpl implements ProgramResultService {

    private EntityLoader<ProgramResult> programResultEntityLoader=new RepositoryLoader<>(ProgramResult.class);

    private EntityLoader<Program> programEntityLoader=new RepositoryLoader<>(Program.class);

    private EntityLoader<TestResult> testResultEntityLoader=new RepositoryLoader<>(TestResult.class);


    @Override
    public ProgramResultInfoDto getProgramInfo(String id) {
        if(ObjectUtils.isEmpty(id)){
            throw new BusinessException("方案结果编号为空");
        }
        ProgramResult programResult = programResultEntityLoader.create(StringIdentifier.valueOf(id));
        if(ObjectUtils.isEmpty(programResult)){
            throw new BusinessException("方案结果不存在");
        }
        ProgramResultInfoDto programResultInfoDto=new ProgramResultInfoDto();
        programResultInfoDto.setId(programResult.getId().toValue());
        programResultInfoDto.setEndTime(programResult.getEndTime());
        programResultInfoDto.setStartTime(programResult.getStartTime());
        programResultInfoDto.setFinishNum(programResult.getFinishNum());
        programResultInfoDto.setGiveUpNum(programResult.getGiveUpNum());
        programResultInfoDto.setWanderNum(programResult.getWanderNum());
        programResultInfoDto.setInsistDay(programResult.getInsistDay());
        programResultInfoDto.setTotal(programResult.getTotal());
        programResultInfoDto.setTranscend(programResult.getTranscend());
        Program program=programResult.getProgram();
        if(!ObjectUtils.isEmpty(program)){
            switch (program.getSrcType()){
                case TEST_PAPER_RESULT:
                    TestResult testResult = testResultEntityLoader.create(StringIdentifier.valueOf(program.getSrc()));
                    if(!ObjectUtils.isEmpty(testResult)){
                        programResultInfoDto.setTestPaperTitle(testResult.getTestPaper().getTitle());
                        programResultInfoDto.setTestPaperId(testResult.getTestPaperId());
                    }
                    break;
                default:
                    break;
            }
        }
        return programResultInfoDto;
    }
}

package com.zqkh.healthy.context.appservice.impl.domain.service;

import com.jovezhao.nest.ddd.StringIdentifier;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.builder.RepositoryLoader;
import com.zqkh.common.exception.BusinessException;
import com.zqkh.healthy.context.appservice.impl.domain.repository.Program;
import com.zqkh.healthy.context.appservice.impl.domain.repository.TestResult;
import com.zqkh.healthy.context.appservice.impl.domain.repository.query.ProgramQueryMapper;
import com.zqkh.healthy.context.appservice.impl.domain.repository.query.ProgramResultQueryMapper;
import com.zqkh.healthy.feign.dto.paper.app.TestResultPointToAppDto;
import com.zqkh.healthy.feign.dto.paper.app.TestResultToAppDto;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 试题结果领域服务
 *
 * @author 东来
 * @create 2018/6/15 0015
 */
@Service
public class TestResultDomainService  {

    private EntityLoader<TestResult> testResultEntityLoader=new RepositoryLoader<>(TestResult.class);

    private EntityLoader<Program> programEntityLoader=new RepositoryLoader<>(Program.class);

    @Resource
    private ProgramResultQueryMapper programResultQueryMapper;

    @Resource
    private ProgramQueryMapper programQueryMapper;

    public TestResultToAppDto getTestResultToApp(String id,List<Program> programs) {
        if (ObjectUtils.isEmpty(id)) {
            throw new BusinessException("侧题结果单编号为空");
        }
        TestResult testResult = testResultEntityLoader.create(StringIdentifier.valueOf(id));
        if (ObjectUtils.isEmpty(testResult)) {
            throw new BusinessException("侧题结果单不存在");
        }
        TestResultToAppDto result = new TestResultToAppDto();
        result.setId(testResult.getTestPaperId());
        result.setTestResultId(id);
        result.setTitle(testResult.getTestPaper().getTitle());


        if (!ObjectUtils.isEmpty(testResult.getPoint())) {
            List<TestResultPointToAppDto> point = testResult.getPoint().stream().map(n -> {
                TestResultPointToAppDto testResultPointToAppDto = new TestResultPointToAppDto();
                testResultPointToAppDto.setExplain(n.getActionContent());
                testResultPointToAppDto.setCoverId(n.getCoverId());
                testResultPointToAppDto.setName(n.getName());
                testResultPointToAppDto.setResult(n.getContent());
                if(!ObjectUtils.isEmpty(programs)){
                    programs.forEach(m->{
                        if(m.getSrcType().equals(Program.SrcType.TEST_PAPER_RESULT)&&m.getSrcDesc().equals(n.getName())){
                            testResultPointToAppDto.setProgramStatus(TestResultPointToAppDto.ProgramStatus.getStatus(m.getStatus().name()));
                            testResultPointToAppDto.setProgramId(m.getId().toValue());
                            if(m.getStatus().equals(Program.Status.END)){
                                //查询方案结果编号
                                testResultPointToAppDto.setProgramResultId(programResultQueryMapper.getNewIdByProgramId(m.getId().toValue()));
                            }
                        }
                    });
                }else{
                    //查询方案编号
                    List<String> programId = programQueryMapper.getIdBySrcTypeAndSrc(Program.SrcType.TEST_PAPER_RESULT, id, n.getName(),null);
                    if(!ObjectUtils.isEmpty(programId)){
                        programId.forEach(m->{
                            Program program = programEntityLoader.create(StringIdentifier.valueOf(m));
                            if (!ObjectUtils.isEmpty(program)) {
                                testResultPointToAppDto.setProgramStatus(TestResultPointToAppDto.ProgramStatus.getStatus(program.getStatus().name()));
                                testResultPointToAppDto.setProgramId(m);
                                if(program.getStatus().equals(Program.Status.END)){
                                    testResultPointToAppDto.setProgramResultId(programResultQueryMapper.getNewIdByProgramId(m));
                                }
                            }
                        });
                    }
                }

                return testResultPointToAppDto;
            }).collect(Collectors.toList());
            result.setPoint(point);
        }
        return result;
    }
}

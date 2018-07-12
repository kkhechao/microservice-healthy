package com.zqkh.healthy.context.appservice.impl;

import com.jovezhao.nest.ddd.StringIdentifier;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.builder.RepositoryLoader;
import com.jovezhao.nest.starter.AppService;
import com.zqkh.common.exception.BusinessException;
import com.zqkh.healthy.context.appservice.impl.domain.repository.TestResult;
import com.zqkh.healthy.context.appservice.impl.domain.repository.query.TestResultQueryMapper;
import com.zqkh.healthy.context.appservice.inter.ProgramActionService;
import com.zqkh.healthy.feign.dto.program.app.ProgramActionDto;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 解决方案业务实现层
 *
 * @author 东来
 * @create 2018/5/15 0015
 */
@AppService
public class ProgramActionServiceImpl  implements ProgramActionService {

    @Resource
    private TestResultQueryMapper testResultQueryMapper;

    private EntityLoader<TestResult> testResultEntityLoader = new RepositoryLoader<>(TestResult.class);

    @Override
    public List<ProgramActionDto> selectProgramAction(String familyMemberId) {
        if (ObjectUtils.isEmpty(familyMemberId)) {
            throw new BusinessException("家庭成员编号为空");
        }

        List<String> testResultList=testResultQueryMapper.getTestResultIdByFamilyMemberId(familyMemberId);
        if(ObjectUtils.isEmpty(testResultList)){
            return null;
        }

        List<ProgramActionDto> result=new ArrayList<>();
        testResultList.forEach(n->{
            TestResult testResult=testResultEntityLoader.create(StringIdentifier.valueOf(n));
            if(!ObjectUtils.isEmpty(testResult)&&!ObjectUtils.isEmpty(testResult.getPoint())){
                testResult.getPoint().forEach(m->{
                    ProgramActionDto programActionDto=new ProgramActionDto();
                    programActionDto.setId(n);
                    programActionDto.setActionContent(m.getActionContent());
                    programActionDto.setCoverId(m.getCoverId());
                    programActionDto.setTitle(testResult.getTestPaper().getTitle());
                    programActionDto.setPointName(m.getName());
                    programActionDto.setResult(m.getContent());
                    result.add(programActionDto);
                });
            }
        });
        return result;
    }
}

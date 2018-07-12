package com.zqkh.healthy.context.appservice.impl.domain.service;

import com.jovezhao.nest.ddd.StringIdentifier;
import com.jovezhao.nest.ddd.builder.ConstructLoader;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.builder.RepositoryLoader;
import com.jovezhao.nest.ddd.identifier.IdGenerator;
import com.zqkh.common.exception.BusinessException;
import com.zqkh.healthy.context.appservice.impl.domain.repository.ProgramTask;
import com.zqkh.healthy.context.appservice.impl.domain.repository.TestResult;
import com.zqkh.healthy.context.appservice.impl.domain.repository.query.ProgramTaskQueryMapper;
import com.zqkh.healthy.context.domain.program.InitProgramTaskVo;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 方案任务领域服务
 *
 * @author 东来
 * @create 2018/6/5 0005
 */
@Service
public class ProgramTaskDomainService {

    private ConstructLoader<ProgramTask> programTaskConstructLoader = new ConstructLoader<>(ProgramTask.class);

    private EntityLoader<ProgramTask> programTaskEntityLoader=new RepositoryLoader<>(ProgramTask.class);


    @Resource
    private ProgramTaskQueryMapper programTaskQueryMapper;

    public void initProgramTask(InitProgramTaskVo initProgramTaskVo){
        if(ObjectUtils.isEmpty(initProgramTaskVo)){
            throw new BusinessException("初始化方案任务参数为空");
        }
        if(ObjectUtils.isEmpty(initProgramTaskVo.getName())){
            throw new BusinessException("初始化方案任务名称为空");
        }
        if(ObjectUtils.isEmpty(initProgramTaskVo.getName())){
            throw new BusinessException("初始化方案任务名称为空");
        }
        if(ObjectUtils.isEmpty(initProgramTaskVo.getProgram())){
            throw new BusinessException("初始化方案任务方案为空");
        }
        if(ObjectUtils.isEmpty(initProgramTaskVo.getFamilyMember())){
            throw new BusinessException("初始化方案任务家庭成员为空");
        }
        ProgramTask programTask = programTaskConstructLoader.create(IdGenerator.getInstance().generate(ProgramTask.class));
        programTask.init(initProgramTaskVo.getTime(),initProgramTaskVo.getName(),initProgramTaskVo.getDesc(),initProgramTaskVo.getFamilyMember(),initProgramTaskVo.getProgram());
    }

    /**
     * 删除
     * @param programId
     */
    public void removeByProgramId(String programId){
        if(ObjectUtils.isEmpty(programId)){
            return;
        }

        List<String>  programTaskList=programTaskQueryMapper.getIdByProgramId(programId);
        if(ObjectUtils.isEmpty(programTaskList)){
            return;
        }
        programTaskList.forEach(n->{
            ProgramTask programTask = programTaskEntityLoader.create(StringIdentifier.valueOf(n));
            if(!ObjectUtils.isEmpty(programTask)){
                programTask.delete();
            }

        });

    }
}

package com.zqkh.healthy.context.appservice.impl.domain.repository.model;

import com.jovezhao.nest.ddd.Identifier;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.repository.IRepository;
import com.zqkh.healthy.context.appservice.impl.domain.repository.ProgramTask;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.ProgramTaskDmoMapper;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.ProgramTaskDmo;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * 方案任务仓储
 *
 * @author 东来
 * @create 2018/6/7 0007
 */
@Component("ProgramTask_Repository")
public  class ProgramTaskRepository  implements IRepository<ProgramTask> {

    @Resource
    private ProgramTaskDmoMapper programTaskDmoMapper;

    @Autowired
    private DozerBeanMapper modelMapper;

    @Override
    public ProgramTask getEntityById(Identifier identifier, EntityLoader<ProgramTask> entityLoader) {

        ProgramTaskDmo programTaskDmo = programTaskDmoMapper.selectByPrimaryKey(identifier.toValue());
        if(ObjectUtils.isEmpty(programTaskDmo)){
            return null;
        }
        ProgramTask programTask = entityLoader.create(identifier);
        modelMapper.map(programTaskDmo,programTask);
        return programTask;
    }

    @Override
    public void save(ProgramTask programTask) {
        if(ObjectUtils.isEmpty(programTask)){
            return;
        }
        ProgramTaskDmo programTaskDmo = modelMapper.map(programTask, ProgramTaskDmo.class);
        if(programTaskDmoMapper.updateByPrimaryKey(programTaskDmo)==0){
            programTaskDmoMapper.insert(programTaskDmo);
        }
    }

    @Override
    public void remove(ProgramTask programTask) {
        programTaskDmoMapper.deleteByPrimaryKey(programTask.getId().toValue());
    }
}

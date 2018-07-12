package com.zqkh.healthy.context.appservice.impl.domain.repository.model;

import com.jovezhao.nest.ddd.Identifier;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.builder.EntityObjectUtils;
import com.jovezhao.nest.ddd.repository.IRepository;
import com.jovezhao.nest.utils.JsonUtils;
import com.zqkh.healthy.context.appservice.impl.domain.repository.Program;
import com.zqkh.healthy.context.appservice.impl.domain.repository.ProgramResult;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.ProgramResultDmoMapper;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.ProgramResultDmo;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * 方案结果仓储
 *
 * @author 东来
 * @create 2018/6/8 0008
 */
@Component("ProgramResult_Repository")
public class ProgramResultRepository implements IRepository<ProgramResult> {

    @Resource
    private ProgramResultDmoMapper programResultDmoMapper;

    private static final String PROGRAM="program";

    @Autowired
    private DozerBeanMapper modelMapper;

    @Override
    public ProgramResult getEntityById(Identifier identifier, EntityLoader<ProgramResult> entityLoader) {
        ProgramResultDmo programResultDmo = programResultDmoMapper.selectByPrimaryKey(identifier.toValue());
        if(ObjectUtils.isEmpty(programResultDmo)){
            return null;
        }
        ProgramResult programResult = entityLoader.create(identifier);
        modelMapper.map(programResultDmo,programResult);

        if(!ObjectUtils.isEmpty(programResultDmo.getProgram())){
            EntityObjectUtils.setValue(ProgramResult.class,programResult,PROGRAM, JsonUtils.toObj(programResultDmo.getProgram(), Program.class));
        }

        return programResult;
    }

    @Override
    public void save(ProgramResult programResult) {
        if(ObjectUtils.isEmpty(programResult)){
            return;
        }

        ProgramResultDmo map = modelMapper.map(programResult, ProgramResultDmo.class);

        if(!ObjectUtils.isEmpty(programResult.getProgram())){
            map.setProgram(JsonUtils.toJsonString(programResult.getProgram()));
        }

        if(programResultDmoMapper.updateByPrimaryKey(map)==0){
            programResultDmoMapper.insert(map);
        }

    }

    @Override
    public void remove(ProgramResult programResult) {
        programResultDmoMapper.deleteByPrimaryKey(programResult.getId().toValue());
    }
}

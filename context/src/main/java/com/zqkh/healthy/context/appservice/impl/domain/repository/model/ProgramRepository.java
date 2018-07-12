package com.zqkh.healthy.context.appservice.impl.domain.repository.model;

import com.jovezhao.nest.ddd.Identifier;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.builder.EntityObjectUtils;
import com.jovezhao.nest.ddd.repository.IRepository;
import com.jovezhao.nest.utils.JsonUtils;
import com.zqkh.healthy.context.appservice.impl.domain.repository.Program;
import com.zqkh.healthy.context.appservice.impl.domain.repository.ProgramFeedback;
import com.zqkh.healthy.context.appservice.impl.domain.repository.ProgramTemplate;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.ProgramDmoMapper;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.ProgramDmoWithBLOBs;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * 方案仓储
 *
 * @author 东来
 * @create 2018/6/5 0005
 */
@Component("Program_Repository")
public class ProgramRepository implements IRepository<Program> {

    @Resource
    private ProgramDmoMapper programDmoMapper;

    @Autowired
    private DozerBeanMapper modelMapper;

    private static final String FEEDBACK="feedback";

    private static final String PROGRAM_TEMPLATE="programTemplate";

    @Override
    public Program getEntityById(Identifier identifier, EntityLoader<Program> entityLoader) {
       ProgramDmoWithBLOBs programDmo= programDmoMapper.selectByPrimaryKey(identifier.toValue());
       if(ObjectUtils.isEmpty(programDmo)){
           return null;
       }
        Program program = entityLoader.create(identifier);
        modelMapper.map(programDmo,program);
        if(!ObjectUtils.isEmpty(programDmo.getFeedback())){
            EntityObjectUtils.setValue(Program.class,program,FEEDBACK, JsonUtils.toListObj(programDmo.getFeedback(), ProgramFeedback.class));
        }
        if(!ObjectUtils.isEmpty(programDmo.getProgramTemplate())){
            EntityObjectUtils.setValue(Program.class,program,PROGRAM_TEMPLATE, JsonUtils.toObj(programDmo.getProgramTemplate(), ProgramTemplate.class));
        }
        return program;
    }

    @Override
    public void save(Program program) {
        if(ObjectUtils.isEmpty(program)){
            return;
        }
        ProgramDmoWithBLOBs programDmo = modelMapper.map(program, ProgramDmoWithBLOBs.class);

        if(!ObjectUtils.isEmpty(program.getFeedback())){
            programDmo.setFeedback(JsonUtils.toJsonString(program.getFeedback()));
        }
        if(!ObjectUtils.isEmpty(program.getProgramTemplate())){
            programDmo.setProgramTemplate(JsonUtils.toJsonString(program.getProgramTemplate()));
        }
        if(programDmoMapper.updateByPrimaryKeyWithBLOBs(programDmo)==0){
            programDmoMapper.insert(programDmo);
        }

    }

    @Override
    public void remove(Program program) {
        programDmoMapper.deleteByPrimaryKey(program.getId().toValue());
    }
}

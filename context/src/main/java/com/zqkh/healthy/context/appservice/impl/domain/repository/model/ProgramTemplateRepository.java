package com.zqkh.healthy.context.appservice.impl.domain.repository.model;

import com.jovezhao.nest.ddd.Identifier;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.builder.EntityObjectUtils;
import com.jovezhao.nest.ddd.repository.IRepository;
import com.jovezhao.nest.utils.JsonUtils;
import com.zqkh.healthy.context.appservice.impl.domain.repository.ProgramTaskTemplate;
import com.zqkh.healthy.context.appservice.impl.domain.repository.ProgramTemplate;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.ProgramTemplateDmoMapper;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.ProgramTemplateDmoWithBLOBs;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 方案模板仓储
 *
 * @author 东来
 * @create 2018/6/4 0004
 */
@Component("ProgramTemplate_Repository")
public class ProgramTemplateRepository implements IRepository<ProgramTemplate> {

    private static final String SHARING_IMG="sharingImg";

    private static final String TASK="task";

    @Resource
    private ProgramTemplateDmoMapper programTemplateDmoMapper;

    @Autowired
    private DozerBeanMapper modelMapper;

    @Override
    public ProgramTemplate getEntityById(Identifier identifier, EntityLoader<ProgramTemplate> entityLoader) {

        ProgramTemplateDmoWithBLOBs templateDmoWithBLOBs= programTemplateDmoMapper.selectByPrimaryKey(identifier.toValue());
        if(ObjectUtils.isEmpty(templateDmoWithBLOBs)){
            return null;
        }

        ProgramTemplate programTemplate= entityLoader.create(identifier);

        modelMapper.map(templateDmoWithBLOBs,programTemplate);

        if(!ObjectUtils.isEmpty(templateDmoWithBLOBs.getSharingImg())){
            EntityObjectUtils.setValue(ProgramTemplate.class,programTemplate,SHARING_IMG, Arrays.stream(templateDmoWithBLOBs.getSharingImg().split(",")).collect(Collectors.toList()));
        }
        if(!ObjectUtils.isEmpty(templateDmoWithBLOBs.getTask())){
            EntityObjectUtils.setValue(ProgramTemplate.class,programTemplate,TASK, JsonUtils.toListObj(templateDmoWithBLOBs.getTask(), ProgramTaskTemplate.class));
        }
        return programTemplate;
    }

    @Override
    public void save(ProgramTemplate programTemplate) {
        if(ObjectUtils.isEmpty(programTemplate)){
            return;
        }

        ProgramTemplateDmoWithBLOBs programTemplateDmoWithBLOBs=modelMapper.map(programTemplate,ProgramTemplateDmoWithBLOBs.class);
        if(!ObjectUtils.isEmpty(programTemplate.getSharingImg())){

            programTemplateDmoWithBLOBs.setSharingImg(programTemplate.getSharingImg().stream().collect(Collectors.joining(",")));
        }
        if(!ObjectUtils.isEmpty(programTemplate.getTask())){
            programTemplateDmoWithBLOBs.setTask(JsonUtils.toJsonString(programTemplate.getTask()));
        }

        if(programTemplateDmoMapper.updateByPrimaryKeyWithBLOBs(programTemplateDmoWithBLOBs)==0){
            programTemplateDmoMapper.insert(programTemplateDmoWithBLOBs);
        }

    }

    @Override
    public void remove(ProgramTemplate programTemplate) {
        programTemplateDmoMapper.deleteByPrimaryKey(programTemplate.getId().toValue());
    }
}

package com.zqkh.healthy.context.appservice.impl.domain.repository.model;

import com.jovezhao.nest.ddd.Identifier;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.builder.EntityObjectUtils;
import com.jovezhao.nest.ddd.repository.IRepository;
import com.jovezhao.nest.utils.JsonUtils;
import com.zqkh.healthy.context.appservice.impl.domain.repository.ProgramFeedbackButton;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.ProgramFeedbackButtonDmoMapper;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.ProgramFeedbackButtonDmo;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * 方案反馈按钮仓储
 *
 * @author 东来
 * @create 2018/7/4 0004
 */
@Component("ProgramFeedbackButton_Repository")
public class ProgramFeedbackButtonRepository implements IRepository<ProgramFeedbackButton> {

    private static final String FEEDBACK="feedback";

    @Resource
    private ProgramFeedbackButtonDmoMapper programFeedbackButtonDmoMapper;


    @Autowired
    private DozerBeanMapper modelMapper;

    @Override
    public ProgramFeedbackButton getEntityById(Identifier identifier, EntityLoader<ProgramFeedbackButton> entityLoader) {

        ProgramFeedbackButtonDmo programFeedbackButtonDmo = programFeedbackButtonDmoMapper.selectByPrimaryKey(identifier.toValue());
        if(ObjectUtils.isEmpty(programFeedbackButtonDmo)){
            return null;
        }

        ProgramFeedbackButton programFeedbackButton = entityLoader.create(identifier);
        modelMapper.map(programFeedbackButtonDmo,programFeedbackButton);

        if(!ObjectUtils.isEmpty(programFeedbackButtonDmo.getFeedback())){
            EntityObjectUtils.setValue(ProgramFeedbackButton.class,programFeedbackButton,FEEDBACK, JsonUtils.toListObj(programFeedbackButtonDmo.getFeedback(),String.class));
        }
        return programFeedbackButton;
    }

    @Override
    public void save(ProgramFeedbackButton programFeedbackButton) {

        if(ObjectUtils.isEmpty(programFeedbackButton)){
            return;
        }


        ProgramFeedbackButtonDmo buttonDmo = modelMapper.map(programFeedbackButton, ProgramFeedbackButtonDmo.class);

        if(!ObjectUtils.isEmpty(programFeedbackButton.getFeedback())){
            buttonDmo.setFeedback(JsonUtils.toJsonString(programFeedbackButton.getFeedback()));
        }

        if(programFeedbackButtonDmoMapper.updateByPrimaryKeyWithBLOBs(buttonDmo)<=0){
            programFeedbackButtonDmoMapper.insert(buttonDmo);
        }
    }

    @Override
    public void remove(ProgramFeedbackButton programFeedbackButton) {
        if(ObjectUtils.isEmpty(programFeedbackButton)){
           return;
        }
        programFeedbackButtonDmoMapper.deleteByPrimaryKey(programFeedbackButton.getId().toValue());

    }
}

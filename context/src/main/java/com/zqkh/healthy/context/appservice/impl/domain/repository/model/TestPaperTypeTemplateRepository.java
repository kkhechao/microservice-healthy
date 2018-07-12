package com.zqkh.healthy.context.appservice.impl.domain.repository.model;

import com.jovezhao.nest.ddd.Identifier;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.repository.IRepository;
import com.zqkh.healthy.context.appservice.impl.domain.repository.TestPaperTypeTemplate;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.TestPaperTypeTemplateDmoMapper;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.TestPaperTypeTemplateDmo;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * 测题类型前端实现模板仓储
 *
 * @author 东来
 * @create 2018/6/29 0029
 */
@Component("TestPaperTypeTemplate_Repository")
public class TestPaperTypeTemplateRepository implements IRepository<TestPaperTypeTemplate> {

    @Resource
    private TestPaperTypeTemplateDmoMapper testPaperTypeTemplateDmoMapper;

    @Autowired
    private DozerBeanMapper modelMapper;

    @Override
    public TestPaperTypeTemplate getEntityById(Identifier identifier, EntityLoader<TestPaperTypeTemplate> entityLoader) {

        TestPaperTypeTemplateDmo testPaperTypeTemplateDmo = testPaperTypeTemplateDmoMapper.selectByPrimaryKey(identifier.toValue());
        if(ObjectUtils.isEmpty(testPaperTypeTemplateDmo)){
            return null;
        }
        TestPaperTypeTemplate testPaperTypeTemplate = entityLoader.create(identifier);
        modelMapper.map(testPaperTypeTemplateDmo,testPaperTypeTemplate);

        return testPaperTypeTemplate;
    }

    @Override
    public void save(TestPaperTypeTemplate testPaperTypeTemplate) {
        if(ObjectUtils.isEmpty(testPaperTypeTemplate)){
            return;
        }
        TestPaperTypeTemplateDmo testPaperTypeTemplateDmo = modelMapper.map(testPaperTypeTemplate, TestPaperTypeTemplateDmo.class);

        if(testPaperTypeTemplateDmoMapper.updateByPrimaryKey(testPaperTypeTemplateDmo)<=0){
            testPaperTypeTemplateDmoMapper.insert(testPaperTypeTemplateDmo);
        }
    }

    @Override
    public void remove(TestPaperTypeTemplate testPaperTypeTemplate) {
        if(ObjectUtils.isEmpty(testPaperTypeTemplate)){
            return;
        }
        testPaperTypeTemplateDmoMapper.deleteByPrimaryKey(testPaperTypeTemplate.getId().toValue());


    }
}

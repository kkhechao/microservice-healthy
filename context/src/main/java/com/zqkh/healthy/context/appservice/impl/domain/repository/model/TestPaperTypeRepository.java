package com.zqkh.healthy.context.appservice.impl.domain.repository.model;

import com.jovezhao.nest.ddd.Identifier;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.repository.IRepository;
import com.zqkh.healthy.context.appservice.impl.domain.repository.TestPaperType;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.TestPaperTypeDmoMapper;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.TestPaperTypeDmo;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * @author 东来
 * @create 2018/5/28 0028
 */
@Component("TestPaperType_Repository")
public class TestPaperTypeRepository implements IRepository<TestPaperType> {

    @Resource
    private TestPaperTypeDmoMapper testPaperTypeDmoMapper;

    @Autowired
    private DozerBeanMapper modelMapper;

    @Override
    public TestPaperType getEntityById(Identifier identifier, EntityLoader<TestPaperType> entityLoader) {
       TestPaperTypeDmo testPaperTypeDmo= testPaperTypeDmoMapper.selectByPrimaryKey(identifier.toValue());
       if(ObjectUtils.isEmpty(testPaperTypeDmo)){
           return null;
       }

       TestPaperType testPaperType  = entityLoader.create(identifier);

       modelMapper.map(testPaperTypeDmo,testPaperType);
        return testPaperType;
    }

    @Override
    public void save(TestPaperType testPaperType) {

        if(ObjectUtils.isEmpty(testPaperType)){
            return;
        }
        TestPaperTypeDmo testPaperTypeDmo=modelMapper.map(testPaperType,TestPaperTypeDmo.class);
        if(testPaperTypeDmoMapper.updateByPrimaryKeySelective(testPaperTypeDmo)<=0){
            testPaperTypeDmoMapper.insert(testPaperTypeDmo);
        }
    }

    @Override
    public void remove(TestPaperType testPaperType) {
        testPaperTypeDmoMapper.deleteByPrimaryKey(testPaperType.getId().toValue());
    }
}

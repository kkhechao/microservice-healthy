package com.zqkh.healthy.context.appservice.impl.domain.repository.model;

import com.jovezhao.nest.ddd.Identifier;
import com.jovezhao.nest.ddd.StringIdentifier;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.builder.EntityObjectUtils;
import com.jovezhao.nest.ddd.builder.RepositoryLoader;
import com.jovezhao.nest.ddd.repository.IRepository;
import com.jovezhao.nest.utils.JsonUtils;
import com.zqkh.healthy.context.appservice.impl.domain.repository.TestPaper;
import com.zqkh.healthy.context.appservice.impl.domain.repository.TestPaperType;
import com.zqkh.healthy.context.appservice.impl.domain.repository.TestPoint;
import com.zqkh.healthy.context.appservice.impl.domain.repository.TestQuestions;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.TestPaperDmoMapper;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.TestPaperDmoWithBLOBs;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 试题仓储
 * @author 东来
 * @create 2018/5/7 0007
 */
@Component("TestPaper_Repository")
public class TestPaperRepository  implements IRepository<TestPaper> {


    private static final String TEST_PAPER_POINT="point";

    private static final String TEST_PAPER_QUESTIONS="question";

    private static final String TEST_PAPER_TYPE="testPaperType";

    private EntityLoader<TestPaperType> testPaperTypeEntityLoader=new RepositoryLoader<>(TestPaperType.class);

    @Resource
    private TestPaperDmoMapper testPaperDmoMapper;

    @Autowired
    private DozerBeanMapper modelMapper;

    @Override
    public TestPaper getEntityById(Identifier identifier, EntityLoader<TestPaper> entityLoader) {
        TestPaperDmoWithBLOBs testPaperDmoWithBLOBs=testPaperDmoMapper.selectByPrimaryKey(identifier.toValue());
        if(ObjectUtils.isEmpty(testPaperDmoWithBLOBs)){
            return null;
        }
        TestPaper testPaper=entityLoader.create(identifier);
        modelMapper.map(testPaperDmoWithBLOBs,testPaper);

     /*   if(!ObjectUtils.isEmpty(testPaperDmoWithBLOBs.getType())){
           TestPaperType testPaperType= testPaperTypeEntityLoader.create(StringIdentifier.valueOf(testPaperDmoWithBLOBs.getType()));
           if(!ObjectUtils.isEmpty(testPaperType)){
               EntityObjectUtils.setValue(TestPaper.class,testPaper,TEST_PAPER_TYPE,testPaperType);
           }
        }*/

        if(!ObjectUtils.isEmpty(testPaperDmoWithBLOBs.getTestPoint())){
           List<TestPoint> testPointList= JsonUtils.toListObj(testPaperDmoWithBLOBs.getTestPoint(), TestPoint.class);
           if(!ObjectUtils.isEmpty(testPointList)){
               EntityObjectUtils.setValue(TestPaper.class,testPaper,TEST_PAPER_POINT,testPointList);
           }
        }
        if(!ObjectUtils.isEmpty(testPaperDmoWithBLOBs.getTestQuestions())){
            List<TestQuestions> testQuestions=JsonUtils.toListObj(testPaperDmoWithBLOBs.getTestQuestions(), TestQuestions.class);
            if(!ObjectUtils.isEmpty(testQuestions)){
                EntityObjectUtils.setValue(TestPaper.class,testPaper,TEST_PAPER_QUESTIONS,testQuestions);
            }
        }

        return testPaper;
    }

    @Override
    public void save(TestPaper testPaper) {
        if(ObjectUtils.isEmpty(testPaper)){
            return;
        }


        TestPaperDmoWithBLOBs testPaperDmoWithBLOBs=modelMapper.map(testPaper,TestPaperDmoWithBLOBs.class);
        testPaperDmoWithBLOBs.setId(testPaper.getId().toValue());
        if(!ObjectUtils.isEmpty(testPaper.getQuestion())){
            String question=JsonUtils.toJsonString(testPaper.getQuestion());
            testPaperDmoWithBLOBs.setTestQuestions(question);
        }
        if(!ObjectUtils.isEmpty(testPaper.getPoint())){
            String point=JsonUtils.toJsonString(testPaper.getPoint());
            testPaperDmoWithBLOBs.setTestPoint(point);
        }
        if(testPaperDmoMapper.updateByPrimaryKeyWithBLOBs(testPaperDmoWithBLOBs)==0){
            testPaperDmoMapper.insert(testPaperDmoWithBLOBs);
        }
    }

    @Override
    public void remove(TestPaper testPaper) {
        testPaperDmoMapper.deleteByPrimaryKey(testPaper.getId().toValue());
    }
}

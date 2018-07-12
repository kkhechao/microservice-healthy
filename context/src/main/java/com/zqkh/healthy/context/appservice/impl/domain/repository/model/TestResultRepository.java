package com.zqkh.healthy.context.appservice.impl.domain.repository.model;

import com.jovezhao.nest.ddd.Identifier;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.builder.EntityObjectUtils;
import com.jovezhao.nest.ddd.repository.IRepository;
import com.jovezhao.nest.utils.JsonUtils;
import com.zqkh.healthy.context.appservice.impl.domain.repository.AnswerResult;
import com.zqkh.healthy.context.appservice.impl.domain.repository.TestPaper;
import com.zqkh.healthy.context.appservice.impl.domain.repository.TestResult;
import com.zqkh.healthy.context.appservice.impl.domain.repository.TestResultPoint;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.TestResultDmoMapper;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.TestResultDmoWithBLOBs;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 答题测试单仓储
 *
 * @author 东来
 * @create 2018/5/10 0010
 */
@Component("TestResult_Repository")
public class TestResultRepository implements IRepository<TestResult> {

    private final static String ANSWER="answer";

    private final static String POINT="point";

    private final static String TESTPAPER="testPaper";

    @Autowired
    private DozerBeanMapper modelMapper;

    @Resource
    private TestResultDmoMapper testResultDmoMapper;


    @Override
    public TestResult getEntityById(Identifier identifier, EntityLoader<TestResult> entityLoader) {
        TestResultDmoWithBLOBs testResultDmoWithBLOBs= testResultDmoMapper.selectByPrimaryKey(identifier.toValue());
        if(ObjectUtils.isEmpty(testResultDmoWithBLOBs)){
            return null;
        }
        TestResult testResult=entityLoader.create(identifier);
        modelMapper.map(testResultDmoWithBLOBs,testResult);

        if(!ObjectUtils.isEmpty(testResultDmoWithBLOBs.getPointResult())){
            List<TestResultPoint> point=JsonUtils.toListObj(testResultDmoWithBLOBs.getPointResult(),TestResultPoint.class);
            if(!ObjectUtils.isEmpty(point)){
                EntityObjectUtils.setValue(TestResult.class,testResult,POINT,point);
            }
        }
        if(!ObjectUtils.isEmpty(testResultDmoWithBLOBs.getAnswerResult())){
            List<AnswerResult> answer=JsonUtils.toListObj(testResultDmoWithBLOBs.getAnswerResult(),AnswerResult.class);
            if(!ObjectUtils.isEmpty(answer)){
                EntityObjectUtils.setValue(TestResult.class,testResult,ANSWER,answer);
            }
        }

        if(!ObjectUtils.isEmpty(testResultDmoWithBLOBs.getTestPaper())){
            EntityObjectUtils.setValue(TestResult.class,testResult,TESTPAPER,JsonUtils.toObj(testResultDmoWithBLOBs.getTestPaper(), TestPaper.class));
        }
        return testResult;
    }

    @Override
    public void save(TestResult testResult) {
        if(ObjectUtils.isEmpty(testResult)){
            return;
        }

        TestResultDmoWithBLOBs testResultDmoWithBLOBs=modelMapper.map(testResult,TestResultDmoWithBLOBs.class);
        testResultDmoWithBLOBs.setAnswerResult(JsonUtils.toJsonString(testResult.getAnswer()));
        testResultDmoWithBLOBs.setPointResult(JsonUtils.toJsonString(testResult.getPoint()));
        testResultDmoWithBLOBs.setTestPaper(JsonUtils.toJsonString(testResult.getTestPaper()));
        if(testResultDmoMapper.updateByPrimaryKeySelective(testResultDmoWithBLOBs)<=0){
            testResultDmoMapper.insert(testResultDmoWithBLOBs);
        }
    }

    @Override
    public void remove(TestResult testResult) {
        testResultDmoMapper.deleteByPrimaryKey(testResult.getId().toValue());
    }
}

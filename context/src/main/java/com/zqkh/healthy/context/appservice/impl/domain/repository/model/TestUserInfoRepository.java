package com.zqkh.healthy.context.appservice.impl.domain.repository.model;

import com.jovezhao.nest.ddd.Identifier;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.repository.IRepository;
import com.zqkh.healthy.context.appservice.impl.domain.repository.TestUserInfo;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.ProgramUserDmoMapper;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.ProgramUserDmo;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/***
 * 用户信息仓储
 * @Author chao
 * @Description
 * @Date 2018/7/12 0012 上午 9:22
 * @Param
 **/
@Component("TestUserInfo_Repository")
public class TestUserInfoRepository implements IRepository<TestUserInfo> {
    @Resource
    private ProgramUserDmoMapper programUserDmoMapper;

    @Autowired
    private DozerBeanMapper modelMapper;

    @Override
    public TestUserInfo getEntityById(Identifier id, EntityLoader<TestUserInfo> builder) {
        ProgramUserDmo programUserDmo = programUserDmoMapper.selectByPrimaryKey(id.toValue());
        if (ObjectUtils.isEmpty(programUserDmo)) {
            return null;
        }
        TestUserInfo testUserInfo = builder.create(id);
        modelMapper.map(programUserDmo, testUserInfo);

        return testUserInfo;
    }

    @Override
    public void save(TestUserInfo testUserInfo) {
        if (ObjectUtils.isEmpty(testUserInfo)) {
            return;
        }
        ProgramUserDmo programUserDmo = modelMapper.map(testUserInfo, ProgramUserDmo.class);
        if (programUserDmoMapper.updateByPrimaryKey(programUserDmo) <= 0) {
            programUserDmoMapper.insert(programUserDmo);
    }

    }

    @Override
    public void remove(TestUserInfo testUserInfo) {
          if(ObjectUtils.isEmpty(testUserInfo)){
              return;
          }
          programUserDmoMapper.deleteByPrimaryKey(testUserInfo.getId().toValue());
    }
}

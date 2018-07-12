package com.zqkh.healthy.context.appservice.impl;

import com.jovezhao.nest.ddd.StringIdentifier;
import com.jovezhao.nest.ddd.builder.ConstructLoader;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.builder.RepositoryLoader;
import com.jovezhao.nest.ddd.identifier.IdGenerator;
import com.jovezhao.nest.starter.AppService;
import com.jovezhao.nest.utils.JsonUtils;
import com.zqkh.common.exception.BusinessException;
import com.zqkh.healthy.context.appservice.impl.domain.repository.TestUserInfo;
import com.zqkh.healthy.context.appservice.inter.TestUserInfoService;
import com.zqkh.healthy.feign.dto.paper.app.TestUserInfoToAppDto;
import com.zqkh.healthy.feign.vo.program.template.ProgramUserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/***
 * @Author chao
 * @Description
 * @Date 2018/7/11 0011 下午 16:55
 * @Param
 **/
@AppService
@Slf4j
public class TestUserInfoServiceImpl implements TestUserInfoService {

    Logger logger=LoggerFactory.getLogger(TestUserInfoServiceImpl.class);
    private EntityLoader<TestUserInfo> programUserInfoEntityLoader=new RepositoryLoader<>(TestUserInfo.class);

    private ConstructLoader<TestUserInfo> programUserInfoConstructLoader=new ConstructLoader<>(TestUserInfo.class);

    @Autowired
    private DozerBeanMapper modelMapper;
    @Override
    public TestUserInfoToAppDto UserInfo(String id) {
        if(StringUtils.isBlank(id)){
            throw  new BusinessException("用户id不存在");
        }
           TestUserInfo testUserInfo=programUserInfoEntityLoader.create(StringIdentifier.valueOf(id));
           log.info("testUserInfo==="+JsonUtils.toJsonString(testUserInfo));
            TestUserInfoToAppDto testUserInfoToAppDto=modelMapper.map(testUserInfo,TestUserInfoToAppDto.class);

        return testUserInfoToAppDto;
    }

    @Override
    public String saveUserInfo(ProgramUserInfoVo programUserInfoVo) {
         if(org.springframework.util.ObjectUtils.isEmpty(programUserInfoVo)) {
             return "参数为null";
         }
         if(org.springframework.util.ObjectUtils.isEmpty(programUserInfoVo.getId())){
           TestUserInfo testUserInfo=programUserInfoConstructLoader.create(IdGenerator.getInstance().generate(TestUserInfo.class));
           logger.info("1111");
           testUserInfo.init(programUserInfoVo.getName(),programUserInfoVo.getJson(),programUserInfoVo.getAge());
           return "Create SUCC";
         }else{
            TestUserInfo testUserInfo1=programUserInfoEntityLoader.create(StringIdentifier.valueOf(programUserInfoVo.getId()));
            if(org.springframework.util.ObjectUtils.isEmpty(testUserInfo1)){
                throw  new BusinessException("用户id不存在");
            }
            testUserInfo1.edit(programUserInfoVo.getName(),programUserInfoVo.getJson(),programUserInfoVo.getAge());
            return "Edit SUCC";
         }
    }


}

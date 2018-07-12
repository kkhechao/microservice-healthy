package com.zqkh.healthy.context.appservice.impl.domain.repository.mappers;

import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.TestResultDmo;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.TestResultDmoExample;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.TestResultDmoWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestResultDmoMapper {
    int countByExample(TestResultDmoExample example);

    int deleteByExample(TestResultDmoExample example);

    int deleteByPrimaryKey(String id);

    int insert(TestResultDmoWithBLOBs record);

    int insertSelective(TestResultDmoWithBLOBs record);

    List<TestResultDmoWithBLOBs> selectByExampleWithBLOBs(TestResultDmoExample example);

    List<TestResultDmo> selectByExample(TestResultDmoExample example);

    TestResultDmoWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TestResultDmoWithBLOBs record, @Param("example") TestResultDmoExample example);

    int updateByExampleWithBLOBs(@Param("record") TestResultDmoWithBLOBs record, @Param("example") TestResultDmoExample example);

    int updateByExample(@Param("record") TestResultDmo record, @Param("example") TestResultDmoExample example);

    int updateByPrimaryKeySelective(TestResultDmoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TestResultDmoWithBLOBs record);

    int updateByPrimaryKey(TestResultDmo record);
}
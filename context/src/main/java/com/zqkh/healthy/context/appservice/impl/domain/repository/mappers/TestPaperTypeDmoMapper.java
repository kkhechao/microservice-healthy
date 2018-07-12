package com.zqkh.healthy.context.appservice.impl.domain.repository.mappers;

import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.TestPaperTypeDmo;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.TestPaperTypeDmoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestPaperTypeDmoMapper {
    int countByExample(TestPaperTypeDmoExample example);

    int deleteByExample(TestPaperTypeDmoExample example);

    int deleteByPrimaryKey(String id);

    int insert(TestPaperTypeDmo record);

    int insertSelective(TestPaperTypeDmo record);

    List<TestPaperTypeDmo> selectByExample(TestPaperTypeDmoExample example);

    TestPaperTypeDmo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TestPaperTypeDmo record, @Param("example") TestPaperTypeDmoExample example);

    int updateByExample(@Param("record") TestPaperTypeDmo record, @Param("example") TestPaperTypeDmoExample example);

    int updateByPrimaryKeySelective(TestPaperTypeDmo record);

    int updateByPrimaryKey(TestPaperTypeDmo record);
}
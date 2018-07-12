package com.zqkh.healthy.context.appservice.impl.domain.repository.mappers;

import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.TestPaperDmo;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.TestPaperDmoExample;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.TestPaperDmoWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestPaperDmoMapper {
    int countByExample(TestPaperDmoExample example);

    int deleteByExample(TestPaperDmoExample example);

    int deleteByPrimaryKey(String id);

    int insert(TestPaperDmoWithBLOBs record);

    int insertSelective(TestPaperDmoWithBLOBs record);

    List<TestPaperDmoWithBLOBs> selectByExampleWithBLOBs(TestPaperDmoExample example);

    List<TestPaperDmo> selectByExample(TestPaperDmoExample example);

    TestPaperDmoWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TestPaperDmoWithBLOBs record, @Param("example") TestPaperDmoExample example);

    int updateByExampleWithBLOBs(@Param("record") TestPaperDmoWithBLOBs record, @Param("example") TestPaperDmoExample example);

    int updateByExample(@Param("record") TestPaperDmo record, @Param("example") TestPaperDmoExample example);

    int updateByPrimaryKeySelective(TestPaperDmoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TestPaperDmoWithBLOBs record);

    int updateByPrimaryKey(TestPaperDmo record);
}
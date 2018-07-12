package com.zqkh.healthy.context.appservice.impl.domain.repository.mappers;

import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.ProgramTaskDmo;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.ProgramTaskDmoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProgramTaskDmoMapper {
    int countByExample(ProgramTaskDmoExample example);

    int deleteByExample(ProgramTaskDmoExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProgramTaskDmo record);

    int insertSelective(ProgramTaskDmo record);

    List<ProgramTaskDmo> selectByExample(ProgramTaskDmoExample example);

    ProgramTaskDmo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProgramTaskDmo record, @Param("example") ProgramTaskDmoExample example);

    int updateByExample(@Param("record") ProgramTaskDmo record, @Param("example") ProgramTaskDmoExample example);

    int updateByPrimaryKeySelective(ProgramTaskDmo record);

    int updateByPrimaryKey(ProgramTaskDmo record);
}
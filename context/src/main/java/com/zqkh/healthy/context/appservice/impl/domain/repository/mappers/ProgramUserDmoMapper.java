package com.zqkh.healthy.context.appservice.impl.domain.repository.mappers;

import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.ProgramUserDmo;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.ProgramUserDmoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProgramUserDmoMapper {
    int countByExample(ProgramUserDmoExample example);

    int deleteByExample(ProgramUserDmoExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProgramUserDmo record);

    int insertSelective(ProgramUserDmo record);

    List<ProgramUserDmo> selectByExampleWithBLOBs(ProgramUserDmoExample example);

    List<ProgramUserDmo> selectByExample(ProgramUserDmoExample example);

    ProgramUserDmo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProgramUserDmo record, @Param("example") ProgramUserDmoExample example);

    int updateByExampleWithBLOBs(@Param("record") ProgramUserDmo record, @Param("example") ProgramUserDmoExample example);

    int updateByExample(@Param("record") ProgramUserDmo record, @Param("example") ProgramUserDmoExample example);

    int updateByPrimaryKeySelective(ProgramUserDmo record);

    int updateByPrimaryKeyWithBLOBs(ProgramUserDmo record);

    int updateByPrimaryKey(ProgramUserDmo record);
}
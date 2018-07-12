package com.zqkh.healthy.context.appservice.impl.domain.repository.mappers;

import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.ProgramResultDmo;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.ProgramResultDmoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProgramResultDmoMapper {
    int countByExample(ProgramResultDmoExample example);

    int deleteByExample(ProgramResultDmoExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProgramResultDmo record);

    int insertSelective(ProgramResultDmo record);

    List<ProgramResultDmo> selectByExampleWithBLOBs(ProgramResultDmoExample example);

    List<ProgramResultDmo> selectByExample(ProgramResultDmoExample example);

    ProgramResultDmo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProgramResultDmo record, @Param("example") ProgramResultDmoExample example);

    int updateByExampleWithBLOBs(@Param("record") ProgramResultDmo record, @Param("example") ProgramResultDmoExample example);

    int updateByExample(@Param("record") ProgramResultDmo record, @Param("example") ProgramResultDmoExample example);

    int updateByPrimaryKeySelective(ProgramResultDmo record);

    int updateByPrimaryKeyWithBLOBs(ProgramResultDmo record);

    int updateByPrimaryKey(ProgramResultDmo record);
}
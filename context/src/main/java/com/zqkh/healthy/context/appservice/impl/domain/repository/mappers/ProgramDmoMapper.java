package com.zqkh.healthy.context.appservice.impl.domain.repository.mappers;

import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.ProgramDmo;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.ProgramDmoExample;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.ProgramDmoWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProgramDmoMapper {
    int countByExample(ProgramDmoExample example);

    int deleteByExample(ProgramDmoExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProgramDmoWithBLOBs record);

    int insertSelective(ProgramDmoWithBLOBs record);

    List<ProgramDmoWithBLOBs> selectByExampleWithBLOBs(ProgramDmoExample example);

    List<ProgramDmo> selectByExample(ProgramDmoExample example);

    ProgramDmoWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProgramDmoWithBLOBs record, @Param("example") ProgramDmoExample example);

    int updateByExampleWithBLOBs(@Param("record") ProgramDmoWithBLOBs record, @Param("example") ProgramDmoExample example);

    int updateByExample(@Param("record") ProgramDmo record, @Param("example") ProgramDmoExample example);

    int updateByPrimaryKeySelective(ProgramDmoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ProgramDmoWithBLOBs record);

    int updateByPrimaryKey(ProgramDmo record);
}
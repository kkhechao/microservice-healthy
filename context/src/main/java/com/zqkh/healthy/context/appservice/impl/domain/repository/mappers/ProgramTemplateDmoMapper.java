package com.zqkh.healthy.context.appservice.impl.domain.repository.mappers;

import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.ProgramTemplateDmo;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.ProgramTemplateDmoExample;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.ProgramTemplateDmoWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProgramTemplateDmoMapper {
    int countByExample(ProgramTemplateDmoExample example);

    int deleteByExample(ProgramTemplateDmoExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProgramTemplateDmoWithBLOBs record);

    int insertSelective(ProgramTemplateDmoWithBLOBs record);

    List<ProgramTemplateDmoWithBLOBs> selectByExampleWithBLOBs(ProgramTemplateDmoExample example);

    List<ProgramTemplateDmo> selectByExample(ProgramTemplateDmoExample example);

    ProgramTemplateDmoWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProgramTemplateDmoWithBLOBs record, @Param("example") ProgramTemplateDmoExample example);

    int updateByExampleWithBLOBs(@Param("record") ProgramTemplateDmoWithBLOBs record, @Param("example") ProgramTemplateDmoExample example);

    int updateByExample(@Param("record") ProgramTemplateDmo record, @Param("example") ProgramTemplateDmoExample example);

    int updateByPrimaryKeySelective(ProgramTemplateDmoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ProgramTemplateDmoWithBLOBs record);

    int updateByPrimaryKey(ProgramTemplateDmo record);
}
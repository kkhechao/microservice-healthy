package com.zqkh.healthy.context.appservice.impl.domain.repository.mappers;

import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.ProgramFeedbackButtonDmo;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.ProgramFeedbackButtonDmoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProgramFeedbackButtonDmoMapper {
    int countByExample(ProgramFeedbackButtonDmoExample example);

    int deleteByExample(ProgramFeedbackButtonDmoExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProgramFeedbackButtonDmo record);

    int insertSelective(ProgramFeedbackButtonDmo record);

    List<ProgramFeedbackButtonDmo> selectByExampleWithBLOBs(ProgramFeedbackButtonDmoExample example);

    List<ProgramFeedbackButtonDmo> selectByExample(ProgramFeedbackButtonDmoExample example);

    ProgramFeedbackButtonDmo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProgramFeedbackButtonDmo record, @Param("example") ProgramFeedbackButtonDmoExample example);

    int updateByExampleWithBLOBs(@Param("record") ProgramFeedbackButtonDmo record, @Param("example") ProgramFeedbackButtonDmoExample example);

    int updateByExample(@Param("record") ProgramFeedbackButtonDmo record, @Param("example") ProgramFeedbackButtonDmoExample example);

    int updateByPrimaryKeySelective(ProgramFeedbackButtonDmo record);

    int updateByPrimaryKeyWithBLOBs(ProgramFeedbackButtonDmo record);

    int updateByPrimaryKey(ProgramFeedbackButtonDmo record);
}
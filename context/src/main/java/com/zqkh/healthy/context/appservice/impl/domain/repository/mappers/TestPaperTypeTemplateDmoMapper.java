package com.zqkh.healthy.context.appservice.impl.domain.repository.mappers;

import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.TestPaperTypeTemplateDmo;
import com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo.TestPaperTypeTemplateDmoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestPaperTypeTemplateDmoMapper {
    int countByExample(TestPaperTypeTemplateDmoExample example);

    int deleteByExample(TestPaperTypeTemplateDmoExample example);

    int deleteByPrimaryKey(String id);

    int insert(TestPaperTypeTemplateDmo record);

    int insertSelective(TestPaperTypeTemplateDmo record);

    List<TestPaperTypeTemplateDmo> selectByExample(TestPaperTypeTemplateDmoExample example);

    TestPaperTypeTemplateDmo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TestPaperTypeTemplateDmo record, @Param("example") TestPaperTypeTemplateDmoExample example);

    int updateByExample(@Param("record") TestPaperTypeTemplateDmo record, @Param("example") TestPaperTypeTemplateDmoExample example);

    int updateByPrimaryKeySelective(TestPaperTypeTemplateDmo record);

    int updateByPrimaryKey(TestPaperTypeTemplateDmo record);
}
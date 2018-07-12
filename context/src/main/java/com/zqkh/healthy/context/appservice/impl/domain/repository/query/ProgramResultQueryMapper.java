package com.zqkh.healthy.context.appservice.impl.domain.repository.query;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 方案结果查询
 *
 * @author 东来
 * @create 2018/6/13 0013
 */
public interface ProgramResultQueryMapper {

    /**
     * 根据方案编号查询方案结果编号
     * @param programId
     * @return
     */
    List<String> getIdByProgramId(@Param("programId")String programId);

    /**
     * 获取最新的方案结果编号
     * @param programId
     * @return
     */
    String getNewIdByProgramId(@Param("programId")String programId);
}

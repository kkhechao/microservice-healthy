package com.zqkh.healthy.context.appservice.impl.domain.repository.query;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 东来
 * @create 2018/6/5 0005
 */
public interface ProgramTaskQueryMapper {




    /**
     * 查询编号
     * @param programId
     * @return
     */
    List<String>  getIdByProgramId(@Param("programId")String programId);

    /**
     * 查询坚持天数
     * @param programId
     * @return
     */
    Long  insistDay(@Param("programId") String programId);

    /**
     * 完成任务次数
     * @param programId:方案编号
     * @return
     */
    Long finishTaskNum(@Param("programId") String programId);



}

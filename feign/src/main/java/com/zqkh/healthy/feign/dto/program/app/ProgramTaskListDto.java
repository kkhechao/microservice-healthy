package com.zqkh.healthy.feign.dto.program.app;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 方案任务列表DTO
 *
 * @author 东来
 * @create 2018/6/6 0006
 */
@NoArgsConstructor
@Getter
@Setter
public class ProgramTaskListDto implements Serializable {


    /**
     * 编号
     */
    private String id;

    /**
     * 投放日期
     */
    private Date time;

    /**
     * 是否完成
     */
    private Boolean done;


    /**
     *任务名称
     */
    private String name;

    /**
     * 任务说明
     */
    private String desc;



}

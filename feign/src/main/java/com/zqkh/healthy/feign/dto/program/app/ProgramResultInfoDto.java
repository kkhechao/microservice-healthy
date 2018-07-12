package com.zqkh.healthy.feign.dto.program.app;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 方案结果详情Dto
 *
 * @author 东来
 * @create 2018/6/8 0008
 */
@NoArgsConstructor
@Getter
@Setter
public class ProgramResultInfoDto implements Serializable {

    /**
     * 方案结果编号
     */
    private String id;

    /**
     * 起始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 持续天数
     */
    private long insistDay;

    /**
     * 徘徊次数
     */
    private int  wanderNum;

    /**
     * 放弃次数
     */
    private int giveUpNum;

    /**
     *  完成任务次数
     */
    private long finishNum;

    /**
     * 总人数
     */
    private long total=0;

    /**
     * 超越人数
     */
    private long transcend=0;


    /**
     * 测题编号
     */
    private String testPaperId;

    /**
     * 测题标题
     */
    private String testPaperTitle;

}

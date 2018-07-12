package com.zqkh.healthy.context.domain.program;

import com.zqkh.healthy.context.appservice.impl.domain.repository.Program;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * 初始化方案结果
 *
 * @author 东来
 * @create 2018/6/8 0008
 */
@NoArgsConstructor
@Getter
@Setter
public class InitProgramResultVo {

    /**
     * 所属成员
     */
    private String familyMember;

    /**
     * 方案
     */
    private Program program;

    /**
     * 起始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     *  坚持天数
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
     * 完成任务次数
     */
    private long finishNum;

    /**
     * 总人数
     */
    private long total;

    /**
     * 超越人数
     */
    private long transcend;



}

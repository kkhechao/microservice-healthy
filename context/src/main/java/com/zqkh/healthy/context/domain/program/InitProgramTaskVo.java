package com.zqkh.healthy.context.domain.program;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 初始化方案任务
 *
 * @author 东来
 * @create 2018/6/5 0005
 */
@NoArgsConstructor
@Getter
@Setter
public class InitProgramTaskVo implements Serializable {

    /**
     * 投放日期
     */
    private Date time;

    /**
     * 名称
     */
    private String name;

    /**
     * 备注
     */
    private String desc;


    /**
     * 所属成员
     */
    private String familyMember;

    /**
     * 所属方案
     */
    private String program;

}

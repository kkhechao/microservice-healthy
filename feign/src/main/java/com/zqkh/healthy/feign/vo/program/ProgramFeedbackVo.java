package com.zqkh.healthy.feign.vo.program;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 方案反馈VO
 * @author 东来
 * @create 2018/6/6 0006
 */
@NoArgsConstructor
@Getter
@Setter
public class ProgramFeedbackVo implements Serializable {

    /**
     * 反馈用户
     */
    private String familyMemberId;

    /**
     * 方案编号
     */
    private String programId;

    /**
     * 反馈时间
     */
    private Date time;

    /**
     * 反馈类型
     */
    private ProgramFeedbackTypeEnum type;



}

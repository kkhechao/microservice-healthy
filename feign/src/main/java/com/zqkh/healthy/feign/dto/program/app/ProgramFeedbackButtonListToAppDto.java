package com.zqkh.healthy.feign.dto.program.app;

import com.zqkh.healthy.feign.vo.program.ProgramFeedbackTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 方案反馈按钮列表DTO
 *
 * @author 东来
 * @create 2018/7/4 0004
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ProgramFeedbackButtonListToAppDto implements Serializable{

    /**
     * 按钮名称
     */
    private String name;

    /**
     * 副标题
     */
    private String title;

    /**
     * 按钮类型
     */
    private ProgramFeedbackTypeEnum type;

    /**
     * 反馈内容
     */
    private String feedback;

}

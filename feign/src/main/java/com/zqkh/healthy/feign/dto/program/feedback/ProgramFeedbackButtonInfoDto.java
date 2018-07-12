package com.zqkh.healthy.feign.dto.program.feedback;

import com.zqkh.healthy.feign.vo.program.ProgramFeedbackTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 方案反馈按钮详情
 *
 * @author 东来
 * @create 2018/7/4 0004
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ProgramFeedbackButtonInfoDto implements Serializable {

    /**
     * 按钮编号
     */
    private String id;

    /**
     * 按钮名称
     */
    private String name;

    /**
     * 反馈内容
     */
    private List<String> feedback;

    /**
     * 标题
     */
    private String title;

    /**
     * 按钮类型
     */
    private ProgramFeedbackTypeEnum type;


}

package com.zqkh.healthy.feign.dto.program.feedback;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 方案反馈按钮列表DTO
 *
 * @author 东来
 * @create 2018/7/4 0004
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProgramFeedbackButtonListDto implements Serializable {

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

}

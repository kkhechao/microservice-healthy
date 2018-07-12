package com.zqkh.healthy.feign.vo.program.feedback;

import com.zqkh.healthy.feign.vo.program.ProgramFeedbackTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 保存方案反馈按钮
 *
 * @author 东来
 * @create 2018/7/4 0004
 */
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class SaveProgramFeedbackVo implements Serializable {

    private String id;

    private String name;

    private List<String> feedback;

    private ProgramFeedbackTypeEnum type;

    private String userId;

    private String title;


}

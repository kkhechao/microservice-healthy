package com.zqkh.healthy.feign.vo.program.template;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 保存方案模板
 *
 * @author 东来
 * @create 2018/6/4 0004
 */
@NoArgsConstructor
@Getter
@Setter
public class SaveProgramTemplateVo implements Serializable {

    /**
     * 方案编号
     */
    private String id;

    /**
     *方案名称
     */
    private String name;

    /**
     * 方案提醒
     */
    private String remind;

    /**
     * 分享图
     */
    private List<String> sharingImg;

    /**
     * 任务
     */
    private List<ProgramTaskVo> task;

    /**
     * 操作者
     */
    private String userId;



}

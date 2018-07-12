package com.zqkh.healthy.feign.dto.program.template;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 方案模板详情DTO
 *
 * @author 东来
 * @create 2018/6/5 0005
 */
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProgramTemplateInfoDto implements Serializable {

    /**
     * 方案编号
     */
    private String id;

    /**
     * 方案名称
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
    private List<ProgramTaskDto> task;


}

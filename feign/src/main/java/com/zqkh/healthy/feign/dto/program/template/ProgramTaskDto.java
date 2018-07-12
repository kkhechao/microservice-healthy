package com.zqkh.healthy.feign.dto.program.template;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 方案任务Dto
 *
 * @author 东来
 * @create 2018/6/4 0004
 */
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProgramTaskDto implements Serializable {

    /**
     * 任务说明
     */
    private String explain;

    /**
     * 任务备注
     */
    private String desc;

    /**
     * 天数
     */
    private List<Integer> day;


}

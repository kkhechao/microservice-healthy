package com.zqkh.healthy.feign.vo.program.template;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 方案任务VO
 *
 * @author 东来
 * @create 2018/6/4 0004
 */
@NoArgsConstructor
@Getter
@Setter
public class ProgramTaskVo  implements Serializable {

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

package com.zqkh.healthy.context.appservice.impl.domain.repository;

import com.jovezhao.nest.ddd.ValueObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 方案任务模板
 *
 * @author 东来
 * @create 2018/6/4 0004
 */
@NoArgsConstructor
@Getter
@Setter
public class ProgramTaskTemplate extends ValueObject {

    /**
     * 任务说明
     */
    private String explain;

    /**
     *任务备注
     */
    private String desc;

    /**
     * 天数
     */
    private List<Integer> day;


}

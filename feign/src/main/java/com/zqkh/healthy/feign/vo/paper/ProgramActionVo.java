package com.zqkh.healthy.feign.vo.paper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 方案动作
 *
 * @author 东来
 * @create 2018/6/1 0001
 */
@NoArgsConstructor
@Getter
@Setter
public class ProgramActionVo{


    /**
     * 动作说明
     */
    private String explain;


    /**
     * 知识库
     */
    private List<String> knowledgeBase;

    /**
     * 备注
     */
    private String desc;

    /**
     * 天数
     */
    private List<Integer> day;

}

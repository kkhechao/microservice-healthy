package com.zqkh.healthy.feign.dto.paper.app;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 试卷列表DTO
 *
 * @author 东来
 * @create 2018/5/10 0010
 */
@NoArgsConstructor
@Setter
@Getter
public class TestPaperListToAppDto implements Serializable {

    /**
     * 试题编号
     */
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 是否已完成
     */
    private Boolean done=false;

    /**
     * 封面图资源编号
     */
    private String coverId;

    /**
     * 做题结果编号
     */
    private String testResultId;


}

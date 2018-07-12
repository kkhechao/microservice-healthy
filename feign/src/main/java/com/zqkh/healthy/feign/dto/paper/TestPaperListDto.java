package com.zqkh.healthy.feign.dto.paper;

import com.zqkh.healthy.feign.dto.paper.type.TestPaperTypeInfoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 试卷列表DTO
 *
 * @author 东来
 * @create 2018/5/8 0008
 */
@NoArgsConstructor
@Getter
@Setter
public class TestPaperListDto implements Serializable {

    private String id;

    private String title;

    private Boolean release=false;

    /**
     * 试卷类型
     */
    private TestPaperTypeInfoDto type;

    private int questionNum=0;

    /**
     * 封面图资源编号
     */
    private String coverId;

    /**
     * 是否配置了方案
     */
    private Boolean program=false;






}

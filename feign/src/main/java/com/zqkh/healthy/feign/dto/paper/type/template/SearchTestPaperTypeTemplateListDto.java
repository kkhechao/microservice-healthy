package com.zqkh.healthy.feign.dto.paper.type.template;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 搜索测题类型显示模板DTO
 *
 * @author 东来
 * @create 2018/6/29 0029
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchTestPaperTypeTemplateListDto implements Serializable {

    /**
     * 模板编号
     */
    private String id;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 包含自测题类型数量
     */
    private long num=0;



}

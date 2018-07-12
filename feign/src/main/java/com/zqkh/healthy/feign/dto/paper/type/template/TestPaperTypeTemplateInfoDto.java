package com.zqkh.healthy.feign.dto.paper.type.template;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 测题类型前端显示模板详情DTO
 *
 * @author 东来
 * @create 2018/6/29 0029
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestPaperTypeTemplateInfoDto implements Serializable{


    private String id;


    private String name;


}

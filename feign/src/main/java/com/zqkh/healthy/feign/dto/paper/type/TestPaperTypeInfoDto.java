package com.zqkh.healthy.feign.dto.paper.type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 试题类型详情DTO
 *
 * @author 东来
 * @create 2018/5/28 0028
 */
@NoArgsConstructor
@Getter
@Setter
public class TestPaperTypeInfoDto implements Serializable {

    private String id;

    private String name;

    private String desc;

    private String templateName;

    private String templateId;



}

package com.zqkh.healthy.feign.dto.paper.type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 试卷类型列表DTO
 *
 * @author 东来
 * @create 2018/5/28 0028
 */
@NoArgsConstructor
@Getter
@Setter
public class TestPaperTypeListDto implements Serializable {

    private String id;

    private String name;

    private String templateName;

    private long num;

    private int seq;

    private String desc;

}

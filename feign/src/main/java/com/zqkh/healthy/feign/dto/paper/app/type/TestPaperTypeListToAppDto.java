package com.zqkh.healthy.feign.dto.paper.app.type;

import com.zqkh.healthy.feign.dto.paper.app.TestPaperListToAppDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 测题类型DTO
 *
 * @author 东来
 * @create 2018/6/7 0007
 */
@NoArgsConstructor
@Getter
@Setter
public class TestPaperTypeListToAppDto {

    /**
     * 类型编号
     */
    private String id;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 类型描述
     */
    private String desc;

    /**
     * 前端显示模板
     */
    private String template;

    /**
     * 测题
     */
    List<TestPaperListToAppDto> testPaper;

}

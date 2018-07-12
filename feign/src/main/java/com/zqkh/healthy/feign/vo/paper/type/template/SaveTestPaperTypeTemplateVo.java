package com.zqkh.healthy.feign.vo.paper.type.template;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 保存前端显示模板
 *
 * @author 东来
 * @create 2018/6/29 0029
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveTestPaperTypeTemplateVo implements Serializable {

    private String id;

    private String name;

    private String userId;

}

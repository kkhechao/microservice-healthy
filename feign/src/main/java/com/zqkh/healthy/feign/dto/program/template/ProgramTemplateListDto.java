package com.zqkh.healthy.feign.dto.program.template;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 东来
 * @create 2018/6/4 0004
 */
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProgramTemplateListDto implements Serializable{

    /**
     * 编号
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 是否启用
     */
    private Boolean enable;

    /**
     * 是否被使用
     */
    private Boolean use;


}

package com.zqkh.healthy.feign.dto.paper.app;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 家庭成员答题结果
 *
 * @author 东来
 * @create 2018/5/10 0010
 */
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FamilyMemberTestResultDto implements Serializable {

    /**
     * 第几题
     */
    private Integer index;

    /**
     * 选项名
     */
    private String optionName;

    /**
     * 上一题
     */
    private Integer previousIndex;

}

package com.zqkh.healthy.feign.dto.paper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 试卷完成情况DTO
 *
 * @author 东来
 * @create 2018/5/9 0009
 */
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestPaperCompletionDto implements Serializable {

    /**
     *
     */
    private String name;

    private boolean done;

}

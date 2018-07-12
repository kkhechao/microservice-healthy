package com.zqkh.healthy.feign.vo.program.template;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/***
 * @Author chao
 * @Description
 * @Date 2018/7/11 0011 下午 17:27
 * @Param
 **/
@NoArgsConstructor
@Getter
@Setter
public class ProgramUserInfoVo implements Serializable {
    private String name;

    private String json;

    private Integer age;

    private String id;
}

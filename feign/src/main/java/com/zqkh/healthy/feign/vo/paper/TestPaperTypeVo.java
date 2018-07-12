package com.zqkh.healthy.feign.vo.paper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 试题类型VO
 *
 * @author 东来
 * @create 2018/5/28 0028
 */
@NoArgsConstructor
@Getter
@Setter
public class TestPaperTypeVo implements Serializable {

    private String id;

    private String name;

    private String desc;

    private String userId;

    private String template;


}

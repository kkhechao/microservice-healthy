package com.zqkh.healthy.feign.vo.paper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 方案
 *
 * @author 东来
 * @create 2018/6/1 0001
 */
@NoArgsConstructor
@Getter
@Setter
public class ProgramVo implements Serializable {


    /**
     * 方案名称
     */
    private String name;

    /**
     * 方案提醒
     */
    private String remind;


    /**
     * 分享图片
     */
    private List<String> sharingImg;


    /**
     * 方案动作
     */
    private List<ProgramActionVo> action;



}

package com.zqkh.healthy.feign.dto.paper.app;

import com.zqkh.healthy.feign.dto.program.app.ProgramInfoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

/**
 * @author 东来
 * @create 2018/5/11 0011
 */
@NoArgsConstructor
@Getter
@Setter
public class TestResultPointToAppDto implements Serializable {

    /**
     * 方案状态
     */
    public enum ProgramStatus{

        /**
         * 接收
         */
        RECEIVE,

        /**
         * 拒绝接收时间
         */
        REJECT,
        /**
         * 开启
         */
        OPEN,

        /**
         * 结束
         */
        END,
        ;

        public static final ProgramStatus getStatus(String status){
            if(ObjectUtils.isEmpty(status)){
                return null;
            }
            for (ProgramStatus st: ProgramStatus.values()) {
                if(status.equals(st.name())){
                    return st;
                }
            }

            return null;
        }


    }

    /**
     * 指标名
     */
    private String name;

    /**
     * 背景图
     */
    private String coverId;

    /**
     * 指标评分结果:中高低等
     */
    private String result;

    /**
     * 结果示意
     */
    private String explain;


    /**
     * 解决方案编号
     */
    private String programId;

    /**
     * 方案状态
     */
    private ProgramStatus programStatus;

    /**
     * 方案结果编号
     */
    private String programResultId;

}

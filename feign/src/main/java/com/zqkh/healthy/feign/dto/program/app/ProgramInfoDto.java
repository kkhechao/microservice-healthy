package com.zqkh.healthy.feign.dto.program.app;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 方案详情DTO
 *
 * @author 东来
 * @create 2018/6/6 0006
 */
@Getter
@Setter
@NoArgsConstructor
public class ProgramInfoDto  implements Serializable {


    public enum Status{

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

        public static final Status getStatus(String status){
            if(ObjectUtils.isEmpty(status)){
                return null;
            }
            for (Status st:Status.values()) {
                if(status.equals(st.name())){
                    return st;
                }
            }

            return null;
        }


    }

    /**
     * 编号
     */
    private String id;

    /**
     * 试题标题
     */
    private String testPaperTitle;

    /**
     * 方案名称
     */
    private String name;

    /**
     * 总天数
     */
    private long totalDay;

    /**
     * 已坚持多少天
     */
    private long insistDay;

    /**
     * 状态
     */
    private Status status;

    /**
     * 任务
     */
    private List<ProgramTaskListDto> task;


    /**
     * 方案结果编号
     */
    private String programResultId;





}

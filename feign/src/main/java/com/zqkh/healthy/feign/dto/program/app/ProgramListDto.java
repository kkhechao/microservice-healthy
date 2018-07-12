package com.zqkh.healthy.feign.dto.program.app;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

/**
 * 解决方案列表DTO
 *
 * @author 东来
 * @create 2018/6/6 0006
 */
@NoArgsConstructor
@Getter
@Setter
public class ProgramListDto  implements Serializable{

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
     * 方案编号
     */
    private String id;

    /**
     * 方案名称
     */
    private String name;

    /**
     * 今日完成情况
     */
    private Boolean dayDone=false;

    /**
     * 方案状态
     */
    private Status status;


    /**
     * 坚持天数
     */
    private long insistDay=0;

    /**
     * 方案结果编号
     */
    private String programResultId;




}

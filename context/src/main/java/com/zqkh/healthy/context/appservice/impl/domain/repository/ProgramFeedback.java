package com.zqkh.healthy.context.appservice.impl.domain.repository;

import com.jovezhao.nest.ddd.ValueObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import java.util.Date;

/**
 * 方案反馈
 *
 * @author 东来
 * @create 2018/6/5 0005
 */
@NoArgsConstructor
@Getter
@Setter
public class ProgramFeedback extends ValueObject {


    /**
     * 反馈时间
     */
    private Date time=new Date();

    /**
     * 反馈状态
     */
    private ProgramFeedbackType tye;
}

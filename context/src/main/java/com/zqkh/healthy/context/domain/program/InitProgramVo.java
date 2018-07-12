package com.zqkh.healthy.context.domain.program;

import com.jovezhao.nest.ddd.Identifier;
import com.zqkh.healthy.context.appservice.impl.domain.repository.Program;
import com.zqkh.healthy.context.appservice.impl.domain.repository.ProgramTemplate;
import lombok.*;

import java.io.Serializable;

/**
 * 初始化方案
 *
 * @author 东来
 * @create 2018/6/5 0005
 */
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class InitProgramVo implements Serializable {


    /**
     * 方案编号,可选
     */
    private Identifier identifier;


    /**
     * 所属成员
     */
    private String familyMember;


    /**
     * 来源类型
     */
    private Program.SrcType srcType;


    /**
     * 来源
     */
    private String src;


    /**
     * 来源备注
     */
    private String srcDesc;


    /**
     * 方案模板
     */
    private ProgramTemplate programTemplate;

}

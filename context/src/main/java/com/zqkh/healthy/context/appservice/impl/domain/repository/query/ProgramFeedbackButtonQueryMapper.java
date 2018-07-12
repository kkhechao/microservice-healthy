package com.zqkh.healthy.context.appservice.impl.domain.repository.query;

import com.jovezhao.nest.PageList;
import com.jovezhao.nest.mybatis.PageParames;
import com.zqkh.healthy.feign.vo.program.ProgramFeedbackTypeEnum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 方案反馈按钮查询
 *
 * @author 东来
 * @create 2018/7/4 0004
 */
public interface ProgramFeedbackButtonQueryMapper {

    /**
     * 获取所有方案反馈按钮
     * @param type:
     * @param pageParames :
     * @return
     */
    PageList<String> search(@Param("type") ProgramFeedbackTypeEnum type, PageParames pageParames);


    /**
     * 验证按钮名称是否存在
     * @param name:按钮名称
     * @param id:需要排除的id
     * @return
     */
    Boolean validateName(@Param("name") String name, @Param("id") String id);


    /**
     * app按钮列表
     * @return
     */
    List<String> appButtonList();

}

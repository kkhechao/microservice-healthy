package com.zqkh.healthy.context.appservice.impl.domain.repository.query;

import com.jovezhao.nest.PageList;
import com.jovezhao.nest.mybatis.PageParames;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 东来
 * @create 2018/5/28 0028
 */
public interface TestPaperTypeQueryMapper{

    /**
     * 验证名字
     * @param name:名称
     * @param id:需要排除的id,可为空
     * @return true通过验证,false 未通过
     */
    boolean nameValidation(@Param("name") String name,@Param("id") String id);

    /**
     * 试卷总数
     * @param testPaperTypeId:试题类型编号
     * @return
     */
    long count(@Param("testPaperTypeId") String testPaperTypeId);

    /**
     * 试卷类型列表
     * @param pageParames
     * @return
     */
    PageList<String> list(PageParames pageParames);

    /**
     * 获取所有试卷类型
     * @return
     */
    List<String> all();

    /**
     * 根据模板编号统计
     * @param templateId:模板编号
     * @return
     */
    long countByTemplateId(@Param("templateId")String templateId);

}

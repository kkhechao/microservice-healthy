package com.zqkh.healthy.context.appservice.impl.domain.repository.query;

import com.jovezhao.nest.PageList;
import com.jovezhao.nest.mybatis.PageParames;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 测题类型前端显示模板查询
 *
 * @author 东来
 * @create 2018/6/29 0029
 */
public interface TestPaperTypeTemplateQueryMapper {

    /**
     * 搜索
     * @param pageParames
     * @return
     */
    PageList<String> search(PageParames pageParames);

    /**
     * 验证名称是否可用
     * @param name:名称
     * @param id:需要排除的编号
     * @return
     */
    boolean validationName(@Param("name")String name,@Param("id") String id);


    /**
     * 获取所有
     * @return
     */
    List<String> allList();
}

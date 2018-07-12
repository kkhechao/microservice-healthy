package com.zqkh.healthy.context.appservice.impl.domain.repository.query;

import com.jovezhao.nest.PageList;
import com.jovezhao.nest.mybatis.PageParames;
import com.zqkh.healthy.context.appservice.impl.domain.repository.TestPaperTypeEnum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 试卷查询
 *
 * @author 东来
 * @create 2018/5/8 0008
 */
public interface TestPaperQueryMapper {

    /**
     * 搜索试卷
     * @param title:试卷标题
     * @param typeId:试卷类型
     * @param pageParames:分页参数
     * @return
     */
    PageList<String> search(@Param("title") String title,@Param("release")Boolean release,@Param("type") String typeId, @Param("pageParames") PageParames pageParames);


    /**
     * 搜索包含Title的试题编号
     * @param title
     * @param release
     * @param type
     * @return
     */
    List<String> searchContainsTitle(@Param("title") String title,@Param("release")Boolean release,@Param("type") String type);

    /**
     * 获取所有试卷
     * @param type
     * @return
     */
    List<String> allTestPaperByType(@Param("type") String type,@Param("release")Boolean release);

}

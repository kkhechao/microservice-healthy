package com.zqkh.healthy.context.appservice.impl.domain.repository.query;

import com.jovezhao.nest.PageList;
import com.jovezhao.nest.mybatis.PageParames;
import org.apache.ibatis.annotations.Param;

/**
 * @author 东来
 * @create 2018/6/4 0004
 */
public interface ProgramTemplateQueryMapper {

    PageList<String> search(@Param("key") String key,@Param("enable")Boolean enable, PageParames pageParames);


}

package com.zqkh.healthy.context.appservice.impl.domain.repository.query;

import com.jovezhao.nest.PageList;
import com.jovezhao.nest.mybatis.PageParames;
import com.zqkh.healthy.context.appservice.impl.domain.repository.Program;
import com.zqkh.healthy.feign.vo.program.ProgramSrcTypeEnum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 方案查询
 *
 * @author 东来
 * @create 2018/6/4 0004
 */
public interface ProgramQueryMapper {

    /**
     * 统计
     * @param programTemplateId
     * @return
     */
    Long countByProgramTemplateId(@Param("programTemplateId") String programTemplateId);

    /**
     * 统计尚未做完人数
     * @param programTemplateId
     * @return
     */
    Long countNotEnd(@Param("programTemplateId") String programTemplateId);

    /**
     * 查询编号
     * @param srcType
     * @param src
     * @param status :
     * @return
     */
    List<String> getIdBySrcTypeAndSrc(@Param("srcType") Program.SrcType srcType,@Param("src") String src,@Param("srcDesc")String srcDesc,@Param("status")List<Program.Status> status);


    /**
     * 查询编号
     * @param familyMemberId:家庭成员编号
     * @param dayDone:今日是否完成
     * @return
     */
    List<String> getIdByDayDoneAndFamilyMemberId(@Param("familyMemberId")String familyMemberId,@Param("dayDone")boolean dayDone);

    /**
     * 搜索
     * @param key:搜索关键
     * @param familyMemberId:家庭成员
     * @param done:是否完成
     * @param ids:需要包含的id
     * @param srcType :方案来源类型
     * @param src :方案来源
     * @param pageParames
     * @return
     */
    PageList<String> search(@Param("key")String key,
                            @Param("familyMemberId")String familyMemberId,
                            @Param("done")Boolean done,
                            @Param("ids")List<String> ids,
                            @Param("srcType")Program.SrcType srcType,
                            @Param("src")String src,
                            PageParames pageParames);

}

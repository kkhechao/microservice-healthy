package com.zqkh.healthy.context.appservice.impl.domain.repository.query;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 测题结果单查询
 *
 * @author 东来
 * @create 2018/5/9 0009
 */
public interface TestResultQueryMapper {


    /**
     * 获取用户测试单编号
     * @param testPaperIdList
     * @param familyMemberId
     * @return
     */
    List<String> getTestResultIdByTestPaperIdList(@Param("testPaperIdList") List<String> testPaperIdList,@Param("familyMemberId")String familyMemberId);


    /**
     * 获取用户测试单编号
     * @param testPaperId
     * @param familyMemberId
     * @return
     */
    String getTestResultIdByTestPaperId(@Param("testPaperId") String testPaperId,@Param("familyMemberId")String familyMemberId);

    /**
     * 获取试卷编号
     * @param familyMemberId
     * @return
     */
    List<String> getTestResultIdByFamilyMemberId(@Param("familyMemberId")String familyMemberId);
}

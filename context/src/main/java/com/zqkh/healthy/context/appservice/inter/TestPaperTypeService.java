package com.zqkh.healthy.context.appservice.inter;

import com.zqkh.common.result.PageResult;
import com.zqkh.healthy.feign.dto.paper.app.type.TestPaperTypeListToAppDto;
import com.zqkh.healthy.feign.dto.paper.type.TestPaperTypeInfoDto;
import com.zqkh.healthy.feign.dto.paper.type.TestPaperTypeListDto;
import com.zqkh.healthy.feign.vo.paper.TestPaperTypeVo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 试卷类型业务接口
 *
 * @author 东来
 * @create 2018/5/28 0028
 */
public interface TestPaperTypeService {

    /**
     * 保存试卷类型
     * @param testPaperTypeVo
     */
    void save(TestPaperTypeVo testPaperTypeVo);

    /**
     * 获取详情
     * @param id
     * @return
     */
    TestPaperTypeInfoDto getInfo(String id);

    /**
     * 查看试卷类型列表
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PageResult<TestPaperTypeListDto> getTestPaperTypeListDto(Integer pageIndex, Integer pageSize);

    /**
     * 删除试卷类型
     * @param id
     */
    void del(String id);

    /**
     * 获取所有试卷类型
     * @return
     */
    List<TestPaperTypeListDto> all();

    /**
     * 获取试卷类型列表
     * @param familyMemberId:家庭成员编号
     * @return
     */
    List<TestPaperTypeListToAppDto> getTaperTypeListToApp(String familyMemberId);

    /**
     * 升序
     * @param id:类型编号
     * @param userId:操作者
     */
    void asc(String id, String userId);


    /**
     * 降序
     * @param id:类型编号
     * @param userId:操作者
     */
    void descend(String id, String userId);
}

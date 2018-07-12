package com.zqkh.healthy.context.appservice.inter;

import com.zqkh.healthy.feign.dto.program.app.ProgramResultInfoDto;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 方案结果业务接口
 *
 * @author 东来
 * @create 2018/6/8 0008
 */
public interface ProgramResultService {
    /**
     * 查询方案结果详情
     * @param id:方案结果编号
     * @return
     */
    ProgramResultInfoDto getProgramInfo(String id);


}

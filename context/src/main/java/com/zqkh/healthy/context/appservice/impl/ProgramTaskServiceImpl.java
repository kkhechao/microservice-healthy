package com.zqkh.healthy.context.appservice.impl;

import com.jovezhao.nest.ddd.StringIdentifier;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.builder.RepositoryLoader;
import com.jovezhao.nest.starter.AppService;
import com.zqkh.common.exception.BusinessException;
import com.zqkh.healthy.context.appservice.impl.domain.repository.ProgramTask;
import com.zqkh.healthy.context.appservice.inter.ProgramTaskService;
import org.springframework.util.ObjectUtils;

/**
 * 方案任务接口实现
 *
 * @author 东来
 * @create 2018/6/8 0008
 */
@AppService
public class ProgramTaskServiceImpl  implements ProgramTaskService {

    private EntityLoader<ProgramTask> programTaskEntityLoader=new RepositoryLoader<>(ProgramTask.class);


    @Override
    public void editDone(String id, String familyMemberId, boolean done) {
        if(ObjectUtils.isEmpty(id)){
            throw new BusinessException("任务编号为空");
        }
        if(ObjectUtils.isEmpty(familyMemberId)){
            throw new BusinessException("家庭成员编号为空");
        }
        ProgramTask programTask = programTaskEntityLoader.create(StringIdentifier.valueOf(id));
        if(ObjectUtils.isEmpty(programTask)){
            throw new BusinessException("任务不存在");
        }
        if(!familyMemberId.equals(programTask.getFamilyMember())){
            throw new BusinessException("不可操作他人任务");
        }
        programTask.doneTask(done);

    }
}

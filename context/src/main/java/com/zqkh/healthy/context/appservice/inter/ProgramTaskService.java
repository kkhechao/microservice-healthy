package com.zqkh.healthy.context.appservice.inter;

/**
 * 方案任务接口
 *
 * @author 东来
 * @create 2018/6/8 0008
 */
public interface ProgramTaskService {

    /**
     * 修改任务方案完成情况
     * @param id:任务编号
     * @param familyMemberId:家庭成员编号
     * @param done:是否完成
     */
    void editDone(String id, String familyMemberId, boolean done);
}

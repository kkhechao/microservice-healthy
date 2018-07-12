package com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo;

import java.util.Date;

public class TestResultDmo {
    private String id;

    private String fkTestPaperId;

    private String userId;

    private Date createTime;

    private Date updateTime;

    private String familyMemberId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFkTestPaperId() {
        return fkTestPaperId;
    }

    public void setFkTestPaperId(String fkTestPaperId) {
        this.fkTestPaperId = fkTestPaperId == null ? null : fkTestPaperId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getFamilyMemberId() {
        return familyMemberId;
    }

    public void setFamilyMemberId(String familyMemberId) {
        this.familyMemberId = familyMemberId == null ? null : familyMemberId.trim();
    }
}
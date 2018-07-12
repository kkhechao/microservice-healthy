package com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo;

import java.util.Date;

public class ProgramDmo {
    private String id;

    private String familyMemberId;

    private String status;

    private String sourceType;

    private Date receiveTime;

    private Date openTime;

    private Date endTime;

    private Date rejectTime;

    private String src;

    private String sharingImg;

    private String programTemplateId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFamilyMemberId() {
        return familyMemberId;
    }

    public void setFamilyMemberId(String familyMemberId) {
        this.familyMemberId = familyMemberId == null ? null : familyMemberId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.trim();
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getRejectTime() {
        return rejectTime;
    }

    public void setRejectTime(Date rejectTime) {
        this.rejectTime = rejectTime;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src == null ? null : src.trim();
    }

    public String getSharingImg() {
        return sharingImg;
    }

    public void setSharingImg(String sharingImg) {
        this.sharingImg = sharingImg == null ? null : sharingImg.trim();
    }

    public String getProgramTemplateId() {
        return programTemplateId;
    }

    public void setProgramTemplateId(String programTemplateId) {
        this.programTemplateId = programTemplateId == null ? null : programTemplateId.trim();
    }
}
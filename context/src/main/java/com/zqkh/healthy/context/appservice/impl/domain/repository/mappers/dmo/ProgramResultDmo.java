package com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo;

import java.util.Date;

public class ProgramResultDmo {
    private String id;

    private String familymemberId;

    private String programId;

    private Date startTime;

    private Date endTime;

    private Integer insistDay;

    private Integer wanderNum;

    private Integer giveUpNum;

    private Integer finishNum;

    private Date createTime;

    private Integer total;

    private Integer transcend;

    private String program;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFamilymemberId() {
        return familymemberId;
    }

    public void setFamilymemberId(String familymemberId) {
        this.familymemberId = familymemberId == null ? null : familymemberId.trim();
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId == null ? null : programId.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getInsistDay() {
        return insistDay;
    }

    public void setInsistDay(Integer insistDay) {
        this.insistDay = insistDay;
    }

    public Integer getWanderNum() {
        return wanderNum;
    }

    public void setWanderNum(Integer wanderNum) {
        this.wanderNum = wanderNum;
    }

    public Integer getGiveUpNum() {
        return giveUpNum;
    }

    public void setGiveUpNum(Integer giveUpNum) {
        this.giveUpNum = giveUpNum;
    }

    public Integer getFinishNum() {
        return finishNum;
    }

    public void setFinishNum(Integer finishNum) {
        this.finishNum = finishNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTranscend() {
        return transcend;
    }

    public void setTranscend(Integer transcend) {
        this.transcend = transcend;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program == null ? null : program.trim();
    }
}
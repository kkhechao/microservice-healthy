package com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo;

public class ProgramTemplateDmoWithBLOBs extends ProgramTemplateDmo {
    private String sharingImg;

    private String task;

    private String remind;

    public String getSharingImg() {
        return sharingImg;
    }

    public void setSharingImg(String sharingImg) {
        this.sharingImg = sharingImg == null ? null : sharingImg.trim();
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task == null ? null : task.trim();
    }

    public String getRemind() {
        return remind;
    }

    public void setRemind(String remind) {
        this.remind = remind == null ? null : remind.trim();
    }
}
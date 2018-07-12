package com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo;

public class ProgramDmoWithBLOBs extends ProgramDmo {
    private String programTemplate;

    private String feedback;

    private String srcDesc;

    public String getProgramTemplate() {
        return programTemplate;
    }

    public void setProgramTemplate(String programTemplate) {
        this.programTemplate = programTemplate == null ? null : programTemplate.trim();
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback == null ? null : feedback.trim();
    }

    public String getSrcDesc() {
        return srcDesc;
    }

    public void setSrcDesc(String srcDesc) {
        this.srcDesc = srcDesc == null ? null : srcDesc.trim();
    }
}
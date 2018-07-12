package com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo;

public class TestPaperDmoWithBLOBs extends TestPaperDmo {
    private String testPoint;

    private String testQuestions;

    public String getTestPoint() {
        return testPoint;
    }

    public void setTestPoint(String testPoint) {
        this.testPoint = testPoint == null ? null : testPoint.trim();
    }

    public String getTestQuestions() {
        return testQuestions;
    }

    public void setTestQuestions(String testQuestions) {
        this.testQuestions = testQuestions == null ? null : testQuestions.trim();
    }
}
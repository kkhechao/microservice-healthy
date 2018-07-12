package com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo;

public class TestResultDmoWithBLOBs extends TestResultDmo {
    private String answerResult;

    private String feedback;

    private String pointResult;

    private String testPaper;

    public String getAnswerResult() {
        return answerResult;
    }

    public void setAnswerResult(String answerResult) {
        this.answerResult = answerResult == null ? null : answerResult.trim();
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback == null ? null : feedback.trim();
    }

    public String getPointResult() {
        return pointResult;
    }

    public void setPointResult(String pointResult) {
        this.pointResult = pointResult == null ? null : pointResult.trim();
    }

    public String getTestPaper() {
        return testPaper;
    }

    public void setTestPaper(String testPaper) {
        this.testPaper = testPaper == null ? null : testPaper.trim();
    }
}
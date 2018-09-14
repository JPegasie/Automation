package com.pegasie.datamodel;

public class TestStep {
    public int testId;
    public String testName;
    public String page;
    public String stepAction;
    public String stepArgument;

    public TestStep(int testId, String testName, String page, String stepAction, String stepArgument) {
        this.testId = testId;
        this.testName = testName;
        this.page = page;
        this.stepAction = stepAction;
        this.stepArgument = stepArgument;
    }
}

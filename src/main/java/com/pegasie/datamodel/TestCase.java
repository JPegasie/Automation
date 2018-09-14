package com.pegasie.datamodel;

public class TestCase {
    public int testId;
    public String testName;
    public String testStatus;

    public TestCase(int testId, String testName, String testStatus) {
        this.testId = testId;
        this.testName = testName;
        this.testStatus = testStatus;
    }
}

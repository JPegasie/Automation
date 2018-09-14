package com.pegasie.util;

import org.openqa.selenium.WebElement;

public class TextBoxUtil {
    public static void inputValue(WebElement textBox, String value) {
        String randomString, randomTime;
        value = value.trim();
        switch (value) {
            case "@RandomString":
                randomString = RandomUtil.getRandomString();
                value = randomString;
                System.setProperty("RandomString", randomString);
                break;
            case "@RandomTime":
                randomTime = RandomUtil.getRandomTime();
                value = randomTime.substring(6);
                System.setProperty("RandomTime", value);
                break;
            case "@PreviousRandomString":
                value = System.getProperty("RandomString");
                break;
            case "@PreviousRandomTime":
                value = System.getProperty("RandomTime");
                break;
            case "@GetOutputValue1":
            case "@GetOutputValue2":
            case "@GetOutputValue3":
                OutputValueUtil outputValue = new OutputValueUtil();
                value = outputValue.getValue(value);
                break;
            case "@GetMonthDayTimeStamp":
                randomTime = RandomUtil.getRandomTime();
                value = randomTime.substring(4,14);
                break;
        }
        textBox.sendKeys(value);
    }
}

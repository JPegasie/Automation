package com.pegasie.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DropDownListUtil {

    public static boolean selectByVisibleText(WebElement select, String text) {
        boolean result = false;
        if (text.length() != 0) {
            List<WebElement> options = select.findElements(By.tagName("option"));
            for (WebElement option : options) {
                if (option.getText().equals(text)) {
                    option.click();
                    result = true;
                    break;
                }
            }
        }
        else {
            result = true;
        }
        return result;
    }

    public static void selectByValue(WebElement select, String value) {
        List<WebElement> options = select.findElements(By.tagName("option"));
        for (WebElement option : options) {
            if (option.getAttribute("value").equals(value)) {
                option.click();
                break;
            }
        }
    }

    public static void selectByIndex(WebElement select, String index) {
        List<WebElement> options = select.findElements(By.tagName("option"));
        options.get(StringUtil.convertToInt(index)).click();
    }
}

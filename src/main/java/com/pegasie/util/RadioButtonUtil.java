package com.pegasie.util;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RadioButtonUtil {

   public static boolean selectByValue(List<WebElement> toggleButtons, String text) {
        boolean result = false;
        if (text.length() != 0) {
            for (WebElement toggle : toggleButtons) {
                if (toggle.getAttribute("value").equalsIgnoreCase(text)) {
                    WebElement e = toggle.findElement(By.xpath("following-sibling::label"));
                    e.click();
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
}

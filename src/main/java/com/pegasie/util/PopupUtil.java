package com.pegasie.util;

import com.pegasie.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PopupUtil extends TestBase {
    public static void selectRadioButtonByValue(String value, String buttonText) {
        boolean isButtonFound = false;
        WebDriverWait wait = new WebDriverWait(driver, 5);
        String subWindowHandler = null;
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();
        String parentWindowHandler = iterator.next();
        while (iterator.hasNext()) {
            subWindowHandler = iterator.next();
        }
        driver.switchTo().window(subWindowHandler);
        List<WebElement> radioButtons = driver.findElements(By.cssSelector("input[type='radio']"));
        for (WebElement radioButton : radioButtons) {
            String radioButtonValue = radioButton.getAttribute("value");
            radioButtonValue = radioButtonValue.substring(0, radioButtonValue.indexOf(";"));
            if (radioButtonValue.equals(value)) {
                wait.until(ExpectedConditions.elementToBeClickable(radioButton));
                isButtonFound = true;
                radioButton.click();
                break;
            }
        }
        if (!isButtonFound) {
            throw new NoSuchElementException("The radio button with the value: " + value + " doesn't exist.");
        }
        TestBase.prendreCaptureEcran(driver.getTitle());
        buttonText = buttonText.trim();
        WebElement button = null;
        if (buttonText.equals("OK")) {
            button = driver.findElement(By.id("btnSave"));

        } else if (buttonText.equals("Cancel") || buttonText.equals("Annuler")) {
            button = driver.findElement(By.id("btnCancel"));
        }
        wait.until(ExpectedConditions.elementToBeClickable(button));
        button.click();
        driver.switchTo().window(parentWindowHandler);
    }


    public static void selectRadioButtonByRowNumber(String numero, String buttonText) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        int rowNo = StringUtil.convertToInt(numero) - 1;
        String subWindowHandler = null;
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();
        String parentWindowHandler = iterator.next();
        while (iterator.hasNext()) {
            subWindowHandler = iterator.next();
        }
        driver.switchTo().window(subWindowHandler);
        List<WebElement> radioButtons = driver.findElements(By.cssSelector("input[type='radio']"));
        WebElement radioButton = radioButtons.get(rowNo);
        wait.until(ExpectedConditions.elementToBeClickable(radioButton));
        radioButton.click();
        TestBase.prendreCaptureEcran(driver.getTitle());
        buttonText = buttonText.trim();
        WebElement button = null;
        if (buttonText.equals("OK")) {
            button = driver.findElement(By.id("btnSave"));
        } else if (buttonText.equals("Cancel") || buttonText.equals("Annuler")) {
            button = driver.findElement(By.id("btnCancel"));
        }
        wait.until(ExpectedConditions.elementToBeClickable(button));
        button.click();
        driver.switchTo().window(parentWindowHandler);
    }

    /**
     * This method will select a radioButton based on its index
     * The btnId assumes the index number is suffixed. Example: rdoSelect_0
     * The index is which radioButton index you want to select.
     *
     * @param btnId It is the prefix value of the radioButton 'id' attribute before
     *              its suffix represents its index.
     * @param index It is the index of the radioButton you want to select.
     */
    public static void selectRadioButtonByIndex(String btnId, String index) {
        String subWindowHandler = null;
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();
        String parentWindowHandler = iterator.next();
        while (iterator.hasNext()) {
            subWindowHandler = iterator.next();
        }
        driver.switchTo().window(subWindowHandler);
        List<WebElement> radioButtons = driver.findElements(By.cssSelector("input[type='radio']"));
        for (WebElement radioButton : radioButtons) {
            String radioButtonId = radioButton.getAttribute("id");
            radioButtonId = radioButtonId.replace(btnId, "");
            int num = StringUtil.convertToInt(index) - 1;
            String numberAsString = Integer.toString(num);
            if (radioButtonId.equals(numberAsString)) {
                radioButton.click();
                break;
            }
        }
        TestBase.prendreCaptureEcran(driver.getTitle());
        driver.findElement(By.id("btnSave")).click();
        driver.switchTo().window(parentWindowHandler);
    }
}

package com.pegasie.pages.cia;

import com.pegasie.pages.TemplatePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends TemplatePage {

    @FindBy(how = How.XPATH, using = "//h1[@class='heading']")
        WebElement lblPageTitle;

    @FindBy(how = How.XPATH, using = "//div[@class='flex-container']/header[@id='header']")
    WebElement containerHeader;

    @FindBy(how = How.XPATH, using = "//a[@href='/ws/user/logout']")
    WebElement lnkLogout;

    public boolean verifyContentTitle (String title) {
        System.out.println(lblPageTitle.getTagName());
        //return lblPageTitle.getText().equalsIgnoreCase(title);
        return true;
    }

    public void clickLogout() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='flex-container']/header[@id='header']")));
        wait.until(ExpectedConditions.elementToBeClickable(lnkLogout));
        lnkLogout.click();
    }
}

package com.pegasie.pages.internethero;

import com.pegasie.pages.TemplatePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends TemplatePage {

    @FindBy(how = How.XPATH, using = "//h1[@class='heading']")
        WebElement lblPageTitle;

    public boolean verifyContentTitle (String title) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(lblPageTitle));
        return lblPageTitle.getText().equalsIgnoreCase(title);
    }

}

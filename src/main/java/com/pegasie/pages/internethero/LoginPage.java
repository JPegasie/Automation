package com.pegasie.pages.internethero;

import com.pegasie.pages.TemplatePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends TemplatePage {

    @FindBy(how = How.ID, using = "username")
    WebElement txtUserName;

    @FindBy(how = How.ID, using = "password")
    WebElement txtPassword;

    @FindBy(how = How.XPATH, using = "/html/body/div[2]/div/div/form/button/i")
    WebElement btLogin;

    public boolean enterUserName(String username) {
        boolean result = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOf(txtUserName));
            txtUserName.sendKeys(username);
            result = true;
        }
        catch (Exception ex) {
            System.out.println("ERROR = " + ex);
        }
        return result;
    }

    public boolean enterPassword(String password) {
        boolean result = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOf(txtPassword));
            txtPassword.sendKeys(password);
            result = true;
        }
        catch (Exception ex) {
            System.out.println("ERROR = " + ex);
        }
        return result;
    }

    public boolean clickLogin() {
        boolean result = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOf(btLogin));
            btLogin.click();
            result = true;
        }
        catch (Exception ex) {
            System.out.println("ERROR = " + ex);
        }
        return result;
    }


}

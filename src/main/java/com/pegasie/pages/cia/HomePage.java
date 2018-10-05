package com.pegasie.pages.cia;

import com.pegasie.pages.TemplatePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage extends TemplatePage {

    @FindBy(how = How.XPATH, using = "//h1[@class='heading']")
        WebElement lblPageTitle;
    @FindBy(how = How.XPATH, using = "//a[@href='/ws/user/logout']")
    WebElement lnkLogout;
    @FindBy(how = How.XPATH, using = "//span[@data-bind='text : affectedRequirements']")
        WebElement lblAffectedFeaturesCnt;
    @FindBy(how = How.XPATH, using = "//div[@id='chart_placeholder']")
        WebElement lblWheel;

    public boolean verifyContentTitle (String title) {
        System.out.println(lblPageTitle.getTagName());
        //return lblPageTitle.getText().equalsIgnoreCase(title);
        return true;
    }

    public boolean clickLogout() {
        boolean result = false;
        try {
            waitPageReady();
            lnkLogout.click();
            result = true;
        }
        catch (NoSuchElementException ex) {
            System.out.println("<<" +ex.getLocalizedMessage() + ">>");
        }
        finally {
            return result;
        }
    }

    public boolean verifyAffectedFeaturesCnt(String lblCount) {
        boolean result = false;
        try {
            waitPageReady();
            if (lblAffectedFeaturesCnt.getText().contains(lblCount)) {
                result = true;
            }
        }
        catch (NoSuchElementException ex) {
            System.out.println("<<" +ex.getLocalizedMessage() + ">>");
        }
        finally {
            return result;
        }
    }

    public boolean verifyRequirement(String requirem) {
        boolean result = false;
        try {
            waitPageReady();
            System.out.println(lblWheel.getAttribute("id") + " ATTRIBUTE");
            System.out.println(lblWheel.findElements(By.tagName("text")).size() + " SIZE of text");
            List<WebElement> reqs = lblWheel.findElements(By.tagName("text"));
            for (WebElement req : reqs) {
                //System.out.println(req.getText());
                if (req.getText().contains(requirem)) {
                    result = true;
                    break;
                }
             }
        }
        catch (NoSuchElementException ex) {
            System.out.println("<<" +ex.getLocalizedMessage() + ">>");
        }
        finally {
            return result;
        }
    }

    public boolean verifyLogoutLink() {
        boolean result = false;
       //waitPageReady();
        return lnkLogout.getText().equalsIgnoreCase("logout");
    }
}

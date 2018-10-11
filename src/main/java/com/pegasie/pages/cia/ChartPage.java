package com.pegasie.pages.cia;

import com.pegasie.pages.TemplatePage;
import com.pegasie.util.HTMLTableUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ChartPage extends TemplatePage {


    @FindBy(how = How.XPATH, using = "//span[@data-bind='text : affectedRequirements']")
        WebElement lblAffectedFeaturesCnt;
    @FindBy(how = How.XPATH, using = "//div[@id='chart_placeholder']")
        WebElement lblWheel;

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






    public boolean verifyRequirementNotContains(String requirem) {
        String sFunc="verifyRequirementNotContains";
        boolean result = true;
        try {
            waitPageReady();
            System.out.println(lblWheel.getAttribute("id") + " ATTRIBUTE");
            System.out.println(lblWheel.findElements(By.tagName("text")).size() + " SIZE of text");
            List<WebElement> reqs = lblWheel.findElements(By.tagName("text"));
            for (WebElement req : reqs) {
                //System.out.println(req.getText());
                if (req.getText().contains(requirem)) {
                    logger.info("search requirement is:"+requirem);
                    logger.info("Actual requirement is:"+req.getText());
                    logger.warn("Function name is:"+sFunc+"--Failed");
                    result = false;
                    break;
                }
            }
        }
        catch (NoSuchElementException ex) {
            System.out.println("<<" +ex.getLocalizedMessage() + ">>");
            logger.error(ex.getMessage());
        }
        finally {
            return result;
        }
    }


}

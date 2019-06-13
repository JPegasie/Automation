package com.pegasie.pages.cia;

import com.pegasie.pages.TemplatePage;
import com.pegasie.util.HTMLTableUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ChartPage extends TemplatePage {


    @FindBy(how = How.XPATH, using = "//span[@data-bind='text : affectedRequirements']")
        WebElement lblAffectedFeaturesCnt;
    @FindBy(how = How.XPATH, using = "//div[@id='chart_placeholder']")
        WebElement lblWheel;
    @FindBy(how = How.ID, using = "affectedRequirementslabel")
        WebElement lablelAffectedassets;
    @FindBy(how = How.CLASS_NAME, using = "styled-select")
        WebElement releaseName;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/div[1]/div[2]/div[1]/div[1]/label[1]")
        WebElement butLevelView;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/div/div[2]/div[1]/div[1]/label[2]")
        WebElement butFolderView;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/span[14]/a")
    WebElement labSelectLevel4;
    @FindBy(how = How.ID, using = "affectedRequirementslabel")
    WebElement lblAffectedFeaturesCnt2;




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
            List<WebElement> reqs = getWheelItems();
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
            List<WebElement> reqs = getWheelItems();
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


    public boolean verifyAffectedAssets(String lblAffectedAssets) {
        boolean result = false;
        try {
            waitPageReady();
            if (lablelAffectedassets.getText().contains(lblAffectedAssets)) {
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

    public boolean verifyAffectedAssetsCnt(String lblAffectedAssets) {
        boolean result = false;
        try {
            waitPageReady();
            //Remove .0 from the excel file
            String tmpString = lblAffectedAssets.replace(".0","");
            if (lblAffectedFeaturesCnt2.getText().contains(tmpString)) {
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



    public boolean selectRel(String rel){
        boolean result = false;
        try {
//            waitPageReady();
            new Select(releaseName).selectByVisibleText(rel);
            releaseName.click();
//            waitPageReady();
            result = true;
        }
        catch(Exception ex){
            System.out.println("<<" +ex.getLocalizedMessage() + ">>");
            result = false;
        }
        finally {
            return result;
        }


    }

    public boolean selectLevelView() {
        boolean result = false;
        try {
//            waitPageReady();
            butLevelView.click();
//            waitPageReady();
            result = true;
        } catch (Exception ex) {
            System.out.println("<<" + ex.getLocalizedMessage() + ">>");
            result = false;
        } finally {
            return result;
        }
    }

    public boolean selectFolderView() {
        boolean result = false;
        try {
//            waitPageReady();
            butFolderView.click();
//            waitPageReady();
            result = true;
        } catch (Exception ex) {
            System.out.println("<<" + ex.getLocalizedMessage() + ">>");
            result = false;
        } finally {
            return result;
        }
    }
    public boolean selectLevel4() {
        boolean result = false;
        try {
            waitPageReady();
//            Thread.sleep(3000);
            labSelectLevel4.click();
            result = true;
        } catch (Exception ex) {
            System.out.println("<<" + ex.getLocalizedMessage() + ">>");
            result = false;
        } finally {
            return result;
        }
    }
    public boolean verifyRequirementColorRed(String requirem) {
        boolean result = false;
        String reqColor;
        String reqColorCheck = "#FF0000";
        try {
            if (verifyRequirement(requirem)){
                List<WebElement> reqs = getWheelItems();
                for (WebElement req : reqs) {
                    //System.out.println(req.getText());
                    if (req.getText().contains(requirem)) {
                        reqColor = req.getCssValue("fill");
                        reqColor = reqColor.toUpperCase();
//                        System.out.println("reqColor = " + reqColor);
                        if (reqColor.equalsIgnoreCase(reqColorCheck) ){
                            result = true;
                            break;
                        }
                        else{
                            result = false;
                        }
                        break;
                    }
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

    public boolean verifyRequirementColorBlue(String requirem) {
        boolean result = false;
        String reqColor;
        String reqColorCheck = "#35A3DE";
        try {
            if (verifyRequirement(requirem)){
                List<WebElement> reqs = getWheelItems();
                for (WebElement req : reqs) {
                    //System.out.println(req.getText());
                    if (req.getText().contains(requirem)) {
                        reqColor = req.getCssValue("fill");
                        reqColor = reqColor.toUpperCase();
                        System.out.println("reqColor = " + reqColor);
                        if (reqColor.equalsIgnoreCase(reqColorCheck) ){
                            result = true;
                            break;
                        }
                        break;
                    }
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

    public boolean verifyRequirementColorBlack(String requirem) {
        boolean result = false;
        String reqColor;
        String reqColorCheck1 = "#35A3DE";
        String reqColorCheck2 = "#FF0000";

        try {
            if (verifyRequirement(requirem)){
                List<WebElement> reqs = getWheelItems();
                for (WebElement req : reqs) {
                    //System.out.println(req.getText());
                    if (req.getText().contains(requirem)) {
                        reqColor = req.getCssValue("fill");
                        reqColor = reqColor.toUpperCase();
//                        System.out.println("reqColor = " + reqColor);
                        if (!reqColor.equalsIgnoreCase(reqColorCheck1) &&  !reqColor.equalsIgnoreCase(reqColorCheck2)){
                            result = true;
                            break;
                        }
                        break;
                    }
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

    public List<WebElement> getWheelItems(){
        waitPageReady();
//        System.out.println(lblWheel.getAttribute("id") + " ATTRIBUTE");
//        System.out.println(lblWheel.findElements(By.tagName("text")).size() + " SIZE of text");
        List<WebElement> reqs = lblWheel.findElements(By.tagName("text"));
        return reqs;
    }

}

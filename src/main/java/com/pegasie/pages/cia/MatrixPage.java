package com.pegasie.pages.cia;

import com.pegasie.pages.TemplatePage;
import com.pegasie.util.WebElementUtil;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MatrixPage extends TemplatePage{
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/div/div[2]/div[2]/div[1]/div[2]/button/span")
    WebElement bExpot;

    @FindBy(how = How.XPATH, using = "/html/body/div[2]/div/div[2]/div[2]/div[2]/div[4]/button[1]")
    WebElement bTo;

    @FindBy(how = How.XPATH, using = "/html/body/div[2]/div/div[2]/div[2]/div[2]/div[4]/button[2]")
    WebElement bFrom;

    @FindBy(how = How.ID, using = "addFilterRequirement")
    WebElement txtAddReq;

    @FindBy(how = How.ID, using = "inputFilterRequirements")
    WebElement txtSearchForReq;

    @FindBy(how = How.ID, using="requirementsFilterSelectList")
    WebElement selectReqFilter;

    @FindBy(how = How.CLASS_NAME, using ="styled-select")
    WebElement releaseName;

    @FindBy(how = How.ID, using="matrix_table")
    WebElement tableMatrix;

    public boolean verifyMatrix() {
        boolean result = false;
        try {
            //waitPageReady();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(tableMatrix));
            result = WebElementUtil.isWebElementExist(tableMatrix);
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
//            releaseName.click();
            waitPageReady();
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



}

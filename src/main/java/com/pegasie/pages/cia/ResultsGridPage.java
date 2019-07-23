package com.pegasie.pages.cia;

import com.pegasie.pages.TemplatePage;
import com.pegasie.util.DropDownListUtil;
import com.pegasie.util.HTMLTableUtil;
import com.pegasie.util.RadioButtonUtil;
import com.pegasie.util.WebElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class ResultsGridPage extends TemplatePage {

    @FindBy(how = How.XPATH, using = "//h1[@class='heading']")
        WebElement lblPageTitle;
    @FindBy(how = How.XPATH, using = "/html/body/div[1]/header/div[3]/div/a[2]/span")
            WebElement lnkLogout;
    @FindBy(how = How.XPATH, using = "//*[@id=\'header\']/div[3]/div/a[2]/span")
        WebElement lnkLogoutOri;
    @FindBy(how = How.CLASS_NAME, using = "styled-select")
        WebElement drpDwnRelease;
    @FindBy(how = How.XPATH, using = "//input[@type='radio'][@name='view']")
    List<WebElement> rdoBtnView;
    @FindBy(how = How.CSS, using = ".btn.btn-info.btn-sm.refresh")
        WebElement btnRefresh;


    @FindBy(how = How.XPATH, using = "//div[@title='Summary']//table[@class='table table-hover optimizedScope']")
        WebElement tblSummaryOptimized;
    @FindBy(how = How.XPATH, using = "//div[@title='Initial Scope']//table[@class='table table-hover']")
        WebElement tblSummaryInitialScope;
    @FindBy(how = How.XPATH, using = "//div[@title='Savings']//table[@class='table table-hover']")
        WebElement tblSummarySavings;

    //FR Test 2018-10-10
    //@FindBy(how = How.XPATH, using = "//*[@id='filters']/div[1]/label[1]")
    @FindBy(how = How.XPATH, using ="//*[@id='filters']/h4/button")
        WebElement btnRefreshFR;

    @FindBy(how = How.XPATH, using ="//*[@id=\'wheelChartBreadCrumb\']/div/span[1]")
        WebElement bcRoot;

    @FindBy(how = How.XPATH, using ="//*[@id=\'wheelChartBreadCrumb\']/div/span[5]/a")
        WebElement bcSuite;

    @FindBy(how = How.XPATH, using ="//*[@id=\'wheelChartBreadCrumb\']/div/span[8]/a")
        WebElement bcApplication;

    @FindBy(how = How.XPATH, using ="//*[@id=\'wheelChartBreadCrumb\']/div/span[11]/a")
        WebElement bcTab;

    @FindBy(how = How.XPATH, using ="//*[@id=\'wheelChartBreadCrumb\']/div/span[14]/a")
        WebElement bcFunction;

    @FindBy(how = How.XPATH, using ="//*[@id=\'wheelChartBreadCrumb\']/div/span[17]/a")
        WebElement bcFeature;

    @FindBy(how = How.XPATH, using ="//*[@id=\'header\']/div[4]")
    WebElement lblLoginName;

    //Joseph 2019-07-17
    @FindBy(how = How.XPATH, using ="//*[@id=\'wheelChartBreadCrumb\']/div/span[11]/a")
    WebElement bcFunctions;

    @FindBy(how = How.XPATH, using ="//*[@id=\'exTab\']/ul/li[1]/a/h5")
    WebElement tabRequirements;

    @FindBy(how = How.XPATH, using ="//*[@id=\'exTab\']/ul/li[2]/a/h5")
    WebElement tabDefects;


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
            waitPageReady();
            result = true;
        }
        catch (NoSuchElementException ex) {
            System.out.println("<<" +ex.getLocalizedMessage() + ">>");
        }
        finally {
            return result;
        }
    }

    public boolean selectRelease(String value) {
        boolean result = false;
        try {
            waitPageReady();
            result = DropDownListUtil.selectByVisibleText(drpDwnRelease, value);
        }
        catch (NoSuchElementException ex) {
            System.out.println("<<" +ex.getLocalizedMessage() + ">>");
        }
        finally {
            return result;
        }
    }

    public boolean selectView(String value) {
        boolean result = false;
        try {
            waitPageReady();
            System.out.println("Size of View: " + rdoBtnView.size());
            result = RadioButtonUtil.selectByValue(rdoBtnView, value);
        }
        catch (NoSuchElementException ex) {
            System.out.println("<<" +ex.getLocalizedMessage() + ">>");
        }
        finally {
            return result;
        }
    }

    public boolean clickRefresh() {
        boolean result = false;
        try {
            waitPageReady();
            btnRefresh.click();
            result = true;
        }
        catch (NoSuchElementException ex) {
            System.out.println("<<" +ex.getLocalizedMessage() + ">>");
        }
        finally {
            return result;
        }
    }

    public boolean verifyOptScopeTextExecTargeted(String value) {
        boolean result = false;
        System.out.println(tblSummaryOptimized.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(tblSummaryOptimized);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,2);
        result = Cols.get(1).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifyOptScopeTextExecAdditional(String value) {
        boolean result = false;
        System.out.println(tblSummaryOptimized.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(tblSummaryOptimized);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,2);
        result = Cols.get(2).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifyOptScopeTextExecTotal(String value) {
        boolean result = false;
        System.out.println(tblSummaryOptimized.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(tblSummaryOptimized);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,2);
        result = Cols.get(3).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifyOptScopeLinkedTestsTargeted(String value) {
        boolean result = false;
        System.out.println(tblSummaryOptimized.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(tblSummaryOptimized);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,3);
        result = Cols.get(1).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifyOptScopeLinkedTestsAdditional(String value) {
        boolean result = false;
        System.out.println(tblSummaryOptimized.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(tblSummaryOptimized);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,3);
        result = Cols.get(2).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifyOptScopeLinkedTestsTotal(String value) {
        boolean result = false;
        System.out.println(tblSummaryOptimized.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(tblSummaryOptimized);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,3);
        result = Cols.get(3).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifyIntScopeTextExecTargeted(String value) {
        boolean result = false;
        System.out.println(tblSummaryInitialScope.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(tblSummaryInitialScope);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,2);
        result = Cols.get(1).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifyIntScopeTextExecAdditional(String value) {
        boolean result = false;
        System.out.println(tblSummaryInitialScope.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(tblSummaryInitialScope);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,2);
        result = Cols.get(2).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifyIntScopeTextExecTotal(String value) {
        boolean result = false;
        System.out.println(tblSummaryInitialScope.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(tblSummaryInitialScope);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,2);
        result = Cols.get(3).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifyIntScopeLinkedTestsTargeted(String value) {
        boolean result = false;
        System.out.println(tblSummaryInitialScope.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(tblSummaryInitialScope);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,3);
        result = Cols.get(1).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifyIntScopeLinkedTestsAdditional(String value) {
        boolean result = false;
        System.out.println(tblSummaryInitialScope.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(tblSummaryInitialScope);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,3);
        result = Cols.get(2).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifyIntScopeLinkedTestsTotal(String value) {
        boolean result = false;
        System.out.println(tblSummaryInitialScope.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(tblSummaryInitialScope);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,3);
        result = Cols.get(3).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifySavingsTestExecFullRegression(String value) {
        boolean result = false;
        System.out.println(tblSummarySavings.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(tblSummarySavings);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,2);
        result = Cols.get(1).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifySavingsTestExecInitialScope(String value) {
        boolean result = false;
        System.out.println(tblSummarySavings.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(tblSummarySavings);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,2);
        result = Cols.get(2).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifySavingsTestExecOptimizedScope(String value) {
        boolean result = false;
        System.out.println(tblSummarySavings.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(tblSummarySavings);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,2);
        result = Cols.get(3).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifyLogoutLink() {
        boolean result = false;
       //waitPageReady();
        return lnkLogout.getText().equalsIgnoreCase("logout");
    }

    //Frank Test 2018-10-10
    public boolean getAttributeOfRefresh() {
        boolean result = false;
        String oAttri;
        System.out.println("Test started");
        //oAttri = btnRefreshFR.getAttribute("class");
        System.out.println("FR class is="+btnRefreshFR.getAttribute("class"));
        //waitPageReady();
        result = btnRefreshFR.getText().equalsIgnoreCase("Refresh");
        System.out.println(btnRefreshFR.getText());
        //return lnkLogout.getText().equalsIgnoreCase("Refresh");
        return result;
    }

    //Frank @ 2018-10-10
    public boolean isElementExisting(String sWebEle) {
        String sFuncName = "isElementExisting";
        String sID = "filters";
        System.out.println(sFuncName +" is started Here");
        Boolean bResult = false;
        String sExpected = "Refresh";
        try {
            bResult = WebElementUtil.isWebElementExistByID( driver, sID);
            System.out.println("The Execution result is:"+bResult);
            bResult = WebElementUtil.isWebElementExist(btnRefreshFR);
            if  (bResult==true); {
                bResult = WebElementUtil.isWebElementExist(btnRefreshFR, sExpected );
                //if (bResult==true); {
                //
                //    bResult = WebElementUtil.isWebElementExistByID( driver, sID);
                //}
            }



            return bResult;
        } catch(NoSuchElementException e) {
            System.out.println("Element does not exist.");
            return false;
        }
    }

    public boolean verifyLogin() {
        boolean result = false;
        try {
            //waitPageReady();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(lblLoginName));
            result = WebElementUtil.isWebElementExist(lblLoginName);
            //lnkLogout.click();
            //result = true;
        }
        catch (NoSuchElementException ex) {
            System.out.println("<<" +ex.getLocalizedMessage() + ">>");
        }
        finally {
            return result;
        }
    }



    public boolean clickBreadCrumb(String sBC) {
        boolean result = false;
        String sClass;
        try {
            //waitPageReady();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            switch (sBC.toUpperCase()) {
                case "ROOT":
                    wait.until(ExpectedConditions.elementToBeClickable(bcRoot));
                    bcRoot.click();
                    break;
                case "SUITE":
                    wait.until(ExpectedConditions.elementToBeClickable(bcSuite));
                    bcSuite.click();
                    break;
                case "APPLICATION":
                    wait.until(ExpectedConditions.elementToBeClickable(bcApplication));
                    bcApplication.click();
                    break;
                case "TAB":
                    //WebDriverWait wait = new WebDriverWait(driver, 10);
                    wait.until(ExpectedConditions.elementToBeClickable(bcTab));
                    bcTab.click();
                    break;
                case "FUNCTION":
//                    WebDriverWait wait = new WebDriverWait(driver, 10);
                    wait.until(ExpectedConditions.elementToBeClickable(bcFunction));
                    sClass = bcFunction.getAttribute("class");
                   // if (sClass.equalsIgnoreCase("selected")) {
                   //     result = false;
                    //}
//                    else {
                     bcFunction.click();
                        result = true;
                        break;
//                    }

                case "FEATURE":
                    wait.until(ExpectedConditions.elementToBeClickable(bcFeature));
                    bcFeature.click();
                    break;
                case "FUNCTIONS":
                    wait.until(ExpectedConditions.elementToBeClickable(bcFunctions));
                    bcFunctions.click();
                    break;

            }

            result = true;
        }
        catch (NoSuchElementException ex) {
            System.out.println("<<" +ex.getLocalizedMessage() + ">>");
            logger.error(ex.getMessage());
        }
        finally {
            return result;
        }
    }

    //Joseph 2019_07_17
    public boolean clickDefects() {
        boolean result = false;
        try {
            waitPageReady();
            tabDefects.click();
            result = true;
        }
        catch (NoSuchElementException ex) {
            System.out.println("<<" +ex.getLocalizedMessage() + ">>");
        }
        finally {
            return result;
        }
    }

    public boolean clickReuirements() {
        boolean result = false;
        try {
            waitPageReady();
            tabRequirements.click();
            result = true;
        }
        catch (NoSuchElementException ex) {
            System.out.println("<<" +ex.getLocalizedMessage() + ">>");
        }
        finally {
            return result;
        }
    }

    /*
    public boolean getAttributeOfRefresh() {

        boolean result = false;
        String oAttri;
        System.out.println("Test started");
        //oAttri = btnRefreshFR.getAttribute("class");
        System.out.println("FR class is="+btnRefreshFR.getAttribute("class"));
        //waitPageReady();
        result = btnRefreshFR.getText().equalsIgnoreCase("Refresh");
        System.out.println(btnRefreshFR.getText());
        //return lnkLogout.getText().equalsIgnoreCase("Refresh");
        return result;
    }
    */

}

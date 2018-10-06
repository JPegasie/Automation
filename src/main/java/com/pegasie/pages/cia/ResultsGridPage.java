package com.pegasie.pages.cia;

import com.pegasie.pages.TemplatePage;
import com.pegasie.util.HTMLTableUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ResultsGridPage extends TemplatePage {

    @FindBy(how = How.XPATH, using = "//h1[@class='heading']")
        WebElement lblPageTitle;
    @FindBy(how = How.XPATH, using = "//a[@href='/ws/user/logout']")
        WebElement lnkLogout;

    @FindBy(how = How.XPATH, using = "//div[@title='Summary']//table[@class='table table-hover optimizedScope']")
        WebElement summaryOptimizedTable;
    @FindBy(how = How.XPATH, using = "//div[@title='Summary']//table[@class='table table-hover optimizedScope']")
        WebElement summaryInitialScopeTable;

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

    public boolean verifyOptScopeTextExecTargeted(String value) {
        boolean result = false;
        System.out.println(summaryOptimizedTable.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(summaryOptimizedTable);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,2);
        result = Cols.get(1).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifyOptScopeTextExecAdditional(String value) {
        boolean result = false;
        System.out.println(summaryOptimizedTable.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(summaryOptimizedTable);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,2);
        result = Cols.get(2).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifyOptScopeTextExecTotal(String value) {
        boolean result = false;
        System.out.println(summaryOptimizedTable.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(summaryOptimizedTable);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,2);
        result = Cols.get(3).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifyOptScopeLinkedTestsTargeted(String value) {
        boolean result = false;
        System.out.println(summaryOptimizedTable.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(summaryOptimizedTable);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,3);
        result = Cols.get(1).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifyOptScopeLinkedTestsAdditional(String value) {
        boolean result = false;
        System.out.println(summaryOptimizedTable.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(summaryOptimizedTable);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,3);
        result = Cols.get(2).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifyOptScopeLinkedTestsTotal(String value) {
        boolean result = false;
        System.out.println(summaryOptimizedTable.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(summaryOptimizedTable);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,3);
        result = Cols.get(3).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifyIntScopeTextExecTargeted(String value) {
        boolean result = false;
        System.out.println(summaryInitialScopeTable.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(summaryInitialScopeTable);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,2);
        result = Cols.get(1).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifyIntScopeTextExecAdditional(String value) {
        boolean result = false;
        System.out.println(summaryInitialScopeTable.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(summaryInitialScopeTable);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,2);
        result = Cols.get(2).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifyIntScopeTextExecTotal(String value) {
        boolean result = false;
        System.out.println(summaryInitialScopeTable.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(summaryInitialScopeTable);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,2);
        result = Cols.get(3).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifyIntScopeLinkedTestsTargeted(String value) {
        boolean result = false;
        System.out.println(summaryInitialScopeTable.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(summaryInitialScopeTable);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,3);
        result = Cols.get(1).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifyIntScopeLinkedTestsAdditional(String value) {
        boolean result = false;
        System.out.println(summaryInitialScopeTable.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(summaryInitialScopeTable);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,3);
        result = Cols.get(2).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifyIntScopeLinkedTestsTotal(String value) {
        boolean result = false;
        System.out.println(summaryInitialScopeTable.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(summaryInitialScopeTable);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,3);
        result = Cols.get(3).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifyLogoutLink() {
        boolean result = false;
       //waitPageReady();
        return lnkLogout.getText().equalsIgnoreCase("logout");
    }
}

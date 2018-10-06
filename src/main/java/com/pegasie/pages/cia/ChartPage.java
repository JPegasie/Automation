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

    @FindBy(how = How.XPATH, using = "//div[@id='chart_placeholder']")
        WebElement lblWheel;
    @FindBy(how = How.XPATH, using = "//div[@title='Summary']//table[@class='table table-hover optimizedScope']")
        WebElement summaryTable;

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

    public boolean verifyOptScopeTextExecTargeted(String value) {
        boolean result = false;
        System.out.println(summaryTable.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(summaryTable);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,2);
        result = Cols.get(1).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifyOptScopeTextExecAdditional(String value) {
        boolean result = false;
        System.out.println(summaryTable.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(summaryTable);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,2);
        result = Cols.get(2).getText().equalsIgnoreCase(value);
        return result;
    }

    public boolean verifyOptScopeTextExecTotal(String value) {
        boolean result = false;
        System.out.println(summaryTable.getAttribute("class"));
        List<WebElement> Rows = HTMLTableUtil.getRowsFromTableBody(summaryTable);
        List<WebElement> Cols = HTMLTableUtil.getCellsFromRows(Rows,2);
        result = Cols.get(3).getText().equalsIgnoreCase(value);
        return result;
    }
}
package com.pegasie.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HTMLTableUtil {

    /**
     * The method retrieve all the rows from a HTML table body.
     * Return a tr web element list.
     *
     * @param htmlTable A table WebElement.
     * @return A list of "tr" element.
     * @since 2018-07-23
     */
    public static List<WebElement> getRowsFromTableBody(WebElement htmlTable) {
        List<WebElement> rows = htmlTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        return rows;
    }

    /**
     * The method retrieve a single row contains td elements from a rows list.
     * Return a td element list.
     *
     * @param rows  A list of "tr" element.
     * @param rowNo A row number.
     * @return A list of "td" element of the specific row.
     * @since 2018-07-23
     */
    public static List<WebElement> getCellsFromRows(List<WebElement> rows, int rowNo) {
        List<WebElement> cells = rows.get(rowNo - 1).findElements(By.tagName("td"));
        return cells;
    }

    /**
     * The method retrieve column names from HTML table header, in current design the column names use navHeader class.
     * Return a tr element list.
     *
     * @param htmlTable A table WebElement.
     * @return A list of "tr" element.
     * @since 2018-07-23
     */
    public static List<WebElement> getRowsFromTableHeader(WebElement htmlTable) {
        List<WebElement> rows = htmlTable.findElement(By.tagName("thead")).findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            if (row.getAttribute("class").equalsIgnoreCase("navHeader")) {
                rows.remove(row);
            }
        }
        return rows;
    }

    /**
     * The method verify a list of column content exist in a HTML table.
     * If the row number is provided, it will check the existence of the expect content in that row.
     * If the row number is not provided, it will iterate through all the row to find whether it exists.
     * Return true only if all the expect contents exist in the table, otherwise return false.
     *
     * @param htmlTable     A table WebElement.
     * @param expectContent A list of column content, example below:
     *                      Nom du rapport = Liste des employés (PDF) ; Date prod. = 2018/07/13 11:51:01
     *                      Nom du rapport = Liste des employés (PDF) ; Date prod. = 2018/07/13 12:05:31 ; Row = 3
     * @return True if expectContent is found, otherwise return false.
     * @since 2018-07-23
     */
    public static boolean verifierTable(WebElement htmlTable, String expectContent) {
        String[] parameters = null;
        int rowNo = 0;
        if (expectContent.contains(";")) {
            parameters = expectContent.split(";");
            if (expectContent.contains("Row")) {
                for (int i = 0; i < parameters.length; i++) {
                    String parameter = parameters[i];
                    if (parameter.contains("Row")) {
                        rowNo = StringUtil.convertToInt(parameter.substring(parameter.indexOf("=") + 1).trim()) - 1;
                        break;
                    }
                }
            }
        } else {
            parameters = new String[1];
            parameters[0] = expectContent.trim();
        }

        List<WebElement> columnHeaders = getRowsFromTableHeader(htmlTable).get(0).findElements(By.tagName("th"));
        List<WebElement> rows = getRowsFromTableBody(htmlTable);
        List<WebElement> records = new ArrayList<>();
        boolean result = true;
        if (rowNo != 0) {
            records = rows.get(rowNo).findElements(By.tagName("td"));
            for (int i = 0; i < columnHeaders.size(); i++) {
                for (String parameter : parameters) {
                    String columnName = columnHeaders.get(i).getText().trim();
                    if (columnName != null && !columnName.isEmpty() && parameter.contains(columnName)) {
                        String expectValue = parameter.substring(parameter.indexOf("=") + 1).trim();
                        String actualValue = records.get(i).getText().trim();
                        result = actualValue.equalsIgnoreCase(expectValue);
                        break;
                    }
                }
                if (!result) {
                    break;
                }
            }
        } else {
            boolean isFound = false;
            for (int i = 0; i < rows.size(); i++) {
                if (rows.get(i).getAttribute("style").equalsIgnoreCase("display: none;")) {
                    rows.remove(i);
                }
            }
            for (int i = 0; i < rows.size(); i++) {
                records = rows.get(i).findElements(By.tagName("td"));
                Boolean[] resultArray = new Boolean[parameters.length];
                for (int j = 0; j < columnHeaders.size(); j++) {
                    for (int k = 0; k < parameters.length; k++) {
                        String columnName = columnHeaders.get(j).getText().trim();
                        if (columnName != null && !columnName.isEmpty() && parameters[k].contains(columnName)) {
                            String expectValue = parameters[k].substring(parameters[k].indexOf("=") + 1).trim();
                            String actualValue = records.get(j).getText().trim();
                            resultArray[k] = actualValue.equalsIgnoreCase(expectValue);
                        }
                    }
                }
                for (boolean temp : resultArray) {
                    if (!temp) {
                        isFound = false;
                        break;
                    } else {
                        isFound = true;
                    }
                }
                if (isFound) {
                    break;
                }
            }
            result = isFound;
        }
        return result;
    }

}

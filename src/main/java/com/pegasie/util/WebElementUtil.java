package com.pegasie.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.NoSuchElementException;

import static com.pegasie.base.TestBase.logger;

public class WebElementUtil {
    public static boolean isWebElementExist(WebElement oWebEle) {
        boolean result = false;
        String sFunc = "isWebElementExist";
        String sClass;
        System.out.println(sFunc + " is started");
        try{

            sClass = oWebEle.getAttribute("class");
            if (sClass  != null && !sClass.isEmpty()) {
                System.out.println("The class is="+sClass);
                logger.info("Function name:"+sFunc+"--The WebElement is Existed. The class is:"+sClass);
                result = true;
            }
            else{
                logger.info("Function name:"+sFunc+"--The WebElement is Not Existed.");
                result = false;
            }


            return result;
        } catch(NoSuchElementException e) {
            System.out.println("Element does not exist.");
            e.printStackTrace();
            logger.error(e.getMessage());
            return false;
        }


    }

    public static boolean isWebElementExist(WebElement oWebEle, String sExpected) {
        boolean result = false;
        String sFunc = "isWebElementExist";
        String sClass;
        String sTextOfWebElement;
        System.out.println(sFunc + " is started");
        try{

            sClass = oWebEle.getAttribute("class");
            if (sClass  != null && !sClass.isEmpty()) {
                System.out.println("The class is="+sClass);
                sTextOfWebElement = oWebEle.getText();
                System.out.println("The WebElement text is:"+ sTextOfWebElement);
                System.out.println("The expected string is:"+sExpected);
                if (sTextOfWebElement.equalsIgnoreCase(sExpected)) {
                    System.out.println("I am here");
                    result = true;}
                else {
                    result = false;}
            }
            else{
                result = false;
            }

            System.out.println(result);
            return result;
        } catch(NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("Element does not exist.");
            return false;
        }


    }

    public static String getElementText(WebElement oWebEle) {
        //boolean result = false;
        String sFunc = "getElementText";
        String sClass;
        String sTextOfWebElement = "FAILED";
        System.out.println(sFunc + " is started");
        try{

            sClass = oWebEle.getAttribute("class");
            if (sClass  != null && !sClass.isEmpty()) {
                System.out.println("The class is="+sClass);
                sTextOfWebElement = oWebEle.getText();
                //System.out.println("The WebElement text is:"+ sTextOfWebElement);
                //return sTextOfWebElement;

            }


            //System.out.println(sTextOfWebElement);
            return sTextOfWebElement;
        } catch(NoSuchElementException e) {
            e.printStackTrace();
            //System.out.println("Element does not exist.");

            return sTextOfWebElement;
        }


    }

    public static Boolean verifyMessage(WebElement oWebEle, String sMsg) {
        boolean result = false;
        String sFunc = "verifyMessage";
        String sClass;
        String sTextOfWebElement = "FAILED";
        System.out.println(sFunc + " is started");
        try{

            sClass = oWebEle.getAttribute("class");
            if (sClass  != null && !sClass.isEmpty()) {
                //System.out.println("The class is="+sClass);
                sTextOfWebElement = oWebEle.getText();
                //System.out.println("The WebElement text is:"+ sTextOfWebElement);
                //return sTextOfWebElement;
                if (sTextOfWebElement.equalsIgnoreCase(sMsg)) {
                    result = true;
                    logger.info("The expected result is:"+sMsg);
                    logger.info("The actual result is:"+sTextOfWebElement);
                    logger.info("The function:"+sFunc+"--Passed");
                }
                else {
                    result = false;
                    logger.info("The expected result is:"+sMsg);
                    logger.info("The actual result is:"+sTextOfWebElement);
                    logger.warn("The function:"+sFunc+"--failed");
                }




            }


            //System.out.println(sTextOfWebElement);
            return result;
        } catch(NoSuchElementException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            //System.out.println("Element does not exist.");
            return false;
        }


    }



    public static boolean isWebElementExistByID(WebDriver driver, String sID1) {
        boolean result = false;
        String sFunc = "isWebElementExistByID";
        String sClass;
        String sText;
        System.out.println(sFunc + " is started*****");
        try{
            //driver.findElement(By.Id())
            sClass = driver.findElement(By.id(sID1)).getAttribute("class");
            sText = driver.findElement(By.id(sID1)).getText();
            System.out.println("The class is="+sClass);
            //sClass = oWebEle.getAttribute("class");
            if (sClass  != null && !sClass.isEmpty()) {
                System.out.println("The class is="+sClass);
                System.out.println("The text is "+sText);
                result = true;
                //return result;
            }
            else{
                result = false;
                //return result;
            }


            return result;
        } catch(NoSuchElementException e) {
            System.out.println("Element does not exist.");
            return false;
        }


    }
}

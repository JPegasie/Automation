package com.pegasie.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class QADebug {
    static WebDriver driver;

    public static void main(String[] args) {
        getNethris("Chrome");
//        getEmployerD("firefox");
    }

    private static void getEmployerD(String browserType) {
        getDriver(browserType);
        driver.get("https://demo.employeurd.com/WebPortal/UserLogin.aspx");
//        driver.get("https://demo.employeurd.com/WebPortal/UserLogin.aspx?CHANGELANGUAGE=en-US");
        driver.findElement(By.id("LOGINNAME__UMLOGIN__0")).sendKeys("Admin998");
        driver.findElement(By.id("COMMUNITY_CODE__UM_LOGIN__0")).sendKeys("ROBOT003");
        driver.findElement(By.id("buttonLogin")).click();
        driver.findElement(By.id("LOGIN_PASSWORD__UM_LOGIN__0")).sendKeys("ROBGCQ03");
        driver.findElement(By.id("buttonLogin")).click();
        driver.findElement(By.id("MIDDLE_SIN__UM_USER__0")).sendKeys("123");
        driver.findElement(By.id("LOGIN_ANSWER__UM_LOGIN_QUESTION__0")).sendKeys("nethris");
        driver.findElement(By.id("btnSave")).click();
    }

    private static void getNethris(String browserType) {
        getDriver(browserType);
        driver.get("https://demo.nethris.com/WebPortal/UserLogin.aspx");
        // driver.get("https://demo.nethris.com/WebPortal/UserLogin.aspx?CHANGELANGUAGE=en-US");
        driver.findElement(By.id("LOGINNAME__UMLOGIN__0")).sendKeys("Admin902");
        driver.findElement(By.id("COMMUNITY_CODE__UM_LOGIN__0")).sendKeys("ROBOT001");
        driver.findElement(By.id("buttonLogin")).click();
        driver.findElement(By.id("LOGIN_PASSWORD__UM_LOGIN__0")).sendKeys("Nethris005");
        driver.findElement(By.id("buttonLogin")).click();
        driver.findElement(By.id("MIDDLE_SIN__UM_USER__0")).sendKeys("123");
        driver.findElement(By.id("LOGIN_ANSWER__UM_LOGIN_QUESTION__0")).sendKeys("nethris");
        driver.findElement(By.id("btnSave")).click();
    }

    private static void getDriver(String browserType) {
        browserType = browserType.trim().toLowerCase();
        switch (browserType) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "C:/AUTOMATION/Web/drivers/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "C:/AUTOMATION/Web/drivers/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "ie":
                System.setProperty("webdriver.ie.driver", "C:/AUTOMATION/Web/drivers/IEDriverServer_32.exe");
                driver = new InternetExplorerDriver();
                break;
        }
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();
    }
}

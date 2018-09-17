package com.pegasie.base;

import com.pegasie.util.ExcelUtil;
import com.pegasie.util.StringUtil;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Text;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class TestBase {

    @FindBy(how = How.XPATH, using = "//body")
    WebElement containerHeader;

    private static Logger logger = LogManager.getLogger();
    public static WebDriver driver;
    private boolean isRemoteTest = Boolean.parseBoolean(System.getProperty("IsRemoteTest"));
    private String driverPath = System.getProperty("DriverPath");
    private static String screenshotPath = System.getProperty("ScreenshotPath");
    private static String screenshotFormat = System.getProperty("ScreenshotFormat");
    private String seleniumGridUrl = System.getProperty("SeleniumGridUrl");
    private String DownLoadFolder = System.getProperty("DownloadFolder");
    private File downloadFolder = new File(DownLoadFolder);

    public boolean waitPageReady() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        System.out.println("<body class=" + containerHeader.getAttribute("class"));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(containerHeader,"class", "loading")));
        System.out.println("<body class=" + containerHeader.getAttribute("class"));
        return containerHeader.getAttribute("class").equals("");
    }

    public void saisirType(String browserType) {
        if (driver != null) {
            logger.warn("Trying to open a new browser without closing the previous one.");
            driver.quit();
        }
        browserType = browserType.toLowerCase();
        System.setProperty("BrowserType", browserType);
        String version = null;
        if (isRemoteTest) {
            version = System.getProperty("VERSION");
        }
        switch (browserType) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                FirefoxProfile firefoxProfile = new FirefoxProfile();
                firefoxProfile.setPreference("browser.download.folderList", 2);
                firefoxProfile.setPreference("browser.download.dir", downloadFolder.getAbsolutePath());
                firefoxProfile.setPreference("browser.download.manager.alertOnEXEOpen", false);
                firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                        "application/msword, application/csv, application/ris, text/csv, image/png, application/pdf, text/html, text/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream");
                firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
                firefoxProfile.setPreference("browser.download.manager.focusWhenStarting", false);
                firefoxProfile.setPreference("browser.download.useDownloadDir", true);
                firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
                firefoxProfile.setPreference("browser.download.manager.alertOnEXEOpen", false);
                firefoxProfile.setPreference("browser.download.manager.closeWhenDone", true);
                firefoxProfile.setPreference("browser.download.manager.showAlertOnComplete", false);
                firefoxProfile.setPreference("browser.download.manager.useWindow", false);
                firefoxProfile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
                firefoxProfile.setPreference("pdfjs.disabled", true);
                firefoxProfile.setPreference("network.proxy.type", 0);
                firefoxOptions.setProfile(firefoxProfile);
                if (isRemoteTest) {
                    // Remote Test Config
                    try {
                        firefoxOptions.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
                        firefoxOptions.setCapability(CapabilityType.VERSION, version);
                        driver = new RemoteWebDriver(new URL(seleniumGridUrl), firefoxOptions);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Local Test Config
                    System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
                    driver = new FirefoxDriver(firefoxOptions);
                }
                driver.manage().deleteAllCookies();
                driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
                break;
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", downloadFolder.getAbsolutePath());
                chromeOptions.setExperimentalOption("prefs", chromePrefs);
                if (isRemoteTest) {
                    // Remote Test Config
                    chromeOptions.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
                    chromeOptions.setCapability(CapabilityType.VERSION, version);
                    try {
                        driver = new RemoteWebDriver(new URL(seleniumGridUrl), chromeOptions);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Local Test Config
                    System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
                    driver = new ChromeDriver(chromeOptions);
                }
                driver.manage().deleteAllCookies();
                driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
                break;
            case "edge":
                if (isRemoteTest) {
                    // Remote Test Config
                    try {
                        DesiredCapabilities desiredCapabilities = DesiredCapabilities.edge();
                        desiredCapabilities.setBrowserName("MicrosoftEdge");
                        desiredCapabilities.setPlatform(Platform.WIN10);
                        driver = new RemoteWebDriver(new URL(seleniumGridUrl), desiredCapabilities);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Local Test Config
                    System.setProperty("webdriver.edge.driver", driverPath + "MicrosoftWebDriver.exe");
                    driver = new EdgeDriver();
                    driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
                }
                break;
            case "ie":
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                ieOptions.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                ieOptions.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
                ieOptions.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
                ieOptions.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, UnexpectedAlertBehaviour.DISMISS);
                ieOptions.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "about:blank");
                if (isRemoteTest) {
                    // Remote Test Config
                    try {
                        ieOptions.setCapability(CapabilityType.BROWSER_NAME, BrowserType.IE);
                        ieOptions.setCapability(CapabilityType.VERSION, version);
                        Proxy p = new Proxy();
                        p.setProxyType(Proxy.ProxyType.DIRECT);
                        ieOptions.setProxy(p);
                        ieOptions.usePerProcessProxy();
                        driver = new RemoteWebDriver(new URL(seleniumGridUrl), ieOptions);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Local Test Config
                    System.setProperty("webdriver.ie.driver", driverPath + "IEDriverServer_32.exe");
                    driver = new InternetExplorerDriver(ieOptions);
                }
                driver.manage().timeouts().implicitlyWait(90000, TimeUnit.MILLISECONDS);
                break;
            default:
                System.out.println("We don't support this " + browserType);
        }

        driver.manage().window().maximize();
        if (!downloadFolder.exists()) {
            try {
                downloadFolder.mkdir();
                String temp = downloadFolder.toString();
                Files.createDirectory(Paths.get(downloadFolder.toString() + "/archive"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void prendreCaptureEcran(String pageName) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss"); // add S if you need milliseconds
        String fileName = screenshotPath + pageName + "_" + df.format(new Date()) + screenshotFormat;
        try {
            FileUtils.copyFile(src, new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
//        driver.close();
        //driver.quit();
        driver = null;
    }

    public void cliquerLien(String linkText) throws InterruptedException {
        waitPageReady();
        linkText = linkText.trim();
        WebElement targetElement = null;
        boolean isElementFound = false;
        WebDriverWait wait = new WebDriverWait(driver, 3);
//        WebElement targetDiv = driver.findElement(By.id("divApplicationContent"));
//        List<WebElement> targetElements = targetDiv.findElements(By.linkText(linkText));
        List<WebElement> targetElements = driver.findElements(By.linkText(linkText));
        //List<WebElement> targetElements = driver.findElements(By.xpath("//a"));
        if (!targetElements.isEmpty()) {
            targetElement = targetElements.get(0);
            isElementFound = true;
        }
        if (isElementFound) {
            wait.until(ExpectedConditions.elementToBeClickable(targetElement));
            targetElement.click();
        } else {
            throw new NoSuchElementException("Link Web element with text: " + linkText + " doesn't exist.");
        }
    }

    public void cliquerBouton(String boutonText) {
        boutonText = boutonText.trim();
        WebElement targetElement = null;
        boolean isElementFound = false;
        WebDriverWait wait = new WebDriverWait(driver, 3);
//        WebElement targetDiv = driver.findElement(By.id("divApplicationContent"));
//        List<WebElement> targetElements = targetDiv.findElements(By.linkText(boutonText));
          //List<WebElement> targetElements = driver.findElements(By.linkText(boutonText));
        List<WebElement> targetElements = driver.findElements(By.xpath("//input[@type='submit']"));
        if (!targetElements.isEmpty()) {
            targetElement = targetElements.get(0);
            isElementFound = true;
        }
        if (!isElementFound) {
            //targetElements = targetDiv.findElements(By.xpath("//button"));
            targetElements = driver.findElements(By.xpath("//button"));
            for (WebElement bouton : targetElements) {
                if (bouton.getText().contains(boutonText)) {
                    targetElement = bouton;
                    isElementFound = true;
                    break;
                }
            }
        }
        if (isElementFound) {
            wait.until(ExpectedConditions.elementToBeClickable(targetElement));
            targetElement.click();
        } else {
            throw new NoSuchElementException("Web element with text: " + boutonText + " doesn't exist.");
        }
    }

    public void ouvrirPDF() throws InterruptedException {
        Thread.sleep(2000);
        File[] files = downloadFolder.listFiles();
        Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
        File file = files[0];
        if (file.getName().contains(".pdf")) {
            ((JavascriptExecutor) driver).executeScript("window.open('about:blank', '-blank')");
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            if (System.getProperty("BrowserType").equals("firefox")) {
                driver.get("file:///" + file.getAbsolutePath());
            } else {
                driver.get(file.getAbsolutePath());
            }
            Thread.sleep(2000);
            prendreCaptureEcran("pdf");
            driver.close();
            driver.switchTo().window(tabs.get(0));
        }
    }

    public boolean verifierDocument(String expectContent) throws InterruptedException, IOException, Docx4JException, JAXBException, OpenXML4JException, XmlException {
        String actualContent = null;
        boolean result = false;
        String[] contents = null;
        if (expectContent.contains(" ; ")) {
            expectContent.split(" ; ");
        } else {
            contents = new String[1];
            contents[0] = expectContent;
        }
        Thread.sleep(2000);

        File[] files = downloadFolder.listFiles();
        Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
        File file = files[0];
        if (file.getName().contains(".pdf")) {
            PDDocument pdfFile = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            actualContent = pdfStripper.getText(pdfFile);
            pdfFile.close();
        } else if (file.getName().contains(".docx")) {
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(file);
            MainDocumentPart mainDocumentPart = wordMLPackage.getMainDocumentPart();
            String textNodesXPath = "//w:t";
            List<Object> textNodes = mainDocumentPart.getJAXBNodesViaXPath(textNodesXPath, true);
            for (Object obj : textNodes) {
                Text text = (Text) ((JAXBElement) obj).getValue();
                actualContent += text.getValue();
            }
        } else if (file.getName().contains(".xlsx")) {
            actualContent = ExcelUtil.getAllContentFromExcel(file);
        } else if (file.getName().contains(".txt")) {
            actualContent = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        }
        if (actualContent != null && !actualContent.isEmpty()) {
            for (String content : contents) {
                result = actualContent.contains(content.trim());
                if (!result) {
                    break;
                }
            }
        } else {
            result = false;
        }
        Path source = Paths.get(file.getAbsoluteFile().toString());
        Path target = Paths.get(downloadFolder.getAbsolutePath() + "/archive/" + file.getName());
        CopyOption copyOption = StandardCopyOption.REPLACE_EXISTING;
        Files.move(source, target, copyOption);
        return result;
    }

    public void accepterAlerte() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
    }

    public void annulerAlerte() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
        wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
    }

    /**
     * This method provides availability to tester to hold the browser for waiting the back-end process.
     * In some test scenarios, we modify the data from front-end, then verify the history of modification.
     * However it takes couple seconds for the back-end to finish the whole process, then we can verify it later.
     *
     * @param seconds An integer value (time unit of second) to hold the browser.
     * @since 2018-07-31
     */
    public void attendre(String seconds) {
        int waitingTime = StringUtil.convertToInt(seconds) * 1000;
        try {
            Thread.sleep(waitingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

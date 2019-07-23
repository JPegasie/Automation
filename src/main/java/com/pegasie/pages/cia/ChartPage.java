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
//    @FindBy(how = How.XPATH, using = "/html/body/div[2]/div[1]/div[2]/div[1]/div[1]/label[1]")
    @FindBy(how = How.XPATH, using = "//*[@id=\"1\"]//div[1]/label[1]")
        WebElement butLevelView;
//    @FindBy(how = How.XPATH, using = "/html/body/div[2]/div/div[2]/div[1]/div[1]/label[2]")
    @FindBy(how = How.XPATH, using = "//*[@id=\"1\"]//div[1]/label[2]")
        WebElement butFolderView;
    @FindBy(how = How.XPATH, using = "/html/body/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/span[14]/a")
    WebElement labSelectLevel4;
    @FindBy(how = How.ID, using = "affectedRequirementslabel")
    WebElement lblAffectedFeaturesCnt2;
    @FindBy(how = How.XPATH, using = "//*[@id=\"scopeSummary\"]/div[1]/div/label[1]")
    WebElement butDEFUALT;
    @FindBy(how = How.XPATH, using = "//*[@id=\"scopeSummary\"]/div[1]/div/label[2]")
    WebElement butSTD;




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
//            releaseName.click();
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

    public boolean selectDEFAULT() {
        boolean result = false;
        try {
//            waitPageReady();
            butDEFUALT.click();
            waitPageReady();
            result = true;
        } catch (Exception ex) {
            System.out.println("<<" + ex.getLocalizedMessage() + ">>");
            result = false;
        } finally {
            return result;
        }
    }

    public boolean selectSTD() {
        boolean result = false;
        try {
//            waitPageReady();
            butSTD.click();
            waitPageReady();
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
//
//    public boolean verifyAffectedFeaturesCnt(String lblCount) {
//        boolean result = false;
//        try {
//            waitPageReady();
//            if (lblAffectedFeaturesCnt.getText().contains(lblCount)) {
//                result = true;
//            }
//        } catch (NoSuchElementException ex) {
//            System.out.println("<<" + ex.getLocalizedMessage() + ">>");
//        } finally {
//            return result;
//        }
//    }
//
//    public boolean verifyRequirement(String requirem) {
//        boolean result = false;
//        try {
//            waitPageReady();
//            System.out.println(lblWheel.getAttribute("id") + " ATTRIBUTE");
//            System.out.println(lblWheel.findElements(By.tagName("text")).size() + " SIZE of text");
//            List<WebElement> reqs = lblWheel.findElements(By.tagName("text"));
//            for (WebElement req : reqs) {
//                //System.out.println(req.getText());
//                // System.out.println(req.getCssValue("background-color"));
//                if (req.getText().contains(requirem)) {
//
//                    result = true;
//                    break;
//                }
//            }
//        } catch (NoSuchElementException ex) {
//            System.out.println("<<" + ex.getLocalizedMessage() + ">>");
//        } finally {
//            return result;
//        }
//    }
//
//
//    public boolean verifyRequirementNotContains(String requirem) {
//        String sFunc = "verifyRequirementNotContains";
//        boolean result = true;
//        try {
//            waitPageReady();
//            System.out.println(lblWheel.getAttribute("id") + " ATTRIBUTE");
//            System.out.println(lblWheel.findElements(By.tagName("text")).size() + " SIZE of text");
//            List<WebElement> reqs = lblWheel.findElements(By.tagName("text"));
//            for (WebElement req : reqs) {
//                //System.out.println(req.getText());
//                if (req.getText().contains(requirem)) {
//                    logger.info("search requirement is:" + requirem);
//                    logger.info("Actual requirement is:" + req.getText());
//                    logger.warn("Function name is:" + sFunc + "--Failed");
//                    result = false;
//                    break;
//                }
//            }
//        } catch (NoSuchElementException ex) {
//            System.out.println("<<" + ex.getLocalizedMessage() + ">>");
//            logger.error(ex.getMessage());
//        } finally {
//            return result;
//        }
//    }
//
//    public static String getReqNameListFromPageSource() {
//        String sFunc = "getReqNameListFromPageSource";
//        System.out.println("Function:" + sFunc + "---Started");
//        String innerHTML="";
//        //List<String> lResult = new ArrayList<>();
//        try {
//
//            WebElement svg = driver.findElement(By.tagName("svg"));
//            innerHTML = svg.getAttribute("innerHTML");
//            //logger.info(innerHTML);
//            //lResult=XMLUtil.getListFromXML(innerHTML,"svg");
//            System.out.println("Function:" + sFunc + "---Ended");
//
//        }
//        catch (Exception e) {
//            logger.error(e.getMessage());
//        }
//
//        finally {
//            return innerHTML;
//        }
//    }
//
//    public static boolean verifyReqEnd2End(String sParam) {
//        String sFunc = "verifyReqEnd2End";
//        System.out.println("Function:" + sFunc + "---Started");
//        boolean bResult = false;
//        String sLayer = "";
//        String sRelease = "";
//        String sHtml="";
//        try {
//            String [] sParamList = sParam.split(";;");
//            sLayer = sParamList[0];
//            sRelease = sParamList[1];
//
//
//            //get Actual requirement list from UI
//            sHtml = getReqNameListFromPageSource();
//
//            List<String> lResult=XMLUtil.getListFromXML(sHtml,"g");
//
//            //List<String[]> lResult=XMLUtil.getListFromXML(sHtml,"g");
//
//            System.out.println("The list size is:"+lResult.size());
//
//            //Get Expected result from ALM DB
//            List<TestRequirement> arrReq = SQLSrvUtil.getReqDSFromALMDBDataModelDataModel("12.53",sLayer, sRelease);
//
//
//            if (lResult.size()==arrReq.size()) {
//                Collections.sort(lResult);
//
//                List<String> testRequirementList= new ArrayList<String>();
//                //Collections.sort(arrReq);
//                logger.info("requirement from DB");
//                for (TestRequirement testRequirement: arrReq) {
//                    System.out.println(testRequirement.requirementName);
//                    logger.info(testRequirement.requirementName);
//                    testRequirementList.add(testRequirement.requirementName);
//
//                }
//
//                Collections.sort(testRequirementList);
//
//
//
//                bResult = true;
//                int iMatched=0;
//                for (int iCount =0; iCount<lResult.size(); iCount++) {
//                    if (! lResult.get(iCount).equalsIgnoreCase(arrReq.get(iCount).requirementName)) {
//                        bResult = false;
//                        logger.info("Actual Requirement is:" + lResult.get(iCount));
//                        logger.info("Expected requirement is:" + arrReq.get(iCount).requirementName);
//                        logger.warn("Function name is:" + sFunc + "--Failed");
//
//
//
//                        break;
//                    }
//                    else {
//                        iMatched = iMatched+1;
//                    }
//                }
//
//                if (iMatched==lResult.size()) {
//                    bResult = true;
//                }
//                else {
//                    bResult = false;
//                }
//
//            }
//            else{
//                bResult = false;
//                logger.info("Actual Total number of Requirement is:" + lResult.size());
//                logger.info("Expected Total number of requirement is:" + arrReq.size());
//                logger.warn("Function name is:" + sFunc + "--Failed");
//
//
//            }
//
//
//        }
//        catch (Exception e) {
//            System.out.println("<<" + e.getLocalizedMessage() + ">>");
//            logger.error(e.getMessage());
//            //logger.info("The requirement total number is:" + RequirementCount);
//            logger.warn("Function name is:" + sFunc + "--Failed");
//            bResult = false;
//        }
//
//
//        finally {
//            return bResult;
//        }
//    }
//
//    public static boolean verifyAffectedFeature (String sParam) {
//        String sFunc = "verifyReqEnd2EndForAffectedFeature";
//        System.out.println("Function:" + sFunc + "---Started");
//        boolean bResult = false;
//        String sTotalLayer = "";
//        String sRelease = "";
//        String sHtml="";
//        try {
//            String [] sParamList = sParam.split(";;");
//            sTotalLayer  = sParamList[0];
//            sRelease = sParamList[1];
//
//            Thread.sleep(5000);
//            //wait for wheel not working
//            //WebDriverWait wait = new WebDriverWait(driver, 40);
//            //wait.until(ExpectedConditions.not(By.xpath("//*[@id=\\'testSetModal\\']"),"span aria-hidden",null));
//            //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\'testSetModal\']")));
//            String sActualFeatureList = lblAffectedFeaturesCnt.getText();
//
//            //get Actual requirement list from UI
//            //System.out.println(sActualFeature);
//            String [] sActualFeatureListA = sActualFeatureList.split(":");
//            String sActualFeature = sActualFeatureListA[1].trim();
//            System.out.println(sActualFeature);
//
//
//
//            int iActCnt =0;
//            //Get Expected result from ALM DB
//            int iActLayer = 3;
//
//            //iActCnt = SQLSrvUtil.getReqCntFromALMDB("12.53",iActLayer, sRelease);
//            //List<TestRequirement> arrReq = SQLSrvUtil.getReqDSFromALMDBDataModelDataModel("12.53",sLayer, sRelease);
//            for (int iLayer = 1; iLayer<=Integer.valueOf(sTotalLayer); iLayer++) {
//                iActCnt = iActCnt + SQLSrvUtil.getReqCntFromALMDB("12.53",iActLayer, sRelease);
//                //System.out.println("The layer:" +iLayer+" requirement count is:"+iActCnt);
//                iActLayer = iActLayer +3;
//            }
//
//            //System.out.println("Total Affected Feature is:"+iActCnt);
//
//            if (iActCnt==Integer.valueOf(sActualFeature)) {
//                bResult = true;
//                logger.info("Actual Requirement Count is:" + sActualFeature);
//                logger.info("Expected requirement Count is:" + iActCnt);
//                logger.info("Function name is:" + sFunc + "--Passed");
//            }
//            else {
//                bResult = false;
//                logger.info("Actual Requirement Count is:" + sActualFeature);
//                logger.info("Expected requirement Count is:" + iActCnt);
//                logger.warn("Function name is:" + sFunc + "--Failed");
//            }
//
//
//        }
//        catch (Exception e) {
//            System.out.println("<<" + e.getLocalizedMessage() + ">>");
//            logger.error(e.getMessage());
//            //logger.info("The requirement total number is:" + RequirementCount);
//            logger.warn("Function name is:" + sFunc + "--Failed");
//            bResult = false;
//        }
//
//
//        finally {
//            return bResult;
//        }
//    }
//    public  boolean __verifyRequirementsColor(String name, String color, String shouldHaveAttrib, String mixedReq) {
//        boolean result = true;
//        String sFunc = "verifyRequirementsColor";
//        System.out.println("Function:" + sFunc + "---Started");
//        try {
//            waitPageReady();
//
//            WebElement svg = driver.findElement(By.tagName("svg"));
//            List<WebElement> AllRequirement = svg.findElements(By.className("group"));
//
//            for(WebElement w : AllRequirement){
//                WebElement wb = w.findElement(By.tagName("text"));
//                //System.out.println(wb.getText());
//                if (wb.getText().startsWith(name)) {
//                    String s= wb.getAttribute("fill");
//                    if (s !=null) {
//                        if(Boolean.parseBoolean(mixedReq)) {
//
//                        }else {
//                            if(!Boolean.parseBoolean(shouldHaveAttrib)) {
//                                result = false;
//                                logger.error("The " + wb.getText() + " requirement's color is not " + color);
//                                break;
//                            }else {
//                                // nothing to do
//                            }
//                        }
//                    }else {
//                        if(!Boolean.parseBoolean(shouldHaveAttrib)) {
//                            // nothing to do
//                        }else {
//                            result = false;
//                            logger.error("The " + wb.getText() + " requirement's color is not " + color);
//                            break;
//                        }
//
//                    }
//                }
//            }
//        }catch (Exception e) {
//            result = false;
//        }
//        System.out.println("Function " + sFunc + " returns " + result);
//        return result;
//    }
//
//    //CRISTIAN
//    public  boolean verifyRequirementsColor(String name, String colorCode, String shouldHaveCode) {
//
//        boolean result = true;
//        String color="";
//        if (colorCode.equalsIgnoreCase("#35a3de"))
//            color = "BLUE";
//        else if (colorCode.equalsIgnoreCase("#ff0000"))
//            color = "RED";
//        else
//            color = "BLACK";
//
//        String sFunc = "verifyRequirementsColor";
//        System.out.println("Function:" + sFunc + "---Started");
//        try {
//            waitPageReady();
//
//            WebElement svg = driver.findElement(By.tagName("svg"));
//            List<WebElement> AllRequirement = svg.findElements(By.className("group"));
//
//            for(WebElement w : AllRequirement){
//                WebElement wb = w.findElement(By.tagName("text"));
//                //System.out.println(wb.getText());
//                if (wb.getText().startsWith(name)) {
//                    String s= wb.getAttribute("fill");
//                    if (s !=null) {
//                        if(Boolean.parseBoolean(shouldHaveCode.trim()))
//                            if (s.equalsIgnoreCase(colorCode)) {
//                                //ok
//                                logger.debug("The " + wb.getText() + " requirement's color is " + color);
//                            }else {
//                                result = false;
//                                logger.error("The " + wb.getText() + " requirement's color is not " + color);
//                                //break;
//                            }
//                        else {
//                            if (color == "BLACK") {
//                                result = false;
//                                logger.error("The " + wb.getText() + " requirement's color is not " + color);
//                            }
//                        }
//
//                    }else {
//
//                        if(Boolean.parseBoolean(shouldHaveCode.trim())) {
//                            result = false;
//                            logger.error("The " + wb.getText() + " requirement's color is not " + color );
//                            //break;
//                        }else
//                            logger.debug("The " + wb.getText() + " requirement's color is " + color);
//                    }
//                }
//            }
//        }catch (Exception e) {
//            result = false;
//        }
//        System.out.println("Function " + sFunc + " returns " + result + " for " +name);
//        //CRISTIAN
//        // if(!result)
//        /*
//         * if(Boolean.parseBoolean(System.getProperty("forcetakeScreenshot")))
//         * TestBase.prendreCaptureEcran(name + "_" + color);
//         */
//
//        return result;
//    }
//
//    //CRISTIAN
//    public boolean validateReqCountAndNames( String nbr, String names) {
//        String sFunc = "validateReqCountAndNames";
//        System.out.println("Function:" + sFunc + "---Started");
//        boolean result = false;
//        int RequirementCount = 0;
//        String[] s  =names.split(",");
//
//        try {
//            waitPageReady();
//            WebElement svg = driver.findElement(By.tagName("svg"));
//            List<WebElement> AllRequirement = svg.findElements(By.className("group"));
//            RequirementCount = AllRequirement.size();
//            if (RequirementCount != Integer.parseInt(nbr) )
//                return false;
//            else
//                result = true;
//            if (Integer.parseInt(nbr) > 0) {
//                result = false;
//                for (WebElement g : AllRequirement) {
//                    String actual = g.findElement(By.tagName("text")).getText();
//                    for(String _s : s) {
//                        if (_s.equalsIgnoreCase(actual)) {
//                            result = true;
//                            break;
//                        }
//                        if(!result) {
//                            System.out.println("Function:" + sFunc + " returned FALSE");
//                            return false;
//                        }
//                    }
//                }
//            }
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            return false;
//        }
//        System.out.println("Function:" + sFunc + " returned " + result);
//        //CRISTIAN
//        /*
//         * if(!result) if(Boolean.parseBoolean(System.getProperty("takeScreenshot")))
//         * TestBase.prendreCaptureEcran("ReqCountAndName" + "_" + nbr);
//         */
//        return result;
//
//    }
//    public int getActualReqCntFromWheel() {
//        String sFunc = "getActualReqCntFromWheel";
//        System.out.println("Function:" + sFunc + "---Started");
//        int RequirementCount = 0;
//        try {
//            //Try DB connection:
//            // String userName = "sa";
//            //String password = "Pegasie2008!";
//
//
//            waitPageReady();
//
//
//            WebElement svg = driver.findElement(By.tagName("svg"));
//            // String innerHTML = svg.getAttribute("innerHTML");
//
//			/* String innerHTML = svg.getAttribute("innerHTML");
//            logger.info(innerHTML);
//
//
//            String pageSource = driver.getPageSource();
//            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//            InputSource is = new InputSource();
//            is.setCharacterStream(new StringReader(innerHTML));
//
//            Document doc = db.parse(is);
//            //NodeList nodes = doc.getElementsByTagName("svg");
//
//            //Document doc = dBuilder.parse(new InputSource(new StringReader(temp)));
//            doc.getDocumentElement().normalize();
//
//            NodeList nList;
//            nList = doc.getElementsByTagName("svg");
//            //System.out.println("svg " + nList.toString());
//            System.out.println("svg " + nList.item(0).getAttributes());*/
//
//
//            List<WebElement> AllRequirement = svg.findElements(By.className("group"));
//            RequirementCount = AllRequirement.size();
//            System.out.println("Total number of requirement is:" + RequirementCount);
//            logger.info("The requirement total number is:" + RequirementCount);
//
//            String arrText = "";
//            for (int iCnt = 1; iCnt <= AllRequirement.size(); iCnt++) {
//
//                arrText = driver.findElement(By.cssSelector("#chart_placeholder > svg > g > g:nth-child(" + iCnt + ") > text")).getText();
//                System.out.println("The requirement name: The number of " + iCnt + " requirement is:" + arrText);
//                logger.info("The requirement name: The number of " + iCnt + " requirement is:" + arrText);
//            }
//
//
//            int iCnt2 = 0;
//            for (WebElement g : AllRequirement) {
//                String actual = g.findElement(By.tagName("text")).getText();
//                if (actual.isEmpty()) {
//                    System.out.println("Empty");
//                    //g.click();
//                    logger.warn("empty string!");
//                    Actions act = new Actions(driver);
//                    // act.dragAndDropBy(lblWheel, 0, 20).perform();
//                }
//                System.out.println("The second method requirement: The number of " + iCnt2 + " req name is:" + actual);
//                logger.info("The second method requirement: The number of " + iCnt2 + " req name is:" + actual);
//                iCnt2 = iCnt2 + 1;
//            }
//
//
//
//            logger.warn("Function name is:" + sFunc + "--Passed");
//
//        } catch (NoSuchElementException ex) {
//            System.out.println("<<" + ex.getLocalizedMessage() + ">>");
//            logger.error(ex.getMessage());
//            RequirementCount = -1;
//            logger.info("The requirement total number is:" + RequirementCount);
//            logger.warn("Function name is:" + sFunc + "--Failed");
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error(e.getMessage());
//        } finally {
//            return RequirementCount;
//        }
//    }
//
//    public boolean verifyInitialLinkedTests (String sRelease) {
//        String sFunc = "verifyInitialLinkedTests";
//        System.out.println("Function:" + sFunc + "---Started");
//        boolean bResult = false;
//        String sActualResult="";
//        String sTbl;
//
//        try {
//
//
//            Thread.sleep(5000);
//            //get actual result from web table
//            sTbl = "InitialScope";
//            sActualResult = getValueFromWebTables(sTbl, 2,1);
//            System.out.println("The actual result from UI is:"+sActualResult);
//
//            int iExpResult =0;
//            //Get Expected result from ALM DB
//            iExpResult = SQLSrvUtil.getLinkedTestsCntFromALMDB("12.53", sRelease);
//            //            System.out.println("The expected result is:"+String.valueOf(iExpResult));
//
//
//
//            if (iExpResult==Integer.valueOf(sActualResult)) {
//                bResult = true;
//                logger.info("Actual Linked test Count is:" + sActualResult);
//                logger.info("Expected Linked test Count is:" + iExpResult);
//                logger.info("Function name is:" + sFunc + "--Passed");
//            }
//            else {
//                bResult = false;
//                logger.info("Actual Linked test Count is:" + sActualResult);
//                logger.info("Expected requirement Count is:" + iExpResult);
//                logger.warn("Function name is:" + sFunc + "--Failed");
//            }
//
//
//        }
//        catch (Exception e) {
//            System.out.println("<<" + e.getLocalizedMessage() + ">>");
//            logger.error(e.getMessage());
//
//            logger.warn("Function name is:" + sFunc + "--Failed");
//            bResult = false;
//        }
//
//
//        finally {
//            return bResult;
//        }
//    }
//    private String getValueFromWebTables (String sTblName, int rowIdx, int colIdx) {
//        String sFunc = "getValueFromWebTables";
//        System.out.println("Function:" + sFunc + "---Started");
//        String sResult = "";
//
//        try {
//
//
//            //get specified table
//            WebElement targetTbl=null ;   //define a null WebElement
//
//            switch (sTblName.toUpperCase()) {
//                case "OPTIMIZEDSCOPE":   targetTbl = tblContainer.findElements(By.tagName("table")).get(0);
//                    break;
//                case "INITIALSCOPE":     targetTbl = tblContainer.findElements(By.tagName("table")).get(1);
//                    break;
//                case "SAVINGS":          targetTbl = tblContainer.findElements(By.tagName("table")).get(2);
//                    break;
//
//            }
//            //get specified row
//            WebElement targetRow = targetTbl.findElements(By.tagName("tr")).get(rowIdx);
//
//            //get specified cell
//            WebElement targetCell = targetRow.findElements(By.tagName("td")).get(colIdx);
//
//            //get specified value-- Not necessary to get i
//            sResult = targetCell.getText();
//
//
//            //            System.out.println(sResult);
//
//
//
//        }
//        catch (Exception e) {
//            System.out.println("<<" + e.getLocalizedMessage() + ">>");
//            logger.error(e.getMessage());
//            //logger.info("The requirement total number is:" + RequirementCount);
//            logger.warn("Function name is:" + sFunc + "--Failed");
//
//        }
//
//
//        finally {
//            return sResult;
//        }
//    }
//
//
//    public static boolean createALMRequirements (String sParam) {
//        String sFunc = "createALMRequirements";
//        System.out.println("Function:" + sFunc + "---Started");
//        boolean bResult = false;
//
//        //bResult = ALMUtil.getALMUploadString("1;;1;;1;;1;;2");
//
//        return bResult;
//
//    }

}

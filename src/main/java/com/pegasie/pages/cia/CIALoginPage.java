package com.pegasie.pages.cia;

import com.pegasie.pages.TemplatePage;
import com.pegasie.util.WebElementUtil;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CIALoginPage extends TemplatePage {

    @FindBy(how = How.ID, using = "inputSite")
    WebElement txtSite;

    @FindBy(how = How.ID, using = "inputDomain")
    WebElement txtDomain;

    @FindBy(how = How.ID, using = "inputProject")
    WebElement txtProject;

    @FindBy(how = How.ID, using = "inputUsername")
    WebElement txtUserName;

    @FindBy(how = How.ID, using = "inputPassword")
    WebElement txtPassword;

    @FindBy(how = How.ID, using="globalMessage")
    WebElement lblGlobalMesssage;
    @FindBy(how = How.XPATH, using ="//*[@id=\'globalMessage\']/div")
    WebElement lblDetailError;

        //WebElement lblPageTitle;

//    public boolean verifyContentTitle (String title) {
//        System.out.println(lblPageTitle.getTagName());
//        //return lblPageTitle.getText().equalsIgnoreCase(title);
//        return true;
//    }

    public void enterALMSite(String url) {
        txtSite.clear();
        txtSite.sendKeys(url);
    }

    public void enterDomain(String domain) {
        txtDomain.clear();
        txtDomain.sendKeys(domain);
    }

    public void enterProject(String project) {
        txtProject.clear();
        txtProject.sendKeys(project);
    }

    public void enterUserName(String username) {
        txtUserName.clear();
        txtUserName.sendKeys(username);
    }

    public void enterPassword(String password) {
        txtPassword.clear();
        txtPassword.sendKeys(password);
    }


    //Frank @ 2018-10-11
    public boolean verifyLoginErrorMsg(String sErrorMsg) {
        String sFuncName = "verifyLoginErrorMsg";
        String sID = "filters";
        String sErr;
        System.out.println(sFuncName +" is started");
        Boolean bResult = false;
        String sExpected = "Refresh";
        try {
            bResult = WebElementUtil.isWebElementExist(lblGlobalMesssage);
            if  (bResult==true); {
                System.out.println(sErrorMsg);
                System.out.println(sErrorMsg);
                sErr = WebElementUtil.getElementText( lblDetailError );
                System.out.println(sErr );
                bResult = WebElementUtil.verifyMessage(lblDetailError, sErrorMsg);

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


    public boolean verifyGlobalMessage(String msg) {
        return msg.equalsIgnoreCase(lblGlobalMesssage.getText());
    }


}

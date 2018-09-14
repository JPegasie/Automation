package com.pegasie.pages.cia;

import com.pegasie.pages.TemplatePage;
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

        //WebElement lblPageTitle;

//    public boolean verifyContentTitle (String title) {
//        System.out.println(lblPageTitle.getTagName());
//        //return lblPageTitle.getText().equalsIgnoreCase(title);
//        return true;
//    }

    public void enterALMSite(String url) {
        txtSite.sendKeys(url);
    }

    public void enterDomain(String domain) {
        txtDomain.sendKeys(domain);
    }

    public void enterProject(String project) {
        txtProject.sendKeys(project);
    }

    public void enterUserName(String username) {
        txtUserName.sendKeys(username);
    }

    public void enterPassword(String password) {
        txtPassword.sendKeys(password);
    }


}

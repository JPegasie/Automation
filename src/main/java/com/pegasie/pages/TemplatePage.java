package com.pegasie.pages;

import com.pegasie.base.TestBase;
import com.pegasie.util.StringUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class TemplatePage extends TestBase {
    public TemplatePage() {
        PageFactory.initElements(driver, this);
    }

    // Accès rapide
    @FindBy(xpath = "//*[@id='Margin']/div")
    WebElement mainAccesRapide;
    @FindBy(id = "imgUnLockFav")
    WebElement imgUnLock;
    @FindBy(id = "imgLockFav")
    WebElement imgLock;
    @FindBy(id = "lnkEditFavorite")
    WebElement lnkEditFavorite;

    // Main menu
    @FindBy(id = "MainMenu")
    WebElement mainMenu;
    // First level menu
    @FindBy(id = "EntrepriseLogo")
    WebElement logo;
    @FindBy(id = "TAB__0")
    WebElement menuRH;
    @FindBy(id = "TAB__1")
    WebElement menuPaie;
    @FindBy(id = "TAB__2")
    WebElement menuRapports;
    @FindBy(id = "TAB__3")
    WebElement menuGrandLivre;
    @FindBy(id = "TAB__4")
    WebElement menuAdministration;
    @FindBy(className = "Icon Profil")
    WebElement iconProfil;
    @FindBy(className = "Icon Quit")
    WebElement iconQuit;

    // RH menu
    @FindBy(xpath = "//*[@id='TAB__0']/ul/li[1]/a")
    WebElement menuSelectionDesEmployes;
    @FindBy(xpath = "//*[@id='TAB__0']/ul/li[2]/a")
    WebElement menuEmbaucherUnEmploye;
    @FindBy(xpath = "//*[@id='TAB__0']/ul/li[3]/a")
    WebElement menuEmbaucheExpress;
    @FindBy(xpath = "//*[@id='TAB__0']/ul/li[4]/a")
    WebElement menuProduireUnReleveDeEmploi;
    @FindBy(xpath = "//*[@id='TAB__0']/ul/li[5]/a")
    WebElement menuProfilDeEmploi;
    @FindBy(xpath = "//*[@id='TAB__0']/ul/li[5]/ul/li[3]/a")
    WebElement menuParametresDePaie;
    @FindBy(xpath = "//*[@id='TAB__0']/ul/li[15]/a")
    WebElement menuPostesGroup;
    @FindBy(xpath = "//*[@id='TAB__0']/ul/li[15]/ul/li[2]/a")
    WebElement menuPostes;

    // Paie menu
    @FindBy(xpath = "//*[@id='TAB__1']/ul/li[1]")
    WebElement menuCalendrierDePaieCourant;
    @FindBy(xpath = "//*[@id='TAB__1']/ul/li[10]/a")
    WebElement menuOutils;
    @FindBy(xpath = "//*[@id='TAB__1']/ul/li[10]/ul/li[1]/a")
    WebElement menuSimulerUnePaie;

    // Todo Rapports menu

    // Todo Grand-livre menu

    // Todo Administration menu

    // Profil menu
    @FindBy(xpath = "//*[@id='OptionMenu']/li[1]/a")
    WebElement menuProfil;
    @FindBy(xpath = "//*[@id='OptionMenu']/li[1]/ul/li[1]/a")
    WebElement menuProfilUilisateur;

    // Aide menu
    @FindBy(xpath = "//*[@id='OptionMenu']/li[2]/a")
    WebElement menuAide;

    // Message section
    @FindBy(xpath = "//*[@id='sectionErrorID']/div[2]/table/tbody/tr/td[1]")
    WebElement message;
    @FindBy(id = "tdRule__CD_PST__COMMUNITY_POSITION__0")
    WebElement errorMessage;

    /**
     * The method verifies the message displayed on the web page.
     *
     * @param expectMessage The message you expect to be displayed.
     * @return True if expectMessage is displayed, otherwise return false.
     * @since 2018-07-26
     */
    public boolean verifierMessage(String expectMessage) {
        return message.getText().trim().equals(expectMessage.trim());
    }

    public boolean verifierErrorMessage(String expectMessage) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        String actualMessage = errorMessage.getText();
        return actualMessage.contains(expectMessage);
    }

    public void logOut() {
        iconQuit.click();
    }

    //<editor-fold desc="These are all the actions for switching page.">
    public void allerAccueilPage() {
        logo.click();
    }

    public void allerSelectionDesEmployesPage() {
        menuRH.click();
        menuSelectionDesEmployes.click();
    }

    public void allerCalendrierDePaieCourantPage() {
        menuPaie.click();
        menuCalendrierDePaieCourant.click();
    }

    public void allerParametresDePaiePage() {
        menuRH.click();
        menuProfilDeEmploi.click();
        menuParametresDePaie.click();
    }

    public void allerProfilUtilisateurPage() {
        iconProfil.click();
    }
    //</editor-fold>

    //<editor-fold desc="This section is for menu navigation.">
    public boolean selectionnerMainMenu(String menuList) {
        String[] menuItems = StringUtil.getStringArrayParameter(menuList);
        int numOfLinksToClick = menuItems.length;
        int numOfLinksClicked = 0;
        WebDriverWait wait = new WebDriverWait(driver, 3);
        List<WebElement> menuLevel1 = mainMenu.findElements(By.xpath("child::li/a"));
        List<WebElement> menuLevel2;
        List<WebElement> menuLevel3;
        for (WebElement m1 : menuLevel1) {
            wait.until(ExpectedConditions.visibilityOf(m1));
            if (m1.getText().contains(menuItems[0])) {
                m1.click();
                numOfLinksClicked++;
                menuLevel2 = m1.findElements(By.xpath("following-sibling::ul/li/a"));
                for (WebElement m2 : menuLevel2) {
                    wait.until(ExpectedConditions.visibilityOf(m2));
                    if (m2.getText().contains(menuItems[1])) {
                        m2.click();
                        numOfLinksClicked++;
                        if (menuItems.length > 2) {
                            menuLevel3 = m2.findElements(By.xpath("following-sibling::ul/li/a"));
                            for (WebElement m3 : menuLevel3) {
                                if (!m3.isDisplayed()) {
                                    m2.click();
                                }
                                if (m3.getText().contains(menuItems[2])) {
                                    m3.click();
                                    numOfLinksClicked++;
                                    break;
                                }
                            }
                        } else {
                            break;
                        }
                        break;
                    }
                }
                break;
            }
        }
        return numOfLinksToClick == numOfLinksClicked;
    }

    public void selectionnerIconMenu(String menuList) {
        String[] menuItems = StringUtil.getStringArrayParameter(menuList);
        WebElement targetMenu = null;
        List<WebElement> menuLevel1;
        switch (menuItems[0]) {
            case "Profil":
                targetMenu = menuProfil;
                //menuProfil.click();
                break;
            case "Aide":
                targetMenu = menuAide;
                //menuAide.click();
                break;
        }
        targetMenu.click();
        menuLevel1 = targetMenu.findElements(By.xpath("following-sibling::ul/li/a"));
        for (WebElement element : menuLevel1) {
            if (element.getText().contains(menuItems[1])) {
                element.click();
                break;
            }
        }
    }
    //</editor-fold>
}



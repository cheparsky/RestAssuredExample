package cheparsky.pages;

import cheparsky.cucsteps.SeleniumSteps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccesPage {

    public static WebDriver wd;

    @FindBy(xpath = "//h3[text()='Zgoda została zaakceptowana']")
    public static WebElement succespageInfoL;

    //dodac czy pojawil sie przycisk

    public static void setPageObject (WebDriver wd) {
        PageFactory.initElements(wd, new SuccesPage());
        SeleniumSteps.webElementsList.put("KOMUNIKAT_O_ZAAKCEPTOWANEJ_ZGODZIE",succespageInfoL);
    }

}

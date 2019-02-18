package cheparsky.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import cheparsky.cucsteps.SeleniumSteps;

public class RejectedPage {

    public static WebDriver wd;

    @FindBy(xpath = "//h3[text()='Zgoda została odrzucona']")
    public static WebElement failurepageInfoL;

    //dodac czy pojawil sie przycisk

    public static void setPageObject (WebDriver wd) {
        PageFactory.initElements(wd, new RejectedPage());
        SeleniumSteps.webElementsList.put("KOMUNIKAT_O_ODRZUCENIU_ZGODY",failurepageInfoL);
    }

}

package cheparsky.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import cheparsky.cucumberSteps.SeleniumSteps;

public class ErrorPage {

    @FindBy(xpath = "//div[text()='Przejściowy problem aplikacji']")
    public static WebElement errorpageTemporaryProblemM;

    @FindBy(xpath = "//li[text()='Dostęp do rachunku zabroniony. Twoja sesja musiała zostać zakończona.']")
    public static WebElement failurepageNoAccesToSenderAccountNumberM;

    @FindBy(xpath = "//div[text()='Przejściowy problem aplikacji']")
    public static WebElement failurepagePrzejsciowyProblemAplikacjiM;

    //<li>Dostęp do rachunku zabroniony. Twoja sesja musiała zostać zakończona.</li>

    //dodac czy pojawil sie przycisk

    public static void setPageObject (WebDriver wd) {
        PageFactory.initElements(wd, new ErrorPage());
        SeleniumSteps.webElementsList.put("KOMUNIKAT_O_PRZEJSCIOWYM_PROBLEMOE_APLIKACJI",errorpageTemporaryProblemM);
        SeleniumSteps.webElementsList.put("KOMUNIKAT_O_NIEDOSTEPNYM_RACHUNKU_ZLECENIODAWCY",failurepageNoAccesToSenderAccountNumberM);
    }
}
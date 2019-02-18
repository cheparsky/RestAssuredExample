package cheparsky.cucsteps;

import cheparsky.pages.AuthorizationPage;
import cheparsky.pages.LoginPage;
import cheparsky.psd2requests.ConsentRequest;
import cheparsky.utilities.MyDriver;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

public class SeleniumSteps {

    protected static WebDriver wd;
    protected static int waitTime = 5;
    protected static String browserName = "Chrome";
    protected static String pathToDriver = "src/test/resources/chromedriver.2.45.exe";
    public static Map<String, WebElement> webElementsList = new HashMap<String, WebElement>();

    @When("^przechodzimy na stronę autoryzacji$")
    public void setUpAndGoToUrl() {
        MyDriver.runWDriver(browserName, pathToDriver);
        MyDriver.setWebDriverWait(waitTime);
        wd = MyDriver.wd;
        MyDriver.setPageObjectAll(wd);

        wd.get(ConsentRequest.getAuthorizationRedirectUri());
    }

    @When("^logujemy się login: (.+), hasło: (.+)$")
    public static void actionOfLogIn(String login, String pass) {
        LoginPage.loginpageLogowanie(login, pass);
    }

    @When("^akceptujemy zgodę hasłem: (.+)$")
    public static void actionOfAcceptingScope(String pass) {
        AuthorizationPage.authorizationpageSuccessfulAuthorization(pass);
    }

    @When("^odrzucamy zgodę$")
    public static void actionOfRejectingScope() {
        AuthorizationPage.authorizationpageNegativeAuthorization();
    }


    @When("^wybieramy KONTEKST_NOWY$")
    public static void actionOfSelectingContext() {
        LoginPage.loginpageKontekstNowyB(AuthorizationPage.authorizationpageSenderAccountDD);
    }

    @When("^wybieramy jeden rachunek")
    public String selectionOfOneAccounts() {
        AuthorizationPage.authorizationpageListOfAccountsDD();
        AuthorizationPage.authorizationpageFirstElementOfListOfAccountsDD();
        AuthorizationPage.authorizationpageListOfAccountsDD();
        return "Wybór rachunków udał się";
    }
}



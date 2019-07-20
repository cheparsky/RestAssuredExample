package cheparsky.pages;

import cheparsky.utilities.MyDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import cheparsky.cucumberSteps.SeleniumSteps;

public class LoginPage {

    public static WebDriver wd;

    @FindBy(id = "login-id")
    public static WebElement loginpageLoginI;

    @FindBy(id = "submit-btn")
    public static WebElement loginpageSubmitB;

    @FindBy(id = "token-field")
    public static WebElement loginpageTokenI;

    @FindBy(id = "token")
    public static WebElement loginpageTokenRadioB;

    @FindBy(xpath = "//*[@id='profile-selector']/div/div[1]/ul/li[1]")
    public static WebElement loginpageKontekstNowyB;

    public static String iLogin;
    public static String iPass;

    // Definiujemy akcje, ktora odpowiada za wpaymentywanie loginu
    public static void loginpageLoginI (String login){
        MyDriver.InputData(loginpageLoginI,
                login,
                "Strona logowania: input 'Identyfikator' nie jest edytowalny.",
                "Strona logowania nie zawiera inputu 'Identyfikator'.");
    }

    // Definiujemy akcje, ktora odpowiada za wpaymentywanie hasla
    public static void loginpageTokenI (String pass){
        MyDriver.InputData(loginpageTokenI,
                pass,
                "Strona logowania: input 'Hasło' nie jest edytowalny.",
                "Strona logowania nie zawiera inputu 'Hasło'.");
    }

    // Definiujemy akcje, ktora odpowiada za wciskanie przycisku Dalej/Zaloguj
    public static void loginpageSubmitB (String succesMessage, String error1Message){
        MyDriver.ButtonTypical(loginpageSubmitB,
                            succesMessage,
                            error1Message,
                    "Strona logowania nie zawiera przycisku 'Zaloguj'.");
    }

    // Definiujemy funkcje, ktora odpowiada za logowanie - proces
    public static void loginpageLogowanie (String login, String pass){
        LoginPage.iLogin = login;
        LoginPage.iPass = pass;
        try{ MyDriver.wd.switchTo().frame("main"); } catch (Exception e){System.out.println(e.getStackTrace());}
        LoginPage.loginpageLoginI(login);
        LoginPage.loginpageSubmitB("Strona logowania: klikniecie w przycisk 'Dalej' udalo sie.", "Strona logowania: przycisk 'Dalej' jest nie klikalny.");
        LoginPage.loginpageTokenI(pass);
        LoginPage.loginpageSubmitB("Logowanie za pomocą konta: "+LoginPage.iLogin+", udalo się.", "Strona logowania: przycisk 'Zaloguj' jest nie klikalny.");
    }

    public static void loginpageKontekstNowyB (WebElement elementCheck){
        MyDriver.buttonElement(loginpageKontekstNowyB,
                elementCheck,
                "Logowanie za pomoca konta: "+LoginPage.iLogin+", udalo się.",
                "Strona logowania: przycisk 'zaloguj' jest nie klikalny.",
                "Strona logowania nie zawiera przycisku 'zaloguj'.");
    }


    // Definiujemy akcje, ktora odpowiada za dodanie Page Object Pattern
    public static void setPageObject (WebDriver wd) {
        PageFactory.initElements(wd, new LoginPage());
        SeleniumSteps.webElementsList.put("KONTEKST_NOWY",loginpageKontekstNowyB);
    }






}

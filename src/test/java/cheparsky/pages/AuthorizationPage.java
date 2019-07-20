package cheparsky.pages;

import cheparsky.utilities.MyDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import cheparsky.cucumberSteps.SeleniumSteps;

public class  AuthorizationPage {

    public static WebDriver wd;

    //ELEMENTS OF AUTORIZATION PAGE

    @FindBy(css = ".button.big")
    public static WebElement authorizationpageAutoryzujB;

    @FindBy(css = ".password-large")
    public static WebElement authorizationpageTokenI;

    @FindBy(css = ".button.big.pdl10")
    public static WebElement authorizationpageOdrzucB;

    @FindBy(css = ".style-select-header-ng4")
    public static WebElement authorizationpageSenderAccountDD;

    @FindBy(css = ".style-select-header-ng4.grouped-drop-right")
    public static WebElement authorizationpageListOfAccountAccountsDD;
//*[@id="cmn-dropdown-1"]/div

    @FindBy(xpath = "//*[@id='cmn-dropdown-2']/div/div[2]/ul/li[1]")
    public static WebElement authorizationpageFirstElementOfListOfAccountsDD;

    //ERROR MESSAGE

    @FindBy(xpath = "//li[text()='Na formularzu wystąpiły błędy']")
    public static WebElement authorizationpageTopErrorM;

    @FindBy(xpath = "//span[text()='Niepoprawna suma kontrolna numeru rachunku bankowego kontrahenta w formacie NRB.']")
    public static WebElement authorizationpageNoCorrectCheckSumContractorNrbM;

    @FindBy(xpath = "//span[text()='Niepoprawna suma kontrolna numeru rachunku bankowego organu podatkowego w formacie NRB.']")
    public static WebElement authorizationpageNoCorrectCheckSumTaxAuthorityNrbM;

    @FindBy(xpath = "//span[text()='Nieokreślony numer rachunku kontrahenta.']")
    public static WebElement authorizationpageNoRecipientAccountNumberM;

    @FindBy(xpath = "//span[text()='Nieokreślony numer rachunku organu podatkowego.']")
    public static WebElement authorizationpageNoTaxAuthorityAccountNumberM;

    @FindBy(xpath = "//span[text()='Nieokreślony numer rachunku zleceniodawcy.']")
    public static WebElement authorizationpageNoSenderAccountNumberM;

    @FindBy(xpath = "//span[text()='Kwota zlecenia powinna być wartością dodatnią.']")
    public static WebElement authorizationpageNoPositiveAmountM;

    @FindBy(xpath = "//span[text()='Tryb realizacji nie może być określony']")
    public static WebElement authorizationpageNoSpecifiedExecutionModeM;

    @FindBy(xpath = "//span[text()='Nieokreślona nazwa kontrahenta.']")
    public static WebElement authorizationpageNoSenderNameM;

    @FindBy(xpath = "//span[text()='Nieokreślony tytuł zlecenia.']")
    public static WebElement authorizationpageNoOrderNameM;

    @FindBy(xpath = "//span[text()='Niepoprawna suma kontrolna numeru rachunku bankowego kontrahenta w formacie IBAN.']")
    public static WebElement authorizationpageNoCorrectCheckSumContractorIBANM;

    @FindBy(xpath = "//span[text()='Brak możliwości realizacji Przelewu SEPA dla podanego rachunku IBAN.']")
    public static WebElement authorizationpageNoPossibilitySEPAForContractorIBANM;

    @FindBy(xpath = "//span[text()='Kod kraju w numerze rachunku kontrahenta niezgodny z kodem kraju w numerze SWIFT.']")
    public static WebElement authorizationpageNoCorrectCountryCodeOfContractorNumberWithSWIFTNumberM;

    @FindBy(xpath = "//span[text()='Kwota zlecenia powinna być wartością dodatnią.']")
    public static WebElement authorizationpageNotPositiveAmountM;

    public static void authorizationpageSuccessfulAuthorization(String pass){
        AuthorizationPage.authorizationpageSubmitB(authorizationpageTokenI);
        AuthorizationPage.authorizationpageTokenI(pass);
        AuthorizationPage.authorizationpageSubmitB(SuccesPage.succespageInfoL);
    }

    public static void authorizationpageNegativeAuthorization(){
        AuthorizationPage.authorizationpageDiscardRejectB();
    }

    // Definiujemy akcje, ktora odpowiada za wpaymentywanie hasla
    public static void authorizationpageTokenI (String pass){
        MyDriver.InputData(authorizationpageTokenI,
                        pass,
                "Strona autoruzacji: input 'Haslo' nie jest edytowalny.",
                "Strona autoruzacji nie zawiera inputu 'Haslo'.");
    }

    // Definiujemy akcje, ktora odpowiada za wciskanie przycisku Autoryzuj
    public static void authorizationpageSubmitB (WebElement succesElementCheck){
        //WebDriver remember that we switched to frame 'main'
        MyDriver.buttonElement(authorizationpageAutoryzujB,
                succesElementCheck,
                "Autoryzowanie za pomoca hasla: "+ LoginPage.iPass+", udalo sie.",
                "Strona autoruzacji: przycisk 'Autoryzuj' jest nie klikalny.",
                "Strona autoruzacji nie zawiera przycisku 'Autoryzuj'.");
    }

    // Definiujemy akcje, ktora odpowiada za wciskanie przycisku Odrzuc
    public static void authorizationpageDiscardRejectB (){
        //WebDriver remember that we switched to frame 'main'
        MyDriver.buttonElement(authorizationpageOdrzucB,
                RejectedPage.failurepageInfoL,
                "Odrzucenie udalo sie.",
                "Strona autoruzacji: przycisk 'Odrzuc' jest nie klikalny.",
                "Strona autoruzacji nie zawiera przycisku 'Odrzuc'.");
    }

    public static void authorizationpageListOfAccountsDD (){
        MyDriver.ButtonTypical(AuthorizationPage.authorizationpageListOfAccountAccountsDD,
                "Kliknięcie w listę z rachunkami udalo sie.",
                "Strona autoruzacji: lista z rachunkami jest nie klikalna.",
                "Strona autoruzacji nie zawiera listy z rachunkami.");

    }

    public static void authorizationpageFirstElementOfListOfAccountsDD () {
        MyDriver.ButtonTypical(authorizationpageFirstElementOfListOfAccountsDD,
                "Kliknięcie w pierwszy element listy z rachunkami udalo sie.",
                "Strona autoruzacji: pierwszy element listy z rachunkami jest nie klikalny.",
                "Strona autoruzacji nie zawiera pierwszego elementu listy z rachunkami.");
    }

    // Definiujemy akcje, ktora odpowiada za dodanie Page Object Pattern
    public static void setPageObject (WebDriver wd) {
        PageFactory.initElements(wd, new AuthorizationPage());
        SeleniumSteps.webElementsList.put("KOMUNIKAT_O_NIEOKRESLONYM_NUMERZE_KONTRAHENTA",authorizationpageNoRecipientAccountNumberM);
        SeleniumSteps.webElementsList.put("KOMUNIKAT_O_NIEOKRESLONYM_NUMERZE_ORGANU_PODATKOWEGO",authorizationpageNoTaxAuthorityAccountNumberM);
        SeleniumSteps.webElementsList.put("KOMUNIKAT_O_NIEOKRESLONYM_NUMERZE_ZLECENIODAWCY",authorizationpageNoSenderAccountNumberM);
        SeleniumSteps.webElementsList.put("KOMUNIKAT_O_BLEDACH_NA_FORMULARZU",authorizationpageTopErrorM);
        SeleniumSteps.webElementsList.put("KOMUNIKAT_O_NIEOKRESLONYM_TRYBIE_PILNOSCI", authorizationpageNoSpecifiedExecutionModeM);
        SeleniumSteps.webElementsList.put("RACHUNEK_ZLECENIODAWCY", authorizationpageSenderAccountDD);
        SeleniumSteps.webElementsList.put("KOMUNIKAT_O_NIEOKRESLONYM_IMIE_KONTRAHENTA",authorizationpageNoSenderNameM);
        SeleniumSteps.webElementsList.put("KOMUNIKAT_O_NIEOKRESLONYM_TYTULE_ZLECENIA",authorizationpageNoOrderNameM);
        SeleniumSteps.webElementsList.put("KOMUNIKAT_O_BLEDNYM_NUMERZE_KONTRAHENTA_NRB",authorizationpageNoCorrectCheckSumContractorNrbM);
        SeleniumSteps.webElementsList.put("KOMUNIKAT_O_BLEDNYM_NUMERZE_ORGANU_PODATKOWEGO_NRB",authorizationpageNoCorrectCheckSumTaxAuthorityNrbM);
        SeleniumSteps.webElementsList.put("KOMUNIKAT_O_BLEDNYM_NUMERZE_KONTRAHENTA_IBAN",authorizationpageNoCorrectCheckSumContractorIBANM);
        SeleniumSteps.webElementsList.put("KOMUNIKAT_O_BLEDNYM_REALIZACJI_SEPA_Z_NUMEREM_KONTRAHENTA_IBAN",authorizationpageNoPossibilitySEPAForContractorIBANM);
        SeleniumSteps.webElementsList.put("KOMUNIKAT_O_BLEDNYM_NUMEREM_KRAJU_KONTRAHENTA_SWIFT",authorizationpageNoCorrectCountryCodeOfContractorNumberWithSWIFTNumberM);
        SeleniumSteps.webElementsList.put("KOMUNIKAT_O_NIE_DODATNIEJ_KWOCIE",authorizationpageNotPositiveAmountM);
    }

}

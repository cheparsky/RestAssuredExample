package cheparsky.cucsteps;

import cheparsky.psd2methods.*;
import cheparsky.psd2requests.*;
import cheparsky.utilities.AspspJsonTemplates;
import cheparsky.utilities.RandomStringUUID;
import cheparsky.utilities.jsonHelper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RESTSteps {

    public static PSD2Method.PSD2MethodBuilder bodyBuilder;
    public static PSD2Request request;
    public static PSD2Method consent;
    public static ConsentRequest consentRequest;
    public static ScopeRequest scopeRequest;
    public static PISRequest pisRequest;
    public static StatusRequest statusRequest;
    public static String typScenariusza;
    public static String typMethodyPis;
    private String transferDataExecutionDate;
    private String scopeDetailsScopeTimeLimit;
    private static boolean defaultTemplate;
    public static String body = null;
    private String bodyStringTemplate = null; // earlier was: String fileName = null;
    private final static String DOMESTIC = "Przelew krajowy";
    private final static String TAX = "Przelew podatkowy";
    private final static String EEA = "Przelew zagraniczny SEPA";
    private final static String NONEEA = "Przelew zagraniczny inny niż SEPA";
    private final static String ACCOUNTS = "Listę rachunków";
    private final static String ACCOUNTS_DETAILS = "Szczegóły rachunku";
    private final static String TRANSACTIONS = "Listę transakcji";
    private final static String TRANSACTIONS_DETAILS = "Szczegóły transakcji";


    //CREATING OF REQUEST


    @Given("^że chcemy (wysłać|pobrać) (.+)$")
    public void setMethodBuilder(String blablabla, String typeOfOrder) {
        RESTSteps.typScenariusza = typeOfOrder;
        RESTSteps.typMethodyPis = RESTSteps.getTypeOfPIS(typScenariusza);
        transferDataExecutionDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now());
        scopeDetailsScopeTimeLimit = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now().plusDays(1)) + "T" + DateTimeFormatter.ofPattern("HH:mm:ss.SSS").format(LocalDateTime.now()) + "Z";
    }

    @When("^tworzymy zapytanie (consent|scope|pis|status|ais|patch)$")
    public void createRequest(String typZapytania) {
        //on this step we use default template for our request body
        defaultTemplate = true;
        body = null;
        bodyStringTemplate = null; // earlier was: fileName = null;
        bodyBuilder = null;
        jsonHelper.privilegeListString = null;
        //Generating of builder of body of our request
        if (typZapytania.equals("consent") || typZapytania.equals("pis") || typZapytania.equals("patch")) {
            bodyStringTemplate = getBodyTemplate(typScenariusza, typZapytania);//earlier was: RESTSteps.getFileName(typZapytania, typScenariusza);
            bodyBuilder = RESTSteps.createBodyBuilder(typScenariusza, bodyStringTemplate, transferDataExecutionDate, scopeDetailsScopeTimeLimit, typZapytania);

        }
        //Generating of our request
        request = null;
        request = RESTSteps.createRequestTemplate(typZapytania);
    }

    @Then("^tworzymy zapytanie (ais) (.+)$")
    public void createAISRequest(String typZapytania, String typScenariusza) {
        //on this step we use default template for our request body
        defaultTemplate = true;
        body = null;
        //Generating of our request
        RESTSteps.typScenariusza = typScenariusza;
        request = null;
        request = RESTSteps.createRequestTemplate(typZapytania);
    }


    //CHANGING OF REQUEST

    @Then("^pole (.+) ma wartość \"([^\"]*)\"$")
    public void setVariablesInBuilder(MethodsNamesForFields pole, String wartosc) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> c = null;
        c = bodyBuilder.getClass();
        Method method = c.getMethod(pole.getMethod(), String.class);
        method.invoke(bodyBuilder, wartosc);
    }

    @Then("^dodamy do zapytania (consent|scope|pis|status) header z nazwą '(.+)' i wartością '(.+)'")
    public void addHeaderToRequest(String typZapytania, String headerKey, String headerValue) {
        request.addHeader(headerKey, headerValue);
    }

    @Then("^skasujemy z zapytania (consent|scope|pis|status) header z nazwą '(.+)'")
    public void deleteHeaderFromRequest(String typZapytania, String headerKey) {
        request.removeHeader(headerKey);
    }

    @Then("^zmienimy wartość dla zapytania (consent|scope|pis|status) headera '(.+)' na wartość '(.+)'")
    public void changeHeaderInRequest(String typZapytania, String headerKey, String headerValue) {
        request.changeValueOfHeader(headerKey, headerValue);
    }

    @Then("^dodamy do zapytania (consent|scope|pis|status) parametr z nazwą '(.+)' i wartością '(.+)'")
    public void addParamsToRequest(String typZapytania, String paramsKey, String paramsValue) {
        request.addParams(paramsKey, paramsValue);
    }

    @Then("^skasujemy z zapytania (consent|scope|pis|status) parametr z nazwą '(.+)'")
    public void deleteParamsFromRequest(String typZapytania, String paramsKey) {
        request.removeParams(paramsKey);
    }

    @Then("^zmienimy wartość dla zapytania (consent|scope|pis|status) parametru '(.+)' na wartość '(.+)'")
    public void changeParamsInRequest(String typZapytania, String paramsKey, String paramsValue) {
        request.changeValueOfParams(paramsKey, paramsValue);
    }

    @Then("^body ma wartość '(.+)'")
    public void setNotDefaultBody(String notDefaultBodyValue) {
        defaultTemplate = false;
        body = notDefaultBodyValue;
    }

    @Then("^dodamy do body zapytania metodę '(.+)'")
    public void addAdditionalMethodToPrivilegeList(String methodToPutIntoPrivilegeList) throws IllegalAccessException {
        Field[] allMethods = AspspJsonTemplates.class.getDeclaredFields();
        for (Field method : allMethods) {
            if (method.getName().equals(methodToPutIntoPrivilegeList))
                jsonHelper.addMethodToPrivilegeList(typScenariusza, (String) method.get(new AspspJsonTemplates()));
        }
    }

    @Then("^budujemy body (consent|pis|patch)")
    public void buildBody(String typZapytania) {
        if (typZapytania.equals("consent")) jsonHelper.buildBodyForConsent();
        else if (typZapytania.equals("pis")) jsonHelper.buildBodyForPis(typMethodyPis);
        else if (typZapytania.equals("patch")) jsonHelper.buildBodyForPatch();
    }

    @Then("^dodamy do body zapytania w miejsce '(.+)' klucz '(.+)' i  wartość '(.+)'")
    public void addKeyAndValueToBody(String pathWherePut, String key, String value) {
        String[] elementOfPath = pathWherePut.split("\\.");
        jsonHelper.putKeyAndValueToJson(jsonHelper.mainMethodObject, elementOfPath, key, value, 0);
    }

    @Then("^skasujemy z body zapytania w miejsce '(.+)' klucze '(.+)'")
    public void removeKeyFromBody(String pathWherePut, String keys) {
        String[] klucze = keys.split("\\,");
        for (String klucz : klucze) {
            String[] elementOfPath = pathWherePut.split("\\.");
            jsonHelper.removeKeyFromJson(jsonHelper.mainMethodObject, elementOfPath, klucz, 0);
        }
    }

    @When("^wysyłamy zapytanie (consent|scope|pis|status|ais|patch)$")
    public void sendRequest(String typZapytania) {
        if (defaultTemplate) {
            if (bodyStringTemplate != null) {
                consent = bodyBuilder.build();
                bodyBuilder = null;
                body = consent.generateJson(); // add typZapytania
            }
        }
        //Generating of body of our request if it is default
        request.sendRequest(body);

    }


    //ACTIONS WITH RESPONSE


    @Then("^id zgody ma wartość \"([^\"]*)\"$")
    public void updatingOfAspspConsentId(String newAspspIdConsent) {
        if (newAspspIdConsent.equals("random")) {
            ConsentRequest.aspspConsentId = RandomStringUUID.generateUUID();
        } else {
            ConsentRequest.aspspConsentId = newAspspIdConsent;
        }
    }

    @Then("^id płatności ma wartość \"([^\"]*)\"$")
    public void updatingOfAspspPaymentId(String newAspspPaymentId) {
        if (newAspspPaymentId.equals("random")) {
            PISRequest.aspspPaymentId = RandomStringUUID.generateUUID();
        } else {
            PISRequest.aspspPaymentId = newAspspPaymentId;
        }
    }

    @Then("^typ płatności ma wartość \"([^\"]*)\"$")
    public void updatingOPaymentProduct(String newPaymentProduct) {
        PISRequest.paymentProduct = newPaymentProduct;

    }

    @Then("^id transakcji ma wartość \"([^\"]*)\"$")
    public void updatingOfAspspTransactionId(String AspspTransactionId) {
        TransactionsDetailsRequest.aspspTransactionId = AspspTransactionId;
    }

    @Then("^kopiujemy wartości odpowiedzi (consent|pis) do schowka$")
    public void copyValuesofScopeRequest(String typeOfPIS) {
        if (typeOfPIS.equals("consent")) {
            ConsentRequest.aspspConsentId = request.getResponse().jsonPath().get("aspspConsentId");
            ConsentRequest.authorizationRedirectUri = request.getResponse().jsonPath().get("authorizationRedirectUri");
        } else if (typeOfPIS.equals("pis")) {
            PISRequest.aspspPaymentId = request.getResponse().jsonPath().get("aspspPaymentId");
        }
    }

    //HELPING METHODS

    public static PSD2Method.PSD2MethodBuilder createBodyBuilder(String typScenariusza, String bodyTemplate, String transferDataExecutionDate, String scopeDetailsScopeTimeLimit, String typZapytania) {
        PSD2Method.PSD2MethodBuilder bodyBuilderTemplate = null;
        if (typZapytania.equals("patch")) {
            bodyBuilderTemplate = new PatchMethod.PatchMethodBuilder();
            bodyBuilderTemplate.setTransferDataExecutionDate(transferDataExecutionDate);
            bodyBuilderTemplate.setScopeDetailsScopeTimeLimit(scopeDetailsScopeTimeLimit);
        } else if (typScenariusza.equals(DOMESTIC)) {
            bodyBuilderTemplate = new DomesticMethod.DomesticMethodBuilder(bodyTemplate);
            bodyBuilderTemplate.setTransferDataExecutionDate(transferDataExecutionDate);
            bodyBuilderTemplate.setScopeDetailsScopeTimeLimit(scopeDetailsScopeTimeLimit);
        } else if (typScenariusza.equals(TAX)) {
            bodyBuilderTemplate = new TaxMethod.TaxMethodBuilder(bodyTemplate);
            bodyBuilderTemplate.setTransferDataExecutionDate(transferDataExecutionDate);
            bodyBuilderTemplate.setScopeDetailsScopeTimeLimit(scopeDetailsScopeTimeLimit);
        } else if (typScenariusza.equals(EEA)) {
            bodyBuilderTemplate = new EeaMethod.EeaMethodBuilder(bodyTemplate);
            bodyBuilderTemplate.setTransferDataExecutionDate(transferDataExecutionDate);
            bodyBuilderTemplate.setScopeDetailsScopeTimeLimit(scopeDetailsScopeTimeLimit);
        } else if (typScenariusza.equals(NONEEA)) {
            bodyBuilderTemplate = new NonEeaMethod.NonEeaMethodBuilder(bodyTemplate);
            bodyBuilderTemplate.setTransferDataExecutionDate(transferDataExecutionDate);
            bodyBuilderTemplate.setScopeDetailsScopeTimeLimit(scopeDetailsScopeTimeLimit);
        } else if (typScenariusza.equals(ACCOUNTS)) {
            bodyBuilderTemplate = new AisAccountsMethod.AisAccountsMethodBuilder(bodyTemplate);
            bodyBuilderTemplate.setTransferDataExecutionDate(transferDataExecutionDate);
            bodyBuilderTemplate.setScopeDetailsScopeTimeLimit(scopeDetailsScopeTimeLimit);
        } else if (typScenariusza.equals(ACCOUNTS_DETAILS)) {
            bodyBuilderTemplate = new AISMethod.AISMethodBuilder(bodyTemplate);
            bodyBuilderTemplate.setTransferDataExecutionDate(transferDataExecutionDate);
            bodyBuilderTemplate.setScopeDetailsScopeTimeLimit(scopeDetailsScopeTimeLimit);
        } else if (typScenariusza.equals(TRANSACTIONS)) {
            bodyBuilderTemplate = new AISMethod.AISMethodBuilder(bodyTemplate);
            bodyBuilderTemplate.setTransferDataExecutionDate(transferDataExecutionDate);
            bodyBuilderTemplate.setScopeDetailsScopeTimeLimit(scopeDetailsScopeTimeLimit);
        } else if (typScenariusza.equals(TRANSACTIONS_DETAILS)) {
            bodyBuilderTemplate = new AISMethod.AISMethodBuilder(bodyTemplate);
            bodyBuilderTemplate.setTransferDataExecutionDate(transferDataExecutionDate);
            bodyBuilderTemplate.setScopeDetailsScopeTimeLimit(scopeDetailsScopeTimeLimit);
        }

        return bodyBuilderTemplate;
    }

    public static String getBodyTemplate(String typScenariusza, String typZapytania) {
        if (typZapytania.equals("patch")) return "";
        else if (typScenariusza.equals(DOMESTIC)) {
            return AspspJsonTemplates.pisDomestic;
        } else if (typScenariusza.equals(TAX)) {
            return AspspJsonTemplates.pisTax;
        } else if (typScenariusza.equals(EEA)) {
            return AspspJsonTemplates.pisEea;
        } else if (typScenariusza.equals(NONEEA)) {
            return AspspJsonTemplates.pisNonEea;
        } else if (typScenariusza.equals(ACCOUNTS)) {
            return AspspJsonTemplates.ais_accounts;
        } else if (typScenariusza.equals(ACCOUNTS_DETAILS)) {
            return AspspJsonTemplates.aisAccountDetails;
        } else if (typScenariusza.equals(TRANSACTIONS)) {
            return AspspJsonTemplates.aisTransactions;
        } else if (typScenariusza.equals(TRANSACTIONS_DETAILS)) {
            return AspspJsonTemplates.aisTransactionDetails;
        } else return null;

    }

    public static String getFileName(String typeOfRequest, String typeOfOrder) {
        if (typeOfOrder.equals(DOMESTIC)) {
            if (typeOfRequest.equals("consent")) return "psd2JsonFiles/consentDomesticTemplate.json";
            else if (typeOfRequest.equals("pis")) return "psd2JsonFiles/domesticTemplate.json";
            else return null;

        } else if (typeOfOrder.equals(TAX)) {
            if (typeOfRequest.equals("consent")) return "psd2JsonFiles/consentTaxTemplate.json";
            else if (typeOfRequest.equals("pis")) return "psd2JsonFiles/taxTemplate.json";
            else return null;

        } else if (typeOfOrder.equals(EEA)) {
            if (typeOfRequest.equals("consent")) return "psd2JsonFiles/consentEeaTemplate.json";
            else if (typeOfRequest.equals("pis")) return "psd2JsonFiles/eeaTemplate.json";
            else return null;

        } else if (typeOfOrder.equals(NONEEA)) {
            if (typeOfRequest.equals("consent")) return "psd2JsonFiles/consentNonEeaTemplate.json";
            else if (typeOfRequest.equals("pis")) return "psd2JsonFiles/nonEeaTemplate.json";
            else return null;

        } else if (typeOfOrder.equals(ACCOUNTS)) {
            if (typeOfRequest.equals("consent")) return "psd2JsonFiles/consentAccountsTemplate.json";
            else return null;

        } else if (typeOfOrder.equals(ACCOUNTS_DETAILS)) {
            if (typeOfRequest.equals("consent")) return "psd2JsonFiles/consentAccountsDetailsTemplate.json";
            else return null;

        } else if (typeOfOrder.equals(TRANSACTIONS)) {
            if (typeOfRequest.equals("consent")) return "psd2JsonFiles/consentTransactionsTemplate.json";
            else return null;

        } else if (typeOfOrder.equals(TRANSACTIONS_DETAILS)) {
            if (typeOfRequest.equals("consent")) return "psd2JsonFiles/consentTransactionsDetailsTemplate.json";
            else return null;

        } else return null;

    }

    public static String getTypeOfPIS(String typeOfOrder) {
        if (typeOfOrder.equals(DOMESTIC)) {
            return "domestic";
        } else if (typeOfOrder.equals(TAX)) {
            return "tax";
        } else if (typeOfOrder.equals(EEA)) {
            return "eea";
        } else if (typeOfOrder.equals(NONEEA)) {
            return "non-eea";
        } else {
            return null;
        }
    }

    public static PSD2Request createRequestTemplate(String typZapytania) {
        PSD2Request requestTemplate = null;
        if (typZapytania.equals("consent")) {
            requestTemplate = new ConsentRequest();
        } else if (typZapytania.equals("pis")) {
            requestTemplate = new PISRequest();
        } else if (typZapytania.equals("scope")) {
            requestTemplate = new ScopeRequest();
        } else if (typZapytania.equals("status")) {
            requestTemplate = new StatusRequest();
        } else if (typZapytania.equals("ais")) {
            if (typScenariusza.equals(ACCOUNTS)) {
                requestTemplate = new AccountsRequest();
            } else if (typScenariusza.equals(ACCOUNTS_DETAILS)) {
                requestTemplate = new AccountsDetailsRequest();
            } else if (typScenariusza.equals(TRANSACTIONS)) {
                requestTemplate = new TransactionsRequest();
            } else if (typScenariusza.equals(TRANSACTIONS_DETAILS)) {
                requestTemplate = new TransactionsDetailsRequest();
            }
        } else if (typZapytania.equals("patch")) {
            requestTemplate = new PatchRequest();
        }

        requestTemplate.createRequest();
        return requestTemplate;
    }
}


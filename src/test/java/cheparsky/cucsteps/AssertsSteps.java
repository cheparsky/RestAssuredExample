package cheparsky.cucsteps;

import cheparsky.utilities.MyDriver;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.Is.is;

public class AssertsSteps {

    @Then("^status odpowiedzi (consent|scope|pis|status|ais|patch) jest (.+)")
    public static void CheckResponseStatus(String typeOfResponse, int status) {
        RESTSteps.request.getResponse().then().statusCode(status);

    }

    @Then("^jest wartość odpowiedzi (consent|scope|pis|status|ais) (.+)")
    public static void CheckResponseValueIsNotNull(String typeOfResponse, String fieldToCheck) {
        RESTSteps.request.getResponse().
                                                then().body(fieldToCheck,is(notNullValue()));

    }

    @Then("^dostejemy w (.+) wiadomość '(.+)'")
    public void CheckTextsInWebElementErrorBox(String nameOfWebelement,String textToCheck) {
        Assert.assertTrue(MyDriver.isVisible(SeleniumSteps.webElementsList.get(nameOfWebelement)));
        Assert.assertEquals(textToCheck, SeleniumSteps.webElementsList.get(nameOfWebelement).getText());
    }

    @Then("^nie widzimy na stronie (.+)")
    public void CheckIfWebelementNotPresentOnPage(String webElement) {
        Assert.assertFalse(MyDriver.isVisible(SeleniumSteps.webElementsList.get(webElement)));
    }

    @Then("^widzimy na stronie (.+)")
    public void CheckIfWebelementIsPresentOnPage(String webElement) {
        Assert.assertTrue(MyDriver.isVisible(SeleniumSteps.webElementsList.get(webElement)));
    }

    @Then("^odpowiedź (consent|scope|pis|status|ais) zawiera pole (.+) z wartościami w liście '(.+)'")
    public static void CheckResponseBodyForManyItemsInListByKey(String typeOfResponse, String keyToCheck, String valuesToCheck) {
        RESTSteps.request.getResponse().
                then().body(keyToCheck,hasItems(valuesToCheck));

    }

    @Then("^odpowiedź (consent|scope|pis|status|ais) zawiera pole (.+) z wartoścą w liście '(.+)'")
    public static void CheckResponseBodyForOneItemInListByKey(String typeOfResponse, String keyToCheck, String valuesToCheck) {
        RESTSteps.request.getResponse().
                then().body(keyToCheck,hasItem(valuesToCheck));

    }

    public static void CheckResponseValueIsEqualToExpectedValue(Response response, String responseValue, String expectedValue) {
        response.
                then().body(responseValue,equalTo(expectedValue));

    }
    @Then("^odpowiedź (consent|scope|pis|status|ais) zawiera pole (.+) z wartoścą '(.+)'")
    public static void CheckResponseBodyForOneItemInFieldByKey(String typeOfResponse, String keyToCheck, String valuesToCheck) {
        RESTSteps.request.getResponse().
                then().body(keyToCheck,containsString(valuesToCheck));
    }

/*    public static Response getResponseByType (String typeOfResponse){
        Response response = null;
        if(typeOfResponse.equals("consent")) response = RESTSteps.consentRequest.getResponse();
        else if(typeOfResponse.equals("scope")) response = RESTSteps.scopeRequest.response;
        else if(typeOfResponse.equals("pis")) response = RESTSteps.pisRequest.getResponse();
        else if(typeOfResponse.equals("status")) response = RESTSteps.statusRequest.response;
        return response;
    }*/

}

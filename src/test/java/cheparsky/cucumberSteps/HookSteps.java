package cheparsky.cucumberSteps;

import cheparsky.restRequests.ConsentRequest;
import cheparsky.utilities.jsonHelper;
import cucumber.api.java.After;
import org.json.JSONArray;

import static cheparsky.cucumberSteps.SeleniumSteps.wd;

public class HookSteps {

    @After
    public void cleanTestData() {
        try {
            wd.quit();

        } catch (Exception e) {
        }
        RESTSteps.bodyBuilder = null;
        RESTSteps.consent = null;
        RESTSteps.consentRequest = null;
        RESTSteps.scopeRequest = null;
        RESTSteps.paymentRequest = null;
        RESTSteps.statusRequest = null;
        wd = null;
        RESTSteps.body = null;
        ConsentRequest.consentId = null;
        ConsentRequest.authorizationRedirectUri = null;
        cheparsky.restRequests.PaymentRequest.paymentId = null;
        jsonHelper.mainMethodObject = null;
        jsonHelper.privilegeList = new JSONArray();
        jsonHelper.consentObject = null;
        jsonHelper.patchObject = null;



    }
}

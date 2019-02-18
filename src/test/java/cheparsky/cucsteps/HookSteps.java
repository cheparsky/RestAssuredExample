package cheparsky.cucsteps;

import cheparsky.psd2requests.ConsentRequest;
import cheparsky.psd2requests.PISRequest;
import cheparsky.utilities.jsonHelper;
import cucumber.api.java.After;
import org.json.JSONArray;

import static cheparsky.cucsteps.SeleniumSteps.wd;

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
        RESTSteps.pisRequest = null;
        RESTSteps.statusRequest = null;
        wd = null;
        RESTSteps.body = null;
        ConsentRequest.aspspConsentId = null;
        ConsentRequest.authorizationRedirectUri = null;
        PISRequest.aspspPaymentId = null;
        jsonHelper.mainMethodObject = null;
        jsonHelper.privilegeList = new JSONArray();
        jsonHelper.consentObject = null;
        jsonHelper.patchObject = null;



    }
}

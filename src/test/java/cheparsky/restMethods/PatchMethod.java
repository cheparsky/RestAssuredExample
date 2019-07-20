package cheparsky.restMethods;


import org.json.JSONArray;
import cheparsky.restRequests.PatchRequest;
import cheparsky.utilities.jsonHelper;

public class PatchMethod extends ConsentJSONFactory implements RestMethod {

    private PatchMethod(PatchMethodBuilder builder) {
        super(builder);
    }

    public static class PatchMethodBuilder extends ConsentBuilder implements cheparsky.restMethods.RestMethod.RestMethodBuilder {

        public PatchMethodBuilder() { // add typZapytania
            super("{}","","");// add typZapytania
            jsonHelper.privilegeList = new JSONArray();
            jsonHelper.privilegeListString = null;
            super.setScopeDetailsScopeTimeLimit(PatchRequest.scopeTimeLimit);
            super.setKeyToReplaceInTppConsentId(PatchRequest.tppConsentId);

        }

        public PatchMethod build() {
            return new PatchMethod(this);
        }


    }
}

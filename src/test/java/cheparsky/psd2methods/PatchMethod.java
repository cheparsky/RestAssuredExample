package cheparsky.psd2methods;


import org.json.JSONArray;
import cheparsky.psd2requests.PatchRequest;
import cheparsky.utilities.jsonHelper;

public class PatchMethod extends ConsentJSONFactory implements PSD2Method {

    private PatchMethod(PatchMethodBuilder builder) {
        super(builder);
    }

    public static class PatchMethodBuilder extends ConsentBuilder implements PSD2MethodBuilder {

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

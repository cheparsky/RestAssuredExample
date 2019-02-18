package cheparsky.psd2methods;

public class AisAccountsMethod extends ConsentJSONFactory implements PSD2Method {

    private AisAccountsMethod(AisAccountsMethodBuilder builder) {
        super(builder);
    }

    public static class AisAccountsMethodBuilder extends ConsentBuilder implements PSD2MethodBuilder {

        public AisAccountsMethodBuilder(String bodyTemplate) {
            super(bodyTemplate,"","");
        }

        public AisAccountsMethod build() {
            return new AisAccountsMethod(this);
        }


    }
}

package cheparsky.psd2methods;

public class DomesticMethod extends ConsentJSONFactory implements PSD2Method {

    private DomesticMethod(DomesticMethodBuilder builder) {
        super(builder);
    }

    public static class DomesticMethodBuilder extends ConsentBuilder implements PSD2MethodBuilder {

        public DomesticMethodBuilder(String bodyTemplate) { // add typZapytania

            super(bodyTemplate,"","");// add typZapytania
            super.setPisDeliveryMode("");
            super.setPisSystem("");
            super.setRecipientAdsressLine("ul. Uchwały 1, 00-123 Warszawa");
        }

        public DomesticMethod build() {
            return new DomesticMethod(this);
        }


    }
}

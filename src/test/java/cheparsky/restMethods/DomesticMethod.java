package cheparsky.restMethods;

public class DomesticMethod extends ConsentJSONFactory implements RestMethod {

    private DomesticMethod(DomesticMethodBuilder builder) {
        super(builder);
    }

    public static class DomesticMethodBuilder extends ConsentBuilder implements cheparsky.restMethods.RestMethod.RestMethodBuilder {

        public DomesticMethodBuilder(String bodyTemplate) { // add typZapytania

            super(bodyTemplate,"","");// add typZapytania
            super.setPaymentDeliveryMode("");
            super.setPaymentSystem("");
            super.setRecipientAdsressLine("ulica A, Warszawa");
        }

        public DomesticMethod build() {
            return new DomesticMethod(this);
        }


    }
}

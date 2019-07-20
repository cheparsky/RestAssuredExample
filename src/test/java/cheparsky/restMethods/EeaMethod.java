package cheparsky.restMethods;

public class EeaMethod extends ConsentJSONFactory implements RestMethod {

    private EeaMethod(EeaMethodBuilder builder) {
        super(builder);
    }

    public static class EeaMethodBuilder extends ConsentBuilder implements cheparsky.restMethods.RestMethod.RestMethodBuilder {

        public EeaMethodBuilder(String bodyTemplate) {
            super(bodyTemplate,"","");
            super.setRecipientAccountNumber("");
            super.setSenderAccountNumber("");
            super.setTransferDataCurrency("");
            super.setPaymentDeliveryMode("");
            super.setPaymentSystem("");
            super.setRecipientAdsressLine("ulica A, Warszawa");
        }

        public EeaMethod build(){
            return new EeaMethod(this);
        }



    }
}

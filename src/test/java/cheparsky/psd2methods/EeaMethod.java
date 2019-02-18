package cheparsky.psd2methods;

public class EeaMethod extends ConsentJSONFactory implements PSD2Method {

    private EeaMethod(EeaMethodBuilder builder) {
        super(builder);
    }

    public static class EeaMethodBuilder extends ConsentBuilder implements PSD2MethodBuilder{

        public EeaMethodBuilder(String bodyTemplate) {
            super(bodyTemplate,"","");
            super.setRecipientAccountNumber("");
            super.setSenderAccountNumber("");
            super.setTransferDataCurrency("");
            super.setPisDeliveryMode("");
            super.setPisSystem("");
            super.setRecipientAdsressLine("ul. Uchwały 1, 00-123 Warszawa");
        }

        public EeaMethod build(){
            return new EeaMethod(this);
        }



    }
}

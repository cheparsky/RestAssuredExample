package cheparsky.psd2methods;

public class NonEeaMethod extends ConsentJSONFactory implements PSD2Method {

    private final static String RECIPIENT_BANK_BIC_OR_SWIFT = "recipientBankBicOrSwift";
    private final static String RECIPIENT_BANK_NAME = "recipientBankName";
    private final static String RECIPIENT_BANK_CODE = "recipientBankCode";
    private final static String RECIPIENT_BANK_ADRESS_COUNTRY = "recipientBankAdressCountry";
    private final static String RECIPIENT_BANK_ADRESS_ADRESSLINE = "recipientBankAdressAdressLine";


    private NonEeaMethod(NonEeaMethodBuilder builder) {
        super(builder);
        valuesToReplace.put(RECIPIENT_BANK_BIC_OR_SWIFT, builder.recipientBankBicOrSwift);
        valuesToReplace.put(RECIPIENT_BANK_NAME, builder.recipientBankName);
        valuesToReplace.put(RECIPIENT_BANK_CODE, builder.recipientBankCode);
        valuesToReplace.put(RECIPIENT_BANK_ADRESS_COUNTRY, builder.recipientBankAdressCountry);
        valuesToReplace.put(RECIPIENT_BANK_ADRESS_ADRESSLINE, builder.recipientBankAdressAdressLine);

    }

    public String getRecipientBankBicOrSwift() {
        return valuesToReplace.get(RECIPIENT_BANK_BIC_OR_SWIFT);
    }
    public String getRecipientBankName() {
        return valuesToReplace.get(RECIPIENT_BANK_NAME);
    }
    public String getRecipientBankCode() {
        return valuesToReplace.get(RECIPIENT_BANK_CODE);
    }
    public String getRecipientBankAdressCountry() {
        return valuesToReplace.get(RECIPIENT_BANK_ADRESS_COUNTRY);
    }
    public String getRecipientBankAdressAdressLine() {
        return valuesToReplace.get(RECIPIENT_BANK_ADRESS_ADRESSLINE);
    }

    public static class NonEeaMethodBuilder extends ConsentBuilder implements PSD2MethodBuilder{

        public NonEeaMethodBuilder(String bodyTemplate) {
            super(bodyTemplate,"","");
            super.setRecipientAccountNumber("");
            super.setSenderAccountNumber("");
            super.setTransferDataCurrency("EUR");
            super.setPisDeliveryMode("");
            super.setPisSystem("");
            super.setRecipientAdsressLine("ul. Uchwały 1, 00-123 Warszawa");
        }

        private String recipientBankBicOrSwift = "HSBCCNSHSZN";
        private String recipientBankName = "HSBC BANK (CHINA) COMP";
        private String recipientBankCode = "HSBCCNSH";
        private String recipientBankAdressCountry = "CHN";
        private String recipientBankAdressAdressLine = "518010 SHENZHEN CHINA";

        public NonEeaMethodBuilder setRecipientBankBicOrSwift(String recipientBankBicOrSwift) {
            this.recipientBankBicOrSwift = recipientBankBicOrSwift;
            return this;
        }

        public NonEeaMethodBuilder setRecipientBankName(String recipientBankName) {
            this.recipientBankName = recipientBankName;
            return this;
        }

        public NonEeaMethodBuilder setRecipientBankCode(String recipientBankCode) {
            this.recipientBankCode = recipientBankCode;
            return this;
        }

        public NonEeaMethodBuilder setRecipientBankAdressCountry(String recipientBankAdressCountry) {
            this.recipientBankAdressCountry = recipientBankAdressCountry;
            return this;
        }

        public NonEeaMethodBuilder setRecipientBankAdressAdressLine(String recipientBankAdressAdressLine) {
            this.recipientBankAdressAdressLine = recipientBankAdressAdressLine;
            return this;
        }

        public NonEeaMethod build(){
            return new NonEeaMethod(this);
        }



    }
}

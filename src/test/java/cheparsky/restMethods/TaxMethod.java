package cheparsky.restMethods;

public class TaxMethod extends ConsentJSONFactory implements RestMethod {

    private TaxMethod(TaxMethodBuilder builder) {
        super(builder);
        valuesToReplace.put(US_INFO_PAYER_ID, builder.usInfoPayerId);
        valuesToReplace.put(US_INFO_PAYER_TYPE, builder.usInfoPayerType);
        valuesToReplace.put(US_INFO_FORM_CODE, builder.usInfoFormCode);
        valuesToReplace.put(US_INFO_PAERIOD_ID, builder.usInfoPeriodId);
        valuesToReplace.put(US_INFO_PAERIOD_ID_TYPE, builder.usInfoPeriodType);
        valuesToReplace.put(US_INFO_YEAR, builder.usInfoYear);
    }

    private final static String US_INFO_PAYER_ID = "usInfoPayerId";
    private final static String US_INFO_PAYER_TYPE = "usInfoPayerType";
    private final static String US_INFO_FORM_CODE = "usInfoFormCode";
    private final static String US_INFO_PAERIOD_ID = "usInfoPeriodId";
    private final static String US_INFO_PAERIOD_ID_TYPE = "usInfoPeriodType";
    private final static String US_INFO_YEAR = "usInfoYear";

    public String getUsInfoPayerId() {
        return valuesToReplace.get(US_INFO_PAYER_ID);
    }

    public String getUsInfoPayerType() {
        return valuesToReplace.get(US_INFO_PAYER_TYPE);
    }

    public String getUsInfoFormCode() {
        return valuesToReplace.get(US_INFO_FORM_CODE);
    }

    public String getUsInfoPaeriodId() {
        return valuesToReplace.get(US_INFO_PAERIOD_ID);
    }

    public String getUsInfoPaeriodIdType() {
        return valuesToReplace.get(US_INFO_PAERIOD_ID_TYPE);
    }

    public String getUsInfoYear() {
        return valuesToReplace.get(US_INFO_YEAR);
    }

    public static class TaxMethodBuilder extends ConsentBuilder implements cheparsky.restMethods.RestMethod.RestMethodBuilder {

        public TaxMethodBuilder(String bodyTemplate) {
            super(bodyTemplate,"","IS");
            super.setPaymentDeliveryMode("");
            super.setPaymentSystem("");
            super.setRecipientAdsressLine("ulica A, Warszawa");
        }

        private String usInfoPayerId = "";
        private String usInfoPayerType = "";
        private String usInfoFormCode = "";
        private String usInfoPeriodId = "";
        private String usInfoPeriodType = "";
        private String usInfoYear = "2018";

        public TaxMethodBuilder setUsInfoPayerId(String usInfoPayerId) {
            this.usInfoPayerId = usInfoPayerId;
            return this;
        }

        public TaxMethodBuilder setUsInfoPayerType(String usInfoPayerType) {
            this.usInfoPayerType = usInfoPayerType;
            return this;
        }

        public TaxMethodBuilder setUsInfoFormCode(String usInfoFormCode) {
            this.usInfoFormCode = usInfoFormCode;
            return this;
        }

        public TaxMethodBuilder setUsInfoPeriodId(String usInfoPeriodId) {
            this.usInfoPeriodId = usInfoPeriodId;
            return this;
        }

        public TaxMethodBuilder setUsInfoPeriodType(String usInfoPeriodType) {
            this.usInfoPeriodType = usInfoPeriodType;
            return this;
        }

        public TaxMethodBuilder setUsInfoYear(String usInfoYear) {
            this.usInfoYear = usInfoYear;
            return this;
        }

        public TaxMethod build() {
            return new TaxMethod(this);
        }


    }
}
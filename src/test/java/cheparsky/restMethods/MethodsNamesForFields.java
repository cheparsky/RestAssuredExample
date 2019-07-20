package cheparsky.restMethods;

public enum MethodsNamesForFields {
    
    KWOTA("setTransferDataAmount"),
    OPIS("setTransferDataDescription"),
    RACHUNEK_ZLECENIODAWCY("setSenderAccountNumber"),
    RACHUNEK_KONTRAHENTA("setRecipientAccountNumber"),
    WALUTA("setTransferDataCurrency"),
    NAZWA_KONTRAHENTA("setRecipientName"),
    NAZWA_ZLECENIODAWCY("setSenderName"),
    TYTUL_ZLECENIA("setTransferDataDescription"),
    STATUS_TRANSAKCJI("setAccountTransactionStatus"),
    BANK_BIC_OR_SWIFT("setRecipientBankBicOrSwift"),
    BANK_CODE("setRecipientBankCode"),
    BANK_NAME("setRecipientBankName"),
    BANK_COUNTRY("setRecipientBankAdressCountry"),
    BANK_ADDRESS("setRecipientBankAdressAdressLine"),
    TRANSFER_CHARGES("setPaymentTransferCharges"),
    SCOPE("setConsentScopeScope"),
    SYSTEM("setPaymentSystem"),
    EXECUTION_DATE("setTransferDataExecutionDate"),
    PAYER_ID("setUsInfoPayerId"),
    PAYER_TYPE("setUsInfoPayerType"),
    FORM_CODE("setUsInfoFormCode"),
    PERIOD_ID("setUsInfoPeriodId"),
    PERIOD_TYPE("setUsInfoPeriodType"),
    US_INFO_YEAR("setUsInfoYear"),
    RECIPIENT_ADDRESS("setRecipientAdsressLine");


    private String method;

    public String getMethod(){
        return this.method;
    }

    MethodsNamesForFields(String method) {
        this.method = method;
    }
}

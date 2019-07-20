package cheparsky.restMethods;

import cheparsky.cucumberSteps.RESTSteps;
import cheparsky.restRequests.PatchRequest;
import cheparsky.utilities.RandomStringUUID;
import cheparsky.utilities.jsonHelper;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConsentJSONFactory {

    private final static String TPP_CONSENT_ID_KEY = "keyToReplaceIntppConsentId";
    private final static String AUTHORIZATION_CODE = "keyToReplaceInauthorizationCode";
    private final static String STATE = "keyToReplaceInstate";
    private final static String TEMPLATE_FILE_NAME = "templateFileName";
    private final static String RECIPIENT_ACCOUNT_NUMBER = "recipientAccountNumber";
    private final static String SENDER_ACCOUNT_NUMBER = "senderAccountNumber";
    private final static String TRANSFER_DATA_AMOUNT = "transferDataAmount";
    private final static String TRANSFER_DATA_CURRENCY = "transferDataCurrency";
    private final static String TRANSFER_DATA_DESCRIPTION = "transferDataDescription";
    private final static String RECIPIENT_NAME = "recipientName";
    private final static String SENDER_NAME = "senderName";
    private final static String TRANSFER_DATA_EXECUTION_DATE = "transferDataExecutionDate";
    private final static String SCOPE_DETAILS_SCOPE_TIME_LIMIT = "scopeDetailsScopeTimeLimit";
    private final static String CONSENT_SCOPE_SCOPE = "consentScopeScope";
    private final static String SCOPE_DETAILS_SCOPE_GROUP_TYPE = "scopeDetailsScopeGroupType";
    private final static String Account_TRANSACTION_STATUS = "accountTransactionStatus";
    private final static String Payment_TRANSFER_CHARGES = "paymentTransferCharges";
    private final static String Payment_DELIVERY_MODE = "paymentDeliveryMode";
    private final static String Payment_SYSTEM = "paymentSystem";
    private final static String RECIPIENT_ADDRESS_LINE = "recipientAdsressLine";


    static LinkedHashMap<String, String> valuesToReplace = new LinkedHashMap<>();
    private String jsonFile;

    ConsentJSONFactory(ConsentBuilder builder) {
        valuesToReplace.put(TPP_CONSENT_ID_KEY, builder.keyToReplaceIntppConsentId);
        valuesToReplace.put(AUTHORIZATION_CODE, RandomStringUUID.generateUUID());
        valuesToReplace.put(STATE, RandomStringUUID.generateUUID());
        valuesToReplace.put(TEMPLATE_FILE_NAME, builder.templateFileName);
        valuesToReplace.put(RECIPIENT_ACCOUNT_NUMBER, builder.recipientAccountNumber);
        valuesToReplace.put(SENDER_ACCOUNT_NUMBER, builder.senderAccountNumber);
        valuesToReplace.put(TRANSFER_DATA_AMOUNT, builder.transferDataAmount);
        valuesToReplace.put(TRANSFER_DATA_CURRENCY, builder.transferDataCurrency);
        valuesToReplace.put(TRANSFER_DATA_DESCRIPTION, builder.transferDataDescription);
        valuesToReplace.put(RECIPIENT_NAME, builder.recipientName);
        valuesToReplace.put(SENDER_NAME, builder.senderName);
        valuesToReplace.put(TRANSFER_DATA_EXECUTION_DATE, builder.transferDataExecutionDate);
        valuesToReplace.put(SCOPE_DETAILS_SCOPE_TIME_LIMIT, builder.scopeDetailsScopeTimeLimit);
        valuesToReplace.put(CONSENT_SCOPE_SCOPE, builder.consentScopeScope);
        valuesToReplace.put(SCOPE_DETAILS_SCOPE_GROUP_TYPE, builder.scopeDetailsScopeGroupType);
        valuesToReplace.put(Account_TRANSACTION_STATUS, builder.accountTransactionStatus);
        valuesToReplace.put(Payment_TRANSFER_CHARGES, builder.paymentTransferCharges);
        valuesToReplace.put(Payment_DELIVERY_MODE, builder.paymentDeliveryMode);
        valuesToReplace.put(Payment_SYSTEM, builder.paymentSystem);
        valuesToReplace.put(RECIPIENT_ADDRESS_LINE, builder.recipientAdsressLine);
    }

    public String getTemplateFileName() {
        return valuesToReplace.get(TEMPLATE_FILE_NAME);
    }

    public String getRecipientAccountNumber() {
        return valuesToReplace.get(RECIPIENT_ACCOUNT_NUMBER);
    }

    public String getSenderAccountNumber() {
        return valuesToReplace.get(SENDER_ACCOUNT_NUMBER);
    }

    public static String getTransferDataAmount() {
        return valuesToReplace.get(TRANSFER_DATA_AMOUNT);
    }

    public String getTransferDataCurrency() {
        return valuesToReplace.get(TRANSFER_DATA_CURRENCY);
    }

    public String getTransferDataDescription() {
        return valuesToReplace.get(TRANSFER_DATA_DESCRIPTION);
    }

    public String getRecipientName() {
        return valuesToReplace.get(RECIPIENT_NAME);
    }

    public String getTransferDataExecutionDate() {
        return valuesToReplace.get(TRANSFER_DATA_EXECUTION_DATE);
    }

    public String getScopeDetailsScopeTimeLimit() {
        return valuesToReplace.get(SCOPE_DETAILS_SCOPE_TIME_LIMIT);
    }

    public String getSenderName() {
        return valuesToReplace.get(SENDER_NAME);
    }

    public String getConsentScopeScope() {
        return valuesToReplace.get(CONSENT_SCOPE_SCOPE);
    }

    public String getScopeDetailsScopeGroupType() {
        return valuesToReplace.get(SCOPE_DETAILS_SCOPE_GROUP_TYPE);
    }

    public String getAccountTransactionStatus() {
        return valuesToReplace.get(Account_TRANSACTION_STATUS);
    }

    public String getPaymentTransferCharges() {
        return valuesToReplace.get(Payment_TRANSFER_CHARGES);
    }

    public String getPaymentDeliveryMode() {
        return valuesToReplace.get(Payment_DELIVERY_MODE);
    }

    public String getPaymentSystem() { return valuesToReplace.get(Payment_SYSTEM); }

    public String getRecipientAdsressLine() { return valuesToReplace.get(RECIPIENT_ADDRESS_LINE); }


    public String getConsentDomesticJson() {
        return jsonFile;
    }


    public String generateJson() { // add typZapytania
        //if (typZapytania.equals("consent")) {jsonHelper.generateBodyForConsent}
        jsonFile = jsonHelper.getJsonContent();
        for (Map.Entry<String, String> entry : valuesToReplace.entrySet()) {
            jsonFile = jsonHelper.replaceValueInJson(jsonFile, entry.getKey(), entry.getValue());
        }
        //copying data for patch if we will be used it
        PatchRequest.tppConsentId = null;
        PatchRequest.tppConsentId = valuesToReplace.get(TPP_CONSENT_ID_KEY);
        PatchRequest.scopeTimeLimit = null;
        PatchRequest.scopeTimeLimit = valuesToReplace.get(SCOPE_DETAILS_SCOPE_TIME_LIMIT);
        return jsonFile;
    }

    public static class ConsentBuilder {

        public ConsentBuilder(String bodyTemplate, String consentScopeScope, String scopeDetailsScopeGroupType) { // add typZapytania
            this.bodyTemplate = bodyTemplate;
            this.consentScopeScope = consentScopeScope;
            this.scopeDetailsScopeGroupType = scopeDetailsScopeGroupType;
            jsonHelper.addMethodToPrivilegeList(RESTSteps.typScenariusza, bodyTemplate); //change to parseBody and add typZapytania
        }

        private String templateFileName = "";
        private String bodyTemplate = "";
        private String recipientAccountNumber = "";
        private String senderAccountNumber = "";
        private String transferDataAmount = "10.00";
        private String transferDataCurrency = "PLN";
        private String transferDataDescription = "Payment transfer";
        private String recipientName = "Jan Kowalski";
        private String senderName = "Adam Nowak";
        private String transferDataExecutionDate = "2019-11-18";
        private String scopeDetailsScopeTimeLimit = "2019-11-18T14:37:42.493Z";
        private String consentScopeScope;
        private String scopeDetailsScopeGroupType;
        private String keyToReplaceIntppConsentId = RandomStringUUID.generateUUID();
        private String accountTransactionStatus = "DONE";
        private String paymentTransferCharges = "SHA";
        private String paymentDeliveryMode = "";
        private String paymentSystem = "";
        private String recipientAdsressLine = "";

        public ConsentBuilder setRecipientAccountNumber(String recipientAccountNumber) {
            this.recipientAccountNumber = recipientAccountNumber;
            return this;
        }

        public ConsentBuilder setSenderAccountNumber(String senderAccountNumber) {
            this.senderAccountNumber = senderAccountNumber;
            return this;
        }

        public ConsentBuilder setTransferDataAmount(String transferDataAmount) {
            this.transferDataAmount = transferDataAmount;
            return this;
        }

        public ConsentBuilder setTransferDataCurrency(String transferDataCurrency) {
            this.transferDataCurrency = transferDataCurrency;
            return this;
        }

        public ConsentBuilder setTemplateFileName(String templateFileName) {
            this.templateFileName = templateFileName;
            return this;
        }

        public ConsentBuilder setBodyTemplate(String bodyTemplate) {
            this.bodyTemplate = bodyTemplate;
            return this;
        }

        public ConsentBuilder setTransferDataDescription(String transferDataDescription) {
            this.transferDataDescription = transferDataDescription;
            return this;
        }

        public ConsentBuilder setRecipientName(String recipientName) {
            this.recipientName = recipientName;
            return this;
        }

        public ConsentBuilder setSenderName(String senderName) {
            this.senderName = senderName;
            return this;
        }

        public ConsentBuilder setTransferDataExecutionDate(String transferDataExecutionDate) {
            this.transferDataExecutionDate = transferDataExecutionDate;
            return this;
        }

        public ConsentBuilder setScopeDetailsScopeTimeLimit(String scopeDetailsScopeTimeLimit) {
            this.scopeDetailsScopeTimeLimit = scopeDetailsScopeTimeLimit;
            return this;
        }

        public ConsentBuilder setConsentScopeScope(String consentScopeScope) {
            this.consentScopeScope = consentScopeScope;
            return this;
        }

        public ConsentBuilder setScopeDetailsScopeGroupType(String scopeDetailsScopeGroupType) {
            this.scopeDetailsScopeGroupType = scopeDetailsScopeGroupType;
            return this;
        }

        public ConsentBuilder setKeyToReplaceInTppConsentId(String keyToReplaceIntppConsentId) {
            this.keyToReplaceIntppConsentId = keyToReplaceIntppConsentId;
            return this;
        }

        public void setAccountTransactionStatus(String accountTransactionStatus) {
            this.accountTransactionStatus = accountTransactionStatus;
        }

        public void setPaymentTransferCharges(String paymentTransferCharges) {
            this.paymentTransferCharges = paymentTransferCharges;
        }

        public void setPaymentDeliveryMode(String paymentDeliveryMode) {
            this.paymentDeliveryMode = paymentDeliveryMode;
        }

        public void setRecipientAdsressLine(String recipientAdsressLine) {
            this.recipientAdsressLine = recipientAdsressLine;
        }

        public void setPaymentSystem(String paymentSystem) {
            this.paymentSystem = paymentSystem;
        }


        public ConsentJSONFactory build() {
            return new ConsentJSONFactory(this); //add here .generateJson
        }
    }
}

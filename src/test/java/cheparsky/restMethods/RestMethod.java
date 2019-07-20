package cheparsky.restMethods;

public interface RestMethod {


    String generateJson();

    String getSenderAccountNumber();

    String getAccountTransactionStatus();

    public static interface RestMethodBuilder {



        ConsentJSONFactory.ConsentBuilder setTransferDataAmount(String s);

        RestMethod build();

        ConsentJSONFactory.ConsentBuilder setTransferDataDescription(String s);

        ConsentJSONFactory.ConsentBuilder setTransferDataExecutionDate(String transferDataExecutionDate);

        ConsentJSONFactory.ConsentBuilder setScopeDetailsScopeTimeLimit(String scopeDetailsScopeTimeLimit);
    }
}

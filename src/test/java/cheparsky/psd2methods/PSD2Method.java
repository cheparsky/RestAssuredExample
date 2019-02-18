package cheparsky.psd2methods;

public interface PSD2Method {


    String generateJson();

    String getSenderAccountNumber();

    String getAisTransactionStatus();

    public static interface PSD2MethodBuilder{



        ConsentJSONFactory.ConsentBuilder setTransferDataAmount(String s);

        PSD2Method build();

        ConsentJSONFactory.ConsentBuilder setTransferDataDescription(String s);

        ConsentJSONFactory.ConsentBuilder setTransferDataExecutionDate(String transferDataExecutionDate);

        ConsentJSONFactory.ConsentBuilder setScopeDetailsScopeTimeLimit(String scopeDetailsScopeTimeLimit);
    }
}

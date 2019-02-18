package cheparsky.psd2requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import cheparsky.cucsteps.RESTSteps;

import java.util.HashMap;
import java.util.Map;

public class TransactionsRequest implements PSD2Request {

    private RequestSpecification httpRequest;
    private Map<String, String> transactionsHeader = new HashMap<String, String>();
    private Map<String, String> transactionsParams = new HashMap<String, String>();
    private Response response;
    private String responseBody;

    public void createRequest() {
        String aspspConsentId = ConsentRequest.getAspspConsentId();
        String accountNumber = RESTSteps.consent.getSenderAccountNumber();
        String transactionStatus = RESTSteps.consent.getAisTransactionStatus();
        System.out.println("transactionStatus: "+transactionStatus);
        //URL where we send ot=ur request
        RestAssured.baseURI = "/accounts/"+accountNumber+"/transactions/"+transactionStatus;
        // Creating of request
        httpRequest = RestAssured.given();
        //Headers of our reqiest
        transactionsHeader.put("Content-Type", "application/json");
        transactionsHeader.put("TPP-Request-ID", "123");
        transactionsHeader.put("Accept-Encoding", "gzip, deflate");
        transactionsHeader.put("Accept-Language", "en-US");

    }

    public void sendRequest(String jsonFile) {
        for (Map.Entry<String, String> entry : transactionsHeader.entrySet()) {
            httpRequest.header(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, String> entry : transactionsParams.entrySet()) {
            httpRequest.queryParam(entry.getKey(), entry.getValue());
        }
        if (jsonFile != null) {
            httpRequest.body(jsonFile);
        }

        response = httpRequest.get();
        responseBody = response.asString();
        System.out.println(responseBody);
    }

    public void addHeader(String key, String value) {
        transactionsHeader.put(key, value);
    }

    public void changeValueOfHeader(String key, String value) {
        transactionsHeader.replace(key, value);
    }

    public void removeHeader(String key) {
        transactionsHeader.remove(key);
    }

    public void addParams(String key, String value) {
        transactionsParams.put(key, value);
    }

    public void changeValueOfParams(String key, String value) {
        transactionsParams.replace(key, value);
    }

    public void removeParams(String key) {
        transactionsParams.remove(key);
    }

    public Response getResponse() {
        return response;
    }

}

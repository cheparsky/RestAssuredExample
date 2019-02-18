package cheparsky.psd2requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import cheparsky.cucsteps.RESTSteps;

import java.util.HashMap;
import java.util.Map;

public class TransactionsDetailsRequest implements PSD2Request {

    private RequestSpecification httpRequest;
    private Map<String, String> transactionsDetailsHeader = new HashMap<String, String>();
    private Map<String, String> transactionsDetailsParams = new HashMap<String, String>();
    private Response response;
    private String responseBody;
    public static String aspspTransactionId;

    public void createRequest() {
        String aspspConsentId = ConsentRequest.getAspspConsentId();
        String accountNumber = RESTSteps.consent.getSenderAccountNumber();
        aspspTransactionId = "";
        //URL where we send ot=ur request
        RestAssured.baseURI = "/accounts/"+accountNumber+"/transaction-details/"+aspspTransactionId;
        // Creating of request
        httpRequest = RestAssured.given();
        //Headers of our reqiest
        transactionsDetailsHeader.put("Content-Type", "application/json");
        transactionsDetailsHeader.put("TPP-Request-ID", "123");
        transactionsDetailsHeader.put("Accept-Encoding", "gzip, deflate");
        transactionsDetailsHeader.put("Accept-Language", "en-US");
    }

    public void sendRequest(String jsonFile) {
        for (Map.Entry<String, String> entry : transactionsDetailsHeader.entrySet()) {
            httpRequest.header(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, String> entry : transactionsDetailsParams.entrySet()) {
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
        transactionsDetailsHeader.put(key, value);
    }

    public void changeValueOfHeader(String key, String value) {
        transactionsDetailsHeader.replace(key, value);
    }

    public void removeHeader(String key) {
        transactionsDetailsHeader.remove(key);
    }

    public void addParams(String key, String value) {
        transactionsDetailsParams.put(key, value);
    }

    public void changeValueOfParams(String key, String value) {
        transactionsDetailsParams.replace(key, value);
    }

    public void removeParams(String key) {
        transactionsDetailsParams.remove(key);
    }

    public Response getResponse() {
        return response;
    }
}

package cheparsky.restRequests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import cheparsky.cucumberSteps.RESTSteps;

import java.util.HashMap;
import java.util.Map;

public class AccountsDetailsRequest implements RestRequest {

    private RequestSpecification httpRequest;
    private Map<String, String> accountsDetailsHeader = new HashMap<String, String>();
    private Map<String, String> accountsDetailsParams = new HashMap<String, String>();
    private Response response;
    private String responseBody;

    public void createRequest() {
        String consentId = ConsentRequest.getConsentId();
        String accountNumber = RESTSteps.consent.getSenderAccountNumber();
        //URL where we send ot=ur request
        RestAssured.baseURI = "/accounts/"+accountNumber;
        // Creating of request
        httpRequest = RestAssured.given();
        //Headers of our reqiest
        accountsDetailsHeader.put("Content-Type", "application/json");
        accountsDetailsHeader.put("TPP-Request-ID", "123");
        accountsDetailsHeader.put("Accept-Encoding", "gzip, deflate");
        accountsDetailsHeader.put("Accept-Language", "en-US");
    }

    public void sendRequest(String jsonFile) {
        for (Map.Entry<String, String> entry : accountsDetailsHeader.entrySet()) {
            httpRequest.header(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, String> entry : accountsDetailsParams.entrySet()) {
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
        accountsDetailsHeader.put(key, value);
    }

    public void changeValueOfHeader(String key, String value) {
        accountsDetailsHeader.replace(key, value);
    }

    public void removeHeader(String key) {
        accountsDetailsHeader.remove(key);
    }

    public void addParams(String key, String value) {
        accountsDetailsParams.put(key, value);
    }

    public void changeValueOfParams(String key, String value) {
        accountsDetailsParams.replace(key, value);
    }

    public void removeParams(String key) {
        accountsDetailsParams.remove(key);
    }

    public Response getResponse() {
        return response;
    }
    
}

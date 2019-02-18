package cheparsky.psd2requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class AccountsRequest implements PSD2Request {

    private RequestSpecification httpRequest;
    private Map<String, String> accountsHeader = new HashMap<String, String>();
    private Map<String, String> accountsParams = new HashMap<String, String>();
    private Response response;
    private String responseBody;

    public void createRequest() {
        String aspspConsentId = ConsentRequest.getAspspConsentId();
        //URL where we send ot=ur request
        RestAssured.baseURI = "/accounts";
        // Creating of request
        httpRequest = RestAssured.given();
        //Headers of our reqiest
        accountsHeader.put("Content-Type", "application/json");
        accountsHeader.put("TPP-Request-ID", "123");
        accountsHeader.put("Accept-Encoding", "gzip, deflate");
        accountsHeader.put("Accept-Language", "en-US");
    }

    public void sendRequest(String jsonFile) {
        for (Map.Entry<String, String> entry : accountsHeader.entrySet()) {
            httpRequest.header(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, String> entry : accountsParams.entrySet()) {
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
        accountsHeader.put(key, value);
    }

    public void changeValueOfHeader(String key, String value) {
        accountsHeader.replace(key, value);
    }

    public void removeHeader(String key) {
        accountsHeader.remove(key);
    }

    public void addParams(String key, String value) {
        accountsParams.put(key, value);
    }

    public void changeValueOfParams(String key, String value) {
        accountsParams.replace(key, value);
    }

    public void removeParams(String key) {
        accountsParams.remove(key);
    }

    public Response getResponse() {
        return response;
    }

}

package cheparsky.restRequests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class ScopeRequest implements RestRequest {

    private RequestSpecification httpRequest;
    private Map<String, String> statusHeader = new HashMap<String, String>();
    private Map<String, String> statusParams = new HashMap<String, String>();
    private Response response;
    private String responseBody;

    public void createRequest() {
        String consentId = ConsentRequest.getConsentId();
        RestAssured.baseURI = "/consents/" + consentId + "/scope?dypbdgofkae=abc";

        httpRequest = RestAssured.given();

        statusHeader.put("TPP-Request-ID", "123");
        statusHeader.put("Accept-Encoding", "gzip, deflate");
        statusHeader.put("Accept-Language", "en-US");

    }

    public void sendRequest(String jsonFile) {
        for (Map.Entry<String, String> entry : statusHeader.entrySet()) {
            httpRequest.header(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, String> entry : statusParams.entrySet()) {
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
        statusHeader.put(key, value);
    }

    public void changeValueOfHeader(String key, String value) {
        statusHeader.replace(key, value);
    }

    public void removeHeader(String key) {
        statusHeader.remove(key);
    }

    public void addParams(String key, String value) {
        statusParams.put(key, value);
    }

    public void changeValueOfParams(String key, String value) {
        statusParams.replace(key, value);
    }

    public void removeParams(String key) {
        statusParams.remove(key);
    }

    public Response getResponse() {
        return response;
    }

    public void setResposeValue(String nameOfValue, String value) {

    }

}

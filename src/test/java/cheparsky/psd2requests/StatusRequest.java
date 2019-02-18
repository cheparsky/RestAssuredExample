package cheparsky.psd2requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import cheparsky.cucsteps.RESTSteps;

import java.util.HashMap;
import java.util.Map;

public class StatusRequest implements PSD2Request {

    private RequestSpecification httpRequest;
    private Map<String, String> scopeHeader = new HashMap<String, String>();
    private Map<String, String> scopeParams = new HashMap<String, String>();
    private Response response;
    private String responseBody;

    public void createRequest() {
        String paymentProduct = RESTSteps.typMethodyPis;
        String aspspConsentId = ConsentRequest.getAspspConsentId();
        String aspspPaymentId = PISRequest.getAspspPaymentId();
        RestAssured.baseURI = "/payments/" + paymentProduct + "/" + aspspPaymentId + "/status?dypbdgofkae=abc";

        httpRequest = RestAssured.given();

        scopeHeader.put("TPP-Request-ID", "123");
        scopeHeader.put("Accept-Encoding", "gzip, deflate");
        scopeHeader.put("Accept-Language", "en-US");
    }

    public void sendRequest(String jsonFile) {
        for (Map.Entry<String, String> entry : scopeHeader.entrySet()) {
            httpRequest.header(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, String> entry : scopeParams.entrySet()) {
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
        scopeHeader.put(key, value);
    }

    public void changeValueOfHeader(String key, String value) {
        scopeHeader.replace(key, value);
    }

    public void removeHeader(String key) {
        scopeHeader.remove(key);
    }

    public void addParams(String key, String value) {
        scopeParams.put(key, value);
    }

    public void changeValueOfParams(String key, String value) {
        scopeParams.replace(key, value);
    }

    public void removeParams(String key) {
        scopeParams.remove(key);
    }

    public Response getResponse() {
        return response;
    }

    public void setResposeValue(String nameOfValue, String value) {

    }
}

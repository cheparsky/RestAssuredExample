package cheparsky.psd2requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import cheparsky.cucsteps.RESTSteps;
import cheparsky.utilities.RandomStringUUID;

import java.util.HashMap;
import java.util.Map;

public class PISRequest implements PSD2Request {

    private RequestSpecification httpRequest;
    private Map<String, String> pisHeader = new HashMap<String, String>();
    private Map<String, String> pisParams = new HashMap<String, String>();
    private Response response;
    private String responseBody;
    public static String aspspPaymentId;

    public static String paymentProduct;

    public void createRequest() {
        paymentProduct = RESTSteps.typMethodyPis;
        String aspspConsentId = ConsentRequest.getAspspConsentId();
        RestAssured.baseURI = "/payments/" + paymentProduct + "?dypbdgofkae=abc";

        httpRequest = RestAssured.given();

        pisHeader.put("Content-Type", "application/json");
        pisHeader.put("Accept-Encoding", "gzip, deflate");
        pisHeader.put("TPP-Request-ID", "123");
        pisHeader.put("TPP-Payment-ID", RandomStringUUID.generateUUID());
        pisHeader.put("ASPSP-Consent-ID", aspspConsentId);
        pisHeader.put("Accept-Language", "en-US");
    }

    public void sendRequest(String jsonFile) {
        for (Map.Entry<String, String> entry : pisHeader.entrySet()) {
            httpRequest.header(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, String> entry : pisParams.entrySet()) {
            httpRequest.queryParam(entry.getKey(), entry.getValue());
        }
        if (jsonFile != null) {
            httpRequest.body(jsonFile);
        }

        System.out.println(jsonFile);
        response = httpRequest.post();
        responseBody = response.asString();
        System.out.println(responseBody);
    }

    public void addHeader(String key, String value) {
        pisHeader.put(key, value);
    }

    public void changeValueOfHeader(String key, String value) {
        pisHeader.replace(key, value);
    }

    public void removeHeader(String key) {
        pisHeader.remove(key);
    }

    public void addParams(String key, String value) {
        pisParams.put(key, value);
    }

    public void changeValueOfParams(String key, String value) {
        pisParams.replace(key, value);
    }

    public void removeParams(String key) {
        pisParams.remove(key);
    }

    public Response getResponse() {
        return response;
    }

    public static String getAspspPaymentId() {
        return aspspPaymentId;
    }

    public static String getPaymentProduct() {
        return paymentProduct;
    }

}

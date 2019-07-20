package cheparsky.restRequests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import cheparsky.cucumberSteps.RESTSteps;
import cheparsky.utilities.RandomStringUUID;

import java.util.HashMap;
import java.util.Map;

public class PaymentRequest implements RestRequest {

    private RequestSpecification httpRequest;
    private Map<String, String> paymentHeader = new HashMap<String, String>();
    private Map<String, String> paymentParams = new HashMap<String, String>();
    private Response response;
    private String responseBody;
    public static String paymentId;

    public static String paymentProduct;

    public void createRequest() {
        paymentProduct = RESTSteps.typMethodyPayment;
        String consentId = ConsentRequest.getConsentId();
        RestAssured.baseURI = "/payments/" + paymentProduct + "?dypbdgofkae=abc";

        httpRequest = RestAssured.given();

        paymentHeader.put("Content-Type", "application/json");
        paymentHeader.put("Accept-Encoding", "gzip, deflate");
        paymentHeader.put("TPP-Request-ID", "123");
        paymentHeader.put("TPP-Payment-ID", RandomStringUUID.generateUUID());
        paymentHeader.put("Consent-ID", consentId);
        paymentHeader.put("Accept-Language", "en-US");
    }

    public void sendRequest(String jsonFile) {
        for (Map.Entry<String, String> entry : paymentHeader.entrySet()) {
            httpRequest.header(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, String> entry : paymentParams.entrySet()) {
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
        paymentHeader.put(key, value);
    }

    public void changeValueOfHeader(String key, String value) {
        paymentHeader.replace(key, value);
    }

    public void removeHeader(String key) {
        paymentHeader.remove(key);
    }

    public void addParams(String key, String value) {
        paymentParams.put(key, value);
    }

    public void changeValueOfParams(String key, String value) {
        paymentParams.replace(key, value);
    }

    public void removeParams(String key) {
        paymentParams.remove(key);
    }

    public Response getResponse() {
        return response;
    }

    public static String getPaymentId() {
        return paymentId;
    }

    public static String getPaymentProduct() {
        return paymentProduct;
    }

}

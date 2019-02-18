package cheparsky.psd2requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ConsentRequest implements PSD2Request {

    private RequestSpecification httpRequest;
    private Map<String, String> consentHeader = new HashMap<String, String>();
    private Map<String, String> consentParams = new HashMap<String, String>();
    private Response response;
    private String responseBody;
    public static String aspspConsentId;
    public static String authorizationRedirectUri;


    public void createRequest() {
        //URL where we send ot=ur request
        RestAssured.baseURI = "/consents?dypbdgofkae=abc";
        // Creating of request
        httpRequest = RestAssured.given();
        //Headers of our reqiest
        consentHeader.put("Content-Type", "application/json");
        consentHeader.put("TPP-Request-ID", "123"); // zmienić na uuid
        consentHeader.put("TPP-Name", "string");
        consentHeader.put("Accept-Encoding", "gzip, deflate");
        consentHeader.put("Accept-Language", "en-US");
        consentHeader.put("PSU-IP-Address", "193.41.230.87");
        consentHeader.put("PSU-IP-Port", "80");
        consentHeader.put("PSU-User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0 Mozilla/5.0 (Macintosh; Intel Mac OS X x.y; rv:42.0) Gecko/20100101 Firefox/42.0.");
        consentHeader.put("TPP-Send-Date", "2017-11-24T14:13:05.424Z");


    }

    public void sendRequest(String jsonFile) {
        for (Map.Entry<String, String> entry : consentHeader.entrySet()) {
            httpRequest.header(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, String> entry : consentParams.entrySet()) {
            httpRequest.queryParam(entry.getKey(), entry.getValue());
        }
        if (jsonFile != null) {
            httpRequest.body(jsonFile);
        }

        System.out.println(new JSONObject(jsonFile));
        response = httpRequest.post();
        responseBody = response.asString();
        System.out.println(responseBody);
    }

    public void addHeader(String key, String value) {
        consentHeader.put(key, value);
    }

    public void changeValueOfHeader(String key, String value) {
        consentHeader.replace(key, value);
    }

    public void removeHeader(String key) {
        consentHeader.remove(key);
    }

    public void addParams(String key, String value) {
        consentParams.put(key, value);
    }

    public void changeValueOfParams(String key, String value) {
        consentParams.replace(key, value);
    }

    public void removeParams(String key) {
        consentParams.remove(key);
    }

    public Response getResponse() {
        return response;
    }

    public static String getAspspConsentId() {
        return aspspConsentId;
    }

    public static String getAuthorizationRedirectUri() {
        return authorizationRedirectUri;
    }

}

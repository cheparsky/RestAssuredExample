package cheparsky.psd2requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class PatchRequest implements PSD2Request {

    private RequestSpecification httpRequest;
    private Map<String, String> patchHeader = new HashMap<String, String>();
    private Map<String, String> patchParams = new HashMap<String, String>();
    private Response response;
    public static String tppConsentId;
    public static String scopeTimeLimit;

    public void createRequest() {
        String aspspConsentId = ConsentRequest.getAspspConsentId();
        //URL where we send ot=ur request
        RestAssured.baseURI = "/consents/"+aspspConsentId+"?dypbdgofkae=abc";
        // Creating of request
        httpRequest = RestAssured.given();
        //Headers of our reqiest
        patchHeader.put("Content-Type", "application/json");
        patchHeader.put("TPP-Request-ID", "123");
        patchHeader.put("TPP-Name", "string");
        patchHeader.put("Accept-Encoding", "gzip, deflate");
        patchHeader.put("Accept-Language", "en-US");
        patchHeader.put("PSU-IP-Address", "193.41.230.87");
        patchHeader.put("PSU-IP-Port", "80");
        patchHeader.put("PSU-User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0 Mozilla/5.0 (Macintosh; Intel Mac OS X x.y; rv:42.0) Gecko/20100101 Firefox/42.0.");
        patchHeader.put("TPP-Send-Date", "2017-11-24T14:13:05.424Z");
    }

    public void sendRequest(String jsonFile) {
        for (Map.Entry<String, String> entry : patchHeader.entrySet()) {
            httpRequest.header(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, String> entry : patchParams.entrySet()) {
            httpRequest.queryParam(entry.getKey(), entry.getValue());
        }
        if (jsonFile != null) {
            httpRequest.body(jsonFile);
        }
        System.out.println(jsonFile);
        response = httpRequest.patch();
        System.out.println(response.asString());
    }

    public void addHeader(String key, String value) {
        patchHeader.put(key, value);
    }

    public void changeValueOfHeader(String key, String value) {
        patchHeader.replace(key, value);
    }

    public void removeHeader(String key) {
        patchHeader.remove(key);
    }

    public void addParams(String key, String value) {
        patchParams.put(key, value);
    }

    public void changeValueOfParams(String key, String value) {
        patchParams.replace(key, value);
    }

    public void removeParams(String key) {
        patchParams.remove(key);
    }

    public Response getResponse() {
        return response;
    }

}

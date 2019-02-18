package cheparsky.psd2requests;

import io.restassured.response.Response;

public interface PSD2Request {

    void createRequest();

    void addHeader(String headerKey, String headerValue);

    void removeHeader(String headerKey);

    void changeValueOfHeader(String headerKey, String headerValue);

    void addParams(String paramsKey, String paramsValue);

    void removeParams(String paramsKey);

    void changeValueOfParams(String paramsKey, String paramsValue);

    void sendRequest(String body);

    Response getResponse();

}

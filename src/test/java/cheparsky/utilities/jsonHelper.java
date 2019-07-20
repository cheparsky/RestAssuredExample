package cheparsky.utilities;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class jsonHelper {
    public static JSONObject mainMethodObject = null;
    public static JSONArray privilegeList = new JSONArray();
    public static JSONObject consentObject = null;
    public static JSONObject patchObject = null;
    public static String privilegeListString;


    public static void parseJsonFromString(String templateString) {
        mainMethodObject = new JSONObject("{\n" + templateString + " \n}");
    }

    public static void parseJsonFromFile(String templateFileName) {

        try {
            ClassLoader classLoader = jsonHelper.class.getClassLoader();//jsonHelper.class.getClassLoader().getResource(templateFileName);
            File json = new File(classLoader.getResource(templateFileName).getFile());
            String content = FileUtils.readFileToString(json, "utf-8");
            mainMethodObject = new JSONObject(content);

        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void addMethodToPrivilegeList(String typScenariusza, String method) {
        if (privilegeListString != null) {
            privilegeListString = privilegeListString + ",";
            privilegeListString = privilegeListString + method;
        } else {
            if(typScenariusza.equals("Szczegóły rachunku") || typScenariusza.equals("Listę transakcji") || typScenariusza.equals("Szczegóły transakcji")){
                privilegeListString = ConsentJsonTemplates.accountNumber;
                privilegeListString = privilegeListString + method;
            } else{
                privilegeListString = method;
            }

        }
    }

    public static void addMethodToPrivilegeListArray(String method) {
        JSONObject obj = new JSONObject(method);
        privilegeList.put(new JSONObject(method));
    }

    public static void buildBodyForConsent() {
        //System.out.println("{"+privilegeListString+"}");
        privilegeList.put(new JSONObject("{" + privilegeListString + "}"));
        consentObject = new JSONObject(ConsentJsonTemplates.consentBasis);
        String path = "consentScope.scopeDetails";
        String key = "privilegeList";
        String[] elementOfPath = path.split("\\.");
        mainMethodObject = jsonHelper.putElementOfArrayToJson(consentObject, elementOfPath, key, privilegeList, 0);
    }

    public static void buildBodyForPayment(String typMethodyPayment) {
        mainMethodObject = new JSONObject("{" + privilegeListString + "}");
        if (typMethodyPayment.equals("domestic")) {
            mainMethodObject = (JSONObject) mainMethodObject.get("paymentDomestic");
            mainMethodObject = (JSONObject) mainMethodObject.get("domesticPaymentRequest");
        } else if (typMethodyPayment.equals("tax")) {
            mainMethodObject = (JSONObject) mainMethodObject.get("paymentTax");
            mainMethodObject = (JSONObject) mainMethodObject.get("taxPaymentRequest");
        } else if (typMethodyPayment.equals("eea")) {
            mainMethodObject = (JSONObject) mainMethodObject.get("paymentEea");
            mainMethodObject = (JSONObject) mainMethodObject.get("eeaPaymentRequest");
        } else if (typMethodyPayment.equals("non-eea")) {
            mainMethodObject = (JSONObject) mainMethodObject.get("paymentNonEea");
            mainMethodObject = (JSONObject) mainMethodObject.get("nonEeaPaymentRequest");
        }

    }

    public static void buildBodyForPatch() {
        //System.out.println("\"accountNumber\": \"senderAccountNumber\",\n" +"{"+privilegeListString+"}");
        privilegeList.put(new JSONObject("{" + "\"accountNumber\": \"senderAccountNumber\",\n" + privilegeListString + "}"));
        patchObject = new JSONObject(ConsentJsonTemplates.patchBasis);
        String path = "consentScope.scopeDetails";
        String key = "privilegeList";
        String[] elementOfPath = path.split("\\.");
        mainMethodObject = jsonHelper.putElementOfArrayToJson(patchObject, elementOfPath, key, privilegeList, 0);
        //System.out.println("PATCH: " + mainMethodObject);
    }

    public static JSONObject removeKeyFromJson(JSONObject jsonObject, String[] elementOfPath, String key, int elem) {
        int rec = elem + 1;
        JSONObject jsonObject1 = null;
        if (jsonObject.get(elementOfPath[elem]) instanceof JSONObject) {
            jsonObject1 = (JSONObject) jsonObject.get(elementOfPath[elem]);
        } else if (jsonObject.get(elementOfPath[elem]) instanceof JSONArray) {
            JSONArray jsonArr = (JSONArray) jsonObject.getJSONArray(elementOfPath[elem]);
            jsonObject1 = jsonArr.getJSONObject(0);
        }
        if (elementOfPath.length > rec) {
            jsonObject1 = jsonHelper.removeKeyFromJson(jsonObject1, elementOfPath, key, rec);
        } else {
            jsonObject1.remove(key);
        }
        if (jsonObject.get(elementOfPath[elem]) instanceof JSONArray){
            JSONArray jsonArray1 = new JSONArray();
            jsonArray1.put(jsonObject1);
            jsonObject.remove(elementOfPath[elem]);
            jsonObject.put(elementOfPath[elem], jsonArray1);
        } else if (jsonObject.get(elementOfPath[elem]) instanceof JSONObject) {
            jsonObject.remove(elementOfPath[elem]);
            jsonObject.put(elementOfPath[elem], jsonObject1);
        }
        return jsonObject;
    }

    public static JSONObject putKeyAndValueToJson(JSONObject jsonObject, String[] elementOfPath, String key, String value, int elem) {
        int rec = elem + 1;
        JSONObject jsonObject1 = null;
        if (jsonObject.get(elementOfPath[elem]) instanceof JSONObject) {
            jsonObject1 = (JSONObject) jsonObject.get(elementOfPath[elem]);
        } else if (jsonObject.get(elementOfPath[elem]) instanceof JSONArray) {
            JSONArray jsonArr = (JSONArray) jsonObject.getJSONArray(elementOfPath[elem]);
            jsonObject1 = jsonArr.getJSONObject(0);
        }
        if (elementOfPath.length > rec) {
            jsonObject1 = jsonHelper.putKeyAndValueToJson(jsonObject1, elementOfPath, key, value, rec);
        } else {
            jsonObject1.put(key, value);
        }
        jsonObject.remove(elementOfPath[elem]);
        jsonObject.put(elementOfPath[elem], jsonObject1);
        return jsonObject;
    }

    public static JSONObject putElementOfArrayToJson(JSONObject jsonObject, String[] elementOfPath, String key, JSONArray value, int elem) {
        int rec = elem + 1;
        JSONObject jsonObject1 = null;
        if (jsonObject.get(elementOfPath[elem]) instanceof JSONObject) {
            jsonObject1 = (JSONObject) jsonObject.get(elementOfPath[elem]);
        } else if (jsonObject.get(elementOfPath[elem]) instanceof JSONArray) {
            JSONArray jsonArr = (JSONArray) jsonObject.getJSONArray(elementOfPath[elem]);
        }
        if (elementOfPath.length > rec) {
            jsonObject1 = jsonHelper.putElementOfArrayToJson(jsonObject1, elementOfPath, key, value, rec);
        } else {
            jsonObject1.put(key, value);
        }
        jsonObject.remove(elementOfPath[elem]);
        jsonObject.put(elementOfPath[elem], jsonObject1);
        return jsonObject;
    }

    //generateBodyForConsent
    //add parsing consentBasis and putting in it our mainMethodObject with list of methods in privilegeList
    //1) parse consentBasis
    //2) in basis putElementOfArrayToJson mainMethodObject

    public static String getJsonContent() {
        //get templateFileName - body of privilegeList
        //
        if (mainMethodObject != null)
            return mainMethodObject.toString();
        else {
            return null;
        }

    }

    public static String replaceValueInJson(String consentJson, String valueToChange, String targetValue) {

        return consentJson.replace(valueToChange, targetValue);

    }

    @Test
    public void TestTest() {
/*        String path = "consentScope.scopeDetails";
        String key = "privilegeList";
        String value = "";

        jsonHelper.parseJsonFromFile("restJsonFiles/consentDomesticTemplate.json");

        String[] elementOfPath = path.split("\\.");
        JSONObject jsonObjectf = jsonHelper.putKeyAndValueToJson(mainMethodObject, elementOfPath, key, value, 0);
        System.out.println(jsonObjectf);*/

        JSONObject jsonObjectt = new JSONObject(ConsentJsonTemplates.consentBasis);
        String path = "consentScope.scopeDetails";
        String key = "privilegeList";

        String[] elementOfPath = path.split("\\.");
        JSONArray ja = new JSONArray();
        ja.put(new JSONObject(ConsentJsonTemplates.account_accounts));
        ja.put(new JSONObject(ConsentJsonTemplates.accountAccountDetails));
        JSONObject jsonObjectf = jsonHelper.putElementOfArrayToJson(jsonObjectt, elementOfPath, key, ja, 0);
        //System.out.println(jsonObjectf);

    }

}

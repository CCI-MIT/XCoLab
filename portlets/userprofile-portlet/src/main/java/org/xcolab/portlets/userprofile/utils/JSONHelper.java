package org.xcolab.portlets.userprofile.utils;

import org.json.JSONObject;

import java.io.IOException;

import javax.portlet.ResourceResponse;

public class JSONHelper {

    private final static String JSON_SUCCESS_RESPONSE_MESSAGE = "success";
    private final static String JSON_ERROR_RESPONSE_MESSAGE = "error";
    private final static boolean JSON_FALSE_RESPONSE_MESSAGE = false;

    public void writeSuccessResultResponseJSON(boolean successStatus, ResourceResponse response) {
        JSONObject resultResponseJson = createSuccessResultResponseJson(successStatus);
        writeResponseJSON(resultResponseJson.toString(), response);
    }

    private static JSONObject createSuccessResultResponseJson(boolean success) {
        JSONObject resultResponseJson = new JSONObject();
        resultResponseJson.put(JSON_SUCCESS_RESPONSE_MESSAGE, success);
        return resultResponseJson;
    }

    private void writeResponseJSON(String message, ResourceResponse response) {
        try {
            response.getPortletOutputStream().write(message.getBytes());
        } catch (IOException ignored) { }
    }

    public void writeErrorResultResponseJSON(String errorMessage, ResourceResponse response) {
        JSONObject resultResponseJson = createErrorResultResponseJson(errorMessage);
        writeResponseJSON(resultResponseJson.toString(), response);
    }

    private static JSONObject createErrorResultResponseJson(String errorMessage) {
        JSONObject resultResponseJson = new JSONObject();
        resultResponseJson.put(JSON_SUCCESS_RESPONSE_MESSAGE, JSON_FALSE_RESPONSE_MESSAGE);
        resultResponseJson.put(JSON_ERROR_RESPONSE_MESSAGE, errorMessage);
        return resultResponseJson;
    }

}

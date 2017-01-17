package org.xcolab.view.pages.profile.utils;

import org.json.JSONObject;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;


public class JSONHelper {

    private final static String JSON_SUCCESS_RESPONSE_MESSAGE = "success";
    private final static String JSON_ERROR_RESPONSE_MESSAGE = "error";
    private final static boolean JSON_FALSE_RESPONSE_MESSAGE = false;

    public void writeSuccessResultResponseJSON(boolean successStatus, HttpServletResponse response) {
        JSONObject resultResponseJson = createSuccessResultResponseJson(successStatus);
        writeResponseJSON(resultResponseJson.toString(), response);
    }

    private static JSONObject createSuccessResultResponseJson(boolean success) {
        JSONObject resultResponseJson = new JSONObject();
        resultResponseJson.put(JSON_SUCCESS_RESPONSE_MESSAGE, success);
        return resultResponseJson;
    }

    private void writeResponseJSON(String message, HttpServletResponse response) {
        try {
            response.getOutputStream().write(message.getBytes());
        } catch (IOException ignored) { }
    }

    public void writeErrorResultResponseJSON(String errorMessage, HttpServletResponse response) {
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

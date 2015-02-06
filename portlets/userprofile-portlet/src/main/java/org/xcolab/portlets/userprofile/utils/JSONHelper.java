package org.xcolab.portlets.userprofile.utils;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import javax.portlet.ResourceResponse;
import java.io.IOException;

/**
 * Created by Thomas on 1/14/2015.
 */
public class JSONHelper {

    private final static String JSON_SUCCESS_RESPONSE_MESSAGE = "success";
    private final static String JSON_ERROR_RESPONSE_MESSAGE = "error";
    private final static boolean JSON_FALSE_RESPONSE_MESSAGE = false;
    private final static boolean JSON_TRUE_RESPONSE_MESSAGE = true;

    private static JSONObject createSuccessResultResponseJson(boolean success){
        JSONObject resultResponseJson = JSONFactoryUtil.createJSONObject();
        resultResponseJson.put(JSON_SUCCESS_RESPONSE_MESSAGE, success);
        return resultResponseJson;
    }

    private static JSONObject createErrorResultResponseJson(String errorMessage){
        JSONObject resultResponseJson = JSONFactoryUtil.createJSONObject();
        resultResponseJson.put(JSON_SUCCESS_RESPONSE_MESSAGE, JSON_FALSE_RESPONSE_MESSAGE);
        resultResponseJson.put(JSON_ERROR_RESPONSE_MESSAGE, errorMessage);
        return resultResponseJson;
    }

    private void writeResponseJSON(String message,ResourceResponse response){
        try {
            response.getPortletOutputStream().write(message.getBytes());
        } catch (IOException e) {
        }
    }
    public void writeSuccessResultResponseJSON(boolean successStatus, ResourceResponse response){
            JSONObject resultResponseJson = createSuccessResultResponseJson(successStatus);
            writeResponseJSON(resultResponseJson.toString(), response);
    }

    public void writeErrorResultResponseJSON(String errorMessage, ResourceResponse response){
        JSONObject resultResponseJson = createErrorResultResponseJson(errorMessage);
        writeResponseJSON(resultResponseJson.toString(), response);
    }

}

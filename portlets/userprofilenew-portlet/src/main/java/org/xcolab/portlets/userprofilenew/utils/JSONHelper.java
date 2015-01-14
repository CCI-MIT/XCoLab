package org.xcolab.portlets.userprofilenew.utils;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import javax.portlet.ResourceResponse;
import java.io.IOException;

/**
 * Created by Thomas on 1/14/2015.
 */
public class JSONHelper {

    private final static String JSON_RESPONSE_MESSAGE = "success";

    private JSONObject createResultResponseJson(boolean success){

        JSONObject resultResponseJson = JSONFactoryUtil.createJSONObject();
        resultResponseJson.put(JSON_RESPONSE_MESSAGE, success);
        return resultResponseJson;

    }

    public void writeResultResponseJSON(boolean successStatus, ResourceResponse response){

        try {
            JSONObject resultResponseJson = createResultResponseJson(successStatus);
            response.getPortletOutputStream().write(resultResponseJson.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

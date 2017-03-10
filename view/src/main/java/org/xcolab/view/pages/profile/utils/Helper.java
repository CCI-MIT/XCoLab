package org.xcolab.view.pages.profile.utils;

import org.xcolab.view.util.entity.portlet.session.SessionErrors;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Helper {

    private static final String COLLAB_URL_PARAMETER_PREFIX = "_collab_param";

    public static String removeParamFromRequestStr(String requestStr, String param) {
        return requestStr == null ? null : requestStr.replaceAll("&?" + param + "=[^&#]*", "");
    }

    public static String getUrlParameterKey(String key) {
        return COLLAB_URL_PARAMETER_PREFIX + key;
    }

    public static String modifyRedirectUrl(String redirect, HttpServletRequest request, Map<String, String> parameters)
            throws UnsupportedEncodingException {

        // add error messages to redirect url
        StringBuilder sb = new StringBuilder();
        // remove error info from url
        String[] locationAndHash = redirect.split("#");
        String[] locationAndQueryString = locationAndHash[0].split("\\?");

        sb.append(locationAndQueryString[0]);
        sb.append("?");


        if (locationAndQueryString.length > 1 && !locationAndQueryString[1].isEmpty()) {
            sb.append(locationAndQueryString[1]);
            sb.append("&");
        }

        Iterator<String> iter = SessionErrors.iterator(request);
        boolean appendAnd = false;

        while (iter.hasNext()) {
            if (appendAnd) {
                sb.append("&");
            }

            sb.append("signinRegError=");
            String exception = iter.next();
            sb.append(exception.substring(exception.lastIndexOf(".") + 1));

            appendAnd = true;
        }

        for (String key : parameters.keySet()) {
            if (appendAnd) {
                sb.append("&");
            }
            appendAnd = true;
            sb.append(String.format("%s=%s", key, URLEncoder.encode(parameters.get(key), "UTF-8")));
        }

        if (locationAndHash.length > 1) {
            sb.append("#");
            sb.append(locationAndHash[1]);
        }

        redirect = sb.toString();

        return redirect;
    }
}

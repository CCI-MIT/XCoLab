package org.xcolab.view.util.entity;

import org.xcolab.util.exceptions.InternalException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public final class HttpUtils {

    public static String addParameter(String url, String name, String value) {
        if (url == null) {
            return null;
        } else {
            String[] urlArray = stripURLAnchor(url, "#");
            url = urlArray[0];
            String anchor = urlArray[1];
            StringBuilder sb = new StringBuilder(7);
            sb.append(url);
            if(url.indexOf(63) == -1) {
                sb.append("?");
            } else if(!url.endsWith("?") && !url.endsWith("&")) {
                sb.append("&");
            }

            sb.append(name);
            sb.append("=");
            sb.append( encodeURL(value));
            sb.append(anchor);
            return sb.toString();
        }
    }

    public static String encodeURL(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new InternalException(e);
        }

    }

    public static String[] stripURLAnchor(String url, String separator) {
        String anchor = "";
        int pos = url.indexOf(separator);
        if(pos != -1) {
            anchor = url.substring(pos);
            url = url.substring(0, pos);
        }

        return new String[]{url, anchor};
    }
}

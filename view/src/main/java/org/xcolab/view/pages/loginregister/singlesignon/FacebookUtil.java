package org.xcolab.view.pages.loginregister.singlesignon;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.admin.enums.PlatformAttributeKey;
import org.xcolab.util.http.RequestHelper;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.exceptions.translation.TranslationErrorHandler;
import org.xcolab.util.http.exceptions.translation.facebook.FacebookExceptionTranslator;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

public final class FacebookUtil {

    private static final Logger _log = LoggerFactory.getLogger(FacebookUtil.class);

    public static final String FB_PROFILE_PIC_URL_FORMAT_STRING =
            "https://graph.facebook.com/%d/?fields=picture.type(large)";
    public static final String AUTH_URL = "https://graph.facebook.com/oauth/authorize";

    private static final String GRAPH_URL = "https://graph.facebook.com";
    private static final String TOKEN_URL = "https://graph.facebook.com/oauth/access_token";

    private static final RequestHelper requestHelper = new RequestHelper(
            new TranslationErrorHandler(new FacebookExceptionTranslator()));

    private FacebookUtil() {
    }

    /**
     * Get the real image URL
     */
    public static String getFacebookPictureURLString(String fbProfilePictureURL) {
        try {
            //TODO: this doesn't work -> requires token!
            // Get real facebook image URL
            InputStream is = new URL(fbProfilePictureURL).openStream();
            StringWriter writer = new StringWriter();
            IOUtils.copy(is, writer, "UTF-8");
            String json = writer.toString();

            JSONObject fbJson = new JSONObject(json);
            return fbJson.getJSONObject("picture").getJSONObject("data").getString("url");
        } catch (JSONException | IOException e) {
            _log.error("Error while getting facebook picture url", e);
        }

        return null;
    }

    public static String getAccessToken(HttpServletRequest request, String code) {
        UriBuilder uriBuilder = new UriBuilder(UriComponentsBuilder.fromHttpUrl(TOKEN_URL))
                .queryParam("client_id", ConfigurationAttributeKey.FACEBOOK_APPLICATION_ID.get())
                .queryParam("redirect_uri", getAuthRedirectURL(request))
                .queryParam("client_secret", ConfigurationAttributeKey.FACEBOOK_APPLICATION_SECRET.get())
                .queryParam("code", code);
        final String requestResult = requestHelper.post(uriBuilder, null, String.class);

        if (StringUtils.isEmpty(requestResult)) {
            throw new FacebookResponseException("Token request returned empty string");
        }
        JSONObject json = new JSONObject(requestResult);
        if (!json.has("access_token")) {
            throw new FacebookResponseException("No access token returned.");
        }
        return json.getString("access_token");
    }

    public static JSONObject getGraphResources(String path, String accessToken, String fields) {
        UriBuilder uriBuilder = new UriBuilder(UriComponentsBuilder.fromHttpUrl(GRAPH_URL + path))
                .queryParam("access_token", accessToken)
                .queryParam("fields", fields);
        final String json = requestHelper.getUnchecked(uriBuilder, String.class);
        try {
            return new JSONObject(json);
        } catch (JSONException e) {
            return null;
        }
    }

    public static String getAuthRedirectURL(HttpServletRequest request) {
        return PlatformAttributeKey.PLATFORM_COLAB_URL.get() + SsoEndpoint.FACEBOOK_CALLBACK.getUrl();
    }

    private static class FacebookResponseException extends RuntimeException {

        public FacebookResponseException(String message) {
            super(message);
        }
    }
}

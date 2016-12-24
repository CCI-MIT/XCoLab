package org.xcolab.view.pages.loginregister.singlesignon;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.json.JSONException;
import org.json.JSONObject;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.util.exceptions.InternalException;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.regex.Pattern;

/**
 * Requested from https://github.com/mdanter/OAuth2v1
 * <p/>
 * <p/>
 * A helper class for Google's OAuth2 authentication API.
 *
 * @author Matyas Danter
 * @version 20130224
 */
public final class GoogleAuthHelper {

    // start google authentication constants
    private static final Collection<String> SCOPE = Arrays.asList(
            "https://www.googleapis.com/auth/userinfo.profile;https://www.googleapis.com/auth/userinfo.email"
                    .split(";"));
    private static final String USER_INFO_URL = "https://www.googleapis.com/oauth2/v1/userinfo";
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    // end google authentication constants
    private final GoogleAuthorizationCodeFlow flow;
    private String stateToken;
    private final String redirectUri;

    /**
     * Constructor initializes the Google Authorization Code Flow with CLIENT ID, SECRET, and SCOPE
     */
    public GoogleAuthHelper(String redirectURI) {
        this.redirectUri = redirectURI;
        flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT,
                JSON_FACTORY, ConfigurationAttributeKey.GOOGLE_AUTH_CLIENT_ID.get(),
                ConfigurationAttributeKey.GOOGLE_AUTH_CLIENT_SECRET.get(),
                SCOPE).build();

        generateStateToken();
    }

    /**
     * Generates a secure state token
     */
    private void generateStateToken() {
        SecureRandom sr1 = new SecureRandom();

        stateToken = "google;" + sr1.nextInt();
    }

    /**
     * Builds a login URL based on client ID, secret, callback URI, and scope
     */
    public String buildLoginUrl() {

        final GoogleAuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();

        return url.setRedirectUri(redirectUri).setState(stateToken).build();
    }

    /**
     * Accessor for state token
     */
    public String getStateToken() {
        return stateToken;
    }

    /**
     * Expects an Authentication Code, and makes an authenticated request for the user's profile information
     *
     * @param authCode authentication code provided by google
     * @return JSON formatted user profile information
     */
    public JSONObject getUserInfoJson(final String authCode) throws IOException {

        final GoogleTokenResponse
                response = flow.newTokenRequest(authCode).setRedirectUri(redirectUri).execute();

        final Credential credential = flow.createAndStoreCredential(response, null);
        final HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(credential);
        // Make an authenticated request
        final GenericUrl url = new GenericUrl(USER_INFO_URL);
        final HttpRequest request = requestFactory.buildGetRequest(url);
        request.getHeaders().setContentType("application/json");
        final String jsonIdentity = request.execute().parseAsString();

        JSONObject userInfo = null;

        try {
            userInfo = new JSONObject(jsonIdentity);

            // Get openid token
            String idTokenPayload = deserialize(response.getIdToken());
            JSONObject idTokenJson = new JSONObject(idTokenPayload);

            userInfo.put("openid_id", idTokenJson.getString("openid_id"));
        } catch (JSONException e) {
            throw new InternalException(e);
        }

        return userInfo;
    }

    private String deserialize(String tokenString) {
        String[] pieces = splitTokenString(tokenString);
        String jwtPayloadSegment = pieces[1];
        try {
            JsonParser parser = JSON_FACTORY.createJsonParser(decodeUrlSafe(jwtPayloadSegment));
            return parser.parse(String.class);
        } catch (IOException e) {
            throw new InternalException(e);
        }
    }

    /**
     * @param tokenString The original encoded representation of a JWT
     * @return Three components of the JWT as an array of strings
     */
    private String[] splitTokenString(String tokenString) {
        String[] pieces = tokenString.split(Pattern.quote("."));
        if (pieces.length != 3) {
            throw new IllegalStateException("Expected JWT to have 3 segments separated by '"
                    + "." + "', but it has " + pieces.length + " segments");
        }
        return pieces;
    }

    private String decodeUrlSafe(String in) {
        byte[] data = in.getBytes();
        byte[] encoded = Arrays.copyOf(data, data.length);
        for (int i = 0; i < encoded.length; i++) {
            if (encoded[i] == '-') {
                encoded[i] = '+';
            } else if (encoded[i] == '_') {
                encoded[i] = '/';
            }
        }
        return new String(Base64.getDecoder().decode(encoded));
    }

}

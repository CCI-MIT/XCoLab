package org.xcolab.service.members.util.email;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConnectorEmmaAPI {

    private static final String EMMA_USER_ACCOUNT_ACTIVE_STATUS = "a";
    private static final String CONTENT_TYPE = "application/json";
    private static final String CHARSET = java.nio.charset.StandardCharsets.UTF_8.name();
    private String myEmmaApiBaseUrl;

    private final AccountDetailsEmmaAPI accountDetailsEmmaAPI;

    @Autowired
    public ConnectorEmmaAPI(AccountDetailsEmmaAPI accountDetailsEmmaAPI) {
        this.accountDetailsEmmaAPI = accountDetailsEmmaAPI;
    }

    private String getMyEmmaApiBaseUrl() {
        if (myEmmaApiBaseUrl == null) {
            myEmmaApiBaseUrl = "https://api.e2ma.net/" + accountDetailsEmmaAPI.getAccountId();
        }
        return myEmmaApiBaseUrl;
    }

    public boolean unSubscribeUserWithEmail(String email) throws IOException {
        JSONObject memberDetails = getUserJSONfromEmail(email);
        return !memberDetails.has("member_id") || unSubscribeUserWithuserId(memberDetails.getLong("member_id"));
    }

    private boolean unSubscribeUserWithuserId(long emmauserId) throws IOException {
        boolean unsubscribeSuccessful = false;

        if (accountDetailsEmmaAPI.isEnabled()) {
            try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
                HttpUriRequest newsletterSubscribeRequest = createDeleteWithAuthorization(getMyEmmaApiBaseUrl() + "/members/" + emmauserId,
                        CONTENT_TYPE, CHARSET, accountDetailsEmmaAPI.getEncodedAuthorization());

                try (CloseableHttpResponse newsletterSubscribeResponse = httpclient.execute(newsletterSubscribeRequest)) {
                    if (newsletterSubscribeResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        HttpEntity entity = newsletterSubscribeResponse.getEntity();
                        unsubscribeSuccessful = Boolean.parseBoolean(EntityUtils.toString(entity));
                        EntityUtils.consume(entity);
                    }
                }
            }
        }
        return unsubscribeSuccessful;
    }

    public JSONObject subscribeUserWithEmail(String email) throws IOException {

        JSONObject jsonSubscribeInformation = new JSONObject();
        JSONObject memberDetails = new JSONObject();

        if (accountDetailsEmmaAPI.isEnabled()) {
            JSONArray groupIds = new JSONArray();
            groupIds.put(accountDetailsEmmaAPI.getGroupId());
            jsonSubscribeInformation.put("email", email);
            jsonSubscribeInformation.put("group_ids", groupIds);

            try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
                HttpUriRequest newsletterSubscribeRequest = createPostWithAuthorizationForJSONObject(getMyEmmaApiBaseUrl() + "/members/add",
                        CONTENT_TYPE, CHARSET, accountDetailsEmmaAPI.getEncodedAuthorization(), jsonSubscribeInformation);

                try (CloseableHttpResponse newsletterSubscribeResponse = httpclient.execute(newsletterSubscribeRequest)) {
                    if (newsletterSubscribeResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        HttpEntity entity = newsletterSubscribeResponse.getEntity();
                        memberDetails = new JSONObject(EntityUtils.toString(entity));
                        EntityUtils.consume(entity);
                    }
                }
            }
        }
        return memberDetails;
    }

    public JSONObject getUserJSONfromEmail(String email) throws IOException {
        JSONObject memberDetails = new JSONObject();
        if (accountDetailsEmmaAPI.isEnabled()) {
            try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
                HttpGet getUserDetails = createGetWithAuthorization(getMyEmmaApiBaseUrl() + "/members/email/" + email,
                        CONTENT_TYPE, CHARSET, accountDetailsEmmaAPI.getEncodedAuthorization());

                try (CloseableHttpResponse getUserDetailsResponse = httpclient.execute(getUserDetails)) {
                    if (getUserDetailsResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        HttpEntity entity = getUserDetailsResponse.getEntity();
                        memberDetails = new JSONObject(EntityUtils.toString(entity));
                        EntityUtils.consume(entity);
                    }
                } catch (JSONException ignored) {
                }
            }
        }
        return memberDetails;
    }

    public static boolean hasUserActiveSubscription(JSONObject memberDetails, boolean isNewUser) {
        if (isNewUser) {
            return memberDetails.has("status") && memberDetails.getString("status")
                    .equals(EMMA_USER_ACCOUNT_ACTIVE_STATUS);
        }
        return memberDetails.has("member_status_id") && memberDetails.getString("member_status_id")
                .equals(EMMA_USER_ACCOUNT_ACTIVE_STATUS);
    }

    private static HttpGet createGetWithAuthorization(
            String url, String contentType, String charset, String encodedAuthorization) {

        HttpGet get = new HttpGet(url);
        get.setHeader(HttpHeaders.CONTENT_TYPE, contentType);
        get.setHeader(HttpHeaders.ACCEPT_CHARSET, charset);
        get.setHeader(HttpHeaders.AUTHORIZATION, encodedAuthorization);
        return  get;
    }

    private static HttpDelete createDeleteWithAuthorization(
            String url, String contentType, String charset, String encodedAuthorization) {

        HttpDelete delete = new HttpDelete(url);
        delete.setHeader(HttpHeaders.CONTENT_TYPE, contentType);
        delete.setHeader(HttpHeaders.ACCEPT_CHARSET, charset);
        delete.setHeader(HttpHeaders.AUTHORIZATION, encodedAuthorization);
        return delete;
    }

    private static HttpUriRequest createPostWithAuthorizationForJSONObject(
            String url, String contentType, String charset, String encodedAuthorization, JSONObject params) {

        HttpPost post = new HttpPost(url);
        post.setHeader(HttpHeaders.CONTENT_TYPE, contentType);
        post.setHeader(HttpHeaders.ACCEPT_CHARSET, charset);
        post.setHeader(HttpHeaders.AUTHORIZATION, encodedAuthorization);
        post.setEntity(createStringEntity(params, contentType, charset));
        return post;
    }

    private static HttpEntity createStringEntity(JSONObject params, String contentType, String charset) {
        StringEntity se = new StringEntity(params.toString(), charset);
        se.setContentType(contentType+"; charset=" + charset);
        return se;
    }
}

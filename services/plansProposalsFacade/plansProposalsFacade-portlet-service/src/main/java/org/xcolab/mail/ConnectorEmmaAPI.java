package org.xcolab.mail;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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

import java.io.IOException;

/**
 * Created by Thomas on 1/12/2015.
 */
public class ConnectorEmmaAPI {

    private final String charset = java.nio.charset.StandardCharsets.UTF_8.name();
    private final String contentType = "application/json";
    private final String myEmmaApiBaseUrl;

    private final AccountDetailsEmmaAPI accountDetailsEmmaAPI;

    public ConnectorEmmaAPI() {
        accountDetailsEmmaAPI = new AccountDetailsEmmaAPI();
        myEmmaApiBaseUrl = "https://api.e2ma.net/" + accountDetailsEmmaAPI.getAccountId();
    }

    public boolean unSubscribeMemberWithEmail(String email) throws IOException {

        JSONObject memberDetails = getMemberJSONfromEmail(email);
        return !memberDetails.has("member_id") || unSubscribeMemberWithMemberId(memberDetails.getString("member_id"));
    }

    private boolean unSubscribeMemberWithMemberId(String memberId) throws IOException {
        boolean unsubscribeSuccessful = false;

        if (accountDetailsEmmaAPI.isEnabled()) {
            try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
                HttpUriRequest newsletterSubscribeRequest = createDeleteWithAuthorization(myEmmaApiBaseUrl + "/members/" + memberId,
                        contentType, charset, accountDetailsEmmaAPI.getEncodedAuthorization());

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

    public JSONObject subscribeMemberWithEmail(String email) throws IOException, JSONException {

        JSONObject jsonSubscribeInformation = JSONFactoryUtil.createJSONObject();
        JSONObject memberDetails = JSONFactoryUtil.createJSONObject();

        if (accountDetailsEmmaAPI.isEnabled()) {
            JSONArray groupIds = JSONFactoryUtil.createJSONArray();
            groupIds.put(accountDetailsEmmaAPI.getGroupId());
            jsonSubscribeInformation.put("email", email);
            jsonSubscribeInformation.put("group_ids", groupIds);

            try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
                HttpUriRequest newsletterSubscribeRequest = createPostWithAuthorizationForJSONObject(myEmmaApiBaseUrl + "/members/add",
                        contentType, charset, accountDetailsEmmaAPI.getEncodedAuthorization(), jsonSubscribeInformation);

                try (CloseableHttpResponse newsletterSubscribeResponse = httpclient.execute(newsletterSubscribeRequest)) {
                    if (newsletterSubscribeResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        HttpEntity entity = newsletterSubscribeResponse.getEntity();
                        memberDetails = JSONFactoryUtil.createJSONObject(EntityUtils.toString(entity));
                        EntityUtils.consume(entity);
                    }
                }
            }
        }
        return memberDetails;
    }

    public JSONObject getMemberJSONfromEmail(String email) throws IOException {
        JSONObject memberDetails = JSONFactoryUtil.createJSONObject();
        if (accountDetailsEmmaAPI.isEnabled()) {
            try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
                HttpGet getMemberDetails = createGetWithAuthorization(myEmmaApiBaseUrl + "/members/email/" + email,
                        contentType, charset, accountDetailsEmmaAPI.getEncodedAuthorization());

                try (CloseableHttpResponse getMemberDetailsResponse = httpclient.execute(getMemberDetails)) {
                    if (getMemberDetailsResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        HttpEntity entity = getMemberDetailsResponse.getEntity();
                        memberDetails = JSONFactoryUtil.createJSONObject(EntityUtils.toString(entity));
                        EntityUtils.consume(entity);
                    }
                } catch (JSONException ignored) {
                }
            }
        }
        return memberDetails;
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

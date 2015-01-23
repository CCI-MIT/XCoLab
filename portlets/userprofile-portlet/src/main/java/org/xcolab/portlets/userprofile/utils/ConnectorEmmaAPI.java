package org.xcolab.portlets.userprofile.utils;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.xcolab.portlets.userprofile.entity.AccountDetailsEmmaAPI;

import javax.portlet.PortletRequest;
import java.io.IOException;

/**
 * Created by Thomas on 1/12/2015.
 */
public class ConnectorEmmaAPI {

    final private String charset = java.nio.charset.StandardCharsets.UTF_8.name();
    final private String contentType = "application/json";
    private String myEmmaApiBaseUrl;

    private AccountDetailsEmmaAPI accountDetailsEmmaAPI;

    public ConnectorEmmaAPI(PortletRequest request){
        accountDetailsEmmaAPI = new AccountDetailsEmmaAPI(request.getPreferences());
        myEmmaApiBaseUrl = "https://api.e2ma.net/" + accountDetailsEmmaAPI.getAccountId();
    }

    public boolean unSubscribeMemberWithEmail(String email) throws IOException{

        JSONObject memberDetails = getMemberJSONfromEmail(email);
        if(memberDetails.has("member_id")){
            return unSubscribeMemberWithMemberId(memberDetails.getString("member_id"));
        }

        return true;
    }

    private boolean unSubscribeMemberWithMemberId(String memberId) throws IOException{
        CloseableHttpClient httpclient = HttpClients.createDefault();
        boolean unsubscribeSuccessfull = false;

        try{
            HttpUriRequest newsletterSubscribeRequest = createDeleteWithAuthorization(myEmmaApiBaseUrl + "/members/" + memberId,
                    contentType, charset, accountDetailsEmmaAPI.getEncodedAuthorization());

            CloseableHttpResponse newsletterSubscribeResponse = httpclient.execute(newsletterSubscribeRequest);

            try {
                if(newsletterSubscribeResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = newsletterSubscribeResponse.getEntity();
                    unsubscribeSuccessfull =  Boolean.parseBoolean(EntityUtils.toString(entity));
                    EntityUtils.consume(entity);
                }
            } finally {
                newsletterSubscribeResponse.close();
            }

        } finally {
            httpclient.close();
        }

        return unsubscribeSuccessfull;

    }

    public JSONObject subscribeMemberWithEmail(String email) throws IOException, JSONException{

        JSONObject jsonSubscribeInformation = JSONFactoryUtil.createJSONObject();
        JSONObject memberDetails = JSONFactoryUtil.createJSONObject();
        CloseableHttpClient httpclient = HttpClients.createDefault();

        JSONArray groupIds = JSONFactoryUtil.createJSONArray();
        groupIds.put(accountDetailsEmmaAPI.getGroupId());
        jsonSubscribeInformation.put("email", email);
        jsonSubscribeInformation.put("group_ids", groupIds);

        try{
            HttpUriRequest newsletterSubscribeRequest = createPostWithAuthorizationForJSONObject(myEmmaApiBaseUrl + "/members/add",
                    contentType, charset, accountDetailsEmmaAPI.getEncodedAuthorization(), jsonSubscribeInformation);

             CloseableHttpResponse newsletterSubscribeResponse = httpclient.execute(newsletterSubscribeRequest);

            try {
                if(newsletterSubscribeResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = newsletterSubscribeResponse.getEntity();
                    memberDetails =  JSONFactoryUtil.createJSONObject(EntityUtils.toString(entity));
                    EntityUtils.consume(entity);
                }
            } finally {
                newsletterSubscribeResponse.close();
            }

        } finally {
            httpclient.close();
        }

        return memberDetails;

    }

    public JSONObject getMemberJSONfromEmail(String email) throws IOException {
        JSONObject memberDetails = JSONFactoryUtil.createJSONObject();
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try{
            HttpGet getMemberDetails=createGetWithAuthorization( myEmmaApiBaseUrl + "/members/email/" + email,
                    contentType, charset, accountDetailsEmmaAPI.getEncodedAuthorization());

            CloseableHttpResponse getMemberDetailsResponse = httpclient.execute(getMemberDetails);

            try {
                if(getMemberDetailsResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = getMemberDetailsResponse.getEntity();
                    memberDetails =  JSONFactoryUtil.createJSONObject(EntityUtils.toString(entity));
                    EntityUtils.consume(entity);
                }
            } catch (JSONException e){

            } finally {
                getMemberDetailsResponse.close();
            }

        } finally {
            httpclient.close();
        }

        return memberDetails;
    }

    private static HttpGet createGetWithAuthorization(
            String url, String contentType, String charset, String encodedAuthorization){

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

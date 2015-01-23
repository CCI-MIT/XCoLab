package org.xcolab.portlets.userprofile.entity;

import org.apache.commons.codec.binary.Base64;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;
import java.io.IOException;

/**
 * Created by Thomas on 1/14/2015.
 */
public class AccountDetailsEmmaAPI {

    private String accountId;
    private String groupId;
    private String publicApiKey;
    private String privateApiKey;
    private String encodedAuthorization;

    private final static String ACCOUNT_ID_PREF = "ACCOUNT_ID";
    private final static String GROUP_ID_PREF = "GROUP_ID";
    private final static String PUBLIC_API_KEY_PREF = "PUBLIC_API_KEY";
    private final static String PRIVATE_API_KEY_PREF = "PRIVATE_API_KEY";
    private final static String KEY_NOT_SET = "KEY_NOT_SET";

    public AccountDetailsEmmaAPI() {
    }

    public AccountDetailsEmmaAPI(PortletPreferences prefs){

        accountId = prefs.getValue(ACCOUNT_ID_PREF, KEY_NOT_SET);
        groupId = prefs.getValue(GROUP_ID_PREF, KEY_NOT_SET);
        publicApiKey = prefs.getValue(PUBLIC_API_KEY_PREF, KEY_NOT_SET);
        privateApiKey = prefs.getValue(PRIVATE_API_KEY_PREF, KEY_NOT_SET);
        encodedAuthorization = "Basic " + new Base64().encodeToString((publicApiKey + ":" + privateApiKey).getBytes()).trim();

    }

    public void persistAccountDetailsEmmaAPI(PortletPreferences prefs) throws ReadOnlyException, ValidatorException, IOException {

        prefs.setValue(ACCOUNT_ID_PREF, accountId);
        prefs.setValue(GROUP_ID_PREF, groupId);
        prefs.setValue(PUBLIC_API_KEY_PREF, publicApiKey);
        prefs.setValue(PRIVATE_API_KEY_PREF, privateApiKey);
        prefs.store();

    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getPublicApiKey() {
        return publicApiKey;
    }

    public void setPublicApiKey(String publicApiKey) {
        this.publicApiKey = publicApiKey;
    }

    public String getPrivateApiKey() {
        return privateApiKey;
    }

    public void setPrivateApiKey(String privateApiKey) {
        this.privateApiKey = privateApiKey;
    }

    public String getEncodedAuthorization() {
        return encodedAuthorization;
    }

}

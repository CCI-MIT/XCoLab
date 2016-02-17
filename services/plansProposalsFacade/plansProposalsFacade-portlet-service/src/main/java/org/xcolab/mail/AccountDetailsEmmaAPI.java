package org.xcolab.mail;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.xcolab.utils.PropertiesUtils;

/**
 * Created by Thomas on 1/14/2015.
 */
public class AccountDetailsEmmaAPI {

    private String accountId;
    private String groupId;
    private String publicApiKey;
    private String privateApiKey;
    private final String encodedAuthorization;

    private final static String ACCOUNT_ID_PROPERTY_NAME = "com.xcolab.mail.e2ma.accountId";
    private final static String GROUP_ID_PROPERTY_NAME = "com.xcolab.mail.e2ma.groupId";
    private final static String PUBLIC_API_KEY_PROPERTY_NAME = "com.xcolab.mail.e2ma.publicApiKey";
    private final static String PRIVATE_API_KEY_PROPERTY_NAME = "com.xcolab.mail.e2ma.privateApiKey";

    public AccountDetailsEmmaAPI() {
        accountId = PropertiesUtils.get(ACCOUNT_ID_PROPERTY_NAME);
        groupId = PropertiesUtils.get(GROUP_ID_PROPERTY_NAME);
        publicApiKey = PropertiesUtils.get(PUBLIC_API_KEY_PROPERTY_NAME);
        privateApiKey = PropertiesUtils.get(PRIVATE_API_KEY_PROPERTY_NAME);
        encodedAuthorization = "Basic " + new Base64().encodeToString((publicApiKey + ":" + privateApiKey).getBytes()).trim();
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

    public boolean isEnabled() {
        return StringUtils.isNotBlank(accountId)
                && StringUtils.isNotBlank(groupId)
                && StringUtils.isNotBlank(publicApiKey)
                && StringUtils.isNotBlank(privateApiKey);
    }

}

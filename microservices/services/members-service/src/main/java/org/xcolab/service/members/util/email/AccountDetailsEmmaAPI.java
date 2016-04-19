package org.xcolab.service.members.util.email;

import com.liferay.portal.kernel.exception.SystemException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.xcolab.enums.ConfigurationAttributeKey;

public class AccountDetailsEmmaAPI {

    private String accountId;
    private String groupId;
    private String publicApiKey;
    private String privateApiKey;
    private final String encodedAuthorization;

    public AccountDetailsEmmaAPI() throws SystemException {
        accountId = ConfigurationAttributeKey.MY_EMMA_ACCOUNT_ID.getStringValue();
        groupId = ConfigurationAttributeKey.MY_EMMA_GROUP_ID.getStringValue();
        publicApiKey = ConfigurationAttributeKey.MY_EMMA_PUBLIC_API_KEY.getStringValue();
        privateApiKey = ConfigurationAttributeKey.MY_EMMA_PRIVATE_API_KEY.getStringValue();
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

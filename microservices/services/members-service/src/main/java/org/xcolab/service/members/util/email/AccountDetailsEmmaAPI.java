package org.xcolab.service.members.util.email;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.admin.exceptions.ConfigurationAttributeNotFoundException;

@Component
@PropertySource({"classpath:application.properties", "file:${user.home}/.xcolab.application.properties"})
public class AccountDetailsEmmaAPI {

    @Autowired
    private Environment env;

    private boolean enabled;
    private boolean initialized;
    private String accountId;
    private String groupId;
    private String publicApiKey;
    private String privateApiKey;
    private String encodedAuthorization;

    private void init() {
        if (!initialized) {
            enabled = ConfigurationAttributeKey.IS_MY_EMMA_ACTIVE.getBooleanValue()
                    && "production".equalsIgnoreCase(env.getProperty("environment"));
            if (enabled) {
                try {
                    accountId = ConfigurationAttributeKey.MY_EMMA_ACCOUNT_ID.getStringValue();
                    groupId = ConfigurationAttributeKey.MY_EMMA_GROUP_ID.getStringValue();
                    publicApiKey = ConfigurationAttributeKey.MY_EMMA_PUBLIC_API_KEY
                            .getStringValue();
                    privateApiKey = ConfigurationAttributeKey.MY_EMMA_PRIVATE_API_KEY
                            .getStringValue();
                } catch (ConfigurationAttributeNotFoundException e) {
                    accountId = "";
                    groupId = "";
                    publicApiKey = "";
                    privateApiKey = "";
                }
                if (StringUtils.isBlank(accountId) || StringUtils.isBlank(groupId)
                        || StringUtils.isBlank(publicApiKey)
                        || StringUtils.isNotBlank(privateApiKey)) {
                    throw new IllegalArgumentException("Invalid MyEmma Configuration - "
                            + "please provide all attribute or deactivate MyEmma");
                }
                encodedAuthorization = "Basic " + new Base64()
                        .encodeToString((publicApiKey + ":" + privateApiKey).getBytes()).trim();

            }
            initialized = true;
        }
    }

    public String getAccountId() {
        init();
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getGroupId() {
        init();
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getPublicApiKey() {
        init();
        return publicApiKey;
    }

    public void setPublicApiKey(String publicApiKey) {
        this.publicApiKey = publicApiKey;
    }

    public String getPrivateApiKey() {
        init();
        return privateApiKey;
    }

    public void setPrivateApiKey(String privateApiKey) {
        this.privateApiKey = privateApiKey;
    }

    public String getEncodedAuthorization() {
        init();
        return encodedAuthorization;
    }

    public boolean isEnabled() {
        init();
        return enabled;
    }
}

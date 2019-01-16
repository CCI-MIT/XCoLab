package org.xcolab.service.members.util.email;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.commons.attributes.exceptions.AttributeNotFoundException;

@Component
@PropertySource({"file:${user.home}/.xcolab.application.properties"})
public class AccountDetailsEmmaAPI {

    private static final Logger log = LoggerFactory.getLogger(AccountDetailsEmmaAPI.class);

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
            final String environmentProperty = env.getProperty("environment");
            final boolean isMyEmmaActive = ConfigurationAttributeKey.IS_MY_EMMA_ACTIVE.get();
            enabled = isMyEmmaActive && "production".equalsIgnoreCase(environmentProperty);
            if (enabled) {
                log.info("MyEmma configuration enabled, retrieving account details");
                try {
                    accountId = ConfigurationAttributeKey.MY_EMMA_ACCOUNT_ID.get();
                    groupId = ConfigurationAttributeKey.MY_EMMA_GROUP_ID.get();
                    publicApiKey = ConfigurationAttributeKey.MY_EMMA_PUBLIC_API_KEY
                            .get();
                    privateApiKey = ConfigurationAttributeKey.MY_EMMA_PRIVATE_API_KEY
                            .get();
                } catch (AttributeNotFoundException e) {
                    accountId = "";
                    groupId = "";
                    publicApiKey = "";
                    privateApiKey = "";
                }
                if (StringUtils.isBlank(accountId)
                        || StringUtils.isBlank(groupId)
                        || StringUtils.isBlank(publicApiKey)
                        || StringUtils.isBlank(privateApiKey)) {
                    throw new IllegalArgumentException("Invalid MyEmma Configuration - "
                            + "please provide all attributes or deactivate MyEmma");
                }
                encodedAuthorization = "Basic " + new Base64()
                        .encodeToString((publicApiKey + ":" + privateApiKey).getBytes()).trim();

            } else {
                log.warn("MyEmma integration disabled: IS_MY_EMMA_ACTIVE = {}, environment = {}",
                        isMyEmmaActive, environmentProperty);
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

package org.xcolab.service.members.domain.messaginguserpreferences;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.user.pojo.MessagingUserPreference;

public class DefaultMessagingUserPreference extends MessagingUserPreference {

    private static final boolean EMAIL_ON_SEND = false;

    public DefaultMessagingUserPreference(long userId) {
        super();
        setUserId(userId);
        setEmailOnSend(EMAIL_ON_SEND);
        setEmailOnReceipt(ConfigurationAttributeKey.MESSAGING_EMAIL_ON_RECEIPT_DEFAULT.get());
        setEmailOnActivity(ConfigurationAttributeKey.MESSAGING_EMAIL_ON_ACTIVITY_DEFAULT.get());
        setEmailActivityDailyDigest(ConfigurationAttributeKey.MESSAGING_DAILY_DIGEST_DEFAULT.get());
    }

}

package org.xcolab.service.members.domain.messaginguserpreferences;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.model.tables.pojos.MessagingUserPreferences;

public class DefaultMessagingUserPreferences extends MessagingUserPreferences {

    private static final boolean EMAIL_ON_SEND = false;

    public DefaultMessagingUserPreferences(long memberId) {
        super();
        setUserId(memberId);
        setEmailOnSend(EMAIL_ON_SEND);
        setEmailOnReceipt(ConfigurationAttributeKey.MESSAGING_EMAIL_ON_RECEIPT_DEFAULT.get());
        setEmailOnActivity(ConfigurationAttributeKey.MESSAGING_EMAIL_ON_ACTIVITY_DEFAULT.get());
        setEmailActivityDailyDigest(ConfigurationAttributeKey.MESSAGING_DAILY_DIGEST_DEFAULT.get());
    }

}

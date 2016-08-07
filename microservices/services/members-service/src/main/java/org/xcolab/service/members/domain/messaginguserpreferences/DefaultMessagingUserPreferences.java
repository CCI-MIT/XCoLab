package org.xcolab.service.members.domain.messaginguserpreferences;

import org.xcolab.model.tables.pojos.MessagingUserPreferences;

public class DefaultMessagingUserPreferences extends MessagingUserPreferences {

    private static final boolean EMAIL_ON_SEND = false;
    private static final boolean EMAIL_ON_RECEIPT = true;
    private static final boolean EMAIL_ON_ACTIVITY = true;
    private static final boolean EMAIL_ACTIVITY_DAILY_DIGEST = true;

    public DefaultMessagingUserPreferences(long memberId) {
        super();
        setUserId(memberId);
        setEmailOnSend(EMAIL_ON_SEND);
        setEmailOnReceipt(EMAIL_ON_RECEIPT);
        setEmailOnActivity(EMAIL_ON_ACTIVITY);
        setEmailActivityDailyDigest(EMAIL_ACTIVITY_DAILY_DIGEST);
    }

}

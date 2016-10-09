package org.xcolab.portlets.notificationunregister;

import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.MessagingUserPreferences;

class ActivityDailyDigestNotificationUnregisterHandler implements NotificationUnregisterHandler {

    private static final String UNSUBSCRIBE_RESPONSE_TEXT =
            "from the daily digest and all email notifications about activity on the Climate CoLab.  " +
                    "To resubscribe or manage your subscriptions, please log in to your account, select “My profile”, " +
                    "and select the “Manage” button underneath “Subscribed Activity” on the righthand side.";

    @Override
    public void unregister(Member member) {
        final MessagingUserPreferences preferences = MessagingClient
                .getMessagingPreferencesForMember(member.getUserId());
        preferences.setEmailActivityDailyDigest(false);
        preferences.setEmailOnActivity(false);
        MessagingClient.updateMessagingPreferences(preferences);
    }

    @Override
    public String getSuccessResponse() {
        return UNSUBSCRIBE_RESPONSE_TEXT;
    }
}

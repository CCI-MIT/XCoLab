package org.xcolab.view.pages.unsubscribe;

import org.xcolab.client.user.MessagingClient;
import org.xcolab.client.user.pojo.Member;
import org.xcolab.client.user.pojo.MessagingUserPreference;

class ActivityDailyDigestNotificationUnregisterHandler implements NotificationUnregisterHandler {

    private static final String UNSUBSCRIBE_RESPONSE_TEXT =
            "from the daily digest and all email notifications about activity on the Climate "
                    + "CoLab.  "
                    + "To resubscribe or manage your subscriptions, please log in to your "
                    + "account, select “My profile”, "
                    + "and select the “Manage” button underneath “Subscribed Activity” on the "
                    + "right-hand side.";

    @Override
    public void unregister(Member member) {
        final MessagingUserPreference preferences =
                MessagingClient.getMessagingPreferencesForMember(member.getId());
        preferences.setEmailActivityDailyDigest(false);
        preferences.setEmailOnActivity(false);
        MessagingClient.updateMessagingPreferences(preferences);
    }

    @Override
    public String getSuccessResponse() {
        return UNSUBSCRIBE_RESPONSE_TEXT;
    }
}

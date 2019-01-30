package org.xcolab.view.pages.unsubscribe;

import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.MessagingUserPreferenceWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;

class ActivityDailyDigestNotificationUnregisterHandler implements NotificationUnregisterHandler {

    private static final String UNSUBSCRIBE_RESPONSE_TEXT =
            "from the daily digest and all email notifications about activity on the Climate "
                    + "CoLab.  "
                    + "To resubscribe or manage your subscriptions, please log in to your "
                    + "account, select “My profile”, "
                    + "and select the “Manage” button underneath “Subscribed Activity” on the "
                    + "right-hand side.";

    @Override
    public void unregister(UserWrapper member) {
        final MessagingUserPreferenceWrapper preferences =
                StaticUserContext.getMessagingClient().getMessagingPreferences(member.getId());
        preferences.setEmailActivityDailyDigest(false);
        preferences.setEmailOnActivity(false);
        StaticUserContext.getMessagingClient().updateMessagingPreferences(member.getId(),
                preferences.getId(),preferences);
    }

    @Override
    public String getSuccessResponse() {
        return UNSUBSCRIBE_RESPONSE_TEXT;
    }
}

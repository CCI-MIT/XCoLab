package org.xcolab.portlets.notificationunregister;

import com.ext.portlet.model.MessagingIgnoredRecipients;
import com.ext.portlet.service.MessagingIgnoredRecipientsLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.members.pojo.Member;

import java.util.Date;

class MassmessagingNotificationUnregisterHandler implements NotificationUnregisterHandler {

    private static final String UNSUBSCRIBE_RESPONSE_TEXT =
            "Your address has been excluded from our newsletter recipients.";

    @Override
    public void unregister(Member user) throws SystemException {
        MessagingIgnoredRecipients ignoredRecipients = null;
        try {
            ignoredRecipients = MessagingIgnoredRecipientsLocalServiceUtil.findByUserId(user.getUserId());
        }
        catch (Exception e) {
            //
        }
        if (ignoredRecipients == null) {
            // save
            Long ignoredRecipientId = CounterLocalServiceUtil.increment(MessagingIgnoredRecipients.class.getName());
            MessagingIgnoredRecipients ignoredRecipient = MessagingIgnoredRecipientsLocalServiceUtil
                    .createMessagingIgnoredRecipients(ignoredRecipientId);

            ignoredRecipient.setUserId(user.getUserId());
            ignoredRecipient.setName(user.getScreenName());
            ignoredRecipient.setEmail(user.getEmailAddress());

            ignoredRecipient.setCreateDate(new Date());

            MessagingIgnoredRecipientsLocalServiceUtil.addMessagingIgnoredRecipients(ignoredRecipient);
        }
    }

    @Override
    public String getSuccessResponse() {
        return UNSUBSCRIBE_RESPONSE_TEXT;
    }
}

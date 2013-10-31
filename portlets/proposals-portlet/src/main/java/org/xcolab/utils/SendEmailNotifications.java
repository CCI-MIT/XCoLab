package org.xcolab.utils;


import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;

public class SendEmailNotifications implements MessageListener {

    @Override
    public void receive(Message message) throws MessageListenerException {
        try {
            ActivitySubscriptionLocalServiceUtil.sendEmailNotifications();
        }
        catch (SystemException e) {
            throw new MessageListenerException(e);
        }
        catch (PortalException e) {
            throw new MessageListenerException(e);
        }
    }

}

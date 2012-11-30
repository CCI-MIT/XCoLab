package com.ext.portlet.messaging.service.messaging;

import com.ext.portlet.messaging.service.ClpSerializer;
import com.ext.portlet.messaging.service.MessageLocalServiceUtil;
import com.ext.portlet.messaging.service.MessageRecipientStatusLocalServiceUtil;
import com.ext.portlet.messaging.service.MessageRecipientStatusServiceUtil;
import com.ext.portlet.messaging.service.MessageServiceUtil;
import com.ext.portlet.messaging.service.MessagingUserPreferencesLocalServiceUtil;
import com.ext.portlet.messaging.service.MessagingUserPreferencesServiceUtil;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;


public class ClpMessageListener extends BaseMessageListener {
    public static String getServletContextName() {
        return ClpSerializer.getServletContextName();
    }

    @Override
    protected void doReceive(Message message) throws Exception {
        String command = message.getString("command");
        String servletContextName = message.getString("servletContextName");

        if (command.equals("undeploy") &&
                servletContextName.equals(getServletContextName())) {
            MessageLocalServiceUtil.clearService();

            MessageServiceUtil.clearService();
            MessageRecipientStatusLocalServiceUtil.clearService();

            MessageRecipientStatusServiceUtil.clearService();
            MessagingUserPreferencesLocalServiceUtil.clearService();

            MessagingUserPreferencesServiceUtil.clearService();
        }
    }
}

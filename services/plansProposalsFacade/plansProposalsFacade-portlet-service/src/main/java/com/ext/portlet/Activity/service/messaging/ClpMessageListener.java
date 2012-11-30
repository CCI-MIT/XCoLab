package com.ext.portlet.Activity.service.messaging;

import com.ext.portlet.Activity.service.ActivitySubscriptionLocalServiceUtil;
import com.ext.portlet.Activity.service.ActivitySubscriptionServiceUtil;
import com.ext.portlet.Activity.service.ClpSerializer;

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
            ActivitySubscriptionLocalServiceUtil.clearService();

            ActivitySubscriptionServiceUtil.clearService();
        }
    }
}

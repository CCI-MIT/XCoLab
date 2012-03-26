package com.xcolab.services.sample.service.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

import com.xcolab.services.sample.service.ClpSerializer;
import com.xcolab.services.sample.service.SampleEntityLocalServiceUtil;
import com.xcolab.services.sample.service.SampleEntityServiceUtil;


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
            SampleEntityLocalServiceUtil.clearService();

            SampleEntityServiceUtil.clearService();
        }
    }
}

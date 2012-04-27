package org.xcolab.services.proposalsService.service.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

import org.xcolab.services.proposalsService.service.ClpSerializer;
import org.xcolab.services.proposalsService.service.FooLocalServiceUtil;
import org.xcolab.services.proposalsService.service.FooServiceUtil;


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
            FooLocalServiceUtil.clearService();

            FooServiceUtil.clearService();
        }
    }
}

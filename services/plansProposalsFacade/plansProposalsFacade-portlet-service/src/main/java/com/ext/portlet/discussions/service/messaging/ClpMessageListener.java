package com.ext.portlet.discussions.service.messaging;

import com.ext.portlet.discussions.service.ClpSerializer;
import com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.discussions.service.DiscussionCategoryGroupServiceUtil;
import com.ext.portlet.discussions.service.DiscussionCategoryLocalServiceUtil;
import com.ext.portlet.discussions.service.DiscussionCategoryServiceUtil;
import com.ext.portlet.discussions.service.DiscussionMessageFlagLocalServiceUtil;
import com.ext.portlet.discussions.service.DiscussionMessageFlagServiceUtil;
import com.ext.portlet.discussions.service.DiscussionMessageLocalServiceUtil;
import com.ext.portlet.discussions.service.DiscussionMessageServiceUtil;

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
            DiscussionCategoryLocalServiceUtil.clearService();

            DiscussionCategoryServiceUtil.clearService();
            DiscussionCategoryGroupLocalServiceUtil.clearService();

            DiscussionCategoryGroupServiceUtil.clearService();
            DiscussionMessageLocalServiceUtil.clearService();

            DiscussionMessageServiceUtil.clearService();
            DiscussionMessageFlagLocalServiceUtil.clearService();

            DiscussionMessageFlagServiceUtil.clearService();
        }
    }
}

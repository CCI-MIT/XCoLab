package com.ext.portlet.contests.service.messaging;

import com.ext.portlet.contests.service.ClpSerializer;
import com.ext.portlet.contests.service.ContestDebateLocalServiceUtil;
import com.ext.portlet.contests.service.ContestDebateServiceUtil;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.ext.portlet.contests.service.ContestPhaseColumnLocalServiceUtil;
import com.ext.portlet.contests.service.ContestPhaseColumnServiceUtil;
import com.ext.portlet.contests.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.contests.service.ContestPhaseServiceUtil;
import com.ext.portlet.contests.service.ContestServiceUtil;
import com.ext.portlet.contests.service.ContestTeamMemberLocalServiceUtil;
import com.ext.portlet.contests.service.ContestTeamMemberServiceUtil;

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
            ContestLocalServiceUtil.clearService();

            ContestServiceUtil.clearService();
            ContestDebateLocalServiceUtil.clearService();

            ContestDebateServiceUtil.clearService();
            ContestPhaseLocalServiceUtil.clearService();

            ContestPhaseServiceUtil.clearService();
            ContestPhaseColumnLocalServiceUtil.clearService();

            ContestPhaseColumnServiceUtil.clearService();
            ContestTeamMemberLocalServiceUtil.clearService();

            ContestTeamMemberServiceUtil.clearService();
        }
    }
}

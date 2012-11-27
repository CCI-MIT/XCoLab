package com.ext.portlet.models.service.messaging;

import com.ext.portlet.models.service.ClpSerializer;
import com.ext.portlet.models.service.ModelCategoryLocalServiceUtil;
import com.ext.portlet.models.service.ModelCategoryServiceUtil;
import com.ext.portlet.models.service.ModelDiscussionLocalServiceUtil;
import com.ext.portlet.models.service.ModelDiscussionServiceUtil;
import com.ext.portlet.models.service.ModelGlobalPreferenceLocalServiceUtil;
import com.ext.portlet.models.service.ModelGlobalPreferenceServiceUtil;
import com.ext.portlet.models.service.ModelInputGroupLocalServiceUtil;
import com.ext.portlet.models.service.ModelInputGroupServiceUtil;
import com.ext.portlet.models.service.ModelInputItemLocalServiceUtil;
import com.ext.portlet.models.service.ModelInputItemServiceUtil;
import com.ext.portlet.models.service.ModelOutputChartOrderLocalServiceUtil;
import com.ext.portlet.models.service.ModelOutputChartOrderServiceUtil;
import com.ext.portlet.models.service.ModelOutputItemLocalServiceUtil;
import com.ext.portlet.models.service.ModelOutputItemServiceUtil;
import com.ext.portlet.models.service.ModelPositionLocalServiceUtil;
import com.ext.portlet.models.service.ModelPositionServiceUtil;

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
            ModelCategoryLocalServiceUtil.clearService();

            ModelCategoryServiceUtil.clearService();
            ModelDiscussionLocalServiceUtil.clearService();

            ModelDiscussionServiceUtil.clearService();
            ModelGlobalPreferenceLocalServiceUtil.clearService();

            ModelGlobalPreferenceServiceUtil.clearService();
            ModelInputGroupLocalServiceUtil.clearService();

            ModelInputGroupServiceUtil.clearService();
            ModelInputItemLocalServiceUtil.clearService();

            ModelInputItemServiceUtil.clearService();
            ModelOutputChartOrderLocalServiceUtil.clearService();

            ModelOutputChartOrderServiceUtil.clearService();
            ModelOutputItemLocalServiceUtil.clearService();

            ModelOutputItemServiceUtil.clearService();
            ModelPositionLocalServiceUtil.clearService();

            ModelPositionServiceUtil.clearService();
        }
    }
}

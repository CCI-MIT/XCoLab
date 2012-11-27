package com.ext.portlet.ontology.service.messaging;

import com.ext.portlet.ontology.service.ClpSerializer;
import com.ext.portlet.ontology.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.ontology.service.FocusAreaOntologyTermLocalServiceUtil;
import com.ext.portlet.ontology.service.FocusAreaOntologyTermServiceUtil;
import com.ext.portlet.ontology.service.FocusAreaServiceUtil;
import com.ext.portlet.ontology.service.OntologySpaceLocalServiceUtil;
import com.ext.portlet.ontology.service.OntologySpaceServiceUtil;
import com.ext.portlet.ontology.service.OntologyTermEntityLocalServiceUtil;
import com.ext.portlet.ontology.service.OntologyTermEntityServiceUtil;
import com.ext.portlet.ontology.service.OntologyTermLocalServiceUtil;
import com.ext.portlet.ontology.service.OntologyTermServiceUtil;

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
            FocusAreaLocalServiceUtil.clearService();

            FocusAreaServiceUtil.clearService();
            FocusAreaOntologyTermLocalServiceUtil.clearService();

            FocusAreaOntologyTermServiceUtil.clearService();
            OntologySpaceLocalServiceUtil.clearService();

            OntologySpaceServiceUtil.clearService();
            OntologyTermLocalServiceUtil.clearService();

            OntologyTermServiceUtil.clearService();
            OntologyTermEntityLocalServiceUtil.clearService();

            OntologyTermEntityServiceUtil.clearService();
        }
    }
}

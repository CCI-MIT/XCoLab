package com.ext.portlet.plans.service.messaging;

import com.ext.portlet.plans.service.ClpSerializer;
import com.ext.portlet.plans.service.PlanAttributeFilterLocalServiceUtil;
import com.ext.portlet.plans.service.PlanAttributeFilterServiceUtil;
import com.ext.portlet.plans.service.PlanAttributeLocalServiceUtil;
import com.ext.portlet.plans.service.PlanAttributeServiceUtil;
import com.ext.portlet.plans.service.PlanColumnSettingsLocalServiceUtil;
import com.ext.portlet.plans.service.PlanColumnSettingsServiceUtil;
import com.ext.portlet.plans.service.PlanDescriptionLocalServiceUtil;
import com.ext.portlet.plans.service.PlanDescriptionServiceUtil;
import com.ext.portlet.plans.service.PlanFanLocalServiceUtil;
import com.ext.portlet.plans.service.PlanFanServiceUtil;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanItemServiceUtil;
import com.ext.portlet.plans.service.PlanMetaLocalServiceUtil;
import com.ext.portlet.plans.service.PlanMetaServiceUtil;
import com.ext.portlet.plans.service.PlanModelRunLocalServiceUtil;
import com.ext.portlet.plans.service.PlanModelRunServiceUtil;
import com.ext.portlet.plans.service.PlanPositionItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanPositionItemServiceUtil;
import com.ext.portlet.plans.service.PlanPositionLocalServiceUtil;
import com.ext.portlet.plans.service.PlanPositionServiceUtil;
import com.ext.portlet.plans.service.PlanPositionsLocalServiceUtil;
import com.ext.portlet.plans.service.PlanPositionsServiceUtil;
import com.ext.portlet.plans.service.PlanPropertyFilterLocalServiceUtil;
import com.ext.portlet.plans.service.PlanPropertyFilterServiceUtil;
import com.ext.portlet.plans.service.PlanRelatedLocalServiceUtil;
import com.ext.portlet.plans.service.PlanRelatedServiceUtil;
import com.ext.portlet.plans.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.plans.service.PlanSectionDefinitionServiceUtil;
import com.ext.portlet.plans.service.PlanSectionLocalServiceUtil;
import com.ext.portlet.plans.service.PlanSectionPlanMapLocalServiceUtil;
import com.ext.portlet.plans.service.PlanSectionPlanMapServiceUtil;
import com.ext.portlet.plans.service.PlanSectionServiceUtil;
import com.ext.portlet.plans.service.PlanTeamHistoryLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTeamHistoryServiceUtil;
import com.ext.portlet.plans.service.PlanTemplateLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTemplateSectionLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTemplateSectionServiceUtil;
import com.ext.portlet.plans.service.PlanTemplateServiceUtil;
import com.ext.portlet.plans.service.PlanTypeAttributeLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTypeAttributeServiceUtil;
import com.ext.portlet.plans.service.PlanTypeColumnLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTypeColumnServiceUtil;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTypeServiceUtil;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.ext.portlet.plans.service.PlanVoteServiceUtil;
import com.ext.portlet.plans.service.PlansFilterLocalServiceUtil;
import com.ext.portlet.plans.service.PlansFilterPositionLocalServiceUtil;
import com.ext.portlet.plans.service.PlansFilterPositionServiceUtil;
import com.ext.portlet.plans.service.PlansFilterServiceUtil;
import com.ext.portlet.plans.service.PlansUserSettingsLocalServiceUtil;
import com.ext.portlet.plans.service.PlansUserSettingsServiceUtil;

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
            PlanAttributeLocalServiceUtil.clearService();

            PlanAttributeServiceUtil.clearService();
            PlanAttributeFilterLocalServiceUtil.clearService();

            PlanAttributeFilterServiceUtil.clearService();
            PlanColumnSettingsLocalServiceUtil.clearService();

            PlanColumnSettingsServiceUtil.clearService();
            PlanDescriptionLocalServiceUtil.clearService();

            PlanDescriptionServiceUtil.clearService();
            PlanFanLocalServiceUtil.clearService();

            PlanFanServiceUtil.clearService();
            PlanItemLocalServiceUtil.clearService();

            PlanItemServiceUtil.clearService();
            PlanMetaLocalServiceUtil.clearService();

            PlanMetaServiceUtil.clearService();
            PlanModelRunLocalServiceUtil.clearService();

            PlanModelRunServiceUtil.clearService();
            PlanPositionLocalServiceUtil.clearService();

            PlanPositionServiceUtil.clearService();
            PlanPositionItemLocalServiceUtil.clearService();

            PlanPositionItemServiceUtil.clearService();
            PlanPositionsLocalServiceUtil.clearService();

            PlanPositionsServiceUtil.clearService();
            PlanPropertyFilterLocalServiceUtil.clearService();

            PlanPropertyFilterServiceUtil.clearService();
            PlanRelatedLocalServiceUtil.clearService();

            PlanRelatedServiceUtil.clearService();
            PlanSectionLocalServiceUtil.clearService();

            PlanSectionServiceUtil.clearService();
            PlanSectionDefinitionLocalServiceUtil.clearService();

            PlanSectionDefinitionServiceUtil.clearService();
            PlanSectionPlanMapLocalServiceUtil.clearService();

            PlanSectionPlanMapServiceUtil.clearService();
            PlansFilterLocalServiceUtil.clearService();

            PlansFilterServiceUtil.clearService();
            PlansFilterPositionLocalServiceUtil.clearService();

            PlansFilterPositionServiceUtil.clearService();
            PlansUserSettingsLocalServiceUtil.clearService();

            PlansUserSettingsServiceUtil.clearService();
            PlanTeamHistoryLocalServiceUtil.clearService();

            PlanTeamHistoryServiceUtil.clearService();
            PlanTemplateLocalServiceUtil.clearService();

            PlanTemplateServiceUtil.clearService();
            PlanTemplateSectionLocalServiceUtil.clearService();

            PlanTemplateSectionServiceUtil.clearService();
            PlanTypeLocalServiceUtil.clearService();

            PlanTypeServiceUtil.clearService();
            PlanTypeAttributeLocalServiceUtil.clearService();

            PlanTypeAttributeServiceUtil.clearService();
            PlanTypeColumnLocalServiceUtil.clearService();

            PlanTypeColumnServiceUtil.clearService();
            PlanVoteLocalServiceUtil.clearService();

            PlanVoteServiceUtil.clearService();
        }
    }
}

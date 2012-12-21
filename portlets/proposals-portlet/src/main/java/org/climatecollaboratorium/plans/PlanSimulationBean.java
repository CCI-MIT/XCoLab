/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.plans;

import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.plans.activity.PlanActivityKeys;

import com.ext.portlet.model.PlanItem;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

public class PlanSimulationBean {
    private Long scenario;

    private PlanItem plan;
    private PlanBean planBean;
    private boolean editing;
    private ThemeDisplay td = Helper.getThemeDisplay();

    public PlanSimulationBean(PlanItem plan, PlanBean planBean) {
        this.plan = plan;
        this.planBean = planBean;
    }

    public void update(ActionEvent e) throws SystemException, PortalException {
        if (Helper.isUserLoggedIn()) {
            PlanItemLocalServiceUtil.setScenarioId(plan, scenario, Helper.getLiferayUser().getUserId());

            SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                    PlanItem.class.getName(), plan.getPlanId(), PlanActivityKeys.EDIT_SCENARIO.id(),null, 0);
            planBean.refresh();

        }
        editing = false;
    }

    public void cancel(ActionEvent e) throws SystemException, PortalException {
        editing = false;
    }

    public Long getScenario() {
        return scenario;
    }

    public void setScenario(Long scenario) {
        this.scenario = scenario;
    }

    public boolean isEditing() {
        return editing;
    }

    public void edit(ActionEvent e) {
        editing = true;
    }

}
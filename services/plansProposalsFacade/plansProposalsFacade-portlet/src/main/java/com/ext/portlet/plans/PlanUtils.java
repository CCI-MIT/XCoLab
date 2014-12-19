package com.ext.portlet.plans;

import com.ext.portlet.model.PlanItem;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class PlanUtils {
    public static String getPlanURL(PlanItem p) throws PortalException, SystemException {
        return String.format("/web/guest/plans/-/plans/contestId/"
                + PlanItemLocalServiceUtil.getContest(p).getContestPK() + "/planId/" + p.getPlanId());

    }

}

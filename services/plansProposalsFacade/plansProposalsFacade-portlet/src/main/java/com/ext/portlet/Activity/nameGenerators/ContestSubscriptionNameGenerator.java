package com.ext.portlet.Activity.nameGenerators;

import com.ext.portlet.model.ActivitySubscription;
import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.apache.commons.lang.StringUtils;

public class ContestSubscriptionNameGenerator extends BaseSubscriptionNameGenerator {
    @Override
    public String getName(ActivitySubscription subscription) {
        try {
            Contest contest = ContestLocalServiceUtil.getContest(subscription.getClassPK());
            final String contestNameString = ContestTypeLocalServiceUtil.getContestType(contest).getContestName();
            return contest.getContestShortName() + " " + StringUtils.uncapitalize(contestNameString);
        } catch (PortalException | SystemException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected Class<?> getSupportedClass() {
        return Contest.class;
    }

}

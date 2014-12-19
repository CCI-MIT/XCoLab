package com.ext.portlet.Activity.nameGenerators;

import com.ext.portlet.model.ActivitySubscription;
import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class ContestSubscriptionNameGenerator extends BaseSubscriptionNameGenerator {
    @Override
    public String getName(ActivitySubscription subscription) {
        Contest c;
        try {
            c = ContestLocalServiceUtil.getContest(subscription.getClassPK());
            return c.getContestShortName() + " contest";
        } catch (PortalException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected Class<?> getSupportedClass() {
        return Contest.class;
    }
    
    

}

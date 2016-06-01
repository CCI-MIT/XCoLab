package org.xcolab.activityEntry;

import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.apache.commons.lang.StringUtils;
import org.xcolab.client.activities.pojo.ActivitySubscription;

import java.util.List;

public class ActivitySubscriptionNameGenerator {

    private static List<ActivitySubscriptionNameGenerator> nameGenerators;

    public static String getName(ActivitySubscription subscription) {

        if (subscription.getClassNameId().equals(ActivityEntryType.PROPOSOSAL.getPrimaryTypeId())) {
            return getNameForProposalSubscription(subscription);
        } else {
            if (subscription.getClassNameId().equals(ActivityEntryType.CONTEST.getPrimaryTypeId())) {
                return getNameForContestSubscription(subscription);
            } else {
                if (subscription.getClassNameId().equals(ActivityEntryType.DISCUSSION.getPrimaryTypeId())) {
                    return getNameForDiscussionSubscription(subscription);
                }
            }
            return null;
        }
    }
    private static String getNameForProposalSubscription(ActivitySubscription subscription){
        try {
            Contest contest = ContestLocalServiceUtil.getContest(subscription.getClassPK());
            final String contestNameString = ContestTypeLocalServiceUtil.getContestType(contest).getContestName();
            return contest.getContestShortName() + " " + StringUtils.uncapitalize(contestNameString);
        } catch (PortalException | SystemException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String getNameForContestSubscription(ActivitySubscription subscription){
        try {
            Contest contest = ContestLocalServiceUtil.getContest(subscription.getClassPK());
            final String contestNameString = ContestTypeLocalServiceUtil.getContestType(contest).getContestName();
            return contest.getContestShortName() + " " + StringUtils.uncapitalize(contestNameString);
        } catch (PortalException | SystemException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getNameForDiscussionSubscription(ActivitySubscription subscription){
        return null;
    }


}



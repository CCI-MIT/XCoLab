package com.ext.utils.subscriptions;

import com.ext.portlet.service.ProposalLocalService;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mente on 08/05/14.
 */
public class ActivitySubscriptionWhitelistProposalSupportHandler implements ActivitySubscriptionWhitelistHandler {
    @Override
    public List<Long> getWhitelistedUsers(long classPk) {
        List<Long> contributorIds = new ArrayList<>();

        try {
            List<User> contributors = ProposalLocalServiceUtil.getMembers(classPk);
            for (User contributor : contributors) {
                contributorIds.add(contributor.getUserId());
            }
        } catch (PortalException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        }

        return contributorIds;
    }
}

package com.ext.utils.subscriptions;

import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.util.exceptions.ReferenceResolutionException;

import java.util.ArrayList;
import java.util.List;

public class ActivitySubscriptionWhitelistProposalContributorHandler implements ActivitySubscriptionWhitelistHandler {
    @Override
    public List<Long> getWhitelistedUsers(long classPk) {
        List<Long> contributorIds = new ArrayList<>();

        try {
            List<User> contributors = ProposalLocalServiceUtil.getMembers(classPk);
            for (User contributor : contributors) {
                contributorIds.add(contributor.getUserId());
            }
        } catch (PortalException e) {
            throw ReferenceResolutionException.toObject(Member.class, "contributor list")
                    .fromObject(Proposal.class, classPk);
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }

        return contributorIds;
    }
}

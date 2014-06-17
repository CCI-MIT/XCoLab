package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.model.ProposalRating;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.model.User;

/**
 * Created by Manuel Thurner
 */
public class ProposalRatingWrapper {
    private ProposalRating proposalRating;


    public ProposalRatingWrapper(ProposalRating proposalRating) {
        this.proposalRating = proposalRating;
    }


    public Long getRating() {
        return this.proposalRating.getRating();
    }

    public String getComment() {
        return this.proposalRating.getComment();
    }

    public User getAuthor() throws SystemException, PortalException {
        return UserLocalServiceUtil.getUser(this.proposalRating.getUserId());
    }
}

package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalRating;
import com.ext.portlet.model.ProposalRatingType;
import com.ext.portlet.model.ProposalRatingValue;
import com.ext.portlet.service.ProposalRatingTypeLocalServiceUtil;
import com.ext.portlet.service.ProposalRatingValueLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by Manuel Thurner
 */
public class ProposalRatingsWrapper {
    private List<ProposalRatingWrapper> proposalRatings;
    private User author;

    public ProposalRatingsWrapper(long authorId, List<ProposalRating> proposalRatings) throws SystemException, PortalException {
        this(UserLocalServiceUtil.getUser(authorId), proposalRatings);
    }

    public ProposalRatingsWrapper(User author, List<ProposalRating> proposalRatings) throws SystemException, PortalException {
        List<ProposalRatingWrapper> wrapped = new ArrayList<ProposalRatingWrapper>();
        for (ProposalRating r : proposalRatings) {
            wrapped.add(new ProposalRatingWrapper(r));
        }

        //sort the list
        Collections.sort(wrapped, new Comparator<ProposalRatingWrapper>() {
            public int compare(ProposalRatingWrapper r1, ProposalRatingWrapper r2) {
                return r1.getRatingTypeId().compareTo(r2.getRatingTypeId());
            }
        });

        this.proposalRatings = wrapped;
        this.author = author;
    }


    public List<ProposalRatingWrapper> getRatings() {
        return proposalRatings;
    }

    public String getComment() {
        for (ProposalRatingWrapper r : proposalRatings) {
            if (r.unwrap().isCommentEnabled()) {
                return r.unwrap().getComment();
            }
        }
        return "";
    }

    public boolean isReviewComplete() {
        if (this.proposalRatings.size() > 0) {
            boolean result = true;
            for (ProposalRatingWrapper r : proposalRatings) {
                if (!r.unwrap().isRatingComplete()) {
                    result = false;
                }
            }
            return result;
        } else {
            return false;
        }
    }

    public User getAuthor() {
        return author;
    }
}

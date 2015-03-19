package org.xcolab.wrapper.proposal;

import com.ext.portlet.model.ProposalRating;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.apache.commons.lang.StringEscapeUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    public String getCommentEscaped() {
        String comment = this.getComment();
        comment = StringEscapeUtils.escapeHtml(comment);
        comment = comment.replaceAll("\n", "<br>");
        return comment;
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

package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.model.*;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalRatingTypeLocalServiceUtil;
import com.ext.portlet.service.ProposalRatingValueLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.apache.commons.lang.StringEscapeUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by Manuel Thurner
 */
public class ProposalRatingsWrapper {
    private final static Log _log = LogFactoryUtil.getLog(ProposalRatingsWrapper.class);
    private List<ProposalRatingWrapper> proposalRatings;
    private User author;
    private String comment;
    private ContestPhase contestPhase;

    public ProposalRatingsWrapper(long authorId, List<ProposalRating> proposalRatings, Long roundFactor) throws SystemException, PortalException {
        this(UserLocalServiceUtil.getUser(authorId), proposalRatings, roundFactor);
    }

    public ProposalRatingsWrapper(long authorId, List<ProposalRating> proposalRatings) throws SystemException, PortalException {
        this(UserLocalServiceUtil.getUser(authorId), proposalRatings);
    }

    public ProposalRatingsWrapper(User author, List<ProposalRating> proposalRatings) throws SystemException, PortalException {
        this(author, proposalRatings, 1L);
    }

    public ProposalRatingsWrapper(User author, List<ProposalRating> proposalRatings, Long roundFactor) throws SystemException, PortalException {
        List<ProposalRatingWrapper> wrapped = new ArrayList<>();
        for (ProposalRating r : proposalRatings) {
            wrapped.add(new ProposalRatingWrapper(r, roundFactor));
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
        if(comment != null){
            return comment;
        } else {
            for (ProposalRatingWrapper r : proposalRatings) {
                if (r.unwrap().isCommentEnabled()) {
                    return r.unwrap().getComment();
                }
            }
            return "";
        }
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentEscaped() {
        String comment = this.getComment();
        comment = StringEscapeUtils.escapeHtml(comment);
        comment = comment.replaceAll("\n", "<br>");
        return comment;
    }

    public void setContestPhase(ContestPhase contestPhase){
        this.contestPhase = contestPhase;
    }

    public String getContestPhase(){
        String contestPhaseTitle = "";

        try {
            if(this.contestPhase != null) {
                contestPhaseTitle = ContestPhaseLocalServiceUtil.getName(this.contestPhase);
            } else {
                for (ProposalRatingWrapper r : proposalRatings) {
                    Long contestPhaseId = r.unwrap().getContestPhaseId();
                    ContestPhase contestPhase = ContestPhaseLocalServiceUtil.getContestPhase(contestPhaseId);
                    contestPhaseTitle = ContestPhaseLocalServiceUtil.getName(contestPhase);
                    break;
                }
            }
        } catch (Exception e){
            _log.warn("Could not get phase title for rating wrapper", e);
        }

        String contestSelectionPhaseTitleAdjusted = contestPhaseTitle.replace("selection", "Evaluation");
        return contestSelectionPhaseTitleAdjusted;
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

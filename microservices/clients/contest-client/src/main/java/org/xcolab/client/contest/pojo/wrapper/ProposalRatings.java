package org.xcolab.client.contest.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.contest.StaticContestContext;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public abstract class ProposalRatings {

    private final List<ProposalRatingWrapper> ratings;

    private String comment;
    private Boolean shouldAdvance;
    private ContestPhaseWrapper contestPhase;
    private String contestPhaseTitle;

    public ProposalRatings(List<ProposalRatingWrapper> ratings, Long roundFactor) {
        List<ProposalRatingWrapper> wrapped = new ArrayList<>();
        for (ProposalRatingWrapper r : ratings) {
            wrapped.add(new ProposalRatingWrapper(r, roundFactor));
        }

        //sort the list
        wrapped.sort(Comparator.comparing(ProposalRatingWrapper::getRatingTypeId));

        this.ratings = wrapped;
    }

    public List<ProposalRatingWrapper> getRatings() {
        return ratings;
    }

    public String getComment() {
        if (comment != null) {
            return comment;
        } else {
            for (ProposalRatingWrapper r : ratings) {
                if (r.isCommentEnabled()) {
                    return r.getComment();
                }
            }
            return "";
        }
    }

    public Boolean isShouldAdvance() {
        if (shouldAdvance != null) {
            return shouldAdvance;
        } else {
            for (ProposalRatingWrapper r : ratings) {
                if (r.isCommentEnabled()) {
                    final String shouldAdvanceString = r.getOtherDataString();
                    shouldAdvance = StringUtils.isNotBlank(shouldAdvanceString)
                            ? Boolean.parseBoolean(shouldAdvanceString) : null;
                    return shouldAdvance;
                }
            }
            return null;
        }
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentEscaped() {
        String comment = this.getComment();
        //comment = org.apache.commons.lang3.StringEscapeUtils.escapeHtml(comment);
        comment = comment.replaceAll("\n", "<br>");
        return comment;
    }

    public void setContestPhase(ContestPhaseWrapper contestPhase) {
        this.contestPhase = contestPhase;
    }


    public void setContestPhaseTitle(String contestPhaseTitle) {
        this.contestPhaseTitle = contestPhaseTitle;
    }

    public String getContestPhase() {
        String contestPhaseTitleAux = "";

        if (this.contestPhase != null) {
            contestPhaseTitleAux = this.contestPhaseTitle;
        } else {
            if (!ratings
                    .isEmpty()) {//this should never happen on cross lab otherwise oh snap
                long contestPhaseId = ratings.get(0).getContestPhaseId();
                ContestPhaseWrapper contestPhase = StaticContestContext.getContestClient()
                        .getContestPhase(contestPhaseId);
                contestPhaseTitleAux = StaticContestContext.getContestClient()
                        .getContestPhaseName(contestPhase);
            }
        }

        return contestPhaseTitleAux.replace("selection", "Evaluation");
    }

    public boolean isReviewComplete() {
        if (!this.ratings.isEmpty()) {
            boolean result = true;
            for (ProposalRatingWrapper r : ratings) {
                if (!r.isRatingComplete()) {
                    result = false;
                }
            }
            return result;
        } else {
            return false;
        }
    }
}

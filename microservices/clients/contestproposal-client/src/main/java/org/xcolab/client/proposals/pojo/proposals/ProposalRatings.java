package org.xcolab.client.proposals.pojo.proposals;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRating;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class ProposalRatings {

    private final List<ProposalRating> ratings;
    private String comment;
    private Boolean shouldAdvance;
    private ContestPhase contestPhase;
    private String contestPhaseTitle;

    public ProposalRatings(List<ProposalRating> ratings, Long roundFactor) {
        List<ProposalRating> wrapped = new ArrayList<>();
        for (ProposalRating r : ratings) {
            wrapped.add(new ProposalRating(r, roundFactor));
        }

        //sort the list
        wrapped.sort(Comparator.comparing(ProposalRating::getRatingTypeId));

        this.ratings = wrapped;
    }

    public List<ProposalRating> getRatings() {
        return ratings;
    }

    public String getComment() {
        if (comment != null) {
            return comment;
        } else {
            for (ProposalRating r : ratings) {
                if (r.getCommentEnabled()) {
                    return r.getComment();
                }
            }
            return "";
        }
    }

    public Boolean getShouldAdvance() {
        if (shouldAdvance != null) {
            return shouldAdvance;
        } else {
            for (ProposalRating r : ratings) {
                if (r.getCommentEnabled()) {
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

    public void setContestPhase(ContestPhase contestPhase) {
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
                ContestPhase contestPhase = ContestClientUtil.getContestPhase(contestPhaseId);
                contestPhaseTitleAux = ContestClientUtil.getContestPhaseName(contestPhase);
            }
        }

        return contestPhaseTitleAux.replace("selection", "Evaluation");
    }

    public boolean isReviewComplete() {
        if (!this.ratings.isEmpty()) {
            boolean result = true;
            for (ProposalRating r : ratings) {
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

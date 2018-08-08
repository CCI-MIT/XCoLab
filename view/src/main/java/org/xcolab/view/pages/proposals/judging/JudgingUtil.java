package org.xcolab.view.pages.proposals.judging;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRating;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.view.pages.proposals.requests.RatingBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JudgingUtil {

    public static void saveRatings(List<ProposalRating> existingRatings, RatingBean ratingBean,
            long proposalId, long contestPhaseId, long currentUserId, boolean isPublicRating) {
        //initialize a map of existing ratings
        Map<Long, ProposalRating> typeToRatingMap = new HashMap<>();
        for (ProposalRating r : existingRatings) {
            typeToRatingMap.put(r.getRatingTypeId(), r);
        }

        Map<Long, String> ratingsFromForm = ratingBean.getRatingValues();
        //now update the values and save or create a new rating of not existent yet
        if (ratingsFromForm != null) {
            boolean commentAndAdvanceAdded = false;
            for (Map.Entry<Long, String> entry : ratingsFromForm.entrySet()) {
                ProposalRating existingRating = typeToRatingMap.get(entry.getKey());
                long newRatingValueId = Long.parseLong(entry.getValue());

                final Boolean shouldAdvance = ratingBean.getShouldAdvanceProposal();
                if (existingRating != null) {
                    //rating exists. update and save
                    existingRating.setRatingValueId(newRatingValueId);
                    //convention: save comment and shouldAdvance in first type
                    if (!commentAndAdvanceAdded) {
                        existingRating.setComment(HtmlUtil.cleanAll(ratingBean.getComment()));
                        existingRating.setCommentEnabled(true);
                        existingRating.setOtherDataString(shouldAdvance != null
                                ? shouldAdvance.toString() : "");
                        commentAndAdvanceAdded = true;
                    } else {
                        existingRating.setComment(null);
                        existingRating.setOtherDataString("");
                        existingRating.setCommentEnabled(false);
                    }
                    ProposalJudgeRatingClientUtil.updateProposalRating(existingRating);
                } else {
                    String comment = null;
                    String shouldAdvanceString = "";
                    if (!commentAndAdvanceAdded) {
                        comment = ratingBean.getComment();
                        shouldAdvanceString = shouldAdvance != null ? shouldAdvance.toString() : "";
                        commentAndAdvanceAdded = true;
                    }
                    //create a new rating
                    ProposalRating proposalRating = new ProposalRating();
                    proposalRating.setProposalId(proposalId);
                    proposalRating.setContestPhaseId(contestPhaseId);
                    proposalRating.setUserId(currentUserId);
                    proposalRating.setRatingValueId(newRatingValueId);
                    proposalRating.setComment(HtmlUtil.cleanAll(comment));
                    proposalRating.setOtherDataString(shouldAdvanceString);
                    if (StringUtils.isNotEmpty(comment)) {
                        proposalRating.setCommentEnabled(true);
                    } else {
                        proposalRating.setCommentEnabled(false);
                    }
                    proposalRating.setOnlyForInternalUsage(isPublicRating);

                    ProposalJudgeRatingClientUtil.createProposalRating(proposalRating);
                }
            }
        }
    }
}

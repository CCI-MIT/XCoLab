package org.xcolab.portlets.proposals.requests;



import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRating;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRatingType;
import org.xcolab.portlets.proposals.wrappers.ProposalRatingTypeWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalRatingWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RatingBean {
    private List<ProposalRatingTypeWrapper> ratingTypes;
    private Map<Long, String> ratingValues;

    private Long contestPhaseId;

    private String comment;

    private Long screeningUserId;

    public RatingBean(Proposal wrapper, List<ProposalRatingType> presetRatingTypes) {
        this.ratingValues = new HashMap<>();
        this.ratingTypes = new ArrayList<>();

        //initialize ratingValues and types
        for (ProposalRatingType type : presetRatingTypes) {
            ratingValues.put(type.getId_(), "");
            ratingTypes.add(new ProposalRatingTypeWrapper(type));
        }

        //get the existing ratings from the wrapper
        for (ProposalRating ratingWrapper : wrapper.getRatings()) {
            ratingValues.put(ratingWrapper.getRatingTypeId(), String.valueOf(ratingWrapper.unwrap().getRatingValueId()));
        }
        comment = wrapper.getRatingComment();
    }

    public RatingBean() {
    }

    public Long getContestPhaseId() {
        return contestPhaseId;
    }

    public void setContestPhaseId(Long contestPhaseId) {
        this.contestPhaseId = contestPhaseId;
    }

    public String getComment() {
       return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Map<Long, String> getRatingValues() {
        return ratingValues;
    }

    public void setRatingValues(Map<Long, String> ratingValues) {
        this.ratingValues = ratingValues;
    }

    public List<ProposalRatingTypeWrapper> getRatingTypes() {
        return ratingTypes;
    }

    public void setScreeningUserId (Long screeningUserId){this.screeningUserId=screeningUserId;}

    public Long getScreeningUserId(){return screeningUserId;}
}

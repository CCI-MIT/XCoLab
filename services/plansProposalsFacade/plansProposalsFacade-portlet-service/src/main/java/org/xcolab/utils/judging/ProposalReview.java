package org.xcolab.utils.judging;

import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalRatingType;
import com.liferay.portal.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by kmang on 25/05/14.
 */
public class ProposalReview {
    private Proposal proposal;
    ContestPhase contestPhase;
    private String proposalUrl;



    private Map<ProposalRatingType, Double> ratingAverages;

    private Map<User, String> reviews;

    public ProposalReview(Proposal proposal, ContestPhase contestPhase, String proposalUrl) {
        this.proposal = proposal;
        this.contestPhase = contestPhase;
        this.proposalUrl = proposalUrl;
        this.reviews = new HashMap<User, String>();
        this.ratingAverages = new HashMap<ProposalRatingType, Double>();
    }

    public void addReview(User judge, String comment) {
        this.reviews.put(judge, comment);
    }
    public String getReview(User user) {
        return reviews.get(user);
    }

    public void addRatingAverage(ProposalRatingType ratingType, double average) {
        this.ratingAverages.put(ratingType, average);
    }

    public Double getRatingAverage(ProposalRatingType type) {
        return ratingAverages.get(type);
    }

    public Map<ProposalRatingType, Double> getRatingAverages() {
        return ratingAverages;
    }

    public void setRatingAverages(Map<ProposalRatingType, Double> ratingAverages) {
        this.ratingAverages = ratingAverages;
    }

    public Map<User, String> getReviews() {
        return reviews;
    }

    public void setReviews(Map<User, String> reviews) {
        this.reviews = reviews;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    public ContestPhase getContestPhase() {
        return contestPhase;
    }

    public void setContestPhase(ContestPhase contestPhase) {
        this.contestPhase = contestPhase;
    }

    public String getProposalUrl() {
        return proposalUrl;
    }

    public void setProposalUrl(String proposalUrl) {
        this.proposalUrl = proposalUrl;
    }
}

package org.xcolab.utils.judging;

import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalRatingType;
import com.liferay.portal.model.User;

import java.util.*;


/**
 * Created by kmang on 25/05/14.
 */
public class ProposalReview {
    private Proposal proposal;
    ContestPhase contestPhase;
    private String proposalUrl;



    private Map<ProposalRatingType, Double> ratingAverages;
    private Map<User, String> reviews;
    private Set<User> reviewers;
    private Map<User, Map<ProposalRatingType, Double> > userRatings;

    public ProposalReview(Proposal proposal, ContestPhase contestPhase, String proposalUrl) {
        this.proposal = proposal;
        this.contestPhase = contestPhase;
        this.proposalUrl = proposalUrl;
        this.reviews = new HashMap<User, String>();
        this.reviewers = new HashSet<User>();
        this.userRatings = new HashMap<>();
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

    public Double getRatingAverage() {

        double avg = 0F;
        double sum = 0;
        int count = 0;

        for(ProposalRatingType key : ratingAverages.keySet()){
            sum += ratingAverages.get(key);
            count++;
        }

        if(count > 0) {
            avg = sum / count;
        }

        return avg;
    }

    public void addUserRating(User user, final ProposalRatingType ratingType, final double rating) {
        if(this.userRatings.get(user) == null){
            this.userRatings.put(user,new HashMap(){{put(ratingType, rating);}});
        }
        else {
            this.userRatings.get(user).put(ratingType, rating);
        }
    }

    public Map<ProposalRatingType, Double> getUserRatings(User user) {
        return this.userRatings.get(user);
    }

    public Double getUserRating(User user, ProposalRatingType ratingType) {
        if(this.userRatings.get(user) != null) {
            return this.userRatings.get(user).get(ratingType);
        }
        else {
            return null;
        }
    }

    public Double getUserRatingAverage(User user){

        if(userRatings.get(user) != null) {
            double avg = 0F;
            double sum = 0;
            int count = 0;

            //take the average for each user
            for (ProposalRatingType key : userRatings.get(user).keySet()) {
                sum += userRatings.get(user).get(key);
                count++;
            }
            if (count > 0) {
                avg = sum / count;
            }

            return avg;
        }
        else {
            return null;
        }
    }


    public Map<User, String> getReviews() {
        return reviews;
    }

    public void setReviewers(Set<User> reviewers){
        this.reviewers = reviewers;
    }

    public Set<User> getReviewers(){
        return reviewers;
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

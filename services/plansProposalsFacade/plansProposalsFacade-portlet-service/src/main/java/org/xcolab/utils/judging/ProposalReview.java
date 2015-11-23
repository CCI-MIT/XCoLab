package org.xcolab.utils.judging;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.model.ProposalRatingType;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


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
    private final Map<User, Map<ProposalRatingType, Double> > userRatings;

    public ProposalReview(Proposal proposal, ContestPhase contestPhase, String proposalUrl) {
        this.proposal = proposal;
        this.contestPhase = contestPhase;
        this.proposalUrl = proposalUrl;
        this.reviews = new HashMap<>();
        this.reviewers = new HashSet<>();
        this.userRatings = new HashMap<>();
        this.ratingAverages = new HashMap<>();
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
        double sum = 0;
        int count =  ratingAverages.keySet().size();

        for(Map.Entry<ProposalRatingType, Double> entry : ratingAverages.entrySet()){
            sum += entry.getValue();
        }

        double avg = 0F;
        if(count > 0) {
            avg = sum / count;
        }

        return avg;
    }

    public void addUserRating(User user, final ProposalRatingType ratingType, final double rating) {
        Map<ProposalRatingType, Double> ratings;
        if(this.userRatings.get(user) == null){
            ratings = new HashMap<>();
            this.userRatings.put(user, ratings);
        } else {
            ratings = this.userRatings.get(user);
        }
        ratings.put(ratingType, rating);
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
            double sum = 0;
            int count =  userRatings.get(user).keySet().size();

            //take the average for each user
            for (Map.Entry<ProposalRatingType, Double> entry : userRatings.get(user).entrySet()) {
                sum += entry.getValue();
            }
            double avg = 0F;
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

    public String getProposalTeamAuthor(){
        try {
            String authorName = getTeamOrNull();
            if (StringUtils.isBlank(authorName)) {
                authorName = UserLocalServiceUtil.getUser(proposal.getAuthorId()).getScreenName();
            }
            return authorName;
        } catch(SystemException | PortalException ignored){ }
        return "";

    }

    private String getTeamOrNull(){
        try {
            for (ProposalAttribute attr: ProposalLocalServiceUtil.getAttributes(proposal.getProposalId(), proposal.getCurrentVersion())) {
                if (attr.getName().equals(ProposalAttributeKeys.TEAM) && attr.getAdditionalId() == 0) {
                    return attr.getStringValue();
                }
            }
        } catch(SystemException | PortalException ignored){ }

        return null;
    }

}

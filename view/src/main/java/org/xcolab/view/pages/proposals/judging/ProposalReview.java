package org.xcolab.view.pages.proposals.judging;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.contest.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.contest.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalAttribute;
import org.xcolab.client.contest.pojo.IProposalRatingType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ProposalReview {
    private ProposalWrapper proposal;
    private ContestPhaseWrapper contestPhase;
    private String proposalUrl;

    private Map<IProposalRatingType, Double> ratingAverages;
    private Map<Member, String> reviews;
    private final Map<Member, Boolean> shouldAdvanceDecisions;
    private Set<Member> reviewers;
    private final Map<Member, Map<IProposalRatingType, Double> > userRatings;

    public ProposalReview(ProposalWrapper proposal, ContestPhaseWrapper contestPhase, String proposalUrl) {
        this.proposal = proposal;
        this.contestPhase = contestPhase;
        this.proposalUrl = proposalUrl;
        this.reviews = new HashMap<>();
        this.shouldAdvanceDecisions = new HashMap<>();
        this.reviewers = new HashSet<>();
        this.userRatings = new HashMap<>();
        this.ratingAverages = new HashMap<>();
    }

    public void addReview(Member judge, String comment) {
        this.reviews.put(judge, comment);
    }

    public String getReview(Member user) {
        return reviews.get(user);
    }

    public void addShouldAdvanceDecision(Member judge, Boolean decision) {
        shouldAdvanceDecisions.put(judge, decision);
    }

    public Boolean getShouldAdvanceDecision(Member judge) {
        return shouldAdvanceDecisions.get(judge);
    }

    public void addRatingAverage(IProposalRatingType ratingType, double average) {
        this.ratingAverages.put(ratingType, average);
    }

    public Double getRatingAverage(IProposalRatingType type) {
        return ratingAverages.get(type);
    }

    public Map<IProposalRatingType, Double> getRatingAverages() {
        return ratingAverages;
    }

    public void setRatingAverages(Map<IProposalRatingType, Double> ratingAverages) {
        this.ratingAverages = ratingAverages;
    }

    public Double getRatingAverage() {
        double sum = 0;
        int count =  ratingAverages.keySet().size();

        for(Map.Entry<IProposalRatingType, Double> entry : ratingAverages.entrySet()){
            sum += entry.getValue();
        }

        double avg = 0F;
        if(count > 0) {
            avg = sum / count;
        }

        return avg;
    }

    public void addUserRating(Member user, final IProposalRatingType ratingType, final double rating) {
        Map<IProposalRatingType, Double> ratings;
        if (this.userRatings.get(user) == null) {
            ratings = new HashMap<>();
            this.userRatings.put(user, ratings);
        } else {
            ratings = this.userRatings.get(user);
        }
        ratings.put(ratingType, rating);
    }

    public Map<IProposalRatingType, Double> getUserRatings(Member user) {
        return this.userRatings.get(user);
    }

    public Double getUserRating(Member user, IProposalRatingType ratingType) {
        if(this.userRatings.get(user) != null) {
            return this.userRatings.get(user).get(ratingType);
        }
        else {
            return null;
        }
    }

    public Double getUserRatingAverage(Member user){

        if(userRatings.get(user) != null) {
            double sum = 0;
            int count =  userRatings.get(user).keySet().size();

            //take the average for each user
            for (Map.Entry<IProposalRatingType, Double> entry : userRatings.get(user).entrySet()) {
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


    public Map<Member, String> getReviews() {
        return reviews;
    }

    public void setReviewers(Set<Member> reviewers){
        this.reviewers = reviewers;
    }

    public Set<Member> getReviewers(){
        return reviewers;
    }

    public void setReviews(Map<Member, String> reviews) {
        this.reviews = reviews;
    }

    public ProposalWrapper getProposal() {
        return proposal;
    }

    public void setProposal(ProposalWrapper proposal) {
        this.proposal = proposal;
    }

    public ContestPhaseWrapper getContestPhase() {
        return contestPhase;
    }

    public void setContestPhase(ContestPhaseWrapper contestPhase) {
        this.contestPhase = contestPhase;
    }

    public String getProposalUrl() {
        return proposalUrl;
    }

    public void setProposalUrl(String proposalUrl) {
        this.proposalUrl = proposalUrl;
    }

    public String getProposalTeamAuthor() {
        String authorName = getTeamOrNull();
        if (StringUtils.isBlank(authorName)) {
            try {
                final Member member = MembersClient.getMember(proposal.getAuthorUserId());
                authorName = member.getFirstName() + " " + member.getLastName();
            } catch (MemberNotFoundException e) {
                authorName = "";
            }
        }
        return authorName;
    }

    private String getTeamOrNull(){
            for (ProposalAttribute attr: ProposalAttributeClientUtil
                    .getAllProposalAttributes(proposal.getId(), proposal.getCurrentVersion())) {
                if (attr.getName().equals(ProposalAttributeKeys.TEAM) && attr.getAdditionalId() == 0) {
                    return attr.getStringValue();
                }
            }

        return null;
    }

}

package org.xcolab.view.pages.proposals.judging;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.user.MembersClient;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.Member;
import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttribute;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRatingType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ProposalReview {
    private Proposal proposal;
    private ContestPhase contestPhase;
    private String proposalUrl;

    private Map<ProposalRatingType, Double> ratingAverages;
    private Map<Member, String> reviews;
    private final Map<Member, Boolean> shouldAdvanceDecisions;
    private Set<Member> reviewers;
    private final Map<Member, Map<ProposalRatingType, Double> > userRatings;

    public ProposalReview(Proposal proposal, ContestPhase contestPhase, String proposalUrl) {
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

    public void addUserRating(Member user, final ProposalRatingType ratingType, final double rating) {
        Map<ProposalRatingType, Double> ratings;
        if (this.userRatings.get(user) == null) {
            ratings = new HashMap<>();
            this.userRatings.put(user, ratings);
        } else {
            ratings = this.userRatings.get(user);
        }
        ratings.put(ratingType, rating);
    }

    public Map<ProposalRatingType, Double> getUserRatings(Member user) {
        return this.userRatings.get(user);
    }

    public Double getUserRating(Member user, ProposalRatingType ratingType) {
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

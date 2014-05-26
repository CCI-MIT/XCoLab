package org.xcolab.utils.csvExport;

import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kmang on 25/05/14.
 */
public class ProposalReview {
    private Proposal proposal;
    private String proposalUrl;
    private List<IndividualReview> reviews;

    public ProposalReview(Proposal proposal, String proposalUrl) {
        this.proposal = proposal;
        this.proposalUrl = proposalUrl;
        this.reviews = new ArrayList<>();
    }

    public void addIndividualReview(User judge, int rating, String comment) {
        IndividualReview review = new IndividualReview(judge, rating, comment);
        this.reviews.add(review);
    }

    public double getAverageRating() {
        int sum = 0;
        for (IndividualReview individualReview : reviews) {
            sum += individualReview.getRating();
        }

        return (double)sum / ((double)reviews.size());
    }

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    public String getProposalUrl() {
        return proposalUrl;
    }

    public void setProposalUrl(String proposalUrl) {
        this.proposalUrl = proposalUrl;
    }

    public List<IndividualReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<IndividualReview> reviews) {
        this.reviews = reviews;
    }

    public class IndividualReview {
       private int rating;
        private User reviewer;
        private String comment;

        public IndividualReview(User reviewer, int rating, String comment) {
            this.rating = rating;
            this.reviewer = reviewer;
            this.comment = comment;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public User getReviewer() {
            return reviewer;
        }

        public void setReviewer(User reviewer) {
            this.reviewer = reviewer;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }
}

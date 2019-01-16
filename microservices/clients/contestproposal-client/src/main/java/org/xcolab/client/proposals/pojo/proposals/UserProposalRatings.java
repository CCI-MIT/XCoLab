package org.xcolab.client.proposals.pojo.proposals;

import org.xcolab.client.user.MembersClient;
import org.xcolab.client.user.pojo.Member;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRating;

import java.util.List;

public class UserProposalRatings extends ProposalRatings {

    private final Member author;

    public UserProposalRatings(long authorUserId, List<ProposalRating> ratings) {
        this(MembersClient.getMemberUnchecked(authorUserId), ratings);
    }

    public UserProposalRatings(Member author, List<ProposalRating> ratings) {
        this(author, ratings, 1L);
    }

    public UserProposalRatings(Member author, List<ProposalRating> ratings, Long roundFactor) {
        super(ratings, roundFactor);
        this.author = author;
    }

    public Member getAuthor() {
        return author;
    }
}

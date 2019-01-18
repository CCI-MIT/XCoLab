package org.xcolab.client.contest.pojo.wrapper;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;

import java.util.List;

public class UserProposalRatings extends ProposalRatings {

    private final Member author;

    public UserProposalRatings(long authorUserId, List<ProposalRatingWrapper> ratings) {
        this(MembersClient.getMemberUnchecked(authorUserId), ratings);
    }

    public UserProposalRatings(Member author, List<ProposalRatingWrapper> ratings) {
        this(author, ratings, 1L);
    }

    public UserProposalRatings(Member author, List<ProposalRatingWrapper> ratings, Long roundFactor) {
        super(ratings, roundFactor);
        this.author = author;
    }

    public Member getAuthor() {
        return author;
    }
}

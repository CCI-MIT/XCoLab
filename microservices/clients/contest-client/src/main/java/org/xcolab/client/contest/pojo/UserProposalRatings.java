package org.xcolab.client.contest.pojo;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;

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

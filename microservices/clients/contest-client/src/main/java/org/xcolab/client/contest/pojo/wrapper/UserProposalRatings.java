package org.xcolab.client.contest.pojo.wrapper;

import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;

import java.util.List;

public class UserProposalRatings extends ProposalRatings {

    private final UserWrapper author;

    public UserProposalRatings(long authorUserId, List<ProposalRatingWrapper> ratings) {
        this(StaticUserContext.getUserClient().getMemberUnchecked(authorUserId), ratings);
    }

    public UserProposalRatings(UserWrapper author, List<ProposalRatingWrapper> ratings) {
        this(author, ratings, 1L);
    }

    public UserProposalRatings(UserWrapper author, List<ProposalRatingWrapper> ratings, Long roundFactor) {
        super(ratings, roundFactor);
        this.author = author;
    }

    public UserWrapper getAuthor() {
        return author;
    }
}

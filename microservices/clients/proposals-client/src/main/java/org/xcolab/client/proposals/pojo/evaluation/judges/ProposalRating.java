package org.xcolab.client.proposals.pojo.evaluation.judges;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.util.http.client.RestService;

public class ProposalRating extends AbstractProposalRating {

    public ProposalRating() {}

    public ProposalRating(ProposalRating value) {
        super(value);
    }

    public ProposalRating(
            Long id_,
            Long proposalid,
            Long contestphaseid,
            Long userid,
            Long ratingvalueid,
            String comment_,
            Boolean commentenabled,
            String otherdatastring,
            Boolean onlyforinternalusage
    ) {
        super(id_, proposalid, contestphaseid, userid, ratingvalueid, comment_, commentenabled,
                otherdatastring, onlyforinternalusage);
    }

    public ProposalRating(AbstractProposalRating abstractProposalRating, RestService restService) {
        super(abstractProposalRating);
    }

    public boolean isRatingComplete() {
        final boolean commentComplete = !getCommentEnabled()
                || !StringUtils.isEmpty(this.getComment_());
        return getRatingValueId() > 0 && commentComplete;
    }
}

package org.xcolab.service.activities.activityEntry.discussion;

public class DiscussionAddProposalCommentActivityEntry extends DiscussionBaseActivityEntry {



    @Override
    public Long getSecondaryType() {
        return DiscussionActivitySubType.DISCUSSION_PROPOSAL_COMMENT.getSecondaryTypeId();
    }

    @Override
    public String getBody() {
        String template = "%s added a comment to Proposal %s";

        return String.format(template, getUserLink(),
                getProposalLink());

    }

    @Override
    public String getTitle() {
        return getUserLink() + " added a comment to proposal";
    }

    @Override
    public String getName() {
        return "Comment to proposal";
    }
}

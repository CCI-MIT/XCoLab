package org.xcolab.activityEntry.discussion;

public class DiscussionAddProposalCommentActivityEntry extends DiscussionBaseActivityEntry {



    @Override
    public Long getSecondaryType() {
        return DiscussionActivitySubType.DISCUSSION_PROPOSAL_COMMENT.getSecondaryTypeId();
    }

    @Override
    public String getBody() {
        String template = "%s added a comment to %s";

        return String.format(template, getUserLink(),
                getCategoryGroupLink());

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

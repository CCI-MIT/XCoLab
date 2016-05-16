package org.xcolab.activityEntry.discussion;

public class DiscussionAddProposalCommentActivityEntry extends DiscussionBaseActivityEntry {



    @Override
    public Integer getSecondaryType() {
        return 1;
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

package org.xcolab.view.activityentry.discussion;

import org.springframework.stereotype.Component;

@Component
public class DiscussionAddContestCommentActivityEntry  extends DiscussionBaseActivityEntry {



    @Override
    public Long getSecondaryType() {
        return DiscussionActivitySubType.DISCUSSION_CONTEST_COMMENT.getSecondaryTypeId();
    }

    @Override
    public String getBody() {


        String[] params = { getUserLink(),"Contest",getContestLink()};
        return resourceMessageResolver.getLocalizedMessage("activities.discussion.discussionaddcontest.message",params);
    }

    @Override
    public String getTitle() {
        return getUserLink() + " added a comment to contest";
    }

    @Override
    public String getName() {
        return "Comment to contest";
    }
}


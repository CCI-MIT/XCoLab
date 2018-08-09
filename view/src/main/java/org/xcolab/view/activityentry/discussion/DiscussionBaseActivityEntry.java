package org.xcolab.view.activityentry.discussion;

import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.view.activityentry.ActivityInitializationException;
import org.xcolab.view.activityentry.provider.AbstractActivityEntryContentProvider;
import org.xcolab.view.i18n.ResourceMessageResolver;

public abstract class DiscussionBaseActivityEntry extends AbstractActivityEntryContentProvider {

    protected ActivityEntry activityEntry;

    private CommentThread thread;

    protected final ResourceMessageResolver resourceMessageResolver;

    public DiscussionBaseActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        this.resourceMessageResolver = resourceMessageResolver;
    }

    @Override
    public void initializeInternal() throws ActivityInitializationException {

        try {
            thread = ThreadClientUtil.getThread(getActivityEntry().getCategoryId());
        } catch (ThreadNotFoundException e) {
            throw new ActivityInitializationException(activityEntry.getId(), e);
        }
    }

    protected CommentThread getThread() {
        return thread;
    }

    protected String getThreadLink() {
        return String.format(HYPERLINK_FORMAT, thread.getLinkUrl(), thread.getTitle());
    }
}

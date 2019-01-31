package org.xcolab.view.activityentry.discussion;

import org.xcolab.client.comment.StaticCommentContext;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.IThread;
import org.xcolab.view.activityentry.ActivityInitializationException;
import org.xcolab.view.activityentry.provider.AbstractActivityEntryContentProvider;
import org.xcolab.view.i18n.ResourceMessageResolver;

public abstract class DiscussionBaseActivityEntry extends AbstractActivityEntryContentProvider {

    protected final ResourceMessageResolver resourceMessageResolver;

    private IThread thread;

    public DiscussionBaseActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        this.resourceMessageResolver = resourceMessageResolver;
    }

    @Override
    public void initializeInternal() throws ActivityInitializationException {
        try {
            thread = StaticCommentContext.getThreadClient().getThread(getActivityEntry()
                    .getCategoryId());
        } catch (ThreadNotFoundException e) {
            throw new ActivityInitializationException(getActivityEntry().getId(), e);
        }
    }

    protected IThread getThread() {
        return thread;
    }

    protected String getThreadLink() {
        return String.format(HYPERLINK_FORMAT, thread.getLinkUrl(), thread.getTitle());
    }
}

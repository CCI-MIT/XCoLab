package org.xcolab.view.activityentry.discussion;

import org.xcolab.client.comment.IThreadClient;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.IThread;
import org.xcolab.view.activityentry.ActivityInitializationException;
import org.xcolab.view.activityentry.provider.AbstractActivityEntryContentProvider;
import org.xcolab.view.i18n.ResourceMessageResolver;

public abstract class DiscussionBaseActivityEntry extends AbstractActivityEntryContentProvider {

    private static IThreadClient threadClient;

    public static void setThreadClient(IThreadClient threadClient) {
        DiscussionBaseActivityEntry.threadClient = threadClient;
    }

    private IThread thread;

    protected final ResourceMessageResolver resourceMessageResolver;

    public DiscussionBaseActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        this.resourceMessageResolver = resourceMessageResolver;
    }

    @Override
    public void initializeInternal() throws ActivityInitializationException {

        try {
            thread = threadClient.getThread(getActivityEntry().getCategoryId());
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

package org.xcolab.jspTags.discussion.wrappers;

import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.xcolab.jspTags.discussion.ThreadSortColumn;
import org.xcolab.util.HumanTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by johannes on 12/2/15.
 */
public class ThreadWrapper {

    private final DiscussionMessageWrapper threadMessageWrapped;
    private final List<DiscussionMessageWrapper> comments = new ArrayList<>();
    private DiscussionCategoryGroup dcg;
    private User author;

    public ThreadWrapper(long threadId) throws SystemException, PortalException {
        this(DiscussionMessageLocalServiceUtil.getMessageByMessageId(threadId));
    }

    public ThreadWrapper(DiscussionMessage threadMessage) throws SystemException, PortalException {
        threadMessageWrapped = new DiscussionMessageWrapper(threadMessage);
        if (threadMessage.getThreadId() != 0) {
            throw new PortalException("This message is not a thread: "+ threadMessage.getMessageId());
        }
        if (threadMessage.getDeleted() == null) {
            comments.add(threadMessageWrapped);
        }
        final List<DiscussionMessage> messages = DiscussionMessageLocalServiceUtil.getThreadMessages(threadMessageWrapped.getMessageId());
        for (DiscussionMessage message : messages) {
            final DiscussionMessageWrapper discussionMessageWrapper = new DiscussionMessageWrapper(message);
            this.comments.add(discussionMessageWrapper);
        }
        //TODO: get correct order from db
        Collections.sort(this.comments);
    }

    public List<DiscussionMessageWrapper> getComments() {
        return comments;
    }

    public long getThreadId() {
        return threadMessageWrapped.getMessageId();
    }

    public DiscussionMessageWrapper getThreadMessage() {
        return threadMessageWrapped;
    }

    public String getTitle() {
        return threadMessageWrapped.getSubject();
    }

    public int getCommentsCount() {
        return comments.size();
    }

    public DiscussionCategoryGroup getCategoryGroup() throws SystemException {
        if (dcg == null) {
            dcg = DiscussionCategoryGroupLocalServiceUtil.fetchDiscussionCategoryGroup(threadMessageWrapped.getCategoryGroupId());
        }
        return dcg;
    }

    public CategoryWrapper getCategory() throws PortalException, SystemException {
        return threadMessageWrapped.getCategory();
    }

    public long getAuthorId() {
        return threadMessageWrapped.getAuthorId();
    }

    public User getAuthor() throws SystemException {
        if (author == null) {
            author = UserLocalServiceUtil.fetchUser(threadMessageWrapped.getAuthorId());
        }
        return author;
    }

    public String getModifiedDate() {
        return HumanTime.exactly(new Date().getTime() - getLastActivityDate().getTime());
    }

    public String getLinkUrl() {
        return "/web/guest/discussion/-/discussion/thread/"+getThreadId();
    }

    public long getLastActivityAuthorId() {
        long lastActivityAuthorId = threadMessageWrapped.getLastActivityAuthorId();
        if (lastActivityAuthorId == 0L) {
            lastActivityAuthorId = getAuthorId();
        }
        return lastActivityAuthorId;
    }

    public User getLastActivityAuthor() throws SystemException {
        return UserLocalServiceUtil.fetchUser(getLastActivityAuthorId());
    }

    public Date getLastActivityDate() {
        return threadMessageWrapped.getLastActivityDate();
    }

    public static void sort(List<ThreadWrapper> threads, final ThreadSortColumn sortColumn, final boolean sortAscending) {
        Collections.sort(threads, getComparator(sortColumn, sortAscending));
    }

    public static Comparator<ThreadWrapper> getComparator(final ThreadSortColumn sortColumn, final boolean sortAscending) {
        return new Comparator<ThreadWrapper>() {

            @Override
            public int compare(ThreadWrapper o1, ThreadWrapper o2) {
                int ret;

                switch (sortColumn) {
                    case TITLE:
                        ret = o1.getTitle().compareToIgnoreCase(o2.getTitle());
                        break;
                    case REPLIES:
                        ret = o1.getCommentsCount() - o2.getCommentsCount();
                        break;
                    case LAST_COMMENTER:
                        try {
                            final User lastActivityAuthor1 = o1.getLastActivityAuthor();
                            final User lastActivityAuthor2 = o2.getLastActivityAuthor();
                            if (lastActivityAuthor1 != null && lastActivityAuthor2 != null) {
                                ret = lastActivityAuthor1.getScreenName().compareToIgnoreCase(lastActivityAuthor2.getScreenName());
                            } else {
                                ret = (int) (o1.getLastActivityAuthorId() - o2.getLastActivityAuthorId());
                            }
                        } catch (SystemException e) {
                            ret = (int) (o1.getLastActivityAuthorId() - o2.getLastActivityAuthorId());
                        }
                        break;
                    case DATE:
                    default:
                        ret = o1.getLastActivityDate().compareTo(o2.getLastActivityDate());
                        break;
                }

                return sortAscending ? -ret : ret;
            }
        };
    }

    public boolean isSubscribed(long userId) throws SystemException, PortalException {
        return DiscussionCategoryLocalServiceUtil.isSubscribed(userId, dcg.getId(), getThreadId());
    }
}

package org.xcolab.view.taglibs.xcolab.jspTags.discussion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.comment.CategoryClient;
import org.xcolab.client.comment.ThreadClient;
import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.Category;
import org.xcolab.client.comment.pojo.CategoryGroup;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.flagging.FlaggingClient;
import org.xcolab.client.flagging.pojo.ReportTarget;
import org.xcolab.commons.exceptions.ReferenceResolutionException;
import org.xcolab.util.enums.flagging.TargetType;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.wrappers.NewMessageWrapper;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class LoadThreadStartTag extends BodyTagSupport {

    private static final Logger _log = LoggerFactory.getLogger(LoadThreadStartTag.class);

    private long threadId;
    private long categoryId;
    private long categoryGroupId;

    @Override
    public int doStartTag() {
        try {

            HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

            String shareTitle = null;

            if (categoryId > 0) {
                try {
                    Category category = CategoryClient.instance().getCategory(categoryId);
                    shareTitle = category.getName();
                } catch (CategoryNotFoundException e) {
                    throw ReferenceResolutionException.toObject(Category.class, categoryId)
                            .fromObject(LoadThreadStartTag.class, "for thread " + threadId);
                }
            }

            if (categoryGroupId > 0) {
                try {
                    CategoryGroup categoryGroup = CategoryClient.instance().getCategoryGroup(categoryGroupId);
                    if (shareTitle == null) {
                        shareTitle = categoryGroup.getDescription();
                    }
                } catch (CategoryGroupNotFoundException e) {
                    throw ReferenceResolutionException
                            .toObject(CategoryGroup.class, categoryGroupId)
                            .fromObject(LoadThreadStartTag.class, "for thread " + threadId);
                }
            }

            CommentThread thread = ThreadClient.instance().getThread(threadId);

            DiscussionPermissions discussionPermissions = (DiscussionPermissions) request
                    .getAttribute(DiscussionPermissions.REQUEST_ATTRIBUTE_NAME);
            if (discussionPermissions == null) {
                discussionPermissions = new DiscussionPermissions();
            } else {
                _log.debug("Using custom DiscussionPermissions of type {}",
                        discussionPermissions.getClass().getName());
            }

            pageContext.setAttribute("thread", thread);
            pageContext.setAttribute("shareTitle", shareTitle);
            pageContext.setAttribute("newMessage", new NewMessageWrapper());
            pageContext.setAttribute("discussionPermissions", discussionPermissions);
            final List<ReportTarget> reportTargets =
                    FlaggingClient.listReportTargets(TargetType.COMMENT);
            pageContext.setAttribute("reportTargets", reportTargets);

        } catch (ThreadNotFoundException e) {
            throw ReferenceResolutionException.toObject(Thread.class, threadId)
                    .fromObject(LoadThreadStartTag.class, "");
        }
        return EVAL_BODY_INCLUDE;
    }


    public long getCategoryGroupId() {
        return categoryGroupId;
    }

    public void setCategoryGroupId(long categoryGroupId) {
        this.categoryGroupId = categoryGroupId;
    }

    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}

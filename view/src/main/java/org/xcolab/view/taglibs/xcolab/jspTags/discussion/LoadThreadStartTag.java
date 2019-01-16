package org.xcolab.view.taglibs.xcolab.jspTags.discussion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.comment.ICategoryClient;
import org.xcolab.client.comment.IThreadClient;
import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.ICategory;
import org.xcolab.client.comment.pojo.ICategoryGroup;
import org.xcolab.client.comment.pojo.IThread;
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

    private static IThreadClient threadClient;

    public static void setThreadClient(IThreadClient threadClient) {
        LoadThreadStartTag.threadClient = threadClient;
    }

    private static ICategoryClient categoryClient;

    public static void setCategoryClient(ICategoryClient categoryClient) {
        LoadThreadStartTag.categoryClient = categoryClient;
    }

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
                    ICategory category = categoryClient.getCategory(categoryId);
                    shareTitle = category.getName();
                } catch (CategoryNotFoundException e) {
                    throw ReferenceResolutionException.toObject(ICategory.class, categoryId)
                            .fromObject(LoadThreadStartTag.class, "for thread " + threadId);
                }
            }

            if (categoryGroupId > 0) {
                try {
                    ICategoryGroup categoryGroup = categoryClient.getCategoryGroup(categoryGroupId);
                    if (shareTitle == null) {
                        shareTitle = categoryGroup.getDescription();
                    }
                } catch (CategoryGroupNotFoundException e) {
                    throw ReferenceResolutionException
                            .toObject(org.xcolab.client.comment.pojo.tables.pojos.CategoryGroup.class, categoryGroupId)
                            .fromObject(LoadThreadStartTag.class, "for thread " + threadId);
                }
            }

            IThread thread = threadClient.getThread(threadId);

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

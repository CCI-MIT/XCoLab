package org.xcolab.view.taglibs.xcolab.jspTags.discussion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.comment.CategoryClient;
import org.xcolab.client.comment.ThreadClient;
import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.Category;
import org.xcolab.client.comment.pojo.CategoryGroup;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.CategoryClientUtil;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.client.flagging.FlaggingClient;
import org.xcolab.client.flagging.pojo.ReportTarget;
import org.xcolab.util.http.client.CoLabService;
import org.xcolab.util.enums.flagging.TargetType;
import org.xcolab.util.exceptions.ReferenceResolutionException;
import org.xcolab.util.http.client.RefreshingRestService;
import org.xcolab.util.http.client.RestService;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.wrappers.NewMessageWrapper;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class LoadThreadStartTag extends BodyTagSupport {

    private static final Logger _log = LoggerFactory.getLogger(LoadThreadStartTag.class);

    private long threadId;
    private long categoryId;
    private long categoryGroupId;
    private Long sharedColabId;

    @Override
    public int doStartTag() throws JspException {
        try {

            HttpServletRequest portletRequest = (HttpServletRequest) pageContext.getRequest();

            ThreadClient threadClient;
            CategoryClient categoryClient;
            if (sharedColabId != null && sharedColabId > 0) {
                RestService contestService = new RefreshingRestService(CoLabService.COMMENT,
                        ConfigurationAttributeKey.PARTNER_COLAB_NAMESPACE);

                threadClient = ThreadClient.fromService(contestService);
                categoryClient = CategoryClient.fromService(contestService);
            } else {
                threadClient = ThreadClientUtil.getClient();
                categoryClient = CategoryClientUtil.getClient();
            }

            String shareTitle = null;
            String shareUrl = null;

            if (categoryId > 0) {
                try {
                    Category category = categoryClient.getCategory(categoryId);
                    shareTitle = category.getName();
                    //TODO: url
                    shareUrl = "";
                } catch (CategoryNotFoundException e) {
                    throw ReferenceResolutionException.toObject(Category.class, categoryId)
                            .fromObject(LoadThreadStartTag.class, "for thread " + threadId);
                }
            }

            if (categoryGroupId > 0) {
                try {
                    CategoryGroup categoryGroup = categoryClient.getCategoryGroup(categoryGroupId);
                    if (shareTitle == null) {
                        shareTitle = categoryGroup.getDescription();
                    }
                    if (shareUrl == null) {
                        //TODO: url
                        shareUrl = "";
                    }
                } catch (CategoryGroupNotFoundException e) {
                    throw ReferenceResolutionException
                            .toObject(CategoryGroup.class, categoryGroupId)
                            .fromObject(LoadThreadStartTag.class, "for thread " + threadId);
                }
            }

            CommentThread thread = threadClient.getThread(threadId);

            DiscussionPermissions discussionPermissions = (DiscussionPermissions) portletRequest
                    .getAttribute(DiscussionPermissions.REQUEST_ATTRIBUTE_NAME);
            if (discussionPermissions == null) {
                discussionPermissions = new DiscussionPermissions(portletRequest);
            } else {
                _log.info("Found custom DiscussionPermissions of type {}",
                        discussionPermissions.getClass().getName());
            }

            pageContext.setAttribute("thread", thread);
            pageContext.setAttribute("shareTitle", shareTitle);
            pageContext.setAttribute("shareUrl", shareUrl);
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

    public Long getSharedColabId() {
        return sharedColabId;
    }

    public void setSharedColabId(Long sharedColabId) {
        this.sharedColabId = sharedColabId;
    }
}

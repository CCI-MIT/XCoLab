package org.xcolab.jspTags.discussion;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.exceptions.CategoryNotFoundException;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.Category;
import org.xcolab.client.comment.pojo.CategoryGroup;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.jspTags.discussion.wrappers.NewMessageWrapper;

import javax.portlet.PortletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class LoadThreadStartTag extends BodyTagSupport {

    private static final Log _log = LogFactoryUtil.getLog(LoadThreadStartTag.class);

    private long threadId;
    private long categoryId;
    private long categoryGroupId;

    @Override
    public int doStartTag() throws JspException {
        try {
            PortletRequest portletRequest = getPortletRequest(pageContext);

            String shareTitle = null;
            String shareUrl = null;

            if (categoryId > 0) {
                Category category = CommentClient.getCategory(categoryId);
                shareTitle = category.getName();
                //TODO: url
                shareUrl = "";
            }

            if (categoryGroupId > 0) {
                CategoryGroup categoryGroup = CommentClient.getCategoryGroup(categoryGroupId);
                if (shareTitle == null) {
                    shareTitle = categoryGroup.getDescription();
                }
                if (shareUrl == null) {
                    //TODO: url
                    shareUrl = "";
                }
            }

            CommentThread thread = CommentClient.getThread(threadId);

            DiscussionPermissions discussionPermissions = (DiscussionPermissions)
                    portletRequest.getAttribute(DiscussionPermissions.REQUEST_ATTRIBUTE_NAME);
            if (discussionPermissions == null) {
                discussionPermissions = new DiscussionPermissions(portletRequest);
            } else {
                _log.info("Found custom DiscussionPermissions of type " + discussionPermissions.getClass().getName());
            }

            pageContext.setAttribute("thread", thread);
            pageContext.setAttribute("shareTitle", shareTitle);
            pageContext.setAttribute("shareUrl", shareUrl);
            pageContext.setAttribute("newMessage", new NewMessageWrapper());
            pageContext.setAttribute("discussionPermissions", discussionPermissions);
        } catch (JspException | CategoryGroupNotFoundException
                | ThreadNotFoundException | CategoryNotFoundException e) {
            e.printStackTrace();
        }
        return EVAL_BODY_INCLUDE;
    }

    private PortletRequest getPortletRequest(PageContext pageContext) throws JspException{
        PortletRequest portletRequest = (PortletRequest) pageContext.getAttribute("javax.portlet.request", PageContext.REQUEST_SCOPE);
        if (portletRequest == null) {
            throw new JspException("Can't find portlet request");
        }
        return portletRequest;
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

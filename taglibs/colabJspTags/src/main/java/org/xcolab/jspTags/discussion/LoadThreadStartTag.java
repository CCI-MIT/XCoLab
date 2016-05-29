package org.xcolab.jspTags.discussion;

import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.xcolab.jspTags.discussion.wrappers.DiscussionCategoryGroupWrapper;
import org.xcolab.jspTags.discussion.wrappers.NewMessageWrapper;
import org.xcolab.jspTags.discussion.wrappers.ThreadWrapper;

import javax.portlet.PortletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Created by johannes on 12/2/15.
 */
public class LoadThreadStartTag extends BodyTagSupport {

    private static final Log _log = LogFactoryUtil.getLog(LoadThreadStartTag.class);

    private ThreadWrapper thread;
    private long categoryGroupId;

    @Override
    public int doStartTag() throws JspException {
        try {
            PortletRequest portletRequest = getPortletRequest(pageContext);
            DiscussionCategoryGroup dcg = null;
            if (categoryGroupId > 0) {
                dcg = DiscussionCategoryGroupLocalServiceUtil.fetchDiscussionCategoryGroup(categoryGroupId);
                if (thread == null && dcg.getCommentsThread() > 0) {
                    thread = new ThreadWrapper(DiscussionMessageLocalServiceUtil.fetchDiscussionMessage(dcg.getCommentsThread()));
                }
            }
            if (dcg == null) {
                dcg = thread.getCategoryGroup();
            }
            DiscussionPermissions discussionPermissions = (DiscussionPermissions) portletRequest.getAttribute(
                    DiscussionPermissions.REQUEST_ATTRIBUTE_NAME);
            if (discussionPermissions == null) {
                discussionPermissions = new DiscussionPermissions(portletRequest, dcg);
            } else {
                _log.info("Found custom DiscussionPermissions of type " + discussionPermissions.getClass().getName());
            }
            pageContext.setAttribute("thread", thread);
            pageContext.setAttribute("categoryGroup", new DiscussionCategoryGroupWrapper(dcg));
            pageContext.setAttribute("newMessage", new NewMessageWrapper());
            pageContext.setAttribute("discussionPermissions", discussionPermissions);
        } catch (PortalException | SystemException | JspException e) {
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

    public ThreadWrapper getThread() {
        return thread;
    }

    public void setThread(ThreadWrapper thread) {
        this.thread = thread;
    }
}

package org.xcolab.jsp.tags.discussion;

import java.util.Enumeration;

import javax.portlet.PortletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.xcolab.jsp.tags.discussion.wrappers.DiscussionCategoryGroupWrapper;
import org.xcolab.jsp.tags.discussion.wrappers.NewMessageWrapper;

import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class LoadDiscussionTag extends BodyTagSupport {
    
    private long discussionId;

    public long getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(long discussionId) {
        this.discussionId = discussionId;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            DiscussionCategoryGroup dcg = DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(discussionId);
            PortletRequest portletRequest = (PortletRequest) pageContext.getAttribute("javax.portlet.request", PageContext.REQUEST_SCOPE);
            if (portletRequest == null) {
                throw new JspException("Can't find portlet request");
            }
            pageContext.setAttribute("discussion", new DiscussionCategoryGroupWrapper(dcg));
            pageContext.setAttribute("newMessage", new NewMessageWrapper());
            pageContext.setAttribute("discussionPermissions", new DiscussionPermissions(portletRequest, dcg));
        } catch (PortalException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        }
        return EVAL_BODY_INCLUDE; 
    }
    
    
}

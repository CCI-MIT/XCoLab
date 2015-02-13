package org.xcolab.jspTags.discussion;

import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import org.xcolab.jspTags.discussion.wrappers.DiscussionCategoryGroupWrapper;
import org.xcolab.jspTags.discussion.wrappers.NewMessageWrapper;

import javax.portlet.PortletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class LoadDiscussionStartTag extends BodyTagSupport {

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
            PortletRequest portletRequest = getPortletRequest(pageContext);
            DiscussionCategoryGroup dcg = DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(discussionId);
            pageContext.setAttribute("discussion", new DiscussionCategoryGroupWrapper(dcg));
            pageContext.setAttribute("newMessage", new NewMessageWrapper());
            pageContext.setAttribute("discussionPermissions", new DiscussionPermission(portletRequest, dcg));
        } catch (Exception e) {
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
    
    
}

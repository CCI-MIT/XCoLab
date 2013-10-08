package org.xcolab.jsp.tags.discussion;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import org.xcolab.jsp.tags.discussion.wrappers.DiscussionCategoryGroupWrapper;
import org.xcolab.jsp.tags.discussion.wrappers.NewMessageWrapper;

import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class LoadDiscussionTag implements Tag {
    
    private long discussionId;
    private PageContext pageContext;

    public long getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(long discussionId) {
        this.discussionId = discussionId;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.setAttribute("discussion", new DiscussionCategoryGroupWrapper(DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(discussionId)));
            pageContext.setAttribute("newMessage", new NewMessageWrapper());
        } catch (PortalException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    @Override
    public void setPageContext(PageContext pc) {
        pageContext = pc;
        
    }

    @Override
    public void setParent(Tag t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Tag getParent() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void release() {
        // TODO Auto-generated method stub
        
    }
    
    
}

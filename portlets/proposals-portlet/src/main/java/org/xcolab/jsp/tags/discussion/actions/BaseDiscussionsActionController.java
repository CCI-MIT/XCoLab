package org.xcolab.jsp.tags.discussion.actions;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;

import org.xcolab.jsp.tags.discussion.DiscussionPermissions;
import org.xcolab.jsp.tags.discussion.exceptions.DiscussionsException;

import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.util.PortalUtil;

public abstract class BaseDiscussionsActionController {

    public void checkPermissions(ActionRequest request, String accessDeniedMessage, long discussionId) 
            throws DiscussionsException, PortalException, SystemException {

        DiscussionCategoryGroup dcg = DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(discussionId);
        DiscussionPermissions permissions = new DiscussionPermissions(request, dcg);
        
        if (!isUserAllowed(permissions)) {
            throw new DiscussionsException(accessDeniedMessage);
        }
    }
    
    public void redirectToReferer(ActionRequest request, ActionResponse response) throws IOException {
        
        request.setAttribute("ACTION_REDIRECTING", true);
        
        HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
        String referer = httpRequest.getHeader("referer");
        response.sendRedirect(referer);
    }

    public abstract boolean isUserAllowed(DiscussionPermissions permissions);
}
package org.xcolab.portlets.discussions.views;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.pojo.CategoryGroup;
import org.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.jspTags.discussion.exceptions.DiscussionAuthorizationException;
import org.xcolab.portlets.discussions.DiscussionPreferences;

import javax.portlet.PortletRequest;

@Controller
@RequestMapping("view")
public abstract class BaseDiscussionController {

    protected CategoryGroup getCategoryGroup(PortletRequest request) {
        DiscussionPreferences preferences = new DiscussionPreferences(request);

        try {
            return CommentClient.getCategoryGroup(preferences.getCategoryGroupId());
        } catch (CategoryGroupNotFoundException e) {
            throw new IllegalStateException("Category group does not exist: " + preferences.getCategoryGroupId());
        }
    }

    protected void checkCanView(PortletRequest request, String accessDeniedMessage, CategoryGroup categoryGroup, long additionalId) throws DiscussionAuthorizationException, PortalException, SystemException {
        checkPermissions(request, accessDeniedMessage, categoryGroup, additionalId, false);
    }

    protected void checkCanEdit(PortletRequest request, String accessDeniedMessage, CategoryGroup categoryGroup, long additionalId) throws DiscussionAuthorizationException, PortalException, SystemException {
        checkPermissions(request, accessDeniedMessage, categoryGroup, additionalId, true);
    }

    private void checkPermissions(PortletRequest request, String accessDeniedMessage, CategoryGroup categoryGroup,
                                    long additionalId, boolean checkEditPermissions) throws DiscussionAuthorizationException, SystemException, PortalException {
        DiscussionPermissions permissions = new DiscussionPermissions(request);

        if (additionalId > 0 && !getCanView(permissions, categoryGroup, additionalId)) {
            throw new DiscussionAuthorizationException(accessDeniedMessage);
        }
        if (checkEditPermissions && !getCanEdit(permissions, categoryGroup, additionalId)) {
            throw new DiscussionAuthorizationException(accessDeniedMessage);
        }
    }

    public abstract boolean getCanView(DiscussionPermissions permissions, CategoryGroup categoryGroup, long additionalId) throws SystemException;
    public abstract boolean getCanEdit(DiscussionPermissions permissions, CategoryGroup categoryGroup, long additionalId) throws SystemException;
}

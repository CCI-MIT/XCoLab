package org.xcolab.portlets.discussions.views;

import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.jspTags.discussion.exceptions.DiscussionAuthorizationException;
import org.xcolab.jspTags.discussion.wrappers.DiscussionCategoryGroupWrapper;
import org.xcolab.portlets.discussions.DiscussionPreferences;

import javax.portlet.PortletRequest;

/**
 * Created by johannes on 12/5/15.
 */
@Controller
@RequestMapping("view")
public abstract class BaseDiscussionController {

    protected DiscussionCategoryGroupWrapper getDiscussionCategoryGroupWrapper(PortletRequest request) throws SystemException {
        DiscussionPreferences preferences = new DiscussionPreferences(request);

        return new DiscussionCategoryGroupWrapper(
                DiscussionCategoryGroupLocalServiceUtil.fetchDiscussionCategoryGroup(preferences.getCategoryGroupId()));
    }

    protected void checkCanView(PortletRequest request, String accessDeniedMessage, DiscussionCategoryGroup dcg, long additionalId) throws DiscussionAuthorizationException, PortalException, SystemException {
        checkPermissions(request, accessDeniedMessage, dcg, additionalId, false);
    }

    protected void checkCanEdit(PortletRequest request, String accessDeniedMessage, DiscussionCategoryGroup dcg, long additionalId) throws DiscussionAuthorizationException, PortalException, SystemException {
        checkPermissions(request, accessDeniedMessage, dcg, additionalId, true);
    }

    private void checkPermissions(PortletRequest request, String accessDeniedMessage, DiscussionCategoryGroup dcg,
                                    long additionalId, boolean checkEditPermissions) throws DiscussionAuthorizationException, SystemException, PortalException {
        DiscussionPermissions permissions = new DiscussionPermissions(request, dcg);

        if (additionalId > 0 && !getCanView(permissions, additionalId)) {
            throw new DiscussionAuthorizationException(accessDeniedMessage);
        }
        if (checkEditPermissions && !getCanEdit(permissions, additionalId)) {
            throw new DiscussionAuthorizationException(accessDeniedMessage);
        }
    }

    public abstract boolean getCanView(DiscussionPermissions permissions, long additionalId) throws SystemException;
    public abstract boolean getCanEdit(DiscussionPermissions permissions, long additionalId) throws SystemException;
}

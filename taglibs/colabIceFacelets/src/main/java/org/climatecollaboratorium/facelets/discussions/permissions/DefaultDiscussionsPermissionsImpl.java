package org.climatecollaboratorium.facelets.discussions.permissions;

import org.climatecollaboratorium.facelets.discussions.DiscussionBean;
import org.climatecollaboratorium.utils.Helper;

import com.ext.portlet.discussions.DiscussionActions;
import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.security.permission.PermissionChecker;

public class DefaultDiscussionsPermissionsImpl implements DiscussionsPermissions {
    
    private static final String RESOURCE_NAME = DiscussionCategoryGroup.class.getName();
    private static final int RESOURCE_SCOPE = 1;
    private static final Long companyId = Helper.getThemeDisplay().getCompanyId();
    private Long groupId;
    private String primKey;
    private static Log _log = LogFactoryUtil.getLog(DefaultDiscussionsPermissionsImpl.class);
    private DiscussionBean discussionBean;
    
    public DefaultDiscussionsPermissionsImpl(DiscussionBean discussionBean) throws PortalException, SystemException {
        primKey = discussionBean.getDiscussion().getId().toString();
        groupId = discussionBean.getOwningGroupId();
        this.discussionBean = discussionBean;
        /*
        try {
            resource = ResourceLocalServiceUtil.getResource(companyId, RESOURCE_NAME, RESOURCE_SCOPE, primKey);
        }
        catch (Exception e) {
            _log.debug(e);
            resource = ResourceLocalServiceUtil.addResource(companyId, RESOURCE_NAME, RESOURCE_SCOPE, primKey);
        }
        */
    }
    
    @Override
    public boolean getCanAddCategory() {
        return getCanAdmin() || getCanAdminCategories() || permCheck().hasPermission(groupId, RESOURCE_NAME, primKey, DiscussionActions.ADD_CATEGORY.name());
    }
    
    @Override
    public boolean getCanAddThread() {
        return getCanAdmin() || getCanAdminMessages() || permCheck().hasPermission(groupId, RESOURCE_NAME, primKey, DiscussionActions.ADD_THREAD.name());
    }
    
    @Override
    public boolean getCanAddMessage() {
        return getCanAdmin() || getCanAdminMessages() || permCheck().hasPermission(groupId, RESOURCE_NAME, primKey, DiscussionActions.ADD_MESSAGE.name());
    }
    
    @Override
    public boolean getCanAdminMessages() {
        return getCanAdmin() || permCheck().hasPermission(groupId, RESOURCE_NAME, primKey, DiscussionActions.ADMIN_MESSAGES.name());
    }
     
    @Override
    public boolean getCanAdminCategories() {
        return getCanAdmin() || permCheck().hasPermission(groupId, RESOURCE_NAME, primKey, DiscussionActions.ADMIN_CATEGORIES.name());
    }
    
    @Override
    public boolean getCanAdmin() {
        return permCheck().hasPermission(groupId, RESOURCE_NAME, primKey, DiscussionActions.ADMIN.name()) || permCheck().isCommunityAdmin(groupId) || permCheck().isCompanyAdmin();
    }
    
    @Override 
    public boolean getCanSubscribe() {
        return Helper.isUserLoggedIn();
    }

    public boolean getIsLoggedIn() {
        return Helper.isUserLoggedIn();
    }

    private PermissionChecker permCheck() {
        groupId = discussionBean.getOwningGroupId();
        PermissionChecker permCheck = Helper.getPermissionChecker();
        return Helper.getPermissionChecker();
    }

    @Override
    public boolean getCanAddComment() {
        return getCanAdmin() || getCanAdminMessages() || permCheck().hasPermission(groupId, RESOURCE_NAME, primKey, DiscussionActions.ADD_COMMENT.name());
    }
}

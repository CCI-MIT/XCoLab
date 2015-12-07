package org.climatecollaboratorium.facelets.discussions.permissions;

import java.io.Serializable;

import com.ext.portlet.service.SpamReportLocalServiceUtil;
import org.climatecollaboratorium.facelets.discussions.DiscussionBean;
import org.climatecollaboratorium.facelets.discussions.support.MessageWrapper;
import org.climatecollaboratorium.utils.Helper;

import com.ext.portlet.discussions.DiscussionActions;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.security.permission.PermissionChecker;

public class DefaultDiscussionsPermissionsImpl implements DiscussionsPermissions, Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String RESOURCE_NAME = DiscussionCategoryGroup.class.getName();
    private Long groupId;
    private final String primKey;
    private static final Log _log = LogFactoryUtil.getLog(DefaultDiscussionsPermissionsImpl.class);
    private final DiscussionBean discussionBean;
    
    public DefaultDiscussionsPermissionsImpl(DiscussionBean discussionBean) throws PortalException, SystemException {
        primKey = String.valueOf(discussionBean.getDiscussion().getId());
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
        return getCanAdmin()
                //TODO: for some reason fellows don't have "ADD_MESSAGE" permissions (hence the ADMIN_MESSAGES clause
                || permCheck().hasPermission(groupId, RESOURCE_NAME, primKey, DiscussionActions.ADMIN_MESSAGES.name())
                || permCheck().hasPermission(groupId, RESOURCE_NAME, primKey, DiscussionActions.ADD_THREAD.name());
    }
    
    @Override
    public boolean getCanAddMessage() {
        return getCanAdmin()
                //TODO: for some reason fellows don't have "ADD_MESSAGE" permissions (hence the ADMIN_MESSAGES clause
                || permCheck().hasPermission(groupId, RESOURCE_NAME, primKey, DiscussionActions.ADMIN_MESSAGES.name())
                || permCheck().hasPermission(groupId, RESOURCE_NAME, primKey, DiscussionActions.ADD_MESSAGE.name());
    }
    
    @Override
    public boolean getCanAdminMessages() {
        return getCanAdmin();
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

    @Override
    public boolean getIsLoggedIn() {
        return Helper.isUserLoggedIn();
    }

    private PermissionChecker permCheck() {
        groupId = discussionBean.getOwningGroupId();
        return Helper.getPermissionChecker();
    }

    @Override
    public boolean getCanReportSpam() {
        return getCanAdminMessages();
    }

    @Override
    public boolean getCanAdminSpamReports() {
        return getCanAdminMessages();
    }

    @Override
    public boolean getCanReportMessage(MessageWrapper message) throws SystemException {
        final long userId = Long.valueOf(Helper.getLiferayUserId());
        return getCanReportSpam()
                && message.getAuthorId() != userId
                && !SpamReportLocalServiceUtil.hasReporterUserIdDiscussionMessageId(userId, message.getId());
    }

    @Override
    public boolean getCanRemoveSpamReport(MessageWrapper message) throws SystemException {
        final long userId = Long.valueOf(Helper.getLiferayUserId());
        return getCanAdminSpamReports()
                && message.getAuthorId() != userId
                && SpamReportLocalServiceUtil.hasReporterUserIdDiscussionMessageId(userId, message.getId());
    }

    @Override
    public boolean getCanAddComment() {
        return getCanAdmin() || getCanAdminMessages() || permCheck().hasPermission(groupId, RESOURCE_NAME, primKey, DiscussionActions.ADD_COMMENT.name());
    }
}

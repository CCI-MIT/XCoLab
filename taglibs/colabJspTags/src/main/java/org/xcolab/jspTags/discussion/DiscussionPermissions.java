package org.xcolab.jspTags.discussion;

import com.ext.portlet.discussions.DiscussionActions;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.ext.portlet.service.SpamReportLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import org.xcolab.jspTags.discussion.wrappers.DiscussionMessageWrapper;

import javax.portlet.PortletRequest;


public class DiscussionPermissions {

    public static final String REQUEST_ATTRIBUTE_NAME = "DISCUSSION_PERMISSIONS";

    private static final String RESOURCE_NAME = DiscussionCategoryGroup.class.getName();

    protected final User currentUser;
    protected final PermissionChecker permissionChecker;
    protected DiscussionCategoryGroup discussionCategoryGroup;
    protected final long groupId;

    public DiscussionPermissions(PortletRequest request, DiscussionCategoryGroup discussionCategoryGroup)
            throws SystemException, PortalException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        this.permissionChecker = themeDisplay.getPermissionChecker();
        this.discussionCategoryGroup = discussionCategoryGroup;
        groupId = themeDisplay.getScopeGroupId();
        currentUser = themeDisplay.getUser();
    }

    public boolean getCanReportSpam() {
        return getCanAdminMessages();
    }

    public boolean getCanAdminSpamReports() {
        return getCanAdminMessages();
    }

    public boolean getCanReportMessage(DiscussionMessageWrapper message) throws SystemException {
        return getCanReportSpam()
                && message.getAuthorId() != currentUser.getUserId()
                && !SpamReportLocalServiceUtil.hasReporterUserIdDiscussionMessageId(currentUser.getUserId(), message.getMessageId());
    }

    public boolean getCanRemoveSpamReport(DiscussionMessageWrapper message) throws SystemException {
        return getCanAdminSpamReports()
                && message.getAuthorId() != currentUser.getUserId()
                && SpamReportLocalServiceUtil.hasReporterUserIdDiscussionMessageId(currentUser.getUserId(), message.getMessageId());
    }

    public boolean getCanSeeAddCommentButton(){
        return true;
    }

    public boolean getCanViewThread(long threadId) throws SystemException {
        DiscussionMessage thread = DiscussionMessageLocalServiceUtil.fetchDiscussionMessage(threadId);
        return thread != null && discussionCategoryGroup.getId() == thread.getCategoryGroupId();
    }

    public boolean getCanAddComment() {
        return !currentUser.isDefaultUser();
    }

    public boolean getCanAdminMessages() {
        return getCanAdmin();
    }

    public boolean getCanAdminMessage(DiscussionMessageWrapper message) {
        return getCanAdmin(); // || message.getAuthorId() == currentUser.getUserId()
    }

    public boolean getCanCreateCategory() {
        return getCanAdmin();
    }

    public boolean getCanAdmin() {
        return permissionChecker.hasPermission(groupId, RESOURCE_NAME, discussionCategoryGroup.getId(), DiscussionActions.ADMIN.name())
                || permissionChecker.isGroupAdmin(groupId)
                || permissionChecker.isCompanyAdmin();
    }
}

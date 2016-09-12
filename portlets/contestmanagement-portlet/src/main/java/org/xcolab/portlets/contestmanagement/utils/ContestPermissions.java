package org.xcolab.portlets.contestmanagement.utils;


import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.enums.MemberRole;
import org.xcolab.interfaces.TabPermissions;
import org.xcolab.wrappers.BaseContestWrapper;

import javax.portlet.PortletRequest;

//TODO: what's the difference to ContestManagementPermissions?
public class ContestPermissions implements TabPermissions {

    private final User user;
    private final BaseContestWrapper contestWrapper;
    private final boolean isUserNotLoggedIn;

    public ContestPermissions(PortletRequest request, Contest contest) {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        user = themeDisplay.getUser();
        isUserNotLoggedIn = user.isDefaultUser();
        contestWrapper = new BaseContestWrapper(contest);
    }

    @Override
    public boolean getCanRole(MemberRole role) {
        return !isUserNotLoggedIn && contestWrapper
                .getHasUserRoleInContest(user.getUserId(), role.getPrintName());
    }

    @Override
    public boolean getIsOwner() {
        return !isUserNotLoggedIn && user != null && contestWrapper.getAuthorId() == user.getUserId();
    }

    @Override
    public boolean getCanAdmin() {
        return !isUserNotLoggedIn && PermissionsClient.canAdminAll(user.getUserId());
    }

    @Override
    public boolean getCanStaff() {
        if (isUserNotLoggedIn) {
            return false;
        }
        try {
            return RoleLocalServiceUtil.hasUserRole(user.getUserId(), MemberRole.STAFF.getRoleId());
        } catch (SystemException ignored) {
        }
        return false;
    }

    public boolean getCanEdit() {
        return !isUserNotLoggedIn && !user.isDefaultUser();
    }

    @Override
    public boolean getCanDelete() {
        return !isUserNotLoggedIn && !user.isDefaultUser();
    }

    @Override
    public boolean getCanCreate() {
        // TODO check who needs this
        return !isUserNotLoggedIn && !user.isDefaultUser();
    }

    public boolean getCanAdminAll() {
        return PermissionsClient.canAdminAll(user.getUserId());
    }
}

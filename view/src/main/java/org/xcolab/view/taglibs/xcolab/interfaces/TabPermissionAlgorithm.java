package org.xcolab.view.taglibs.xcolab.interfaces;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.user.permissions.SystemRole;
import org.xcolab.view.auth.MemberAuthUtil;

import javax.servlet.http.HttpServletRequest;

public interface TabPermissionAlgorithm {

    boolean canView(TabPermissions permissions, TabContext context, HttpServletRequest request);

    boolean canEdit(TabPermissions permissions, TabContext context, HttpServletRequest request);

    boolean getCanAddComment(TabPermissions permissions, TabContext context,
            HttpServletRequest request);

    TabPermissionAlgorithm alwaysTrueViewAndEdit = new TabPermissionAlgorithm() {

        @Override
        public boolean canView(TabPermissions permissions, TabContext context,
                HttpServletRequest request) {
            return true;
        }

        @Override
        public boolean canEdit(TabPermissions permissions, TabContext context,
                HttpServletRequest request) {
            return true;
        }

        @Override
        public boolean getCanAddComment(TabPermissions permissions, TabContext context,
                HttpServletRequest request) {
            return true;
        }
    };

    TabPermissionAlgorithm alwaysFalseViewAndEdit = new TabPermissionAlgorithm() {

        @Override
        public boolean canView(TabPermissions permissions, TabContext context,
                HttpServletRequest request) {
            return false;
        }

        @Override
        public boolean canEdit(TabPermissions permissions, TabContext context,
                HttpServletRequest request) {
            return false;
        }

        @Override
        public boolean getCanAddComment(TabPermissions permissions, TabContext context,
                HttpServletRequest request) {
            return false;
        }
    };

    TabPermissionAlgorithm adminOnlyViewAndEdit = new TabPermissionAlgorithm() {

        @Override
        public boolean canView(TabPermissions permissions, TabContext context,
                HttpServletRequest request) {
            return permissions.getCanAdmin();
        }

        @Override
        public boolean canEdit(TabPermissions permissions, TabContext context,
                HttpServletRequest request) {
            return permissions.getCanAdmin();
        }

        @Override
        public boolean getCanAddComment(TabPermissions permissions, TabContext context,
                HttpServletRequest request) {
            return permissions.getCanAdmin();
        }
    };

    TabPermissionAlgorithm contestCreationViewAndEdit = new TabPermissionAlgorithm() {

        @Override
        public boolean canView(TabPermissions permissions, TabContext context,
                HttpServletRequest request) {
            return permissions.getCanRole(SystemRole.CONTEST_MANAGER) || permissions
                    .getCanRole(SystemRole.FELLOW) || permissions.getCanAdmin();
        }

        @Override
        public boolean canEdit(TabPermissions permissions, TabContext context,
                HttpServletRequest request) {
            return canView(permissions, context, request);
        }

        @Override
        public boolean getCanAddComment(TabPermissions permissions, TabContext context,
                HttpServletRequest request) {
            return canView(permissions, context, request);
        }
    };

    TabPermissionAlgorithm contestDetailsViewAndEdit = new TabPermissionAlgorithm() {
        @Override
        public boolean canView(TabPermissions permissions, TabContext context,
                HttpServletRequest request) {
            final ContestWrapper contest = context.getContest(request);
            final long userId = MemberAuthUtil.getUserId();
            return permissions.getCanAdmin() || contest.getCanFellow(userId);
        }

        @Override
        public boolean canEdit(TabPermissions permissions, TabContext context,
                HttpServletRequest request) {
            return canView(permissions, context, request);
        }

        @Override
        public boolean getCanAddComment(TabPermissions permissions, TabContext context,
                HttpServletRequest request) {
            return canView(permissions, context, request);
        }
    };

}

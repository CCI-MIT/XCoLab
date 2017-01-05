package org.xcolab.view.taglibs.xcolab.interfaces;

import org.xcolab.entity.utils.enums.MemberRole;

import javax.servlet.http.HttpServletRequest;


public interface TabPermissionAlgorithm {
    boolean canView(TabPermissions permissions, TabContext context, HttpServletRequest request);
    boolean canEdit(TabPermissions permissions, TabContext context, HttpServletRequest request);
    boolean getCanAddComment(TabPermissions permissions, TabContext context, HttpServletRequest request);

    public final static TabPermissionAlgorithm alwaysTrueViewAndEdit = new TabPermissionAlgorithm() {

        @Override
        public boolean canView(TabPermissions permissions, TabContext context, HttpServletRequest request) {
            return true;
        }
        @Override
        public boolean canEdit(TabPermissions permissions, TabContext context, HttpServletRequest request) {
            return true;
        }
        @Override
        public boolean getCanAddComment(TabPermissions permissions, TabContext context, HttpServletRequest request) {
            return true;
        }
    };

    public final static TabPermissionAlgorithm alwaysFalseViewAndEdit = new TabPermissionAlgorithm() {

        @Override
        public boolean canView(TabPermissions permissions, TabContext context, HttpServletRequest request) {
            return false;
        }
        @Override
        public boolean canEdit(TabPermissions permissions, TabContext context, HttpServletRequest request) {
            return false;
        }
        @Override
        public boolean getCanAddComment(TabPermissions permissions, TabContext context, HttpServletRequest request) {
            return false;
        }
    };

    public final static TabPermissionAlgorithm adminOnlyViewAndEdit = new TabPermissionAlgorithm() {

        @Override
        public boolean canView(TabPermissions permissions, TabContext context, HttpServletRequest request) {
            return permissions.getCanAdmin();
        }
        @Override
        public boolean canEdit(TabPermissions permissions, TabContext context, HttpServletRequest request) {
            return permissions.getCanAdmin();
        }
        @Override
        public boolean getCanAddComment(TabPermissions permissions, TabContext context, HttpServletRequest request) {
            return permissions.getCanAdmin();
        }
    };

    public final static TabPermissionAlgorithm
            contestCreationViewAndEdit = new TabPermissionAlgorithm() {

        @Override
        public boolean canView(TabPermissions permissions, TabContext context, HttpServletRequest request) {
            return permissions.getCanRole(MemberRole.CONTEST_MANAGER) || permissions.getCanRole(MemberRole.FELLOW) || permissions.getCanRole(MemberRole.ADVISOR) || permissions.getCanStaff() || permissions.getCanAdmin();
        }
        @Override
        public boolean canEdit(TabPermissions permissions, TabContext context, HttpServletRequest request) {
            return permissions.getCanRole(MemberRole.CONTEST_MANAGER) || permissions.getCanRole(MemberRole.FELLOW) || permissions.getCanRole(MemberRole.ADVISOR) || permissions.getCanStaff() || permissions.getCanAdmin();
        }

        @Override
        public boolean getCanAddComment(TabPermissions permissions, TabContext context, HttpServletRequest request) {
            return permissions.getCanRole(MemberRole.CONTEST_MANAGER) || permissions.getCanRole(MemberRole.FELLOW) || permissions.getCanRole(MemberRole.ADVISOR) || permissions.getCanStaff() || permissions.getCanAdmin();
        }
    };

}
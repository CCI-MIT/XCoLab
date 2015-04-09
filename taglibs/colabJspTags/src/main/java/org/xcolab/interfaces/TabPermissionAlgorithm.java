package org.xcolab.interfaces;

import org.xcolab.enums.MemberRole;

import javax.portlet.PortletRequest;

public interface TabPermissionAlgorithm {
    boolean canView(TabPermissions permissions, TabContext context, PortletRequest request);
    boolean canEdit(TabPermissions permissions, TabContext context, PortletRequest request);
    boolean getCanAddComment(TabPermissions permissions, TabContext context, PortletRequest request);

    public final static TabPermissionAlgorithm alwaysTrueViewAndEdit = new TabPermissionAlgorithm() {

        @Override
        public boolean canView(TabPermissions permissions, TabContext context, PortletRequest request) {
            return true;
        }
        @Override
        public boolean canEdit(TabPermissions permissions, TabContext context, PortletRequest request) {
            return true;
        }
        @Override
        public boolean getCanAddComment(TabPermissions permissions, TabContext context, PortletRequest request) {
            return true;
        }
    };

    public final static TabPermissionAlgorithm alwaysFalseViewAndEdit = new TabPermissionAlgorithm() {

        @Override
        public boolean canView(TabPermissions permissions, TabContext context, PortletRequest request) {
            return false;
        }
        @Override
        public boolean canEdit(TabPermissions permissions, TabContext context, PortletRequest request) {
            return false;
        }
        @Override
        public boolean getCanAddComment(TabPermissions permissions, TabContext context, PortletRequest request) {
            return false;
        }
    };

    public final static TabPermissionAlgorithm adminOnlyViewAndEdit = new TabPermissionAlgorithm() {

        @Override
        public boolean canView(TabPermissions permissions, TabContext context, PortletRequest request) {
            return permissions.getCanAdmin();
        }
        @Override
        public boolean canEdit(TabPermissions permissions, TabContext context, PortletRequest request) {
            return permissions.getCanAdmin();
        }
        @Override
        public boolean getCanAddComment(TabPermissions permissions, TabContext context, PortletRequest request) {
            return permissions.getCanAdmin();
        }
    };

    public final static TabPermissionAlgorithm contestCreationViewAndEdit = new TabPermissionAlgorithm() {

        @Override
        public boolean canView(TabPermissions permissions, TabContext context, PortletRequest request) {
            return permissions.getCanRole(MemberRole.CONTESTMANAGER) || permissions.getCanRole(MemberRole.FELLOW) || permissions.getCanRole(MemberRole.ADVISOR) || permissions.getCanStaff() || permissions.getCanAdmin();
        }
        @Override
        public boolean canEdit(TabPermissions permissions, TabContext context, PortletRequest request) {
            return permissions.getCanRole(MemberRole.CONTESTMANAGER) || permissions.getCanRole(MemberRole.FELLOW) || permissions.getCanRole(MemberRole.ADVISOR) || permissions.getCanStaff() || permissions.getCanAdmin();
        }

        @Override
        public boolean getCanAddComment(TabPermissions permissions, TabContext context, PortletRequest request) {
            return permissions.getCanRole(MemberRole.CONTESTMANAGER) || permissions.getCanRole(MemberRole.FELLOW) || permissions.getCanRole(MemberRole.ADVISOR) || permissions.getCanStaff() || permissions.getCanAdmin();
        }
    };

}
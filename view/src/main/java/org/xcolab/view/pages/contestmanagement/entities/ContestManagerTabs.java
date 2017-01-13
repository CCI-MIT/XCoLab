package org.xcolab.view.pages.contestmanagement.entities;


import org.xcolab.view.taglibs.xcolab.interfaces.TabActivityCountAlgorithm;
import org.xcolab.view.taglibs.xcolab.interfaces.TabContext;
import org.xcolab.view.taglibs.xcolab.interfaces.TabEnum;
import org.xcolab.view.taglibs.xcolab.interfaces.TabPermissionAlgorithm;
import org.xcolab.view.taglibs.xcolab.interfaces.TabPermissions;

import javax.servlet.http.HttpServletRequest;

//TODO: adjust permissions!
public enum ContestManagerTabs implements TabEnum {
    OVERVIEW("Contests", "Contests",
//            TabPermissionAlgorithm.contestCreationViewAndEdit,
            TabPermissionAlgorithm.alwaysTrueViewAndEdit,
            TabActivityCountAlgorithm.alwaysZero),
    SCHEDULES("Schedules", "schedule",
//            TabPermissionAlgorithm.adminOnlyViewAndEdit,
            TabPermissionAlgorithm.alwaysTrueViewAndEdit,
            TabActivityCountAlgorithm.alwaysZero),
    PROPOSAL_TEMPLATES("Proposal Templates", "template",
//            TabPermissionAlgorithm.adminOnlyViewAndEdit,
            TabPermissionAlgorithm.alwaysTrueViewAndEdit,
            TabActivityCountAlgorithm.alwaysZero),
    EMAIL_TEMPLATES("Emails", "emailTemplate",
//            TabPermissionAlgorithm.adminOnlyViewAndEdit,
            TabPermissionAlgorithm.alwaysTrueViewAndEdit,
            TabActivityCountAlgorithm.alwaysZero),
    FLAGGING("Flagging", "flagging",
//            TabPermissionAlgorithm.adminOnlyViewAndEdit,
            TabPermissionAlgorithm.alwaysTrueViewAndEdit,
            TabActivityCountAlgorithm.alwaysZero), //TODO: activate flag count
    COLLECTION_CARDS("Collection Cards", "collectionCard",
//            TabPermissionAlgorithm.adminOnlyViewAndEdit,
            TabPermissionAlgorithm.alwaysTrueViewAndEdit,
            TabActivityCountAlgorithm.alwaysZero),
    EDITORS("Editors", "editors",
//            TabPermissionAlgorithm.adminOnlyViewAndEdit,
            TabPermissionAlgorithm.alwaysTrueViewAndEdit,
            TabActivityCountAlgorithm.alwaysZero);;

    private final String displayName;
    private final String elementType;
    private final TabPermissionAlgorithm tabPermissionAlgorithm;
    private final TabActivityCountAlgorithm activitiesCountAlgorithm;

    ContestManagerTabs(String displayName, String elementType,
            TabPermissionAlgorithm tabPermissionAlgorithm,
            TabActivityCountAlgorithm activitiesCountAlgorithm) {
        this.displayName = displayName;
        this.tabPermissionAlgorithm = tabPermissionAlgorithm;
        this.activitiesCountAlgorithm = activitiesCountAlgorithm;
        this.elementType = elementType;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String getElementType() {
        return elementType;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public boolean getIsDefault() {
        return this.ordinal() == 0;
    }

    @Override
    public boolean getCanView(TabPermissions permissions, TabContext context,
            HttpServletRequest request) {
        return tabPermissionAlgorithm.canView(permissions, context, request);
    }

    @Override
    public boolean getCanEdit(TabPermissions permissions, TabContext context,
            HttpServletRequest request) {
        return tabPermissionAlgorithm.canEdit(permissions, context, request);
    }

    @Override
    public boolean getCanAddComment(TabPermissions permissions, TabContext context,
            HttpServletRequest request) {
        return tabPermissionAlgorithm.getCanAddComment(permissions, context, request);
    }

    @Override
    public int getActivityCount(TabContext context, HttpServletRequest request) {
        return activitiesCountAlgorithm.getActivityCount(context, request);
    }

    public String getTabUrl() {
        return "/admin/contest/manager/tab/" + name();
    }

    public String getTabUrl(long elementId) {
        return getTabUrl() + "?elementId=" + elementId;
    }

    public String getTabUrl(String elementId) {
        return getTabUrl() + "?elementId=" + elementId;
    }

}

package org.xcolab.portlets.contestmanagement.entities;

import org.xcolab.interfaces.TabActivityCountAlgorithm;
import org.xcolab.interfaces.TabContext;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.interfaces.TabPermissionAlgorithm;
import org.xcolab.interfaces.TabPermissions;

import javax.portlet.PortletRequest;

public enum ContestDetailsTabs implements TabEnum {
    DESCRIPTION("Homepage", "description", TabPermissionAlgorithm.contestCreationViewAndEdit,
            TabActivityCountAlgorithm.alwaysZero),
    RESOURCES("Resources Page", "resources", TabPermissionAlgorithm.contestCreationViewAndEdit,
            TabActivityCountAlgorithm.alwaysZero),
    TEAM("Team", "team", TabPermissionAlgorithm.contestCreationViewAndEdit, TabActivityCountAlgorithm.alwaysZero),
    ONTOLOGY("Ontology", "ontology", TabPermissionAlgorithm.contestCreationViewAndEdit,
            TabActivityCountAlgorithm.alwaysZero),
    ADMIN("Admin", "admin", TabPermissionAlgorithm.contestCreationViewAndEdit, TabActivityCountAlgorithm.alwaysZero),
    PROPOSALTEMPLATE("Proposal Template", "template", TabPermissionAlgorithm.alwaysFalseViewAndEdit,
            TabActivityCountAlgorithm.alwaysZero),
    ADVANCED("Advanced", "advanced", TabPermissionAlgorithm.alwaysFalseViewAndEdit,
            TabActivityCountAlgorithm.alwaysZero);

    private final String displayName;
    private final String elementType;
    private final TabPermissionAlgorithm tabPermissionAlgorithm;
    private final TabActivityCountAlgorithm activitiesCountAlgorithm;

    ContestDetailsTabs(String displayName, String elementType, TabPermissionAlgorithm tabPermissionAlgorithm,
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
    public String getName() {
        return this.name();
    }

    @Override
    public String getElementType() {
        return elementType;
    }

    @Override
    public boolean getIsDefault() {
        return this.ordinal() == 0;
    }

    @Override
    public boolean getCanView(TabPermissions permissions, TabContext context, PortletRequest request) {
        return tabPermissionAlgorithm.canView(permissions, context, request);
    }

    @Override
    public boolean getCanEdit(TabPermissions permissions, TabContext context, PortletRequest request) {
        return tabPermissionAlgorithm.canEdit(permissions, context, request);
    }

    @Override
    public boolean getCanAddComment(TabPermissions permissions, TabContext context, PortletRequest request) {
        return tabPermissionAlgorithm.getCanAddComment(permissions, context, request);
    }

    @Override
    public int getActivityCount(TabContext context, PortletRequest request) {
        return activitiesCountAlgorithm.getActivityCount(context, request);
    }

}

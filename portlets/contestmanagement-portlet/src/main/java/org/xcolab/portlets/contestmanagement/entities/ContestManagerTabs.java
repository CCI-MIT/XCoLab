package org.xcolab.portlets.contestmanagement.entities;

import org.xcolab.interfaces.*;

import javax.portlet.PortletRequest;

/**
 * Created by Thomas on 2/9/2015.
 */
public enum ContestManagerTabs implements TabEnum{
		OVERVIEW("Contests overview", "Contests", TabPermissionAlgorithm.contestCreationViewAndEdit, TabActivityCountAlgorithm.alwaysZero),
	//PHASES("Phase types", "", TabPermissionAlgorithm.contestCreationViewAndEdit, TabActivityCountAlgorithm.alwaysZero),
	SCHEDULES("Schedules", "schedule", TabPermissionAlgorithm.adminOnlyViewAndEdit, TabActivityCountAlgorithm.alwaysZero),
	PROPOSALTEMPLATES("Proposal Templates", "template", TabPermissionAlgorithm.adminOnlyViewAndEdit, TabActivityCountAlgorithm.alwaysZero);

	private final String displayName;
	private final String elementType;
	private final TabPermissionAlgorithm tabPermissionAlgorithm;
	private final TabActivityCountAlgorithm activitiesCountAlgorithm;

	private ContestManagerTabs(String displayName, String elementType, TabPermissionAlgorithm tabPermissionAlgorithm,
							   TabActivityCountAlgorithm activitiesCountAlgorithm) {
		this.displayName = displayName;
		this.tabPermissionAlgorithm = tabPermissionAlgorithm;
		this.activitiesCountAlgorithm = activitiesCountAlgorithm;
		this.elementType = elementType;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getElementType() {
		return elementType;
	}

	public String getName() {
		return this.name();
	}

	public boolean getIsDefault() {
		return this.ordinal() == 0;
	}

	public boolean getCanView(TabPermissions permissions, TabContext context, PortletRequest request) {
		return tabPermissionAlgorithm.canView(permissions, context, request);
	}

	public boolean getCanEdit(TabPermissions permissions, TabContext context, PortletRequest request) {
		return tabPermissionAlgorithm.canEdit(permissions, context, request);
	}

	public boolean getCanAddComment(TabPermissions permissions, TabContext context, PortletRequest request){
		return tabPermissionAlgorithm.getCanAddComment(permissions, context, request);
	}

	public int getActivityCount(TabContext context, PortletRequest request){
		return activitiesCountAlgorithm.getActivityCount(context, request);
	}

}

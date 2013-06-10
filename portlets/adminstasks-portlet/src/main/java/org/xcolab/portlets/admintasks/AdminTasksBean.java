package org.xcolab.portlets.admintasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ext.portlet.discussions.DiscussionActions;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.PlanAttribute;
import com.ext.portlet.model.PlanDescription;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.model.PlanSection;
import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.ext.portlet.service.PlanSectionLocalServiceUtil;
import com.liferay.portal.NoSuchResourceException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Permission;
import com.liferay.portal.model.Resource;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.PermissionLocalServiceUtil;
import com.liferay.portal.service.ResourceLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;

public class AdminTasksBean {
  
    
    public String populateFirstEmptySectionWithDescription() throws SystemException, PortalException {
        for (PlanItem plan: PlanItemLocalServiceUtil.getPlans()) {
            List<PlanSection> sections = PlanItemLocalServiceUtil.getPlanSections(plan);
            if (sections == null || sections.isEmpty()) continue;
            
            // ignore plans where first section isn't empty
            if (sections.get(0).getContent() == null && sections.get(0).getContent().trim().length() > 0) continue;
            
            // ignore plans with empty description
            if (PlanItemLocalServiceUtil.getDescription(plan) == null || PlanItemLocalServiceUtil.getDescription(plan).trim().length() == 0) continue;
            
            PlanItemLocalServiceUtil.setSectionContent(plan, 
                    PlanSectionLocalServiceUtil.getDefinition(sections.get(0)), 
                    PlanItemLocalServiceUtil.getDescription(plan), null, PlanItemLocalServiceUtil.getAuthorId(plan));
        }
        return null;
    }

    private final long defaultCompanyId = 10112L;
    public String fixWikiPermissions() throws SystemException, PortalException {
    	
    	Long companyId = defaultCompanyId;
        Role guest = RoleLocalServiceUtil.getRole(companyId, RoleConstants.GUEST);
        Role userRole = RoleLocalServiceUtil.getRole(companyId, RoleConstants.USER);
        Role siteMemberRole = RoleLocalServiceUtil.getRole(companyId, RoleConstants.SITE_MEMBER);
        String[] guestActions = { ActionKeys.VIEW };
        
        int idx = 0;
        int total = WikiPageLocalServiceUtil.getWikiPagesCount();
        
        String[] actionIds = {ActionKeys.VIEW};
    	for (WikiPage wp: WikiPageLocalServiceUtil.getWikiPages(0,  Integer.MAX_VALUE)) {
    		idx++;
    		System.out.println(idx + " of " + total);
    		
    		Resource resource = null;

    		try {
    	        resource = ResourceLocalServiceUtil.getResource(defaultCompanyId, 
    	        		WikiPage.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(wp.getResourcePrimKey()));
    		}
    		catch (NoSuchResourceException nsre) {
    			System.out.println("Can't find resource for page: " + wp.getPageId());
    			resource = ResourceLocalServiceUtil.addResource(defaultCompanyId, WikiPage.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(wp.getResourcePrimKey()));
    			System.out.println("New resource created: " + resource.getResourceId());
    			ResourceLocalServiceUtil.updateResource(resource);
    		}

            /*PermissionLocalServiceUtil.setRolePermissions(guest.getRoleId(), companyId,
                    WikiPage.class.getName(), ResourceConstants.SCOPE_COMPANY,
                    String.valueOf(wp.getResourcePrimKey()), guestActions);
                    */
    		PermissionLocalServiceUtil.setRolePermissions(guest.getRoleId(), actionIds, resource.getResourceId());
    		PermissionLocalServiceUtil.setRolePermissions(userRole.getRoleId(), actionIds, resource.getResourceId());
            PermissionLocalServiceUtil.setRolePermissions(siteMemberRole.getRoleId(), actionIds, resource.getResourceId());
            
			/*PermissionServiceUtil.setRolePermissions(
					guest.getRoleId(), wp.getGroupId(), actionIds,
					resource.getResourceId());
					*/
    	}
    	return null;
    }
    
    public String fixContestsDiscussionPermissions() throws SystemException, PortalException {
    	for (Contest contest: ContestLocalServiceUtil.getContests(0,  Integer.MAX_VALUE)) {
    		try {
    			Group g = GroupLocalServiceUtil.getGroup(contest.getGroupId());
    		}
    		catch (Exception e) {
    			contest.setGroupId(0L);
    			ContestLocalServiceUtil.store(contest);
    		}
    	
    	}
    	
    	ContestLocalServiceUtil.updateContestGroupsAndDiscussions();


    	Long companyId = defaultCompanyId;
        Role owner = RoleLocalServiceUtil.getRole(companyId, RoleConstants.ORGANIZATION_OWNER);
        Role admin = RoleLocalServiceUtil.getRole(companyId, RoleConstants.ORGANIZATION_ADMINISTRATOR);
        Role member = RoleLocalServiceUtil.getRole(companyId, RoleConstants.SITE_MEMBER);
        Role userRole = RoleLocalServiceUtil.getRole(companyId, RoleConstants.USER);
        Role guest = RoleLocalServiceUtil.getRole(companyId, RoleConstants.GUEST);
        Role moderator = RoleLocalServiceUtil.getRole(companyId, "Moderator");

        String[] ownerActions = { DiscussionActions.ADMIN.name(), DiscussionActions.ADD_CATEGORY.name(),
                DiscussionActions.ADD_MESSAGE.name(), DiscussionActions.ADD_THREAD.name(),
                DiscussionActions.ADMIN_CATEGORIES.name(), DiscussionActions.ADMIN_MESSAGES.name(),
                DiscussionActions.ADD_COMMENT.name() };

        String[] adminActions = { DiscussionActions.ADD_CATEGORY.name(), DiscussionActions.ADD_MESSAGE.name(),
                DiscussionActions.ADD_THREAD.name(), DiscussionActions.ADMIN_CATEGORIES.name(),
                DiscussionActions.ADMIN_MESSAGES.name(), DiscussionActions.ADD_COMMENT.name() };

        String[] moderatorActions = { DiscussionActions.ADD_CATEGORY.name(), DiscussionActions.ADD_MESSAGE.name(),
                DiscussionActions.ADD_THREAD.name(), DiscussionActions.ADMIN_CATEGORIES.name(),
                DiscussionActions.ADMIN_MESSAGES.name(), DiscussionActions.ADD_COMMENT.name() };

        String[] memberActions = { DiscussionActions.ADD_CATEGORY.name(), DiscussionActions.ADD_MESSAGE.name(),
                DiscussionActions.ADD_THREAD.name(), DiscussionActions.ADD_COMMENT.name() };

        String[] userActions = { DiscussionActions.ADD_MESSAGE.name(), DiscussionActions.ADD_THREAD.name(),
                DiscussionActions.ADD_COMMENT.name() };

        String[] guestActions = {};

        Map<Role, String[]> rolesActionsMap = new HashMap<Role, String[]>();

        rolesActionsMap.put(owner, ownerActions);
        rolesActionsMap.put(admin, adminActions);
        rolesActionsMap.put(member, memberActions);
        rolesActionsMap.put(userRole, userActions);
        rolesActionsMap.put(guest, guestActions);
        rolesActionsMap.put(moderator, moderatorActions);
    	
    	for (Contest contest: ContestLocalServiceUtil.getContests(0,  Integer.MAX_VALUE)) {
            for (Role role : rolesActionsMap.keySet()) {
            	
                PermissionLocalServiceUtil.setRolePermissions(role.getRoleId(), companyId,
                        DiscussionCategoryGroup.class.getName(), ResourceConstants.SCOPE_GROUP,
                        String.valueOf(contest.getGroupId()), rolesActionsMap.get(role));
            }
    	
    	}
    	
    	return null;
    	
    }
    
    public String fixResourceReferencesInPermissions() throws SystemException, PortalException {
    	
    	for (Permission p: PermissionLocalServiceUtil.getPermissions(0,  Integer.MAX_VALUE)) {
    		try {
    			ResourceLocalServiceUtil.getResource(p.getResourceId());
    		}
    		catch (NoSuchResourceException e) {
    			PermissionLocalServiceUtil.deletePermission(p);
    		}
    	}
    	
    	
    	
    	return null;
    }
    
    
    
}

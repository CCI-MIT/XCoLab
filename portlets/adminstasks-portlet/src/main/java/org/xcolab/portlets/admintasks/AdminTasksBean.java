package org.xcolab.portlets.admintasks;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.discussions.DiscussionActions;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.model.PlanSection;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.PlanItemGroupLocalServiceUtil;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.ext.portlet.service.PlanSectionLocalServiceUtil;
import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.NoSuchResourceException;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ClassName;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Permission;
import com.liferay.portal.model.Resource;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.PermissionLocalServiceUtil;
import com.liferay.portal.service.ResourceLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;

import edu.emory.mathcs.backport.java.util.Collections;

public class AdminTasksBean {
    private Log _log = LogFactoryUtil.getLog(SyncProposalSupportersBetweenPhasesTask.class);
  
    
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
    
    
    public String syncSupporters() throws SystemException, PortalException {
        new SyncProposalSupportersBetweenPhasesTask().syncSupporters();
        return null;
    }
    
    public String populatePlanItemPhaseMapping() throws PortalException, SystemException {
        
        _log.info("Syncing supporters");
        
        Map<String, Set<Long>> plansToMap = new HashMap<String, Set<Long>>();
        
        _log.info("fetching plans");
        for (PlanItem plan: PlanItemLocalServiceUtil.getPlans()) {
            Contest contest = PlanItemLocalServiceUtil.getContest(plan);
            
            String key = contest.getContestPK() + "_" + PlanItemLocalServiceUtil.getName(plan).trim(); 
            
            Set<Long> plans = plansToMap.get(key);
            if (plans == null) {
                // use tree set to enforce natural ordering on plan ids
                plans = new TreeSet<Long>();
                plansToMap.put(key, plans);
            }
            plans.add(plan.getPlanId());
        }
        
        for (Map.Entry<String, Set<Long>> entry: plansToMap.entrySet()) {
            
            if (entry.getValue().size() > 1) {
                // we have two or more elements in a group, we should add them
                Long[] planIds = entry.getValue().toArray(new Long[] {});
                for (Long planId: planIds) {
                    // add each plan to a group
                    PlanItemGroupLocalServiceUtil.addToGroup(planIds[0], planId);
                }
            }
        }
        
        return null;
        
    }
    
    public void checkForPlanGroupOrphans() throws SystemException, NoSuchModelException, PortalException {
        boolean orphanFound = false;
        _log.info("Looking for orphans");
        for (Contest contest: ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE)) {
            if (!ContestLocalServiceUtil.isActive(contest)) continue;
            ContestPhase activePhase = ContestLocalServiceUtil.getActivePhase(contest);
            
            _log.info("   checking phase: " + activePhase.getContestPhasePK());
            
            for (PlanItem plan: ContestPhaseLocalServiceUtil.getPlans(activePhase)) {
                if (PlanItemGroupLocalServiceUtil.getPlansInGroup(plan.getPlanId()).size() != 2) {
                    _log.error("Plan should belong to a group with 2 elements " + plan.getPlanId());
                    orphanFound = true;
                }
            }
        }
        FacesMessage fm = new FacesMessage();
        if (! orphanFound) {
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            fm.setSummary("No orphans found");
        }
        else {
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            fm.setSummary("Orphans found, check logs");   
        }
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }
    
    public void addMissingCommentsSocialActivities() throws SystemException, PortalException {

       /* ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(servletContextName, name))(MESSAGE_ENTITY_CLASS_LOADER_CONTEXT, 
                "portletClassLoader");
        */
        ClassName cn = ClassNameLocalServiceUtil.getClassName(DiscussionCategoryGroup.class.getName());

        Set<String> discussionNamesToIgnore = new HashSet<String>();
        List<DiscussionCategoryGroup> dcgs = DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroups(0,  10000);
        Collections.sort(dcgs, new Comparator<DiscussionCategoryGroup>() {

            public int compare(DiscussionCategoryGroup o1, DiscussionCategoryGroup o2) {
                if (o1.getCommentsThread() <= 0) {
                    if (o2.getCommentsThread() <= 0) return (int) ( o1.getId() - o2.getId());
                    return 1;
                }
                if (o2.getCommentsThread() <= 0) {
                    return -1;
                }
                try {
                    DiscussionMessage o1m = DiscussionCategoryGroupLocalServiceUtil.getCommentThread(o1);
                    DiscussionMessage o2m = DiscussionCategoryGroupLocalServiceUtil.getCommentThread(o2);
                    if (o1m.getCreateDate().before(o2m.getCreateDate())) return -1;
                    else if (o2m.getCreateDate().before(o1m.getCreateDate())) return 1;
                    return 0;
                    
                }
                catch (Exception e) {
                    // ignore
                }
                return 0;
            }
        });
        
        Collections.reverse(dcgs);
        for (DiscussionCategoryGroup dcg: dcgs) {
            if (dcg.getCommentsThread() <= 0) continue;
            DiscussionMessage message = DiscussionCategoryGroupLocalServiceUtil.getCommentThread(dcg);
            
            if (discussionNamesToIgnore.contains(message.getSubject().trim())) continue;
            discussionNamesToIgnore.add(message.getSubject().trim());
            
            DynamicQuery activityQuery = DynamicQueryFactoryUtil.forClass(SocialActivity.class);//, portletClassLoader);

//            ActivityUtil.getExtraDataForIds(wrapped.getCategoryGroupId(), getThreadId(wrapped), wrapped.getMessageId()), 0);
            
            activityQuery.add(PropertyFactoryUtil.forName("userId").eq(message.getAuthorId()));
            activityQuery.add(PropertyFactoryUtil.forName("classNameId").eq(cn.getClassNameId()));
            activityQuery.add(PropertyFactoryUtil.forName("classPK").eq(dcg.getId()));
            activityQuery.add(PropertyFactoryUtil.forName("createDate").gt(message.getCreateDate().getTime()));
            activityQuery.add(PropertyFactoryUtil.forName("createDate").lt(message.getCreateDate().getTime() + 10000));
            
            activityQuery.addOrder(OrderFactoryUtil.desc("createDate"));

            
            List<SocialActivity> activities = SocialActivityLocalServiceUtil.dynamicQuery(activityQuery);
            if (activities.isEmpty()) {
                System.out.println("---\tNo activity for discussion " + dcg.getId());

                SocialActivityLocalServiceUtil.addUniqueActivity(message.getAuthorId(), 10136L, message.getCreateDate(),
                        DiscussionCategoryGroup.class.getName(), dcg.getId(), 
                        4, 
                        ActivityUtil.getExtraDataForIds(dcg.getId(), message.getMessageId(), message.getMessageId()), 0);
            }
            else {
                System.out.println("+++\tActivity for discussion found " + dcg.getId());
            }
        }
    }
    
    public void synchronizeSections() throws SystemException, PortalException {
        Integer[] ids = new Integer[] { 40223,40205,40207,1314911,1314988,1315032,1315112,1315124,1315412,1315611,1315633,1315655,1315712,1316011,1316044,1316148,1316236,1316312,1316348,1316812,1316836,1316848,1316924,1317420,1317431,1322211,1322222,1322233,1322305,1322317,1322329,1322341,1322353,1322364,1322375,1322386,1322397,1322408,1322419,1322430,1322442,1322454,1322465,1322476,1322487,1322498,1322509,1322520,1322531,1322542,1322553,1322564,1322575,1322586,1322597,1322608,1322619,1322630,1322641,1322652,1322663,1323112,1323124,1323136,1323148,1323160,1323172 };
        
        for (Integer id: ids) {
            PlanItem pi = PlanItemLocalServiceUtil.fetchPlanItem(id.longValue());
            
            List<Long> plans = PlanItemGroupLocalServiceUtil.getPlansInGroup(pi.getPlanId());
            
            if (plans.size() < 2) continue;
            PlanItem sourcePi = PlanItemLocalServiceUtil.getPlan(plans.get(plans.size() - 2));
                    
            List<PlanSection> sections = PlanItemLocalServiceUtil.getPlanSections(sourcePi);
            if (sections != null && !sections.isEmpty()) {
                for (PlanSection section : sections) {
                    PlanSectionDefinition def = PlanSectionLocalServiceUtil
                            .getDefinition(section);
                    
                    PlanSection targetSection = PlanSectionLocalServiceUtil.getCurrentForPlanSectionDef(pi, def);
                    if (targetSection.getContent() == null || targetSection.getContent().trim().length() == 0) {
                        targetSection.setContent(section.getContent());
                    
                        PlanSectionLocalServiceUtil.updatePlanSection(targetSection);
                    }
                }
            }
        }
    }
    
    public void findToSmallGroups() throws SystemException, PortalException {
        Integer[] contestIds = new Integer[] {23,16,15,30,24,17,26,14,25,19,11,7,18,22,13,21,10,20};
        
        boolean toSmallGroupFound = false;
        for (Contest contest: ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE)) {
            if (!ContestLocalServiceUtil.isActive(contest)) continue;
            ContestPhase activePhase = ContestLocalServiceUtil.getActivePhase(contest);
            
            _log.info("********   checking phase: " + activePhase.getContestPhasePK() + "\tplans to check: " + ContestPhaseLocalServiceUtil.getPlans(activePhase).size() + "\texpectedGroupSize: " + (ContestPhaseLocalServiceUtil.getPreviousPhases(activePhase).size() + 1));
            //ContestPhaseLocalServiceUtil.getPreviousPhases(activePhase)
            
            for (PlanItem plan: ContestPhaseLocalServiceUtil.getPlans(activePhase)) {
                System.out.println(plan.getPlanId());
                if (PlanItemGroupLocalServiceUtil.getPlansInGroup(plan.getPlanId()).size() != ContestPhaseLocalServiceUtil.getPreviousPhases(activePhase).size()+1) {
                    _log.error("\tPlan should belong to a group with " + (ContestPhaseLocalServiceUtil.getPreviousPhases(activePhase).size()+1) + " elements " + plan.getPlanId() + "\tsize: " + PlanItemGroupLocalServiceUtil.getPlansInGroup(plan.getPlanId()).size());
                    toSmallGroupFound = true;
                }
            }
        }
        FacesMessage fm = new FacesMessage();
        if (! toSmallGroupFound) {
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            fm.setSummary("No to small groups found");
        }
        else {
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            fm.setSummary("Orphans found, check logs");   
        }
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }
    
    
}

package org.xcolab.portlets.admintasks;

import java.util.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.discussions.DiscussionActions;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.model.PlanItemGroup;
import com.ext.portlet.model.PlanSection;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.ext.portlet.service.PlanItemGroupLocalServiceUtil;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.ext.portlet.service.PlanSectionLocalServiceUtil;
import com.ext.utils.UserAccountGenerator;
import com.liferay.counter.service.CounterLocalServiceUtil;
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
import com.liferay.portal.model.*;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.*;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;

import edu.emory.mathcs.backport.java.util.Collections;

public class AdminTasksBean {
    private Log _log = LogFactoryUtil.getLog(SyncProposalSupportersBetweenPhasesTask.class);


    public String populateFirstEmptySectionWithDescription() throws SystemException, PortalException {
        for (PlanItem plan : PlanItemLocalServiceUtil.getPlans()) {
            List<PlanSection> sections = PlanItemLocalServiceUtil.getPlanSections(plan);
            if (sections == null || sections.isEmpty()) continue;

            // ignore plans where first section isn't empty
            if (sections.get(0).getContent() == null && sections.get(0).getContent().trim().length() > 0) continue;

            // ignore plans with empty description
            if (PlanItemLocalServiceUtil.getDescription(plan) == null || PlanItemLocalServiceUtil.getDescription(plan).trim().length() == 0)
                continue;

            PlanItemLocalServiceUtil.setSectionContent(plan,
                    PlanSectionLocalServiceUtil.getDefinition(sections.get(0)),
                    PlanItemLocalServiceUtil.getDescription(plan), null, PlanItemLocalServiceUtil.getAuthorId(plan));
        }
        return null;
    }

    private static final long companyId = 10112L;

    private final static String REQUEST_PARAM_NAME = "com.liferay.portal.kernel.servlet.PortletServletRequest";

    public static HttpServletRequest getRequest() {
        FacesContext context = FacesContext.getCurrentInstance();
        return (HttpServletRequest) ((HttpServletRequestWrapper) context.getExternalContext()
                .getRequestMap().get(REQUEST_PARAM_NAME)).getRequest();
    }

    private final long defaultCompanyId = 10112L;

    public String fixWikiPermissions() throws SystemException, PortalException {

        Long companyId = defaultCompanyId;
        Role guest = RoleLocalServiceUtil.getRole(companyId, RoleConstants.GUEST);
        Role userRole = RoleLocalServiceUtil.getRole(companyId, RoleConstants.USER);
        Role siteMemberRole = RoleLocalServiceUtil.getRole(companyId, RoleConstants.SITE_MEMBER);
        String[] guestActions = {ActionKeys.VIEW};

        int idx = 0;
        int total = WikiPageLocalServiceUtil.getWikiPagesCount();

        String[] actionIds = {ActionKeys.VIEW};
        for (WikiPage wp : WikiPageLocalServiceUtil.getWikiPages(0, Integer.MAX_VALUE)) {
            idx++;
            System.out.println(idx + " of " + total);

            Resource resource = null;

            try {
                resource = ResourceLocalServiceUtil.getResource(defaultCompanyId,
                        WikiPage.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(wp.getResourcePrimKey()));
            } catch (NoSuchResourceException nsre) {
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
        for (Contest contest : ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE)) {
            try {
                Group g = GroupLocalServiceUtil.getGroup(contest.getGroupId());
            } catch (Exception e) {
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

        String[] ownerActions = {DiscussionActions.ADMIN.name(), DiscussionActions.ADD_CATEGORY.name(),
                DiscussionActions.ADD_MESSAGE.name(), DiscussionActions.ADD_THREAD.name(),
                DiscussionActions.ADMIN_CATEGORIES.name(), DiscussionActions.ADMIN_MESSAGES.name(),
                DiscussionActions.ADD_COMMENT.name()};

        String[] adminActions = {DiscussionActions.ADD_CATEGORY.name(), DiscussionActions.ADD_MESSAGE.name(),
                DiscussionActions.ADD_THREAD.name(), DiscussionActions.ADMIN_CATEGORIES.name(),
                DiscussionActions.ADMIN_MESSAGES.name(), DiscussionActions.ADD_COMMENT.name()};

        String[] moderatorActions = {DiscussionActions.ADD_CATEGORY.name(), DiscussionActions.ADD_MESSAGE.name(),
                DiscussionActions.ADD_THREAD.name(), DiscussionActions.ADMIN_CATEGORIES.name(),
                DiscussionActions.ADMIN_MESSAGES.name(), DiscussionActions.ADD_COMMENT.name()};

        String[] memberActions = {DiscussionActions.ADD_CATEGORY.name(), DiscussionActions.ADD_MESSAGE.name(),
                DiscussionActions.ADD_THREAD.name(), DiscussionActions.ADD_COMMENT.name()};

        String[] userActions = {DiscussionActions.ADD_MESSAGE.name(), DiscussionActions.ADD_THREAD.name(),
                DiscussionActions.ADD_COMMENT.name()};

        String[] guestActions = {};

        Map<Role, String[]> rolesActionsMap = new HashMap<Role, String[]>();

        rolesActionsMap.put(owner, ownerActions);
        rolesActionsMap.put(admin, adminActions);
        rolesActionsMap.put(member, memberActions);
        rolesActionsMap.put(userRole, userActions);
        rolesActionsMap.put(guest, guestActions);
        rolesActionsMap.put(moderator, moderatorActions);

        for (Contest contest : ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE)) {
            for (Role role : rolesActionsMap.keySet()) {

                PermissionLocalServiceUtil.setRolePermissions(role.getRoleId(), companyId,
                        DiscussionCategoryGroup.class.getName(), ResourceConstants.SCOPE_GROUP,
                        String.valueOf(contest.getGroupId()), rolesActionsMap.get(role));
            }

        }

        return null;

    }

    public String fixResourceReferencesInPermissions() throws SystemException, PortalException {

        for (Permission p : PermissionLocalServiceUtil.getPermissions(0, Integer.MAX_VALUE)) {
            try {
                ResourceLocalServiceUtil.getResource(p.getResourceId());
            } catch (NoSuchResourceException e) {
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
        for (PlanItem plan : PlanItemLocalServiceUtil.getPlans()) {
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

        for (Map.Entry<String, Set<Long>> entry : plansToMap.entrySet()) {

            if (entry.getValue().size() > 1) {
                // we have two or more elements in a group, we should add them
                Long[] planIds = entry.getValue().toArray(new Long[]{});
                for (Long planId : planIds) {
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
        for (Contest contest : ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE)) {
            if (!ContestLocalServiceUtil.isActive(contest)) continue;
            ContestPhase activePhase = ContestLocalServiceUtil.getActivePhase(contest);

            _log.info("   checking phase: " + activePhase.getContestPhasePK());

            for (PlanItem plan : ContestPhaseLocalServiceUtil.getPlans(activePhase)) {
                if (PlanItemGroupLocalServiceUtil.getPlansInGroup(plan.getPlanId()).size() != 2) {
                    _log.error("Plan should belong to a group with 2 elements " + plan.getPlanId());
                    orphanFound = true;
                }
            }
        }
        FacesMessage fm = new FacesMessage();
        if (!orphanFound) {
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            fm.setSummary("No orphans found");
        } else {
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
        List<DiscussionCategoryGroup> dcgs = DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroups(0, 10000);
        Collections.sort(dcgs, new Comparator<DiscussionCategoryGroup>() {

            public int compare(DiscussionCategoryGroup o1, DiscussionCategoryGroup o2) {
                if (o1.getCommentsThread() <= 0) {
                    if (o2.getCommentsThread() <= 0) return (int) (o1.getId() - o2.getId());
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

                } catch (Exception e) {
                    // ignore
                }
                return 0;
            }
        });

        Collections.reverse(dcgs);
        for (DiscussionCategoryGroup dcg : dcgs) {
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
            } else {
                System.out.println("+++\tActivity for discussion found " + dcg.getId());
            }
        }
    }

    public void synchronizeSections() throws SystemException, PortalException {
        Integer[] ids = new Integer[]{40223, 40205, 40207, 1314911, 1314988, 1315032, 1315112, 1315124, 1315412, 1315611, 1315633, 1315655, 1315712, 1316011, 1316044, 1316148, 1316236, 1316312, 1316348, 1316812, 1316836, 1316848, 1316924, 1317420, 1317431, 1322211, 1322222, 1322233, 1322305, 1322317, 1322329, 1322341, 1322353, 1322364, 1322375, 1322386, 1322397, 1322408, 1322419, 1322430, 1322442, 1322454, 1322465, 1322476, 1322487, 1322498, 1322509, 1322520, 1322531, 1322542, 1322553, 1322564, 1322575, 1322586, 1322597, 1322608, 1322619, 1322630, 1322641, 1322652, 1322663, 1323112, 1323124, 1323136, 1323148, 1323160, 1323172};

        for (Integer id : ids) {
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
        Integer[] contestIds = new Integer[]{23, 16, 15, 30, 24, 17, 26, 14, 25, 19, 11, 7, 18, 22, 13, 21, 10, 20};

        boolean toSmallGroupFound = false;
        for (Contest contest : ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE)) {
            if (!ContestLocalServiceUtil.isActive(contest)) continue;
            ContestPhase activePhase = ContestLocalServiceUtil.getActivePhase(contest);

            _log.info("********   checking phase: " + activePhase.getContestPhasePK() + "\tplans to check: " + ContestPhaseLocalServiceUtil.getPlans(activePhase).size() + "\texpectedGroupSize: " + (ContestPhaseLocalServiceUtil.getPreviousPhases(activePhase).size() + 1));
            //ContestPhaseLocalServiceUtil.getPreviousPhases(activePhase)

            for (PlanItem plan : ContestPhaseLocalServiceUtil.getPlans(activePhase)) {
                System.out.println(plan.getPlanId());
                if (PlanItemGroupLocalServiceUtil.getPlansInGroup(plan.getPlanId()).size() != ContestPhaseLocalServiceUtil.getPreviousPhases(activePhase).size() + 1) {
                    _log.error("\tPlan should belong to a group with " + (ContestPhaseLocalServiceUtil.getPreviousPhases(activePhase).size() + 1) + " elements " + plan.getPlanId() + "\tsize: " + PlanItemGroupLocalServiceUtil.getPlansInGroup(plan.getPlanId()).size());
                    toSmallGroupFound = true;
                }
            }
        }
        FacesMessage fm = new FacesMessage();
        if (!toSmallGroupFound) {
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            fm.setSummary("No to small groups found");
        } else {
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            fm.setSummary("Orphans found, check logs");
        }
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

    public void synchronizeComments() throws SystemException, PortalException {
        Set<Long> groupsProcessed = new HashSet<Long>();

        for (PlanItemGroup group : PlanItemGroupLocalServiceUtil.getPlanItemGroups(0, Integer.MAX_VALUE)) {
            if (groupsProcessed.contains(group.getGroupId())) continue;
            groupsProcessed.add(group.getGroupId());

            List<PlanItem> plans = new ArrayList<PlanItem>();
            List<DiscussionCategoryGroup> discussions = new ArrayList<DiscussionCategoryGroup>();
            for (Long planId : PlanItemGroupLocalServiceUtil.getPlansInGroup(group.getPlanId())) {
                PlanItem plan = PlanItemLocalServiceUtil.getPlan(planId);
                plans.add(plan);
                discussions.add(PlanItemLocalServiceUtil.getDiscussionCategoryGroup(plan));
            }
            System.out.println("Working on group: " + PlanItemGroupLocalServiceUtil.getPlansInGroup(group.getPlanId()));


            Map<String, DiscussionMessage> allMessagesMap = new HashMap<String, DiscussionMessage>();


            for (DiscussionCategoryGroup discussion : discussions) {
                DiscussionMessage commentsThread = DiscussionCategoryGroupLocalServiceUtil.getCommentThread(discussion);
                if (commentsThread == null) continue;
                allMessagesMap.put(commentsThread.getBody(), commentsThread);
                for (DiscussionMessage msg : DiscussionMessageLocalServiceUtil.getThreadMessages(commentsThread)) {
                    allMessagesMap.put(msg.getBody(), msg);
                }
            }

            // sort all comments by publication date
            List<DiscussionMessage> messages = new ArrayList<DiscussionMessage>(allMessagesMap.values());

            Collections.sort(messages, new Comparator<DiscussionMessage>() {

                public int compare(DiscussionMessage o1, DiscussionMessage o2) {
                    return o1.getCreateDate().compareTo(o2.getCreateDate());
                }
            });

            if (messages.isEmpty()) {
                // nothing to synchronize
                continue;
            }
            for (DiscussionCategoryGroup discussion : discussions) {
                for (DiscussionMessage message : messages) {
                    DiscussionMessage commentsThread = DiscussionCategoryGroupLocalServiceUtil.getCommentThread(discussion);
                    boolean found = false;
                    if (commentsThread != null) {
                        // check if current discussion doesn't contain message already
                        for (DiscussionMessage msg : DiscussionMessageLocalServiceUtil.getThreadMessages(commentsThread)) {
                            if (message.getBody().equals(msg.getBody())) found = true;
                        }

                    }
                    if (found || (commentsThread != null && commentsThread.getBody().equals(message.getBody()))) {
                        // there is such message in the thread, do nothing
                        continue;
                    }

                    // message doesn't exist in the thread create it
                    DiscussionMessage newMessage = (DiscussionMessage) message.clone();

                    newMessage.setPk(CounterLocalServiceUtil.increment(DiscussionMessage.class.getName()));
                    newMessage.setMessageId(CounterLocalServiceUtil.increment(DiscussionMessage.class.getName() + ".discussion"));

                    newMessage.setCategoryGroupId(discussion.getId());
                    newMessage.setNew(true);

                    if (commentsThread == null) {
                        // new message opens a thread
                        newMessage.setThreadId(0);
                        discussion.setCommentsThread(newMessage.getMessageId());
                        DiscussionCategoryGroupLocalServiceUtil.updateDiscussionCategoryGroup(discussion);
                    } else {
                        // we had a thread, decide with to do with new message, it should either go into the thread, or become
                        // the first message in the thread
                        if (newMessage.getCreateDate().before(commentsThread.getCreateDate())) {
                            // newly created message should be the first message in the thread
                            // all messages should be updated to reflect new thread parent
                            newMessage.setThreadId(0);
                            discussion.setCommentsThread(newMessage.getMessageId());
                            DiscussionCategoryGroupLocalServiceUtil.updateDiscussionCategoryGroup(discussion);
                            commentsThread.setThreadId(newMessage.getMessageId());
                            DiscussionMessageLocalServiceUtil.updateDiscussionMessage(commentsThread);

                            for (DiscussionMessage threadMessage : DiscussionMessageLocalServiceUtil.getThreadMessages(commentsThread)) {
                                threadMessage.setThreadId(newMessage.getMessageId());
                                DiscussionMessageLocalServiceUtil.updateDiscussionMessage(threadMessage);
                            }

                        } else {
                            // message will go inside the thread, no need to update other messages
                            newMessage.setThreadId(commentsThread.getMessageId());
                        }
                    }
                    System.out.println("copied message: " + newMessage);
                    DiscussionMessageLocalServiceUtil.addDiscussionMessage(newMessage);


                }
            }
            
            /*
            
            for (DiscussionCategoryGroup discussion: discussions) {
                DiscussionMessage commentsThread = DiscussionCategoryGroupLocalServiceUtil.getCommentThread(discussion);
                for (DiscussionCategoryGroup secondDiscussion: discussions) {
                    System.out.println(">>>> From discussion: " + discussion);
                    System.out.println(">>>> To discussion: " + secondDiscussion);
                    if (secondDiscussion.getId() == discussion.getId()) continue;

                    if (commentsThread == null) {
                        System.out.println("comments thread jest null....");
                        continue;
                    }
                    for (DiscussionMessage msg: DiscussionMessageLocalServiceUtil.getThreadMessages(commentsThread)) {
                        // check if such message is in child comments thread

                        DiscussionMessage secondCommentsThread = 
                                DiscussionCategoryGroupLocalServiceUtil.getCommentThread(secondDiscussion);
                        
                        boolean found = false;
                        if (secondCommentsThread != null) {
                            for (DiscussionMessage msg2: DiscussionMessageLocalServiceUtil.getThreadMessages(secondCommentsThread)) {
                                if (msg2.getBody().trim().equals(msg.getBody().trim())) {
                                    found = true;
                                }
                            }
                        }
                        else {
                            System.out.println("second comments is null");
                        }
                        if (found) continue;
                        
                        System.out.println("Should copy message: " + msg);
                        
                        DiscussionMessage newMessage = (DiscussionMessage) msg.clone();
                        
                        newMessage.setPk(CounterLocalServiceUtil.increment(DiscussionMessage.class.getName()));
                        newMessage.setMessageId(CounterLocalServiceUtil.increment(DiscussionMessage.class.getName() + ".discussion"));
                        
                        newMessage.setCategoryGroupId(secondDiscussion.getId());
                        newMessage.setNew(true);

                        if (secondCommentsThread == null) {
                            System.out.println(" ** creating new thread");
                            // if there was no comments thread available, add one
                            newMessage.setThreadId(0);
                            secondDiscussion.setCommentsThread(newMessage.getMessageId());  
                            DiscussionCategoryGroupLocalServiceUtil.updateDiscussionCategoryGroup(secondDiscussion);
                        }
                        else {
                            // we had a thread, add new message to it
                            
                            newMessage.setThreadId(secondCommentsThread.getMessageId());
                        }
                        System.out.println("copied message: " + newMessage);
                        DiscussionMessageLocalServiceUtil.addDiscussionMessage(newMessage);
                        
                        // clone msg and add it to secondCommentsThread
                        //DiscussionMessage clonedMsg = msg.clone();
                    }
                    
                }
            }
            */
        }

    }


}

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

    public String copyAllProposals() throws SystemException, PortalException {
        //key=contestID, value=All proposals to be copied
        Map<Long, Long[]> map = new HashMap<Long, Long[]>();
        map.put(30L, new Long[]{1301810L,
                1301809L,
                1301808L,
                1301807L,
                1301806L,
                1301805L,
                1301803L,
                1301802L,
                1301801L,
                1301815L,
                1301814L,
                1301813L,
                1301811L});
        map.put(14L, new Long[]{1301942L,
                1301957L,
                1301955L,
                1301953L,
                1301952L,
                1301951L,
                1301949L,
                1301948L,
                1301946L,
                1301944L,
                1301943L});
        map.put(25L, new Long[]{1302132L,
                1302153L,
                1302152L,
                1302150L,
                1302148L,
                1302145L,
                1302143L,
                1302142L,
                1302141L,
                1302139L,
                1302138L,
                1302137L,
                1302136L,
                1302135L,
                1302133L,
                1302144L,
                1302140L,
                1302134L});
        map.put(11L, new Long[]{
                1301888L,
                1301887L,
                1301900L,
                1301899L,
                1301898L,
                1301897L,
                1301896L,
                1301895L,
                1301894L,
                1301893L,
                1301892L,
                1301891L,
                1301890L,
                1301889L,
                1301902L
        });
        map.put(23L, new Long[]{
                1302118L,
                1302125L,
                1302124L,
                1302123L,
                1302120L,
        });
        map.put(24L, new Long[]{
                1302126L,
                1302131L,
                1302130L,
                1302127L,
        });
        map.put(26L, new Long[]{
                1302173L,
                1302155L,
                1302174L,
                1302172L,
                1302171L,
                1302169L,
                1302168L,
                1302167L,
                1302166L,
                1302165L,
                1302164L,
                1302163L,
                1302162L,
                1302160L,
                1302158L,
                1302156L,
                1302159L,
        });
        map.put(19L, new Long[]{
                1302028L,
                1302025L,
                1302024L,
                1302022L,
                1302020L,
                1302016L,
                1302014L,
                1302013L,
                1302012L
        });
        map.put(7L, new Long[]{
                1301838L,
                1301837L,
                1301836L,
                1301835L,
                1301831L,
                1301830L,
                1301828L,
                1301827L,
                1301833L,
                1301825L,
                1301824L,
                1301823L,
                1301822L,
                1301821L,
                1301820L,
                1301819L,
                1301818L,
                1301817L,
                1301816L,
                1301856L,
                1301854L,
                1301853L,
                1301852L,
                1301851L,
                1301849L,
                1301848L,
                1301847L,
                1301846L,
                1301845L,
                1301844L,
                1301842L,
                1301841L,
                1301840L,
                1301839L,
                1301832L,
                1301855L,
        });
        map.put(18L, new Long[]{1301988L,
                1301987L,
                1301986L,
                1301985L,
                1302010L,
                1302009L,
                1302008L,
                1302007L,
                1302006L,
                1302004L,
                1302003L,
                1302000L,
                1301999L,
                1301998L,
                1301997L,
                1301996L,
                1301995L,
                1301994L,
                1301993L,
                1301992L,
                1301991L,
                1301990L,
                1301989L,});
        map.put(22L, new Long[]{
                1302107L,
                1302105L,
                1302103L,
                1302116L,
                1302115L,
                1302113L,
                1302110L,
                1302109L,
                1302108L,
                1302104L,
        });
        map.put(13L, new Long[]{1301926L,
                1301925L,
                1301924L,
                1301923L,
                1301922L,
                1301921L,
                1302015L,
                1301920L,
                1301919L,
                1301918L,
                1301917L,
                1301915L,
                1301904L,
                1301916L,
                1301914L,
                1301913L,
                1301912L,
                1301911L,
                1301910L,
                1301909L,
                1301908L,
                1301907L,
                1301906L,
                1301905L,
                1301941L,
                1301940L,
                1301939L,
                1301938L,
                1301937L,
                1301936L,
                1301935L,
                1301933L,
                1301932L,
                1301931L,
                1301930L,
                1301929L,
                1301928L,});
        map.put(21L, new Long[]{1302076L,
                1302075L,
                1302074L,
                1302073L,
                1302072L,
                1302070L,
                1302069L,
                1302068L,
                1302067L,
                1302066L,
                1302065L,
                1302064L,
                1302102L,
                1302101L,
                1302099L,
                1302098L,
                1302097L,
                1302096L,
                1302095L,
                1302093L,
                1302092L,
                1302091L,
                1302090L,
                1302089L,
                1302087L,
                1302086L,
                1302085L,
                1302084L,
                1302083L,
                1302082L,
                1302081L,
                1302080L,
                1302079L,
                1302077L,});
        map.put(10L, new Long[]{
                1301884L,
                1301863L,
                1301862L,
                1301861L,
                1301860L,
                1301859L,
                1301858L,
                1301857L,
                1301885L,
                1301883L,
                1301880L,
                1301878L,
                1301877L,
                1301876L,
                1301875L,
                1301874L,
                1301870L,
                1301869L,
                1301868L,
                1301867L,
                1301866L,
                1301865L,
                1301864L,
                1301873L,
                1301872L,
                1301871L,
        });
        map.put(20L, new Long[]{
                1302046L,
                1302045L,
                1302044L,
                1302043L,
                1302042L,
                1302041L,
                1302040L,
                1302039L,
                1302038L,
                1302036L,
                1302035L,
                1302034L,
                1302033L,
                1302032L,
                1302031L,
                1302030L,
                1302029L,
                1302063L,
                1302061L,
                1302060L,
                1302059L,
                1302058L,
                1302057L,
                1302055L,
                1302054L,
                1302053L,
                1302050L,
                1302049L,
                1302048L,
                1302047L,
        });
        map.put(16L, new Long[]{
                1301977L,
                1301972L,
                1301973L,
                1301974L,
                1301975L,
                1301976L,
                1301978L,
                1301979L,
                1301970L,
                1301971L,
        });
        map.put(15L, new Long[]{
                1301961L,
                1301960L,
                1301959L,
                1301958L,
                1301969L,
                1301968L,
                1301967L,
                1301966L,
                1301965L,
                1301964L,
                1301963L,
                1301962L,
        });
        map.put(17L, new Long[]{
                1301980L,
                1301984L,
                1301983L,
                1301982L,
                1301981L,
        });


        copyProposals(map);

        return null;
    }

    public String copySemiFinalists() throws PortalException, SystemException {
        Map<Long, Long[]> map = new HashMap<Long, Long[]>();
        map.put(14L, new Long[]{1302202L,
                1302201L});
        map.put(25L, new Long[]{1303509L});
        map.put(23L, new Long[]{1302905L,
                1302901L});
        map.put(26L, new Long[]{1303303L,
                1303304L});
        map.put(7L, new Long[]{1303005L});
        map.put(18L, new Long[]{1302601L,
                1302608L,
                1302602L,
                1302603L});
        map.put(13L, new Long[]{1302804L,
                1302803L,
                1302801L,});
        map.put(10L, new Long[]{1303706L,
                1303702L,
                1303701L});
        map.put(16L, new Long[]{1303402L,
                1303401L});
        map.put(15L, new Long[]{1302302L,
                1302301L});


        copyProposals(map);

        return null;
    }

    /**
     *
     * @param map key=contestId, values=proposals of that contest to be copied
     * @throws SystemException
     * @throws PortalException
     */
    public void copyProposals(Map<Long, Long[]> map) throws SystemException, PortalException {
        for (Map.Entry<Long, Long[]> e : map.entrySet()) {
            // get target contestphase
            Contest c = ContestLocalServiceUtil.getContest(e.getKey());
            ContestPhase target = null;
            for(ContestPhase ph : ContestLocalServiceUtil.getPhases(c)) {
                if(ph.getPhaseEndDate() == null) {
                    target = ph;
                    break;
                }
            }

            //find planItems for plans to be copied
            List<PlanItem> toBeCopied = new LinkedList<PlanItem>();
            for (Long srcProposal : e.getValue()) {
                toBeCopied.add(PlanItemLocalServiceUtil.getPlan(srcProposal));
            }

            //copy
            PlanItemLocalServiceUtil.promotePlans(toBeCopied, target.getContestPhasePK());
        }
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

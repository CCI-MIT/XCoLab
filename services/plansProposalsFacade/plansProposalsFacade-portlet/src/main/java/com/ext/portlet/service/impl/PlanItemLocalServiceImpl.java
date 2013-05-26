package com.ext.portlet.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.ext.portlet.NoSuchPlanFanException;
import com.ext.portlet.NoSuchPlanItemException;
import com.ext.portlet.NoSuchPlanPositionsException;
import com.ext.portlet.NoSuchPlanTeamHistoryException;
import com.ext.portlet.NoSuchPlanVoteException;
import com.ext.portlet.discussions.DiscussionActions;
import com.ext.portlet.model.ActivitySubscription;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.DiscussionCategory;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.model.PlanAttribute;
import com.ext.portlet.model.PlanDescription;
import com.ext.portlet.model.PlanFan;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.model.PlanMeta;
import com.ext.portlet.model.PlanModelRun;
import com.ext.portlet.model.PlanPositions;
import com.ext.portlet.model.PlanSection;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.PlanTeamHistory;
import com.ext.portlet.model.PlanTemplate;
import com.ext.portlet.model.PlanType;
import com.ext.portlet.model.PlanVote;
import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.plans.EntityState;
import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.PlanConstants.Attribute;
import com.ext.portlet.plans.PlanTeamActions;
import com.ext.portlet.plans.PlanUserPermission;
import com.ext.portlet.plans.UpdateType;
import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.OntologyTermLocalServiceUtil;
import com.ext.portlet.service.PlanAttributeLocalServiceUtil;
import com.ext.portlet.service.PlanDescriptionLocalServiceUtil;
import com.ext.portlet.service.PlanFanLocalServiceUtil;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.ext.portlet.service.PlanMetaLocalServiceUtil;
import com.ext.portlet.service.PlanModelRunLocalServiceUtil;
import com.ext.portlet.service.PlanPositionsLocalServiceUtil;
import com.ext.portlet.service.PlanSectionLocalServiceUtil;
import com.ext.portlet.service.PlanTeamHistoryLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateLocalServiceUtil;
import com.ext.portlet.service.PlanTypeLocalServiceUtil;
import com.ext.portlet.service.PlanVoteLocalServiceUtil;
import com.ext.portlet.service.base.PlanItemLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.model.ClassName;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Image;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.MembershipRequestConstants;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.GroupServiceUtil;
import com.liferay.portal.service.ImageLocalServiceUtil;
import com.liferay.portal.service.MembershipRequestLocalServiceUtil;
import com.liferay.portal.service.PermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import edu.mit.cci.roma.client.Simulation;

/**
 * The implementation of the plan item local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.PlanItemLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.PlanItemLocalServiceBaseImpl
 * @see com.ext.portlet.service.PlanItemLocalServiceUtil
 */
public class PlanItemLocalServiceImpl extends PlanItemLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.PlanItemLocalServiceUtil} to access the plan item local service.
     */

    private static Log _log = LogFactoryUtil.getLog(PlanItemLocalServiceImpl.class);

    /**
     * Suffix that should be added to PlanItem class name to get name for plan
     * id counter
     */
    private final String PLAN_ID_NAME_SUFFIX = ".Plan";

    /**
     * Default community permissions for community forum category.
     */
    public static final String[] DEFAULT_CATEGORY_COMMUNITY_PERMISSIONS = { ActionKeys.VIEW, ActionKeys.SUBSCRIBE,
            ActionKeys.REPLY_TO_MESSAGE, ActionKeys.ADD_MESSAGE };

    /**
     * Default guest permissions for community forum category.
     */
    public static final String[] DEFAULT_CATEGORY_GUEST_PERMISSIONS = { ActionKeys.VIEW, ActionKeys.SUBSCRIBE };

    /**
     * Default description of group working on a plan.
     */
    public static final String DEFAULT_GROUP_DESCRIPTION = "Group working on plan %s";

    public static final String DEFAULT_UNTITLED_PLAN_STEM_NAME = "Untitled Proposal ";

    /**
     * Default forum category name.
     */
    public static final String DEFAULT_FORUM_CATEGORY_NAME = "General discussion";

    /**
     * Default forum category description.
     */

    public PlanItem createPlan(ContestPhase phase, Long authorId) throws SystemException, PortalException {
        planItemFinder.clearPhaseCache(phase.getContestPhasePK());
        
        long planItemId = CounterLocalServiceUtil.increment(PlanItem.class.getName());
        long planId = CounterLocalServiceUtil.increment(PlanItem.class.getName() + PLAN_ID_NAME_SUFFIX);
        long planTypeId = ContestPhaseLocalServiceUtil.getContest(phase).getPlanTypeId();
        String name = DEFAULT_UNTITLED_PLAN_STEM_NAME + planId;
        PlanItem planItem = PlanItemLocalServiceUtil.createPlanItem(planItemId);
        planItem.setPlanId(planId);
        planItem.setVersion(0L);
        planItem.setUpdated(new Date());
        planItem.setUpdateAuthorId(authorId);
        planItem.setState(EntityState.ACTIVE.name());
        planItem.setUpdateType(UpdateType.CREATED.name());

        // create related entities, plan description, meta, model run
        PlanDescriptionLocalServiceUtil.createPlanDescription(planItem, name);
        PlanModelRunLocalServiceUtil.createPlanModelRun(planItem);

        PlanMeta meta = PlanMetaLocalServiceUtil.createPlanMeta(planItem, planTypeId);
        PlanType type = PlanTypeLocalServiceUtil.getPlanType(planTypeId);
        Simulation defaultmodel = PlanTypeLocalServiceUtil.getDefaultModel(type);
        if (defaultmodel != null) {
            meta.setModelId(defaultmodel.getId());
        } else {
            List<Simulation> models = PlanTypeLocalServiceUtil.getAvailableModels(type);
            if (models.size() > 0) {
                meta.setModelId(models.get(0).getId());
            }
        }
        meta.setContestPhase(phase.getContestPhasePK());
        PlanMetaLocalServiceUtil.updatePlanMeta(meta);
        PlanPositionsLocalServiceUtil.createPlanPositions(planItem);

        // create community, Message Boards category
        initPlan(planItem);

        /* update/create all attributes */
        // planItem.updateAllAttributes();

        planItem = PlanItemLocalServiceUtil.addPlanItem(planItem);
        updateAttribute(planItem, Attribute.CREATOR.name());
        updateAttribute(planItem, Attribute.NAME.name());
        updateAttribute(planItem, Attribute.DESCRIPTION.name());
        updateAttribute(planItem, Attribute.CREATE_DATE.name());
        updateAttribute(planItem, Attribute.PUBLISH_DATE.name());
        updateAttribute(planItem, Attribute.VOTES.name());
        updateAttribute(planItem, Attribute.SUPPORTERS.name());
        updateAttribute(planItem, Attribute.IS_PLAN_OPEN.name());
        updateAttribute(planItem, Attribute.SEEKING_ASSISTANCE.name());
        updateAttribute(planItem, Attribute.STATUS.name());

        // populate fields with default values
        return planItem;
    }

    /**
     * Creates and initializes new instance of a PlanItem.
     * 
     * All necessary id's are generated, version is set to 0.
     * 
     * @param authorId
     *            Id of user that is creating new plan
     * @deprecated Should use contest enabled plans
     */
    private PlanItem createPlan(Long planTypeId, Long authorId) throws SystemException, PortalException {
        long planItemId = CounterLocalServiceUtil.increment(PlanItem.class.getName());
        long planId = CounterLocalServiceUtil.increment(PlanItem.class.getName() + PLAN_ID_NAME_SUFFIX);

        String name = DEFAULT_UNTITLED_PLAN_STEM_NAME + planId;
        PlanItem planItem = PlanItemLocalServiceUtil.createPlanItem(planItemId);
        planItem.setPlanId(planId);
        planItem.setVersion(0L);
        planItem.setUpdated(new Date());
        planItem.setUpdateAuthorId(authorId);
        planItem.setState(EntityState.ACTIVE.name());
        planItem.setUpdateType(UpdateType.CREATED.name());


        // create related entities, plan description, meta, model run
        PlanDescriptionLocalServiceUtil.createPlanDescription(planItem, name);
        PlanModelRunLocalServiceUtil.createPlanModelRun(planItem);
        PlanMetaLocalServiceUtil.createPlanMeta(planItem, planTypeId);
        PlanPositionsLocalServiceUtil.createPlanPositions(planItem);
        planItem = PlanItemLocalServiceUtil.addPlanItem(planItem);

        // create community, Message Boards category

        /* update/create all attributes */
        // planItem.updateAllAttributes();
        updateAttribute(planItem, Attribute.CREATOR.name());
        updateAttribute(planItem, Attribute.NAME.name());
        updateAttribute(planItem, Attribute.DESCRIPTION.name());
        updateAttribute(planItem, Attribute.CREATE_DATE.name());
        updateAttribute(planItem, Attribute.PUBLISH_DATE.name());
        updateAttribute(planItem, Attribute.VOTES.name());

        updateAttribute(planItem, Attribute.SUPPORTERS.name());
        updateAttribute(planItem, Attribute.IS_PLAN_OPEN.name());
        updateAttribute(planItem, Attribute.SEEKING_ASSISTANCE.name());
        updateAttribute(planItem, Attribute.STATUS.name());

        // populate fields with default values

        return planItem;
    }

    public PlanItem createPlan(PlanItem basePlan, Long authorId) throws SystemException, PortalException {
        throw new RuntimeException("Plan must be part of a contest");
        

        // long type = basePlan.getPlanTypeId();
        // if (basePlan.getPlanType().getPublished()) {
        // type = basePlan.getPlanType().getPublishedCounterpartId();
        // }
        // PlanItem plan = createPlan(type, authorId);
        // PlanDescription description =
        // PlanDescriptionLocalServiceUtil.getCurrentForPlan(plan);
        // PlanModelRun planModelRun =
        // PlanModelRunLocalServiceUtil.getCurrentForPlan(plan);
        // PlanPositions planPositions =
        // PlanPositionsLocalServiceUtil.getCurrentForPlan(plan);
        //
        // // copy description
        // description.setDescription(basePlan.getDescription());
        // description.store();
        //
        // // copy scenario id
        // planModelRun.setScenarioId(basePlan.getScenarioId());
        // planModelRun.store();
        //
        // // copy positions
        // planPositions.setPositionsIds(basePlan.getPositionsIds());
        // planPositions.store();
        //
        // if (basePlan.getScenarioId() != null) {
        // // update all attributes
        // plan.updateAllAttributes();
        // }
        // else {
        // // update only attributes related to new values
        // plan.updateAttribute(Attribute.DESCRIPTION.name());
        // plan.updateAttribute(Attribute.POSITIONS.name());
        // }
        //
        // return plan;
    }

    public PlanItem createPlan(PlanItem basePlan, ContestPhase contestPhase, Long authorId) throws SystemException,
            PortalException {
        planItemFinder.clearPhaseCache(contestPhase.getContestPhasePK());
        long type = getPlanTypeId(basePlan);
        if (ContestLocalServiceUtil.getPlanType(ContestPhaseLocalServiceUtil.getContest(contestPhase)).getPlanTypeId() != type) {
            _log.error("Cannot create plan of type " + type + " for contest phase "
                    + ContestPhaseLocalServiceUtil.getName(contestPhase));
        }

        //PlanItem plan = createPlan(ContestLocalServiceUtil.getPlanType(
        //        ContestPhaseLocalServiceUtil.getContest(contestPhase)).getPlanTypeId(), authorId);
        
        long planItemId = CounterLocalServiceUtil.increment(PlanItem.class.getName());
        long planId = CounterLocalServiceUtil.increment(PlanItem.class.getName() + PLAN_ID_NAME_SUFFIX);

        String name = DEFAULT_UNTITLED_PLAN_STEM_NAME + planId;
        PlanItem planItem = PlanItemLocalServiceUtil.createPlanItem(planItemId);
        planItem.setPlanId(planId);
        planItem.setVersion(0L);
        planItem.setUpdated(new Date());
        planItem.setUpdateAuthorId(authorId);
        planItem.setState(EntityState.ACTIVE.name());
        planItem.setUpdateType(UpdateType.CREATED.name());


        // create related entities, plan description, meta, model run
        PlanDescription description = PlanDescriptionLocalServiceUtil.createPlanDescription(planItem, name);
        description.setDescription(getDescription(basePlan));
        description.setName(getName(basePlan));
        description.setPitch(getPitch(basePlan));
        description.setImage(getImageId(basePlan));
        PlanDescriptionLocalServiceUtil.store(description);
        

        PlanModelRunLocalServiceUtil.createPlanModelRun(planItem);
        PlanMeta planMeta = PlanMetaLocalServiceUtil.createPlanMeta(planItem, ContestPhaseLocalServiceUtil.getContest(contestPhase).getPlanTypeId());
        planMeta.setContestPhase(contestPhase.getContestPhasePK());
        planMeta.setModelId(getPlanMeta(basePlan).getModelId());
        
        PlanMetaLocalServiceUtil.updatePlanMeta(planMeta);
        
        PlanPositionsLocalServiceUtil.createPlanPositions(planItem);

        // create community, Message Boards category

        /* update/create all attributes */
        // planItem.updateAllAttributes();
        updateAttribute(planItem, Attribute.CREATOR.name());
        updateAttribute(planItem, Attribute.NAME.name());
        updateAttribute(planItem, Attribute.DESCRIPTION.name());
        updateAttribute(planItem, Attribute.CREATE_DATE.name());
        updateAttribute(planItem, Attribute.PUBLISH_DATE.name());
        updateAttribute(planItem, Attribute.VOTES.name());
        String team = getTeam(basePlan);
        if (team != null) {
            setAttribute(planItem, Attribute.TEAM, getTeam(basePlan));
        }

        updateAttribute(planItem, Attribute.SUPPORTERS.name());
        updateAttribute(planItem, Attribute.IS_PLAN_OPEN.name());
        updateAttribute(planItem, Attribute.SEEKING_ASSISTANCE.name());
        updateAttribute(planItem, Attribute.STATUS.name());

        planItem.setUpdated(basePlan.getUpdated());
        planItem = PlanItemLocalServiceUtil.addPlanItem(planItem);

        
        //store(plan);

        
        //PlanDescription description = PlanDescriptionLocalServiceUtil.getCurrentForPlan(planItem);
        PlanModelRun planModelRun = PlanModelRunLocalServiceUtil.getCurrentForPlan(planItem);
        PlanPositions planPositions = PlanPositionsLocalServiceUtil.getCurrentForPlan(planItem);

        initPlan(planItem);        
        List<PlanSection> sections = getPlanSections(basePlan);
        if (sections != null && ! sections.isEmpty()) {
            for (PlanSection section: sections) {
                PlanSectionDefinition def = PlanSectionLocalServiceUtil.getDefinition(section);
                setSectionContent(planItem, def, section.getContent(), new ArrayList<Long>(), authorId);
            }
        }

        // copy description

        // copy scenario id
        // check if there is a scenario with given ID
        Long scenarioId = getScenarioId(basePlan);
        try {
            if (scenarioId != null && scenarioId > 0
                    && CollaboratoriumModelingService.repository().getScenario(scenarioId) != null) {
                planModelRun.setScenarioId(scenarioId);

            }
        } catch (Throwable e) {
            // ignore, no such scenario
            _log.error("Can't find scenario with id: " + getScenarioId(basePlan), e);
        }
        PlanModelRunLocalServiceUtil.store(planModelRun);

        // copy positions
        PlanPositionsLocalServiceUtil.setPositionsIds(planPositions, getPositionsIds(basePlan));
        PlanPositionsLocalServiceUtil.store(planPositions);

        if (scenarioId != null && scenarioId > 0) {
            // update all attributes
            updateAllAttributes(planItem);
        }
        // update only attributes related to new values
 
        updateAttribute(planItem, Attribute.DESCRIPTION.name());
        updateAttribute(planItem, Attribute.NAME.name());
        updateAttribute(planItem, Attribute.POSITIONS.name());
        updateAttribute(planItem, Attribute.LAST_MOD_DATE.name());
        updateAttribute(planItem, Attribute.IS_PLAN_OPEN.name());
        updateAttribute(planItem, Attribute.SEEKING_ASSISTANCE.name());
        updateAttribute(planItem, Attribute.STATUS.name());

        if (getOpen(basePlan)) {
            getPlanMeta(planItem).setOpen(true);
            PlanMetaLocalServiceUtil.store(getPlanMeta(planItem));
        }

        // set abstract and pitch
        Attribute[] attributesToCopy = { Attribute.ABSTRACT, Attribute.LAST_MOD_DATE, Attribute.SCRAPBOOK,
                Attribute.SUBREGION, Attribute.REGION, Attribute.SEEKING_ASSISTANCE, Attribute.IS_PLAN_OPEN };

        for (Attribute attr : attributesToCopy) {
            PlanAttribute pa = getPlanAttribute(basePlan, attr.name());
            if (pa != null) {
                setAttribute(planItem, pa.getAttributeName(), pa.getAttributeValue());
            }
        }
        return planItem;
    }

    private void initPlan(PlanItem plan) throws PortalException, SystemException {
        User user = UserLocalServiceUtil.getUser(plan.getUpdateAuthorId());
        
        PrincipalThreadLocal.setName(user.getUserId());
        PermissionChecker permissionChecker;
        try {
            permissionChecker = PermissionCheckerFactoryUtil.create(user, true);
        }
        catch (Exception e) {
            _log.error("Can't instantiate permissionChecker", e);
            throw new RuntimeException(e);
        }
        PermissionThreadLocal.setPermissionChecker(permissionChecker);
        

        ServiceContext groupServiceContext = new ServiceContext();
        groupServiceContext.setUserId(plan.getUpdateAuthorId());

        // in order to prevent group name conflicts,
        // add timestamp and random long value to plan name when setting group
        // name
        Random rand = new Random();
        String groupName = getName(plan) + "_" + System.currentTimeMillis() + "_" + rand.nextLong();
        Group group = null;
        try {
            group = GroupServiceUtil.addGroup(StringUtils.substring(groupName, 0, 80), String.format(DEFAULT_GROUP_DESCRIPTION, 
                    StringUtils.substring(groupName, 0, 80)),
                    GroupConstants.TYPE_SITE_RESTRICTED, null, true, true, groupServiceContext);
        } catch (Exception e) {
            System.err.println("Got error " + e.getMessage());
            e.printStackTrace();
            return;
        }
        Long parentCategoryId = 0L;
        DiscussionCategoryGroup categoryGroup = DiscussionCategoryGroupLocalServiceUtil
                .createDiscussionCategoryGroup(getName(plan) + " discussion");

        categoryGroup.setUrl("/web/guest/plans/-/plans/contestId/" + getContest(plan).getContestPK() + "/planId/"
                + plan.getPlanId() + "#plans=tab:comments");
        
        DiscussionCategoryGroupLocalServiceUtil.store(categoryGroup);

        DiscussionCategory category = DiscussionCategoryGroupLocalServiceUtil.addCategory(categoryGroup, "General discussion", null,
                UserLocalServiceUtil.getUser(getAuthorId(plan)));

        // set up permissions

        Long companyId = group.getCompanyId();
        Role owner = RoleLocalServiceUtil.getRole(companyId, RoleConstants.SITE_OWNER);
        Role admin = RoleLocalServiceUtil.getRole(companyId, RoleConstants.SITE_ADMINISTRATOR);
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

        for (Role role : rolesActionsMap.keySet()) {
            PermissionLocalServiceUtil.setRolePermissions(role.getRoleId(), companyId,
                    DiscussionCategoryGroup.class.getName(), ResourceConstants.SCOPE_GROUP,
                    String.valueOf(group.getGroupId()), rolesActionsMap.get(role));
        }

        // populate plan with id of created group, category
        PlanMeta planMeta = getPlanMeta(plan);

        planMeta.setCategoryGroupId(categoryGroup.getId());
        planMeta.setPlanGroupId(group.getGroupId());
        PlanMetaLocalServiceUtil.store(planMeta);
    }

    public List<PlanItem> getPlans() throws SystemException {
        return this.planItemFinder.getPlans();
    }

    public List<PlanItem> getPlansInContestPhase(long contestPhaseId) throws SystemException, PortalException {

        
        return this.getPlans(Collections.emptyMap(), Collections.emptyMap(), 0L, contestPhaseId, 0, Integer.MAX_VALUE,
                "", "", false);
    }

    public PlanItem getPlan(Long planId) throws NoSuchPlanItemException, SystemException {
        return this.planItemFinder.findLatestVersion(planId);
    }

    public List<PlanItem> getPlans(Map sessionMap, Map requestMap, long planTypeId, long contestPhaseId, int start,
            int end, final String sortColumn, String sortDirection) throws SystemException, PortalException {
        return getPlans(sessionMap, requestMap, planTypeId, contestPhaseId, start, end, sortColumn, sortDirection, true);
    }

    public List<PlanItem> getPlans(Map sessionMap, Map requestMap, long planTypeId, long contestPhaseId, int start,
            int end, final String sortColumn, String sortDirection, boolean applyFilters) throws SystemException,
            PortalException {
        
        PlanType planType = planTypeId > 0 ? PlanTypeLocalServiceUtil.getPlanType(planTypeId) : null;
        ContestPhase phase = contestPhaseId > 0 ? ContestPhaseLocalServiceUtil.getContestPhase(contestPhaseId) : null;
        /*
         * PlansUserSettings planUserSettings =
         * PlansUserSettingsLocalServiceUtil.getPlanUserSettings(sessionMap,
         * requestMap, planType);
         * 
         * return planItemFinder.getPlans(planUserSettings,
         * planType.getPlanTypeId(), start, end, sortColumn, sortDirection);
         */

        List<PlanItem> plans = new ArrayList<PlanItem>();
        
        if (phase != null) {
            try {
                plans = new ArrayList(planItemFinder.getPlansForPhase(phase.getContestPhasePK()));
            }
            catch (Throwable t) {
                t.printStackTrace();
                throw t;
            }
        } else {
            for (PlanItem planItem : planItemFinder.getPlans()) {
                if (planType == null
                        || (getPlanTypeId(planItem) != null && getPlanTypeId(planItem).equals(
                                planType.getPlanTypeId()))) {
                    plans.add(planItem);
                }
            }
        }

        if (planType == null && phase != null) {
            planType = ContestLocalServiceUtil.getPlanType(ContestPhaseLocalServiceUtil.getContest(phase));
        }

        if (sortColumn != null || sortColumn.trim().length() > 0) {
            final int directionModifier = sortDirection.equals("DESC") ? -1 : 1;
            Collections.sort(plans, new Comparator<PlanItem>() {

                @Override
                public int compare(PlanItem arg0, PlanItem arg1) {
                    try {
                        Comparable val1 = null;
                        Comparable val2 = null;
                        PlanAttribute plan1Attr = getPlanAttribute(arg0, sortColumn);
                        PlanAttribute plan2Attr = getPlanAttribute(arg1, sortColumn);

                        if (plan1Attr != null) {
                            val1 = (Comparable) PlanAttributeLocalServiceUtil.getTypedValue(plan1Attr);
                        }
                        if (plan2Attr != null) {
                            val2 = (Comparable) PlanAttributeLocalServiceUtil.getTypedValue(plan2Attr);

                        }
                        if (val1 == null || val2 == null) {
                            try {
                                Attribute attribute = Attribute.valueOf(sortColumn);
                                val1 = (Comparable) attribute.calculateTypedValue(arg0);
                                val2 = (Comparable) attribute.calculateTypedValue(arg1);
                            } catch (Exception e) {
                            }
                        }

                        if (val1 != null) {
                            if (val2 != null) {
                                return val1.compareTo(val2) * directionModifier;
                            } else {
                                return 1 * directionModifier;
                            }
                        } else {
                            return -1 * directionModifier;
                        }
                    } catch (SystemException e) {
                        e.printStackTrace();
                    }
                    return 0;
                }
            });
        }
        if (applyFilters) {
            return applyFilters(sessionMap, requestMap, planType, plans);
        } else {
            return plans;
        }
    }

    public List<PlanItem> getPlans(Map sessionMap, Map requestMap, PlanType planType, List<ContestPhase> phases,
            int start, int end, final String sortColumn, String sortDirection) throws SystemException, PortalException {
        return getPlans(sessionMap, requestMap, planType, phases, start, end, sortColumn, sortDirection, true);
    }

    public List<PlanItem> getPlans(Map sessionMap, Map requestMap, PlanType planType, List<ContestPhase> phases,
            int start, int end, final String sortColumn, String sortDirection, boolean applyFilters)
            throws SystemException, PortalException {
        /*
         * PlansUserSettings planUserSettings =
         * PlansUserSettingsLocalServiceUtil.getPlanUserSettings(sessionMap,
         * requestMap, planType);
         * 
         * return planItemFinder.getPlans(planUserSettings,
         * planType.getPlanTypeId(), start, end, sortColumn, sortDirection);
         */
        Set<Long> phasesIds = new HashSet<Long>();
        for (ContestPhase phase : phases) {
            phasesIds.add(phase.getContestPhasePK());
        }

        List<PlanItem> plans = new ArrayList<PlanItem>();
        for (PlanItem planItem : planItemFinder.getPlans()) {
            if ((planType == null || (getPlanTypeId(planItem) != null && getPlanTypeId(planItem).equals(
                    planType.getPlanTypeId())))
                    && (getPlanMeta(planItem).getContestPhase() != 0 && phasesIds.contains(getPlanMeta(planItem)
                            .getContestPhase()))) {
                plans.add(planItem);
            }
        }

        if (planType == null && phases != null && !phases.isEmpty()) {
            planType = ContestLocalServiceUtil.getPlanType(ContestPhaseLocalServiceUtil.getContest(phases.get(0)));
        }

        final int directionModifier = sortDirection.equals("DESC") ? -1 : 1;
        Collections.sort(plans, new Comparator<PlanItem>() {

            @Override
            public int compare(PlanItem arg0, PlanItem arg1) {
                try {
                    PlanAttribute plan1Attr = getPlanAttribute(arg0, sortColumn);
                    PlanAttribute plan2Attr = getPlanAttribute(arg1, sortColumn);
                    Comparable val1 = (Comparable) (plan1Attr != null ? PlanAttributeLocalServiceUtil.getTypedValue(plan1Attr) : null);
                    Comparable val2 = (Comparable) (plan2Attr != null ? PlanAttributeLocalServiceUtil.getTypedValue(plan2Attr) : null);
                    if (val1 != null) {
                        if (val2 != null) {
                            return val1.compareTo(val2) * directionModifier;
                        } else {
                            return -1 * directionModifier;
                        }
                    } else {
                        return 1 * directionModifier;
                    }
                } catch (SystemException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
        if (applyFilters) {
            return applyFilters(sessionMap, requestMap, planType, plans);
        } else {
            return plans;
        }
    }

    public boolean isNameAvailable(String planName, Contest c) throws SystemException, PortalException {
        for (ContestPhase phase : ContestLocalServiceUtil.getPhases(c)) {
            for (PlanItem item : ContestPhaseLocalServiceUtil.getPlans(phase)) {
                if (getName(item).equals(planName))
                    return false;
            }
        }
        return true;
    }

    public List<PlanItem> applyFilters(Map sessionMap, Map requestMap, PlanType planType, List<PlanItem> plans)
            throws PortalException, SystemException {
        /*
        PlansUserSettings planUserSettings = PlansUserSettingsLocalServiceUtil.getPlanUserSettings(sessionMap,
                requestMap, planType);
        if (!planUserSettings.getFilterEnabled()) {
            return plans;
        }

        List<PlanItem> filteredPlans = new ArrayList<PlanItem>();

        for (PlanItem plan : plans) {
            boolean inFilteredSet = true;
            for (Attribute attribute : Attribute.values()) {
                if (!attribute.isInFilteredSet(planUserSettings, plan)) {
                    inFilteredSet = false;
                    break;
                }
            }
            if (inFilteredSet) {
                filteredPlans.add(plan);
            }
        }

        return filteredPlans;
        */
        return plans;
    }

    public void removePlanWithEntireHistory(Long planId) {
        this.planItemFinder.removePlanWithHistory(planId);
    }

    public List<PlanItem> getAllVersions(PlanItem plan) throws SystemException {
        return this.planItemPersistence.findByAllByPlanId(plan.getPlanId());
    }

    public List<PlanAttribute> getPlanAttributes(PlanItem plan) throws SystemException {
        return PlanAttributeLocalServiceUtil.getPlanAttributes(plan.getPlanId());
    }

    public PlanAttribute getPlanAttribute(PlanItem plan, String name) throws SystemException {
        return PlanAttributeLocalServiceUtil.findPlanAttribute(plan.getPlanId(), name);
    }


    private final long defaultCompanyId = 10112;

    public void reIndex() throws SystemException {
        // reindex concrete plan
        /*
        for (PlanItem plan : PlanItemLocalServiceUtil.getPlans()) {
            try {
                Indexer.updateEntry(defaultCompanyId, plan);
            } catch (SearchException e) {
                _log.error("An exception has been thrown when reindexing plan with id: " + plan.getPlanId(), e);
            }
        }
        */
    }

    public void reIndex(long planId) throws SystemException {
        // reindex concrete plan
        /*
        try {
            PlanItem plan = PlanItemLocalServiceUtil.getPlan(planId);
            Indexer.updateEntry(defaultCompanyId, plan);
        } catch (NoSuchPlanItemException e) {
            _log.error("An exception has been thrown when reindexing plan with id: " + planId, e);
        } catch (SearchException e) {
            _log.error("An exception has been thrown when reindexing plan with id: " + planId, e);
        }*/
    }
    
    public List<PlanItem> findPlansForFocusArea(FocusArea fa) throws PortalException, SystemException {
        return findPlansForOntologyTerms(FocusAreaLocalServiceUtil.getTerms(fa));
    }

    public List<PlanItem> findPlansForOntologyTerms(OntologyTerm...terms) throws NoSuchPlanItemException, SystemException {
        return findPlansForOntologyTerms(Arrays.asList(terms));
    }
    
    public List<PlanItem> findPlansForOntologyTerms(List<OntologyTerm> terms) throws NoSuchPlanItemException, SystemException {
        Set<Long> ids = null;
        
        for (OntologyTerm term: terms) {
            List<Long> tmp = OntologyTermLocalServiceUtil.findTagedIdsForClass(term, PlanItem.class);
            if (ids == null) {
                ids = new HashSet<Long>(tmp);
            }
            else {
                ids.retainAll(tmp);
            }
        }
        
        List<PlanItem> ret = new ArrayList<PlanItem>();
        if (ids != null) {
            for (Long planId: ids) {
                ret.add(PlanItemLocalServiceUtil.getPlan(planId));
            
            }
        }
        
        return ret;
        
    }

    @Override
    public List<PlanItem> findPlansForOntologyTerms(OntologyTerm term) throws NoSuchPlanItemException, SystemException {
        return findPlansForOntologyTerms(new OntologyTerm[] {term});
    }
    
    public long countPlansByContest(Long contestId) throws SystemException, PortalException {
        List<ContestPhase> phases = ContestPhaseLocalServiceUtil.getPhasesForContest(ContestLocalServiceUtil.getContest(contestId));
        long counter = 0;
        for (ContestPhase phase: phases) {
            for (PlanItem plan: planItemFinder.getPlansForPhase(phase.getContestPhasePK())) {
                if (plan.getVersion() > 1 && !plan.getState().equals(EntityState.DELETED.name())) {
                    counter++;
                }
            }
        }
        return counter;
    }
    
    public List<PlanItem> getPlansByContest(Long contestId) throws SystemException, PortalException {
        List<ContestPhase> phases = ContestPhaseLocalServiceUtil.getPhasesForContest(ContestLocalServiceUtil.getContest(contestId));
        List<PlanItem> ret = new ArrayList<PlanItem>();
        long counter = 0;
        for (ContestPhase phase: phases) {
            for (PlanItem plan: planItemFinder.getPlansForPhase(phase.getContestPhasePK())) {
                if (plan.getVersion() > 1 && !plan.getState().equals(EntityState.DELETED.name())) {
                    ret.add(plan);
                }
            }
        }
        return ret;
    }
    
    
    
    public void planDeleted(PlanItem plan) throws PortalException, SystemException {
        planItemFinder.clearPhaseCache(getContestPhase(plan).getContestPhasePK());
        
    }

    
    /** methods from PlanItemImpl.java **/

    /* Description related stuff */
    public String getDescription(PlanItem pi) throws SystemException {
        return PlanDescriptionLocalServiceUtil.getForPlan(pi).getDescription();
    }

    public String getName(PlanItem pi) throws SystemException {
        return PlanDescriptionLocalServiceUtil.getForPlan(pi).getName();
    }
    
    public Long getImageId(PlanItem pi) throws SystemException {
        return PlanDescriptionLocalServiceUtil.getForPlan(pi).getImage();
    }
    
    public String getPitch(PlanItem pi) throws SystemException {
        return PlanDescriptionLocalServiceUtil.getForPlan(pi).getPitch();
    }
    
    
    public Image getImage(PlanItem pi) throws SystemException, PortalException {
        Long imageId = getImageId(pi);
        if (imageId != null) {
            return ImageLocalServiceUtil.getImage(imageId);
        }
        return null;
    }

    public void setDescription(PlanItem pi, String description, Long updateAuthorId) throws SystemException, PortalException {
        newVersion(pi, UpdateType.DESCRIPTION_UPDATED, updateAuthorId);

        PlanDescription planDescription = PlanDescriptionLocalServiceUtil.createNewVersionForPlan(pi);
        planDescription.setDescription(description);
        PlanDescriptionLocalServiceUtil.store(planDescription);
        updateAttribute(pi, Attribute.DESCRIPTION);
        
       //joinIfNotAMember(updateAuthorId);
        updateSearchIndex(pi);
        reindex(pi);
    }

    public void setName(PlanItem pi, String name, Long updateAuthorId) throws SystemException, PortalException {
        newVersion(pi, UpdateType.NAME_UPDATED, updateAuthorId);
        // update plan name attribute

        PlanDescription planDescription = PlanDescriptionLocalServiceUtil.createNewVersionForPlan(pi);
        planDescription.setName(name);
        PlanDescriptionLocalServiceUtil.store(planDescription);
        updateAttribute(pi, Attribute.NAME);
        //joinIfNotAMember(updateAuthorId);
        updateSearchIndex(pi);
        // update referenced discussion category group
        DiscussionCategoryGroup dcg = getDiscussionCategoryGroup(pi);
        dcg.setDescription(name + " discussion");
        DiscussionCategoryGroupLocalServiceUtil.store(dcg);
        reindex(pi);
    }
    
    public void setImage(PlanItem pi, Long imageId, Long updateAuthorId) throws SystemException, PortalException {
        newVersion(pi, UpdateType.IMAGE_UPDATED, updateAuthorId);
        // update plan name attribute

        PlanDescription planDescription = PlanDescriptionLocalServiceUtil.createNewVersionForPlan(pi);
        planDescription.setImage(imageId);
        PlanDescriptionLocalServiceUtil.store(planDescription);
        //updateAttribute(pi, Attribute.IMAGE);
        //joinIfNotAMember(updateAuthorId);
        updateSearchIndex(pi);
        reindex(pi);
    }
    
    public void setPitch(PlanItem pi, String pitch, Long updateAuthorId) throws SystemException, SearchException {
        newVersion(pi, UpdateType.PITCH_UPDATED, updateAuthorId);
        
        PlanDescription planDescription = PlanDescriptionLocalServiceUtil.createNewVersionForPlan(pi);
        planDescription.setPitch(pitch);
        PlanDescriptionLocalServiceUtil.store(planDescription);
        updateAttribute(pi, Attribute.ABSTRACT);
        updateSearchIndex(pi);
        reindex(pi);
    }

    public List<PlanDescription> getAllDescriptionVersions(PlanItem pi) throws SystemException {
        return PlanDescriptionLocalServiceUtil.getAllForPlan(pi);
    }

    /**
     * List of all versions of PlanDescription objects related to given plan
     * 
     * @see com.ext.portlet.model.PlanItem#getPlanDescriptions()
     */
    public List<PlanDescription> getPlanDescriptions(PlanItem pi) throws SystemException {
        return PlanDescriptionLocalServiceUtil.getAllForPlan(pi);
    }

    /*
     * 
     * Scenarios
     */
    public Long getScenarioId(PlanItem pi) throws SystemException {
        return PlanModelRunLocalServiceUtil.getCurrentForPlan(pi).getScenarioId();
    }

    public void setScenarioId(PlanItem pi, Long scenarioId, Long authorId) throws SystemException, PortalException {
        newVersion(pi, UpdateType.SCENARIO_UPDATED, authorId);

        PlanModelRun planModelRun = PlanModelRunLocalServiceUtil.createNewVersionForPlan(pi);
        planModelRun.setScenarioId(scenarioId);
        PlanModelRunLocalServiceUtil.store(planModelRun);

        // update plan attributes to reflect values from new scenario
        PlanType planType = getPlanType(pi);

        for (PlanConstants.Attribute attribute : PlanConstants.Attribute.getPlanTypeAttributes(planType)) {
            updateAttribute(pi, attribute);
        }
        
        reindex(pi);
        //joinIfNotAMember(authorId);
    }

    public void setModelId(PlanItem pi, Long simulationId, Long authorId) throws SystemException, PortalException {

        PlanType planType = getPlanType(pi);
        List<Simulation> sims = PlanTypeLocalServiceUtil.getAvailableModels(planType);
        boolean found = false;
        for (Simulation sim : sims) {
            if (simulationId.equals(sim.getId())) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new SystemException("Model id " + simulationId + " not valid for planType");
        }

        newVersion(pi, UpdateType.MODEL_UPDATED, authorId);
        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(pi);
        planMeta.setModelId(simulationId);
        PlanMetaLocalServiceUtil.store(planMeta);

        setScenarioId(pi, null, authorId);
        //joinIfNotAMember(authorId);
        reindex(pi);
    }

    public List<PlanModelRun> getAllPlanModelRuns(PlanItem pi) throws SystemException {
        return PlanModelRunLocalServiceUtil.getAllForPlan(pi);
    }

    /*
     * Plan meta informations.
     */

    public PlanMeta getPlanMeta(PlanItem pi) throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(pi);
    }

    public List<PlanMeta> getAllPlanMetas(PlanItem pi) throws SystemException {
        return PlanMetaLocalServiceUtil.getAllForPlan(pi);
    }

    public Long getPlanTypeId(PlanItem pi) throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(pi).getPlanTypeId();
    }

    public PlanType getPlanType(PlanItem pi) throws SystemException, PortalException {
        return PlanTypeLocalServiceUtil.getPlanType(getPlanTypeId(pi));
    }

    public Contest getContest(PlanItem pi) throws SystemException, PortalException {
       ContestPhase phase = getContestPhase(pi);
        return phase == null?null: ContestPhaseLocalServiceUtil.getContest(phase);
    }

    public ContestPhase getContestPhase(PlanItem pi) throws SystemException, PortalException {
        Long phase =  getPlanMeta(pi).getContestPhase();
        return phase == null?null:ContestPhaseLocalServiceUtil.getContestPhase(phase);
    }

    public void setContestPhase(PlanItem pi, ContestPhase phase, Long updateAuthorId) throws SystemException {
        newVersion(pi, UpdateType.PLAN_TYPE_UPDATED, updateAuthorId);

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(pi);
        planMeta.setContestPhase(phase.getContestPhasePK());
        PlanMetaLocalServiceUtil.store(planMeta);

        //joinIfNotAMember(updateAuthorId);
        reindex(pi);
    }

    public void setPlanTypeId(PlanItem pi, Long planTypeId, Long updateAuthorId) throws SystemException, PortalException {
        newVersion(pi, UpdateType.PLAN_TYPE_UPDATED, updateAuthorId);

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(pi);
        planMeta.setPlanTypeId(planTypeId);
        PlanMetaLocalServiceUtil.store(planMeta);
        
        //joinIfNotAMember(updateAuthorId);
        reindex(pi);
    }

    public Long getMBCategoryId(PlanItem pi) throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(pi).getMbCategoryId();
    }

    public void setMBCategoryId(PlanItem pi, Long mbCategoryId, Long updateAuthorId) throws SystemException {
        newVersion(pi, UpdateType.MB_GROUP_UPDATED, updateAuthorId);

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(pi);
        planMeta.setMbCategoryId(mbCategoryId);
        PlanMetaLocalServiceUtil.store(planMeta);
        reindex(pi);
    }

    public Long getCategoryGroupId(PlanItem pi) throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(pi).getCategoryGroupId();
    }

    public void setCategoryGroupId(PlanItem pi, Long categoryGroupId, Long updateAuthorId) throws SystemException {
        newVersion(pi, UpdateType.MB_GROUP_UPDATED, updateAuthorId);

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(pi);
        planMeta.setCategoryGroupId(categoryGroupId);
        PlanMetaLocalServiceUtil.store(planMeta);
        reindex(pi);
    }

    public Long getPlanGroupId(PlanItem pi) throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(pi).getPlanGroupId();
    }

    public void setPlanGroupId(PlanItem pi, Long groupId, Long updateAuthorId) throws SystemException {
        newVersion(pi, UpdateType.PLAN_GROUP_UPDATED, updateAuthorId);

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(pi);
        planMeta.setPlanGroupId(groupId);
        PlanMetaLocalServiceUtil.store(planMeta);
        reindex(pi);
    }

    public Long getAuthorId(PlanItem pi) throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(pi).getAuthorId();
    }

    public User getAuthor(PlanItem pi) throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(getAuthorId(pi));
    }

    public void setAuthorId(PlanItem pi, Long authorId, Long updateAuthorId) throws SystemException {
        newVersion(pi, UpdateType.PLAN_GROUP_UPDATED, updateAuthorId);

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(pi);
        planMeta.setAuthorId(authorId);
        PlanMetaLocalServiceUtil.store(planMeta);
        reindex(pi);
    }

    public Date getCreateDate(PlanItem pi) throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(pi).getCreated();
    }

    public Date getPublishDate(PlanItem pi) throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(pi).getCreated();
    }

    public String getCreator(PlanItem pi) throws PortalException, SystemException {
        return getAuthor(pi).getScreenName();

    }

    public Integer getVotes(PlanItem pi) throws SystemException {
        //return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getVotes();
        return PlanVoteLocalServiceUtil.countPlanVotesByPlanId(pi.getPlanId());
    }

    public boolean getOpen(PlanItem pi) throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(pi).getOpen();
    }

    public void setOpen(PlanItem pi, boolean open, Long updateAuthorId) throws SystemException {
        if (open) {
            newVersion(pi, UpdateType.PLAN_OPENED, updateAuthorId);
        }
        else {
            newVersion(pi, UpdateType.PLAN_CLOSED, updateAuthorId);
            
        }

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(pi);
        planMeta.setOpen(open);
        PlanMetaLocalServiceUtil.store(planMeta);
        updateAttribute(pi, Attribute.IS_PLAN_OPEN);
        reindex(pi);

    }
    
    public void setOpen(PlanItem pi, boolean open) throws SystemException {
        PlanMeta planMeta = getPlanMeta(pi);
        planMeta.setOpen(open);
        PlanMetaLocalServiceUtil.store(planMeta);
        updateAttribute(pi, Attribute.IS_PLAN_OPEN);
        reindex(pi);

    }

    public String getStatus(PlanItem pi) throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(pi).getStatus();
    }

    public void setStatus(PlanItem pi, String status, Long updateAuthorId) throws SystemException {
        newVersion(pi, UpdateType.PLAN_STATUS_UPDATED, updateAuthorId);

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(pi);
        planMeta.setStatus(status);
        PlanMetaLocalServiceUtil.store(planMeta);
        updateAttribute(pi, PlanConstants.Attribute.STATUS);
        reindex(pi);
    }

    /*
     * POSITIONS
     */

    public PlanPositions getPlanPositions(PlanItem pi) throws NoSuchPlanPositionsException, SystemException {
        return PlanPositionsLocalServiceUtil.getCurrentForPlan(pi);
    }

    public List<Long> getPositionsIds(PlanItem pi) throws SystemException, NoSuchPlanPositionsException {
        PlanPositions x = PlanPositionsLocalServiceUtil.getCurrentForPlan(pi);
        return PlanPositionsLocalServiceUtil.getPositionsIds(x);
    }

    public Long[] getPositionsIdsArray(PlanItem pi) throws SystemException, NoSuchPlanPositionsException {
        List<Long> idsList = getPositionsIds(pi);
        Long[] ret = new Long[idsList.size()];
        return idsList.toArray(ret);
    }

    public void setPositions(PlanItem pi, List<Long> positionsIds, Long updateAuthorId) throws PortalException, SystemException {
        newVersion(pi, UpdateType.PLAN_POSITIONS_UPDATED, updateAuthorId);

        PlanPositions planPositions = PlanPositionsLocalServiceUtil.createNewVersionForPlan(pi);
        PlanPositionsLocalServiceUtil.store(planPositions);
        PlanPositionsLocalServiceUtil.setPositionsIds(planPositions, positionsIds);
        updateAttribute(pi, Attribute.POSITIONS);
        
        //joinIfNotAMember(updateAuthorId);
    }

    public List<PlanPositions> getAllPositionsVersions(PlanItem pi) throws SystemException {
        return PlanPositionsLocalServiceUtil.getAllForPlan(pi);
    }

    /*
     * VOTES
     */
    public boolean hasUserVoted(PlanItem pi, Long userId) throws PortalException, SystemException {
        try {
            PlanVote vote = PlanVoteLocalServiceUtil.getPlanVote(userId, getContest(pi).getContestPK());
            return vote.getPlanId() == pi.getPlanId();
        } catch (NoSuchPlanVoteException e) {
            // ignore
        }
        return false;
    }

    public void vote(PlanItem pi, Long userId) throws PortalException, SystemException {
        if (PlanVoteLocalServiceUtil.voteForPlan(pi.getPlanId(), userId)) {
            PlanMetaLocalServiceUtil.vote(PlanMetaLocalServiceUtil.getCurrentForPlan(pi));
        }
        updateAttribute(pi, Attribute.VOTES);
    }

    public void unvote(PlanItem pi, Long userId) throws PortalException, SystemException {
        if (PlanVoteLocalServiceUtil.unvote(userId, getContest(pi).getContestPK())) {
            PlanMetaLocalServiceUtil.unvote(PlanMetaLocalServiceUtil.getCurrentForPlan(pi));
        }
        updateAttribute(pi, Attribute.VOTES);
    }


    private PlanItem newVersion(PlanItem pi, UpdateType updateType, Long updateAuthorId) throws SystemException {
        PlanItem latestVersion = pi;
        try {
            /*
             *
             // i don't think that this is a problem
            if (!latestVersion.equals()) {
                throw new SystemException(
                        "Can only create a new version of a plan from the latest version in existence");
            }
            */
            latestVersion = PlanItemLocalServiceUtil.getPlan(pi.getPlanId());
            latestVersion = latestVersion != null ? latestVersion : pi;
        } catch (NoSuchPlanItemException e) {
            // ignore
        }
        PlanItem oldVersion = (PlanItem) latestVersion.clone();
        //newVersion.setId();
        store(oldVersion);

        pi.setId(CounterLocalServiceUtil.increment(PlanItem.class.getName()));
        pi.setVersion(Math.max(pi.getVersion(), latestVersion.getVersion()) + 1);
        pi.setUpdated(new Date());
        pi.setUpdateType(updateType.name());
        pi.setUpdateAuthorId(updateAuthorId);
        pi.setNew(true);
        store(pi);
        
        setAttribute(pi, PlanConstants.Attribute.LAST_MOD_DATE, String.valueOf(pi.getUpdated()));
        
        return pi;
    }

    public void store(PlanItem pi) throws SystemException {
        if (pi.isNew()) {
            PlanItemLocalServiceUtil.addPlanItem(pi);
        } else {
            PlanItemLocalServiceUtil.updatePlanItem(pi);
        }
    }

    /**
     * Updates values of all available attributes.
     * 
     * @throws SystemException
     */

    public void updateAllAttributes(PlanItem pi) throws SystemException {
        for (Attribute attribute : Attribute.values()) {
            updateAttribute(pi, attribute);
        }
    }

    /**
     * Updates value of a given attribute, should be used only for property
     * attributes.
     * 
     * @param attributeName
     *            attribute which value should be updated
     * @throws SystemException
     *             in case of any error
     */
    public void updateAttribute(PlanItem pi, String attributeName) throws SystemException {
        updateAttribute(pi, Attribute.valueOf(attributeName));
    }

    /**
     * Updates value of a given attribute, should be used only for property
     * attributes.
     * 
     * @param attribute
     *            attribute which value should be updated
     * @throws SystemException
     *             in case of any error
     */
    private void updateAttribute(PlanItem pi, Attribute attribute) throws SystemException {
        String value = attribute.calculateValue(pi).toString();
        PlanAttribute att = PlanAttributeLocalServiceUtil.findPlanAttribute(pi.getPlanId(), attribute.name());
        if (att != null) {
            if (! att.getAttributeValue().equals(value)) {
                att.setAttributeValue(value);
                PlanAttributeLocalServiceUtil.updatePlanAttribute(att);
            }
        } else {
            PlanAttributeLocalServiceUtil.addPlanAttribute(pi.getPlanId(), attribute.name(), value);
        }
    }
    
    /**
     * Updates value of a given attribute, should be used only for property
     * attributes.
     * 
     * @param attribute
     *            attribute which value should be updated
     * @throws SystemException
     *             in case of any error
     */
    private void setAttribute(PlanItem pi, Attribute attribute, String value) throws SystemException {
        PlanAttribute att = PlanAttributeLocalServiceUtil.findPlanAttribute(pi.getPlanId(), attribute.name());
        if (att != null) {
            att.setAttributeValue(value);
            PlanAttributeLocalServiceUtil.updatePlanAttribute(att);
        }
        else {
            PlanAttributeLocalServiceUtil.addPlanAttribute(pi.getPlanId(), attribute.name(), value);
        }
    }
    
    /**
     * Updates value of a given attribute, should be used only for property
     * attributes.
     * 
     * @param attribute
     *            attribute which value should be updated
     * @throws SystemException
     *             in case of any error
     */
    private String getAttribute(PlanItem pi, Attribute attribute) throws SystemException {
        PlanAttribute att = PlanAttributeLocalServiceUtil.findPlanAttribute(pi.getPlanId(), attribute.name());
        if (att != null) {
            return att.getAttributeValue();
        }
        return null;
    }
    

    /*
     * Plan membership related stuff
     */

    /**
     * Returns list of plan members.
     */
    public List<User> getMembers(PlanItem pi) throws SystemException {
        return UserLocalServiceUtil.getGroupUsers(getPlanGroupId(pi));
    }

    public List<MembershipRequest> getMembershipRequests(PlanItem pi) throws SystemException {
        return MembershipRequestLocalServiceUtil.search(getPlanGroupId(pi), MembershipRequestConstants.STATUS_PENDING, 0,
                Integer.MAX_VALUE);
    }

    public void addMembershipRequest(PlanItem pi, Long userId, String comments) throws PortalException, SystemException {
        PlanTeamHistoryLocalServiceUtil.newHistoryItem(pi.getPlanId(), userId,
                PlanTeamActions.MEMBERSHIP_REQUESTED.name(), userId);
        MembershipRequestLocalServiceUtil.addMembershipRequest(userId, getPlanGroupId(pi), comments, null);
    }

    public void dennyMembershipRequest(PlanItem pi, Long userId, MembershipRequest request, String reply, Long updateAuthorId)
            throws PortalException, SystemException {
        PlanTeamHistoryLocalServiceUtil.newHistoryItem(pi.getPlanId(), userId,
                PlanTeamActions.MEMBERSHIP_DECLEINED.name(), updateAuthorId);
        MembershipRequestLocalServiceUtil.updateStatus(userId, request.getMembershipRequestId(), reply,
                MembershipRequestConstants.STATUS_DENIED, false, null);
    }

    public void approveMembershipRequest(PlanItem pi, Long userId, MembershipRequest request, String reply, Long updateAuthorId)
            throws PortalException, SystemException {
        PlanTeamHistoryLocalServiceUtil.newHistoryItem(pi.getPlanId(), userId,
                PlanTeamActions.MEMBERSHIP_APPROVED.name(), updateAuthorId);
        MembershipRequestLocalServiceUtil.updateStatus(userId, request.getMembershipRequestId(), reply,
                MembershipRequestConstants.STATUS_APPROVED, true, null);
    }

    /*
     * Plan actions, publishing/deleting
     */
    public void publish(PlanItem pi, Long updateAuthorId) throws SystemException, PortalException {
        newVersion(pi, UpdateType.PLAN_PUBLISHED, updateAuthorId);

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(pi);
        planMeta.setPlanTypeId(getPlanType(pi).getPublishedCounterpartId());
        PlanMetaLocalServiceUtil.store(planMeta);
    }

    public void delete(PlanItem pi, Long updateAuthorId) throws SystemException, PortalException {
        newVersion(pi, UpdateType.PLAN_DELETED, updateAuthorId);
        pi.setState(EntityState.DELETED.name());
        store(pi);
        PlanItemLocalServiceUtil.planDeleted(pi);
        /*
        try {
            Indexer.deleteEntry(10112L, getPlanId());
        } catch (SearchException e) {
            _log.error("can't remove plan " + getPlanId() + " from search index", e);
        }
        */
        
        IndexerRegistryUtil.getIndexer(PlanItem.class).delete(pi.getPlanId());
        
    }

    public User getUpdateAuthor(PlanItem pi) throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(pi.getUpdateAuthorId());
    }

    public List<PlanFan> getFans(PlanItem pi) throws SystemException {
        return PlanFanLocalServiceUtil.getPlanFansForPlan(pi.getPlanId());
    }

    public PlanFan addFan(PlanItem pi, Long userId) throws SystemException {
        if (!isUserAFan(pi, userId)) {
            PlanFan planFan = PlanFanLocalServiceUtil.addFan(pi.getPlanId(), userId);
            setAttribute(pi, Attribute.SUPPORTERS, String.valueOf(PlanFanLocalServiceUtil.countPlanFansForPlan(pi.getPlanId())));
            return planFan;
        }
        return null;
    }

    public void removeFan(PlanItem pi, Long userId) throws SystemException {
        if (isUserAFan(pi, userId)) {
            PlanFanLocalServiceUtil.removePlanFan(pi.getPlanId(), userId);
            setAttribute(pi, Attribute.SUPPORTERS, String.valueOf(PlanFanLocalServiceUtil.countPlanFansForPlan(pi.getPlanId())));
        }
    }

    public boolean isUserAFan(PlanItem pi, Long userId) throws SystemException {
        try {
            return PlanFanLocalServiceUtil.getPlanFanByPlanIdUserId(pi.getPlanId(), userId) != null;
        } catch (NoSuchPlanFanException e) {
            return false;
        }
    }

    public boolean isUserAMember(PlanItem pi, Long userId) throws SystemException {
        return GroupLocalServiceUtil.hasUserGroup(userId, getPlanGroupId(pi));
    }

    public boolean hasUserRequestedMembership(PlanItem pi, Long userId) throws SystemException {
        try {
            PlanTeamHistory action = PlanTeamHistoryLocalServiceUtil.getLastUserActionInPlan(pi.getPlanId(), userId);
            if (action.getAction().equals(PlanTeamActions.MEMBERSHIP_REQUESTED.name())) {
                return true;
            }
        } catch (NoSuchPlanTeamHistoryException e) {
            // ignore
        }
        return false;
    }

    public boolean isAdmin(PlanItem pi, Long userId) throws PortalException, SystemException {
        return UserGroupRoleLocalServiceUtil.hasUserGroupRole(userId, getPlanGroupId(pi),
                RoleConstants.SITE_ADMINISTRATOR)
                || isOwner(pi, userId);
    }

    public boolean isOwner(PlanItem pi, Long userId) throws PortalException, SystemException {
        return UserGroupRoleLocalServiceUtil.hasUserGroupRole(userId, getPlanGroupId(pi), RoleConstants.SITE_OWNER);
    }

    public void setUserPermission(PlanItem pi, Long userId, String userPermission, Long updateAuthorId) throws SystemException,
            PortalException {
        PlanUserPermission userPerm = PlanUserPermission.valueOf(userPermission);

        Group group = GroupLocalServiceUtil.getGroup(getPlanGroupId(pi));
        UserGroupRoleLocalServiceUtil.deleteUserGroupRoles(userId, new long[] { getPlanGroupId(pi) });
        String roleName = RoleConstants.SITE_MEMBER;

        if (userPerm == PlanUserPermission.OWNER) {
            roleName = RoleConstants.SITE_OWNER;
        } else if (userPerm == PlanUserPermission.ADMIN) {
            roleName = RoleConstants.SITE_ADMINISTRATOR;
        }

        Role role = RoleLocalServiceUtil.getRole(group.getCompanyId(), roleName);
        UserGroupRoleLocalServiceUtil.addUserGroupRoles(userId, getPlanGroupId(pi), new long[] { role.getRoleId() });
        PlanTeamHistoryLocalServiceUtil.newHistoryItem(pi.getPlanId(), userId, PlanTeamActions.PERMISSIONS_CHANGED.name(),
                userPermission, updateAuthorId);
    }

    public void removeMember(PlanItem pi, Long userId, Long updateAuthorId) throws SystemException, PortalException {
        UserLocalServiceUtil.unsetGroupUsers(getPlanGroupId(pi), new long[] { userId }, null);
        PlanTeamHistoryLocalServiceUtil.newHistoryItem(pi.getPlanId(), userId, PlanTeamActions.MEMBER_REMOVED.name(),
                updateAuthorId);
    }
    

    public void joinIfNotAMember(PlanItem pi, Long userId) throws SystemException, PortalException {
        if (! isUserAMember(pi, userId)) {
            GroupLocalServiceUtil.addUserGroups(userId, new long[] {getPlanGroupId(pi)});
            PlanTeamHistoryLocalServiceUtil.newHistoryItem(pi.getPlanId(), userId, PlanTeamActions.MEMBERSHIP_APPROVED.name(),
                    userId);
        }
        
    }
    
    public void setSeekingAssistance(PlanItem pi, boolean seekingAssistance) throws SystemException {
        setAttribute(pi, Attribute.SEEKING_ASSISTANCE, String.valueOf(seekingAssistance));
    }
    
    public boolean isSeekingAssistance(PlanItem pi) throws SystemException {
        Object value = Attribute.SEEKING_ASSISTANCE.getValue(pi);
        if ("true".equals(value)) {
            return true;
        }
        return false;
    }
    

    private void updateSearchIndex(PlanItem pi) throws SearchException, SystemException {
        //Indexer.updateEntry(10112L, this);
        
    }
    
    public DiscussionCategoryGroup getDiscussionCategoryGroup(PlanItem pi) throws PortalException, SystemException {
        return DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(getCategoryGroupId(pi));
        
    }
    
    public PlanItem promote(PlanItem pi, User user) throws SystemException, PortalException {
        ContestPhase contestPhase = ContestPhaseLocalServiceUtil.getNextContestPhase(getContestPhase(pi));
        
        PlanMeta planMeta = PlanMetaLocalServiceUtil.getCurrentForPlan(pi);
        planMeta.setPromoted(true);
        planMeta.setPreviousContestPhase(planMeta.getContestPhase());
        planMeta.setContestPhase(contestPhase.getContestPhasePK());
        
        PlanMetaLocalServiceUtil.store(planMeta);
        setAttribute(pi, Attribute.PLAN_PLACE, String.valueOf(1));

        return pi;
    }
    
    
    
    public boolean getPromoted(PlanItem pi) throws SystemException {
        Boolean promoted = PlanMetaLocalServiceUtil.getCurrentForPlan(pi).getPromoted();
        return promoted != null ? promoted : false;
        
    }
    
    public int getCommentsCount(PlanItem pi) throws PortalException, SystemException {
        return DiscussionCategoryGroupLocalServiceUtil.getCommentsCount(getDiscussionCategoryGroup(pi));
    }
    
    public void setPlace(PlanItem pi, int place) throws SystemException {
        setAttribute(pi, Attribute.PLAN_PLACE, String.valueOf(place));
    }
    
    public void removePlace(PlanItem pi) throws SystemException {
        setAttribute(pi, Attribute.PLAN_PLACE, String.valueOf(-1));
    }
    
    public List<PlanVote> getPlanVotes(PlanItem pi) throws SystemException {
        return PlanVoteLocalServiceUtil.getPlanVotes(pi.getPlanId());
    }
    
    public void setRibbon(PlanItem pi, Integer ribbon) throws SystemException {
        setAttribute(pi, Attribute.PLAN_RIBBON, String.valueOf(ribbon));
    }
    

    public void setRibbonText(PlanItem pi, String ribbonText) throws SystemException {
        setAttribute(pi, Attribute.PLAN_RIBBON_TEXT, String.valueOf(ribbonText));
    }
    
    
    public void setAttribute(PlanItem pi, String attributeName, String value) throws SystemException {
        setAttribute(pi, Attribute.valueOf(attributeName), value);
    }
    
    public void removeAttribute(PlanItem pi, String attributeName) throws SystemException {
        PlanAttribute attr = PlanAttributeLocalServiceUtil.findPlanAttribute(pi.getPlanId(), attributeName);
        
        if (attr != null) {
            PlanAttributeLocalServiceUtil.deletePlanAttribute(attr);
        }
    }
    
    public PlanTemplate getPlanTemplate(PlanItem pi) throws PortalException, SystemException {
        return ContestLocalServiceUtil.getPlanTemplate(getContest(pi));
    }
    
    public List<PlanSection> getPlanSections(PlanItem pi) throws PortalException, SystemException {
        PlanTemplate tmpl = getPlanTemplate(pi);
        if (tmpl != null) {
            List<PlanSection> ret = new ArrayList<PlanSection>();
            
            for (PlanSectionDefinition psd: PlanTemplateLocalServiceUtil.getSections(tmpl)) {
                ret.add(PlanSectionLocalServiceUtil.getForPlanSectionDef(pi, psd));
            }
            return ret;
        }
        
        return null;
    }
    
    public void setSectionContent(PlanItem pi, PlanSectionDefinition psd, String content, List<Long> referencedPlans, Long updateAuthorId) 
    throws SystemException, PortalException {
        newVersion(pi, UpdateType.PLAN_SECTION_UPDATED, updateAuthorId);
        PlanSection ps = PlanSectionLocalServiceUtil.createNewVersionForPlanSectionDefinition(pi, psd, false);
        ps.setUpdateAuthorId(updateAuthorId);
        ps.setContent(content);
        
        for (Long planId: referencedPlans) {
            PlanSectionLocalServiceUtil.addPlanReference(ps, planId);
        }
        
        PlanSectionLocalServiceUtil.store(ps);
        reindex(pi);
        
    }
    
    public List<PlanSection> getAllPlanSections(PlanItem pi, PlanSectionDefinition psd) throws SystemException {
        return PlanSectionLocalServiceUtil.getAllForPlanDefinition(pi, psd);
    }

    public Integer getRibbon(PlanItem pi) throws SystemException {
        PlanAttribute attr = getPlanAttribute(pi, PlanConstants.Attribute.PLAN_RIBBON.name());
        try {
            return attr != null && attr.getAttributeValue() != null && attr.getAttributeValue().trim().length() > 0 ? 
                    Integer.parseInt(attr.getAttributeValue()) : null;
        }
        catch (NumberFormatException e) {
            return null;
        }
    }
    
    public void setTeam(PlanItem pi, String team) throws SystemException {
        setAttribute(pi, PlanConstants.Attribute.TEAM, team);
    }
    
    public String getTeam(PlanItem pi) throws SystemException {
        String team = getAttribute(pi, Attribute.TEAM);
        if (team == null || team.trim().length() == 0) {
            return null;
        }
        return team;
    }
    
    public void revertTo(PlanItem pi, Long updateAuthorId) throws SystemException, PortalException {
        
        
        PlanDescription oldDesc = PlanDescriptionLocalServiceUtil.getForPlan(pi);
        PlanModelRun oldAi = PlanModelRunLocalServiceUtil.getForPlan(pi);
        List<PlanSection> oldSections = getPlanSections(pi);
        
        newVersion(pi, UpdateType.PLAN_REVERTED, updateAuthorId);
        
        PlanDescriptionLocalServiceUtil.createNewVersionForPlanFrom(pi, oldDesc, true);
        PlanModelRunLocalServiceUtil.createNewVersionForPlanFrom(pi, oldAi, true);
        
        PlanTemplate tmpl = getPlanTemplate(pi);
        for (PlanSection section: oldSections) {
            PlanSectionLocalServiceUtil.createForPlanFrom(pi, section, true);
        }
        
        updateAttribute(pi, Attribute.ABSTRACT);
        updateAttribute(pi, Attribute.DESCRIPTION);
        updateAttribute(pi, Attribute.NAME);
        //updateAttribute(pi, Attribute.IMAGE);        
        
        PlanType planType = getPlanType(pi);

        for (PlanConstants.Attribute attribute : PlanConstants.Attribute.getPlanTypeAttributes(planType)) {
            updateAttribute(pi, attribute);
        }
    }
    
    public String getTags(PlanItem pi) throws SystemException {
        String tags = getAttribute(pi, Attribute.TAGS);
        if (tags == null || tags.trim().length() == 0) {
            return null;
        }
        return tags;
    }
    
    public void setTags(PlanItem pi, String tags) throws SystemException {
        setAttribute(pi, Attribute.TAGS, tags);
    }
    
    public String getTagsHover(PlanItem pi) throws SystemException {
        String tagsHover = getAttribute(pi, Attribute.TAGS_HOVER);
        if (tagsHover == null || tagsHover.trim().length() == 0) {
            return null;
        }
        return tagsHover;
    }
    
    public void setTagsHover(PlanItem pi, String tagsHover) throws SystemException {
        setAttribute(pi, Attribute.TAGS_HOVER, tagsHover);
    }
    
    public Integer getTagsOrder(PlanItem pi) throws SystemException {
        String tagsOrderStr = getAttribute(pi, Attribute.TAGS_ORDER);
        int ret = 0;
        if (tagsOrderStr != null && tagsOrderStr.trim().length() > 0) {
            try {
                ret = Integer.parseInt(tagsOrderStr);
                
            }
            catch (NumberFormatException e) {
                // ignore
            }
        }
        return ret;
    }
    
    public void setTagsOrder(PlanItem pi, int tagsOrder) throws SystemException {
        setAttribute(pi, Attribute.TAGS_ORDER, String.valueOf(tagsOrder));
    }
    
    public void promotePlans(long sourcePhasePk,long destPhasePk) throws PortalException, SystemException {
        ContestPhase sourcePhase = ContestPhaseLocalServiceUtil.getContestPhase(sourcePhasePk);
        ContestPhase targetPhase = ContestPhaseLocalServiceUtil.getContestPhase(destPhasePk);
        
        if (ContestPhaseLocalServiceUtil.getContest(targetPhase).getPlanTypeId() != 
                ContestPhaseLocalServiceUtil.getContest(sourcePhase).getPlanTypeId()) {
            throw new PortalException("Phases are incompatible, can't promote");
        }
        else {
            List<PlanItem> planCopyItems = new ArrayList<PlanItem>();
            promotePlans(ContestPhaseLocalServiceUtil.getPlans(sourcePhase), destPhasePk);
        }
    }
    
    public void promotePlans(List<PlanItem> plansToBeCopied, long destPhasePk) throws PortalException, SystemException {
        ContestPhase targetPhase = ContestPhaseLocalServiceUtil.getContestPhase(destPhasePk);
        for (PlanItem plan: plansToBeCopied) {
            if (plan.getVersion() < 2) {
                continue;
            }
            PlanItem newPlan = createPlan(plan, targetPhase, getAuthorId(plan));
            //newPlan.setName(plan.getName(), plan.getAuthorId());
            
            // copy fans
            for (PlanFan planFan: getFans(plan)) {
                addFan(newPlan, planFan.getUserId());
            }
            
            // copy votes
            for (PlanVote planVote: getPlanVotes(plan)) {
                vote(newPlan, planVote.getUserId());
            }
            
            
            // copy entire discussions, comments migration
            DiscussionCategoryGroup dcg = getDiscussionCategoryGroup(newPlan);
            DiscussionCategoryGroupLocalServiceUtil.copyEverything(dcg, getDiscussionCategoryGroup(plan));
            

            // update plan version
            newPlan.setVersion(2L);
            PlanItemLocalServiceUtil.store(newPlan);

            long[] userIds = UserLocalServiceUtil.getGroupUserIds(getPlanGroupId(plan));
            UserLocalServiceUtil.addGroupUsers(getPlanGroupId(newPlan), userIds);
            /*
            if (addSemiFinalistRibbon) {
                PlanItemLocalServiceUtil.setRibbon(plan, 1);
                PlanItemLocalServiceUtil.setRibbonText(plan, planAdvancedText);
            }
            */
            
            // copy subscriptions for plan
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(ActivitySubscription.class);
            
            ClassName cn = ClassNameLocalServiceUtil.getClassName(PlanItem.class.getName());
            Criterion criterionClassNameId = RestrictionsFactoryUtil.eq("classNameId", cn.getClassNameId());
            Criterion criterionClassPK = RestrictionsFactoryUtil.eq("classPK", plan.getPlanId());
            query.add(RestrictionsFactoryUtil.and(criterionClassNameId, criterionClassPK));
            
            for (Object subscriptionObj : ActivitySubscriptionLocalServiceUtil.dynamicQuery(query)) {
                ActivitySubscription subscription = (ActivitySubscription) subscriptionObj;
                
                ActivitySubscriptionLocalServiceUtil.addSubscription(PlanItem.class, newPlan.getPlanId(), 0, "", subscription.getReceiverId());
            }
            
            // copy subscriptions for comments
            query = DynamicQueryFactoryUtil.forClass(ActivitySubscription.class);
            
            cn = ClassNameLocalServiceUtil.getClassName(DiscussionCategoryGroup.class.getName());
            criterionClassNameId = RestrictionsFactoryUtil.eq("classNameId", cn.getClassNameId());
            criterionClassPK = RestrictionsFactoryUtil.eq("classPK", PlanItemLocalServiceUtil.getCategoryGroupId(plan));
            query.add(RestrictionsFactoryUtil.and(criterionClassNameId, criterionClassPK));
            
            for (Object subscriptionObj : ActivitySubscriptionLocalServiceUtil.dynamicQuery(query)) {
                ActivitySubscription subscription = (ActivitySubscription) subscriptionObj;
                
                ActivitySubscriptionLocalServiceUtil.addSubscription(DiscussionCategoryGroup.class, 
                        PlanItemLocalServiceUtil.getCategoryGroupId(newPlan), 0, "", subscription.getReceiverId());
            }
            
            
        }
        
        
    }

    
    private void reindex(PlanItem plan) {
        Indexer indexer = IndexerRegistryUtil.getIndexer(PlanItem.class);

        try {
            indexer.reindex(plan.getPlanId());
        } catch (SearchException e) {
            _log.error("Can't reindex plan " + plan.getPlanId(), e);
        }
    }
}

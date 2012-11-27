package com.ext.portlet.plans.service.impl;

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

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.contests.service.ContestLocalServiceUtil;
import com.ext.portlet.contests.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.discussions.DiscussionActions;
import com.ext.portlet.discussions.model.DiscussionCategory;
import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.ontology.model.FocusArea;
import com.ext.portlet.ontology.model.OntologyTerm;
import com.ext.portlet.plans.EntityState;
import com.ext.portlet.plans.NoSuchPlanItemException;
import com.ext.portlet.plans.PlanConstants.Attribute;
import com.ext.portlet.plans.UpdateType;
import com.ext.portlet.plans.model.PlanAttribute;
import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanMeta;
import com.ext.portlet.plans.model.PlanModelRun;
import com.ext.portlet.plans.model.PlanPositions;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.PlansUserSettings;
import com.ext.portlet.plans.service.PlanAttributeLocalServiceUtil;
import com.ext.portlet.plans.service.PlanDescriptionLocalServiceUtil;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanMetaLocalServiceUtil;
import com.ext.portlet.plans.service.PlanModelRunLocalServiceUtil;
import com.ext.portlet.plans.service.PlanPositionsLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.ext.portlet.plans.service.PlansUserSettingsLocalServiceUtil;
import com.ext.portlet.plans.service.base.PlanItemLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.GroupServiceUtil;
import com.liferay.portal.service.PermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

import edu.mit.cci.simulation.client.Simulation;

/**
 * The implementation of the plan item local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.plans.service.PlanItemLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.plans.service.base.PlanItemLocalServiceBaseImpl
 * @see com.ext.portlet.plans.service.PlanItemLocalServiceUtil
 */
public class PlanItemLocalServiceImpl extends PlanItemLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.plans.service.PlanItemLocalServiceUtil} to access the plan item local service.
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
        long planTypeId = phase.getContest().getPlanTypeId();
        String name = DEFAULT_UNTITLED_PLAN_STEM_NAME + planId;
        PlanItem planItem = PlanItemLocalServiceUtil.createPlanItem(planItemId);
        planItem.setPlanId(planId);
        planItem.setVersion(0L);
        planItem.setUpdated(new Date());
        planItem.setUpdateAuthorId(authorId);
        planItem.setState(EntityState.ACTIVE.name());
        planItem.setUpdateType(UpdateType.CREATED.name());

        planItem = PlanItemLocalServiceUtil.addPlanItem(planItem);

        // create related entities, plan description, meta, model run
        PlanDescriptionLocalServiceUtil.createPlanDescription(planItem, name);
        PlanModelRunLocalServiceUtil.createPlanModelRun(planItem);

        PlanMeta meta = PlanMetaLocalServiceUtil.createPlanMeta(planItem, planTypeId);
        PlanType type = PlanTypeLocalServiceUtil.getPlanType(planTypeId);
        Simulation defaultmodel = type.getDefaultModel();
        if (defaultmodel != null) {
            meta.setModelId(defaultmodel.getId());
        } else {
            List<Simulation> models = type.getAvailableModels();
            if (models.size() > 0) {
                meta.setModelId(models.get(0).getId());
            }
        }
        meta.setContestPhase(phase.getContestPhasePK());
        PlanPositionsLocalServiceUtil.createPlanPositions(planItem);

        // create community, Message Boards category
        initPlan(planItem);

        /* update/create all attributes */
        // planItem.updateAllAttributes();
        planItem.updateAttribute(Attribute.CREATOR.name());
        planItem.updateAttribute(Attribute.NAME.name());
        planItem.updateAttribute(Attribute.DESCRIPTION.name());
        planItem.updateAttribute(Attribute.CREATE_DATE.name());
        planItem.updateAttribute(Attribute.PUBLISH_DATE.name());
        planItem.updateAttribute(Attribute.VOTES.name());
        planItem.updateAttribute(Attribute.SUPPORTERS.name());
        planItem.updateAttribute(Attribute.IS_PLAN_OPEN.name());
        planItem.updateAttribute(Attribute.SEEKING_ASSISTANCE.name());
        planItem.updateAttribute(Attribute.STATUS.name());

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

        planItem = PlanItemLocalServiceUtil.addPlanItem(planItem);

        // create related entities, plan description, meta, model run
        PlanDescriptionLocalServiceUtil.createPlanDescription(planItem, name);
        PlanModelRunLocalServiceUtil.createPlanModelRun(planItem);
        PlanMetaLocalServiceUtil.createPlanMeta(planItem, planTypeId);
        PlanPositionsLocalServiceUtil.createPlanPositions(planItem);

        // create community, Message Boards category

        /* update/create all attributes */
        // planItem.updateAllAttributes();
        planItem.updateAttribute(Attribute.CREATOR.name());
        planItem.updateAttribute(Attribute.NAME.name());
        planItem.updateAttribute(Attribute.DESCRIPTION.name());
        planItem.updateAttribute(Attribute.CREATE_DATE.name());
        planItem.updateAttribute(Attribute.PUBLISH_DATE.name());
        planItem.updateAttribute(Attribute.VOTES.name());

        planItem.updateAttribute(Attribute.SUPPORTERS.name());
        planItem.updateAttribute(Attribute.IS_PLAN_OPEN.name());
        planItem.updateAttribute(Attribute.SEEKING_ASSISTANCE.name());
        planItem.updateAttribute(Attribute.STATUS.name());

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
            
        long type = basePlan.getPlanTypeId();
        if (contestPhase.getContest().getPlanType().getPlanTypeId() != type) {
            _log.error("Cannot create plan of type " + type + " for contest phase "
                    + contestPhase.getContestPhaseName());
        }

        PlanItem plan = createPlan(contestPhase.getContest().getPlanType().getPlanTypeId(), authorId);

        plan.setUpdated(basePlan.getUpdated());
        plan.store();

        plan.getPlanMeta().setContestPhase(contestPhase.getContestPhasePK());
        plan.getPlanMeta().setModelId(basePlan.getPlanMeta().getModelId());
        PlanMetaLocalServiceUtil.updatePlanMeta(plan.getPlanMeta());
        PlanDescription description = PlanDescriptionLocalServiceUtil.getCurrentForPlan(plan);
        PlanModelRun planModelRun = PlanModelRunLocalServiceUtil.getCurrentForPlan(plan);
        PlanPositions planPositions = PlanPositionsLocalServiceUtil.getCurrentForPlan(plan);

        initPlan(plan);

        // copy description
        description.setDescription(basePlan.getDescription());
        description.setName(basePlan.getName());
        description.store();

        // copy scenario id
        // check if there is a scenario with given ID
        try {
            if (basePlan.getScenarioId() != null
                    && CollaboratoriumModelingService.repository().getScenario(basePlan.getScenarioId()) != null) {
                planModelRun.setScenarioId(basePlan.getScenarioId());

            }
        } catch (Throwable e) {
            // ignore, no such scenario
            _log.error("Can't find scenario with id: " + basePlan.getScenarioId(), e);
        }
        planModelRun.store();

        // copy positions
        planPositions.setPositionsIds(basePlan.getPositionsIds());
        planPositions.store();

        if (basePlan.getScenarioId() != null) {
            // update all attributes
            plan.updateAllAttributes();
        }
        // update only attributes related to new values

        plan.updateAttribute(Attribute.DESCRIPTION.name());
        plan.updateAttribute(Attribute.NAME.name());
        plan.updateAttribute(Attribute.POSITIONS.name());
        plan.updateAttribute(Attribute.LAST_MOD_DATE.name());
        plan.updateAttribute(Attribute.IS_PLAN_OPEN.name());
        plan.updateAttribute(Attribute.SEEKING_ASSISTANCE.name());
        plan.updateAttribute(Attribute.STATUS.name());

        if (basePlan.getOpen()) {
            plan.getPlanMeta().setOpen(true);
            plan.getPlanMeta().store();
        }

        // set abstract and pitch
        Attribute[] attributesToCopy = { Attribute.ABSTRACT, Attribute.LAST_MOD_DATE, Attribute.SCRAPBOOK,
                Attribute.SUBREGION, Attribute.REGION, Attribute.SEEKING_ASSISTANCE, Attribute.IS_PLAN_OPEN };

        for (Attribute attr : attributesToCopy) {
            PlanAttribute pa = basePlan.getPlanAttribute(attr.name());
            if (pa != null) {
                plan.setAttribute(pa.getAttributeName(), pa.getAttributeValue());
            }
        }

        return plan;
    }

    private void initPlan(PlanItem plan) throws PortalException, SystemException {
        User user = UserLocalServiceUtil.getUser(plan.getUpdateAuthorId());
        //PermissionCheckerUtil.setThreadValues(user);

        ServiceContext groupServiceContext = new ServiceContext();
        groupServiceContext.setUserId(plan.getUpdateAuthorId());

        // in order to prevent group name conflicts,
        // add timestamp and random long value to plan name when setting group
        // name
        Random rand = new Random();
        String groupName = plan.getName() + "_" + System.currentTimeMillis() + "_" + rand.nextLong();
        Group group = null;
        try {
            group = GroupServiceUtil.addGroup(plan.getName(), String.format(DEFAULT_GROUP_DESCRIPTION, groupName),
                    GroupConstants.TYPE_SITE_RESTRICTED, null, true, true, groupServiceContext);
        } catch (Exception e) {
            System.err.println("Got error " + e.getMessage());
            e.printStackTrace();
            return;
        }
        Long parentCategoryId = 0L;
        DiscussionCategoryGroup categoryGroup = DiscussionCategoryGroupLocalServiceUtil
                .createDiscussionCategoryGroup(plan.getName() + " discussion");

        categoryGroup.setUrl("/web/guest/plans/-/plans/contestId/" + plan.getContest().getContestPK() + "/planId/"
                + plan.getPlanId() + "#plans=tab:comments");
        
        categoryGroup.store();

        DiscussionCategory category = categoryGroup.addCategory("General discussion", null,
                UserLocalServiceUtil.getUser(plan.getAuthorId()));

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
        PlanMeta planMeta = plan.getPlanMeta();

        planMeta.setCategoryGroupId(categoryGroup.getId());
        planMeta.setPlanGroupId(group.getGroupId());
        planMeta.store();
    }

    public List<PlanItem> getPlans() throws SystemException {
        return this.planItemFinder.getPlans();
    }

    public List<PlanItem> getPlansInContestPhase(ContestPhase contestPhase) throws SystemException, PortalException {

        
        return this.getPlans(Collections.emptyMap(), Collections.emptyMap(), null, contestPhase, 0, Integer.MAX_VALUE,
                "", "", false);
    }

    public PlanItem getPlan(Long planId) throws NoSuchPlanItemException, SystemException {
        return this.planItemFinder.findLatestVersion(planId);
    }

    public List<PlanItem> getPlans(Map sessionMap, Map requestMap, PlanType planType, ContestPhase phase, int start,
            int end, final String sortColumn, String sortDirection) throws SystemException, PortalException {
        return getPlans(sessionMap, requestMap, planType, phase, start, end, sortColumn, sortDirection, true);
    }

    public List<PlanItem> getPlans(Map sessionMap, Map requestMap, PlanType planType, ContestPhase phase, int start,
            int end, final String sortColumn, String sortDirection, boolean applyFilters) throws SystemException,
            PortalException {
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
            plans = new ArrayList(planItemFinder.getPlansForPhase(phase.getContestPhasePK()));
        } else {
            for (PlanItem planItem : planItemFinder.getPlans()) {
                if (planType == null
                        || (planItem.getPlanTypeId() != null && planItem.getPlanTypeId().equals(
                                planType.getPlanTypeId()))) {
                    plans.add(planItem);
                }
            }
        }

        if (planType == null && phase != null) {
            planType = phase.getContest().getPlanType();
        }

        if (sortColumn != null || sortColumn.trim().length() > 0) {
            final int directionModifier = sortDirection.equals("DESC") ? -1 : 1;
            Collections.sort(plans, new Comparator<PlanItem>() {

                @Override
                public int compare(PlanItem arg0, PlanItem arg1) {
                    try {
                        Comparable val1 = null;
                        Comparable val2 = null;
                        PlanAttribute plan1Attr = arg0.getPlanAttribute(sortColumn);
                        PlanAttribute plan2Attr = arg1.getPlanAttribute(sortColumn);

                        if (plan1Attr != null) {
                            val1 = (Comparable) plan1Attr.getTypedValue();
                        }
                        if (plan2Attr != null) {
                            val2 = (Comparable) plan2Attr.getTypedValue();

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
            if ((planType == null || (planItem.getPlanTypeId() != null && planItem.getPlanTypeId().equals(
                    planType.getPlanTypeId())))
                    && (planItem.getPlanMeta().getContestPhase() != null && phasesIds.contains(planItem.getPlanMeta()
                            .getContestPhase()))) {
                plans.add(planItem);
            }
        }

        if (planType == null && phases != null && !phases.isEmpty()) {
            planType = phases.get(0).getContest().getPlanType();
        }

        final int directionModifier = sortDirection.equals("DESC") ? -1 : 1;
        Collections.sort(plans, new Comparator<PlanItem>() {

            @Override
            public int compare(PlanItem arg0, PlanItem arg1) {
                try {
                    PlanAttribute plan1Attr = arg0.getPlanAttribute(sortColumn);
                    PlanAttribute plan2Attr = arg1.getPlanAttribute(sortColumn);
                    Comparable val1 = (Comparable) (plan1Attr != null ? plan1Attr.getTypedValue() : null);
                    Comparable val2 = (Comparable) (plan2Attr != null ? plan2Attr.getTypedValue() : null);
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
        for (ContestPhase phase : c.getPhases()) {
            for (PlanItem item : phase.getPlans()) {
                if (item.getName().equals(planName))
                    return false;
            }
        }
        return true;
    }

    public List<PlanItem> applyFilters(Map sessionMap, Map requestMap, PlanType planType, List<PlanItem> plans)
            throws PortalException, SystemException {
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
        return findPlansForOntologyTerms(fa.getTerms());
    }

    public List<PlanItem> findPlansForOntologyTerms(OntologyTerm...terms) throws NoSuchPlanItemException, SystemException {
        return findPlansForOntologyTerms(Arrays.asList(terms));
    }
    
    public List<PlanItem> findPlansForOntologyTerms(List<OntologyTerm> terms) throws NoSuchPlanItemException, SystemException {
        Set<Long> ids = null;
        
        for (OntologyTerm term: terms) {
            List<Long> tmp = term.findTagedIdsForClass(PlanItem.class);
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
        planItemFinder.clearPhaseCache(plan.getContestPhase().getContestPhasePK());
        
    }

}

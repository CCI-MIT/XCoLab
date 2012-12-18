/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import com.ext.portlet.plans.PlanConstants.Columns;
import com.ext.portlet.plans.model.PlanColumnSettings;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.PlanTypeColumn;
import com.ext.portlet.plans.model.PlanVote;
import com.ext.portlet.plans.model.PlansFilter;
import com.ext.portlet.plans.model.PlansFilterPosition;
import com.ext.portlet.plans.model.PlansUserSettings;
import com.ext.portlet.plans.service.PlanColumnSettingsLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.ext.portlet.plans.service.PlansFilterLocalServiceUtil;
import com.ext.portlet.plans.service.PlansFilterPositionLocalServiceUtil;
import com.ext.portlet.plans.service.PlansUserSettingsLocalServiceUtil;
import com.ext.portlet.plans.service.persistence.PlanVotePK;
import com.ext.portlet.plans.service.persistence.PlansFilterPositionPK;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

/**
 * Helper class responsible for performing custom queries on PlanLocalService.
 * <p/>
 * In version 1.0 it supports following queries:
 * <ul>
 * <li>get list of plans that have "published" flag set to given value</li>
 * <li>count how many plans have "published" flag set to given value</li>
 * </ul>
 * <p/>
 * This class can't be instantiated. It is thread safe as it has no state.
 * <p/>
 * version 1.1: added methods for plan positions retrieval
 * version 1.2: modified methods responsible for Plans retrieval to support
 * filtering, added methods for retrieval of user vote position column
 * configuration, plan filters
 * version 1.3: refactored to bring "add-plan" into this class
 *
 * @author janusz.p, janusz.p, josh i
 * @version 1.3
 * @since 1.0
 */
public class PlanLocalServiceHelper {

    /**
     * Default community permissions for community forum category.
     */
    public static final String[] DEFAULT_CATEGORY_COMMUNITY_PERMISSIONS = {ActionKeys.VIEW, ActionKeys.SUBSCRIBE,
            ActionKeys.REPLY_TO_MESSAGE, ActionKeys.ADD_MESSAGE};

    /**
     * Default guest permissions for community forum category.
     */
    public static final String[] DEFAULT_CATEGORY_GUEST_PERMISSIONS = {ActionKeys.VIEW, ActionKeys.SUBSCRIBE};

    /**
     * Default description of group working on a plan.
     */
    public static final String DEFAULT_GROUP_DESCRIPTION = "Group working on plan %s";

    /**
     * Default forum category name.
     */
    public static final String DEFAULT_FORUM_CATEGORY_NAME = "General discussion";

    /**
     * Default forum category description.
     */
    public static final String DEFAULT_FORUM_CATEGORY_DESCRIPTION = "General discussion about plan %s";

    /**
     * Plan position state - position already exists.
     */
    public static final int EXISTS = 0;

    /**
     * Plan position state - new position.
     */
    public static final int NEW = 1;

    /**
     * Plan position state - position should be deleted.
     */
    public static final int DELETE = 2;

    /**
     * Name of column that represents value of PlanPosition planId field.
     */
    public static final String POSITION_PLAN_ID = "primaryKey.planId";

    /**
     * Name of column that represents value of PlanPosition planId field.
     */
    public static final String POSITION_POSITION_ID = "primaryKey.positionId";

    /**
     * Name of column that represents value of Plan's "published" field.
     */
    public static final String PLAN_PUBLISHED = "published";


    /**
     * Name of column that represents value of PlansFilterPositions userId field.
     */
    public static final String PLANS_FILTER_USER_ID = "primaryKey.userId";

    private static final String PLANS_FILTER_PLAN_TYPE_ID = "primaryKey.planTypeId";

    /**
     * Private constructor to prevent users from instantiating this class.
     */
    private PlanLocalServiceHelper() {
    }
/*
    public static Plan addPlan(ActionRequest actionRequest) throws PortalException, SystemException {

        String name = ParamUtil.getString(actionRequest, PlanConstants.PLAN_NAME_FIELD);
        Long planTypeId = ParamUtil.getLong(actionRequest, PlanConstants.PLAN_TYPE_ID);

        Date now = new Date();
        long climatePlanId = CounterLocalServiceUtil.increment(Plan.class.getName());
        Plan plan = PlanLocalServiceUtil.createPlan(climatePlanId);
        plan.setName(name);
        plan.setPlanTypeId(planTypeId);

        return initializePlan(plan, actionRequest);
    }
    */


    /**
     * Retrieves list of plans. First this method tries to get filters defined
     * by user, after that uses appropriate method from PlanLocalServiceUtil
     * to get either filtered or not filtered plans.
     *
     * @param request       portlet request
     * @param start         position of first plan to be retrieved
     * @param end           position of last plan to be retrieved
     * @param sortColumn    column over which sorting should be performed
     * @param sortDirection direction of sorting
     * @return list of retrieved plans
     * @throws Exception
     */
    /*
    public static List<Plan> getPlans(PortletRequest request, long planTypeId, int start, int end,
                                      String sortColumn, String sortDirection) throws Exception {
        // get filter and retrieve plans
        PlansUserSettings planUserSettings = getPlanUserSettings(request, planTypeId);
        List<Plan> plans = null;
        if (planUserSettings == null || !planUserSettings.getFilterEnabled()) {
            plans = PlanLocalServiceUtil.getPlans(planTypeId, start, end, sortColumn, sortDirection);
        } else {
            plans = PlanLocalServiceUtil.getFilteredPlans(planUserSettings, start, end, sortColumn, sortDirection);
        }

        // get plans positions and assign them to appropriate plans
        Map<Long, MBMessage> positionsMap = null;//getPositionsMap(request, Long.parseLong(getDefaultTopicId(request)));
        Map<Long, List<MBMessage>> plansPositions = new HashMap<Long, List<MBMessage>>();

        List<PlanPosition> planPositionIds = PlanLocalServiceUtil.getPlansPositions(plans);

        for (PlanPosition planPositionId : planPositionIds) {
            if (!plansPositions.containsKey(planPositionId.getPlanId())) {
                plansPositions.put(planPositionId.getPlanId(), new ArrayList<MBMessage>());
            }
            if (positionsMap.containsKey(planPositionId.getPositionId())) {
                plansPositions.get(planPositionId.getPlanId()).add(positionsMap.get(planPositionId.getPositionId()));
            }
        }

        for (Plan plan : plans) {
            if (plansPositions.containsKey(plan.getPlanId())) {
                plan.setPositions(plansPositions.get(plan.getPlanId()));
            }
        }

        return plans;

    }
    */

    /**
     * Returns count of plans in current data set (if user has enabled
     * filtering, before counting filters are applied).
     *
     * @param request render request
     * @return returns plans count in current data set
     * @throws Exception
     */
    /*
    public static int getPlansCount(PortletRequest request, long planTypeId) throws Exception {
        PlansUserSettings planUserSettings = getPlanUserSettings(request, planTypeId);
        if (planUserSettings == null || !planUserSettings.getFilterEnabled()) {
            return PlanLocalServiceUtil.countPlans(planTypeId);
        } else {
            return PlanLocalServiceUtil.countFilteredPlans(planUserSettings);
        }
    }
    */

    /**
     * Method responsible for retrieval of position of a plan user has voted for.
     *
     * @param request       render request
     * @param sortColumn    column over which sorting should be performed
     * @param sortDirection direction of sorting
     * @return position of a plan user has voted on
     * @throws Exception
     */
    /*
    public static int getUserVotePosition(PortletRequest request, long planTypeId, String sortColumn,
                                          String sortDirection) throws Exception {
        PlansUserSettings planUserSettings = getPlanUserSettings(request, planTypeId);
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long userId = themeDisplay.getUserId();

        if (planUserSettings == null || !planUserSettings.getFilterEnabled()) {
            return PlanLocalServiceUtil.getUserVotePosition(userId, sortColumn, sortDirection);
        } else {
            return PlanLocalServiceUtil.getFilteredUserVotePosition(planUserSettings, userId, sortColumn, sortDirection);
        }
    }
    */

    /**
     * Returns list of positions referenced by a plan.
     *
     * @param planId ID of a plan for which positions should be retrieved
     * @return list of MBMessage objects representing referenced positions
     * @throws PortalException passed up in case of framework error
     * @throws SystemException passed up in case of framework error
     */
    /*
    public static List<MBMessage> getPlanPositions(long planId) throws PortalException, SystemException {
        // load all plan positions
        List<MBMessage> positions = new ArrayList<MBMessage>();

        // create custom query and execute it
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PlanPosition.class);
        Criterion criterionPublished = RestrictionsFactoryUtil.eq(POSITION_PLAN_ID, planId);
        dynamicQuery.add(criterionPublished);

        List<Object> ret = PlanLocalServiceUtil.dynamicQuery(dynamicQuery);

        for (Object positionObj : ret) {
            PlanPosition position = (PlanPosition) positionObj;
            try {
                MBMessage positionMessage = MBMessageLocalServiceUtil.getMessage(position.getPositionId());
                positions.add(positionMessage);
            } catch (NoSuchMessageException e) {
                // ignore
            }
        }

        return positions;
    }
    */

    /*
    public static List<PlanPosition> queryPlanPositions(long planId) throws PortalException, SystemException {
        // load all plan positions
        
        // create custom query and execute it
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PlanPosition.class);
        Criterion criterionPublished = RestrictionsFactoryUtil.eq(POSITION_PLAN_ID, planId);
        dynamicQuery.add(criterionPublished);
        List<PlanPosition> result  = new ArrayList<PlanPosition>();
        for (Object p:PlanLocalServiceUtil.dynamicQuery(dynamicQuery)) {
           result.add((PlanPosition)p);
        }
        return result;
    }*/

    /**
     * Returns list of positions referenced by a plan.
     *
     * @param planId ID of a plan for which positions should be retrieved
     * @return list of MBMessage objects representing referenced positions
     * @throws PortalException passed up in case of framework error
     * @throws SystemException passed up in case of framework error
     */
    /*public static List<DebateItem>  getPlanPositionsDebateRevision(long planId) throws PortalException, SystemException {
        // load all plan positions
        List<DebateItem> positions = new ArrayList<DebateItem>();

        // create custom query and execute it
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PlanPosition.class);
        Criterion criterionPublished = RestrictionsFactoryUtil.eq(POSITION_PLAN_ID, planId);
        dynamicQuery.add(criterionPublished);

        List<Object> ret = PlanLocalServiceUtil.dynamicQuery(dynamicQuery);

        for (Object positionObj : ret) {
            PlanPosition position = (PlanPosition) positionObj;
            DebateItem item = DebateItemLocalServiceUtil.getLastItem(position.getPositionId());
            if (item == null) continue;
            if (DebateItemStatus.valueOf(item.getStatus()) == DebateItemStatus.ACTIVE) {
                positions.add(item);
                System.err.println("Debate is "+item.getDebate());
            }

        }

        return positions;
    }*/

    /**
     * Returns list of PlanPositions for given position ID.
     *
     * @param positionId ID of a plan position
     * @return list of PlanPosition objects
     * @throws PortalException passed up in case of framework error
     * @throws SystemException passed up in case of framework error
     */
    /*
    public static List<PlanPosition> getPlanPositionsByPositionId(long positionId)
            throws PortalException, SystemException {
        // load all plan positions
        List<PlanPosition> planPositions = new ArrayList<PlanPosition>();

        // create custom query and execute it
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PlanPosition.class);
        Criterion criterionPublished = RestrictionsFactoryUtil.eq(POSITION_POSITION_ID, positionId);
        dynamicQuery.add(criterionPublished);

        List<Object> ret = PlanLocalServiceUtil.dynamicQuery(dynamicQuery);

        for (Object positionObj : ret) {
            PlanPosition position = (PlanPosition) positionObj;
            planPositions.add(position);
        }

        return planPositions;
    }
    */

    /**
     * Returns id of default topic that should be referenced by newly created plans.
     *
     * @param request portlet request
     * @return id of default topic
     * @throws SystemException passed up in case of framework error
     */
    public static String getDefaultTopicId(PortletRequest request) throws SystemException {
        //return 401 + "";
        return getPortletPreference(request, PlanConstants.DEFAULT_TOPIC_ID);
    }

    /**
     * Returns id of default model that should be referenced by newly created plans.
     *
     * @param request portlet request
     * @return id of default model
     * @throws SystemException passed up in case of framework error
     */
    public static String getDefaultModelId(PortletRequest request) throws SystemException {
        return getPortletPreference(request, PlanConstants.DEFAULT_MODEL_ID);
    }

    /**
     * Returns portlet preference.
     *
     * @param request        portlet request
     * @param preferenceName name of preference to retrieve
     * @return preference value
     * @throws SystemException passed up in case of framework error
     */
    private static String getPortletPreference(PortletRequest request, String preferenceName) throws SystemException {
        PortletPreferences preferences = request.getPreferences();
        return preferences.getValue(preferenceName, "-1");
    }

    /**
     * Returns user settings for plans portlet (columns configuration).
     * Method first checks if there is object with user settings in current
     * session, if it is, this object is returned. If it isn't, new object
     * is created with default data.
     *
     * @param request render request
     * @return returns columns configuration
     * @throws PortalException passed up in case of framework error
     * @throws SystemException passed up in case of framework error
     */
    public static PlansUserSettings getPlanUserSettings(PortletRequest request, long planTypeId)
            throws PortalException, SystemException {
        String settingsParamName = getUserSettingsParamName(planTypeId);
        PlansUserSettings userSettings =
                (PlansUserSettings) request.getPortletSession().getAttribute(settingsParamName);
        if (userSettings == null) {
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            long userId = themeDisplay.getUserId();
            User user = UserLocalServiceUtil.getUser(userId);

            if (!user.isDefaultUser()) {
                try {
                    List tmp = PlansUserSettingsLocalServiceUtil.getPlansUserSettingses(0, 9999);
                    userSettings = PlansUserSettingsLocalServiceUtil.getByUserIdPlanTypeId(userId, planTypeId);
                    if (userSettings != null) {
                        setPlansFilterPositionsIds(userSettings);
                    }

                } catch (NoSuchPlansUserSettingsException e) {
                    System.out.print(e);
                }
            }

            if (userSettings == null || user.isDefaultUser()) {
                userSettings = PlansUserSettingsLocalServiceUtil.createPlansUserSettings(0L);
                userSettings.setPlanTypeId(planTypeId);
                userSettings.setUserId(userId);
                userSettings.setFilterEnabled(false);
                userSettings.setFilterPositionsAll(false);

                PlanType planType = PlanTypeLocalServiceUtil.getPlanType(planTypeId);
                for (PlanTypeColumn planTypeColumn : PlanTypeLocalServiceUtil.getColumns(planType)) {
                    PlanColumnSettings settings = PlanColumnSettingsLocalServiceUtil.createPlanColumnSettings(0L);
                    settings.setColumnName(planTypeColumn.getColumnName());
                    settings.setPlanUserSettingsId(userSettings.getPlanUserSettingsId());
                    settings.setVisible(planTypeColumn.getVisibleByDefault());


                    userSettings.addColumnSettings(settings);
                }
                /*
                for (Columns c:Columns.defaults()) {
                	c.setUserSetting(userSettings, true);
                }*/
//                userSettings.setShowName(true);
//                userSettings.setShowDate(true);
//                userSettings.setShowTemperatureChange(true);
//                userSettings.setShowCO2(true);
//                userSettings.setShowMitigationCost(true);
//                userSettings.setShowDamageCost(true);
//              //  userSettings.setShowPositions(true);
//                userSettings.setShowVotes(true);
//                userSettings.
            }
            request.getPortletSession().setAttribute(settingsParamName, userSettings);
        }

        return userSettings;
    }

   
    /**
     * Returns name of session parameter with columns configuration for
     * table with published/unpublished plans.
     * retrieved
     *
     * @return name of session parameter with column configuration
     */
    private static String getUserSettingsParamName(long planTypeId) {
        return PlanConstants.USER_SETTINGS + String.valueOf(planTypeId);
    }

    /**
     * Method adds positions and questions related to topic to the request.
     *
     * @param request render request
     * @param topicId ID of topic from which questions and positions should be taken
     * @throws SystemException passed up in case of any error in framework
     * @throws PortalException passed up in case of any error in framework
     */
    /*
    public static void addQuestionsAndPositions(PortletRequest request, long topicId)
            throws SystemException, PortalException {
        int issuesCount = DebatesUtil.getIssuesCount(request, topicId);

        Map<MBMessage, List<MBMessage>> questionPositions = new HashMap<MBMessage, List<MBMessage>>();
        List<MBMessage> questions = new ArrayList<MBMessage>();

        List<MBCategory> questionCategories = DebatesUtil.getIssues(request, topicId, 0, issuesCount);
        for (MBCategory questionCategory : questionCategories) {
            MBCategory debateCategory = DebatesUtil.getSubcategory(questionCategory.getCategoryId(),
                    DebatesConstants.ISSUE_DEBATE_CATEGORY_NAME);
            MBMessage question = DebatesUtil.getDebateMessage(debateCategory.getCategoryId());
            List<MBMessage> positions = DebatesUtil.getChildMessages(question);

            questions.add(question);
            questionPositions.put(question, positions);
        }

        request.setAttribute(PlanConstants.QUESTIONS, questions);
        request.setAttribute(PlanConstants.QUESTION_POSITIONS, questionPositions);
    }
    */

    /*
    public static void addQuestionsAndPositionsRevision(PortletRequest request, long topicId)
            throws SystemException, PortalException {
        List<Debate> debates = DebateCategoryLocalServiceUtil.getDebates(topicId);
        List<DebateItem> questions = new ArrayList<DebateItem>();
        Map<DebateItem, List<DebateItem>> questionPositions = new HashMap<DebateItem, List<DebateItem>>();

        for (Debate d : debates) {
            questions.add(d.getCurrentRoot());
            questionPositions.put(d.getCurrentRoot(), d.getCurrentRoot().getChildren());
        }

        request.setAttribute(PlanConstants.QUESTIONS, questions);
        request.setAttribute(PlanConstants.QUESTION_POSITIONS, questionPositions);
    }
    */

    /**
     * Retrieves map of all positions from given topic keyed by their id's.
     *
     * @param request render request
     * @param topicId id of topic for which positions should be retrieved
     * @return map of positions
     * @throws SystemException passed up in case of framework error
     * @throws PortalException passed up in case of framework error
     */
    /*
    public static Map<Long, MBMessage> getPositionsMap(PortletRequest request, long topicId)
            throws SystemException, PortalException {
        int issuesCount = DebatesUtil.getIssuesCount(request, topicId);

        Map<Long, MBMessage> positions = new HashMap<Long, MBMessage>();

        List<MBCategory> questionCategories = DebatesUtil.getIssues(request, topicId, 0, issuesCount);
        for (MBCategory questionCategory : questionCategories) {
            MBCategory debateCategory = DebatesUtil.getSubcategory(questionCategory.getCategoryId(),
                    DebatesConstants.ISSUE_DEBATE_CATEGORY_NAME);
            MBMessage question = DebatesUtil.getDebateMessage(debateCategory.getCategoryId());
            List<MBMessage> questionPositions = DebatesUtil.getChildMessages(question);

            for (MBMessage position : questionPositions) {
                positions.put(position.getMessageId(), position);
            }
        }
        return positions;
    }
     */
    /**
     * Returns filter settings for plans table with published/unpublished plans.
     * Method first checks if there is object with filters in current
     * session, if it is, this object is returned. If it isn't, new object
     * is created with default data.
     *
     * @param request render request
     * @param published publish status for table which filters configuration is
     *      retrieved
     * @return returns filters configuration
     * @throws PortalException passed up in case of framework error
     * @throws SystemException passed up in case of framework error
     */
    /*
    public static PlansFilter getFilter(PortletRequest request, long planTypeId)
        throws PortalException, SystemException {
        String filterParamName = getFilterParamName(planTypeId);
        PlansFilter plansFilter = (PlansFilter) request.getPortletSession().getAttribute(filterParamName);
        if (plansFilter == null) {
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            long userId = themeDisplay.getUserId();
            User user = UserLocalServiceUtil.getUser(userId);
            PlansFilterPK plansFilterPK = new PlansFilterPK();
            plansFilterPK.setPlanTypeId(planTypeId);
            plansFilterPK.setUserId(userId);

            if (!user.isDefaultUser()) {
                try {
                    plansFilter = PlansFilterLocalServiceUtil.getPlansFilter(plansFilterPK);
                    setPlansFilterPositionsIds(plansFilter);
                    request.getPortletSession().setAttribute(filterParamName, plansFilter);
                } catch (NoSuchFilterException e) {
                    // ignore
                }
            }
        }

        return plansFilter;
    }
*/

    /**
     * Method responsible for storing filters configuration. Configuration is
     * stored in session also, if user is authorized then configuration is
     * persisted.
     *
     * @param request     render request
     * @param plansFilter filters configuration that should be stored
     * @throws PortalException passed up in case of framework error
     * @throws SystemException passed up in case of framework error
     */
    public static void saveFilter(PortletRequest request, PlansFilter plansFilter)
            throws SystemException, PortalException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long userId = themeDisplay.getUserId();
        User user = UserLocalServiceUtil.getUser(userId);
        if (!user.isDefaultUser()) {
            storePlansFilterPositionsIds(plansFilter);
            PlansFilterLocalServiceUtil.updatePlansFilter(plansFilter);

        }
        String filterParamName = getFilterParamName(plansFilter.getPlanTypeId());
        request.getPortletSession().setAttribute(filterParamName, plansFilter);
    }

    public static void saveFilter(PortletRequest request, PlansUserSettings plansFilter) throws SystemException,
            PortalException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long userId = themeDisplay.getUserId();
        User user = UserLocalServiceUtil.getUser(userId);
        if (!user.isDefaultUser()) {
            storePlansFilterPositionsIds(plansFilter);
            PlansUserSettingsLocalServiceUtil.updatePlansUserSettings(plansFilter);

        }
        String filterParamName = getFilterParamName(plansFilter.getPlanTypeId());
        request.getPortletSession().setAttribute(filterParamName, plansFilter);
    }

    /**
     * Returns name of session parameter with filters configuration for
     * table with published/unpublished plans.
     *
     * @return name of session parameter with filters configuration
     */
    private static String getFilterParamName(long planTypeId) {
        return PlanConstants.FILTER_NAME + String.valueOf(planTypeId);
    }

    /**
     * Retrieves PlanFilter positions id's from database and associates them
     * with passed filter.
     *
     * @throws SystemException passed up in case of framework error
     */
    private static void setPlansFilterPositionsIds(PlansUserSettings planUserSettings) throws SystemException {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PlansFilterPosition.class);
        Criterion criterionPlanType = RestrictionsFactoryUtil.eq(PLANS_FILTER_PLAN_TYPE_ID, planUserSettings.getPlanTypeId());
        Criterion criterionUserId = RestrictionsFactoryUtil.eq(PLANS_FILTER_USER_ID, planUserSettings.getUserId());
        dynamicQuery.add(criterionPlanType);
        dynamicQuery.add(criterionUserId);

        List<Object> positionsObjects = PlansFilterPositionLocalServiceUtil.dynamicQuery(dynamicQuery);
        List<Long> positionsIds = new ArrayList<Long>();
        for (Object positionObj : positionsObjects) {
            PlansFilterPosition plansFilterPosition = (PlansFilterPosition) positionObj;
            positionsIds.add(plansFilterPosition.getPositionId());
        }
        planUserSettings.setPositionsIds(positionsIds);
    }

    /**
     * Persist associations between filter and positions.
     *
     * @param plansFilter plan filter
     * @throws SystemException passed up in case of framework error
     */
    private static void storePlansFilterPositionsIds(PlansFilter plansFilter) throws SystemException {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PlansFilterPosition.class);
        Criterion criterionPlanType = RestrictionsFactoryUtil.eq(PLANS_FILTER_PLAN_TYPE_ID, plansFilter.getPlanTypeId());
        Criterion criterionUserId = RestrictionsFactoryUtil.eq(PLANS_FILTER_USER_ID, plansFilter.getUserId());
        dynamicQuery.add(criterionPlanType);
        dynamicQuery.add(criterionUserId);

        // delete current associations
        List<Object> positionsObjects = PlansFilterPositionLocalServiceUtil.dynamicQuery(dynamicQuery);
        for (Object positionObj : positionsObjects) {
            PlansFilterPosition plansFilterPosition = (PlansFilterPosition) positionObj;
            PlansFilterPositionLocalServiceUtil.deletePlansFilterPosition(plansFilterPosition);
        }

        // add new associations
        /*
        for (Long positionId : plansFilter.getPositionsIds()) {
            PlansFilterPositionPK plansFilterPositionPK = new PlansFilterPositionPK();
            plansFilterPositionPK.setPlanTypeId(plansFilter.getPlanTypeId());
            plansFilterPositionPK.setUserId(plansFilter.getUserId());
            plansFilterPositionPK.setPositionId(positionId);

            PlansFilterPosition plansFilterPosition = PlansFilterPositionLocalServiceUtil.createPlansFilterPosition(plansFilterPositionPK);
            PlansFilterPositionLocalServiceUtil.updatePlansFilterPosition(plansFilterPosition);
        }
        */
    }

    private static void storePlansFilterPositionsIds(PlansUserSettings plansFilter) throws SystemException {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PlansFilterPosition.class);
        Criterion criterionPlanType = RestrictionsFactoryUtil.eq(PLANS_FILTER_PLAN_TYPE_ID, plansFilter.getPlanTypeId());
        Criterion criterionUserId = RestrictionsFactoryUtil.eq(PLANS_FILTER_USER_ID, plansFilter.getUserId());
        dynamicQuery.add(criterionPlanType);
        dynamicQuery.add(criterionUserId);

        // delete current associations
        List<Object> positionsObjects = PlansFilterPositionLocalServiceUtil.dynamicQuery(dynamicQuery);
        for (Object positionObj : positionsObjects) {
            PlansFilterPosition plansFilterPosition = (PlansFilterPosition) positionObj;
            PlansFilterPositionLocalServiceUtil.deletePlansFilterPosition(plansFilterPosition);
        }

        // add new associations
        for (Long positionId : plansFilter.getPositionsIds()) {
            PlansFilterPositionPK plansFilterPositionPK = new PlansFilterPositionPK();
            plansFilterPositionPK.setPlanTypeId(plansFilter.getPlanTypeId());
            plansFilterPositionPK.setUserId(plansFilter.getUserId());
            plansFilterPositionPK.setPositionId(positionId);

            PlansFilterPosition plansFilterPosition = PlansFilterPositionLocalServiceUtil.createPlansFilterPosition(plansFilterPositionPK);
            PlansFilterPositionLocalServiceUtil.updatePlansFilterPosition(plansFilterPosition);
        }
    }


    /**
     * Method adds plan vote to request object.
     *
     * @param request request that should be populated with users plan vote
     * @throws PortalException passed up in case of framework exception
     * @throws SystemException passed up in case of framework exception
     */
    public static void addPlanVote(PortletRequest request) throws PortalException, SystemException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long userId = themeDisplay.getUserId();
        try {
            PlanVote planVote = PlanVoteLocalServiceUtil.getPlanVote(new PlanVotePK(userId, 1L));
            request.setAttribute(PlanConstants.VOTE, planVote);
        } catch (NoSuchPlanVoteException e) {
            // ignore
        }
    }


    /**
     * Returns plan that is associated with given group.
     *
     * @param groupId ID of group that is associated with searched plan
     * @return a Plan
     * @throws NoSuchPlanException if there is no plan associated with given id
     * @throws SystemException     passed up in case of framework error
     * @throws NoSuchPlanException
     */
    /*
    public static Plan getPlanByGroupId(long groupId) throws SystemException, NoSuchPlanException {

        // create custom query and execute it
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Plan.class);
        Criterion criterionMessageId = RestrictionsFactoryUtil.eq("childGroupId", groupId);
        dynamicQuery.add(criterionMessageId);

        List<Object> plan = PlanLocalServiceUtil.dynamicQuery(dynamicQuery);
        if (plan.size() != 1) {
            throw new NoSuchPlanException("Can't find plan associated with group " + groupId);
        }

        return (Plan) plan.get(0);
    }
    */

    public static PlanType getDefaultPlanType() throws NoSuchPlanTypeException, SystemException {
        return PlanTypeLocalServiceUtil.getDefaultPlanType();
    }

    public static PlanColumnSettings createColumnSettings(PlansUserSettings settings, Columns column) throws PortalException, SystemException {
        PlanColumnSettings columnSettings = PlanColumnSettingsLocalServiceUtil.createPlanColumnSettings(0L);
        columnSettings.setColumnName(String.valueOf(column));
        columnSettings.setPlanUserSettingsId(settings.getPlanUserSettingsId());

        PlanType planType = PlanTypeLocalServiceUtil.getPlanType(settings.getPlanTypeId());
        List<PlanTypeColumn> xyz = PlanTypeLocalServiceUtil.getColumns(planType);

        for (PlanTypeColumn typeColumn : PlanTypeLocalServiceUtil.getColumns(planType)) {
            if (columnSettings.getColumnName().equals(typeColumn.getColumnName())) {
                columnSettings.setVisible(typeColumn.getVisibleByDefault());
                settings.addColumnSettings(columnSettings);
                return columnSettings;
            }
        }

        // if we have reached this place then there is no definition of requested column
        throw new PortalException("Can't find definition of column " + column.toString() + " in plan type with id " + planType.getPlanTypeId());
    }
}
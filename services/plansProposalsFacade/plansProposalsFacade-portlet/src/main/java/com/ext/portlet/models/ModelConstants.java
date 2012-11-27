/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models;

/**
 * Helper class with constant values that are used across Plans Portlet.
 *
 * version 1.1: added constants for integration plans and models
 * version 1.2: added constants required for new "View plans" page
 *
 * @author janusz.p, janusz.p
 *
 * @version 1.2
 * @since 1.0
 */
public class ModelConstants {
    /**
     * Name of actions tab in view plan tab panel.
     */
    public static final String ACTIONS_TAB = "actions";

    /**
     * Add plan action for resource permissions management.
     */
    public static final String ADD_PLAN = "ADD_PLAN";

    /**
     * Add positions action for resource permissions management.
     */
    public static final String ADD_POSITION = "ADD_POSITION";

    /**
     * Value for Positions filter operator denoting that all positions should be
     * referenced by a plan.
     */
    public static final String ALL = "all";

    /**
     * Value for Positions filter operator denoting that any position should be
     * referenced by a plan.
     */
    public static final String ANY = "any";

    /**
     * Name of request parameter with CO2.
     */
    public static final String CO2 = "AtmosphericCO2Concentration";

    /**
     * Name of request parameter with CO2 max.
     */
    public static final String CO2_MAX = "CO2max";

    /**
     * Name of request parameter with CO2 min.
     */
    public static final String CO2_MIN = "CO2min";

    /**
     * Path to jsp file containing portlet configuration.
     */
    public static final String CONFIGURATION_JSP = "/html/portlet/ext/plans/configuration.jsp";

    /**
     * Forward path for configuring columns.
     */
    public static final String CONFIGURE_COLUMNS_FORWARD = "portlet.ext.plans.configure_columns";

    /**
     * Creator request parameter.
     */
    public static final String CREATOR = "creator";

    /**
     * Name of request parameter with damage cost average.
     */
    public static final String DAMAGE_COST_AVG = "damageCostAvg";

    /**
     * Name of request parameter with damage cost max.
     */
    public static final String DAMAGE_COST_MAX = "damageCostMax";

    /**
     * Name of request parameter with damage cost min.
     */
    public static final String DAMAGE_COST_MIN = "damageCostMin";

    /**
     * Name of request parameter with damage cost standard deviation.
     */
    public static final String DAMAGE_COST_STD_DEV = "damageCostStdDev";

    /**
     * Date request parameter.
     */
    public static final String DATE = "date";

    /**
     * Date max request parameter.
     */
    public static final String DATE_MAX = "dateMax";

    /**
     * Date min request parameter.
     */
    public static final String DATE_MIN = "dateMin";

    /**
     * A placeholder string that is used in pattern URLs. It is used to denote at which place debate
     * category id should be inserted.
     */
    public static final String DEBATE_CATEGORY_ID = "DEBATE_CATEGORY_ID";

    /**
     * Name of default model id portlet preference parameter.
     */
    public static final String DEFAULT_MODEL_ID = "defaultModelId";

    /**
     * Name of default topic request parameter.
     */
    public static final String DEFAULT_TOPIC = "defaultTopicId";

    /**
     * Name of default topic id request parameter.
     */
    public static final String DEFAULT_TOPIC_ID = "defaultTopicId";


    /**
     * Name of default topic id request parameter.
     */
    public static final String DEFAULT_MODEL_DISCUSSION_TOPIC_ID = "defaultDiscussionTopicId";

    public static final String DEFAULT_MODEL_DISCUSSION_TOPIC_NAME = "Discussions about individual models";

    /**
     * Name of description tab in view plan tab panel.
     */
    public static final String DESCRIPTION = "description";

    /**
     * Name of description tab in view plan tab panel.
     */
    public static final String DESCRIPTION_TAB = "description";

    /**
     * Name of request parameter with developed emissions.
     */
    public static final String DEVELOPED_EMISSIONS = "DevelopedFossilFuelEmissions";

    /**
     * Name of request parameter with developing A emissions.
     */
    public static final String DEVELOPING_A_EMISSIONS = "DevelopingAFossilFuelEmissions";

    /**
     * Name of request parameter with developing B emissions.
     */
    public static final String DEVELOPING_B_EMISSIONS = "DevelopingBFossilFuelEmissions";

    /**
     * Name of discussion tab in view plan tab panel.
     */
    public static final String DISCUSSION_TAB = "discussion";

    /**
     * Forward path for editing plan.
     */
    public static final String EDIT_PLAN_FORWARD = "portlet.ext.plans.edit_plan";
    /**
     * Enable filters request parameter.
     */
    public static final String ENABLE_FILTERS = "enableFilters";

    /**
     * Name of session parameter with filter for published plans.
     */
    public static final String FILTER_PUBLISHED = "filterPublished";

    /**
     * Name of session parameter with filter for unpublished plans.
     */
    public static final String FILTER_UNPUBLISHED = "filterUnpublished";

    /**
     * Global emissions request parameter.
     */
    public static final String GLOBAL_EMISSIONS = "globalEmissions";

    /**
     * A placeholder string that is used in pattern URLs. It is used to denote at which place group
     * name should be inserted (group friendly url).
     */
    public static final String GROUP_NAME_PLACEHOLDER = "GROUP_NAME_PLACEHOLDER";

    /**
     * Name of request paremeter which denotes if user vote should be highlighted.
     */
    public static final String HIGHLIGHT_VOTE = "highlightVote";

    /**
     * A placeholder string that is used in pattern URLs. It is used to denote at which place certain
     * id should be inserted (for example planId, group id).
     */
    public static final String ID_PLACEHOLDER = "ID_PLACEHOLDER";

    /**
     * Name of impacts tab in view plan tab panel.
     */
    public static final String IMPACTS_TAB = "impacts";

    /**
     * Requests parameter key that represents user plan membership status.
     */
    public static final String IS_PLAN_MEMBER = "isPlanMember";

    /**
     * Requests parameter key that represents user plan ownership status.
     */
    public static final String IS_PLAN_OWNER = "isPlanOwner";

    /**
     * Name of members tab in view plan tab panel.
     */
    public static final String MEMBERS_TAB = "members";

    /**
     * Name of request parameter with mitigation cost average.
     */
    public static final String MITIGATION_COST_AVG = "mitigationCostAvg";

    /**
     * Name of request parameter with mitigation cost max.
     */
    public static final String MITIGATION_COST_MAX = "mitigationCostMax";

    /**
     * Name of request parameter with mitigation cost min.
     */
    public static final String MITIGATION_COST_MIN = "mitigationCostMin";

    /**
     * Name of request parameter with mitigation cost standard deviation.
     */
    public static final String MITIGATION_COST_STD_DEV = "mitigationCostStdDev";

    /**
     * Requests parameter key that represents model for displaying.
     */
    public static final String MODEL_DISPLAY = "modelDisplay";

    /**
     * Request parameter key with model id.
     */
    public static final String MODEL_ID = "modelId";

    /**
     * A placeholder string that is used in pattern URLs. It is used to denote at which place model
     * id should be inserted.
     */
    public static final String MODEL_ID_PLACEHOLDER = "MODEL_ID_PLACEHOLDER";

    /**
     * Name of model tab in view plan tab panel.
     */
    public static final String MODEL_TAB = "model";

    /**
     * Name request parameter.
     */
    public static final String NAME = "name";

    /**
     * Request parameter with name of column used for ordering.
     */
    public static final String ORDER_COLUMN = "orderColumn";

    /**
     * Denotes ordering direction.
     */
    public static final String ORDER_DIRECTION = "orderDirection";

    /**
     * Pager max page elements request parameter.
     */
    public static final String PAGER_MAX = "pagerMax";

    /**
     * Pager start request parameter (denotes index of first element displayed by pager).
     */
    public static final String PAGER_START = "pagerStart";

    /**
     * Requests parameter key that represents plan object.
     */
    public static final String PLAN = "plan";

    /**
     * Name of portlet preference holding plan manage membership requests URL pattern.
     */
    public static final String PLAN_ACTIONS_URL = "planActionsURL";


    /**
     * Name of parameter holding list of positions referenced by plan.
     */
    public static final String MODEL_POSITIONS = "modelPositions";


    /**
     * Name of parameter holding list of positions referenced by plan.
     */
    public static final String MODEL_QUESTION_URL = "modelQuestionURL";
    public static final String MODEL_QUESTION_URL_ACTUAL = "/web/guest/debates?p_p_id=debates&amp;p_p_lifecycle=0&amp;p_p_state=normal&amp;p_p_mode=view&amp;p_p_col_id=column-1&amp;p_p_col_count=2&amp;_debates_struts_action=%2Fext%2Fdebates%2Fview_debate&amp;_debates_categoryId=DEBATE_CATEGORY_ID";



    /**
     * Name of parameter holding list of positions referenced by plan.
     */
    public static final String MODEL_POSITION_URL = "modelPositionURL";
    public static final String MODEL_POSITION_URL_ACTUAL = "/web/guest/debates?p_p_id=debates&amp;p_p_lifecycle=0&amp;p_p_state=normal&amp;p_p_mode=view&amp;p_p_col_id=column-1&amp;p_p_col_count=2&amp;_debates_struts_action=%2Fext%2Fdebates%2Fview_debate&amp;_debates_categoryId=DEBATE_CATEGORY_ID#_debates_message_POSITION_ID";


    /**
     * Character used to separate plan positions.
     */
    public static final String PLAN_POSITIONS_SEPARATOR = "\\,";

    /**
     * Name of plan's published field.
     */
    public static final String PLAN_PUBLISHED_FIELD = "published";

    /**
     * Name of portlet preference holding question URL pattern.
     */
    public static final String PLAN_QUESTION_URL = "planQuestionURL";

    /**
     * Name of portlet preference holding plan request membership URL pattern.
     */
    public static final String PLAN_REQUEST_MEMBERSHIP_URL = "planRequestMembershipURL";

    /**
     * Requests parameter key that represents plan summary.
     */
    public static final String PLAN_SUMMARY = "planSummary";

    /**
     * Name of portlet resource request parameter.
     */
    public static final String PORTLET_RESOURCE = "portletResource";

    /**
     * A placeholder string that is used in pattern URLs. It is used to denote at which place
     * position id should be inserted.
     */
    public static final String POSITION_ID = "POSITION_ID";

    /**
     * Name of parameter holding list of position ids (separated by positions
     * separator).
     */
    public static final String POSITIONS = "positions";

    /**
     * Name of positions filter operator request parameter.
     */
    public static final String POSITIONS_FILTER_OPERATOR = "positionsFilterOperator";

    /**
     * Published request parameter.
     */
    public static final String PUBLISHED = "published";

    /**
     * Published plans tab name.
     */
    public static final String PUBLISHED_PLANS_TAB = "published-plans-tab";

    /**
     * Name of parameter holding map of questions and their positions.
     */
    public static final String QUESTION_POSITIONS = "questionPositions";

    /**
     * Name of parameter holding list of topic questions.
     */
    public static final String QUESTIONS = "questions";

    /**
     * Requests parameter key that represents URL for redirection.
     */
    public static final String REDIRECT = "redirect";

    /**
     * Name of parameter holding scenario id.
     */
    public static final String SCENARIO_ID = "scenarioId";

    /**
     * A placeholder string that is used in pattern URLs. It is used to denote at which place scenario
     * id should be inserted.
     */
    public static final String SCENARIO_ID_PLACEHOLDER = "SCENARIO_ID_PLACEHOLDER";


    /**
     * Name of request parameter with sea level rise.
     */
    public static final String SEA_LEVEL_RISE = "Sea_Level_Rise_output";

    /**
     * Name of summary tab in view plan tab panel.
     */
    public static final String SUMMARY_TAB = "summary";

    /**
     * Name of request parameter with temperature change.
     */
    public static final String TEMPERATURE_CHANGE = "GlobalTempChange";

    /**
     * Request parameter denoting that filters should be turned on/off.
     */
    public static final String TOGGLE_FILTERS = "toggleFilters";

    /**
     * Unpublished plans tab name.
     */
    public static final String UNPUBLISHED_PLANS_TAB = "unpublished-plans-tab";

    /**
     * Unvote request parameter.
     */
    public static final String UNVOTE = "unvote";

    /**
     * Plan update description update type.
     */
    public static final String UPDATE_DESCRIPTION = "description";


    /**
     * Plan update scenario update type.
     */
    public static final String UPDATE_POSITIONS = "positions";

    /**
     * Plan update published status update type.
     */
    public static final String UPDATE_PUBLISHED = "published";

    /**
     * Plan update scenario update type.
     */
    public static final String UPDATE_SCENARIO = "updateScenario";

    /**
     * Plan update type request parameter name.
     */
    public static final String UPDATE_TYPE = "updateType";

    /**
     * Forward path for editing plan.
     */
    public static final String EDIT_MODEL_FORWARD = "portlet.ext.models.edit_model";

    /**
     * Parameter for model name
     */
	public static final String MODEL_NAME = "modelName";

	public static final String MODEL_DISCUSSION = "modelDiscussion";

	public static final String VIEW_MODEL_INDEX_TABS = "innerModelTabView";


    /**
     * Private constructor to prevent users from instantiating this class.
     */
    private ModelConstants() {
    }
}
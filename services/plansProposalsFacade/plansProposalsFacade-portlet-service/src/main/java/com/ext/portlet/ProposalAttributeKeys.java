package com.ext.portlet;

public class ProposalAttributeKeys {
    //TODO: huh, whats that? please contact PDB and delete this comment
    public static final String TEAM = "TEAM";

    /**
     * Core attributes
     */
    public final static String OPEN = "OPEN"; //proposal open for edit?
    public final static String SECTION = "SECTION";
    public final static String NAME = "NAME";
    public final static String DESCRIPTION = "DESCRIPTION";
    public static final String PITCH = "PITCH";
    public static final String IMAGE_ID = "IMAGE_ID";

    /**
     * modelling engine
     */
    public static final String SCENARIO_ID = "SCENARIO_ID";
    public static final String SCENARIO_CO2_CONCENTRATION = "SCENARIO_CO2_CONCENTRATION";
    public static final String SCENARIO_TEMP_CHANGE = "SCENARIO_TEMP_CHANGE";
    public static final String SCENARIO_MITIGATION_COST_NO_ERRORS = "SCENARIO_MITIGATION_COST_NO_ERRORS";
    public static final String SCENARIO_DAMAGE_COST = "SCENARIO_DAMAGE_COST";
    public static final String REGION = "REGION";
    public static final String SUBREGION = "SUBREGION";
    public static final String REGION_ECONOMY = "REGION_ECONOMY";

    /**
     * Judging system
     */
    public static final String JUDGE_RATING = "JUDGE_RATING";
    public static final String JUDGE_ACTION = "JUDGE_ACTION";
    public static final String JUDGE_COMMENT = "JUDGE_COMMENT";
    public static final String FELLOW_RATING = "FELLOW_RATING";
    public static final String FELLOW_ACTION = "FELLOW_ACTION";
    public static final String SELECTED_JUDGES = "SELECTED_JUDGES";

}

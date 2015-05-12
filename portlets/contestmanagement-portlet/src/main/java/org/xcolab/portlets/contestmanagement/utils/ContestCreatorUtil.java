package org.xcolab.portlets.contestmanagement.utils;

import com.ext.portlet.model.*;
import com.ext.portlet.service.*;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import org.xcolab.portlets.contestmanagement.beans.ContestDescriptionBean;
import org.xcolab.portlets.contestmanagement.beans.ContestPhaseBean;
import org.xcolab.portlets.contestmanagement.entities.StartDateEndDate;
import org.xcolab.portlets.contestmanagement.wrappers.ContestScheduleWrapper;

import java.util.*;

/**
 * Helper class used to automatically create sets of new contests. Uses JSON data to create these contests.
 *
 * Created by Mente on 27/02/15.
 */
public class ContestCreatorUtil {
    private static Log _log = LogFactoryUtil.getLog(ContestCreatorUtil.class);

    public static final String CONTEST_SCHEDULE_2015_SECTOR_LABEL = "2015 Schedule: sector";
    public static final String CONTEST_SCHEDULE_2015_REGIONAL_LABEL = "2015 Schedule: regional";
    public static final String CONTEST_SCHEDULE_2015_GLOBAL_LABEL = "2015 Schedule: global";

    private static final String CONTEST_NAME_KEY = "name";
    private static final String CONTEST_QUESTION_KEY = "question";
    private static final String CONTEST_DESCRIPTION_KEY = "description";
    private static final String CONTEST_TIER_KEY = "contestTier";
    private static final String CONTEST_FOCUS_AREA_KEY = "focusArea";
    private static final String CONTEST_ONTOLOGY_TERMS_KEY = "ontologyTerms";
    private static final String CONTEST_SCHEDULE_KEY = "contestSchedule";
    private static final String CONTEST_FELLOWS_KEY = "fellows";
    private static final String CONTEST_LOGO_ID_KEY = "contestLogoId";

    private static final String FELLOW_ROLE_NAME = "Fellow";


    private static final String BASIC_CONTESTS_2015_JSON = "[\n" +
            "{\"name\": \"Adaptation\", \"contestTier\" : 1, \"contestSchedule\": 1,\"focusArea\": 801, \"ontologyTerms\":[]},\n" +
            "{\"name\":\"Buildings\", \"contestTier\" : 1, \"contestSchedule\": 1,\"focusArea\": 303, \"ontologyTerms\":[]},\n" +
            "{\"name\":\"Energy Supply\", \"contestTier\" : 1, \"contestSchedule\": 1,\"focusArea\": 506, \"ontologyTerms\":[]},\n" +
            "{\"name\":\"Geoengineering\", \"contestTier\" : 1, \"contestSchedule\": 1,\"focusArea\": 202, \"ontologyTerms\":[]},\n" +
            "{\"name\":\"Industry\", \"contestTier\" : 1, \"contestSchedule\": 1,\"focusArea\": 102, \"ontologyTerms\":[]},\n" +
            "{\"name\":\"Land Use: Agriculture, Forestry, Livestock\", \"contestTier\" : 1, \"contestSchedule\": 1,\"focusArea\": 104, \"ontologyTerms\":[]},\n" +
            "{\"name\":\"Shifting Attitudes & Behavior\", \"contestTier\" : 1, \"contestSchedule\": 1,\"focusArea\": 0, \"ontologyTerms\":[1,2,3,1300601]},\n" +
            "{\"name\":\"Transportation\", \"contestTier\" : 1, \"contestSchedule\": 1,\"focusArea\": 601, \"ontologyTerms\":[]},\n" +
            "{\"name\":\"US Carbon Price\", \"contestTier\" : 1, \"contestSchedule\": 1,\"focusArea\": 0, \"ontologyTerms\":[]},\n" +
            "{\"name\":\"Waste Management\", \"contestTier\" : 1, \"contestSchedule\": 1,\"focusArea\": 1000502, \"ontologyTerms\":[]},\n" +
            "{\"name\":\"OIP: Latin America Energy\", \"contestTier\" : 1, \"contestSchedule\": 1,\"focusArea\": 0, \"ontologyTerms\":[]},\n" +
            "{\"name\":\"Somerville: Atypical\", \"contestTier\" : 1, \"contestSchedule\": 1,\"focusArea\": 0, \"ontologyTerms\":[]},\n" +
            "{\"name\":\"Rural resilience\", \"contestTier\" : 1, \"contestSchedule\": 1,\"focusArea\": 0, \"ontologyTerms\":[]}\n" +
            "]";

    private static final String TIER_III_AND_II_CONTESTS_2015_JSON = "[\n" +
                // All Tier III contests
                "{\"name\": \"United States’ Climate Action Plan\", " +
                "\"question\": \"What should be the United States’ plan to address climate change?\"," +
                "\"description\": \"This contest seeks broad, coherent visions for what the United States should do about climate change. To do so, combine proposals from other Climate CoLab contests (both current and past) to develop integrated plans that include all sectors of the United States' economy: energy, transportation, industry, buildings and others. You can also work with our <a href='/resources/-/wiki/Main/Assessing+the+impact+of+your+proposal+or+plan#IAFellows' target='blank_'>Impact Assessment Fellows</a> to estimate the overall greenhouse gas emission reductions that would result from the actions you propose. Plans will be evaluated on how well they bring different proposals together to articulate a comprehensive plan for how the United States can effectively address climate change. \"," +
                "\"contestTier\": 3, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1300340,1,1300601], " +
                "\"fellows\" : [1280958,1430005], \"contestLogoId\": 1249210," +
                // US Tier III plan template
                "\"planTemplate\" : {\"name\" : \"Tier III US Contest\", \"sections\" : [" +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the energy sector?\", \"adminTitle\" : \"Tier III US Energy sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the energy sector. Don’t see a plan you want to use? Create one in the sector workspace (see links on the contest homepage) and link it here.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [409,1300340,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the transportation sector?\", \"adminTitle\" : \"Tier III US Transportation sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the transportation sector. Don’t see a plan you want to use? Create one in the sector workspace (see links on the contest homepage) and link it here.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300379,1300340,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the industrial sector?\", \"adminTitle\" : \"Tier III US Industrial sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the industrial sector. Don’t see a plan you want to use? Create one in the sector workspace (see links on the contest homepage) and link it here.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300382,1300340,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the buildings sector?\", \"adminTitle\" : \"Tier III US Buildings sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the building sector. Don’t see a plan you want to use? Create one in the sector workspace (see links on the contest homepage) and link it here.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300378,1300340,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for other sectors?\", \"adminTitle\" : \"Tier III US Other sectors single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"a.\tSelect one plan to represent the actions that should be taken in sectors not represented above, such as agriculture, forestry, other land use, and waste management.  Don’t see a plan you want to use?  Create one in the sector workspace (see links on the contest homepage) and link it here.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300359,1300340,3,1300601]," +
                    "\"tier\" : 2}," +
                    // Other sections
                    "{\"id\": 1301502},{\"id\": 1301504},{\"id\": 1301601},{\"id\": 1301602},{\"id\": 1301505},{\"id\": 1301506},{\"id\": 1301603}, {\"id\": 1301604}, {\"id\": 1301605}, {\"id\": 1301606}" +
                "]}," +
                // US Tier III sub contests
                "\"subContests\": [" +
                    "{\"name\": \"United States: Building Sector Plan\", " +
                        "\"question\": \"How should the United States address climate change in the building sector?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how the United States collectively should address climate change in its building sector. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p>" +
                        "<br/><br/>" +
                        "<p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level. </p><p><strong>Please note that, unlike a contest, proposals submitted in this workspace will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300378,1300340,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249204},\n" +
                    "{\"name\": \"United States: Energy Sector Plan\", " +
                        "\"question\": \"How should the United States address climate change in the energy sector?\","+
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how the United States collectively should address climate change in its energy sector. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p>" +
                        "<p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level. </p><p><strong>Please note that, unlike a contest, proposals submitted in this workspace will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [409,1300340,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249206},\n" +
                    "{\"name\": \"United States: Industry Sector Plan\", " +
                        "\"question\": \"How should the United States address climate change in the industry sector?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how the United States collectively should address climate change in its industry sector. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p>" +
                        "<p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level. </p><p><strong>Please note that, unlike a contest, proposals submitted in this workspace will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300382,1300340,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249207},\n" +
                    "{\"name\": \"United States: Transportation Sector Plan\", " +
                        "\"question\": \"How should the United States address climate change in the transportation sector?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how the United States collectively should address climate change in its transportation sector. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p>" +
                        "<p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level. </p><p><strong>Please note that, unlike a contest, proposals submitted in this workspace will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300379,1300340,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249208},\n" +
                    "{\"name\": \"United States: Other Sectors Plan\", " +
                        "\"question\": \"How should the United States address climate change in other sectors?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how the United States collectively should address climate change in agriculture, forestry, and other land use; and waste management. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p><p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level, and <strong><strong>will only be evaluated if they are included in a regional plan.</strong></strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300359,1300340,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249209},\n" +
                "]},\n" +
                // ##################### US Tier III done #####################

                "{\"name\": \"Europe’s Climate Action Plan\", " +
                "\"question\": \"What should be the Europe’s plan to address climate change?\"," +
                "\"description\": \"This contest seeks broad, coherent visions for what the Europe should do about climate change. To do so, combine proposals from other Climate CoLab contests (both current and past) to develop integrated plans that include all sectors of the Europe\u0027s economy: energy, transportation, industry, buildings and others. You can also work with our <a href='/resources/-/wiki/Main/Assessing+the+impact+of+your+proposal+or+plan#IAFellows' target='blank_'>Impact Assessment Fellows</a> to estimate the overall greenhouse gas emission reductions that would result from the actions you propose. Plans will be evaluated on how well they bring different proposals together to articulate a comprehensive plan for how the Europe can effectively address climate change. \"," +
                "\"contestTier\": 3, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1300341,1,1300601], " +
                "\"fellows\" : [1972027,1971911],\"contestLogoId\": 1249211," +
                // EU Tier III plan template
                "\"planTemplate\" : {\"name\" : \"Tier III EU Contest\", \"sections\" : [" +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the energy sector?\", \"adminTitle\" : \"Tier III EU Energy sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the energy sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [409,1300341,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the transportation sector?\", \"adminTitle\" : \"Tier III EU Transportation sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the transportation sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300379,1300341,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the industrial sector?\", \"adminTitle\" : \"Tier III EU Industrial sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the industrial sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300382,1300341,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the buildings sector?\", \"adminTitle\" : \"Tier III EU Buildings sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the building sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300378,1300341,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for other sectors?\", \"adminTitle\" : \"Tier III EU Other sectors single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in other sectors, such as agriculture, aquaculture, livestock, forestry, and waste management.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300359,1300341,3,1300601]," +
                    "\"tier\" : 2}," +
                    // Other sections
                    "{\"id\": 1301502},{\"id\": 1301504},{\"id\": 1301601},{\"id\": 1301602},{\"id\": 1301505},{\"id\": 1301506},{\"id\": 1301603}, {\"id\": 1301604}, {\"id\": 1301605}, {\"id\": 1301606}" +
                "]}," +
                // EU Tier III sub contests
                "\"subContests\": [" +
                    "{\"name\": \"Europe: Building Sector Plan\", " +
                        "\"question\": \"How should the Europe address climate change in the building sector?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how the Europe collectively should address climate change in its building sector. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p>" +
                        "<p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level. </p><p><strong>Please note that, unlike a contest, proposals submitted in this workspace will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300378,1300341,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249204},\n" +
                    "{\"name\": \"Europe: Energy Sector Plan\", " +
                        "\"question\": \"How should the Europe address climate change in the energy sector?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how the Europe collectively should address climate change in its energy sector. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p>" +
                        "<p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level. </p><p><strong>Please note that, unlike a contest, proposals submitted in this workspace will only be evaluated if they are included in a regional plan.</strong></p>\"," +
            "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [409,1300341,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249206},\n" +
                    "{\"name\": \"Europe: Industry Sector Plan\", " +
                        "\"question\": \"How should the Europe address climate change in the industry sector?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how the Europe collectively should address climate change in its industry sector. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p>" +
                        "<p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level. </p><p><strong>Please note that, unlike a contest, proposals submitted in this workspace will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300382,1300341,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249207},\n" +
                    "{\"name\": \"Europe: Transportation Sector Plan\", " +
                        "\"question\": \"How should the Europe address climate change in the transportation sector?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how the Europe collectively should address climate change in its transportation sector. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p>" +
                        "<p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level. </p><p><strong>Please note that, unlike a contest, proposals submitted in this workspace will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300379,1300341,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249208},\n" +
                    "{\"name\": \"Europe: Other Sectors Plan\", " +
                        "\"question\": \"How should the Europe address climate change in other sectors?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how Europe collectively should address climate change in agriculture, forestry, and other land use; and waste management. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p><p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level, and <strong>will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300359,1300341,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249209},\n" +
                "]},\n" +
                // ##################### EU Tier III done #####################

                "{\"name\": \"China’s Climate Action Plan\", " +
                "\"question\": \"What should be China’s plan to address climate change?\"," +
                "\"description\": \"This contest seeks broad, coherent visions for what China should do about climate change. To do so, combine proposals from other Climate CoLab contests (both current and past) to develop integrated plans that include all sectors of China\u0027s economy: energy, transportation, industry, buildings and others. You can also work with our <a href='/resources/-/wiki/Main/Assessing+the+impact+of+your+proposal+or+plan#IAFellows' target='blank_'>Impact Assessment Fellows</a> to estimate the overall greenhouse gas emission reductions that would result from the actions you propose. Plans will be evaluated on how well they bring different proposals together to articulate a comprehensive plan for how China can effectively address climate change. \"," +
                "\"contestTier\": 3, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1300345,1,1300601], \"contestLogoId\": 1249212," +
                // China Tier III plan template
                "\"planTemplate\" : {\"name\" : \"Tier III China Contest\", \"sections\" : [" +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the energy sector?\", \"adminTitle\" : \"Tier III China Energy sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the energy sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [409,1300345,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the transportation sector?\", \"adminTitle\" : \"Tier III China Transportation sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the transportation sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300379,1300345,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the industrial sector?\", \"adminTitle\" : \"Tier III China Industrial sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the industrial sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300382,1300345,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the buildings sector?\", \"adminTitle\" : \"Tier III China Buildings sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the building sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300378,1300345,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for other sectors?\", \"adminTitle\" : \"Tier III China Other sectors single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in other sectors, such as agriculture, aquaculture, livestock, forestry, and waste management.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300359,1300345,3,1300601]," +
                    "\"tier\" : 2}," +
                    // Other sections
                    "{\"id\": 1301502},{\"id\": 1301504},{\"id\": 1301601},{\"id\": 1301602},{\"id\": 1301505},{\"id\": 1301506},{\"id\": 1301603}, {\"id\": 1301604}, {\"id\": 1301605}, {\"id\": 1301606}" +
                "]}," +
                // China Tier III sub contests
                "\"subContests\": ["  +
                    "{\"name\": \"China: Building Sector Plan\", " +
                        "\"question\": \"How should China address climate change in the building sector?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how China collectively should address climate change in its building sector. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p>" +
                        "<p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level. </p><p><strong>Please note that, unlike a contest, proposals submitted in this workspace will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300378,1300345,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249204},\n" +
                    "{\"name\": \"China: Energy Sector Plan\", " +
                        "\"question\": \"How should China address climate change in the energy sector?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how China collectively should address climate change in its energy sector. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p>" +
                        "<p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level. </p><p><strong>Please note that, unlike a contest, proposals submitted in this workspace will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [409,1300345,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249206},\n" +
                    "{\"name\": \"China: Industry Sector Plan\", " +
                        "\"question\": \"How should China address climate change in the industry sector?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how China collectively should address climate change in its industry sector. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p>" +
                        "<p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level. </p><p><strong>Please note that, unlike a contest, proposals submitted in this workspace will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300382,1300345,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249207},\n" +
                    "{\"name\": \"China: Transportation Sector Plan\", " +
                        "\"question\": \"How should China address climate change in the transportation sector?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how China collectively should address climate change in its transportation sector. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p>" +
                        "<p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level. </p><p><strong>Please note that, unlike a contest, proposals submitted in this workspace will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300379,1300345,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249208},\n" +
                    "{\"name\": \"China: Other Sectors Plan\", " +
                        "\"question\": \"How should China address climate change in the other sectors?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how China collectively should address climate change in agriculture, forestry, and other land use; and waste management. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p><p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level, and <strong>will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300359,1300345,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249209}\n" +
                "]},\n" +
                // ##################### China Tier III done #####################


                "{\"name\": \"India’s Climate Action Plan\", " +
                "\"question\": \"What should be India’s plan to address climate change?\"," +
                "\"description\": \"This contest seeks broad, coherent visions for what India should do about climate change. To do so, combine proposals from other Climate CoLab contests (both current and past) to develop integrated plans that include all sectors of India\u0027s economy: energy, transportation, industry, buildings and others. You can also work with our <a href='/resources/-/wiki/Main/Assessing+the+impact+of+your+proposal+or+plan#IAFellows' target='blank_'>Impact Assessment Fellows</a> to estimate the overall greenhouse gas emission reductions that would result from the actions you propose. Plans will be evaluated on how well they bring different proposals together to articulate a comprehensive plan for how India can effectively address climate change. \"," +
                "\"contestTier\": 3, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1300346,1,1300601], " +
                "\"fellows\" : [1415452,1967956],\"contestLogoId\": 1249213," +
                "\"planTemplate\" : {\"name\" : \"Tier III India Contest\", \"sections\" : [" +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the energy sector?\", \"adminTitle\" : \"Tier III India Energy sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the energy sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [409,1300346,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the transportation sector?\", \"adminTitle\" : \"Tier III India Transportation sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the transportation sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300379,1300346,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the industrial sector?\", \"adminTitle\" : \"Tier III India Industrial sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the industrial sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300382,1300346,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the buildings sector?\", \"adminTitle\" : \"Tier III India Buildings sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the building sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300378,1300346,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for other sectors?\", \"adminTitle\" : \"Tier III India Other sectors single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in other sectors, such as agriculture, aquaculture, livestock, forestry, and waste management.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300359,1300346,3,1300601]," +
                    "\"tier\" : 2}," +
                    // Other sections
                    "{\"id\": 1301502},{\"id\": 1301504},{\"id\": 1301601},{\"id\": 1301602},{\"id\": 1301505},{\"id\": 1301506},{\"id\": 1301603}, {\"id\": 1301604}, {\"id\": 1301605}, {\"id\": 1301606}" +
                "]}," +
                // India Tier III sub contests
                "\"subContests\": ["  +
                    "{\"name\": \"India: Building Sector Plan\", " +
                        "\"question\": \"How should India address climate change in the building sector?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how India collectively should address climate change in its building sector. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p>" +
                        "<p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level. </p><p><strong>Please note that, unlike a contest, proposals submitted in this workspace will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300378,1300346,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249204},\n" +
                    "{\"name\": \"India: Energy Sector Plan\", " +
                        "\"question\": \"How should India address climate change in the energy sector?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how India collectively should address climate change in its energy sector. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p>" +
                        "<p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level. </p><p><strong>Please note that, unlike a contest, proposals submitted in this workspace will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [409,1300346,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249206},\n" +
                    "{\"name\": \"India: Industry Sector Plan\", " +
                        "\"question\": \"How should India address climate change in the industry sector?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how India collectively should address climate change in its industry sector. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p>" +
                        "<p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level. </p><p><strong>Please note that, unlike a contest, proposals submitted in this workspace will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300382,1300346,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249207},\n" +
                    "{\"name\": \"India: Transportation Sector Plan\", " +
                        "\"question\": \"How should India address climate change in the transportation sector?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how India collectively should address climate change in its transportation sector. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p>" +
                        "<p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level. </p><p><strong>Please note that, unlike a contest, proposals submitted in this workspace will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300379,1300346,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249208},\n" +
                    "{\"name\": \"India: Other Sectors Plan\", " +
                        "\"question\": \"How should India address climate change in the other sectors?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how India collectively should address climate change in agriculture, forestry, and other land use; and waste management. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p><p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level, and <strong>will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300359,1300346,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249209}\n" +
                "]},\n" +
                // ##################### India Tier III done #####################

                "{\"name\": \"Other Developed Countries’ Climate Action Plan\", " +
                "\"question\": \"What should be the Other Developed Countries’ plan to address climate change?\"," +
                "\"description\": \"This contest seeks broad, coherent visions for what the Other Developed Countries should do about climate change. To do so, combine proposals from other Climate CoLab contests (both current and past) to develop integrated plans that include all sectors of the Other Developed Countries' economy: energy, transportation, industry, buildings and others. You can also work with our <a href='/resources/-/wiki/Main/Assessing+the+impact+of+your+proposal+or+plan#IAFellows' target='blank_'>Impact Assessment Fellows</a> to estimate the overall greenhouse gas emission reductions that would result from the actions you propose. Plans will be evaluated on how well they bring different proposals together to articulate a comprehensive plan for how the Other Developed Countries can effectively address climate change. \"," +
                "\"contestTier\": 3, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1311101,1,1300601], " +
                "\"fellows\" : [1419380,1480691],\"contestLogoId\": 1249214," +
                "\"planTemplate\" : {\"name\" : \"Tier III Other Developed Countries Contest\", \"sections\" : [" +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the energy sector?\", \"adminTitle\" : \"Tier III Other Developed Countries Energy sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the energy sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [409,1311101,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the transportation sector?\", \"adminTitle\" : \"Tier III Other Developed Countries Transportation sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the transportation sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300379,1311101,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the industrial sector?\", \"adminTitle\" : \"Tier III Other Developed Countries Industrial sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the industrial sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300382,1311101,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the buildings sector?\", \"adminTitle\" : \"Tier III Other Developed Countries Buildings sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the building sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300378,1311101,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for other sectors?\", \"adminTitle\" : \"Tier III Other Developed Countries Other sectors single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in other sectors, such as agriculture, aquaculture, livestock, forestry, and waste management.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300359,1311101,3,1300601]," +
                    "\"tier\" : 2}," +
                    // Other sections
                    "{\"id\": 1301502},{\"id\": 1301504},{\"id\": 1301601},{\"id\": 1301602},{\"id\": 1301505},{\"id\": 1301506},{\"id\": 1301603}, {\"id\": 1301604}, {\"id\": 1301605}, {\"id\": 1301606}" +
                "]}," +
                // Other Developed Countries Tier III sub contests
                "\"subContests\": [" +
                    "{\"name\": \"Other Developed Countries: Building Sector Plan\", " +
                        "\"question\": \"How should Other Developed Countries address climate change in the building sector?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how Other Developed Countries collectively should address climate change in its building sector. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p>" +
                        "<p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level. </p><p><strong>Please note that, unlike a contest, proposals submitted in this workspace will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300378,1311101,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249204},\n" +
                    "{\"name\": \"Other Developed Countries: Energy Sector Plan\", " +
                        "\"question\": \"How should Other Developed Countries address climate change in the energy sector?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how Other Developed Countries collectively should address climate change in its energy sector. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p>" +
                        "<p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level. </p><p><strong>Please note that, unlike a contest, proposals submitted in this workspace will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [409,1311101,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249206},\n" +
                    "{\"name\": \"Other Developed Countries: Industry Sector Plan\", " +
                        "\"question\": \"How should Other Developed Countries address climate change in the industry sector?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how Other Developed Countries collectively should address climate change in its industry sector. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p>" +
                        "<p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level. </p><p><strong>Please note that, unlike a contest, proposals submitted in this workspace will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300382,1311101,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249207},\n" +
                    "{\"name\": \"Other Developed Countries: Transportation Sector Plan\", " +
                        "\"question\": \"How should Other Developed Countries address climate change in the transportation sector?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how Other Developed Countries collectively should address climate change in its transportation sector. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p>" +
                        "<p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level. </p><p><strong>Please note that, unlike a contest, proposals submitted in this workspace will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300379,1311101,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249208},\n" +
                    "{\"name\": \"Other Developed Countries: Other Sectors Plan\", " +
                        "\"question\": \"How should Other Developed Countries address climate change in the other sectors?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how the Other Developed Countries collectively should address climate change in agriculture, forestry, and other land use; and waste management. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p><p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level, and <strong>will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300359,1311101,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249209}\n" +
                "]},\n" +
                // ##################### Other Developed Countries Tier III done #####################

                "{\"name\": \"Other Developing Countries’ Climate Action Plan\", " +
                "\"question\": \"What should be the Other Developing Countries’ plan to address climate change?\"," +
                "\"description\": \"This contest seeks broad, coherent visions for what the Other Developing Countries should do about climate change. To do so, combine proposals from other Climate CoLab contests (both current and past) to develop integrated plans that include all sectors of the Other Developing Countries\u0027 economy: energy, transportation, industry, buildings and others. You can also work with our <a href='/resources/-/wiki/Main/Assessing+the+impact+of+your+proposal+or+plan#IAFellows' target='blank_'>Impact Assessment Fellows</a> to estimate the overall greenhouse gas emission reductions that would result from the actions you propose. Plans will be evaluated on how well they bring different proposals together to articulate a comprehensive plan for how the Other Developing Countries can effectively address climate change. \"," +
                "\"contestTier\": 3, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1311102,1,1300601], " +
                "\"fellows\" : [1976511,170781,1420797],\"contestLogoId\": 1249215," +
                "\"planTemplate\" : {\"name\" : \"Tier III Other Developing Countries Contest\", \"sections\" :[" +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the energy sector?\", \"adminTitle\" : \"Tier III Other Developing Countries Energy sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the energy sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [409,1311102,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the transportation sector?\", \"adminTitle\" : \"Tier III Other Developing Countries Transportation sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the transportation sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300379,1311102,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the industrial sector?\", \"adminTitle\" : \"Tier III Other Developing Countries Industrial sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the industrial sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300382,1311102,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the buildings sector?\", \"adminTitle\" : \"Tier III Other Developing Countries Buildings sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the building sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300378,1311102,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for other sectors?\", \"adminTitle\" : \"Tier III Other Developing Countries Other sectors single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in other sectors, such as agriculture, aquaculture, livestock, forestry, and waste management.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300359,1311102,3,1300601]," +
                    "\"tier\" : 2}," +
                    // Other sections
                    "{\"id\": 1301502},{\"id\": 1301504},{\"id\": 1301601},{\"id\": 1301602},{\"id\": 1301505},{\"id\": 1301506},{\"id\": 1301603}, {\"id\": 1301604}, {\"id\": 1301605}, {\"id\": 1301606}" +
                "]}," +
                // Other Developed Countries Tier III sub contests
                "\"subContests\": [" +
                    "{\"name\": \"Other Developing Countries: Building Sector Plan\", " +
                        "\"question\": \"How should Other Developing Countries address climate change in the building sector?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how Other Developing Countries collectively should address climate change in its building sector. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p>" +
                        "<p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level. </p><p><strong>Please note that, unlike a contest, proposals submitted in this workspace will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300378,1311102,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249204},\n" +
                    "{\"name\": \"Other Developing Countries: Energy Sector Plan\", " +
                        "\"question\": \"How should Other Developing Countries address climate change in the energy sector?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how Other Developing Countries collectively should address climate change in its energy sector. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p>" +
                        "<p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level. </p><p><strong>Please note that, unlike a contest, proposals submitted in this workspace will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [409,1311102,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249206},\n" +
                    "{\"name\": \"Other Developing Countries: Industry Sector Plan\", " +
                        "\"question\": \"How should Other Developing Countries address climate change in the industry sector?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how Other Developing Countries collectively should address climate change in its industry sector. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p>" +
                        "<p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level. </p><p><strong>Please note that, unlike a contest, proposals submitted in this workspace will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300382,1311102,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249207},\n" +
                    "{\"name\": \"Other Developing Countries: Transportation Sector Plan\", " +
                        "\"question\": \"How should Other Developing Countries address climate change in the transportation sector?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how Other Developing Countries collectively should address climate change in its transportation sector. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p>" +
                        "<p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level. </p><p><strong>Please note that, unlike a contest, proposals submitted in this workspace will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300379,1311102,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249208},\n" +
                    "{\"name\": \"Other Developing Countries: Other Sectors Plan\", " +
                        "\"question\": \"How should Other Developing Countries address climate change in the other sectors?\"," +
                        "\"description\": \"<p>In this workspace, you can combine proposals from Climate CoLab contests, as well as from outside the Climate CoLab, to develop plans for how the Other Developing Countries collectively should address climate change in agriculture, forestry, and other land use; and waste management. You can also estimate the overall greenhouse gas emission reductions that would result from the collection of actions you propose.</p><p>In addition to being completed pieces on their own, proposals submitted here will serve as building blocks for the development of plans on a regional and global level, and <strong>will only be evaluated if they are included in a regional plan.</strong></p>\"," +
                        "\"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300359,1311102,1,1300601], \"planTemplate\" : 1301201, \"contestLogoId\": 1249209}\n" +
                "]},\n" +
                // ##################### Other Developing Countries Tier III done #####################
            "]";



    public static Map<String, String> create2015BasicContests(String portalBaseURL) {
        return createContestsWithJSON(BASIC_CONTESTS_2015_JSON, portalBaseURL);
    }

    public static Map<String, String> create2015Tier2And3Contests(String portalBaseURL) {
        return createContestsWithJSON(TIER_III_AND_II_CONTESTS_2015_JSON, portalBaseURL);
    }


    public static Contest createNewContest(String contestShortName) throws Exception {
        Contest contest = ContestLocalServiceUtil.createNewContest(10144L, contestShortName);
        contest.setContestPrivate(true);
        contest.setShow_in_tile_view(true);
        contest.setShow_in_list_view(true);
        contest.setShow_in_outline_view(true);

        // TODO for now there is always a template preselected
        contest.setPlanTemplateId(102L);
        contest.persist();

        return contest;
    }

    private static String getContestEditLink(Contest contest) {
        return "/web/guest/cms/-/contestmanagement/contestId/" + contest.getContestPK() + "/tab/DESCRIPTION";
    }

    private static String getContestLink(Contest contest) {
        return "/web/guest/plans/-/plans/contestId/" + contest.getContestPK();
    }

    private static Map<String, String> createContestsWithJSON(String json, String portalBaseUrl) {

        JSONArray contestDataArray = null;
        try {
            contestDataArray = JSONFactoryUtil.createJSONArray(json);

            Map<String, String> contestEditMap = new HashMap<>(contestDataArray.length());
            for (int i = 0; i < contestDataArray.length(); i++) {
                JSONObject newContestContents = contestDataArray.getJSONObject(i);
                try {
                    Contest newContest = createSingleContestWithJSON(newContestContents);

                    String absoluteURL = portalBaseUrl + getContestEditLink(newContest);
                    contestEditMap.put(newContest.getContestShortName(), absoluteURL);

                    // Set planTemplate (try to get a JSON object from planTemplate, for the case that a new one has to be created)
                    // Else just use the passed planTemplateId
                    try {
                        PlanTemplate contestPlanTemplate = createPlanTemplateWithJson(newContestContents.getJSONObject("planTemplate"), newContest);
                        newContest.setPlanTemplateId(contestPlanTemplate.getId());
                    } catch (Exception e) {
                        try {
                            newContest.setPlanTemplateId(newContestContents.getLong("planTemplate"));
                        } catch (Exception e2) {
                            _log.error("Could not set the planTemplate for contest " + newContest.getContestPK(), e2);
                        }
                    }

                    newContest.persist();

                    // Create sub contests, if there are any
                    final JSONArray subContestsJson = newContestContents.getJSONArray("subContests");
                    if (Validator.isNotNull(subContestsJson)) {
                        List<Contest> subContests = createSubContests(subContestsJson);
                        String descriptionTemplate = getSubContestHTMLList(subContests);
                        newContest.setContestDescription(newContest.getContestDescription() + " <br/><br/> " + descriptionTemplate);
                        newContest.persist();

                        // Add sub contests to map
                        for (Contest subContest : subContests) {
                            absoluteURL = portalBaseUrl + getContestEditLink(subContest);
                            contestEditMap.put(subContest.getContestShortName(), absoluteURL);
                        }
                    }


                } catch (Exception e) {
                    _log.error("Could not create contest with name " + newContestContents.getString(CONTEST_NAME_KEY) + ": " + e);
                    contestEditMap.put(newContestContents.getString(CONTEST_NAME_KEY), "Could not be created");
                }
            }

            return contestEditMap;
        } catch (JSONException e) {
            _log.error("JSON exception : " + e);
        }

        return null;
    }

    private static Contest createSingleContestWithJSON(JSONObject newContestContents) throws Exception {

        String contestTitle = newContestContents.getString(CONTEST_NAME_KEY);
        Contest newContest = createNewContest(contestTitle);
        newContest.setContestName(newContestContents.getString(CONTEST_QUESTION_KEY));
        newContest.setContestDescription(newContestContents.getString(CONTEST_DESCRIPTION_KEY));
        newContest.setContestTier(newContestContents.getInt(CONTEST_TIER_KEY));
        newContest.setContestScheduleId(newContestContents.getLong(CONTEST_SCHEDULE_KEY));
        newContest.setContestLogoId(newContestContents.getLong(CONTEST_LOGO_ID_KEY));

        JSONArray fellowArray = newContestContents.getJSONArray(CONTEST_FELLOWS_KEY);
        if (Validator.isNotNull(fellowArray) && fellowArray.length() > 0) {
            addFellows(newContest, newContestContents.getJSONArray(CONTEST_FELLOWS_KEY));
        }

        long focusAreaId = newContestContents.getLong(CONTEST_FOCUS_AREA_KEY);
        if (focusAreaId == 0) {
            JSONArray ontologyTermIds = newContestContents.getJSONArray(CONTEST_ONTOLOGY_TERMS_KEY);

            // Create new focus area with the specified ontology terms
            if (ontologyTermIds.length() > 0) {
                FocusArea focusArea = createFocusAreaWithOntologyTermsArray(ontologyTermIds, newContest);

                newContest.setFocusAreaId(focusArea.getId());
            }
        } else {
            newContest.setFocusAreaId(focusAreaId);
        }

        int contestTier = newContestContents.getInt(CONTEST_TIER_KEY);
        boolean isContestTierLevel2 = (contestTier == 2);
        if(isContestTierLevel2){
            newContest.setShow_in_tile_view(false);
            newContest.setShow_in_list_view(false);
        }

        newContest.persist();

        Long contestScheduleTemplateId = newContestContents.getLong(CONTEST_SCHEDULE_KEY);
        createContestPhases(newContest, contestScheduleTemplateId);
        createContestWikiPage(newContest);

        return newContest;
    }

    private static void createContestWikiPage(Contest contest) throws Exception{
        ContestDescriptionBean.updateContestWiki(contest, "");
    }

    private static void createContestPhases(Contest contest, Long contestScheduleTemplateId) throws Exception{
        ContestScheduleWrapper.createContestPhasesAccordingToContestScheduleAndRemoveExistingPhases(contest, contestScheduleTemplateId);
    }

    private static List<Contest> createSubContests(JSONArray subContestsJson) throws Exception {
        List<Contest> subContestList = new ArrayList<>(subContestsJson.length());
        for (int j = 0; j < subContestsJson.length(); j++) {
            subContestList.add(createSingleContestWithJSON(subContestsJson.getJSONObject(j)));
        }

        return subContestList;
    }

    private static String getSubContestHTMLList(List<Contest> subContests) {
        StringBuilder htmlListString = new StringBuilder("<ul>");
        // Create HTML list for all sub contests
        for (Contest subContest : subContests) {
            htmlListString.append("<li><a href='" + getContestLink(subContest) + "'>" + subContest.getContestShortName() + "</a></li>");
        }
        htmlListString.append("</ul>");
        return htmlListString.toString();
    }

    private static FocusArea createFocusAreaWithOntologyTermsArray(JSONArray ontologyTermsJson, Contest forContest) throws SystemException, PortalException {
        FocusArea focusArea = FocusAreaLocalServiceUtil.createFocusArea(CounterLocalServiceUtil.increment(FocusArea.class.getName()));
        focusArea.setName("created by 2015 " + forContest.getContestName());
        focusArea.persist();

        long focusAreaId = focusArea.getId();
        FocusAreaLocalServiceUtil.updateFocusArea(focusArea);

        for (int j = 0; j < ontologyTermsJson.length(); j++) {
            FocusAreaOntologyTermLocalServiceUtil.addAreaTerm(focusAreaId, ontologyTermsJson.getLong(j));
        }

        return focusArea;
    }

    private static PlanTemplate createPlanTemplateWithJson(JSONObject planTemplateJson, Contest forContest) throws PortalException, SystemException {
        PlanTemplate newPlanTemplate = PlanTemplateLocalServiceUtil.createPlanTemplate(CounterLocalServiceUtil.increment(PlanTemplate.class.getName()));
        newPlanTemplate.setName(planTemplateJson.getString("name"));

        // Create/add sections
        final JSONArray sectionsJson = planTemplateJson.getJSONArray("sections");
        for (int i = 0; i < sectionsJson.length(); i++) {
            final JSONObject sectionDefinitionJson = sectionsJson.getJSONObject(i);

            // Add existing section
            if (sectionDefinitionJson.getLong("id") != 0) {
                long sectionDefinitionId = sectionDefinitionJson.getLong("id");
                PlanSectionDefinition sectionDefinition = PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinition(sectionDefinitionId);
                PlanTemplateLocalServiceUtil.addSection(newPlanTemplate, sectionDefinition);
            }
            // Look for planSectionDefinition if it exists for specified params; otherwise create new one
            else {
                PlanSectionDefinition sectionDefinition = getOrCreatePlanSectionDefinitionWithJson(sectionDefinitionJson, forContest);
                PlanTemplateLocalServiceUtil.addSection(newPlanTemplate, sectionDefinition);
            }
        }
        newPlanTemplate.persist();

        return newPlanTemplate;
    }

    /**
     * Tries to retrieve the PlanSectionDefinition for the passed parameters and creates a new PlanSectionDefinition if it does not exist yet
     * @param sectionDefinitionJson
     * @param forContest
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    private static PlanSectionDefinition getOrCreatePlanSectionDefinitionWithJson(JSONObject sectionDefinitionJson, Contest forContest) throws SystemException, PortalException {
        // Get Focus area
        long focusAreaId = sectionDefinitionJson.getLong("focusArea");

        FocusArea focusArea;
        if (focusAreaId == 0) {
            focusArea = createFocusAreaWithOntologyTermsArray(sectionDefinitionJson.getJSONArray("ontologyTerms"), forContest);
        } else {
            try {
                focusArea = FocusAreaLocalServiceUtil.getFocusArea(focusAreaId);
            } catch (Exception e) {
                _log.error("Could not get Focus Area with ID " + focusAreaId + "! Creating new one...", e);
                focusArea = createFocusAreaWithOntologyTermsArray(sectionDefinitionJson.getJSONArray("ontologyTerms"), forContest);
            }
        }

        // Try to retrieve the planSectionDefinition by filtering focusArea, sectionType and contestTier
        String sectionType = sectionDefinitionJson.getString("type");
        Long contestTierType = sectionDefinitionJson.getLong("tier");
        PlanSectionDefinition sectionDefinition = PlanSectionDefinitionLocalServiceUtil.getPlanSectionDefinition(focusArea, sectionType, contestTierType);
        if (Validator.isNotNull(sectionDefinition)) {
            return sectionDefinition;
        }

        // Else create new object
        PlanSectionDefinition newSection = PlanSectionDefinitionLocalServiceUtil.createPlanSectionDefinition(
                CounterLocalServiceUtil.increment(PlanSectionDefinition.class.getName()));

        newSection.setAdminTitle(sectionDefinitionJson.getString("adminTitle"));
        newSection.setTitle(sectionDefinitionJson.getString("title"));
        newSection.setType(sectionDefinitionJson.getString("type"));
        newSection.setHelpText(sectionDefinitionJson.getString("helpText"));
        newSection.setTier(sectionDefinitionJson.getLong("tier"));

        newSection.setFocusAreaId(focusArea.getId());
        newSection.persist();

        return newSection;
    }

    private static void addFellows(Contest contest, JSONArray fellowsJson) {
        for (int i = 0; i < fellowsJson.length(); i++) {
            long userId = fellowsJson.getLong(i);
            try {
                ContestTeamMemberLocalServiceUtil.addContestTeamMember(userId, contest.getContestPK(), FELLOW_ROLE_NAME);
            } catch (SystemException e) {
                _log.error("Could not add user with ID " + userId + " to fellows of contest with ID " + contest.getContestPK());
            }
        }
    }

    public static void insertSeedDataToContestScheduleTableIfNotAvailable() throws Exception{
        if(!isContestSchedulesAvailable()){
            insertSeedDataToContestScheduleTable(CONTEST_SCHEDULE_2015_SECTOR_LABEL, createSeedDataForBasicLevelSchedule());
            insertSeedDataToContestScheduleTable(CONTEST_SCHEDULE_2015_REGIONAL_LABEL, createSeedDataForRegionalLevelSchedule());
        }
    }

    private static boolean isContestSchedulesAvailable() throws Exception{
        return ContestScheduleLocalServiceUtil.getContestSchedulesCount() > 1;
    }

    private static void insertSeedDataToContestScheduleTable(String scheduleName, List<ContestPhaseBean> contestPhaseBeanList) throws Exception{

        ContestSchedule contestSchedule = ContestScheduleLocalServiceUtil.
                createContestSchedule(CounterLocalServiceUtil.increment(ContestSchedule.class.getName()));
        contestSchedule.setName(scheduleName);
        contestSchedule.persist();
        ContestScheduleLocalServiceUtil.updateContestSchedule(contestSchedule);

        for(ContestPhaseBean contestPhaseBean : contestPhaseBeanList){
            ContestPhase contestPhase = ContestPhaseLocalServiceUtil.
                    createContestPhase(CounterLocalServiceUtil.increment(ContestPhase.class.getName()));

            contestPhase.setContestPK(0L);
            contestPhase.setContestScheduleId(contestSchedule.getId());
            contestPhase.setContestPhaseType(contestPhaseBean.getContestPhaseType());
            contestPhase.setPhaseStartDate(contestPhaseBean.getPhaseStartDate());

            if(contestPhaseBean.getPhaseEndDate() != null) {
                contestPhase.setPhaseEndDate(contestPhaseBean.getPhaseEndDate());
            }

            contestPhase.setContestPhaseAutopromote(contestPhaseBean.getContestPhaseAutopromote());
            contestPhase.setFellowScreeningActive(contestPhaseBean.getFellowScreeningActive());
            contestPhase.persist();
            ContestPhaseLocalServiceUtil.updateContestPhase(contestPhase);
        }
    }

    private static List<ContestPhaseBean> createSeedDataForBasicLevelSchedule(){
        List<StartDateEndDate> phaseStartEndDatesBasicLevelSchedule  = new ArrayList<>();
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date("3/6/15 12:00:00 AM"), new Date("5/16/15 11:59:59 PM")));
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date("5/18/15 12:00:00 AM"), new Date("5/29/15 11:59:59 PM")));
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date("6/1/15 12:00:00 AM"), new Date("6/13/15 11:59:59 PM")));
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date("6/15/15 12:00:00 AM"), new Date("6/26/15 11:59:59 PM")));
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date("7/1/15 12:00:00 AM"), new Date("7/31/15 11:59:59 PM")));
        return createPhasesFromStartEndDates(phaseStartEndDatesBasicLevelSchedule);
    }

    private static List<ContestPhaseBean> createSeedDataForRegionalLevelSchedule(){
        List<StartDateEndDate> phaseStartEndDatesBasicLevelSchedule  = new ArrayList<>();
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date("3/6/15 12:00:00 AM"), new Date("6/6/15 11:59:59 PM")));
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date("6/6/15 12:00:00 AM"), new Date("2/7/15 11:59:59 PM")));
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date("7/3/15 12:00:00 AM"), new Date("7/15/15 11:59:59 PM")));
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date("7/16/15 12:00:00 AM"), new Date("8/6/15 11:59:59 PM")));
        phaseStartEndDatesBasicLevelSchedule.add(new StartDateEndDate(new Date("8/3/15 12:00:00 AM"), new Date("9/1/15 11:59:59 PM")));
        return createPhasesFromStartEndDates(phaseStartEndDatesBasicLevelSchedule);
    }

    private static List<ContestPhaseBean> createPhasesFromStartEndDates(List<StartDateEndDate> startDateEndDates){
        List<ContestPhaseBean> contestPhaseBeans = new ArrayList<>();
        contestPhaseBeans.add(new ContestPhaseBean(1L, startDateEndDates.get(0).getStartDate(), startDateEndDates.get(0).getEndDate(), "PROMOTE", false));
        contestPhaseBeans.add(new ContestPhaseBean(16L, startDateEndDates.get(1).getStartDate(), startDateEndDates.get(1).getEndDate(), "PROMOTE_DONE", true));
        contestPhaseBeans.add(new ContestPhaseBean(18L, startDateEndDates.get(2).getStartDate(), startDateEndDates.get(2).getEndDate(), "PROMOTE", false));
        contestPhaseBeans.add(new ContestPhaseBean(19L, startDateEndDates.get(3).getStartDate(), startDateEndDates.get(3).getEndDate(), "PROMOTE_JUDGED", true));
        contestPhaseBeans.add(new ContestPhaseBean(20L, startDateEndDates.get(4).getStartDate(), startDateEndDates.get(4).getEndDate(), "", false));
        contestPhaseBeans.add(new ContestPhaseBean(14L, startDateEndDates.get(4).getEndDate()));
        return  contestPhaseBeans;
    }


    public static ContestSchedule createNewSchedule() throws Exception{
        Long newContestScheduleId = CounterLocalServiceUtil.increment(ContestSchedule.class.getName());
        ContestSchedule newContestSchedule = ContestScheduleLocalServiceUtil.createContestSchedule(newContestScheduleId);
        newContestSchedule.setName("New contest schedule");
        newContestSchedule.persist();
        ContestScheduleLocalServiceUtil.updateContestSchedule(newContestSchedule);
        return newContestSchedule;
    }

}

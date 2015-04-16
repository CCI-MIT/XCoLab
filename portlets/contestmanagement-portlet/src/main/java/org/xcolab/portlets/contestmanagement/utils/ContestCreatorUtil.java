package org.xcolab.portlets.contestmanagement.utils;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.PlanSection;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.PlanTemplate;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.FocusAreaOntologyTermLocalServiceUtil;
import com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateLocalServiceUtil;
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
import com.liferay.portal.theme.ThemeDisplay;
import org.xcolab.enums.ContestTier;
import org.xcolab.portlets.contestmanagement.wrappers.ContestScheduleWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class used to automatically create sets of new contests. Uses JSON data to create these contests.
 *
 * Created by Mente on 27/02/15.
 */
public class ContestCreatorUtil {
    private static Log _log = LogFactoryUtil.getLog(ContestCreatorUtil.class);

    private static final String CONTEST_NAME_KEY = "name";
    private static final String CONTEST_TIER_KEY = "contestTier";
    private static final String CONTEST_FOCUS_AREA_KEY = "focusArea";
    private static final String CONTEST_ONTOLOGY_TERMS_KEY = "ontologyTerms";
    private static final String CONTEST_SCHEDULE_KEY = "contestSchedule";

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
                "{\"name\": \"United States Plan\", \"contestTier\": 3, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1300340,1,1300601], " +
                // US Tier III plan template
                "\"planTemplate\" : {\"name\" : \"Tier III US Contest\", \"sections\" : [" +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the energy sector?\", \"adminTitle\" : \"Tier III US Energy sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the energy sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [409,1300340,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the transportation sector?\", \"adminTitle\" : \"Tier III US Transportation sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the transportation sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300379,1300340,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the industrial sector?\", \"adminTitle\" : \"Tier III US Industrial sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the industrial sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300382,1300340,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the buildings sector?\", \"adminTitle\" : \"Tier III US Buildings sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the buildings sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300378,1300340,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for other sectors?\", \"adminTitle\" : \"Tier III US Other sectors single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in other sectors, such as agriculture, aquaculture, livestock, forestry, and waste management.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [2,1300340,3,1300601]," +
                    "\"tier\" : 2}," +
                    // Other sections
                    "{\"id\": 1301502},{\"id\": 1301504},{\"id\": 1301505},{\"id\": 1301506},{\"id\": 1301507}, {\"id\": 207}, {\"id\": 208}, {\"id\": 1300909}, {\"id\": 210}" +
                "]}," +
                // US Tier III sub contests
                "\"subContests\": [" +
                    "{\"name\": \"Buildings in the United States\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300378,1300340,1,1300601]},\n" +
                    "{\"name\": \"Energy Supply in the United States\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [409,1300340,1,1300601]},\n" +
                    "{\"name\": \"Industry in the United States\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300382,1300340,1,1300601]},\n" +
                    "{\"name\": \"Transportation in the United States\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300379,1300340,1,1300601]},\n" +
                    "{\"name\": \"Other Sectors in the United States\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1300340,1,1300601]},\n" +
                "]},\n" +
                // ##################### US Tier III done #####################

                "{\"name\": \"European Union Plan\", \"contestTier\": 3, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1300341,1,1300601], " +
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
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the buildings sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300378,1300341,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for other sectors?\", \"adminTitle\" : \"Tier III EU Other sectors single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in other sectors, such as agriculture, aquaculture, livestock, forestry, and waste management.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [2,1300341,3,1300601]," +
                    "\"tier\" : 2}," +
                    // Other sections
                    "{\"id\": 1301502},{\"id\": 1301504},{\"id\": 1301505},{\"id\": 1301506},{\"id\": 1301507}, {\"id\": 207}, {\"id\": 208}, {\"id\": 1300909}, {\"id\": 210}" +
                "]}," +
                // EU Tier III sub contests
                "\"subContests\": [" +
                    "{\"name\": \"Buildings in the European Union\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300378,1300341,1,1300601]},\n" +
                    "{\"name\": \"Energy Supply in the European Union\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [409,1300341,1,1300601]},\n" +
                    "{\"name\": \"Industry in the European Union\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300382,1300341,1,1300601]},\n" +
                    "{\"name\": \"Transportation in the European Union\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300379,1300341,1,1300601]},\n" +
                    "{\"name\": \"Other Sectors in the European Union\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1300341,1,1300601]},\n" +
                "]},\n" +
                // ##################### EU Tier III done #####################

                "{\"name\": \"China Plan\", \"contestTier\": 3, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1300345,1,1300601], " +
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
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the buildings sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300378,1300345,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for other sectors?\", \"adminTitle\" : \"Tier III China Other sectors single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in other sectors, such as agriculture, aquaculture, livestock, forestry, and waste management.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [2,1300345,3,1300601]," +
                    "\"tier\" : 2}," +
                    // Other sections
                    "{\"id\": 1301502},{\"id\": 1301504},{\"id\": 1301505},{\"id\": 1301506},{\"id\": 1301507}, {\"id\": 207}, {\"id\": 208}, {\"id\": 1300909}, {\"id\": 210}" +
                "]}," +
                // China Tier III sub contests
                "\"subContests\": ["  +
                    "{\"name\": \"Buildings in China\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300378,1300345,1,1300601]},\n" +
                    "{\"name\": \"Energy Supply in China\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [409,1300345,1,1300601]},\n" +
                    "{\"name\": \"Industry in China\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300382,1300345,1,1300601]},\n" +
                    "{\"name\": \"Transportation in China\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300379,1300345,1,1300601]},\n" +
                    "{\"name\": \"Other Sectors in China\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1300345,1,1300601]}\n" +
                "]},\n" +
                // ##################### China Tier III done #####################


                "{\"name\": \"India Plan\", \"contestTier\": 3, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1300346,1,1300601], " +
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
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the buildings sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300378,1300346,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for other sectors?\", \"adminTitle\" : \"Tier III India Other sectors single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in other sectors, such as agriculture, aquaculture, livestock, forestry, and waste management.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [2,1300346,3,1300601]," +
                    "\"tier\" : 2}," +
                    // Other sections
                    "{\"id\": 1301502},{\"id\": 1301504},{\"id\": 1301505},{\"id\": 1301506},{\"id\": 1301507}, {\"id\": 207}, {\"id\": 208}, {\"id\": 1300909}, {\"id\": 210}" +
                "]}," +
                // India Tier III sub contests
                "\"subContests\": ["  +
                    "{\"name\": \"Buildings in India\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300378,1300346,1,1300601]},\n" +
                    "{\"name\": \"Energy Supply in India\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [409,1300346,1,1300601]},\n" +
                    "{\"name\": \"Industry in India\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300382,1300346,1,1300601]},\n" +
                    "{\"name\": \"Transportation in India\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300379,1300346,1,1300601]},\n" +
                    "{\"name\": \"Other Sectors in India\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1300346,1,1300601]}\n" +
                "]},\n" +
                // ##################### India Tier III done #####################

                "{\"name\": \"Other Developed Economies Plan\", \"contestTier\": 3, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1311101,1,1300601], " +
                "\"planTemplate\" : {\"name\" : \"Tier III Other Developed Economies Contest\", \"sections\" : [" +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the energy sector?\", \"adminTitle\" : \"Tier III Other Developed Economies Energy sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the energy sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [409,1311101,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the transportation sector?\", \"adminTitle\" : \"Tier III Other Developed Economies Transportation sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the transportation sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300379,1311101,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the industrial sector?\", \"adminTitle\" : \"Tier III Other Developed Economies Industrial sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the industrial sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300382,1311101,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the buildings sector?\", \"adminTitle\" : \"Tier III Other Developed Economies Buildings sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the buildings sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300378,1311101,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for other sectors?\", \"adminTitle\" : \"Tier III Other Developed Economies Other sectors single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in other sectors, such as agriculture, aquaculture, livestock, forestry, and waste management.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [2,1311101,3,1300601]," +
                    "\"tier\" : 2}," +
                    // Other sections
                    "{\"id\": 1301502},{\"id\": 1301504},{\"id\": 1301505},{\"id\": 1301506},{\"id\": 1301507}, {\"id\": 207}, {\"id\": 208}, {\"id\": 1300909}, {\"id\": 210}" +
                "]}," +
                // Other Developed Economies Tier III sub contests
                "\"subContests\": [" +
                    "{\"name\": \"Buildings in Other Developed Economies\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300378,1311101,1,1300601]},\n" +
                    "{\"name\": \"Energy Supply in Other Developed Economies\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [409,1311101,1,1300601]},\n" +
                    "{\"name\": \"Industry in Other Developed Economies\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300382,1311101,1,1300601]},\n" +
                    "{\"name\": \"Transportation in Other Developed Economies\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300379,1311101,1,1300601]},\n" +
                    "{\"name\": \"Other Sectors in Other Developed Economies\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1311101,1,1300601]}\n" +
                "]},\n" +
                // ##################### Other developed economies Tier III done #####################

                "{\"name\": \"Other Developing Economies Plan\", \"contestTier\": 3, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1311102,1,1300601], " +
                "\"planTemplate\" : {\"name\" : \"Tier III Other Developing Economies Contest\", \"sections\" :[" +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the energy sector?\", \"adminTitle\" : \"Tier III Other Developing Economies Energy sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the energy sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [409,1311102,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the transportation sector?\", \"adminTitle\" : \"Tier III Other Developing Economies Transportation sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the transportation sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300379,1311102,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the industrial sector?\", \"adminTitle\" : \"Tier III Other Developing Economies Industrial sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the industrial sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300382,1311102,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for the buildings sector?\", \"adminTitle\" : \"Tier III Other Developing Economies Buildings sector single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in the buildings sector.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [1300378,1311102,3,1300601]," +
                    "\"tier\" : 2}," +
                    "{\"id\": 0, \"title\" : \"Which plan do you select for other sectors?\", \"adminTitle\" : \"Tier III Other Developing Economies Other sectors single proposal picker\"," +
                    "\"type\" : \"PROPOSAL_REFERENCE\"," +
                    "\"helpText\" : \"Select one plan to represent the actions that should be taken in other sectors, such as agriculture, aquaculture, livestock, forestry, and waste management.\", " +
                    "\"focusArea\" : 0, \"ontologyTerms\" : [2,1311102,3,1300601]," +
                    "\"tier\" : 2}," +
                    // Other sections
                    "{\"id\": 1301502},{\"id\": 1301504},{\"id\": 1301505},{\"id\": 1301506},{\"id\": 1301507}, {\"id\": 207}, {\"id\": 208}, {\"id\": 1300909}, {\"id\": 210}" +
                "]}," +
                // Other Developed Economies Tier III sub contests
                "\"subContests\": [" +
                    "{\"name\": \"Buildings in Other Developing countries\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300378,1311102,1,1300601]},\n" +
                    "{\"name\": \"Energy Supply in Other Developing Economies\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [409,1311102,1,1300601]},\n" +
                    "{\"name\": \"Industry in Other Developing Economies\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300382,1311102,1,1300601]},\n" +
                    "{\"name\": \"Transportation in Other Developing Economies\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300379,1311102,1,1300601]},\n" +
                    "{\"name\": \"Other Sectors in Other Developing Economies\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1311102,1,1300601]}\n" +
                "]},\n" +
                // ##################### Other developing economies Tier III done #####################
            "]";



    public static Map<String, String> create2015BasicContests(String portalBaseURL) {
        return createContestsWithJSON(BASIC_CONTESTS_2015_JSON, portalBaseURL);
    }

    public static Map<String, String> create2015Tier2And3Contests(String portalBaseURL) {
        return createContestsWithJSON(TIER_III_AND_II_CONTESTS_2015_JSON, portalBaseURL);
    }


    public static Contest createNewContest(String contestShortName) throws SystemException, PortalException {
        Contest contest = ContestLocalServiceUtil.createNewContest(10144L, contestShortName);
        contest.setContestPrivate(true);
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

                    // Set planTemplate
                    PlanTemplate contestPlanTemplate = createPlanTemplateWithJson(newContestContents.getJSONObject("planTemplate"), newContest);
                    newContest.setPlanTemplateId(contestPlanTemplate.getId());
                    newContest.persist();

                    // Create sub contests, if there are any
                    final JSONArray subContestsJson = newContestContents.getJSONArray("subContests");
                    if (Validator.isNotNull(subContestsJson)) {
                        List<Contest> subContests = createSubContests(subContestsJson);
                        String descriptionTemplate = getSubContestHTMLList(subContests);
                        newContest.setContestDescription("&lt;Enter description here&gt; <br/><br/> " + descriptionTemplate);
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

        Contest newContest = createNewContest(newContestContents.getString(CONTEST_NAME_KEY));
        newContest.setContestTier(newContestContents.getInt(CONTEST_TIER_KEY));
        newContest.setContestScheduleId(newContestContents.getLong(CONTEST_SCHEDULE_KEY));

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

        newContest.persist();

        // Install contest schedule
        ContestScheduleWrapper.createContestPhaseAccordingToContestSchedule(newContest, newContestContents.getLong(CONTEST_SCHEDULE_KEY));
        return newContest;
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
}
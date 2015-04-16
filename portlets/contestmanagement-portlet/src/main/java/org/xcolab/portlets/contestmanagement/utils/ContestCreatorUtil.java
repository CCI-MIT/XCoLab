package org.xcolab.portlets.contestmanagement.utils;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.FocusAreaOntologyTermLocalServiceUtil;
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
                "{\"name\": \"United States Plan\", \"contestTier\": 3, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1300340,1,1300601], \"subContests\": [" +
                    "{\"name\": \"Buildings in the United States\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300378,1300340,1,1300601]},\n" +
                    "{\"name\": \"Energy Supply in the United States\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [409,1300340,1,1300601]},\n" +
                    "{\"name\": \"Industry in the United States\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300382,1300340,1,1300601]},\n" +
                    "{\"name\": \"Transportation in the United States\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300379,1300340,1,1300601]},\n" +
                    "{\"name\": \"Other Sectors in the United States\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1300340,1,1300601]},\n" +
                "]},\n" +
                "{\"name\": \"European Union Plan\", \"contestTier\": 3, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1300341,1,1300601], \"subContests\": [" +
                    "{\"name\": \"Buildings in the European Union\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300378,1300341,1,1300601]},\n" +
                    "{\"name\": \"Energy Supply in the European Union\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [409,1300341,1,1300601]},\n" +
                    "{\"name\": \"Industry in the European Union\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300382,1300341,1,1300601]},\n" +
                    "{\"name\": \"Transportation in the European Union\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300379,1300341,1,1300601]},\n" +
                    "{\"name\": \"Other Sectors in the European Union\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1300341,1,1300601]},\n" +
                "]},\n" +
                "{\"name\": \"China Plan\", \"contestTier\": 3, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1300345,1,1300601], \"subContests\": ["  +
                    "{\"name\": \"Buildings in China\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300378,1300345,1,1300601]},\n" +
                    "{\"name\": \"Energy Supply in China\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [409,1300345,1,1300601]},\n" +
                    "{\"name\": \"Industry in China\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300382,1300345,1,1300601]},\n" +
                    "{\"name\": \"Transportation in China\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300379,1300345,1,1300601]},\n" +
                    "{\"name\": \"Other Sectors in China\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1300345,1,1300601]}\n" +
                "]},\n" +
                "{\"name\": \"India Plan\", \"contestTier\": 3, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1300346,1,1300601], \"subContests\": ["  +
                    "{\"name\": \"Buildings in India\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300378,1300346,1,1300601]},\n" +
                    "{\"name\": \"Energy Supply in India\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [409,1300346,1,1300601]},\n" +
                    "{\"name\": \"Industry in India\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300382,1300346,1,1300601]},\n" +
                    "{\"name\": \"Transportation in India\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300379,1300346,1,1300601]},\n" +
                    "{\"name\": \"Other Sectors in India\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1300346,1,1300601]}\n" +
                "]},\n" +
                "{\"name\": \"Other Developed Economies Plan\", \"contestTier\": 3, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1311101,1,1300601], \"subContests\": [" +
                    "{\"name\": \"Buildings in Other Developed Economies\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300378,1311101,1,1300601]},\n" +
                    "{\"name\": \"Energy Supply in Other Developed Economies\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [409,1311101,1,1300601]},\n" +
                    "{\"name\": \"Industry in Other Developed Economies\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300382,1311101,1,1300601]},\n" +
                    "{\"name\": \"Transportation in Other Developed Economies\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300379,1311101,1,1300601]},\n" +
                    "{\"name\": \"Other Sectors in Other Developed Economies\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1311101,1,1300601]}\n" +
                "]},\n" +
                "{\"name\": \"Other Developing Economies Plan\", \"contestTier\": 3, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1311102,1,1300601], \"subContests\": [" +
                    "{\"name\": \"Buildings in Other Developing countries\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300378,1311102,1,1300601]},\n" +
                    "{\"name\": \"Energy Supply in Other Developing Economies\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [409,1311102,1,1300601]},\n" +
                    "{\"name\": \"Industry in Other Developing Economies\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300382,1311102,1,1300601]},\n" +
                    "{\"name\": \"Transportation in Other Developing Economies\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [1300379,1311102,1,1300601]},\n" +
                    "{\"name\": \"Other Sectors in Other Developing Economies\", \"contestTier\": 2, \"contestSchedule\": 3, \"focusArea\": 0, \"ontologyTerms\": [2,1311102,1,1300601]}\n" +
                "]},\n" +
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

                    // Create sub contests, if there are any
                    final JSONArray subContestsJson = newContestContents.getJSONArray("subContests");
                    if (Validator.isNotNull(subContestsJson)) {
                        List<Contest> subContests = createSubContests(subContestsJson);
                        String descriptionTemplate = getSubContestHTMLList(subContests);

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

        long focusAreaId = newContestContents.getLong(CONTEST_FOCUS_AREA_KEY);
        if (focusAreaId == 0) {
            JSONArray ontologyTermIds = newContestContents.getJSONArray(CONTEST_ONTOLOGY_TERMS_KEY);

            // Create new focus area with the specified ontology terms
            if (ontologyTermIds.length() > 0) {
                FocusArea focusArea = FocusAreaLocalServiceUtil.createFocusArea(CounterLocalServiceUtil.increment(FocusArea.class.getName()));
                focusArea.setName("created by 2015 " + newContest.getContestName());
                focusArea.persist();

                focusAreaId = focusArea.getId();
                FocusAreaLocalServiceUtil.updateFocusArea(focusArea);

                for (int j = 0; j < ontologyTermIds.length(); j++) {
                    FocusAreaOntologyTermLocalServiceUtil.addAreaTerm(focusAreaId, ontologyTermIds.getLong(j));
                }

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
}

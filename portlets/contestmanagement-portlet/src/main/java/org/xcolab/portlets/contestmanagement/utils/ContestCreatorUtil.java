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
import com.liferay.portal.theme.ThemeDisplay;

import java.util.HashMap;
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


    public static Map<String, String> create2015BasicContests(String portalBaseURL) {
        return createContestsWithJSON(BASIC_CONTESTS_2015_JSON, portalBaseURL);
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

    private static Map<String, String> createContestsWithJSON(String json, String portalBaseUrl) {

        JSONArray contestDataArray = null;
        try {
            contestDataArray = JSONFactoryUtil.createJSONArray(json);

            Map<String, String> contestEditMap = new HashMap<>(contestDataArray.length());
            for (int i = 0; i < contestDataArray.length(); i++) {
                JSONObject newContestContents = contestDataArray.getJSONObject(i);
                try {
                    Contest newContest = createNewContest(newContestContents.getString(CONTEST_NAME_KEY));
                    newContest.setContestTier(newContestContents.getInt(CONTEST_TIER_KEY));
                    newContest.setContestScheduleId(newContestContents.getLong(CONTEST_SCHEDULE_KEY));

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

                    String absoluteURL = portalBaseUrl + getContestEditLink(newContest);
                    contestEditMap.put(newContest.getContestShortName(), absoluteURL);
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

}

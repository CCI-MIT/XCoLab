package org.xcolab.view.widgets.proposals;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.commons.attributes.AttributeGetter;
import org.xcolab.util.i18n.I18nUtils;
import org.xcolab.view.widgets.WidgetPreference;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RandomProposalsPreferences extends WidgetPreference {

    private static final String SELECTED_PHASES_PREFERENCE = "SELECTED_PHASES";
    private static final String FLAG_FILTER_PREFERENCE = "FLAG_FILTERS";
    private static final String TITLE_PREFERENCE = "TITLE";
    private static final String FEED_SIZE_PREFERENCE = "FEED_SIZE";
    private static final String ALL_PROPOSALS_TITLE = "ALL_PROPOSALS_TITLE";
    private static final String ALL_PROPOSALS_URL = "ALL_PROPOSALS_URL";
    private static final String IS_COMPACT = "IS_COMPACT";

    private Long[] selectedPhases;
    private Long[] flagFilters;
    private String flagFiltersStr;
    private String title;
    private Integer feedSize;
    private Boolean isCompact;
    private String allProposalsUrl;
    private String allProposalsTitle;

    public RandomProposalsPreferences() {
        this(null, I18nUtils.DEFAULT_LANGUAGE);
    }

    public RandomProposalsPreferences(String preferenceId, String language) {
        super(preferenceId, language);

        selectedPhases = convertStringsToLongs(
                jsonPreferences.optString(SELECTED_PHASES_PREFERENCE, "").split("-"));
        flagFiltersStr = jsonPreferences.optString(FLAG_FILTER_PREFERENCE, "");
        flagFilters = convertStringsToLongs(flagFiltersStr.split(","));
        title = jsonPreferences.optString(TITLE_PREFERENCE, "Interesting Proposals");
        allProposalsTitle = jsonPreferences.optString(ALL_PROPOSALS_TITLE, "see all finalists");
        allProposalsUrl = jsonPreferences.optString(ALL_PROPOSALS_URL,
                "/community/-/blogs/finalists-selected-vote-to-select-popular-choice-winner-2"
                        + "#Vote");
        isCompact = jsonPreferences.optBoolean(IS_COMPACT, false);
        feedSize = jsonPreferences.optInt(FEED_SIZE_PREFERENCE, 4);
    }
    
    private static Long[] convertStringsToLongs(String[] arrayStr) {
        if (arrayStr.length == 1 && StringUtils.isBlank(arrayStr[0])) {
            return new Long[]{};
        }

        Long[] arrayLong = new Long[arrayStr.length];
        for (int i = 0; i < arrayStr.length; i++) {
            try {
                arrayLong[i] = Long.parseLong(arrayStr[i]);
            } catch (NumberFormatException e) {
                arrayLong[i] = null;
            }
        }
        return arrayLong;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getFeedSize() {
        return feedSize;
    }

    public void setFeedSize(Integer feedSize) {
        this.feedSize = feedSize;
    }

    @Override
    public AttributeGetter<String> getConfigurationAttribute() {
        return ConfigurationAttributeKey.PORTLET_RANDOM_PROPOSALS_PREFERENCES;
    }

    @Override
    public void savePreferences() {
        JSONObject preferences = new JSONObject();

        preferences.put(SELECTED_PHASES_PREFERENCE,
                StringUtils.join(convertLongsToStrings(selectedPhases), "-"));
        preferences.put(FLAG_FILTER_PREFERENCE, flagFiltersStr);
        preferences.put(TITLE_PREFERENCE, title);
        preferences.put(FEED_SIZE_PREFERENCE, feedSize + "");
        preferences.put(ALL_PROPOSALS_TITLE, allProposalsTitle);
        preferences.put(ALL_PROPOSALS_URL, allProposalsUrl);
        preferences.put(IS_COMPACT, Boolean.toString(isCompact));

        savePreferencesInternal(preferences, preferenceId);

    }

    private static String[] convertLongsToStrings(Long[] arrayLong) {
        String[] arrayStr = new String[arrayLong.length];
        for (int i = 0; i < arrayLong.length; i++) {
            arrayStr[i] = arrayLong[i].toString();
        }
        return arrayStr;
    }

    public Map<Long, String> getContestPhases() {

        List<ContestWrapper> contests = StaticContestContext.getContestClient().getAllContests();

        contests.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));

        Map<Long, String> phases = new LinkedHashMap<>();
        for (ContestWrapper c : contests) {
            for (ContestPhaseWrapper cp : StaticContestContext.getContestClient()
                    .getVisibleContestPhases(c.getId())) {
                String prefix = "";
                if (cp.getPhaseActive()) {
                    prefix = "* ACTIVE *";
                }
                phases.put(cp.getId(),
                        String.format("%d %s %s - %d %s", c.getId(), prefix,
                                c.getTitleWithEndYear(), cp.getId(),
                                cp.getContestStatusStr()));
            }
        }

        return phases;
    }

    public Long[] getSelectedPhases() {
        return selectedPhases;
    }

    public void setSelectedPhases(Long[] selectedPhases) {
        this.selectedPhases = selectedPhases;
    }

    public Long[] getFlagFilters() {
        return flagFilters;
    }

    public void setFlagFilters(Long[] flagFilters) {
        this.flagFilters = flagFilters;
    }

    public String getFlagFiltersStr() {
        return flagFiltersStr;
    }

    public void setFlagFiltersStr(String flagFiltersStr) {
        // check if flag filters are correct
        if (StringUtils.isNotBlank(flagFiltersStr)) {
            // parse all values to check if they are valid ints
            for (String tmp : flagFiltersStr.split(",")) {
                Integer.parseInt(tmp);
            }
        }
        // if we reach this point then there was no parse exception, we can proceed
        this.flagFiltersStr = flagFiltersStr;
    }

    public String getAllProposalsTitle() {
        return allProposalsTitle;
    }

    public void setAllProposalsTitle(String allProposalsTitle) {
        this.allProposalsTitle = allProposalsTitle;
    }

    public String getAllProposalsUrl() {
        return allProposalsUrl;
    }

    public void setAllProposalsUrl(String allProposalsUrl) {
        this.allProposalsUrl = allProposalsUrl;
    }

    public Boolean getCompact() {
        return isCompact;
    }

    public void setCompact(Boolean compact) {
        isCompact = compact;
    }
}

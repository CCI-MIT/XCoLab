package org.xcolab.portlets.randomproposals;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

public class RandomProposalsPreferences {
    private final static String SELECTED_PHASES_PREFERENCE = "SELECTED_PHASES";
    private final static String FLAG_FILTER_PREFERENCE = "FLAG_FILTERS";
    private final static String TITLE_PREFERENCE = "TITLE";
    private final static String FEED_SIZE_PREFERENCE = "FEED_SIZE";
    private final static String ALL_PROPOSALS_TITLE = "ALL_PROPOSALS_TITLE";
    private final static String ALL_PROPOSALS_URL = "ALL_PROPOSALS_URL";
    private final static String IS_COMPACT = "IS_COMPACT";

    private Long[] selectedPhases;
    private Long[] flagFilters;
    private String flagFiltersStr;
    private String title;
    private Integer feedSize;
    private Boolean isCompact;
    private String allProposalsUrl;
    private String allProposalsTitle;

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

    public RandomProposalsPreferences() {}
    
    public RandomProposalsPreferences(PortletRequest request) {
    	PortletPreferences prefs = request.getPreferences();
        selectedPhases = convertStringsToLongs(prefs.getValue(SELECTED_PHASES_PREFERENCE, "").split(","));
        flagFiltersStr = prefs.getValue(FLAG_FILTER_PREFERENCE, "");
        title = prefs.getValue(TITLE_PREFERENCE, "Interesting Proposals");
        allProposalsTitle = prefs.getValue(ALL_PROPOSALS_TITLE, "see all finalists");
        allProposalsUrl = prefs.getValue(ALL_PROPOSALS_URL,
                "/community/-/blogs/finalists-selected-vote-to-select-popular-choice-winner-2#Vote");
        isCompact = Boolean.parseBoolean(prefs.getValue(IS_COMPACT, "false"));
        try {
            feedSize = Integer.parseInt(prefs.getValue(FEED_SIZE_PREFERENCE, "4"));
        } catch (NumberFormatException e) {
            feedSize = 4;
        }
        flagFilters = convertStringsToLongs(flagFiltersStr.split(","));
        
    }
    
    public String submit(PortletRequest request) throws ReadOnlyException, ValidatorException, IOException {
        PortletPreferences prefs = request.getPreferences();
    	
        prefs.setValue(SELECTED_PHASES_PREFERENCE, StringUtils.join(convertLongsToStrings(selectedPhases), ","));
        prefs.setValue(FLAG_FILTER_PREFERENCE, flagFiltersStr);
        prefs.setValue(TITLE_PREFERENCE, title);
        prefs.setValue(FEED_SIZE_PREFERENCE, feedSize+"");
        prefs.setValue(ALL_PROPOSALS_TITLE, allProposalsTitle);
        prefs.setValue(ALL_PROPOSALS_URL, allProposalsUrl);
        prefs.setValue(IS_COMPACT, Boolean.toString(isCompact));

        prefs.store();
        
        return null;
    }
    
    
    public Map<Long,String> getContestPhases() {

        List<Contest> contests = ContestClientUtil.getAllContests();

        Collections.sort(contests, new Comparator<Contest>() {
            @Override
            public int compare(Contest o1, Contest o2) {
                return (int) (o1.getContestPK() - o2.getContestPK());
            }
        });

        Map<Long, String> phases = new LinkedHashMap<>();
        for (Contest c : contests) {
            for (ContestPhase cp : ContestClientUtil.getVisibleContestPhases(c.getContestPK())) {
                String prefix = "";
                if (cp.getPhaseActive()) {
                    prefix = "* ACTIVE *";
                }
                phases.put(cp.getContestPhasePK(),
                        String.format("%d %s %s - %d %s",
                                c.getContestPK(),
                                prefix,
                                c.getContestShortName(),
                                cp.getContestPhasePK(),
                                cp.getContestStatusStr()
                        ));
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
    
    private static Long[] convertStringsToLongs(String[] arrayStr) {
        if (arrayStr.length == 1 && StringUtils.isBlank(arrayStr[0])) { 
            return new Long[] {};
        }
        
        Long[] arrayLong = new Long[arrayStr.length];
        for (int i = 0; i < arrayStr.length; i++) {
            try {
                arrayLong[i] = Long.parseLong(arrayStr[i]);
            }
            catch (NumberFormatException e) {
                arrayLong[i] = null;
            }
        }
        return arrayLong;
    }

    private static String[] convertLongsToStrings(Long[] arrayLong) {
        String[] arrayStr = new String[arrayLong.length];
        for (int i = 0; i < arrayLong.length; i++) {
            arrayStr[i] = arrayLong[i].toString();
        }
        return arrayStr;
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
            for (String tmp: flagFiltersStr.split(",")) {
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

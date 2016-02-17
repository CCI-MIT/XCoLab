package org.xcolab.portlets.randomproposals;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.apache.commons.lang3.StringUtils;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RandomProposalsPreferences {
    private final static String SELECTED_PHASES_PREFERENCE = "SELECTED_PHASES";
    private final static String FLAG_FILTER_PREFERENCE = "FLAG_FILTERS";
    private final static String TITLE_PREFERENCE = "TITLE";
    private final static String FEED_SIZE_PREFERENCE = "FEED_SIZE";
    private final static String ALL_PROPOSALS_TITLE = "ALL_PROPOSALS_TITLE";
    private final static String ALL_PROPOSALS_URL = "ALL_PROPOSALS_URL";

    private Long[] selectedPhases;
    private Long[] flagFilters;
    private String flagFiltersStr;
    private String title;
    private Integer feedSize;
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
        try {
            feedSize = Integer.parseInt(prefs.getValue(FEED_SIZE_PREFERENCE, "4"));
        } catch (NumberFormatException e) {
            feedSize = 4;
        }
        flagFilters = convertStringsToLongs(flagFiltersStr.split(","));
        
    }
    
    public String submit(PortletRequest request) throws ReadOnlyException, ValidatorException, IOException, PortalException, SystemException {
        PortletPreferences prefs = request.getPreferences();
    	
        prefs.setValue(SELECTED_PHASES_PREFERENCE, StringUtils.join(convertLongsToStrings(selectedPhases), ","));
        prefs.setValue(FLAG_FILTER_PREFERENCE, flagFiltersStr);
        prefs.setValue(TITLE_PREFERENCE, title);
        prefs.setValue(FEED_SIZE_PREFERENCE, feedSize+"");
        prefs.setValue(ALL_PROPOSALS_TITLE, allProposalsTitle);
        prefs.setValue(ALL_PROPOSALS_URL, allProposalsUrl);

        prefs.store();
        
        return null;
    }
    
    
    public Map<Long,String> getContestPhases() throws SystemException, PortalException{
        Map<Long, String> phases = new LinkedHashMap<>();

        List<Contest> contests = ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE);

        Collections.sort(contests, new Comparator<Contest>() {
            @Override
            public int compare(Contest o1, Contest o2) {
                return (int) (o1.getContestPK() - o2.getContestPK());
            }
        });

        for (Contest c: contests) {
            for (ContestPhase cp: ContestLocalServiceUtil.getVisiblePhases(c)) {
                String prefix = "";
                if (ContestPhaseLocalServiceUtil.getPhaseActive(cp)) {
                   prefix = "* ACTIVE *";
                }
                phases.put(cp.getContestPhasePK(),
                        String.format("%d %s %s - %d %s",
                                c.getContestPK(),
                                prefix,
                                c.getContestShortName(),
                                cp.getContestPhasePK(),
                                ContestPhaseLocalServiceUtil.getContestStatusStr(cp)
                        )) ;
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
}

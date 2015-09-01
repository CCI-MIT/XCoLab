package org.xcolab.portlets.contests;

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

public class ContestPreferences {
    private Long[] selectedContests;
    private String SELECTED_CONTESTS_PREFERENCE = "SELECTED_CONTESTS";
    private String TITLE_PREFERENCE = "TITLE";
    private String FEED_SIZE_PREFERENCE = "FEED_SIZE";
    private String title;
    private Integer feedSize;
    private List<Contest> contests;

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

    public ContestPreferences() {}
    
    public ContestPreferences(PortletRequest request) {
    	PortletPreferences prefs = request.getPreferences();
        selectedContests = convertStringsToLongs(prefs.getValue(SELECTED_CONTESTS_PREFERENCE, "").split(","));
        title = prefs.getValue(TITLE_PREFERENCE, "Featured contests");
        try {
            feedSize = Integer.parseInt(prefs.getValue(FEED_SIZE_PREFERENCE, "4"));
        } catch (NumberFormatException e) {
            feedSize = 4;
        }
        try {
            contests = ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE);
        } catch (SystemException e) {
            e.printStackTrace();
        }
    }

    public String submit(PortletRequest request) throws ReadOnlyException, ValidatorException, IOException, PortalException, SystemException {
        PortletPreferences prefs = request.getPreferences();

        prefs.setValue(SELECTED_CONTESTS_PREFERENCE, StringUtils.join(convertLongsToStrings(selectedContests), ","));
        prefs.setValue(TITLE_PREFERENCE, title);
        prefs.setValue(FEED_SIZE_PREFERENCE, feedSize+"");

        prefs.store();

        return null;
    }

    public Long[] getSelectedContests() {
        return selectedContests;
    }

    public void setSelectedContests(Long[] selectedPhases) {
        this.selectedContests = selectedPhases;
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

    public void setContests(List<Contest> contests) {
        this.contests = contests;
    }

    public List<Contest> getContests() {
        return contests;
    }
}

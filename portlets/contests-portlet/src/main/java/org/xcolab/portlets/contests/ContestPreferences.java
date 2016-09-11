package org.xcolab.portlets.contests;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.client.contest.pojo.ContestPhaseType;
import org.xcolab.util.IdListUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

public class ContestPreferences {
    private List<Long> selectedContests;
    private final static String SELECTED_CONTESTS_PREFERENCE = "SELECTED_CONTESTS";
    private final static String TITLE_PREFERENCE = "CONTEST_TITLE";
    private final static String FEED_SIZE_PREFERENCE = "CONTEST_FEED_SIZE";
    private final static String ALL_CONTESTS_TITLE = "ALL_CONTESTS_TITLE";
    private final static String ALL_CONTESTS_URL = "ALL_CONTESTS_URL";
    private final static String SHOW_COUNTS = "SHOW_COUNTS";

    private String title;
    private Integer feedSize;
    private String allContestsUrl;
    private String allContestsTitle;
    private Boolean showCounts;
    private Map<Long, String> contestMap;

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
        selectedContests = IdListUtil
                .getIdsFromString(prefs.getValue(SELECTED_CONTESTS_PREFERENCE, ""));
        title = prefs.getValue(TITLE_PREFERENCE, "Featured contests");
        allContestsTitle = prefs.getValue(ALL_CONTESTS_TITLE, "see all contests");
        showCounts = Boolean.parseBoolean(prefs.getValue(SHOW_COUNTS, "true"));
        allContestsUrl = prefs.getValue(ALL_CONTESTS_URL, "/web/guest/plans");
        try {
            feedSize = Integer.parseInt(prefs.getValue(FEED_SIZE_PREFERENCE, "4"));
        } catch (NumberFormatException e) {
            feedSize = 4;
        }


        populateContestMap();
    }

    private void populateContestMap() {
            final List<Contest> contests = ContestClient.getAllContests();
            contestMap = new LinkedHashMap<>();

            Collections.sort(contests, new Comparator<Contest>() {
                @Override
                public int compare(Contest o1, Contest o2) {
                    final Date created1 = o1.getCreated();
                    final Date created2 = o2.getCreated();
                    if (created1 != null && created2 != null) {
                        if (created1.before(created2)) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                    return (int) (o2.getContestPK() - o1.getContestPK());
                }
            });

            for (Contest c: contests) {
                final ContestPhase activeOrLastPhase = ContestClient.getActivePhase(c.getContestPK());
                final String phaseName;
                if (activeOrLastPhase != null) {
                    final long contestPhaseTypeId = activeOrLastPhase.getContestPhaseType();
                    final ContestPhaseType contestPhaseType= ContestClient.getContestPhaseType(contestPhaseTypeId);
                    phaseName = contestPhaseType.getName();
                } else {
                    phaseName = " ";
                }
                contestMap.put(c.getContestPK(),
                        String.format("%d [%s] %s",
                                c.getContestPK(),
                                phaseName,
                                c.getContestShortName()
                        ));
            }

    }

    public String submit(PortletRequest request)
            throws ReadOnlyException, ValidatorException, IOException {
        PortletPreferences prefs = request.getPreferences();

        prefs.setValue(SELECTED_CONTESTS_PREFERENCE, IdListUtil.getStringFromIds(selectedContests));
        prefs.setValue(TITLE_PREFERENCE, title);
        prefs.setValue(FEED_SIZE_PREFERENCE, feedSize+"");
        prefs.setValue(ALL_CONTESTS_TITLE, allContestsTitle);
        prefs.setValue(SHOW_COUNTS, Boolean.toString(showCounts));
        prefs.setValue(ALL_CONTESTS_URL, allContestsUrl);

        prefs.store();

        return null;
    }

    public List<Long> getSelectedContests() {
        return selectedContests;
    }

    public void setSelectedContests(List<Long> selectedContests) {
        this.selectedContests = selectedContests;
    }

    public Map<Long, String> getContestMap() {
        return contestMap;
    }

    public void setContestMap(Map<Long, String> contestMap) {
        this.contestMap = contestMap;
    }

    public String getAllContestsUrl() {
        return allContestsUrl;
    }

    public void setAllContestsUrl(String allContestsUrl) {
        this.allContestsUrl = allContestsUrl;
    }

    public String getAllContestsTitle() {
        return allContestsTitle;
    }

    public void setAllContestsTitle(String allContestsTitle) {
        this.allContestsTitle = allContestsTitle;
    }

    public Boolean getShowCounts() {
        return showCounts;
    }

    public void setShowCounts(Boolean showCounts) {
        this.showCounts = showCounts;
    }
}

package org.xcolab.portlets.userprofile.entity;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.pojo.Proposal;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Badge implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int badgeType; // 1,2,3
    private final String badgeTitle;    // "Winner", "Finalist", "Semi-Finalist"
    private final String badgeText; // "Popular Choice", "Judges Choice", etc
    private final Contest contest;
    private final Proposal proposal;
    private final String planTitle;
    private int year = 2013;
    private final boolean hideRibbon;


    public Badge(int ribbonType, String ribbonText, Proposal proposal, String planTitle, Contest contest) {
        this.badgeType = ribbonType;
        this.planTitle = planTitle;
        this.proposal = proposal;
        this.badgeText = ribbonText;
        this.contest = contest;

        if (ribbonText.equalsIgnoreCase("Finalist") || ribbonText.equalsIgnoreCase("Judges' Special Commendation")) {
            this.badgeTitle = "Finalist";
        } else if (ribbonText.equalsIgnoreCase("Semi-Finalist")) {
            this.badgeTitle = "Semi-Finalist";
        } else {
            this.badgeTitle = "Winner";
        }

        // Associate the year and get hideRibbon property from contest
        hideRibbon = contest.getHideRibbons();

        ContestPhase lastPhase = ContestClientUtil.getActivePhase(contest.getContestPK());
        Date referenceDate =
                lastPhase.getPhaseEndDate() == null ? lastPhase.getPhaseStartDate() : lastPhase.getPhaseEndDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(referenceDate);

        year = cal.get(Calendar.YEAR);
    }

    public String getBadgeTitle() {
        return badgeTitle;
    }

    public String getBadgeText() {
        return badgeText;
    }

    public int getBadgeType() {
        return badgeType;
    }

    public int getBadgeYear() {
        return year;
    }

    public String getBadgeYearShort() {
        final String fullYear = Integer.toString(year);
        return fullYear.substring(2, fullYear.length());
    }

    public String getContestName() {
        return contest.getContestShortName();
    }

    public String getProposalLinkUrl() {
        return proposal.getProposalLinkUrl(contest);
    }

    public String getPlanTitle() {
        return planTitle;
    }

    public boolean isHideRibbon() {
        return hideRibbon;
    }

    @Override
    public String toString() {
        return String.format("BadgeType: %d ,BadgeTitle: %s, BadgeText: %s, Year: %d",
                badgeType, badgeTitle, badgeText, year);
    }
}

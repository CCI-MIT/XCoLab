package org.xcolab.view.pages.profile.entity;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.ContestWrapper;
import org.xcolab.client.contest.pojo.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.IContestPhaseRibbonType;
import org.xcolab.client.contest.pojo.Proposal;
import org.xcolab.commons.time.DateUtil;

import java.io.Serializable;
import java.util.Date;

public class Badge implements Serializable {

    private static final long serialVersionUID = 1L;

    private final IContestPhaseRibbonType ribbonType;
    private final ContestWrapper contest;
    private final Proposal proposal;
    private final String planTitle;
    private final boolean hideRibbon;
    private int year = 2013;


    public Badge(IContestPhaseRibbonType ribbonType, Proposal proposal, String planTitle,
            ContestWrapper contest) {
        this.ribbonType = ribbonType;
        this.planTitle = planTitle;
        this.proposal = proposal;
        this.contest = contest;

        // Associate the year and get hideRibbon property from contest
        hideRibbon = contest.getHideRibbons();

        ContestPhaseWrapper lastPhase = ContestClientUtil.getActivePhase(contest.getId());
        Date referenceDate =
                lastPhase.getPhaseEndDate() == null ? lastPhase.getPhaseStartDate()
                        : lastPhase.getPhaseEndDate();
        year = DateUtil.getYearFromDate(referenceDate) ;
    }

    public String getBadgeTitle() {
        return ribbonType.getTitle();
    }

    public String getBadgeText() {
        return ribbonType.getHoverText();
    }

    public Integer getBadgeType() {
        return ribbonType.getRibbon();
    }

    public int getBadgeYear() {
        return year;
    }

    public String getBadgeYearShort() {
        final String fullYear = Integer.toString(year);
        return fullYear.substring(2, fullYear.length());
    }

    public String getContestName() {
        return contest.getTitleWithEndYear();
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
        return String.format("Badge[type=%d, proposal=%d]",
                ribbonType.getId(), proposal.getId());
    }
}

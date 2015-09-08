package org.xcolab.portlets.userprofile.entity;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 9/9/13
 * Time: 12:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Badge implements Serializable {

    private static final long serialVersionUID = 1L;

    private int badgeType; // 1,2,3
    private long planId; // ID of connected Plan
    private String badgeTitle;    // "Winner", "Finalist", "Semi-Finalist"
    private String badgeText; // "Popular Choice", "Judges Choice", etc
    private int year = 2013;

    private long contestId;
    private String planTitle;


    public Badge(int ribbonType, String ribbonText, long planId, String planTitle, long contestId){
        this.badgeType = ribbonType;
        this.contestId = contestId;
        this.planTitle = planTitle;
        this.planId = planId;
        this.badgeText = ribbonText;

        if (ribbonText.equalsIgnoreCase("Finalist") || ribbonText.equalsIgnoreCase("Semi-Finalist")){
            this.badgeTitle = ribbonText;
        } else{
            this.badgeTitle = "Winner";
        }

        // Associate the year
        try {
            Contest contest = ContestLocalServiceUtil.getContest(contestId);
            ContestPhase lastPhase = ContestPhaseLocalServiceUtil.getActivePhaseForContest(contest);
            Date referenceDate = lastPhase.getPhaseEndDate() == null ? lastPhase.getPhaseStartDate() : lastPhase.getPhaseEndDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(referenceDate);

            year = cal.get(Calendar.YEAR);
        } catch (PortalException | SystemException e) {
            e.printStackTrace();
        }
    }

    public String getBadgeTitle(){
        return badgeTitle;
    }

    public String getBadgeText(){
        return badgeText;
    }

    public int getBadgeType(){
        return badgeType;
    }

    public int getBadgeYear(){
        return year;
    }

    public String getBadgeYearShort() {
        return Integer.toString(year).substring(2, 3);
    }

    public long getContestId(){
        return contestId;
    }

    public long getPlanId(){
        return planId;
    }

    public String getPlanTitle(){
        return planTitle;
    }

    @Override
    public String toString() {
        return "BadgeType: " + badgeType + " ,BadgeTitle: " + badgeTitle + ", BadgeText: " + badgeText + ", Year: " + year;
    }
}

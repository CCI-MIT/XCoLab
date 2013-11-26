package org.xcolab.portlets.userprofile.entity;

import java.io.Serializable;

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

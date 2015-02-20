package org.xcolab.portlets.contestmanagement.beans;


import com.ext.portlet.model.Contest;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.hibernate.validator.constraints.Length;
import org.xcolab.portlets.contestmanagement.wrappers.ContestScheduleWrapper;
import org.xcolab.portlets.contestmanagement.wrappers.WikiPageWrapper;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Thomas on 2/8/2015.
 */
public class ContestDescriptionBean implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long ContestPK;
    private Long contestLogoId;
    private Long sponsorLogoId;

    @Length(min = 5, max = 140, message = "The contest question must be at least 5 characters and not more than 140 characters.")
    private String contestName;

    @Length(min = 5, max = 50, message = "The contest name must be at least 5 characters and not more than 50 characters.")
    private String contestShortName;

    @Length(min = 5, max = 1300, message = "The contest description must be at least 5 characters and not more than 1300 characters.")
    private String contestDescription;

    @NotNull(message = "A plan template must be selected.")
    private Long planTemplateId;

    @NotNull(message = "A schedule template must be selected.")
    private Long scheduleTemplateId;

    @NotNull(message = "A contest tier must be selected.")
    private Long contestTier;

    public ContestDescriptionBean() {
    }

    public ContestDescriptionBean(Contest contest) {

        if(contest != null) {
            ContestPK = contest.getContestPK();
            contestName = contest.getContestName();
            contestShortName = contest.getContestShortName();
            contestDescription = contest.getContestDescription();
            planTemplateId = contest.getPlanTemplateId();
            scheduleTemplateId = contest.getContestScheduleId();
            contestTier = contest.getContestTier();
            contestLogoId = contest.getContestLogoId();
            sponsorLogoId = contest.getSponsorLogoId();
        }
    }

    public void persist(Contest contest) throws Exception {

        String oldContestTitle = contest.getContestShortName();
        String newContestTitle = contestShortName;
        contest.setContestName(contestName);
        contest.setContestShortName(contestShortName);
        contest.setContestDescription(contestDescription);
        contest.setPlanTemplateId(planTemplateId);
        contest.setContestScheduleId(scheduleTemplateId);
        contest.setContestLogoId(contestLogoId);
        contest.setSponsorLogoId(sponsorLogoId);
        contest.setContestTier(contestTier);
        contest.persist();
        updateContestSchedules(contest);
        WikiPageWrapper.updateWikiPageTitleIfExists(oldContestTitle, newContestTitle);

    }

    private void updateContestSchedules(Contest contest)throws Exception{
        ContestScheduleWrapper.createContestPhaseAccordingToContestSchedule(contest);
    }

    public Long getContestPK() {
        return ContestPK;
    }

    public void setContestPK(Long contestPK) {
        ContestPK = contestPK;
    }

    public Long getContestLogoId() {
        return contestLogoId;
    }

    public void setContestLogoId(Long contestLogoId) {
        this.contestLogoId = contestLogoId;
    }

    public Long getSponsorLogoId() {
        return sponsorLogoId;
    }

    public void setSponsorLogoId(Long sponsorLogoId) {
        this.sponsorLogoId = sponsorLogoId;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public String getContestShortName() {
        return contestShortName;
    }

    public void setContestShortName(String contestShortName) {
        this.contestShortName = contestShortName;
    }

    public String getContestDescription() {
        return contestDescription;
    }

    public void setContestDescription(String contestDescription) {
        this.contestDescription = contestDescription;
    }

    public Long getPlanTemplateId() {
        return planTemplateId;
    }

    public void setPlanTemplateId(Long planTemplateId) {
        this.planTemplateId = planTemplateId;
    }

    public Long getScheduleTemplateId() {
        return scheduleTemplateId;
    }

    public void setScheduleTemplateId(Long scheduleTemplateId) {
        this.scheduleTemplateId = scheduleTemplateId;
    }

    public Long getContestTier() {
        return contestTier;
    }

    public void setContestTier(Long contestTier) {
        this.contestTier = contestTier;
    }

}

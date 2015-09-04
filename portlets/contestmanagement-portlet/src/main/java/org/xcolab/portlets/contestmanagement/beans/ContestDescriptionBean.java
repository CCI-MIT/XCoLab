package org.xcolab.portlets.contestmanagement.beans;


import com.ext.portlet.model.Contest;
import org.hibernate.validator.constraints.Length;
import org.xcolab.portlets.contestmanagement.wrappers.ContestScheduleWrapper;
import org.xcolab.portlets.contestmanagement.wrappers.WikiPageWrapper;
import org.xcolab.wrapper.ContestWrapper;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by Thomas on 2/8/2015.
 */
public class ContestDescriptionBean implements Serializable{
    private static final long serialVersionUID = 1L;
    private static final String NO_SPECIAL_CHAR_REGEX ="^[a-zA-Z:,;'’0-9äöüÄÖÜ?! ]*$";

    private Long ContestPK;
    private Long contestLogoId;
    private Long sponsorLogoId;
    private String emailTemplateUrl;

    @Length(min = 5, max = 150, message = "The contest question must be at least 5 characters and not more than 150 characters.")
    private String contestName;

    @Length(min = 5, max = 70, message = "The contest title must be at least 5 characters and not more than 70 characters.")
    @Pattern(regexp = NO_SPECIAL_CHAR_REGEX, message = "The contest title must not contain special characters.")
    private String contestShortName;

    @Length(min = 0, max = 3000, message = "The contest description must have not more than 3000 characters (including html tags).")
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
            emailTemplateUrl = contest.getEmailTemplateUrl();
            sponsorLogoId = contest.getSponsorLogoId();
        }
    }

    public void persist(Contest contest) throws Exception {
        String oldContestTitle = contest.getContestShortName();
        updateContestDescription(contest);
        updateContestSchedule(contest, scheduleTemplateId);
        updateContestWiki(contest, oldContestTitle);
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

    public String getEmailTemplateUrl() {
        if (emailTemplateUrl != null) {
            return emailTemplateUrl;
        } else
            return "";
    }

    public void setEmailTemplateUrl(String emailTemplateUrl) {this.emailTemplateUrl = emailTemplateUrl;}

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

    private void updateContestDescription(Contest contest) throws Exception{
        contest.setContestName(contestName);
        contest.setEmailTemplateUrl(emailTemplateUrl);
        contest.setContestShortName(contestShortName);
        contest.setContestDescription(contestDescription);
        contest.setPlanTemplateId(planTemplateId);
        contest.setContestLogoId(contestLogoId);
        contest.setSponsorLogoId(sponsorLogoId);
        contest.setContestTier(contestTier);
        contest.persist();
    }

    public static void updateContestWiki(Contest contest, String oldContestTitle) throws Exception{
        String newContestTitle = contest.getContestShortName();
        if(!oldContestTitle.equals(newContestTitle)) {
            WikiPageWrapper.updateWikiPageTitleIfExists(oldContestTitle, newContestTitle);
            WikiPageWrapper.updateContestResourceUrl(contest, newContestTitle);
        }
    }

    public static void updateContestSchedule(Contest contest, Long contestScheduleId) throws Exception{
        Long oldScheduleTemplateId = contest.getContestScheduleId();
        boolean noScheduleSelected = contestScheduleId.equals(0L);

        if(!noScheduleSelected && !oldScheduleTemplateId.equals(contestScheduleId)) {
            ContestWrapper contestWrapper = new ContestWrapper(contest);
            boolean proposalsInContest = contestWrapper.getTotalProposalsCount() > 0;
            if(proposalsInContest) {
                ContestScheduleWrapper.updateContestPhasesOfContestAccordingToContestSchedule(contest, contestScheduleId);
            }   else{
                ContestScheduleWrapper.createContestPhasesAccordingToContestScheduleAndRemoveExistingPhases(contest, contestScheduleId);
            }
            contest.setContestScheduleId(contestScheduleId);
            contest.persist();
        }
    }
}

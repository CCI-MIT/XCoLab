package org.xcolab.portlets.contestmanagement.beans;


import org.hibernate.validator.constraints.Length;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.sharedcolab.SharedColabClient;
import org.xcolab.portlets.contestmanagement.utils.schedule.ContestScheduleUtil;
import org.xcolab.portlets.contestmanagement.wrappers.WikiPageWrapper;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ContestDescriptionBean implements Serializable {
    private static final Logger _log = LoggerFactory.getLogger(ContestDescriptionBean.class);

    private static final long serialVersionUID = 1L;
    private static final String NO_SPECIAL_CHAR_REGEX = "^[a-zA-Z:.,;'’0-9äöüÄÖÜ?! ]*$";

    private Long ContestPK;
    private Long contestLogoId;
    private Long sponsorLogoId;

    @Length(min = 3, max = 150, message = "The contest question must be at least 3 characters and not more than 150 characters.")
    private String contestName;

    @Length(min = 3, max = 70, message = "The contest title must be at least 3 characters and not more than 70 characters.")
    @Pattern(regexp = NO_SPECIAL_CHAR_REGEX, message = "The contest title must not contain special characters.")
    private String contestShortName;

    @Length(max = 3000, message = "The contest description must have not more than 3000 characters (including html tags).")
    private String contestDescription;

    @NotNull(message = "A plan template must be selected.")
    private Long planTemplateId;

    @NotNull(message = "A schedule template must be selected.")
    private Long scheduleTemplateId;

    private boolean shouldUpdateContestUrlName;

    private boolean isSharedContest;

    @SuppressWarnings("unused")
    public ContestDescriptionBean() {
    }

    public ContestDescriptionBean(Contest contest) {

        if (contest != null) {
            ContestPK = contest.getContestPK();
            contestName = contest.getContestName();
            contestShortName = contest.getContestShortName();
            contestDescription = contest.getContestDescription();
            planTemplateId = contest.getPlanTemplateId();
            scheduleTemplateId = contest.getContestScheduleId();
            contestLogoId = contest.getContestLogoId();
            sponsorLogoId = contest.getSponsorLogoId();

            shouldUpdateContestUrlName = !contest.getContestActive();
            isSharedContest = contest.getIsSharedContest();
        }
    }

    public void persist(Contest contest) {
        String oldContestName = contest.getContestShortName();
        updateContestDescription(contest);
        updateContestSchedule(contest, scheduleTemplateId);

        try {
            final CommentThread thread = ThreadClientUtil.getThread(contest.getDiscussionGroupId());
            ContestType contestType =
                    ContestClientUtil.getContestType(contest.getContestTypeId());
            thread.setTitle(String.format("%s %s",
                    contestType.getContestName(), contest.getContestShortName()));
            ThreadClientUtil.updateThread(thread);
        } catch (ThreadNotFoundException e) {
            _log.warn("No thread (id = {}) exists for contest {}", contest.getDiscussionGroupId(),
                    contest.getContestPK());
        }

        if (shouldUpdateContestUrlName && !contest.getContestShortName().equals(oldContestName)) {
            contest.setContestUrlName((contest).generateContestUrlName());
                ContestClientUtil.updateContest(contest);
        }
        WikiPageWrapper.updateContestWiki(contest);
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

    public Boolean getShouldUpdateContestUrlName() {
        return shouldUpdateContestUrlName;
    }

    public void setShouldUpdateContestUrlName(Boolean shouldUpdateContestUrlName) {
        this.shouldUpdateContestUrlName = shouldUpdateContestUrlName;
    }

    public boolean getIsSharedContest() {
        return isSharedContest;
    }

    public void setIsSharedContest(boolean sharedContest) {
        isSharedContest = sharedContest;
    }

    private void updateContestDescription(Contest contest) {
        contest.setContestName(contestName);
        contest.setContestShortName(contestShortName);
        contest.setContestDescription(contestDescription);
        contest.setPlanTemplateId(planTemplateId);
        contest.setContestLogoId(contestLogoId);
        contest.setSponsorLogoId(sponsorLogoId);
        contest.setIsSharedContest(isSharedContest);
        ContestClientUtil.updateContest(contest);
        if(contest.getIsSharedContest()) {
            SharedColabClient
                    .updateSharedContestName(contest.getContestPK(), contest.getContestName());
        }

    }

    private static void updateContestSchedule(Contest contest, Long contestScheduleId) {
        Long oldScheduleTemplateId = contest.getContestScheduleId();
        boolean noScheduleSelected = contestScheduleId.equals(0L);

        if (!noScheduleSelected && !oldScheduleTemplateId.equals(contestScheduleId)) {
            ContestScheduleUtil.changeScheduleForContest(contest, contestScheduleId);
        }
    }
}

package org.xcolab.view.pages.contestmanagement.beans;

import org.hibernate.validator.constraints.Length;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.view.pages.contestmanagement.wrappers.WikiPageWrapper;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ContestDescriptionBean implements Serializable {

    private static final Logger _log = LoggerFactory.getLogger(ContestDescriptionBean.class);

    private static final long serialVersionUID = 1L;
    private static final String NO_SPECIAL_CHAR_REGEX = "^[a-zA-Z:.,;'’0-9äöüÄÖÜ?! ]*$";

    private Long contestId;
    private Long contestLogoId;
    private Long sponsorLogoId;
    @Length(max = 500, message = "The sponsor link URL must not be longer than 500 characters.")
    private String sponsorLink;
    private Long defaultproposallogoid;

    @Length(min = 3, max = 150, message = "The contest question must be at least 3 characters and"
            + " not more than 150 characters.")
    private String question;

    @Length(min = 3, max = 55, message = "The contest title must be at least 3 characters and not"
            + " more than 55 characters.")
    @Pattern(regexp = NO_SPECIAL_CHAR_REGEX, message = "The contest title must not contain "
            + "special characters.")
    private String title;

    @Length(max = 3000, message = "The contest description must have not more than 3000 "
            + "characters (including html tags).")
    private String description;

    @NotNull(message = "A plan template must be selected.")
    private Long proposalTemplateId;

    @NotNull(message = "A schedule template must be selected.")
    private Long scheduleTemplateId;

    private boolean shouldUpdateContestUrlName;

    @SuppressWarnings("unused")
    public ContestDescriptionBean() {
    }

    public ContestDescriptionBean(Contest contest) {

        if (contest != null) {
            contestId = contest.getId();
            title = contest.getTitle();
            question = contest.getQuestion();
            description = contest.getDescription();
            proposalTemplateId = contest.getProposalTemplateId();
            scheduleTemplateId = contest.getContestScheduleId();
            contestLogoId = contest.getContestLogoId();
            sponsorLogoId = contest.getSponsorLogoId();
            sponsorLink = contest.getSponsorLink();
            defaultproposallogoid = contest.getDefaultProposalLogoId();
            shouldUpdateContestUrlName = !contest.getContestActive();
        }
    }

    public void persist(Contest contest) {
        String oldContestName = contest.getTitle();
        updateContestDescription(contest);

        try {
            final CommentThread thread = ThreadClientUtil.getThread(contest.getDiscussionGroupId());
            ContestType contestType =
                    ContestTypeClient.getContestType(contest.getContestTypeId());
            thread.setTitle(String.format("%s %s",
                    contestType.getContestName(), contest.getTitle()));
            ThreadClientUtil.updateThread(thread);
        } catch (ThreadNotFoundException e) {
            _log.warn("No thread (id = {}) exists for contest {}", contest.getDiscussionGroupId(),
                    contest.getId());
        }

        if (shouldUpdateContestUrlName && !contest.getTitle().equals(oldContestName)) {
            contest.setContestUrlName((contest).generateContestUrlName());
            ContestClientUtil.updateContest(contest);
        }
        WikiPageWrapper.updateContestWiki(contest);
        updateContestSchedule(contest, scheduleTemplateId);
    }

    private void updateContestDescription(Contest contest) {
        contest.setQuestion(question);
        contest.setTitle(title);
        contest.setDescription(description);
        contest.setProposalTemplateId(proposalTemplateId);
        contest.setContestLogoId(contestLogoId);
        contest.setSponsorLogoId(sponsorLogoId);
        contest.setSponsorLink(sponsorLink);
        contest.setDefaultProposalLogoId(defaultproposallogoid);
        ContestClientUtil.updateContest(contest);
    }

    private static void updateContestSchedule(Contest contest, Long contestScheduleId) {
        Long oldScheduleTemplateId = contest.getContestScheduleId();
        boolean noScheduleSelected = contestScheduleId.equals(0L);

        if (!noScheduleSelected && !oldScheduleTemplateId.equals(contestScheduleId)) {
            contest.changeScheduleTo(contestScheduleId);
        }
    }
    public Long getDefaultProposalLogoId() {
        return this.defaultproposallogoid;
    }

    public void setDefaultProposalLogoId(Long defaultproposallogoid) {
        this.defaultproposallogoid = defaultproposallogoid;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
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

    public String getSponsorLink() {
        return sponsorLink;
    }

    public void setSponsorLink(String sponsorLink) {
        this.sponsorLink= sponsorLink;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProposalTemplateId() {
        return proposalTemplateId;
    }

    public void setProposalTemplateId(Long proposalTemplateId) {
        this.proposalTemplateId = proposalTemplateId;
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
}

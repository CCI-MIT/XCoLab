package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;

import java.sql.Timestamp;

@JsonDeserialize(as = ContestWrapper.class)
public interface IContest {

    Long getId();

    void setId(Long id);

    Long getContestTypeId();

    void setContestTypeId(Long contestTypeId);

    String getQuestion();

    void setQuestion(String question);

    String getTitle();

    void setTitle(String title);

    String getContestUrlName();

    void setContestUrlName(String contestUrlName);

    Long getContestYear();

    void setContestYear(Long contestYear);

    String getDescription();

    void setDescription(String description);

    String getContestModelDescription();

    void setContestModelDescription(String contestModelDescription);

    String getContestPositionsDescription();

    void setContestPositionsDescription(String contestPositionsDescription);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    Timestamp getUpdatedAt();

    void setUpdatedAt(Timestamp updatedAt);

    Long getAuthorUserId();

    void setAuthorUserId(Long authorUserId);

    Boolean isContestActive();

    void setContestActive(Boolean contestActive);

    Long getProposalTemplateId();

    void setProposalTemplateId(Long proposalTemplateId);

    Long getContestScheduleId();

    void setContestScheduleId(Long contestScheduleId);

    String getProposalCreationTemplateString();

    void setProposalCreationTemplateString(String proposalCreationTemplateString);

    String getVoteTemplateString();

    void setVoteTemplateString(String voteTemplateString);

    String getProposalVoteTemplateString();

    void setProposalVoteTemplateString(String proposalVoteTemplateString);

    String getProposalVoteConfirmationTemplateString();

    void setProposalVoteConfirmationTemplateString(String proposalVoteConfirmationTemplateString);

    String getVoteQuestionTemplateString();

    void setVoteQuestionTemplateString(String voteQuestionTemplateString);

    Long getFocusAreaId();

    void setFocusAreaId(Long focusAreaId);

    Long getContestTier();

    void setContestTier(Long contestTier);

    Long getContestLogoId();

    void setContestLogoId(Long contestLogoId);

    Boolean isFeatured();

    void setFeatured(Boolean featured);

    Boolean isPlansOpenByDefault();

    void setPlansOpenByDefault(Boolean plansOpenByDefault);

    Long getSponsorLogoId();

    void setSponsorLogoId(Long sponsorLogoId);

    Long getDefaultProposalLogoId();

    void setDefaultProposalLogoId(Long defaultProposalLogoId);

    String getSponsorText();

    void setSponsorText(String sponsorText);

    String getSponsorLink();

    void setSponsorLink(String sponsorLink);

    Integer getFlag();

    void setFlag(Integer flag);

    String getFlagText();

    void setFlagText(String flagText);

    String getFlagTooltip();

    void setFlagTooltip(String flagTooltip);

    Long getDiscussionGroupId();

    void setDiscussionGroupId(Long discussionGroupId);

    Integer getWeight();

    void setWeight(Integer weight);

    String getResourcesUrl();

    void setResourcesUrl(String resourcesUrl);

    Boolean isContestPrivate();

    void setContestPrivate(Boolean contestPrivate);

    Boolean isUsePermissions();

    void setUsePermissions(Boolean usePermissions);

    String getContestCreationStatus();

    void setContestCreationStatus(String contestCreationStatus);

    Long getDefaultModelId();

    void setDefaultModelId(Long defaultModelId);

    String getOtherModels();

    void setOtherModels(String otherModels);

    String getDefaultModelSettings();

    void setDefaultModelSettings(String defaultModelSettings);

    Double getPoints();

    void setPoints(Double points);

    Long getDefaultParentPointType();

    void setDefaultParentPointType(Long defaultParentPointType);

    String getPointDistributionStrategy();

    void setPointDistributionStrategy(String pointDistributionStrategy);

    String getEmailTemplateUrl();

    void setEmailTemplateUrl(String emailTemplateUrl);

    Boolean isShowInTileView();

    void setShowInTileView(Boolean showInTileView);

    Boolean isShowInListView();

    void setShowInListView(Boolean showInListView);

    Boolean isShowInOutlineView();

    void setShowInOutlineView(Boolean showInOutlineView);

    Boolean isHideRibbons();

    void setHideRibbons(Boolean hideRibbons);

    Long getResourceArticleId();

    void setResourceArticleId(Long resourceArticleId);

    Boolean isReadOnlyComments();

    void setReadOnlyComments(Boolean readOnlyComments);
}

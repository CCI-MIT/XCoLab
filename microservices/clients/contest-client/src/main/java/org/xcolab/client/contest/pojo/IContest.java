package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.Contest;

import java.sql.Timestamp;

@JsonDeserialize(as = Contest.class)
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

    Boolean getContestActive();

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

    Boolean getFeatured();

    void setFeatured(Boolean featured);

    Boolean getPlansOpenByDefault();

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

    Boolean getContestPrivate();

    void setContestPrivate(Boolean contestPrivate);

    Boolean getUsePermissions();

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

    Boolean getShowInTileView();

    void setShowInTileView(Boolean showInTileView);

    Boolean getShowInListView();

    void setShowInListView(Boolean showInListView);

    Boolean getShowInOutlineView();

    void setShowInOutlineView(Boolean showInOutlineView);

    Boolean getHideRibbons();

    void setHideRibbons(Boolean hideRibbons);

    Long getResourceArticleId();

    void setResourceArticleId(Long resourceArticleId);

    Boolean getReadOnlyComments();

    void setReadOnlyComments(Boolean readOnlyComments);
}

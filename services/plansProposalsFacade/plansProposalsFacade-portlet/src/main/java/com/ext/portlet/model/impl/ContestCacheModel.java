package com.ext.portlet.model.impl;

import com.ext.portlet.model.Contest;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Contest in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Contest
 * @generated
 */
public class ContestCacheModel implements CacheModel<Contest>, Externalizable {
    public long ContestPK;
    public String ContestName;
    public String ContestShortName;
    public String ContestDescription;
    public String ContestModelDescription;
    public String ContestPositionsDescription;
    public String defaultPlanDescription;
    public long PlanTypeId;
    public long created;
    public long updated;
    public long authorId;
    public boolean contestActive;
    public long planTemplateId;
    public long contestScheduleId;
    public String proposalCreationTemplateString;
    public String voteTemplateString;
    public String proposalVoteTemplateString;
    public String proposalVoteConfirmationTemplateString;
    public String voteQuestionTemplateString;
    public long focusAreaId;
    public long contestTier;
    public long contestLogoId;
    public boolean featured;
    public boolean plansOpenByDefault;
    public long sponsorLogoId;
    public String sponsorText;
    public String sponsorLink;
    public int flag;
    public String flagText;
    public String flagTooltip;
    public long groupId;
    public long discussionGroupId;
    public long fellowDiscussionGroupId;
    public int weight;
    public String resourcesUrl;
    public boolean contestPrivate;
    public boolean usePermissions;
    public String contestCreationStatus;
    public long defaultModelId;
    public String otherModels;
    public String defaultModelSettings;
    public double points;
    public long defaultParentPointType;
    public String pointDistributionStrategy;
    public String emailTemplateUrl;
    public boolean show_in_tile_view;
    public boolean show_in_list_view;
    public boolean show_in_outline_view;
    public boolean hideRibbons;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(99);

        sb.append("{ContestPK=");
        sb.append(ContestPK);
        sb.append(", ContestName=");
        sb.append(ContestName);
        sb.append(", ContestShortName=");
        sb.append(ContestShortName);
        sb.append(", ContestDescription=");
        sb.append(ContestDescription);
        sb.append(", ContestModelDescription=");
        sb.append(ContestModelDescription);
        sb.append(", ContestPositionsDescription=");
        sb.append(ContestPositionsDescription);
        sb.append(", defaultPlanDescription=");
        sb.append(defaultPlanDescription);
        sb.append(", PlanTypeId=");
        sb.append(PlanTypeId);
        sb.append(", created=");
        sb.append(created);
        sb.append(", updated=");
        sb.append(updated);
        sb.append(", authorId=");
        sb.append(authorId);
        sb.append(", contestActive=");
        sb.append(contestActive);
        sb.append(", planTemplateId=");
        sb.append(planTemplateId);
        sb.append(", contestScheduleId=");
        sb.append(contestScheduleId);
        sb.append(", proposalCreationTemplateString=");
        sb.append(proposalCreationTemplateString);
        sb.append(", voteTemplateString=");
        sb.append(voteTemplateString);
        sb.append(", proposalVoteTemplateString=");
        sb.append(proposalVoteTemplateString);
        sb.append(", proposalVoteConfirmationTemplateString=");
        sb.append(proposalVoteConfirmationTemplateString);
        sb.append(", voteQuestionTemplateString=");
        sb.append(voteQuestionTemplateString);
        sb.append(", focusAreaId=");
        sb.append(focusAreaId);
        sb.append(", contestTier=");
        sb.append(contestTier);
        sb.append(", contestLogoId=");
        sb.append(contestLogoId);
        sb.append(", featured=");
        sb.append(featured);
        sb.append(", plansOpenByDefault=");
        sb.append(plansOpenByDefault);
        sb.append(", sponsorLogoId=");
        sb.append(sponsorLogoId);
        sb.append(", sponsorText=");
        sb.append(sponsorText);
        sb.append(", sponsorLink=");
        sb.append(sponsorLink);
        sb.append(", flag=");
        sb.append(flag);
        sb.append(", flagText=");
        sb.append(flagText);
        sb.append(", flagTooltip=");
        sb.append(flagTooltip);
        sb.append(", groupId=");
        sb.append(groupId);
        sb.append(", discussionGroupId=");
        sb.append(discussionGroupId);
        sb.append(", fellowDiscussionGroupId=");
        sb.append(fellowDiscussionGroupId);
        sb.append(", weight=");
        sb.append(weight);
        sb.append(", resourcesUrl=");
        sb.append(resourcesUrl);
        sb.append(", contestPrivate=");
        sb.append(contestPrivate);
        sb.append(", usePermissions=");
        sb.append(usePermissions);
        sb.append(", contestCreationStatus=");
        sb.append(contestCreationStatus);
        sb.append(", defaultModelId=");
        sb.append(defaultModelId);
        sb.append(", otherModels=");
        sb.append(otherModels);
        sb.append(", defaultModelSettings=");
        sb.append(defaultModelSettings);
        sb.append(", points=");
        sb.append(points);
        sb.append(", defaultParentPointType=");
        sb.append(defaultParentPointType);
        sb.append(", pointDistributionStrategy=");
        sb.append(pointDistributionStrategy);
        sb.append(", emailTemplateUrl=");
        sb.append(emailTemplateUrl);
        sb.append(", show_in_tile_view=");
        sb.append(show_in_tile_view);
        sb.append(", show_in_list_view=");
        sb.append(show_in_list_view);
        sb.append(", show_in_outline_view=");
        sb.append(show_in_outline_view);
        sb.append(", hideRibbons=");
        sb.append(hideRibbons);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public Contest toEntityModel() {
        ContestImpl contestImpl = new ContestImpl();

        contestImpl.setContestPK(ContestPK);

        if (ContestName == null) {
            contestImpl.setContestName(StringPool.BLANK);
        } else {
            contestImpl.setContestName(ContestName);
        }

        if (ContestShortName == null) {
            contestImpl.setContestShortName(StringPool.BLANK);
        } else {
            contestImpl.setContestShortName(ContestShortName);
        }

        if (ContestDescription == null) {
            contestImpl.setContestDescription(StringPool.BLANK);
        } else {
            contestImpl.setContestDescription(ContestDescription);
        }

        if (ContestModelDescription == null) {
            contestImpl.setContestModelDescription(StringPool.BLANK);
        } else {
            contestImpl.setContestModelDescription(ContestModelDescription);
        }

        if (ContestPositionsDescription == null) {
            contestImpl.setContestPositionsDescription(StringPool.BLANK);
        } else {
            contestImpl.setContestPositionsDescription(ContestPositionsDescription);
        }

        if (defaultPlanDescription == null) {
            contestImpl.setDefaultPlanDescription(StringPool.BLANK);
        } else {
            contestImpl.setDefaultPlanDescription(defaultPlanDescription);
        }

        contestImpl.setPlanTypeId(PlanTypeId);

        if (created == Long.MIN_VALUE) {
            contestImpl.setCreated(null);
        } else {
            contestImpl.setCreated(new Date(created));
        }

        if (updated == Long.MIN_VALUE) {
            contestImpl.setUpdated(null);
        } else {
            contestImpl.setUpdated(new Date(updated));
        }

        contestImpl.setAuthorId(authorId);
        contestImpl.setContestActive(contestActive);
        contestImpl.setPlanTemplateId(planTemplateId);
        contestImpl.setContestScheduleId(contestScheduleId);

        if (proposalCreationTemplateString == null) {
            contestImpl.setProposalCreationTemplateString(StringPool.BLANK);
        } else {
            contestImpl.setProposalCreationTemplateString(proposalCreationTemplateString);
        }

        if (voteTemplateString == null) {
            contestImpl.setVoteTemplateString(StringPool.BLANK);
        } else {
            contestImpl.setVoteTemplateString(voteTemplateString);
        }

        if (proposalVoteTemplateString == null) {
            contestImpl.setProposalVoteTemplateString(StringPool.BLANK);
        } else {
            contestImpl.setProposalVoteTemplateString(proposalVoteTemplateString);
        }

        if (proposalVoteConfirmationTemplateString == null) {
            contestImpl.setProposalVoteConfirmationTemplateString(StringPool.BLANK);
        } else {
            contestImpl.setProposalVoteConfirmationTemplateString(proposalVoteConfirmationTemplateString);
        }

        if (voteQuestionTemplateString == null) {
            contestImpl.setVoteQuestionTemplateString(StringPool.BLANK);
        } else {
            contestImpl.setVoteQuestionTemplateString(voteQuestionTemplateString);
        }

        contestImpl.setFocusAreaId(focusAreaId);
        contestImpl.setContestTier(contestTier);
        contestImpl.setContestLogoId(contestLogoId);
        contestImpl.setFeatured(featured);
        contestImpl.setPlansOpenByDefault(plansOpenByDefault);
        contestImpl.setSponsorLogoId(sponsorLogoId);

        if (sponsorText == null) {
            contestImpl.setSponsorText(StringPool.BLANK);
        } else {
            contestImpl.setSponsorText(sponsorText);
        }

        if (sponsorLink == null) {
            contestImpl.setSponsorLink(StringPool.BLANK);
        } else {
            contestImpl.setSponsorLink(sponsorLink);
        }

        contestImpl.setFlag(flag);

        if (flagText == null) {
            contestImpl.setFlagText(StringPool.BLANK);
        } else {
            contestImpl.setFlagText(flagText);
        }

        if (flagTooltip == null) {
            contestImpl.setFlagTooltip(StringPool.BLANK);
        } else {
            contestImpl.setFlagTooltip(flagTooltip);
        }

        contestImpl.setGroupId(groupId);
        contestImpl.setDiscussionGroupId(discussionGroupId);
        contestImpl.setFellowDiscussionGroupId(fellowDiscussionGroupId);
        contestImpl.setWeight(weight);

        if (resourcesUrl == null) {
            contestImpl.setResourcesUrl(StringPool.BLANK);
        } else {
            contestImpl.setResourcesUrl(resourcesUrl);
        }

        contestImpl.setContestPrivate(contestPrivate);
        contestImpl.setUsePermissions(usePermissions);

        if (contestCreationStatus == null) {
            contestImpl.setContestCreationStatus(StringPool.BLANK);
        } else {
            contestImpl.setContestCreationStatus(contestCreationStatus);
        }

        contestImpl.setDefaultModelId(defaultModelId);

        if (otherModels == null) {
            contestImpl.setOtherModels(StringPool.BLANK);
        } else {
            contestImpl.setOtherModels(otherModels);
        }

        if (defaultModelSettings == null) {
            contestImpl.setDefaultModelSettings(StringPool.BLANK);
        } else {
            contestImpl.setDefaultModelSettings(defaultModelSettings);
        }

        contestImpl.setPoints(points);
        contestImpl.setDefaultParentPointType(defaultParentPointType);

        if (pointDistributionStrategy == null) {
            contestImpl.setPointDistributionStrategy(StringPool.BLANK);
        } else {
            contestImpl.setPointDistributionStrategy(pointDistributionStrategy);
        }

        if (emailTemplateUrl == null) {
            contestImpl.setEmailTemplateUrl(StringPool.BLANK);
        } else {
            contestImpl.setEmailTemplateUrl(emailTemplateUrl);
        }

        contestImpl.setShow_in_tile_view(show_in_tile_view);
        contestImpl.setShow_in_list_view(show_in_list_view);
        contestImpl.setShow_in_outline_view(show_in_outline_view);
        contestImpl.setHideRibbons(hideRibbons);

        contestImpl.resetOriginalValues();

        return contestImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        ContestPK = objectInput.readLong();
        ContestName = objectInput.readUTF();
        ContestShortName = objectInput.readUTF();
        ContestDescription = objectInput.readUTF();
        ContestModelDescription = objectInput.readUTF();
        ContestPositionsDescription = objectInput.readUTF();
        defaultPlanDescription = objectInput.readUTF();
        PlanTypeId = objectInput.readLong();
        created = objectInput.readLong();
        updated = objectInput.readLong();
        authorId = objectInput.readLong();
        contestActive = objectInput.readBoolean();
        planTemplateId = objectInput.readLong();
        contestScheduleId = objectInput.readLong();
        proposalCreationTemplateString = objectInput.readUTF();
        voteTemplateString = objectInput.readUTF();
        proposalVoteTemplateString = objectInput.readUTF();
        proposalVoteConfirmationTemplateString = objectInput.readUTF();
        voteQuestionTemplateString = objectInput.readUTF();
        focusAreaId = objectInput.readLong();
        contestTier = objectInput.readLong();
        contestLogoId = objectInput.readLong();
        featured = objectInput.readBoolean();
        plansOpenByDefault = objectInput.readBoolean();
        sponsorLogoId = objectInput.readLong();
        sponsorText = objectInput.readUTF();
        sponsorLink = objectInput.readUTF();
        flag = objectInput.readInt();
        flagText = objectInput.readUTF();
        flagTooltip = objectInput.readUTF();
        groupId = objectInput.readLong();
        discussionGroupId = objectInput.readLong();
        fellowDiscussionGroupId = objectInput.readLong();
        weight = objectInput.readInt();
        resourcesUrl = objectInput.readUTF();
        contestPrivate = objectInput.readBoolean();
        usePermissions = objectInput.readBoolean();
        contestCreationStatus = objectInput.readUTF();
        defaultModelId = objectInput.readLong();
        otherModels = objectInput.readUTF();
        defaultModelSettings = objectInput.readUTF();
        points = objectInput.readDouble();
        defaultParentPointType = objectInput.readLong();
        pointDistributionStrategy = objectInput.readUTF();
        emailTemplateUrl = objectInput.readUTF();
        show_in_tile_view = objectInput.readBoolean();
        show_in_list_view = objectInput.readBoolean();
        show_in_outline_view = objectInput.readBoolean();
        hideRibbons = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(ContestPK);

        if (ContestName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(ContestName);
        }

        if (ContestShortName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(ContestShortName);
        }

        if (ContestDescription == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(ContestDescription);
        }

        if (ContestModelDescription == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(ContestModelDescription);
        }

        if (ContestPositionsDescription == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(ContestPositionsDescription);
        }

        if (defaultPlanDescription == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(defaultPlanDescription);
        }

        objectOutput.writeLong(PlanTypeId);
        objectOutput.writeLong(created);
        objectOutput.writeLong(updated);
        objectOutput.writeLong(authorId);
        objectOutput.writeBoolean(contestActive);
        objectOutput.writeLong(planTemplateId);
        objectOutput.writeLong(contestScheduleId);

        if (proposalCreationTemplateString == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(proposalCreationTemplateString);
        }

        if (voteTemplateString == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(voteTemplateString);
        }

        if (proposalVoteTemplateString == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(proposalVoteTemplateString);
        }

        if (proposalVoteConfirmationTemplateString == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(proposalVoteConfirmationTemplateString);
        }

        if (voteQuestionTemplateString == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(voteQuestionTemplateString);
        }

        objectOutput.writeLong(focusAreaId);
        objectOutput.writeLong(contestTier);
        objectOutput.writeLong(contestLogoId);
        objectOutput.writeBoolean(featured);
        objectOutput.writeBoolean(plansOpenByDefault);
        objectOutput.writeLong(sponsorLogoId);

        if (sponsorText == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(sponsorText);
        }

        if (sponsorLink == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(sponsorLink);
        }

        objectOutput.writeInt(flag);

        if (flagText == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(flagText);
        }

        if (flagTooltip == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(flagTooltip);
        }

        objectOutput.writeLong(groupId);
        objectOutput.writeLong(discussionGroupId);
        objectOutput.writeLong(fellowDiscussionGroupId);
        objectOutput.writeInt(weight);

        if (resourcesUrl == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(resourcesUrl);
        }

        objectOutput.writeBoolean(contestPrivate);
        objectOutput.writeBoolean(usePermissions);

        if (contestCreationStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(contestCreationStatus);
        }

        objectOutput.writeLong(defaultModelId);

        if (otherModels == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(otherModels);
        }

        if (defaultModelSettings == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(defaultModelSettings);
        }

        objectOutput.writeDouble(points);
        objectOutput.writeLong(defaultParentPointType);

        if (pointDistributionStrategy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(pointDistributionStrategy);
        }

        if (emailTemplateUrl == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(emailTemplateUrl);
        }

        objectOutput.writeBoolean(show_in_tile_view);
        objectOutput.writeBoolean(show_in_list_view);
        objectOutput.writeBoolean(show_in_outline_view);
        objectOutput.writeBoolean(hideRibbons);
    }
}

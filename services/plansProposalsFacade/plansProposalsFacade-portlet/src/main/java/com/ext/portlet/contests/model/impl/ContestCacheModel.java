package com.ext.portlet.contests.model.impl;

import com.ext.portlet.contests.model.Contest;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Contest in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Contest
 * @generated
 */
public class ContestCacheModel implements CacheModel<Contest>, Serializable {
    public Long ContestPK;
    public String ContestName;
    public String ContestShortName;
    public String ContestDescription;
    public String ContestModelDescription;
    public String ContestPositionsDescription;
    public String defaultPlanDescription;
    public Long PlanTypeId;
    public long created;
    public long updated;
    public Long authorId;
    public Boolean contestActive;
    public Long planTemplateId;
    public Long focusAreaId;
    public Long contestLogoId;
    public Boolean featured;
    public Boolean plansOpenByDefault;
    public Integer flag;
    public String flagText;
    public Long groupId;
    public Long discussionGroupId;
    public Integer weight;
    public String resourcesUrl;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(47);

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
        sb.append(", focusAreaId=");
        sb.append(focusAreaId);
        sb.append(", contestLogoId=");
        sb.append(contestLogoId);
        sb.append(", featured=");
        sb.append(featured);
        sb.append(", plansOpenByDefault=");
        sb.append(plansOpenByDefault);
        sb.append(", flag=");
        sb.append(flag);
        sb.append(", flagText=");
        sb.append(flagText);
        sb.append(", groupId=");
        sb.append(groupId);
        sb.append(", discussionGroupId=");
        sb.append(discussionGroupId);
        sb.append(", weight=");
        sb.append(weight);
        sb.append(", resourcesUrl=");
        sb.append(resourcesUrl);
        sb.append("}");

        return sb.toString();
    }

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
        contestImpl.setFocusAreaId(focusAreaId);
        contestImpl.setContestLogoId(contestLogoId);
        contestImpl.setFeatured(featured);
        contestImpl.setPlansOpenByDefault(plansOpenByDefault);
        contestImpl.setFlag(flag);

        if (flagText == null) {
            contestImpl.setFlagText(StringPool.BLANK);
        } else {
            contestImpl.setFlagText(flagText);
        }

        contestImpl.setGroupId(groupId);
        contestImpl.setDiscussionGroupId(discussionGroupId);
        contestImpl.setWeight(weight);

        if (resourcesUrl == null) {
            contestImpl.setResourcesUrl(StringPool.BLANK);
        } else {
            contestImpl.setResourcesUrl(resourcesUrl);
        }

        contestImpl.resetOriginalValues();

        return contestImpl;
    }
}

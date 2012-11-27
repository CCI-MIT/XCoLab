package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanMeta;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing PlanMeta in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanMeta
 * @generated
 */
public class PlanMetaCacheModel implements CacheModel<PlanMeta>, Serializable {
    public Long id;
    public Long planId;
    public Long planTypeId;
    public Long planCreated;
    public Long authorId;
    public Integer votes;
    public Long planGroupId;
    public Boolean open;
    public String status;
    public Long mbCategoryId;
    public Long categoryGroupId;
    public Long version;
    public Long planVersion;
    public long created;
    public Long updateAuthorId;
    public Long modelId;
    public Boolean promoted;
    public Long previousContestPhase;
    public Long contestPhase;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(39);

        sb.append("{id=");
        sb.append(id);
        sb.append(", planId=");
        sb.append(planId);
        sb.append(", planTypeId=");
        sb.append(planTypeId);
        sb.append(", planCreated=");
        sb.append(planCreated);
        sb.append(", authorId=");
        sb.append(authorId);
        sb.append(", votes=");
        sb.append(votes);
        sb.append(", planGroupId=");
        sb.append(planGroupId);
        sb.append(", open=");
        sb.append(open);
        sb.append(", status=");
        sb.append(status);
        sb.append(", mbCategoryId=");
        sb.append(mbCategoryId);
        sb.append(", categoryGroupId=");
        sb.append(categoryGroupId);
        sb.append(", version=");
        sb.append(version);
        sb.append(", planVersion=");
        sb.append(planVersion);
        sb.append(", created=");
        sb.append(created);
        sb.append(", updateAuthorId=");
        sb.append(updateAuthorId);
        sb.append(", modelId=");
        sb.append(modelId);
        sb.append(", promoted=");
        sb.append(promoted);
        sb.append(", previousContestPhase=");
        sb.append(previousContestPhase);
        sb.append(", contestPhase=");
        sb.append(contestPhase);
        sb.append("}");

        return sb.toString();
    }

    public PlanMeta toEntityModel() {
        PlanMetaImpl planMetaImpl = new PlanMetaImpl();

        planMetaImpl.setId(id);
        planMetaImpl.setPlanId(planId);
        planMetaImpl.setPlanTypeId(planTypeId);
        planMetaImpl.setPlanCreated(planCreated);
        planMetaImpl.setAuthorId(authorId);
        planMetaImpl.setVotes(votes);
        planMetaImpl.setPlanGroupId(planGroupId);
        planMetaImpl.setOpen(open);

        if (status == null) {
            planMetaImpl.setStatus(StringPool.BLANK);
        } else {
            planMetaImpl.setStatus(status);
        }

        planMetaImpl.setMbCategoryId(mbCategoryId);
        planMetaImpl.setCategoryGroupId(categoryGroupId);
        planMetaImpl.setVersion(version);
        planMetaImpl.setPlanVersion(planVersion);

        if (created == Long.MIN_VALUE) {
            planMetaImpl.setCreated(null);
        } else {
            planMetaImpl.setCreated(new Date(created));
        }

        planMetaImpl.setUpdateAuthorId(updateAuthorId);
        planMetaImpl.setModelId(modelId);
        planMetaImpl.setPromoted(promoted);
        planMetaImpl.setPreviousContestPhase(previousContestPhase);
        planMetaImpl.setContestPhase(contestPhase);

        planMetaImpl.resetOriginalValues();

        return planMetaImpl;
    }
}

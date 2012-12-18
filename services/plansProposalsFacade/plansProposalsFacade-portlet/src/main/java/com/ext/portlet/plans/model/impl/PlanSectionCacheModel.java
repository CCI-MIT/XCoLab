package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanSection;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing PlanSection in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanSection
 * @generated
 */
public class PlanSectionCacheModel implements CacheModel<PlanSection>,
    Serializable {
    public long id;
    public long planSectionDefinitionId;
    public long planId;
    public String content;
    public long created;
    public long version;
    public long planVersion;
    public long updateAuthorId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(17);

        sb.append("{id=");
        sb.append(id);
        sb.append(", planSectionDefinitionId=");
        sb.append(planSectionDefinitionId);
        sb.append(", planId=");
        sb.append(planId);
        sb.append(", content=");
        sb.append(content);
        sb.append(", created=");
        sb.append(created);
        sb.append(", version=");
        sb.append(version);
        sb.append(", planVersion=");
        sb.append(planVersion);
        sb.append(", updateAuthorId=");
        sb.append(updateAuthorId);
        sb.append("}");

        return sb.toString();
    }

    public PlanSection toEntityModel() {
        PlanSectionImpl planSectionImpl = new PlanSectionImpl();

        planSectionImpl.setId(id);
        planSectionImpl.setPlanSectionDefinitionId(planSectionDefinitionId);
        planSectionImpl.setPlanId(planId);

        if (content == null) {
            planSectionImpl.setContent(StringPool.BLANK);
        } else {
            planSectionImpl.setContent(content);
        }

        if (created == Long.MIN_VALUE) {
            planSectionImpl.setCreated(null);
        } else {
            planSectionImpl.setCreated(new Date(created));
        }

        planSectionImpl.setVersion(version);
        planSectionImpl.setPlanVersion(planVersion);
        planSectionImpl.setUpdateAuthorId(updateAuthorId);

        planSectionImpl.resetOriginalValues();

        return planSectionImpl;
    }
}

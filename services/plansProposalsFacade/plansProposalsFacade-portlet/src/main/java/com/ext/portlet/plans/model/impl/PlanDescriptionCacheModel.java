package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanDescription;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing PlanDescription in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanDescription
 * @generated
 */
public class PlanDescriptionCacheModel implements CacheModel<PlanDescription>,
    Serializable {
    public long id;
    public long planId;
    public String name;
    public String description;
    public long version;
    public long planVersion;
    public long created;
    public long updateAuthorId;
    public long image;
    public String pitch;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(21);

        sb.append("{id=");
        sb.append(id);
        sb.append(", planId=");
        sb.append(planId);
        sb.append(", name=");
        sb.append(name);
        sb.append(", description=");
        sb.append(description);
        sb.append(", version=");
        sb.append(version);
        sb.append(", planVersion=");
        sb.append(planVersion);
        sb.append(", created=");
        sb.append(created);
        sb.append(", updateAuthorId=");
        sb.append(updateAuthorId);
        sb.append(", image=");
        sb.append(image);
        sb.append(", pitch=");
        sb.append(pitch);
        sb.append("}");

        return sb.toString();
    }

    public PlanDescription toEntityModel() {
        PlanDescriptionImpl planDescriptionImpl = new PlanDescriptionImpl();

        planDescriptionImpl.setId(id);
        planDescriptionImpl.setPlanId(planId);

        if (name == null) {
            planDescriptionImpl.setName(StringPool.BLANK);
        } else {
            planDescriptionImpl.setName(name);
        }

        if (description == null) {
            planDescriptionImpl.setDescription(StringPool.BLANK);
        } else {
            planDescriptionImpl.setDescription(description);
        }

        planDescriptionImpl.setVersion(version);
        planDescriptionImpl.setPlanVersion(planVersion);

        if (created == Long.MIN_VALUE) {
            planDescriptionImpl.setCreated(null);
        } else {
            planDescriptionImpl.setCreated(new Date(created));
        }

        planDescriptionImpl.setUpdateAuthorId(updateAuthorId);
        planDescriptionImpl.setImage(image);

        if (pitch == null) {
            planDescriptionImpl.setPitch(StringPool.BLANK);
        } else {
            planDescriptionImpl.setPitch(pitch);
        }

        planDescriptionImpl.resetOriginalValues();

        return planDescriptionImpl;
    }
}

package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanPositions;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PlanPositions in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanPositions
 * @generated
 */
public class PlanPositionsCacheModel implements CacheModel<PlanPositions>,
    Externalizable {
    public long id;
    public long planId;
    public long planVersion;
    public long version;
    public long created;
    public long updateAuthorId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{id=");
        sb.append(id);
        sb.append(", planId=");
        sb.append(planId);
        sb.append(", planVersion=");
        sb.append(planVersion);
        sb.append(", version=");
        sb.append(version);
        sb.append(", created=");
        sb.append(created);
        sb.append(", updateAuthorId=");
        sb.append(updateAuthorId);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public PlanPositions toEntityModel() {
        PlanPositionsImpl planPositionsImpl = new PlanPositionsImpl();

        planPositionsImpl.setId(id);
        planPositionsImpl.setPlanId(planId);
        planPositionsImpl.setPlanVersion(planVersion);
        planPositionsImpl.setVersion(version);

        if (created == Long.MIN_VALUE) {
            planPositionsImpl.setCreated(null);
        } else {
            planPositionsImpl.setCreated(new Date(created));
        }

        planPositionsImpl.setUpdateAuthorId(updateAuthorId);

        planPositionsImpl.resetOriginalValues();

        return planPositionsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        planId = objectInput.readLong();
        planVersion = objectInput.readLong();
        version = objectInput.readLong();
        created = objectInput.readLong();
        updateAuthorId = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);
        objectOutput.writeLong(planId);
        objectOutput.writeLong(planVersion);
        objectOutput.writeLong(version);
        objectOutput.writeLong(created);
        objectOutput.writeLong(updateAuthorId);
    }
}

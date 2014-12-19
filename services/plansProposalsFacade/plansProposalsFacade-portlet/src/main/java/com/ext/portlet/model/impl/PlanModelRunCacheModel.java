package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanModelRun;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PlanModelRun in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanModelRun
 * @generated
 */
public class PlanModelRunCacheModel implements CacheModel<PlanModelRun>,
    Externalizable {
    public long id;
    public long planId;
    public long scenarioId;
    public long planVersion;
    public long version;
    public long created;
    public long updateAuthorId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{id=");
        sb.append(id);
        sb.append(", planId=");
        sb.append(planId);
        sb.append(", scenarioId=");
        sb.append(scenarioId);
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
    public PlanModelRun toEntityModel() {
        PlanModelRunImpl planModelRunImpl = new PlanModelRunImpl();

        planModelRunImpl.setId(id);
        planModelRunImpl.setPlanId(planId);
        planModelRunImpl.setScenarioId(scenarioId);
        planModelRunImpl.setPlanVersion(planVersion);
        planModelRunImpl.setVersion(version);

        if (created == Long.MIN_VALUE) {
            planModelRunImpl.setCreated(null);
        } else {
            planModelRunImpl.setCreated(new Date(created));
        }

        planModelRunImpl.setUpdateAuthorId(updateAuthorId);

        planModelRunImpl.resetOriginalValues();

        return planModelRunImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        planId = objectInput.readLong();
        scenarioId = objectInput.readLong();
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
        objectOutput.writeLong(scenarioId);
        objectOutput.writeLong(planVersion);
        objectOutput.writeLong(version);
        objectOutput.writeLong(created);
        objectOutput.writeLong(updateAuthorId);
    }
}

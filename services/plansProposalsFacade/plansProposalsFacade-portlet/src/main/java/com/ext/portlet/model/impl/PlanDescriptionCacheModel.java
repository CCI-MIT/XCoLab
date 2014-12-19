package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanDescription;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PlanDescription in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanDescription
 * @generated
 */
public class PlanDescriptionCacheModel implements CacheModel<PlanDescription>,
    Externalizable {
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

    @Override
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

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        planId = objectInput.readLong();
        name = objectInput.readUTF();
        description = objectInput.readUTF();
        version = objectInput.readLong();
        planVersion = objectInput.readLong();
        created = objectInput.readLong();
        updateAuthorId = objectInput.readLong();
        image = objectInput.readLong();
        pitch = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);
        objectOutput.writeLong(planId);

        if (name == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(name);
        }

        if (description == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(description);
        }

        objectOutput.writeLong(version);
        objectOutput.writeLong(planVersion);
        objectOutput.writeLong(created);
        objectOutput.writeLong(updateAuthorId);
        objectOutput.writeLong(image);

        if (pitch == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(pitch);
        }
    }
}

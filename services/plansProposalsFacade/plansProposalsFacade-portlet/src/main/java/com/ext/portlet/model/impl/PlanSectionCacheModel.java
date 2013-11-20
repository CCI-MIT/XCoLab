package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanSection;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PlanSection in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanSection
 * @generated
 */
public class PlanSectionCacheModel implements CacheModel<PlanSection>,
    Externalizable {
    public long id;
    public long planSectionDefinitionId;
    public long planId;
    public String content;
    public long numericValue;
    public long created;
    public long version;
    public long planVersion;
    public long updateAuthorId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(19);

        sb.append("{id=");
        sb.append(id);
        sb.append(", planSectionDefinitionId=");
        sb.append(planSectionDefinitionId);
        sb.append(", planId=");
        sb.append(planId);
        sb.append(", content=");
        sb.append(content);
        sb.append(", numericValue=");
        sb.append(numericValue);
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

    @Override
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

        planSectionImpl.setNumericValue(numericValue);

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

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        planSectionDefinitionId = objectInput.readLong();
        planId = objectInput.readLong();
        content = objectInput.readUTF();
        numericValue = objectInput.readLong();
        created = objectInput.readLong();
        version = objectInput.readLong();
        planVersion = objectInput.readLong();
        updateAuthorId = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);
        objectOutput.writeLong(planSectionDefinitionId);
        objectOutput.writeLong(planId);

        if (content == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(content);
        }

        objectOutput.writeLong(numericValue);
        objectOutput.writeLong(created);
        objectOutput.writeLong(version);
        objectOutput.writeLong(planVersion);
        objectOutput.writeLong(updateAuthorId);
    }
}

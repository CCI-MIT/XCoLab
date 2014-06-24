package com.ext.portlet.model.impl;

import com.ext.portlet.model.ProposalRatingValue;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ProposalRatingValue in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalRatingValue
 * @generated
 */
public class ProposalRatingValueCacheModel implements CacheModel<ProposalRatingValue>,
    Externalizable {
    public long id;
    public long ratingTypeId;
    public long value;
    public String name;
    public String description;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{id=");
        sb.append(id);
        sb.append(", ratingTypeId=");
        sb.append(ratingTypeId);
        sb.append(", value=");
        sb.append(value);
        sb.append(", name=");
        sb.append(name);
        sb.append(", description=");
        sb.append(description);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ProposalRatingValue toEntityModel() {
        ProposalRatingValueImpl proposalRatingValueImpl = new ProposalRatingValueImpl();

        proposalRatingValueImpl.setId(id);
        proposalRatingValueImpl.setRatingTypeId(ratingTypeId);
        proposalRatingValueImpl.setValue(value);

        if (name == null) {
            proposalRatingValueImpl.setName(StringPool.BLANK);
        } else {
            proposalRatingValueImpl.setName(name);
        }

        if (description == null) {
            proposalRatingValueImpl.setDescription(StringPool.BLANK);
        } else {
            proposalRatingValueImpl.setDescription(description);
        }

        proposalRatingValueImpl.resetOriginalValues();

        return proposalRatingValueImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        ratingTypeId = objectInput.readLong();
        value = objectInput.readLong();
        name = objectInput.readUTF();
        description = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);
        objectOutput.writeLong(ratingTypeId);
        objectOutput.writeLong(value);

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
    }
}

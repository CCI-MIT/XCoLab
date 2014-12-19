package com.ext.portlet.model.impl;

import com.ext.portlet.model.ProposalAttribute;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ProposalAttribute in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalAttribute
 * @generated
 */
public class ProposalAttributeCacheModel implements CacheModel<ProposalAttribute>,
    Externalizable {
    public long id;
    public long proposalId;
    public int version;
    public int versionWhenCreated;
    public String name;
    public long additionalId;
    public long numericValue;
    public String stringValue;
    public double realValue;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(19);

        sb.append("{id=");
        sb.append(id);
        sb.append(", proposalId=");
        sb.append(proposalId);
        sb.append(", version=");
        sb.append(version);
        sb.append(", versionWhenCreated=");
        sb.append(versionWhenCreated);
        sb.append(", name=");
        sb.append(name);
        sb.append(", additionalId=");
        sb.append(additionalId);
        sb.append(", numericValue=");
        sb.append(numericValue);
        sb.append(", stringValue=");
        sb.append(stringValue);
        sb.append(", realValue=");
        sb.append(realValue);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ProposalAttribute toEntityModel() {
        ProposalAttributeImpl proposalAttributeImpl = new ProposalAttributeImpl();

        proposalAttributeImpl.setId(id);
        proposalAttributeImpl.setProposalId(proposalId);
        proposalAttributeImpl.setVersion(version);
        proposalAttributeImpl.setVersionWhenCreated(versionWhenCreated);

        if (name == null) {
            proposalAttributeImpl.setName(StringPool.BLANK);
        } else {
            proposalAttributeImpl.setName(name);
        }

        proposalAttributeImpl.setAdditionalId(additionalId);
        proposalAttributeImpl.setNumericValue(numericValue);

        if (stringValue == null) {
            proposalAttributeImpl.setStringValue(StringPool.BLANK);
        } else {
            proposalAttributeImpl.setStringValue(stringValue);
        }

        proposalAttributeImpl.setRealValue(realValue);

        proposalAttributeImpl.resetOriginalValues();

        return proposalAttributeImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        proposalId = objectInput.readLong();
        version = objectInput.readInt();
        versionWhenCreated = objectInput.readInt();
        name = objectInput.readUTF();
        additionalId = objectInput.readLong();
        numericValue = objectInput.readLong();
        stringValue = objectInput.readUTF();
        realValue = objectInput.readDouble();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);
        objectOutput.writeLong(proposalId);
        objectOutput.writeInt(version);
        objectOutput.writeInt(versionWhenCreated);

        if (name == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(name);
        }

        objectOutput.writeLong(additionalId);
        objectOutput.writeLong(numericValue);

        if (stringValue == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(stringValue);
        }

        objectOutput.writeDouble(realValue);
    }
}

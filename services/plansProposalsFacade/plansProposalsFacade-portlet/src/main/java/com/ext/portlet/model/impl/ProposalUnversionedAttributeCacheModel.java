package com.ext.portlet.model.impl;

import com.ext.portlet.model.ProposalUnversionedAttribute;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ProposalUnversionedAttribute in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalUnversionedAttribute
 * @generated
 */
public class ProposalUnversionedAttributeCacheModel implements CacheModel<ProposalUnversionedAttribute>,
    Externalizable {
    public long id;
    public long proposalId;
    public long createAuthorId;
    public long lastAuthorId;
    public long createDate;
    public long lastUpdateDate;
    public String name;
    public int addtionalId;
    public long numericValue;
    public String stringValue;
    public double realValue;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(23);

        sb.append("{id=");
        sb.append(id);
        sb.append(", proposalId=");
        sb.append(proposalId);
        sb.append(", createAuthorId=");
        sb.append(createAuthorId);
        sb.append(", lastAuthorId=");
        sb.append(lastAuthorId);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append(", lastUpdateDate=");
        sb.append(lastUpdateDate);
        sb.append(", name=");
        sb.append(name);
        sb.append(", addtionalId=");
        sb.append(addtionalId);
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
    public ProposalUnversionedAttribute toEntityModel() {
        ProposalUnversionedAttributeImpl proposalUnversionedAttributeImpl = new ProposalUnversionedAttributeImpl();

        proposalUnversionedAttributeImpl.setId(id);
        proposalUnversionedAttributeImpl.setProposalId(proposalId);
        proposalUnversionedAttributeImpl.setCreateAuthorId(createAuthorId);
        proposalUnversionedAttributeImpl.setLastAuthorId(lastAuthorId);

        if (createDate == Long.MIN_VALUE) {
            proposalUnversionedAttributeImpl.setCreateDate(null);
        } else {
            proposalUnversionedAttributeImpl.setCreateDate(new Date(createDate));
        }

        if (lastUpdateDate == Long.MIN_VALUE) {
            proposalUnversionedAttributeImpl.setLastUpdateDate(null);
        } else {
            proposalUnversionedAttributeImpl.setLastUpdateDate(new Date(
                    lastUpdateDate));
        }

        if (name == null) {
            proposalUnversionedAttributeImpl.setName(StringPool.BLANK);
        } else {
            proposalUnversionedAttributeImpl.setName(name);
        }

        proposalUnversionedAttributeImpl.setAddtionalId(addtionalId);
        proposalUnversionedAttributeImpl.setNumericValue(numericValue);

        if (stringValue == null) {
            proposalUnversionedAttributeImpl.setStringValue(StringPool.BLANK);
        } else {
            proposalUnversionedAttributeImpl.setStringValue(stringValue);
        }

        proposalUnversionedAttributeImpl.setRealValue(realValue);

        proposalUnversionedAttributeImpl.resetOriginalValues();

        return proposalUnversionedAttributeImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        proposalId = objectInput.readLong();
        createAuthorId = objectInput.readLong();
        lastAuthorId = objectInput.readLong();
        createDate = objectInput.readLong();
        lastUpdateDate = objectInput.readLong();
        name = objectInput.readUTF();
        addtionalId = objectInput.readInt();
        numericValue = objectInput.readLong();
        stringValue = objectInput.readUTF();
        realValue = objectInput.readDouble();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);
        objectOutput.writeLong(proposalId);
        objectOutput.writeLong(createAuthorId);
        objectOutput.writeLong(lastAuthorId);
        objectOutput.writeLong(createDate);
        objectOutput.writeLong(lastUpdateDate);

        if (name == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(name);
        }

        objectOutput.writeInt(addtionalId);
        objectOutput.writeLong(numericValue);

        if (stringValue == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(stringValue);
        }

        objectOutput.writeDouble(realValue);
    }
}

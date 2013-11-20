package com.ext.portlet.model.impl;

import com.ext.portlet.model.ProposalContestPhaseAttribute;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ProposalContestPhaseAttribute in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalContestPhaseAttribute
 * @generated
 */
public class ProposalContestPhaseAttributeCacheModel implements CacheModel<ProposalContestPhaseAttribute>,
    Externalizable {
    public long id;
    public long proposalId;
    public long contestPhaseId;
    public String name;
    public long additionalId;
    public long numericValue;
    public String stringValue;
    public double realValue;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(17);

        sb.append("{id=");
        sb.append(id);
        sb.append(", proposalId=");
        sb.append(proposalId);
        sb.append(", contestPhaseId=");
        sb.append(contestPhaseId);
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
    public ProposalContestPhaseAttribute toEntityModel() {
        ProposalContestPhaseAttributeImpl proposalContestPhaseAttributeImpl = new ProposalContestPhaseAttributeImpl();

        proposalContestPhaseAttributeImpl.setId(id);
        proposalContestPhaseAttributeImpl.setProposalId(proposalId);
        proposalContestPhaseAttributeImpl.setContestPhaseId(contestPhaseId);

        if (name == null) {
            proposalContestPhaseAttributeImpl.setName(StringPool.BLANK);
        } else {
            proposalContestPhaseAttributeImpl.setName(name);
        }

        proposalContestPhaseAttributeImpl.setAdditionalId(additionalId);
        proposalContestPhaseAttributeImpl.setNumericValue(numericValue);

        if (stringValue == null) {
            proposalContestPhaseAttributeImpl.setStringValue(StringPool.BLANK);
        } else {
            proposalContestPhaseAttributeImpl.setStringValue(stringValue);
        }

        proposalContestPhaseAttributeImpl.setRealValue(realValue);

        proposalContestPhaseAttributeImpl.resetOriginalValues();

        return proposalContestPhaseAttributeImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        proposalId = objectInput.readLong();
        contestPhaseId = objectInput.readLong();
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
        objectOutput.writeLong(contestPhaseId);

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

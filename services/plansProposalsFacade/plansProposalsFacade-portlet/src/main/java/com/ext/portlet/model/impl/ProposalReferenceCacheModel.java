package com.ext.portlet.model.impl;

import com.ext.portlet.model.ProposalReference;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ProposalReference in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalReference
 * @generated
 */
public class ProposalReferenceCacheModel implements CacheModel<ProposalReference>,
    Externalizable {
    public long proposalId;
    public long subProposalId;
    public long sectionAttributeId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{proposalId=");
        sb.append(proposalId);
        sb.append(", subProposalId=");
        sb.append(subProposalId);
        sb.append(", sectionAttributeId=");
        sb.append(sectionAttributeId);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ProposalReference toEntityModel() {
        ProposalReferenceImpl proposalReferenceImpl = new ProposalReferenceImpl();

        proposalReferenceImpl.setProposalId(proposalId);
        proposalReferenceImpl.setSubProposalId(subProposalId);
        proposalReferenceImpl.setSectionAttributeId(sectionAttributeId);

        proposalReferenceImpl.resetOriginalValues();

        return proposalReferenceImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        proposalId = objectInput.readLong();
        subProposalId = objectInput.readLong();
        sectionAttributeId = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(proposalId);
        objectOutput.writeLong(subProposalId);
        objectOutput.writeLong(sectionAttributeId);
    }
}

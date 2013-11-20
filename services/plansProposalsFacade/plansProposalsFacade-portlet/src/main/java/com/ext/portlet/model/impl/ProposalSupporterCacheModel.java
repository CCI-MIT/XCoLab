package com.ext.portlet.model.impl;

import com.ext.portlet.model.ProposalSupporter;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ProposalSupporter in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalSupporter
 * @generated
 */
public class ProposalSupporterCacheModel implements CacheModel<ProposalSupporter>,
    Externalizable {
    public long proposalId;
    public long userId;
    public long createDate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{proposalId=");
        sb.append(proposalId);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ProposalSupporter toEntityModel() {
        ProposalSupporterImpl proposalSupporterImpl = new ProposalSupporterImpl();

        proposalSupporterImpl.setProposalId(proposalId);
        proposalSupporterImpl.setUserId(userId);

        if (createDate == Long.MIN_VALUE) {
            proposalSupporterImpl.setCreateDate(null);
        } else {
            proposalSupporterImpl.setCreateDate(new Date(createDate));
        }

        proposalSupporterImpl.resetOriginalValues();

        return proposalSupporterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        proposalId = objectInput.readLong();
        userId = objectInput.readLong();
        createDate = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(proposalId);
        objectOutput.writeLong(userId);
        objectOutput.writeLong(createDate);
    }
}

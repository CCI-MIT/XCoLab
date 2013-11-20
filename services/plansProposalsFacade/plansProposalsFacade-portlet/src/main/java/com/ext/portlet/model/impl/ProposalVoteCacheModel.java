package com.ext.portlet.model.impl;

import com.ext.portlet.model.ProposalVote;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ProposalVote in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalVote
 * @generated
 */
public class ProposalVoteCacheModel implements CacheModel<ProposalVote>,
    Externalizable {
    public long proposalId;
    public long contestPhaseId;
    public long userId;
    public long createDate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{proposalId=");
        sb.append(proposalId);
        sb.append(", contestPhaseId=");
        sb.append(contestPhaseId);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ProposalVote toEntityModel() {
        ProposalVoteImpl proposalVoteImpl = new ProposalVoteImpl();

        proposalVoteImpl.setProposalId(proposalId);
        proposalVoteImpl.setContestPhaseId(contestPhaseId);
        proposalVoteImpl.setUserId(userId);

        if (createDate == Long.MIN_VALUE) {
            proposalVoteImpl.setCreateDate(null);
        } else {
            proposalVoteImpl.setCreateDate(new Date(createDate));
        }

        proposalVoteImpl.resetOriginalValues();

        return proposalVoteImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        proposalId = objectInput.readLong();
        contestPhaseId = objectInput.readLong();
        userId = objectInput.readLong();
        createDate = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(proposalId);
        objectOutput.writeLong(contestPhaseId);
        objectOutput.writeLong(userId);
        objectOutput.writeLong(createDate);
    }
}

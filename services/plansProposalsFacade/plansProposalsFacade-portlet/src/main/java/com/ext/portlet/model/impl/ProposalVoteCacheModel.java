package com.ext.portlet.model.impl;

import com.ext.portlet.model.ProposalVote;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
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
    public boolean isValid;
    public long confirmationEmailSendDate;
    public String confirmationToken;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{proposalId=");
        sb.append(proposalId);
        sb.append(", contestPhaseId=");
        sb.append(contestPhaseId);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append(", isValid=");
        sb.append(isValid);
        sb.append(", confirmationEmailSendDate=");
        sb.append(confirmationEmailSendDate);
        sb.append(", confirmationToken=");
        sb.append(confirmationToken);
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

        proposalVoteImpl.setIsValid(isValid);

        if (confirmationEmailSendDate == Long.MIN_VALUE) {
            proposalVoteImpl.setConfirmationEmailSendDate(null);
        } else {
            proposalVoteImpl.setConfirmationEmailSendDate(new Date(
                    confirmationEmailSendDate));
        }

        if (confirmationToken == null) {
            proposalVoteImpl.setConfirmationToken(StringPool.BLANK);
        } else {
            proposalVoteImpl.setConfirmationToken(confirmationToken);
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
        isValid = objectInput.readBoolean();
        confirmationEmailSendDate = objectInput.readLong();
        confirmationToken = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(proposalId);
        objectOutput.writeLong(contestPhaseId);
        objectOutput.writeLong(userId);
        objectOutput.writeLong(createDate);
        objectOutput.writeBoolean(isValid);
        objectOutput.writeLong(confirmationEmailSendDate);

        if (confirmationToken == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(confirmationToken);
        }
    }
}

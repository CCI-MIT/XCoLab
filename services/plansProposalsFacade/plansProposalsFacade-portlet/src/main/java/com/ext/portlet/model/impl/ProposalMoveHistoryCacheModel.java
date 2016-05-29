package com.ext.portlet.model.impl;

import com.ext.portlet.model.ProposalMoveHistory;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ProposalMoveHistory in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalMoveHistory
 * @generated
 */
public class ProposalMoveHistoryCacheModel implements CacheModel<ProposalMoveHistory>,
    Externalizable {
    public long id_;
    public long sourceProposalId;
    public long sourceContestId;
    public long sourcePhaseId;
    public long targetProposalId;
    public long targetContestId;
    public long targetPhaseId;
    public long movingUserId;
    public long moveDate;
    public String moveType;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(21);

        sb.append("{id_=");
        sb.append(id_);
        sb.append(", sourceProposalId=");
        sb.append(sourceProposalId);
        sb.append(", sourceContestId=");
        sb.append(sourceContestId);
        sb.append(", sourcePhaseId=");
        sb.append(sourcePhaseId);
        sb.append(", targetProposalId=");
        sb.append(targetProposalId);
        sb.append(", targetContestId=");
        sb.append(targetContestId);
        sb.append(", targetPhaseId=");
        sb.append(targetPhaseId);
        sb.append(", movingUserId=");
        sb.append(movingUserId);
        sb.append(", moveDate=");
        sb.append(moveDate);
        sb.append(", moveType=");
        sb.append(moveType);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ProposalMoveHistory toEntityModel() {
        ProposalMoveHistoryImpl proposalMoveHistoryImpl = new ProposalMoveHistoryImpl();

        proposalMoveHistoryImpl.setId_(id_);
        proposalMoveHistoryImpl.setSourceProposalId(sourceProposalId);
        proposalMoveHistoryImpl.setSourceContestId(sourceContestId);
        proposalMoveHistoryImpl.setSourcePhaseId(sourcePhaseId);
        proposalMoveHistoryImpl.setTargetProposalId(targetProposalId);
        proposalMoveHistoryImpl.setTargetContestId(targetContestId);
        proposalMoveHistoryImpl.setTargetPhaseId(targetPhaseId);
        proposalMoveHistoryImpl.setMovingUserId(movingUserId);

        if (moveDate == Long.MIN_VALUE) {
            proposalMoveHistoryImpl.setMoveDate(null);
        } else {
            proposalMoveHistoryImpl.setMoveDate(new Date(moveDate));
        }

        if (moveType == null) {
            proposalMoveHistoryImpl.setMoveType(StringPool.BLANK);
        } else {
            proposalMoveHistoryImpl.setMoveType(moveType);
        }

        proposalMoveHistoryImpl.resetOriginalValues();

        return proposalMoveHistoryImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id_ = objectInput.readLong();
        sourceProposalId = objectInput.readLong();
        sourceContestId = objectInput.readLong();
        sourcePhaseId = objectInput.readLong();
        targetProposalId = objectInput.readLong();
        targetContestId = objectInput.readLong();
        targetPhaseId = objectInput.readLong();
        movingUserId = objectInput.readLong();
        moveDate = objectInput.readLong();
        moveType = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id_);
        objectOutput.writeLong(sourceProposalId);
        objectOutput.writeLong(sourceContestId);
        objectOutput.writeLong(sourcePhaseId);
        objectOutput.writeLong(targetProposalId);
        objectOutput.writeLong(targetContestId);
        objectOutput.writeLong(targetPhaseId);
        objectOutput.writeLong(movingUserId);
        objectOutput.writeLong(moveDate);

        if (moveType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(moveType);
        }
    }
}

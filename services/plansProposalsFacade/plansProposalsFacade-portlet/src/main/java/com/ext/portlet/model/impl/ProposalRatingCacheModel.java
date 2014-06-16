package com.ext.portlet.model.impl;

import com.ext.portlet.model.ProposalRating;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ProposalRating in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalRating
 * @generated
 */
public class ProposalRatingCacheModel implements CacheModel<ProposalRating>,
    Externalizable {
    public long id;
    public long proposalId;
    public long contestPhaseId;
    public long userId;
    public int ratingType;
    public long rating;
    public String comment;
    public String otherDataString;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(17);

        sb.append("{id=");
        sb.append(id);
        sb.append(", proposalId=");
        sb.append(proposalId);
        sb.append(", contestPhaseId=");
        sb.append(contestPhaseId);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", ratingType=");
        sb.append(ratingType);
        sb.append(", rating=");
        sb.append(rating);
        sb.append(", comment=");
        sb.append(comment);
        sb.append(", otherDataString=");
        sb.append(otherDataString);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ProposalRating toEntityModel() {
        ProposalRatingImpl proposalRatingImpl = new ProposalRatingImpl();

        proposalRatingImpl.setId(id);
        proposalRatingImpl.setProposalId(proposalId);
        proposalRatingImpl.setContestPhaseId(contestPhaseId);
        proposalRatingImpl.setUserId(userId);
        proposalRatingImpl.setRatingType(ratingType);
        proposalRatingImpl.setRating(rating);

        if (comment == null) {
            proposalRatingImpl.setComment(StringPool.BLANK);
        } else {
            proposalRatingImpl.setComment(comment);
        }

        if (otherDataString == null) {
            proposalRatingImpl.setOtherDataString(StringPool.BLANK);
        } else {
            proposalRatingImpl.setOtherDataString(otherDataString);
        }

        proposalRatingImpl.resetOriginalValues();

        return proposalRatingImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        proposalId = objectInput.readLong();
        contestPhaseId = objectInput.readLong();
        userId = objectInput.readLong();
        ratingType = objectInput.readInt();
        rating = objectInput.readLong();
        comment = objectInput.readUTF();
        otherDataString = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);
        objectOutput.writeLong(proposalId);
        objectOutput.writeLong(contestPhaseId);
        objectOutput.writeLong(userId);
        objectOutput.writeInt(ratingType);
        objectOutput.writeLong(rating);

        if (comment == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(comment);
        }

        if (otherDataString == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(otherDataString);
        }
    }
}

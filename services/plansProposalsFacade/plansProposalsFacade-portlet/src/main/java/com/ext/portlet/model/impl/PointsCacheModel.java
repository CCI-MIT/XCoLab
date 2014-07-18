package com.ext.portlet.model.impl;

import com.ext.portlet.model.Points;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Points in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Points
 * @generated
 */
public class PointsCacheModel implements CacheModel<Points>, Externalizable {
    public long id;
    public long proposalId;
    public long userId;
    public double materializedPoints;
    public double hypotheticalPoints;
    public long pointsSourceId;
    public long originatingContestPK;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{id=");
        sb.append(id);
        sb.append(", proposalId=");
        sb.append(proposalId);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", materializedPoints=");
        sb.append(materializedPoints);
        sb.append(", hypotheticalPoints=");
        sb.append(hypotheticalPoints);
        sb.append(", pointsSourceId=");
        sb.append(pointsSourceId);
        sb.append(", originatingContestPK=");
        sb.append(originatingContestPK);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public Points toEntityModel() {
        PointsImpl pointsImpl = new PointsImpl();

        pointsImpl.setId(id);
        pointsImpl.setProposalId(proposalId);
        pointsImpl.setUserId(userId);
        pointsImpl.setMaterializedPoints(materializedPoints);
        pointsImpl.setHypotheticalPoints(hypotheticalPoints);
        pointsImpl.setPointsSourceId(pointsSourceId);
        pointsImpl.setOriginatingContestPK(originatingContestPK);

        pointsImpl.resetOriginalValues();

        return pointsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        proposalId = objectInput.readLong();
        userId = objectInput.readLong();
        materializedPoints = objectInput.readDouble();
        hypotheticalPoints = objectInput.readDouble();
        pointsSourceId = objectInput.readLong();
        originatingContestPK = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);
        objectOutput.writeLong(proposalId);
        objectOutput.writeLong(userId);
        objectOutput.writeDouble(materializedPoints);
        objectOutput.writeDouble(hypotheticalPoints);
        objectOutput.writeLong(pointsSourceId);
        objectOutput.writeLong(originatingContestPK);
    }
}

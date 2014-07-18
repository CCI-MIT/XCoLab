package com.ext.portlet.model.impl;

import com.ext.portlet.model.PointDistributionTarget;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing PointDistributionTarget in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PointDistributionTarget
 * @generated
 */
public class PointDistributionTargetCacheModel implements CacheModel<PointDistributionTarget>,
    Externalizable {
    public long id;
    public long contestId;
    public long proposalId;
    public double numberOfPoints;
    public long pointTypeOverride;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{id=");
        sb.append(id);
        sb.append(", contestId=");
        sb.append(contestId);
        sb.append(", proposalId=");
        sb.append(proposalId);
        sb.append(", numberOfPoints=");
        sb.append(numberOfPoints);
        sb.append(", pointTypeOverride=");
        sb.append(pointTypeOverride);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public PointDistributionTarget toEntityModel() {
        PointDistributionTargetImpl pointDistributionTargetImpl = new PointDistributionTargetImpl();

        pointDistributionTargetImpl.setId(id);
        pointDistributionTargetImpl.setContestId(contestId);
        pointDistributionTargetImpl.setProposalId(proposalId);
        pointDistributionTargetImpl.setNumberOfPoints(numberOfPoints);
        pointDistributionTargetImpl.setPointTypeOverride(pointTypeOverride);

        pointDistributionTargetImpl.resetOriginalValues();

        return pointDistributionTargetImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        contestId = objectInput.readLong();
        proposalId = objectInput.readLong();
        numberOfPoints = objectInput.readDouble();
        pointTypeOverride = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);
        objectOutput.writeLong(contestId);
        objectOutput.writeLong(proposalId);
        objectOutput.writeDouble(numberOfPoints);
        objectOutput.writeLong(pointTypeOverride);
    }
}

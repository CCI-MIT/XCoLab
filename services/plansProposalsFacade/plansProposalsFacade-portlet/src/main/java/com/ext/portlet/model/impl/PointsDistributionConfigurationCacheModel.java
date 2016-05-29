package com.ext.portlet.model.impl;

import com.ext.portlet.model.PointsDistributionConfiguration;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PointsDistributionConfiguration in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PointsDistributionConfiguration
 * @generated
 */
public class PointsDistributionConfigurationCacheModel implements CacheModel<PointsDistributionConfiguration>,
    Externalizable {
    public long id;
    public long proposalId;
    public long pointTypeId;
    public long targetUserId;
    public long targetSubProposalId;
    public long targetPlanSectionDefinitionId;
    public double percentage;
    public long creator;
    public long createDate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(19);

        sb.append("{id=");
        sb.append(id);
        sb.append(", proposalId=");
        sb.append(proposalId);
        sb.append(", pointTypeId=");
        sb.append(pointTypeId);
        sb.append(", targetUserId=");
        sb.append(targetUserId);
        sb.append(", targetSubProposalId=");
        sb.append(targetSubProposalId);
        sb.append(", targetPlanSectionDefinitionId=");
        sb.append(targetPlanSectionDefinitionId);
        sb.append(", percentage=");
        sb.append(percentage);
        sb.append(", creator=");
        sb.append(creator);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public PointsDistributionConfiguration toEntityModel() {
        PointsDistributionConfigurationImpl pointsDistributionConfigurationImpl = new PointsDistributionConfigurationImpl();

        pointsDistributionConfigurationImpl.setId(id);
        pointsDistributionConfigurationImpl.setProposalId(proposalId);
        pointsDistributionConfigurationImpl.setPointTypeId(pointTypeId);
        pointsDistributionConfigurationImpl.setTargetUserId(targetUserId);
        pointsDistributionConfigurationImpl.setTargetSubProposalId(targetSubProposalId);
        pointsDistributionConfigurationImpl.setTargetPlanSectionDefinitionId(targetPlanSectionDefinitionId);
        pointsDistributionConfigurationImpl.setPercentage(percentage);
        pointsDistributionConfigurationImpl.setCreator(creator);

        if (createDate == Long.MIN_VALUE) {
            pointsDistributionConfigurationImpl.setCreateDate(null);
        } else {
            pointsDistributionConfigurationImpl.setCreateDate(new Date(
                    createDate));
        }

        pointsDistributionConfigurationImpl.resetOriginalValues();

        return pointsDistributionConfigurationImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        proposalId = objectInput.readLong();
        pointTypeId = objectInput.readLong();
        targetUserId = objectInput.readLong();
        targetSubProposalId = objectInput.readLong();
        targetPlanSectionDefinitionId = objectInput.readLong();
        percentage = objectInput.readDouble();
        creator = objectInput.readLong();
        createDate = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);
        objectOutput.writeLong(proposalId);
        objectOutput.writeLong(pointTypeId);
        objectOutput.writeLong(targetUserId);
        objectOutput.writeLong(targetSubProposalId);
        objectOutput.writeLong(targetPlanSectionDefinitionId);
        objectOutput.writeDouble(percentage);
        objectOutput.writeLong(creator);
        objectOutput.writeLong(createDate);
    }
}

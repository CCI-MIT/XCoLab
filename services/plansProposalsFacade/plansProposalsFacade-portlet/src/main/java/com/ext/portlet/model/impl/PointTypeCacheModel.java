package com.ext.portlet.model.impl;

import com.ext.portlet.model.PointType;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing PointType in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PointType
 * @generated
 */
public class PointTypeCacheModel implements CacheModel<PointType>,
    Externalizable {
    public long id;
    public long parentPointTypeId;
    public double percentageOfParent;
    public String distributionStrategy;
    public String receiverLimitationStrategy;
    public String name;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{id=");
        sb.append(id);
        sb.append(", parentPointTypeId=");
        sb.append(parentPointTypeId);
        sb.append(", percentageOfParent=");
        sb.append(percentageOfParent);
        sb.append(", distributionStrategy=");
        sb.append(distributionStrategy);
        sb.append(", receiverLimitationStrategy=");
        sb.append(receiverLimitationStrategy);
        sb.append(", name=");
        sb.append(name);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public PointType toEntityModel() {
        PointTypeImpl pointTypeImpl = new PointTypeImpl();

        pointTypeImpl.setId(id);
        pointTypeImpl.setParentPointTypeId(parentPointTypeId);
        pointTypeImpl.setPercentageOfParent(percentageOfParent);

        if (distributionStrategy == null) {
            pointTypeImpl.setDistributionStrategy(StringPool.BLANK);
        } else {
            pointTypeImpl.setDistributionStrategy(distributionStrategy);
        }

        if (receiverLimitationStrategy == null) {
            pointTypeImpl.setReceiverLimitationStrategy(StringPool.BLANK);
        } else {
            pointTypeImpl.setReceiverLimitationStrategy(receiverLimitationStrategy);
        }

        if (name == null) {
            pointTypeImpl.setName(StringPool.BLANK);
        } else {
            pointTypeImpl.setName(name);
        }

        pointTypeImpl.resetOriginalValues();

        return pointTypeImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        parentPointTypeId = objectInput.readLong();
        percentageOfParent = objectInput.readDouble();
        distributionStrategy = objectInput.readUTF();
        receiverLimitationStrategy = objectInput.readUTF();
        name = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);
        objectOutput.writeLong(parentPointTypeId);
        objectOutput.writeDouble(percentageOfParent);

        if (distributionStrategy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(distributionStrategy);
        }

        if (receiverLimitationStrategy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(receiverLimitationStrategy);
        }

        if (name == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(name);
        }
    }
}

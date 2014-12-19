package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlansFilterPosition;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing PlansFilterPosition in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlansFilterPosition
 * @generated
 */
public class PlansFilterPositionCacheModel implements CacheModel<PlansFilterPosition>,
    Externalizable {
    public long userId;
    public long planTypeId;
    public long positionId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{userId=");
        sb.append(userId);
        sb.append(", planTypeId=");
        sb.append(planTypeId);
        sb.append(", positionId=");
        sb.append(positionId);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public PlansFilterPosition toEntityModel() {
        PlansFilterPositionImpl plansFilterPositionImpl = new PlansFilterPositionImpl();

        plansFilterPositionImpl.setUserId(userId);
        plansFilterPositionImpl.setPlanTypeId(planTypeId);
        plansFilterPositionImpl.setPositionId(positionId);

        plansFilterPositionImpl.resetOriginalValues();

        return plansFilterPositionImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        userId = objectInput.readLong();
        planTypeId = objectInput.readLong();
        positionId = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(userId);
        objectOutput.writeLong(planTypeId);
        objectOutput.writeLong(positionId);
    }
}

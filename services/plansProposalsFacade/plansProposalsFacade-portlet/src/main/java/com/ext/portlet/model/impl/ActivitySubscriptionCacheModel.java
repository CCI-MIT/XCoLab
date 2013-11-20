package com.ext.portlet.model.impl;

import com.ext.portlet.model.ActivitySubscription;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ActivitySubscription in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ActivitySubscription
 * @generated
 */
public class ActivitySubscriptionCacheModel implements CacheModel<ActivitySubscription>,
    Externalizable {
    public long pk;
    public long classNameId;
    public long classPK;
    public int type;
    public int automaticSubscriptionCounter;
    public String extraData;
    public long receiverId;
    public long createDate;
    public long modifiedDate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(19);

        sb.append("{pk=");
        sb.append(pk);
        sb.append(", classNameId=");
        sb.append(classNameId);
        sb.append(", classPK=");
        sb.append(classPK);
        sb.append(", type=");
        sb.append(type);
        sb.append(", automaticSubscriptionCounter=");
        sb.append(automaticSubscriptionCounter);
        sb.append(", extraData=");
        sb.append(extraData);
        sb.append(", receiverId=");
        sb.append(receiverId);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ActivitySubscription toEntityModel() {
        ActivitySubscriptionImpl activitySubscriptionImpl = new ActivitySubscriptionImpl();

        activitySubscriptionImpl.setPk(pk);
        activitySubscriptionImpl.setClassNameId(classNameId);
        activitySubscriptionImpl.setClassPK(classPK);
        activitySubscriptionImpl.setType(type);
        activitySubscriptionImpl.setAutomaticSubscriptionCounter(automaticSubscriptionCounter);

        if (extraData == null) {
            activitySubscriptionImpl.setExtraData(StringPool.BLANK);
        } else {
            activitySubscriptionImpl.setExtraData(extraData);
        }

        activitySubscriptionImpl.setReceiverId(receiverId);

        if (createDate == Long.MIN_VALUE) {
            activitySubscriptionImpl.setCreateDate(null);
        } else {
            activitySubscriptionImpl.setCreateDate(new Date(createDate));
        }

        if (modifiedDate == Long.MIN_VALUE) {
            activitySubscriptionImpl.setModifiedDate(null);
        } else {
            activitySubscriptionImpl.setModifiedDate(new Date(modifiedDate));
        }

        activitySubscriptionImpl.resetOriginalValues();

        return activitySubscriptionImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        pk = objectInput.readLong();
        classNameId = objectInput.readLong();
        classPK = objectInput.readLong();
        type = objectInput.readInt();
        automaticSubscriptionCounter = objectInput.readInt();
        extraData = objectInput.readUTF();
        receiverId = objectInput.readLong();
        createDate = objectInput.readLong();
        modifiedDate = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(pk);
        objectOutput.writeLong(classNameId);
        objectOutput.writeLong(classPK);
        objectOutput.writeInt(type);
        objectOutput.writeInt(automaticSubscriptionCounter);

        if (extraData == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(extraData);
        }

        objectOutput.writeLong(receiverId);
        objectOutput.writeLong(createDate);
        objectOutput.writeLong(modifiedDate);
    }
}

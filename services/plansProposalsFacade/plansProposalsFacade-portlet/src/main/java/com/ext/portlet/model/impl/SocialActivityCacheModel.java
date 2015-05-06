package com.ext.portlet.model.impl;

import com.ext.portlet.model.SocialActivity;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SocialActivity in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivity
 * @generated
 */
public class SocialActivityCacheModel implements CacheModel<SocialActivity>,
    Externalizable {
    public long activityId;
    public long userId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(5);

        sb.append("{activityId=");
        sb.append(activityId);
        sb.append(", userId=");
        sb.append(userId);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public SocialActivity toEntityModel() {
        SocialActivityImpl socialActivityImpl = new SocialActivityImpl();

        socialActivityImpl.setActivityId(activityId);
        socialActivityImpl.setUserId(userId);

        socialActivityImpl.resetOriginalValues();

        return socialActivityImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        activityId = objectInput.readLong();
        userId = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(activityId);
        objectOutput.writeLong(userId);
    }
}

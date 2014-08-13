package com.ext.portlet.model.impl;

import com.ext.portlet.model.TrackedVisitor2User;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TrackedVisitor2User in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TrackedVisitor2User
 * @generated
 */
public class TrackedVisitor2UserCacheModel implements CacheModel<TrackedVisitor2User>,
    Externalizable {
    public long id;
    public String uuid;
    public long userId;
    public long createDate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{id=");
        sb.append(id);
        sb.append(", uuid=");
        sb.append(uuid);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public TrackedVisitor2User toEntityModel() {
        TrackedVisitor2UserImpl trackedVisitor2UserImpl = new TrackedVisitor2UserImpl();

        trackedVisitor2UserImpl.setId(id);

        if (uuid == null) {
            trackedVisitor2UserImpl.setUuid(StringPool.BLANK);
        } else {
            trackedVisitor2UserImpl.setUuid(uuid);
        }

        trackedVisitor2UserImpl.setUserId(userId);

        if (createDate == Long.MIN_VALUE) {
            trackedVisitor2UserImpl.setCreateDate(null);
        } else {
            trackedVisitor2UserImpl.setCreateDate(new Date(createDate));
        }

        trackedVisitor2UserImpl.resetOriginalValues();

        return trackedVisitor2UserImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        uuid = objectInput.readUTF();
        userId = objectInput.readLong();
        createDate = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);

        if (uuid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(uuid);
        }

        objectOutput.writeLong(userId);
        objectOutput.writeLong(createDate);
    }
}

package com.ext.portlet.model.impl;

import com.ext.portlet.model.User_;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing User_ in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see User_
 * @generated
 */
public class User_CacheModel implements CacheModel<User_>, Externalizable {
    public long userId;
    public long createDate;
    public String screenName;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{userId=");
        sb.append(userId);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append(", screenName=");
        sb.append(screenName);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public User_ toEntityModel() {
        User_Impl user_Impl = new User_Impl();

        user_Impl.setUserId(userId);

        if (createDate == Long.MIN_VALUE) {
            user_Impl.setCreateDate(null);
        } else {
            user_Impl.setCreateDate(new Date(createDate));
        }

        if (screenName == null) {
            user_Impl.setScreenName(StringPool.BLANK);
        } else {
            user_Impl.setScreenName(screenName);
        }

        user_Impl.resetOriginalValues();

        return user_Impl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        userId = objectInput.readLong();
        createDate = objectInput.readLong();
        screenName = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(userId);
        objectOutput.writeLong(createDate);

        if (screenName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(screenName);
        }
    }
}

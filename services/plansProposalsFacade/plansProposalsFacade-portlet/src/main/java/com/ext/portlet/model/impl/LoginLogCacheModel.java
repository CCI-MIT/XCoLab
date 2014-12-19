package com.ext.portlet.model.impl;

import com.ext.portlet.model.LoginLog;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LoginLog in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see LoginLog
 * @generated
 */
public class LoginLogCacheModel implements CacheModel<LoginLog>, Externalizable {
    public long pk;
    public long userId;
    public long createDate;
    public String ipAddress;
    public String city;
    public String country;
    public String entryUrl;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{pk=");
        sb.append(pk);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append(", ipAddress=");
        sb.append(ipAddress);
        sb.append(", city=");
        sb.append(city);
        sb.append(", country=");
        sb.append(country);
        sb.append(", entryUrl=");
        sb.append(entryUrl);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public LoginLog toEntityModel() {
        LoginLogImpl loginLogImpl = new LoginLogImpl();

        loginLogImpl.setPk(pk);
        loginLogImpl.setUserId(userId);

        if (createDate == Long.MIN_VALUE) {
            loginLogImpl.setCreateDate(null);
        } else {
            loginLogImpl.setCreateDate(new Date(createDate));
        }

        if (ipAddress == null) {
            loginLogImpl.setIpAddress(StringPool.BLANK);
        } else {
            loginLogImpl.setIpAddress(ipAddress);
        }

        if (city == null) {
            loginLogImpl.setCity(StringPool.BLANK);
        } else {
            loginLogImpl.setCity(city);
        }

        if (country == null) {
            loginLogImpl.setCountry(StringPool.BLANK);
        } else {
            loginLogImpl.setCountry(country);
        }

        if (entryUrl == null) {
            loginLogImpl.setEntryUrl(StringPool.BLANK);
        } else {
            loginLogImpl.setEntryUrl(entryUrl);
        }

        loginLogImpl.resetOriginalValues();

        return loginLogImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        pk = objectInput.readLong();
        userId = objectInput.readLong();
        createDate = objectInput.readLong();
        ipAddress = objectInput.readUTF();
        city = objectInput.readUTF();
        country = objectInput.readUTF();
        entryUrl = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(pk);
        objectOutput.writeLong(userId);
        objectOutput.writeLong(createDate);

        if (ipAddress == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(ipAddress);
        }

        if (city == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(city);
        }

        if (country == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(country);
        }

        if (entryUrl == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(entryUrl);
        }
    }
}

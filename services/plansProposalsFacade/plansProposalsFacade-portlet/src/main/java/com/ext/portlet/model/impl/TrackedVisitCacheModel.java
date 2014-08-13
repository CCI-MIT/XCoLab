package com.ext.portlet.model.impl;

import com.ext.portlet.model.TrackedVisit;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing TrackedVisit in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TrackedVisit
 * @generated
 */
public class TrackedVisitCacheModel implements CacheModel<TrackedVisit>,
    Externalizable {
    public long id;
    public String uuid;
    public String ip;
    public String city;
    public String country;
    public String url;
    public String browser;
    public String headers;
    public String referer;
    public long createDate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(21);

        sb.append("{id=");
        sb.append(id);
        sb.append(", uuid=");
        sb.append(uuid);
        sb.append(", ip=");
        sb.append(ip);
        sb.append(", city=");
        sb.append(city);
        sb.append(", country=");
        sb.append(country);
        sb.append(", url=");
        sb.append(url);
        sb.append(", browser=");
        sb.append(browser);
        sb.append(", headers=");
        sb.append(headers);
        sb.append(", referer=");
        sb.append(referer);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public TrackedVisit toEntityModel() {
        TrackedVisitImpl trackedVisitImpl = new TrackedVisitImpl();

        trackedVisitImpl.setId(id);

        if (uuid == null) {
            trackedVisitImpl.setUuid(StringPool.BLANK);
        } else {
            trackedVisitImpl.setUuid(uuid);
        }

        if (ip == null) {
            trackedVisitImpl.setIp(StringPool.BLANK);
        } else {
            trackedVisitImpl.setIp(ip);
        }

        if (city == null) {
            trackedVisitImpl.setCity(StringPool.BLANK);
        } else {
            trackedVisitImpl.setCity(city);
        }

        if (country == null) {
            trackedVisitImpl.setCountry(StringPool.BLANK);
        } else {
            trackedVisitImpl.setCountry(country);
        }

        if (url == null) {
            trackedVisitImpl.setUrl(StringPool.BLANK);
        } else {
            trackedVisitImpl.setUrl(url);
        }

        if (browser == null) {
            trackedVisitImpl.setBrowser(StringPool.BLANK);
        } else {
            trackedVisitImpl.setBrowser(browser);
        }

        if (headers == null) {
            trackedVisitImpl.setHeaders(StringPool.BLANK);
        } else {
            trackedVisitImpl.setHeaders(headers);
        }

        if (referer == null) {
            trackedVisitImpl.setReferer(StringPool.BLANK);
        } else {
            trackedVisitImpl.setReferer(referer);
        }

        if (createDate == Long.MIN_VALUE) {
            trackedVisitImpl.setCreateDate(null);
        } else {
            trackedVisitImpl.setCreateDate(new Date(createDate));
        }

        trackedVisitImpl.resetOriginalValues();

        return trackedVisitImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        uuid = objectInput.readUTF();
        ip = objectInput.readUTF();
        city = objectInput.readUTF();
        country = objectInput.readUTF();
        url = objectInput.readUTF();
        browser = objectInput.readUTF();
        headers = objectInput.readUTF();
        referer = objectInput.readUTF();
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

        if (ip == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(ip);
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

        if (url == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(url);
        }

        if (browser == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(browser);
        }

        if (headers == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(headers);
        }

        if (referer == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(referer);
        }

        objectOutput.writeLong(createDate);
    }
}

package com.ext.portlet.model.impl;

import com.ext.portlet.model.BalloonUserTracking;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing BalloonUserTracking in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see BalloonUserTracking
 * @generated
 */
public class BalloonUserTrackingCacheModel implements CacheModel<BalloonUserTracking>,
    Externalizable {
    public String uuid;
    public String email;
    public String parent;
    public String ip;
    public long createDate;
    public long registrationDate;
    public long formFiledDate;
    public long userId;
    public long balloonTextId;
    public String referrer;
    public double latitude;
    public double longitude;
    public String city;
    public String country;
    public String extraData;
    public String balloonLinkUuid;
    public String balloonLinkContext;
    public String userAgent;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(37);

        sb.append("{uuid=");
        sb.append(uuid);
        sb.append(", email=");
        sb.append(email);
        sb.append(", parent=");
        sb.append(parent);
        sb.append(", ip=");
        sb.append(ip);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append(", registrationDate=");
        sb.append(registrationDate);
        sb.append(", formFiledDate=");
        sb.append(formFiledDate);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", balloonTextId=");
        sb.append(balloonTextId);
        sb.append(", referrer=");
        sb.append(referrer);
        sb.append(", latitude=");
        sb.append(latitude);
        sb.append(", longitude=");
        sb.append(longitude);
        sb.append(", city=");
        sb.append(city);
        sb.append(", country=");
        sb.append(country);
        sb.append(", extraData=");
        sb.append(extraData);
        sb.append(", balloonLinkUuid=");
        sb.append(balloonLinkUuid);
        sb.append(", balloonLinkContext=");
        sb.append(balloonLinkContext);
        sb.append(", userAgent=");
        sb.append(userAgent);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public BalloonUserTracking toEntityModel() {
        BalloonUserTrackingImpl balloonUserTrackingImpl = new BalloonUserTrackingImpl();

        if (uuid == null) {
            balloonUserTrackingImpl.setUuid(StringPool.BLANK);
        } else {
            balloonUserTrackingImpl.setUuid(uuid);
        }

        if (email == null) {
            balloonUserTrackingImpl.setEmail(StringPool.BLANK);
        } else {
            balloonUserTrackingImpl.setEmail(email);
        }

        if (parent == null) {
            balloonUserTrackingImpl.setParent(StringPool.BLANK);
        } else {
            balloonUserTrackingImpl.setParent(parent);
        }

        if (ip == null) {
            balloonUserTrackingImpl.setIp(StringPool.BLANK);
        } else {
            balloonUserTrackingImpl.setIp(ip);
        }

        if (createDate == Long.MIN_VALUE) {
            balloonUserTrackingImpl.setCreateDate(null);
        } else {
            balloonUserTrackingImpl.setCreateDate(new Date(createDate));
        }

        if (registrationDate == Long.MIN_VALUE) {
            balloonUserTrackingImpl.setRegistrationDate(null);
        } else {
            balloonUserTrackingImpl.setRegistrationDate(new Date(
                    registrationDate));
        }

        if (formFiledDate == Long.MIN_VALUE) {
            balloonUserTrackingImpl.setFormFiledDate(null);
        } else {
            balloonUserTrackingImpl.setFormFiledDate(new Date(formFiledDate));
        }

        balloonUserTrackingImpl.setUserId(userId);
        balloonUserTrackingImpl.setBalloonTextId(balloonTextId);

        if (referrer == null) {
            balloonUserTrackingImpl.setReferrer(StringPool.BLANK);
        } else {
            balloonUserTrackingImpl.setReferrer(referrer);
        }

        balloonUserTrackingImpl.setLatitude(latitude);
        balloonUserTrackingImpl.setLongitude(longitude);

        if (city == null) {
            balloonUserTrackingImpl.setCity(StringPool.BLANK);
        } else {
            balloonUserTrackingImpl.setCity(city);
        }

        if (country == null) {
            balloonUserTrackingImpl.setCountry(StringPool.BLANK);
        } else {
            balloonUserTrackingImpl.setCountry(country);
        }

        if (extraData == null) {
            balloonUserTrackingImpl.setExtraData(StringPool.BLANK);
        } else {
            balloonUserTrackingImpl.setExtraData(extraData);
        }

        if (balloonLinkUuid == null) {
            balloonUserTrackingImpl.setBalloonLinkUuid(StringPool.BLANK);
        } else {
            balloonUserTrackingImpl.setBalloonLinkUuid(balloonLinkUuid);
        }

        if (balloonLinkContext == null) {
            balloonUserTrackingImpl.setBalloonLinkContext(StringPool.BLANK);
        } else {
            balloonUserTrackingImpl.setBalloonLinkContext(balloonLinkContext);
        }

        if (userAgent == null) {
            balloonUserTrackingImpl.setUserAgent(StringPool.BLANK);
        } else {
            balloonUserTrackingImpl.setUserAgent(userAgent);
        }

        balloonUserTrackingImpl.resetOriginalValues();

        return balloonUserTrackingImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        uuid = objectInput.readUTF();
        email = objectInput.readUTF();
        parent = objectInput.readUTF();
        ip = objectInput.readUTF();
        createDate = objectInput.readLong();
        registrationDate = objectInput.readLong();
        formFiledDate = objectInput.readLong();
        userId = objectInput.readLong();
        balloonTextId = objectInput.readLong();
        referrer = objectInput.readUTF();
        latitude = objectInput.readDouble();
        longitude = objectInput.readDouble();
        city = objectInput.readUTF();
        country = objectInput.readUTF();
        extraData = objectInput.readUTF();
        balloonLinkUuid = objectInput.readUTF();
        balloonLinkContext = objectInput.readUTF();
        userAgent = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (uuid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(uuid);
        }

        if (email == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(email);
        }

        if (parent == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(parent);
        }

        if (ip == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(ip);
        }

        objectOutput.writeLong(createDate);
        objectOutput.writeLong(registrationDate);
        objectOutput.writeLong(formFiledDate);
        objectOutput.writeLong(userId);
        objectOutput.writeLong(balloonTextId);

        if (referrer == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(referrer);
        }

        objectOutput.writeDouble(latitude);
        objectOutput.writeDouble(longitude);

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

        if (extraData == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(extraData);
        }

        if (balloonLinkUuid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(balloonLinkUuid);
        }

        if (balloonLinkContext == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(balloonLinkContext);
        }

        if (userAgent == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(userAgent);
        }
    }
}

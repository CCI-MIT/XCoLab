package com.ext.portlet.model.impl;

import com.ext.portlet.model.BalloonLink;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing BalloonLink in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see BalloonLink
 * @generated
 */
public class BalloonLinkCacheModel implements CacheModel<BalloonLink>,
    Externalizable {
    public String uuid;
    public String targetUrl;
    public int visits;
    public String balloonUserUuid;
    public long createDate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{uuid=");
        sb.append(uuid);
        sb.append(", targetUrl=");
        sb.append(targetUrl);
        sb.append(", visits=");
        sb.append(visits);
        sb.append(", balloonUserUuid=");
        sb.append(balloonUserUuid);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public BalloonLink toEntityModel() {
        BalloonLinkImpl balloonLinkImpl = new BalloonLinkImpl();

        if (uuid == null) {
            balloonLinkImpl.setUuid(StringPool.BLANK);
        } else {
            balloonLinkImpl.setUuid(uuid);
        }

        if (targetUrl == null) {
            balloonLinkImpl.setTargetUrl(StringPool.BLANK);
        } else {
            balloonLinkImpl.setTargetUrl(targetUrl);
        }

        balloonLinkImpl.setVisits(visits);

        if (balloonUserUuid == null) {
            balloonLinkImpl.setBalloonUserUuid(StringPool.BLANK);
        } else {
            balloonLinkImpl.setBalloonUserUuid(balloonUserUuid);
        }

        if (createDate == Long.MIN_VALUE) {
            balloonLinkImpl.setCreateDate(null);
        } else {
            balloonLinkImpl.setCreateDate(new Date(createDate));
        }

        balloonLinkImpl.resetOriginalValues();

        return balloonLinkImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        uuid = objectInput.readUTF();
        targetUrl = objectInput.readUTF();
        visits = objectInput.readInt();
        balloonUserUuid = objectInput.readUTF();
        createDate = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (uuid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(uuid);
        }

        if (targetUrl == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(targetUrl);
        }

        objectOutput.writeInt(visits);

        if (balloonUserUuid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(balloonUserUuid);
        }

        objectOutput.writeLong(createDate);
    }
}

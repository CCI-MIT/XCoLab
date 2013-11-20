package com.ext.portlet.model.impl;

import com.ext.portlet.model.BalloonStatsEntry;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing BalloonStatsEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see BalloonStatsEntry
 * @generated
 */
public class BalloonStatsEntryCacheModel implements CacheModel<BalloonStatsEntry>,
    Externalizable {
    public long id;
    public long firstContestId;
    public long secondContestId;
    public int choosenContest;
    public String cookie;
    public String ip;
    public String extraData;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{id=");
        sb.append(id);
        sb.append(", firstContestId=");
        sb.append(firstContestId);
        sb.append(", secondContestId=");
        sb.append(secondContestId);
        sb.append(", choosenContest=");
        sb.append(choosenContest);
        sb.append(", cookie=");
        sb.append(cookie);
        sb.append(", ip=");
        sb.append(ip);
        sb.append(", extraData=");
        sb.append(extraData);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public BalloonStatsEntry toEntityModel() {
        BalloonStatsEntryImpl balloonStatsEntryImpl = new BalloonStatsEntryImpl();

        balloonStatsEntryImpl.setId(id);
        balloonStatsEntryImpl.setFirstContestId(firstContestId);
        balloonStatsEntryImpl.setSecondContestId(secondContestId);
        balloonStatsEntryImpl.setChoosenContest(choosenContest);

        if (cookie == null) {
            balloonStatsEntryImpl.setCookie(StringPool.BLANK);
        } else {
            balloonStatsEntryImpl.setCookie(cookie);
        }

        if (ip == null) {
            balloonStatsEntryImpl.setIp(StringPool.BLANK);
        } else {
            balloonStatsEntryImpl.setIp(ip);
        }

        if (extraData == null) {
            balloonStatsEntryImpl.setExtraData(StringPool.BLANK);
        } else {
            balloonStatsEntryImpl.setExtraData(extraData);
        }

        balloonStatsEntryImpl.resetOriginalValues();

        return balloonStatsEntryImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        firstContestId = objectInput.readLong();
        secondContestId = objectInput.readLong();
        choosenContest = objectInput.readInt();
        cookie = objectInput.readUTF();
        ip = objectInput.readUTF();
        extraData = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);
        objectOutput.writeLong(firstContestId);
        objectOutput.writeLong(secondContestId);
        objectOutput.writeInt(choosenContest);

        if (cookie == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(cookie);
        }

        if (ip == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(ip);
        }

        if (extraData == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(extraData);
        }
    }
}

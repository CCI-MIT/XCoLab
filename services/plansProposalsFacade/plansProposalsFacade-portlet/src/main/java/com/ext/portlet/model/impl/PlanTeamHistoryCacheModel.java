package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanTeamHistory;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PlanTeamHistory in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanTeamHistory
 * @generated
 */
public class PlanTeamHistoryCacheModel implements CacheModel<PlanTeamHistory>,
    Externalizable {
    public long id;
    public long planId;
    public long userId;
    public String action;
    public String payload;
    public long created;
    public long updateAuthorId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{id=");
        sb.append(id);
        sb.append(", planId=");
        sb.append(planId);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", action=");
        sb.append(action);
        sb.append(", payload=");
        sb.append(payload);
        sb.append(", created=");
        sb.append(created);
        sb.append(", updateAuthorId=");
        sb.append(updateAuthorId);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public PlanTeamHistory toEntityModel() {
        PlanTeamHistoryImpl planTeamHistoryImpl = new PlanTeamHistoryImpl();

        planTeamHistoryImpl.setId(id);
        planTeamHistoryImpl.setPlanId(planId);
        planTeamHistoryImpl.setUserId(userId);

        if (action == null) {
            planTeamHistoryImpl.setAction(StringPool.BLANK);
        } else {
            planTeamHistoryImpl.setAction(action);
        }

        if (payload == null) {
            planTeamHistoryImpl.setPayload(StringPool.BLANK);
        } else {
            planTeamHistoryImpl.setPayload(payload);
        }

        if (created == Long.MIN_VALUE) {
            planTeamHistoryImpl.setCreated(null);
        } else {
            planTeamHistoryImpl.setCreated(new Date(created));
        }

        planTeamHistoryImpl.setUpdateAuthorId(updateAuthorId);

        planTeamHistoryImpl.resetOriginalValues();

        return planTeamHistoryImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        planId = objectInput.readLong();
        userId = objectInput.readLong();
        action = objectInput.readUTF();
        payload = objectInput.readUTF();
        created = objectInput.readLong();
        updateAuthorId = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);
        objectOutput.writeLong(planId);
        objectOutput.writeLong(userId);

        if (action == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(action);
        }

        if (payload == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(payload);
        }

        objectOutput.writeLong(created);
        objectOutput.writeLong(updateAuthorId);
    }
}

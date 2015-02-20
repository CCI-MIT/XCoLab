package com.ext.portlet.model.impl;

import com.ext.portlet.model.ContestSchedule;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ContestSchedule in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ContestSchedule
 * @generated
 */
public class ContestScheduleCacheModel implements CacheModel<ContestSchedule>,
    Externalizable {
    public long id;
    public String name;
    public String description;
    public String status;
    public Long baseScheduleId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{id=");
        sb.append(id);
        sb.append(", name=");
        sb.append(name);
        sb.append(", description=");
        sb.append(description);
        sb.append(", status=");
        sb.append(status);
        sb.append(", baseScheduleId=");
        sb.append(baseScheduleId);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ContestSchedule toEntityModel() {
        ContestScheduleImpl contestScheduleImpl = new ContestScheduleImpl();

        contestScheduleImpl.setId(id);

        if (name == null) {
            contestScheduleImpl.setName(StringPool.BLANK);
        } else {
            contestScheduleImpl.setName(name);
        }

        if (description == null) {
            contestScheduleImpl.setDescription(StringPool.BLANK);
        } else {
            contestScheduleImpl.setDescription(description);
        }

        if (status == null) {
            contestScheduleImpl.setStatus(StringPool.BLANK);
        } else {
            contestScheduleImpl.setStatus(status);
        }

        contestScheduleImpl.setBaseScheduleId(baseScheduleId);

        contestScheduleImpl.resetOriginalValues();

        return contestScheduleImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        name = objectInput.readUTF();
        description = objectInput.readUTF();
        status = objectInput.readUTF();
        baseScheduleId = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);

        if (name == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(name);
        }

        if (description == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(description);
        }

        if (status == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(status);
        }

        objectOutput.writeLong(baseScheduleId);
    }
}

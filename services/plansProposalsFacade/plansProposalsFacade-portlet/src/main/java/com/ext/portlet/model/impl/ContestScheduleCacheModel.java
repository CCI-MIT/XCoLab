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
    public long ContestSchedulePK;
    public long ContestPK;
    public String name;
    public String description;
    public String status;
    public boolean invisible;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{ContestSchedulePK=");
        sb.append(ContestSchedulePK);
        sb.append(", ContestPK=");
        sb.append(ContestPK);
        sb.append(", name=");
        sb.append(name);
        sb.append(", description=");
        sb.append(description);
        sb.append(", status=");
        sb.append(status);
        sb.append(", invisible=");
        sb.append(invisible);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ContestSchedule toEntityModel() {
        ContestScheduleImpl contestScheduleImpl = new ContestScheduleImpl();

        contestScheduleImpl.setContestSchedulePK(ContestSchedulePK);
        contestScheduleImpl.setContestPK(ContestPK);

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

        contestScheduleImpl.setInvisible(invisible);

        contestScheduleImpl.resetOriginalValues();

        return contestScheduleImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        ContestSchedulePK = objectInput.readLong();
        ContestPK = objectInput.readLong();
        name = objectInput.readUTF();
        description = objectInput.readUTF();
        status = objectInput.readUTF();
        invisible = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(ContestSchedulePK);
        objectOutput.writeLong(ContestPK);

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

        objectOutput.writeBoolean(invisible);
    }
}

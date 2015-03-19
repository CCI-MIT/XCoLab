package com.ext.portlet.model.impl;

import com.ext.portlet.model.ContestPhaseType;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ContestPhaseType in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseType
 * @generated
 */
public class ContestPhaseTypeCacheModel implements CacheModel<ContestPhaseType>,
    Externalizable {
    public long id;
    public String name;
    public String description;
    public String status;
    public boolean fellowScreeningActiveDefault;
    public String contestPhaseAutopromoteDefault;
    public boolean invisible;
    public int pointsAccessible;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(17);

        sb.append("{id=");
        sb.append(id);
        sb.append(", name=");
        sb.append(name);
        sb.append(", description=");
        sb.append(description);
        sb.append(", status=");
        sb.append(status);
        sb.append(", fellowScreeningActiveDefault=");
        sb.append(fellowScreeningActiveDefault);
        sb.append(", contestPhaseAutopromoteDefault=");
        sb.append(contestPhaseAutopromoteDefault);
        sb.append(", invisible=");
        sb.append(invisible);
        sb.append(", pointsAccessible=");
        sb.append(pointsAccessible);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ContestPhaseType toEntityModel() {
        ContestPhaseTypeImpl contestPhaseTypeImpl = new ContestPhaseTypeImpl();

        contestPhaseTypeImpl.setId(id);

        if (name == null) {
            contestPhaseTypeImpl.setName(StringPool.BLANK);
        } else {
            contestPhaseTypeImpl.setName(name);
        }

        if (description == null) {
            contestPhaseTypeImpl.setDescription(StringPool.BLANK);
        } else {
            contestPhaseTypeImpl.setDescription(description);
        }

        if (status == null) {
            contestPhaseTypeImpl.setStatus(StringPool.BLANK);
        } else {
            contestPhaseTypeImpl.setStatus(status);
        }

        contestPhaseTypeImpl.setFellowScreeningActiveDefault(fellowScreeningActiveDefault);

        if (contestPhaseAutopromoteDefault == null) {
            contestPhaseTypeImpl.setContestPhaseAutopromoteDefault(StringPool.BLANK);
        } else {
            contestPhaseTypeImpl.setContestPhaseAutopromoteDefault(contestPhaseAutopromoteDefault);
        }

        contestPhaseTypeImpl.setInvisible(invisible);
        contestPhaseTypeImpl.setPointsAccessible(pointsAccessible);

        contestPhaseTypeImpl.resetOriginalValues();

        return contestPhaseTypeImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        name = objectInput.readUTF();
        description = objectInput.readUTF();
        status = objectInput.readUTF();
        fellowScreeningActiveDefault = objectInput.readBoolean();
        contestPhaseAutopromoteDefault = objectInput.readUTF();
        invisible = objectInput.readBoolean();
        pointsAccessible = objectInput.readInt();
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

        objectOutput.writeBoolean(fellowScreeningActiveDefault);

        if (contestPhaseAutopromoteDefault == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(contestPhaseAutopromoteDefault);
        }

        objectOutput.writeBoolean(invisible);
        objectOutput.writeInt(pointsAccessible);
    }
}

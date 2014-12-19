package com.ext.portlet.model.impl;

import com.ext.portlet.model.ContestPhaseColumn;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ContestPhaseColumn in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseColumn
 * @generated
 */
public class ContestPhaseColumnCacheModel implements CacheModel<ContestPhaseColumn>,
    Externalizable {
    public long id;
    public long ContestPhasePK;
    public String columnName;
    public int columnWeight;
    public boolean defaultSort;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{id=");
        sb.append(id);
        sb.append(", ContestPhasePK=");
        sb.append(ContestPhasePK);
        sb.append(", columnName=");
        sb.append(columnName);
        sb.append(", columnWeight=");
        sb.append(columnWeight);
        sb.append(", defaultSort=");
        sb.append(defaultSort);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ContestPhaseColumn toEntityModel() {
        ContestPhaseColumnImpl contestPhaseColumnImpl = new ContestPhaseColumnImpl();

        contestPhaseColumnImpl.setId(id);
        contestPhaseColumnImpl.setContestPhasePK(ContestPhasePK);

        if (columnName == null) {
            contestPhaseColumnImpl.setColumnName(StringPool.BLANK);
        } else {
            contestPhaseColumnImpl.setColumnName(columnName);
        }

        contestPhaseColumnImpl.setColumnWeight(columnWeight);
        contestPhaseColumnImpl.setDefaultSort(defaultSort);

        contestPhaseColumnImpl.resetOriginalValues();

        return contestPhaseColumnImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        ContestPhasePK = objectInput.readLong();
        columnName = objectInput.readUTF();
        columnWeight = objectInput.readInt();
        defaultSort = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);
        objectOutput.writeLong(ContestPhasePK);

        if (columnName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(columnName);
        }

        objectOutput.writeInt(columnWeight);
        objectOutput.writeBoolean(defaultSort);
    }
}

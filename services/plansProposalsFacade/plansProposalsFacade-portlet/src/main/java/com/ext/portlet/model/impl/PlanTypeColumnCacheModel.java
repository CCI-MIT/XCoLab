package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanTypeColumn;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing PlanTypeColumn in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanTypeColumn
 * @generated
 */
public class PlanTypeColumnCacheModel implements CacheModel<PlanTypeColumn>,
    Externalizable {
    public long planTypeColumnId;
    public long planTypeId;
    public int weight;
    public String columnName;
    public boolean visibleByDefault;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{planTypeColumnId=");
        sb.append(planTypeColumnId);
        sb.append(", planTypeId=");
        sb.append(planTypeId);
        sb.append(", weight=");
        sb.append(weight);
        sb.append(", columnName=");
        sb.append(columnName);
        sb.append(", visibleByDefault=");
        sb.append(visibleByDefault);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public PlanTypeColumn toEntityModel() {
        PlanTypeColumnImpl planTypeColumnImpl = new PlanTypeColumnImpl();

        planTypeColumnImpl.setPlanTypeColumnId(planTypeColumnId);
        planTypeColumnImpl.setPlanTypeId(planTypeId);
        planTypeColumnImpl.setWeight(weight);

        if (columnName == null) {
            planTypeColumnImpl.setColumnName(StringPool.BLANK);
        } else {
            planTypeColumnImpl.setColumnName(columnName);
        }

        planTypeColumnImpl.setVisibleByDefault(visibleByDefault);

        planTypeColumnImpl.resetOriginalValues();

        return planTypeColumnImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        planTypeColumnId = objectInput.readLong();
        planTypeId = objectInput.readLong();
        weight = objectInput.readInt();
        columnName = objectInput.readUTF();
        visibleByDefault = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(planTypeColumnId);
        objectOutput.writeLong(planTypeId);
        objectOutput.writeInt(weight);

        if (columnName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(columnName);
        }

        objectOutput.writeBoolean(visibleByDefault);
    }
}

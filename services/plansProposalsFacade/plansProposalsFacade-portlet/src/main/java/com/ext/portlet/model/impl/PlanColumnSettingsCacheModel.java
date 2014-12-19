package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanColumnSettings;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing PlanColumnSettings in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanColumnSettings
 * @generated
 */
public class PlanColumnSettingsCacheModel implements CacheModel<PlanColumnSettings>,
    Externalizable {
    public long planColumnSettingsId;
    public String columnName;
    public long planUserSettingsId;
    public boolean visible;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{planColumnSettingsId=");
        sb.append(planColumnSettingsId);
        sb.append(", columnName=");
        sb.append(columnName);
        sb.append(", planUserSettingsId=");
        sb.append(planUserSettingsId);
        sb.append(", visible=");
        sb.append(visible);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public PlanColumnSettings toEntityModel() {
        PlanColumnSettingsImpl planColumnSettingsImpl = new PlanColumnSettingsImpl();

        planColumnSettingsImpl.setPlanColumnSettingsId(planColumnSettingsId);

        if (columnName == null) {
            planColumnSettingsImpl.setColumnName(StringPool.BLANK);
        } else {
            planColumnSettingsImpl.setColumnName(columnName);
        }

        planColumnSettingsImpl.setPlanUserSettingsId(planUserSettingsId);
        planColumnSettingsImpl.setVisible(visible);

        planColumnSettingsImpl.resetOriginalValues();

        return planColumnSettingsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        planColumnSettingsId = objectInput.readLong();
        columnName = objectInput.readUTF();
        planUserSettingsId = objectInput.readLong();
        visible = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(planColumnSettingsId);

        if (columnName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(columnName);
        }

        objectOutput.writeLong(planUserSettingsId);
        objectOutput.writeBoolean(visible);
    }
}

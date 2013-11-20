package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlansUserSettings;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing PlansUserSettings in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlansUserSettings
 * @generated
 */
public class PlansUserSettingsCacheModel implements CacheModel<PlansUserSettings>,
    Externalizable {
    public long planUserSettingsId;
    public long userId;
    public long planTypeId;
    public String sortColumn;
    public String sortDirection;
    public boolean filterEnabled;
    public boolean filterPositionsAll;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{planUserSettingsId=");
        sb.append(planUserSettingsId);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", planTypeId=");
        sb.append(planTypeId);
        sb.append(", sortColumn=");
        sb.append(sortColumn);
        sb.append(", sortDirection=");
        sb.append(sortDirection);
        sb.append(", filterEnabled=");
        sb.append(filterEnabled);
        sb.append(", filterPositionsAll=");
        sb.append(filterPositionsAll);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public PlansUserSettings toEntityModel() {
        PlansUserSettingsImpl plansUserSettingsImpl = new PlansUserSettingsImpl();

        plansUserSettingsImpl.setPlanUserSettingsId(planUserSettingsId);
        plansUserSettingsImpl.setUserId(userId);
        plansUserSettingsImpl.setPlanTypeId(planTypeId);

        if (sortColumn == null) {
            plansUserSettingsImpl.setSortColumn(StringPool.BLANK);
        } else {
            plansUserSettingsImpl.setSortColumn(sortColumn);
        }

        if (sortDirection == null) {
            plansUserSettingsImpl.setSortDirection(StringPool.BLANK);
        } else {
            plansUserSettingsImpl.setSortDirection(sortDirection);
        }

        plansUserSettingsImpl.setFilterEnabled(filterEnabled);
        plansUserSettingsImpl.setFilterPositionsAll(filterPositionsAll);

        plansUserSettingsImpl.resetOriginalValues();

        return plansUserSettingsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        planUserSettingsId = objectInput.readLong();
        userId = objectInput.readLong();
        planTypeId = objectInput.readLong();
        sortColumn = objectInput.readUTF();
        sortDirection = objectInput.readUTF();
        filterEnabled = objectInput.readBoolean();
        filterPositionsAll = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(planUserSettingsId);
        objectOutput.writeLong(userId);
        objectOutput.writeLong(planTypeId);

        if (sortColumn == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(sortColumn);
        }

        if (sortDirection == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(sortDirection);
        }

        objectOutput.writeBoolean(filterEnabled);
        objectOutput.writeBoolean(filterPositionsAll);
    }
}

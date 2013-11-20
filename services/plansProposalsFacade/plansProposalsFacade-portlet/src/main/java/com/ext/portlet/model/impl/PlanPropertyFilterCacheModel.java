package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanPropertyFilter;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing PlanPropertyFilter in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanPropertyFilter
 * @generated
 */
public class PlanPropertyFilterCacheModel implements CacheModel<PlanPropertyFilter>,
    Externalizable {
    public long planPropertyFilterId;
    public String propertyName;
    public long planUserSettingsId;
    public String value;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{planPropertyFilterId=");
        sb.append(planPropertyFilterId);
        sb.append(", propertyName=");
        sb.append(propertyName);
        sb.append(", planUserSettingsId=");
        sb.append(planUserSettingsId);
        sb.append(", value=");
        sb.append(value);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public PlanPropertyFilter toEntityModel() {
        PlanPropertyFilterImpl planPropertyFilterImpl = new PlanPropertyFilterImpl();

        planPropertyFilterImpl.setPlanPropertyFilterId(planPropertyFilterId);

        if (propertyName == null) {
            planPropertyFilterImpl.setPropertyName(StringPool.BLANK);
        } else {
            planPropertyFilterImpl.setPropertyName(propertyName);
        }

        planPropertyFilterImpl.setPlanUserSettingsId(planUserSettingsId);

        if (value == null) {
            planPropertyFilterImpl.setValue(StringPool.BLANK);
        } else {
            planPropertyFilterImpl.setValue(value);
        }

        planPropertyFilterImpl.resetOriginalValues();

        return planPropertyFilterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        planPropertyFilterId = objectInput.readLong();
        propertyName = objectInput.readUTF();
        planUserSettingsId = objectInput.readLong();
        value = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(planPropertyFilterId);

        if (propertyName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(propertyName);
        }

        objectOutput.writeLong(planUserSettingsId);

        if (value == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(value);
        }
    }
}

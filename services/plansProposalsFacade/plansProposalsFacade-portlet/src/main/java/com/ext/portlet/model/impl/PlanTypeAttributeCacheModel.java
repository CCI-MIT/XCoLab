package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanTypeAttribute;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing PlanTypeAttribute in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanTypeAttribute
 * @generated
 */
public class PlanTypeAttributeCacheModel implements CacheModel<PlanTypeAttribute>,
    Externalizable {
    public long planTypeAttributeId;
    public long planTypeId;
    public String attributeName;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{planTypeAttributeId=");
        sb.append(planTypeAttributeId);
        sb.append(", planTypeId=");
        sb.append(planTypeId);
        sb.append(", attributeName=");
        sb.append(attributeName);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public PlanTypeAttribute toEntityModel() {
        PlanTypeAttributeImpl planTypeAttributeImpl = new PlanTypeAttributeImpl();

        planTypeAttributeImpl.setPlanTypeAttributeId(planTypeAttributeId);
        planTypeAttributeImpl.setPlanTypeId(planTypeId);

        if (attributeName == null) {
            planTypeAttributeImpl.setAttributeName(StringPool.BLANK);
        } else {
            planTypeAttributeImpl.setAttributeName(attributeName);
        }

        planTypeAttributeImpl.resetOriginalValues();

        return planTypeAttributeImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        planTypeAttributeId = objectInput.readLong();
        planTypeId = objectInput.readLong();
        attributeName = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(planTypeAttributeId);
        objectOutput.writeLong(planTypeId);

        if (attributeName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(attributeName);
        }
    }
}

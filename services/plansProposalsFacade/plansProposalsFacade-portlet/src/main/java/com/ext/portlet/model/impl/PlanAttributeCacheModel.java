package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanAttribute;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing PlanAttribute in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanAttribute
 * @generated
 */
public class PlanAttributeCacheModel implements CacheModel<PlanAttribute>,
    Externalizable {
    public long attributeId;
    public long planId;
    public String attributeName;
    public String attributeValue;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{attributeId=");
        sb.append(attributeId);
        sb.append(", planId=");
        sb.append(planId);
        sb.append(", attributeName=");
        sb.append(attributeName);
        sb.append(", attributeValue=");
        sb.append(attributeValue);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public PlanAttribute toEntityModel() {
        PlanAttributeImpl planAttributeImpl = new PlanAttributeImpl();

        planAttributeImpl.setAttributeId(attributeId);
        planAttributeImpl.setPlanId(planId);

        if (attributeName == null) {
            planAttributeImpl.setAttributeName(StringPool.BLANK);
        } else {
            planAttributeImpl.setAttributeName(attributeName);
        }

        if (attributeValue == null) {
            planAttributeImpl.setAttributeValue(StringPool.BLANK);
        } else {
            planAttributeImpl.setAttributeValue(attributeValue);
        }

        planAttributeImpl.resetOriginalValues();

        return planAttributeImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        attributeId = objectInput.readLong();
        planId = objectInput.readLong();
        attributeName = objectInput.readUTF();
        attributeValue = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(attributeId);
        objectOutput.writeLong(planId);

        if (attributeName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(attributeName);
        }

        if (attributeValue == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(attributeValue);
        }
    }
}

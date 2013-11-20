package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanItemGroup;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing PlanItemGroup in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanItemGroup
 * @generated
 */
public class PlanItemGroupCacheModel implements CacheModel<PlanItemGroup>,
    Externalizable {
    public long planId;
    public long groupId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(5);

        sb.append("{planId=");
        sb.append(planId);
        sb.append(", groupId=");
        sb.append(groupId);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public PlanItemGroup toEntityModel() {
        PlanItemGroupImpl planItemGroupImpl = new PlanItemGroupImpl();

        planItemGroupImpl.setPlanId(planId);
        planItemGroupImpl.setGroupId(groupId);

        planItemGroupImpl.resetOriginalValues();

        return planItemGroupImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        planId = objectInput.readLong();
        groupId = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(planId);
        objectOutput.writeLong(groupId);
    }
}

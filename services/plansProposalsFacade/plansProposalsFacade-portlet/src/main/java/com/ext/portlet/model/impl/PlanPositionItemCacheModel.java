package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanPositionItem;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing PlanPositionItem in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanPositionItem
 * @generated
 */
public class PlanPositionItemCacheModel implements CacheModel<PlanPositionItem>,
    Externalizable {
    public long planPositionsId;
    public long positionId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(5);

        sb.append("{planPositionsId=");
        sb.append(planPositionsId);
        sb.append(", positionId=");
        sb.append(positionId);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public PlanPositionItem toEntityModel() {
        PlanPositionItemImpl planPositionItemImpl = new PlanPositionItemImpl();

        planPositionItemImpl.setPlanPositionsId(planPositionsId);
        planPositionItemImpl.setPositionId(positionId);

        planPositionItemImpl.resetOriginalValues();

        return planPositionItemImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        planPositionsId = objectInput.readLong();
        positionId = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(planPositionsId);
        objectOutput.writeLong(positionId);
    }
}

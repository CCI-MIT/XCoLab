package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanItemGroup;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing PlanItemGroup in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanItemGroup
 * @generated
 */
public class PlanItemGroupCacheModel implements CacheModel<PlanItemGroup>,
    Serializable {
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

    public PlanItemGroup toEntityModel() {
        PlanItemGroupImpl planItemGroupImpl = new PlanItemGroupImpl();

        planItemGroupImpl.setPlanId(planId);
        planItemGroupImpl.setGroupId(groupId);

        planItemGroupImpl.resetOriginalValues();

        return planItemGroupImpl;
    }
}

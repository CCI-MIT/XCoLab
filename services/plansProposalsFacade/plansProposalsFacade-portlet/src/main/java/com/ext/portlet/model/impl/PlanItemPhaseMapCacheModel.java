package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanItemPhaseMap;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing PlanItemPhaseMap in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanItemPhaseMap
 * @generated
 */
public class PlanItemPhaseMapCacheModel implements CacheModel<PlanItemPhaseMap>,
    Serializable {
    public long id;
    public long planId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(5);

        sb.append("{id=");
        sb.append(id);
        sb.append(", planId=");
        sb.append(planId);
        sb.append("}");

        return sb.toString();
    }

    public PlanItemPhaseMap toEntityModel() {
        PlanItemPhaseMapImpl planItemPhaseMapImpl = new PlanItemPhaseMapImpl();

        planItemPhaseMapImpl.setId(id);
        planItemPhaseMapImpl.setPlanId(planId);

        planItemPhaseMapImpl.resetOriginalValues();

        return planItemPhaseMapImpl;
    }
}

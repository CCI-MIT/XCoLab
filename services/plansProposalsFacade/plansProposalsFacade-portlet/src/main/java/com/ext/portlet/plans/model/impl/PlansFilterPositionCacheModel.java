package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlansFilterPosition;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing PlansFilterPosition in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlansFilterPosition
 * @generated
 */
public class PlansFilterPositionCacheModel implements CacheModel<PlansFilterPosition>,
    Serializable {
    public long userId;
    public long planTypeId;
    public long positionId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{userId=");
        sb.append(userId);
        sb.append(", planTypeId=");
        sb.append(planTypeId);
        sb.append(", positionId=");
        sb.append(positionId);
        sb.append("}");

        return sb.toString();
    }

    public PlansFilterPosition toEntityModel() {
        PlansFilterPositionImpl plansFilterPositionImpl = new PlansFilterPositionImpl();

        plansFilterPositionImpl.setUserId(userId);
        plansFilterPositionImpl.setPlanTypeId(planTypeId);
        plansFilterPositionImpl.setPositionId(positionId);

        plansFilterPositionImpl.resetOriginalValues();

        return plansFilterPositionImpl;
    }
}

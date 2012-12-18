package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanPositionItem;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing PlanPositionItem in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanPositionItem
 * @generated
 */
public class PlanPositionItemCacheModel implements CacheModel<PlanPositionItem>,
    Serializable {
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

    public PlanPositionItem toEntityModel() {
        PlanPositionItemImpl planPositionItemImpl = new PlanPositionItemImpl();

        planPositionItemImpl.setPlanPositionsId(planPositionsId);
        planPositionItemImpl.setPositionId(positionId);

        planPositionItemImpl.resetOriginalValues();

        return planPositionItemImpl;
    }
}

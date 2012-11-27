package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanTypeAttribute;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing PlanTypeAttribute in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanTypeAttribute
 * @generated
 */
public class PlanTypeAttributeCacheModel implements CacheModel<PlanTypeAttribute>,
    Serializable {
    public Long planTypeAttributeId;
    public Long planTypeId;
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
}

package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanAttribute;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing PlanAttribute in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanAttribute
 * @generated
 */
public class PlanAttributeCacheModel implements CacheModel<PlanAttribute>,
    Serializable {
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
}

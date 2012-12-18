package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanAttributeFilter;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing PlanAttributeFilter in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanAttributeFilter
 * @generated
 */
public class PlanAttributeFilterCacheModel implements CacheModel<PlanAttributeFilter>,
    Serializable {
    public long planAttributeFilterId;
    public String attributeName;
    public long planUserSettingsId;
    public Double max;
    public Double min;
    public String stringVal;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{planAttributeFilterId=");
        sb.append(planAttributeFilterId);
        sb.append(", attributeName=");
        sb.append(attributeName);
        sb.append(", planUserSettingsId=");
        sb.append(planUserSettingsId);
        sb.append(", max=");
        sb.append(max);
        sb.append(", min=");
        sb.append(min);
        sb.append(", stringVal=");
        sb.append(stringVal);
        sb.append("}");

        return sb.toString();
    }

    public PlanAttributeFilter toEntityModel() {
        PlanAttributeFilterImpl planAttributeFilterImpl = new PlanAttributeFilterImpl();

        planAttributeFilterImpl.setPlanAttributeFilterId(planAttributeFilterId);

        if (attributeName == null) {
            planAttributeFilterImpl.setAttributeName(StringPool.BLANK);
        } else {
            planAttributeFilterImpl.setAttributeName(attributeName);
        }

        planAttributeFilterImpl.setPlanUserSettingsId(planUserSettingsId);
        planAttributeFilterImpl.setMax(max);
        planAttributeFilterImpl.setMin(min);

        if (stringVal == null) {
            planAttributeFilterImpl.setStringVal(StringPool.BLANK);
        } else {
            planAttributeFilterImpl.setStringVal(stringVal);
        }

        planAttributeFilterImpl.resetOriginalValues();

        return planAttributeFilterImpl;
    }
}

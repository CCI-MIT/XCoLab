package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanPropertyFilter;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing PlanPropertyFilter in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanPropertyFilter
 * @generated
 */
public class PlanPropertyFilterCacheModel implements CacheModel<PlanPropertyFilter>,
    Serializable {
    public long planPropertyFilterId;
    public String propertyName;
    public long planUserSettingsId;
    public String value;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{planPropertyFilterId=");
        sb.append(planPropertyFilterId);
        sb.append(", propertyName=");
        sb.append(propertyName);
        sb.append(", planUserSettingsId=");
        sb.append(planUserSettingsId);
        sb.append(", value=");
        sb.append(value);
        sb.append("}");

        return sb.toString();
    }

    public PlanPropertyFilter toEntityModel() {
        PlanPropertyFilterImpl planPropertyFilterImpl = new PlanPropertyFilterImpl();

        planPropertyFilterImpl.setPlanPropertyFilterId(planPropertyFilterId);

        if (propertyName == null) {
            planPropertyFilterImpl.setPropertyName(StringPool.BLANK);
        } else {
            planPropertyFilterImpl.setPropertyName(propertyName);
        }

        planPropertyFilterImpl.setPlanUserSettingsId(planUserSettingsId);

        if (value == null) {
            planPropertyFilterImpl.setValue(StringPool.BLANK);
        } else {
            planPropertyFilterImpl.setValue(value);
        }

        planPropertyFilterImpl.resetOriginalValues();

        return planPropertyFilterImpl;
    }
}

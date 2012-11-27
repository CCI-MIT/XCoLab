package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlansUserSettings;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing PlansUserSettings in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlansUserSettings
 * @generated
 */
public class PlansUserSettingsCacheModel implements CacheModel<PlansUserSettings>,
    Serializable {
    public Long planUserSettingsId;
    public Long userId;
    public Long planTypeId;
    public String sortColumn;
    public String sortDirection;
    public Boolean filterEnabled;
    public Boolean filterPositionsAll;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{planUserSettingsId=");
        sb.append(planUserSettingsId);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", planTypeId=");
        sb.append(planTypeId);
        sb.append(", sortColumn=");
        sb.append(sortColumn);
        sb.append(", sortDirection=");
        sb.append(sortDirection);
        sb.append(", filterEnabled=");
        sb.append(filterEnabled);
        sb.append(", filterPositionsAll=");
        sb.append(filterPositionsAll);
        sb.append("}");

        return sb.toString();
    }

    public PlansUserSettings toEntityModel() {
        PlansUserSettingsImpl plansUserSettingsImpl = new PlansUserSettingsImpl();

        plansUserSettingsImpl.setPlanUserSettingsId(planUserSettingsId);
        plansUserSettingsImpl.setUserId(userId);
        plansUserSettingsImpl.setPlanTypeId(planTypeId);

        if (sortColumn == null) {
            plansUserSettingsImpl.setSortColumn(StringPool.BLANK);
        } else {
            plansUserSettingsImpl.setSortColumn(sortColumn);
        }

        if (sortDirection == null) {
            plansUserSettingsImpl.setSortDirection(StringPool.BLANK);
        } else {
            plansUserSettingsImpl.setSortDirection(sortDirection);
        }

        plansUserSettingsImpl.setFilterEnabled(filterEnabled);
        plansUserSettingsImpl.setFilterPositionsAll(filterPositionsAll);

        plansUserSettingsImpl.resetOriginalValues();

        return plansUserSettingsImpl;
    }
}

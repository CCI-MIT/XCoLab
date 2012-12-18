package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanColumnSettings;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing PlanColumnSettings in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanColumnSettings
 * @generated
 */
public class PlanColumnSettingsCacheModel implements CacheModel<PlanColumnSettings>,
    Serializable {
    public long planColumnSettingsId;
    public String columnName;
    public long planUserSettingsId;
    public boolean visible;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{planColumnSettingsId=");
        sb.append(planColumnSettingsId);
        sb.append(", columnName=");
        sb.append(columnName);
        sb.append(", planUserSettingsId=");
        sb.append(planUserSettingsId);
        sb.append(", visible=");
        sb.append(visible);
        sb.append("}");

        return sb.toString();
    }

    public PlanColumnSettings toEntityModel() {
        PlanColumnSettingsImpl planColumnSettingsImpl = new PlanColumnSettingsImpl();

        planColumnSettingsImpl.setPlanColumnSettingsId(planColumnSettingsId);

        if (columnName == null) {
            planColumnSettingsImpl.setColumnName(StringPool.BLANK);
        } else {
            planColumnSettingsImpl.setColumnName(columnName);
        }

        planColumnSettingsImpl.setPlanUserSettingsId(planUserSettingsId);
        planColumnSettingsImpl.setVisible(visible);

        planColumnSettingsImpl.resetOriginalValues();

        return planColumnSettingsImpl;
    }
}

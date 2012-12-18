package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.model.PlanSectionPlanMap;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing PlanSectionPlanMap in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanSectionPlanMap
 * @generated
 */
public class PlanSectionPlanMapCacheModel implements CacheModel<PlanSectionPlanMap>,
    Serializable {
    public long sectionId;
    public long relatedPlanId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(5);

        sb.append("{sectionId=");
        sb.append(sectionId);
        sb.append(", relatedPlanId=");
        sb.append(relatedPlanId);
        sb.append("}");

        return sb.toString();
    }

    public PlanSectionPlanMap toEntityModel() {
        PlanSectionPlanMapImpl planSectionPlanMapImpl = new PlanSectionPlanMapImpl();

        planSectionPlanMapImpl.setSectionId(sectionId);
        planSectionPlanMapImpl.setRelatedPlanId(relatedPlanId);

        planSectionPlanMapImpl.resetOriginalValues();

        return planSectionPlanMapImpl;
    }
}

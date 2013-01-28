package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanRelated;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing PlanRelated in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanRelated
 * @generated
 */
public class PlanRelatedCacheModel implements CacheModel<PlanRelated>,
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

    public PlanRelated toEntityModel() {
        PlanRelatedImpl planRelatedImpl = new PlanRelatedImpl();

        planRelatedImpl.setSectionId(sectionId);
        planRelatedImpl.setRelatedPlanId(relatedPlanId);

        planRelatedImpl.resetOriginalValues();

        return planRelatedImpl;
    }
}

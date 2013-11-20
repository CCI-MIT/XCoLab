package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanSectionPlanMap;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing PlanSectionPlanMap in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanSectionPlanMap
 * @generated
 */
public class PlanSectionPlanMapCacheModel implements CacheModel<PlanSectionPlanMap>,
    Externalizable {
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

    @Override
    public PlanSectionPlanMap toEntityModel() {
        PlanSectionPlanMapImpl planSectionPlanMapImpl = new PlanSectionPlanMapImpl();

        planSectionPlanMapImpl.setSectionId(sectionId);
        planSectionPlanMapImpl.setRelatedPlanId(relatedPlanId);

        planSectionPlanMapImpl.resetOriginalValues();

        return planSectionPlanMapImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        sectionId = objectInput.readLong();
        relatedPlanId = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(sectionId);
        objectOutput.writeLong(relatedPlanId);
    }
}

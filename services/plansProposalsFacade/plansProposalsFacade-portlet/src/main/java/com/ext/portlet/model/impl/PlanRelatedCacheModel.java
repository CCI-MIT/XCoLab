package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanRelated;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing PlanRelated in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanRelated
 * @generated
 */
public class PlanRelatedCacheModel implements CacheModel<PlanRelated>,
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
    public PlanRelated toEntityModel() {
        PlanRelatedImpl planRelatedImpl = new PlanRelatedImpl();

        planRelatedImpl.setSectionId(sectionId);
        planRelatedImpl.setRelatedPlanId(relatedPlanId);

        planRelatedImpl.resetOriginalValues();

        return planRelatedImpl;
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

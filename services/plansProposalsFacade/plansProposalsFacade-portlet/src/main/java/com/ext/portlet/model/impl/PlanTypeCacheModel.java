package com.ext.portlet.model.impl;

import com.ext.portlet.model.PlanType;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing PlanType in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PlanType
 * @generated
 */
public class PlanTypeCacheModel implements CacheModel<PlanType>, Serializable {
    public long planTypeId;
    public String name;
    public String description;
    public long modelId;
    public String modelTypeName;
    public boolean published;
    public long publishedCounterpartId;
    public boolean isDefault;
    public long defaultModelId;
    public long defaultScenarioId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(21);

        sb.append("{planTypeId=");
        sb.append(planTypeId);
        sb.append(", name=");
        sb.append(name);
        sb.append(", description=");
        sb.append(description);
        sb.append(", modelId=");
        sb.append(modelId);
        sb.append(", modelTypeName=");
        sb.append(modelTypeName);
        sb.append(", published=");
        sb.append(published);
        sb.append(", publishedCounterpartId=");
        sb.append(publishedCounterpartId);
        sb.append(", isDefault=");
        sb.append(isDefault);
        sb.append(", defaultModelId=");
        sb.append(defaultModelId);
        sb.append(", defaultScenarioId=");
        sb.append(defaultScenarioId);
        sb.append("}");

        return sb.toString();
    }

    public PlanType toEntityModel() {
        PlanTypeImpl planTypeImpl = new PlanTypeImpl();

        planTypeImpl.setPlanTypeId(planTypeId);

        if (name == null) {
            planTypeImpl.setName(StringPool.BLANK);
        } else {
            planTypeImpl.setName(name);
        }

        if (description == null) {
            planTypeImpl.setDescription(StringPool.BLANK);
        } else {
            planTypeImpl.setDescription(description);
        }

        planTypeImpl.setModelId(modelId);

        if (modelTypeName == null) {
            planTypeImpl.setModelTypeName(StringPool.BLANK);
        } else {
            planTypeImpl.setModelTypeName(modelTypeName);
        }

        planTypeImpl.setPublished(published);
        planTypeImpl.setPublishedCounterpartId(publishedCounterpartId);
        planTypeImpl.setIsDefault(isDefault);
        planTypeImpl.setDefaultModelId(defaultModelId);
        planTypeImpl.setDefaultScenarioId(defaultScenarioId);

        planTypeImpl.resetOriginalValues();

        return planTypeImpl;
    }
}

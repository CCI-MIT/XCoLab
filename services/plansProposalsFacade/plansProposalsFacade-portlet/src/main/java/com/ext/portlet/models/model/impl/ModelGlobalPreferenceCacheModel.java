package com.ext.portlet.models.model.impl;

import com.ext.portlet.models.model.ModelGlobalPreference;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing ModelGlobalPreference in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ModelGlobalPreference
 * @generated
 */
public class ModelGlobalPreferenceCacheModel implements CacheModel<ModelGlobalPreference>,
    Serializable {
    public long modelGlobalPreferencePK;
    public long modelId;
    public boolean visible;
    public int weight;
    public long expertEvaluationPageId;
    public long modelCategoryId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{modelGlobalPreferencePK=");
        sb.append(modelGlobalPreferencePK);
        sb.append(", modelId=");
        sb.append(modelId);
        sb.append(", visible=");
        sb.append(visible);
        sb.append(", weight=");
        sb.append(weight);
        sb.append(", expertEvaluationPageId=");
        sb.append(expertEvaluationPageId);
        sb.append(", modelCategoryId=");
        sb.append(modelCategoryId);
        sb.append("}");

        return sb.toString();
    }

    public ModelGlobalPreference toEntityModel() {
        ModelGlobalPreferenceImpl modelGlobalPreferenceImpl = new ModelGlobalPreferenceImpl();

        modelGlobalPreferenceImpl.setModelGlobalPreferencePK(modelGlobalPreferencePK);
        modelGlobalPreferenceImpl.setModelId(modelId);
        modelGlobalPreferenceImpl.setVisible(visible);
        modelGlobalPreferenceImpl.setWeight(weight);
        modelGlobalPreferenceImpl.setExpertEvaluationPageId(expertEvaluationPageId);
        modelGlobalPreferenceImpl.setModelCategoryId(modelCategoryId);

        modelGlobalPreferenceImpl.resetOriginalValues();

        return modelGlobalPreferenceImpl;
    }
}

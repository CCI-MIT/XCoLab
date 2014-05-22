package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ModelGlobalPreference}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelGlobalPreference
 * @generated
 */
public class ModelGlobalPreferenceWrapper implements ModelGlobalPreference,
    ModelWrapper<ModelGlobalPreference> {
    private ModelGlobalPreference _modelGlobalPreference;

    public ModelGlobalPreferenceWrapper(
        ModelGlobalPreference modelGlobalPreference) {
        _modelGlobalPreference = modelGlobalPreference;
    }

    @Override
    public Class<?> getModelClass() {
        return ModelGlobalPreference.class;
    }

    @Override
    public String getModelClassName() {
        return ModelGlobalPreference.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("modelGlobalPreferencePK", getModelGlobalPreferencePK());
        attributes.put("modelId", getModelId());
        attributes.put("visible", getVisible());
        attributes.put("weight", getWeight());
        attributes.put("expertEvaluationPageId", getExpertEvaluationPageId());
        attributes.put("modelCategoryId", getModelCategoryId());
        attributes.put("usesCustomInputs", getUsesCustomInputs());
        attributes.put("customInputsDefinition", getCustomInputsDefinition());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long modelGlobalPreferencePK = (Long) attributes.get(
                "modelGlobalPreferencePK");

        if (modelGlobalPreferencePK != null) {
            setModelGlobalPreferencePK(modelGlobalPreferencePK);
        }

        Long modelId = (Long) attributes.get("modelId");

        if (modelId != null) {
            setModelId(modelId);
        }

        Boolean visible = (Boolean) attributes.get("visible");

        if (visible != null) {
            setVisible(visible);
        }

        Integer weight = (Integer) attributes.get("weight");

        if (weight != null) {
            setWeight(weight);
        }

        Long expertEvaluationPageId = (Long) attributes.get(
                "expertEvaluationPageId");

        if (expertEvaluationPageId != null) {
            setExpertEvaluationPageId(expertEvaluationPageId);
        }

        Long modelCategoryId = (Long) attributes.get("modelCategoryId");

        if (modelCategoryId != null) {
            setModelCategoryId(modelCategoryId);
        }

        Boolean usesCustomInputs = (Boolean) attributes.get("usesCustomInputs");

        if (usesCustomInputs != null) {
            setUsesCustomInputs(usesCustomInputs);
        }

        String customInputsDefinition = (String) attributes.get(
                "customInputsDefinition");

        if (customInputsDefinition != null) {
            setCustomInputsDefinition(customInputsDefinition);
        }
    }

    /**
    * Returns the primary key of this model global preference.
    *
    * @return the primary key of this model global preference
    */
    @Override
    public long getPrimaryKey() {
        return _modelGlobalPreference.getPrimaryKey();
    }

    /**
    * Sets the primary key of this model global preference.
    *
    * @param primaryKey the primary key of this model global preference
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _modelGlobalPreference.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the model global preference p k of this model global preference.
    *
    * @return the model global preference p k of this model global preference
    */
    @Override
    public long getModelGlobalPreferencePK() {
        return _modelGlobalPreference.getModelGlobalPreferencePK();
    }

    /**
    * Sets the model global preference p k of this model global preference.
    *
    * @param modelGlobalPreferencePK the model global preference p k of this model global preference
    */
    @Override
    public void setModelGlobalPreferencePK(long modelGlobalPreferencePK) {
        _modelGlobalPreference.setModelGlobalPreferencePK(modelGlobalPreferencePK);
    }

    /**
    * Returns the model ID of this model global preference.
    *
    * @return the model ID of this model global preference
    */
    @Override
    public long getModelId() {
        return _modelGlobalPreference.getModelId();
    }

    /**
    * Sets the model ID of this model global preference.
    *
    * @param modelId the model ID of this model global preference
    */
    @Override
    public void setModelId(long modelId) {
        _modelGlobalPreference.setModelId(modelId);
    }

    /**
    * Returns the visible of this model global preference.
    *
    * @return the visible of this model global preference
    */
    @Override
    public boolean getVisible() {
        return _modelGlobalPreference.getVisible();
    }

    /**
    * Returns <code>true</code> if this model global preference is visible.
    *
    * @return <code>true</code> if this model global preference is visible; <code>false</code> otherwise
    */
    @Override
    public boolean isVisible() {
        return _modelGlobalPreference.isVisible();
    }

    /**
    * Sets whether this model global preference is visible.
    *
    * @param visible the visible of this model global preference
    */
    @Override
    public void setVisible(boolean visible) {
        _modelGlobalPreference.setVisible(visible);
    }

    /**
    * Returns the weight of this model global preference.
    *
    * @return the weight of this model global preference
    */
    @Override
    public int getWeight() {
        return _modelGlobalPreference.getWeight();
    }

    /**
    * Sets the weight of this model global preference.
    *
    * @param weight the weight of this model global preference
    */
    @Override
    public void setWeight(int weight) {
        _modelGlobalPreference.setWeight(weight);
    }

    /**
    * Returns the expert evaluation page ID of this model global preference.
    *
    * @return the expert evaluation page ID of this model global preference
    */
    @Override
    public long getExpertEvaluationPageId() {
        return _modelGlobalPreference.getExpertEvaluationPageId();
    }

    /**
    * Sets the expert evaluation page ID of this model global preference.
    *
    * @param expertEvaluationPageId the expert evaluation page ID of this model global preference
    */
    @Override
    public void setExpertEvaluationPageId(long expertEvaluationPageId) {
        _modelGlobalPreference.setExpertEvaluationPageId(expertEvaluationPageId);
    }

    /**
    * Returns the model category ID of this model global preference.
    *
    * @return the model category ID of this model global preference
    */
    @Override
    public long getModelCategoryId() {
        return _modelGlobalPreference.getModelCategoryId();
    }

    /**
    * Sets the model category ID of this model global preference.
    *
    * @param modelCategoryId the model category ID of this model global preference
    */
    @Override
    public void setModelCategoryId(long modelCategoryId) {
        _modelGlobalPreference.setModelCategoryId(modelCategoryId);
    }

    /**
    * Returns the uses custom inputs of this model global preference.
    *
    * @return the uses custom inputs of this model global preference
    */
    @Override
    public boolean getUsesCustomInputs() {
        return _modelGlobalPreference.getUsesCustomInputs();
    }

    /**
    * Returns <code>true</code> if this model global preference is uses custom inputs.
    *
    * @return <code>true</code> if this model global preference is uses custom inputs; <code>false</code> otherwise
    */
    @Override
    public boolean isUsesCustomInputs() {
        return _modelGlobalPreference.isUsesCustomInputs();
    }

    /**
    * Sets whether this model global preference is uses custom inputs.
    *
    * @param usesCustomInputs the uses custom inputs of this model global preference
    */
    @Override
    public void setUsesCustomInputs(boolean usesCustomInputs) {
        _modelGlobalPreference.setUsesCustomInputs(usesCustomInputs);
    }

    /**
    * Returns the custom inputs definition of this model global preference.
    *
    * @return the custom inputs definition of this model global preference
    */
    @Override
    public java.lang.String getCustomInputsDefinition() {
        return _modelGlobalPreference.getCustomInputsDefinition();
    }

    /**
    * Sets the custom inputs definition of this model global preference.
    *
    * @param customInputsDefinition the custom inputs definition of this model global preference
    */
    @Override
    public void setCustomInputsDefinition(
        java.lang.String customInputsDefinition) {
        _modelGlobalPreference.setCustomInputsDefinition(customInputsDefinition);
    }

    @Override
    public boolean isNew() {
        return _modelGlobalPreference.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _modelGlobalPreference.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _modelGlobalPreference.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _modelGlobalPreference.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _modelGlobalPreference.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _modelGlobalPreference.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _modelGlobalPreference.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _modelGlobalPreference.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _modelGlobalPreference.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _modelGlobalPreference.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _modelGlobalPreference.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ModelGlobalPreferenceWrapper((ModelGlobalPreference) _modelGlobalPreference.clone());
    }

    @Override
    public int compareTo(ModelGlobalPreference modelGlobalPreference) {
        return _modelGlobalPreference.compareTo(modelGlobalPreference);
    }

    @Override
    public int hashCode() {
        return _modelGlobalPreference.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<ModelGlobalPreference> toCacheModel() {
        return _modelGlobalPreference.toCacheModel();
    }

    @Override
    public ModelGlobalPreference toEscapedModel() {
        return new ModelGlobalPreferenceWrapper(_modelGlobalPreference.toEscapedModel());
    }

    @Override
    public ModelGlobalPreference toUnescapedModel() {
        return new ModelGlobalPreferenceWrapper(_modelGlobalPreference.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _modelGlobalPreference.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _modelGlobalPreference.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _modelGlobalPreference.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ModelGlobalPreferenceWrapper)) {
            return false;
        }

        ModelGlobalPreferenceWrapper modelGlobalPreferenceWrapper = (ModelGlobalPreferenceWrapper) obj;

        if (Validator.equals(_modelGlobalPreference,
                    modelGlobalPreferenceWrapper._modelGlobalPreference)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ModelGlobalPreference getWrappedModelGlobalPreference() {
        return _modelGlobalPreference;
    }

    @Override
    public ModelGlobalPreference getWrappedModel() {
        return _modelGlobalPreference;
    }

    @Override
    public void resetOriginalValues() {
        _modelGlobalPreference.resetOriginalValues();
    }
}

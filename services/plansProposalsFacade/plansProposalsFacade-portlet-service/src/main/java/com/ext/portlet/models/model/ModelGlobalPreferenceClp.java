package com.ext.portlet.models.model;

import com.ext.portlet.models.service.ModelGlobalPreferenceLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class ModelGlobalPreferenceClp extends BaseModelImpl<ModelGlobalPreference>
    implements ModelGlobalPreference {
    private Long _modelGlobalPreferencePK;
    private Long _modelId;
    private Boolean _visible;
    private Integer _weight;
    private Long _expertEvaluationPageId;
    private Long _modelCategoryId;

    public ModelGlobalPreferenceClp() {
    }

    public Class<?> getModelClass() {
        return ModelGlobalPreference.class;
    }

    public String getModelClassName() {
        return ModelGlobalPreference.class.getName();
    }

    public Long getPrimaryKey() {
        return _modelGlobalPreferencePK;
    }

    public void setPrimaryKey(Long primaryKey) {
        setModelGlobalPreferencePK(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_modelGlobalPreferencePK);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public Long getModelGlobalPreferencePK() {
        return _modelGlobalPreferencePK;
    }

    public void setModelGlobalPreferencePK(Long modelGlobalPreferencePK) {
        _modelGlobalPreferencePK = modelGlobalPreferencePK;
    }

    public Long getModelId() {
        return _modelId;
    }

    public void setModelId(Long modelId) {
        _modelId = modelId;
    }

    public Boolean getVisible() {
        return _visible;
    }

    public void setVisible(Boolean visible) {
        _visible = visible;
    }

    public Integer getWeight() {
        return _weight;
    }

    public void setWeight(Integer weight) {
        _weight = weight;
    }

    public Long getExpertEvaluationPageId() {
        return _expertEvaluationPageId;
    }

    public void setExpertEvaluationPageId(Long expertEvaluationPageId) {
        _expertEvaluationPageId = expertEvaluationPageId;
    }

    public Long getModelCategoryId() {
        return _modelCategoryId;
    }

    public void setModelCategoryId(Long modelCategoryId) {
        _modelCategoryId = modelCategoryId;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            ModelGlobalPreferenceLocalServiceUtil.addModelGlobalPreference(this);
        } else {
            ModelGlobalPreferenceLocalServiceUtil.updateModelGlobalPreference(this);
        }
    }

    @Override
    public ModelGlobalPreference toEscapedModel() {
        return (ModelGlobalPreference) Proxy.newProxyInstance(ModelGlobalPreference.class.getClassLoader(),
            new Class[] { ModelGlobalPreference.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ModelGlobalPreferenceClp clone = new ModelGlobalPreferenceClp();

        clone.setModelGlobalPreferencePK(getModelGlobalPreferencePK());
        clone.setModelId(getModelId());
        clone.setVisible(getVisible());
        clone.setWeight(getWeight());
        clone.setExpertEvaluationPageId(getExpertEvaluationPageId());
        clone.setModelCategoryId(getModelCategoryId());

        return clone;
    }

    public int compareTo(ModelGlobalPreference modelGlobalPreference) {
        Long primaryKey = modelGlobalPreference.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ModelGlobalPreferenceClp modelGlobalPreference = null;

        try {
            modelGlobalPreference = (ModelGlobalPreferenceClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long primaryKey = modelGlobalPreference.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey().hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{modelGlobalPreferencePK=");
        sb.append(getModelGlobalPreferencePK());
        sb.append(", modelId=");
        sb.append(getModelId());
        sb.append(", visible=");
        sb.append(getVisible());
        sb.append(", weight=");
        sb.append(getWeight());
        sb.append(", expertEvaluationPageId=");
        sb.append(getExpertEvaluationPageId());
        sb.append(", modelCategoryId=");
        sb.append(getModelCategoryId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.models.model.ModelGlobalPreference");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>modelGlobalPreferencePK</column-name><column-value><![CDATA[");
        sb.append(getModelGlobalPreferencePK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelId</column-name><column-value><![CDATA[");
        sb.append(getModelId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>visible</column-name><column-value><![CDATA[");
        sb.append(getVisible());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>weight</column-name><column-value><![CDATA[");
        sb.append(getWeight());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>expertEvaluationPageId</column-name><column-value><![CDATA[");
        sb.append(getExpertEvaluationPageId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelCategoryId</column-name><column-value><![CDATA[");
        sb.append(getModelCategoryId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}

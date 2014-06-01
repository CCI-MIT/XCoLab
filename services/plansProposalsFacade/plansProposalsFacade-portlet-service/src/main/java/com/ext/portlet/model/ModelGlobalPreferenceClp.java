package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ModelGlobalPreferenceLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;


public class ModelGlobalPreferenceClp extends BaseModelImpl<ModelGlobalPreference>
    implements ModelGlobalPreference {
    private long _modelGlobalPreferencePK;
    private long _modelId;
    private boolean _visible;
    private int _weight;
    private long _expertEvaluationPageId;
    private long _modelCategoryId;
    private boolean _usesCustomInputs;
    private String _customInputsDefinition;
    private BaseModel<?> _modelGlobalPreferenceRemoteModel;

    public ModelGlobalPreferenceClp() {
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
    public long getPrimaryKey() {
        return _modelGlobalPreferencePK;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setModelGlobalPreferencePK(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _modelGlobalPreferencePK;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
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

    @Override
    public long getModelGlobalPreferencePK() {
        return _modelGlobalPreferencePK;
    }

    @Override
    public void setModelGlobalPreferencePK(long modelGlobalPreferencePK) {
        _modelGlobalPreferencePK = modelGlobalPreferencePK;

        if (_modelGlobalPreferenceRemoteModel != null) {
            try {
                Class<?> clazz = _modelGlobalPreferenceRemoteModel.getClass();

                Method method = clazz.getMethod("setModelGlobalPreferencePK",
                        long.class);

                method.invoke(_modelGlobalPreferenceRemoteModel,
                    modelGlobalPreferencePK);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getModelId() {
        return _modelId;
    }

    @Override
    public void setModelId(long modelId) {
        _modelId = modelId;

        if (_modelGlobalPreferenceRemoteModel != null) {
            try {
                Class<?> clazz = _modelGlobalPreferenceRemoteModel.getClass();

                Method method = clazz.getMethod("setModelId", long.class);

                method.invoke(_modelGlobalPreferenceRemoteModel, modelId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getVisible() {
        return _visible;
    }

    @Override
    public boolean isVisible() {
        return _visible;
    }

    @Override
    public void setVisible(boolean visible) {
        _visible = visible;

        if (_modelGlobalPreferenceRemoteModel != null) {
            try {
                Class<?> clazz = _modelGlobalPreferenceRemoteModel.getClass();

                Method method = clazz.getMethod("setVisible", boolean.class);

                method.invoke(_modelGlobalPreferenceRemoteModel, visible);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getWeight() {
        return _weight;
    }

    @Override
    public void setWeight(int weight) {
        _weight = weight;

        if (_modelGlobalPreferenceRemoteModel != null) {
            try {
                Class<?> clazz = _modelGlobalPreferenceRemoteModel.getClass();

                Method method = clazz.getMethod("setWeight", int.class);

                method.invoke(_modelGlobalPreferenceRemoteModel, weight);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getExpertEvaluationPageId() {
        return _expertEvaluationPageId;
    }

    @Override
    public void setExpertEvaluationPageId(long expertEvaluationPageId) {
        _expertEvaluationPageId = expertEvaluationPageId;

        if (_modelGlobalPreferenceRemoteModel != null) {
            try {
                Class<?> clazz = _modelGlobalPreferenceRemoteModel.getClass();

                Method method = clazz.getMethod("setExpertEvaluationPageId",
                        long.class);

                method.invoke(_modelGlobalPreferenceRemoteModel,
                    expertEvaluationPageId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getModelCategoryId() {
        return _modelCategoryId;
    }

    @Override
    public void setModelCategoryId(long modelCategoryId) {
        _modelCategoryId = modelCategoryId;

        if (_modelGlobalPreferenceRemoteModel != null) {
            try {
                Class<?> clazz = _modelGlobalPreferenceRemoteModel.getClass();

                Method method = clazz.getMethod("setModelCategoryId", long.class);

                method.invoke(_modelGlobalPreferenceRemoteModel, modelCategoryId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getUsesCustomInputs() {
        return _usesCustomInputs;
    }

    @Override
    public boolean isUsesCustomInputs() {
        return _usesCustomInputs;
    }

    @Override
    public void setUsesCustomInputs(boolean usesCustomInputs) {
        _usesCustomInputs = usesCustomInputs;

        if (_modelGlobalPreferenceRemoteModel != null) {
            try {
                Class<?> clazz = _modelGlobalPreferenceRemoteModel.getClass();

                Method method = clazz.getMethod("setUsesCustomInputs",
                        boolean.class);

                method.invoke(_modelGlobalPreferenceRemoteModel,
                    usesCustomInputs);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCustomInputsDefinition() {
        return _customInputsDefinition;
    }

    @Override
    public void setCustomInputsDefinition(String customInputsDefinition) {
        _customInputsDefinition = customInputsDefinition;

        if (_modelGlobalPreferenceRemoteModel != null) {
            try {
                Class<?> clazz = _modelGlobalPreferenceRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomInputsDefinition",
                        String.class);

                method.invoke(_modelGlobalPreferenceRemoteModel,
                    customInputsDefinition);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getModelGlobalPreferenceRemoteModel() {
        return _modelGlobalPreferenceRemoteModel;
    }

    public void setModelGlobalPreferenceRemoteModel(
        BaseModel<?> modelGlobalPreferenceRemoteModel) {
        _modelGlobalPreferenceRemoteModel = modelGlobalPreferenceRemoteModel;
    }

    public Object invokeOnRemoteModel(String methodName,
        Class<?>[] parameterTypes, Object[] parameterValues)
        throws Exception {
        Object[] remoteParameterValues = new Object[parameterValues.length];

        for (int i = 0; i < parameterValues.length; i++) {
            if (parameterValues[i] != null) {
                remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
            }
        }

        Class<?> remoteModelClass = _modelGlobalPreferenceRemoteModel.getClass();

        ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

        Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].isPrimitive()) {
                remoteParameterTypes[i] = parameterTypes[i];
            } else {
                String parameterTypeName = parameterTypes[i].getName();

                remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
            }
        }

        Method method = remoteModelClass.getMethod(methodName,
                remoteParameterTypes);

        Object returnValue = method.invoke(_modelGlobalPreferenceRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ModelGlobalPreferenceLocalServiceUtil.addModelGlobalPreference(this);
        } else {
            ModelGlobalPreferenceLocalServiceUtil.updateModelGlobalPreference(this);
        }
    }

    @Override
    public ModelGlobalPreference toEscapedModel() {
        return (ModelGlobalPreference) ProxyUtil.newProxyInstance(ModelGlobalPreference.class.getClassLoader(),
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
        clone.setUsesCustomInputs(getUsesCustomInputs());
        clone.setCustomInputsDefinition(getCustomInputsDefinition());

        return clone;
    }

    @Override
    public int compareTo(ModelGlobalPreference modelGlobalPreference) {
        long primaryKey = modelGlobalPreference.getPrimaryKey();

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
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ModelGlobalPreferenceClp)) {
            return false;
        }

        ModelGlobalPreferenceClp modelGlobalPreference = (ModelGlobalPreferenceClp) obj;

        long primaryKey = modelGlobalPreference.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(17);

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
        sb.append(", usesCustomInputs=");
        sb.append(getUsesCustomInputs());
        sb.append(", customInputsDefinition=");
        sb.append(getCustomInputsDefinition());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(28);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ModelGlobalPreference");
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
        sb.append(
            "<column><column-name>usesCustomInputs</column-name><column-value><![CDATA[");
        sb.append(getUsesCustomInputs());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>customInputsDefinition</column-name><column-value><![CDATA[");
        sb.append(getCustomInputsDefinition());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}

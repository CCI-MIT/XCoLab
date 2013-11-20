package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlanTypeLocalServiceUtil;

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


public class PlanTypeClp extends BaseModelImpl<PlanType> implements PlanType {
    private long _planTypeId;
    private String _name;
    private String _description;
    private long _modelId;
    private String _modelTypeName;
    private boolean _published;
    private long _publishedCounterpartId;
    private boolean _isDefault;
    private long _defaultModelId;
    private long _defaultScenarioId;
    private BaseModel<?> _planTypeRemoteModel;

    public PlanTypeClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PlanType.class;
    }

    @Override
    public String getModelClassName() {
        return PlanType.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _planTypeId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setPlanTypeId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _planTypeId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("planTypeId", getPlanTypeId());
        attributes.put("name", getName());
        attributes.put("description", getDescription());
        attributes.put("modelId", getModelId());
        attributes.put("modelTypeName", getModelTypeName());
        attributes.put("published", getPublished());
        attributes.put("publishedCounterpartId", getPublishedCounterpartId());
        attributes.put("isDefault", getIsDefault());
        attributes.put("defaultModelId", getDefaultModelId());
        attributes.put("defaultScenarioId", getDefaultScenarioId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long planTypeId = (Long) attributes.get("planTypeId");

        if (planTypeId != null) {
            setPlanTypeId(planTypeId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        Long modelId = (Long) attributes.get("modelId");

        if (modelId != null) {
            setModelId(modelId);
        }

        String modelTypeName = (String) attributes.get("modelTypeName");

        if (modelTypeName != null) {
            setModelTypeName(modelTypeName);
        }

        Boolean published = (Boolean) attributes.get("published");

        if (published != null) {
            setPublished(published);
        }

        Long publishedCounterpartId = (Long) attributes.get(
                "publishedCounterpartId");

        if (publishedCounterpartId != null) {
            setPublishedCounterpartId(publishedCounterpartId);
        }

        Boolean isDefault = (Boolean) attributes.get("isDefault");

        if (isDefault != null) {
            setIsDefault(isDefault);
        }

        Long defaultModelId = (Long) attributes.get("defaultModelId");

        if (defaultModelId != null) {
            setDefaultModelId(defaultModelId);
        }

        Long defaultScenarioId = (Long) attributes.get("defaultScenarioId");

        if (defaultScenarioId != null) {
            setDefaultScenarioId(defaultScenarioId);
        }
    }

    @Override
    public long getPlanTypeId() {
        return _planTypeId;
    }

    @Override
    public void setPlanTypeId(long planTypeId) {
        _planTypeId = planTypeId;

        if (_planTypeRemoteModel != null) {
            try {
                Class<?> clazz = _planTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanTypeId", long.class);

                method.invoke(_planTypeRemoteModel, planTypeId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public void setName(String name) {
        _name = name;

        if (_planTypeRemoteModel != null) {
            try {
                Class<?> clazz = _planTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_planTypeRemoteModel, name);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDescription() {
        return _description;
    }

    @Override
    public void setDescription(String description) {
        _description = description;

        if (_planTypeRemoteModel != null) {
            try {
                Class<?> clazz = _planTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setDescription", String.class);

                method.invoke(_planTypeRemoteModel, description);
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

        if (_planTypeRemoteModel != null) {
            try {
                Class<?> clazz = _planTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setModelId", long.class);

                method.invoke(_planTypeRemoteModel, modelId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModelTypeName() {
        return _modelTypeName;
    }

    @Override
    public void setModelTypeName(String modelTypeName) {
        _modelTypeName = modelTypeName;

        if (_planTypeRemoteModel != null) {
            try {
                Class<?> clazz = _planTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setModelTypeName", String.class);

                method.invoke(_planTypeRemoteModel, modelTypeName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getPublished() {
        return _published;
    }

    @Override
    public boolean isPublished() {
        return _published;
    }

    @Override
    public void setPublished(boolean published) {
        _published = published;

        if (_planTypeRemoteModel != null) {
            try {
                Class<?> clazz = _planTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setPublished", boolean.class);

                method.invoke(_planTypeRemoteModel, published);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getPublishedCounterpartId() {
        return _publishedCounterpartId;
    }

    @Override
    public void setPublishedCounterpartId(long publishedCounterpartId) {
        _publishedCounterpartId = publishedCounterpartId;

        if (_planTypeRemoteModel != null) {
            try {
                Class<?> clazz = _planTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setPublishedCounterpartId",
                        long.class);

                method.invoke(_planTypeRemoteModel, publishedCounterpartId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getIsDefault() {
        return _isDefault;
    }

    @Override
    public boolean isIsDefault() {
        return _isDefault;
    }

    @Override
    public void setIsDefault(boolean isDefault) {
        _isDefault = isDefault;

        if (_planTypeRemoteModel != null) {
            try {
                Class<?> clazz = _planTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setIsDefault", boolean.class);

                method.invoke(_planTypeRemoteModel, isDefault);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getDefaultModelId() {
        return _defaultModelId;
    }

    @Override
    public void setDefaultModelId(long defaultModelId) {
        _defaultModelId = defaultModelId;

        if (_planTypeRemoteModel != null) {
            try {
                Class<?> clazz = _planTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setDefaultModelId", long.class);

                method.invoke(_planTypeRemoteModel, defaultModelId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getDefaultScenarioId() {
        return _defaultScenarioId;
    }

    @Override
    public void setDefaultScenarioId(long defaultScenarioId) {
        _defaultScenarioId = defaultScenarioId;

        if (_planTypeRemoteModel != null) {
            try {
                Class<?> clazz = _planTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setDefaultScenarioId",
                        long.class);

                method.invoke(_planTypeRemoteModel, defaultScenarioId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlanTypeRemoteModel() {
        return _planTypeRemoteModel;
    }

    public void setPlanTypeRemoteModel(BaseModel<?> planTypeRemoteModel) {
        _planTypeRemoteModel = planTypeRemoteModel;
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

        Class<?> remoteModelClass = _planTypeRemoteModel.getClass();

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

        Object returnValue = method.invoke(_planTypeRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanTypeLocalServiceUtil.addPlanType(this);
        } else {
            PlanTypeLocalServiceUtil.updatePlanType(this);
        }
    }

    @Override
    public PlanType toEscapedModel() {
        return (PlanType) ProxyUtil.newProxyInstance(PlanType.class.getClassLoader(),
            new Class[] { PlanType.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanTypeClp clone = new PlanTypeClp();

        clone.setPlanTypeId(getPlanTypeId());
        clone.setName(getName());
        clone.setDescription(getDescription());
        clone.setModelId(getModelId());
        clone.setModelTypeName(getModelTypeName());
        clone.setPublished(getPublished());
        clone.setPublishedCounterpartId(getPublishedCounterpartId());
        clone.setIsDefault(getIsDefault());
        clone.setDefaultModelId(getDefaultModelId());
        clone.setDefaultScenarioId(getDefaultScenarioId());

        return clone;
    }

    @Override
    public int compareTo(PlanType planType) {
        long primaryKey = planType.getPrimaryKey();

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

        if (!(obj instanceof PlanTypeClp)) {
            return false;
        }

        PlanTypeClp planType = (PlanTypeClp) obj;

        long primaryKey = planType.getPrimaryKey();

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
        StringBundler sb = new StringBundler(21);

        sb.append("{planTypeId=");
        sb.append(getPlanTypeId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", modelId=");
        sb.append(getModelId());
        sb.append(", modelTypeName=");
        sb.append(getModelTypeName());
        sb.append(", published=");
        sb.append(getPublished());
        sb.append(", publishedCounterpartId=");
        sb.append(getPublishedCounterpartId());
        sb.append(", isDefault=");
        sb.append(getIsDefault());
        sb.append(", defaultModelId=");
        sb.append(getDefaultModelId());
        sb.append(", defaultScenarioId=");
        sb.append(getDefaultScenarioId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(34);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanType");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planTypeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelId</column-name><column-value><![CDATA[");
        sb.append(getModelId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelTypeName</column-name><column-value><![CDATA[");
        sb.append(getModelTypeName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>published</column-name><column-value><![CDATA[");
        sb.append(getPublished());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>publishedCounterpartId</column-name><column-value><![CDATA[");
        sb.append(getPublishedCounterpartId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>isDefault</column-name><column-value><![CDATA[");
        sb.append(getIsDefault());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>defaultModelId</column-name><column-value><![CDATA[");
        sb.append(getDefaultModelId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>defaultScenarioId</column-name><column-value><![CDATA[");
        sb.append(getDefaultScenarioId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}

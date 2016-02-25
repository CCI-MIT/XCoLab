package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ConfigurationAttributeLocalServiceUtil;
import com.ext.portlet.service.persistence.ConfigurationAttributePK;

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


public class ConfigurationAttributeClp extends BaseModelImpl<ConfigurationAttribute>
    implements ConfigurationAttribute {
    private String _name;
    private long _additionalId;
    private long _numericValue;
    private String _stringValue;
    private double _realValue;
    private BaseModel<?> _configurationAttributeRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public ConfigurationAttributeClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ConfigurationAttribute.class;
    }

    @Override
    public String getModelClassName() {
        return ConfigurationAttribute.class.getName();
    }

    @Override
    public ConfigurationAttributePK getPrimaryKey() {
        return new ConfigurationAttributePK(_name, _additionalId);
    }

    @Override
    public void setPrimaryKey(ConfigurationAttributePK primaryKey) {
        setName(primaryKey.name);
        setAdditionalId(primaryKey.additionalId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new ConfigurationAttributePK(_name, _additionalId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((ConfigurationAttributePK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("name", getName());
        attributes.put("additionalId", getAdditionalId());
        attributes.put("numericValue", getNumericValue());
        attributes.put("stringValue", getStringValue());
        attributes.put("realValue", getRealValue());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        Long additionalId = (Long) attributes.get("additionalId");

        if (additionalId != null) {
            setAdditionalId(additionalId);
        }

        Long numericValue = (Long) attributes.get("numericValue");

        if (numericValue != null) {
            setNumericValue(numericValue);
        }

        String stringValue = (String) attributes.get("stringValue");

        if (stringValue != null) {
            setStringValue(stringValue);
        }

        Double realValue = (Double) attributes.get("realValue");

        if (realValue != null) {
            setRealValue(realValue);
        }
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public void setName(String name) {
        _name = name;

        if (_configurationAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _configurationAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_configurationAttributeRemoteModel, name);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getAdditionalId() {
        return _additionalId;
    }

    @Override
    public void setAdditionalId(long additionalId) {
        _additionalId = additionalId;

        if (_configurationAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _configurationAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setAdditionalId", long.class);

                method.invoke(_configurationAttributeRemoteModel, additionalId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getNumericValue() {
        return _numericValue;
    }

    @Override
    public void setNumericValue(long numericValue) {
        _numericValue = numericValue;

        if (_configurationAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _configurationAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setNumericValue", long.class);

                method.invoke(_configurationAttributeRemoteModel, numericValue);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getStringValue() {
        return _stringValue;
    }

    @Override
    public void setStringValue(String stringValue) {
        _stringValue = stringValue;

        if (_configurationAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _configurationAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setStringValue", String.class);

                method.invoke(_configurationAttributeRemoteModel, stringValue);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getRealValue() {
        return _realValue;
    }

    @Override
    public void setRealValue(double realValue) {
        _realValue = realValue;

        if (_configurationAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _configurationAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setRealValue", double.class);

                method.invoke(_configurationAttributeRemoteModel, realValue);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getConfigurationAttributeRemoteModel() {
        return _configurationAttributeRemoteModel;
    }

    public void setConfigurationAttributeRemoteModel(
        BaseModel<?> configurationAttributeRemoteModel) {
        _configurationAttributeRemoteModel = configurationAttributeRemoteModel;
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

        Class<?> remoteModelClass = _configurationAttributeRemoteModel.getClass();

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

        Object returnValue = method.invoke(_configurationAttributeRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ConfigurationAttributeLocalServiceUtil.addConfigurationAttribute(this);
        } else {
            ConfigurationAttributeLocalServiceUtil.updateConfigurationAttribute(this);
        }
    }

    @Override
    public ConfigurationAttribute toEscapedModel() {
        return (ConfigurationAttribute) ProxyUtil.newProxyInstance(ConfigurationAttribute.class.getClassLoader(),
            new Class[] { ConfigurationAttribute.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ConfigurationAttributeClp clone = new ConfigurationAttributeClp();

        clone.setName(getName());
        clone.setAdditionalId(getAdditionalId());
        clone.setNumericValue(getNumericValue());
        clone.setStringValue(getStringValue());
        clone.setRealValue(getRealValue());

        return clone;
    }

    @Override
    public int compareTo(ConfigurationAttribute configurationAttribute) {
        ConfigurationAttributePK primaryKey = configurationAttribute.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ConfigurationAttributeClp)) {
            return false;
        }

        ConfigurationAttributeClp configurationAttribute = (ConfigurationAttributeClp) obj;

        ConfigurationAttributePK primaryKey = configurationAttribute.getPrimaryKey();

        if (getPrimaryKey().equals(primaryKey)) {
            return true;
        } else {
            return false;
        }
    }

    public Class<?> getClpSerializerClass() {
        return _clpSerializerClass;
    }

    @Override
    public int hashCode() {
        return getPrimaryKey().hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{name=");
        sb.append(getName());
        sb.append(", additionalId=");
        sb.append(getAdditionalId());
        sb.append(", numericValue=");
        sb.append(getNumericValue());
        sb.append(", stringValue=");
        sb.append(getStringValue());
        sb.append(", realValue=");
        sb.append(getRealValue());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ConfigurationAttribute");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>additionalId</column-name><column-value><![CDATA[");
        sb.append(getAdditionalId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>numericValue</column-name><column-value><![CDATA[");
        sb.append(getNumericValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>stringValue</column-name><column-value><![CDATA[");
        sb.append(getStringValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>realValue</column-name><column-value><![CDATA[");
        sb.append(getRealValue());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}

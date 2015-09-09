package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlanAttributeLocalServiceUtil;

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


public class PlanAttributeClp extends BaseModelImpl<PlanAttribute>
    implements PlanAttribute {
    private long _attributeId;
    private long _planId;
    private String _attributeName;
    private String _attributeValue;
    private BaseModel<?> _planAttributeRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public PlanAttributeClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PlanAttribute.class;
    }

    @Override
    public String getModelClassName() {
        return PlanAttribute.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _attributeId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setAttributeId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _attributeId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("attributeId", getAttributeId());
        attributes.put("planId", getPlanId());
        attributes.put("attributeName", getAttributeName());
        attributes.put("attributeValue", getAttributeValue());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long attributeId = (Long) attributes.get("attributeId");

        if (attributeId != null) {
            setAttributeId(attributeId);
        }

        Long planId = (Long) attributes.get("planId");

        if (planId != null) {
            setPlanId(planId);
        }

        String attributeName = (String) attributes.get("attributeName");

        if (attributeName != null) {
            setAttributeName(attributeName);
        }

        String attributeValue = (String) attributes.get("attributeValue");

        if (attributeValue != null) {
            setAttributeValue(attributeValue);
        }
    }

    @Override
    public long getAttributeId() {
        return _attributeId;
    }

    @Override
    public void setAttributeId(long attributeId) {
        _attributeId = attributeId;

        if (_planAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _planAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setAttributeId", long.class);

                method.invoke(_planAttributeRemoteModel, attributeId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getPlanId() {
        return _planId;
    }

    @Override
    public void setPlanId(long planId) {
        _planId = planId;

        if (_planAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _planAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanId", long.class);

                method.invoke(_planAttributeRemoteModel, planId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAttributeName() {
        return _attributeName;
    }

    @Override
    public void setAttributeName(String attributeName) {
        _attributeName = attributeName;

        if (_planAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _planAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setAttributeName", String.class);

                method.invoke(_planAttributeRemoteModel, attributeName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAttributeValue() {
        return _attributeValue;
    }

    @Override
    public void setAttributeValue(String attributeValue) {
        _attributeValue = attributeValue;

        if (_planAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _planAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setAttributeValue",
                        String.class);

                method.invoke(_planAttributeRemoteModel, attributeValue);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlanAttributeRemoteModel() {
        return _planAttributeRemoteModel;
    }

    public void setPlanAttributeRemoteModel(
        BaseModel<?> planAttributeRemoteModel) {
        _planAttributeRemoteModel = planAttributeRemoteModel;
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

        Class<?> remoteModelClass = _planAttributeRemoteModel.getClass();

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

        Object returnValue = method.invoke(_planAttributeRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanAttributeLocalServiceUtil.addPlanAttribute(this);
        } else {
            PlanAttributeLocalServiceUtil.updatePlanAttribute(this);
        }
    }

    @Override
    public PlanAttribute toEscapedModel() {
        return (PlanAttribute) ProxyUtil.newProxyInstance(PlanAttribute.class.getClassLoader(),
            new Class[] { PlanAttribute.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanAttributeClp clone = new PlanAttributeClp();

        clone.setAttributeId(getAttributeId());
        clone.setPlanId(getPlanId());
        clone.setAttributeName(getAttributeName());
        clone.setAttributeValue(getAttributeValue());

        return clone;
    }

    @Override
    public int compareTo(PlanAttribute planAttribute) {
        long primaryKey = planAttribute.getPrimaryKey();

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

        if (!(obj instanceof PlanAttributeClp)) {
            return false;
        }

        PlanAttributeClp planAttribute = (PlanAttributeClp) obj;

        long primaryKey = planAttribute.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
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
        return (int) getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{attributeId=");
        sb.append(getAttributeId());
        sb.append(", planId=");
        sb.append(getPlanId());
        sb.append(", attributeName=");
        sb.append(getAttributeName());
        sb.append(", attributeValue=");
        sb.append(getAttributeValue());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanAttribute");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>attributeId</column-name><column-value><![CDATA[");
        sb.append(getAttributeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>attributeName</column-name><column-value><![CDATA[");
        sb.append(getAttributeName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>attributeValue</column-name><column-value><![CDATA[");
        sb.append(getAttributeValue());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
